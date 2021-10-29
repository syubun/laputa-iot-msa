package com.laputa.iot.common.oss.template;


import com.google.common.collect.HashMultimap;
import com.laputa.iot.common.oss.model.ObjectInfo;
import com.laputa.iot.common.oss.properties.FileServerProperties;
import com.laputa.iot.common.oss.util.CustomMinioClient;
import com.laputa.iot.common.oss.vo.MinioItem;
import com.laputa.iot.common.oss.vo.MinioObject;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import io.minio.messages.Part;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Minio 配置
 *
 * @author sommer.jiang
 * @date 2021/2/11
 * <p>
 */
@ConditionalOnClass(MinioObject.class)
@ConditionalOnProperty(prefix = FileServerProperties.PREFIX, name = "type", havingValue = FileServerProperties.TYPE_MIOIO)
public class MinioTemplate implements InitializingBean {
    private static final String DEF_CONTEXT_TYPE = "application/octet-stream";
    private static final String PATH_SPLIT = "/";
    private static final int FILE_PART_SIZE = 1024 * 1024 * 10;
    private CustomMinioClient customMinioClient;
    @Autowired
    private FileServerProperties fileProperties;

    private MinioClient client;

    @Override
    public void afterPropertiesSet()  {

//        AwsClientBuilder.EndpointConfiguration endpoint = new AwsClientBuilder.EndpointConfiguration(fileProperties.getS3().getEndpoint(), fileProperties.getS3().getRegion());
//        AWSCredentials credentials = new BasicAWSCredentials(fileProperties.getS3().getAccessKey(), fileProperties.getS3().getAccessKeySecret());
//        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(credentials);

        this.client =  MinioClient.builder()
                .endpoint(fileProperties.getOss().getEndpoint())
               .credentials(fileProperties.getOss().getAccessKey(),fileProperties.getOss().getAccessKeySecret())
                .build();
        customMinioClient = new CustomMinioClient(this.client);
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

    /**
     * 是否存在bucket
     * @param bucketName
     * @return
     */
    @SneakyThrows
    public boolean bucketExists(String bucketName) {
        BucketExistsArgs bucketExistsArgs = BucketExistsArgs.builder().bucket(bucketName).build();
        return client.bucketExists(bucketExistsArgs);
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
    private ObjectInfo upload(String bucketName, String objectName, InputStream is, int size, String contentType) throws IOException, NoSuchAlgorithmException, InvalidKeyException,  InvalidResponseException, InternalException,   InsufficientDataException, ErrorResponseException,  ServerException, XmlParserException {
        client.putObject( PutObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .stream(is, size, FILE_PART_SIZE)
                .contentType(contentType)
                .build());


        ObjectInfo obj = new ObjectInfo();
        obj.setObjectPath(bucketName + PATH_SPLIT + objectName);
        obj.setObjectUrl(fileProperties.getOss().getEndpoint() + PATH_SPLIT + obj.getObjectPath());
        return obj;
    }

    public void delete(String objectName) {
        delete(fileProperties.getOss().getBucketName(), objectName);
    }

    @SneakyThrows
    public void delete(String bucketName, String objectName) {
            client.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());

    }

    /**
     * 获取预览地址
     * @param bucketName bucket名称
     * @param objectName 对象名
     * @param expires 有效时间(分钟)，最大7天有效
     * @return
     */
    @SneakyThrows
    public String getViewUrl(String bucketName, String objectName, int expires) {

        return  client.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().bucket(bucketName)
                .expiry(expires).object(objectName).build());
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
        InputStream object = client.getObject(GetObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName).build());
        IOUtils.copy(object, os);
    }


    /**
     * 创建bucket
     * @param bucketName bucket名称
     */
    @SneakyThrows
    public void createBucket(String bucketName) {
        if (!client.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
            client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }

    /**
     * 获取全部bucket
     * <p>
     *
     * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/s3-2006-03-01/ListBuckets">AWS
     * API Documentation</a>
     * @return
     */
    @SneakyThrows
    public List<Bucket> getAllBuckets() {
        return client.listBuckets();
    }

    /**
     * @param bucketName bucket名称
     * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/s3-2006-03-01/ListBuckets">AWS
     * API Documentation</a>
     */
    @SneakyThrows
    public Optional<Bucket> getBucket(String bucketName) {
        return client.listBuckets().stream().filter(b -> b.name().equals(bucketName)).findFirst();
    }

    /**
     * @param bucketName bucket名称
     * @see <a href=
     * "http://docs.aws.amazon.com/goto/WebAPI/s3-2006-03-01/DeleteBucket">AWS API
     * Documentation</a>
     */
    @SneakyThrows
    public void removeBucket(String bucketName) {
        client.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
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
    public List<MinioItem>  getAllObjectsByPrefix(String bucketName, String prefix, boolean recursive) {
        List<MinioItem> objectList = new ArrayList<>();
        Iterable<Result<Item>> objectsIterator = client
                .listObjects(ListObjectsArgs.builder()
                        .bucket(bucketName)
                        .prefix(prefix)
                        .recursive(recursive).build());

        while (objectsIterator.iterator().hasNext()) {
            objectList.add(new MinioItem(objectsIterator.iterator().next().get()));
        }
        return objectList;

    }

    /**
     * 获取文件外链
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param expires 过期时间 <=7
     * @return url
     * @see
     */
    @SneakyThrows
    public String getObjectURL(String bucketName, String objectName, Integer expires) {

        return client.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().object(objectName)
                .bucket(bucketName)
                .method(Method.GET)
                .expiry(expires).build());
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
    public InputStream getObject(String bucketName, String objectName) {
        return client.getObject(GetObjectArgs.builder()
                .bucket(bucketName).object(objectName).build());
    }


    /**
     * 获取文件信息
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#statObject
     * @return
     */
    public StatObjectResponse getObjectInfo(String bucketName, String objectName) throws Exception {
        return client.statObject(StatObjectArgs.builder()
                .object(objectName)
                .bucket(bucketName).build());
    }

    /**
     * 上传文件
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param stream 文件流
     * @throws Exception
     */
    public void putObject(String bucketName, String objectName, InputStream stream) throws Exception {
        putObject(bucketName, objectName, stream, stream.available(), "application/octet-stream");
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
    public void putObject(String bucketName, String objectName, InputStream stream, long size,
                                     String contextType) throws Exception {
        // 上传
        client.putObject(PutObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .stream(stream,size,FILE_PART_SIZE)
                .contentType(contextType)
        .build());


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
        client.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }


    /**
     * 单文件签名上传
     *
     * @param objectName 文件全路径名称
     * @return /
     */
    public String getUploadObjectUrl(String objectName) {
        // 上传文件时携带content-type头即可
        /*if (StrUtil.isBlank(contentType)) {
            contentType = "application/octet-stream";
        }
        HashMultimap<String, String> headers = HashMultimap.create();
        headers.put("Content-Type", contentType);*/
        try {
            return customMinioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.PUT)
                            .bucket(fileProperties.getOss().getBucketName())
                            .object(objectName)
                            .expiry(1, TimeUnit.DAYS)
                            //.extraHeaders(headers)
                            .build()
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *  初始化分片上传
     *
     * @param objectName 文件全路径名称
     * @param partCount 分片数量
     * @param contentType 类型，如果类型使用默认流会导致无法预览
     * @return /
     */
    public Map<String, Object> initMultiPartUpload(String objectName, int partCount, String contentType) {
        Map<String, Object> result = new HashMap<>();
        try {
            if (StringUtils.isBlank(contentType)) {
                contentType = "application/octet-stream";
            }
            HashMultimap<String, String> headers = HashMultimap.create();
            headers.put("Content-Type", contentType);
            String uploadId = customMinioClient.initMultiPartUpload(fileProperties.getOss().getBucketName(), null, objectName, headers, null);

            result.put("uploadId", uploadId);
            List<String> partList = new ArrayList<>();

            Map<String, String> reqParams = new HashMap<>();
            //reqParams.put("response-content-type", "application/json");
            reqParams.put("uploadId", uploadId);
            for (int i = 1; i <= partCount; i++) {
                reqParams.put("partNumber", String.valueOf(i));
                String uploadUrl = customMinioClient.getPresignedObjectUrl(
                        GetPresignedObjectUrlArgs.builder()
                                .method(Method.PUT)
                                .bucket(fileProperties.getOss().getBucketName())
                                .object(objectName)
                                .expiry(1, TimeUnit.DAYS)
                                .extraQueryParams(reqParams)
                                .build());
                partList.add(uploadUrl);
            }
            result.put("uploadUrls", partList);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return result;
    }

    /**
     * 分片上传完后合并
     *
     * @param objectName 文件全路径名称
     * @param uploadId 返回的uploadId
     * @return /
     */
    public boolean mergeMultipartUpload(String objectName, String uploadId) {
        try {
            //TODO::目前仅做了最大1000分片
            Part[] parts = new Part[1000];
            ListPartsResponse partResult = customMinioClient.listMultipart(fileProperties.getOss().getBucketName(), null, objectName, 1000, 0, uploadId, null, null);
            int partNumber = 1;
            for (Part part : partResult.result().partList()) {
                parts[partNumber - 1] = new Part(partNumber, part.etag());
                partNumber++;
            }
            customMinioClient.mergeMultipartUpload(fileProperties.getOss().getBucketName(), null, objectName, uploadId, parts, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


}
