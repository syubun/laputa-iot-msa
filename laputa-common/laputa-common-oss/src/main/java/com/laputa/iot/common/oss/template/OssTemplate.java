package com.laputa.iot.common.oss.template;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.laputa.iot.common.oss.model.ObjectInfo;
import com.laputa.iot.common.oss.properties.FileServerProperties;
import lombok.Cleanup;
import lombok.SneakyThrows;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.*;

/**
 * aws s3配置
 *
 * @author sommer.jiang
 * @date 2021/2/11
 * <p>
 * Blog: https://zlt2000.gitee.io
 * Github: https://github.com/zlt2000
 */
@ConditionalOnClass(AmazonS3.class)
@ConditionalOnProperty(prefix = FileServerProperties.PREFIX, name = "type", havingValue = FileServerProperties.TYPE_S3)
public class OssTemplate implements InitializingBean {
    private static final String DEF_CONTEXT_TYPE = "application/octet-stream";
    private static final String PATH_SPLIT = "/";

    @Autowired
    private FileServerProperties fileProperties;

    private AmazonS3 amazonS3;

    @Override
    public void afterPropertiesSet() {
        ClientConfiguration config = new ClientConfiguration();
        AwsClientBuilder.EndpointConfiguration endpoint = new AwsClientBuilder.EndpointConfiguration(fileProperties.getOss().getEndpoint(), fileProperties.getOss().getRegion());
        AWSCredentials credentials = new BasicAWSCredentials(fileProperties.getOss().getAccessKey(), fileProperties.getOss().getAccessKeySecret());
        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(credentials);
        this.amazonS3 = AmazonS3Client.builder()
                .withEndpointConfiguration(endpoint)
                .withClientConfiguration(config)
                .withCredentials(awsCredentialsProvider)
                .withPathStyleAccessEnabled(fileProperties.getOss().getPathStyleAccessEnabled())
                .disableChunkedEncoding()
                .build();
    }

    @SneakyThrows
    public ObjectInfo upload(String fileName, InputStream is) {
        return upload(fileProperties.getOss().getBucketName(), fileName, is, is.available(), DEF_CONTEXT_TYPE);
    }

    @SneakyThrows
    public ObjectInfo upload(MultipartFile file) {
        return upload(fileProperties.getOss().getBucketName(), file.getOriginalFilename(), file.getInputStream()
                , ((Long)file.getSize()).intValue(), file.getContentType());
    }

    @SneakyThrows
    public ObjectInfo upload(String bucketName, String fileName, InputStream is) {
        return upload(bucketName, fileName, is, is.available(), DEF_CONTEXT_TYPE);
    }

    /**
     * 上传对象
     * @param bucketName bucket名称
     * @param objectName 对象名
     * @param is 对象流
     * @param size 大小
     * @param contentType 类型
     */
    private ObjectInfo upload(String bucketName, String objectName, InputStream is, int size, String contentType) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(size);
        objectMetadata.setContentType(contentType);
        PutObjectRequest putObjectRequest = new PutObjectRequest(
                bucketName, objectName, is, objectMetadata);
        putObjectRequest.getRequestClientOptions().setReadLimit(size + 1);
        amazonS3.putObject(putObjectRequest);

        ObjectInfo obj = new ObjectInfo();
        obj.setObjectPath(bucketName + PATH_SPLIT + objectName);
        obj.setObjectUrl(fileProperties.getOss().getEndpoint() + PATH_SPLIT + obj.getObjectPath());
        return obj;
    }

    public void delete(String objectName) {
        delete(fileProperties.getOss().getBucketName(), objectName);
    }

    public void delete(String bucketName, String objectName) {
        amazonS3.deleteObject(bucketName, objectName);
    }

    /**
     * 获取预览地址
     * @param bucketName bucket名称
     * @param objectName 对象名
     * @param expires 有效时间(分钟)，最大7天有效
     * @return
     */
    public String getViewUrl(String bucketName, String objectName, int expires) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, expires);
        URL url = amazonS3.generatePresignedUrl(bucketName, objectName, cal.getTime());
        return url.toString();
    }

    public void out(String objectName, OutputStream os) {
        out(fileProperties.getOss().getBucketName(), objectName, os);
    }

    /**
     * 输出对象
     * @param bucketName bucket名称
     * @param objectName 对象名
     * @param os 输出流
     */
    @SneakyThrows
    public void out(String bucketName, String objectName, OutputStream os) {
        S3Object s3Object = amazonS3.getObject(bucketName, objectName);
        try (
                S3ObjectInputStream s3is = s3Object.getObjectContent();
        ) {
            IOUtils.copy(s3is, os);
        }
    }


    /**
     * 创建bucket
     * @param bucketName bucket名称
     */
    @SneakyThrows
    public void createBucket(String bucketName) {
        if (!amazonS3.doesBucketExistV2(bucketName)) {
            amazonS3.createBucket((bucketName));
        }
    }

    /**
     * 获取全部bucket
     * <p>
     *
     * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/s3-2006-03-01/ListBuckets">AWS
     * API Documentation</a>
     */
    @SneakyThrows
    public List<Bucket> getAllBuckets() {
        return amazonS3.listBuckets();
    }

    /**
     * @param bucketName bucket名称
     * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/s3-2006-03-01/ListBuckets">AWS
     * API Documentation</a>
     */
    @SneakyThrows
    public Optional<Bucket> getBucket(String bucketName) {
        return amazonS3.listBuckets().stream().filter(b -> b.getName().equals(bucketName)).findFirst();
    }

    /**
     * @param bucketName bucket名称
     * @see <a href=
     * "http://docs.aws.amazon.com/goto/WebAPI/s3-2006-03-01/DeleteBucket">AWS API
     * Documentation</a>
     */
    @SneakyThrows
    public void removeBucket(String bucketName) {
        amazonS3.deleteBucket(bucketName);
    }

    /**
     * 根据文件前置查询文件
     * @param bucketName bucket名称
     * @param prefix 前缀
     * @param recursive 是否递归查询
     * @return S3ObjectSummary 列表
     * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/s3-2006-03-01/ListObjects">AWS
     * API Documentation</a>
     */
    @SneakyThrows
    public List<S3ObjectSummary> getAllObjectsByPrefix(String bucketName, String prefix, boolean recursive) {
        ObjectListing objectListing = amazonS3.listObjects(bucketName, prefix);
        return new ArrayList<>(objectListing.getObjectSummaries());
    }

    /**
     * 获取文件外链
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param expires 过期时间 <=7
     * @return url
     * @see AmazonS3#generatePresignedUrl(String bucketName, String key, Date expiration)
     */
    @SneakyThrows
    public String getObjectURL(String bucketName, String objectName, Integer expires) {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, expires);
        URL url = amazonS3.generatePresignedUrl(bucketName, objectName, calendar.getTime());
        return url.toString();
    }

    /**
     * 获取文件
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @return 二进制流
     * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/s3-2006-03-01/GetObject">AWS
     * API Documentation</a>
     */
    @SneakyThrows
    public S3Object getObject(String bucketName, String objectName) {
        return amazonS3.getObject(bucketName, objectName);
    }

    /**
     * 上传文件
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param stream 文件流
     * @throws Exception
     */
    public void putObject(String bucketName, String objectName, InputStream stream) throws Exception {
        putObject(bucketName, objectName, stream, (long) stream.available(), "application/octet-stream");
    }

    /**
     * 上传文件
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param stream 文件流
     * @param size 大小
     * @param contextType 类型
     * @throws Exception
     * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/s3-2006-03-01/PutObject">AWS
     * API Documentation</a>
     */
    public PutObjectResult putObject(String bucketName, String objectName, InputStream stream, long size,
                                     String contextType) throws Exception {
        // String fileName = getFileName(objectName);
        byte[] bytes = IOUtils.toByteArray(stream);
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(size);
        objectMetadata.setContentType(contextType);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        // 上传
        return amazonS3.putObject(bucketName, objectName, byteArrayInputStream, objectMetadata);

    }

    /**
     * 获取文件信息
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/s3-2006-03-01/GetObject">AWS
     * API Documentation</a>
     */
    public S3Object getObjectInfo(String bucketName, String objectName) throws Exception {
        @Cleanup
        S3Object object = amazonS3.getObject(bucketName, objectName);
        return object;
    }

    /**
     * 删除文件
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @throws Exception
     * @see <a href=
     * "http://docs.aws.amazon.com/goto/WebAPI/s3-2006-03-01/DeleteObject">AWS API
     * Documentation</a>
     */
    public void removeObject(String bucketName, String objectName) throws Exception {
        amazonS3.deleteObject(bucketName, objectName);
    }


}
