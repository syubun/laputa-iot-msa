package com.laputa.iot.common.es.config;

import com.laputa.iot.common.es.properties.RestClientPoolProperties;

import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

import org.springframework.boot.autoconfigure.elasticsearch.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

/**
 * es自动配置
 *
 * @author sommer.jiang
 * @date 2020/3/28
 * <p>
 */
@EnableConfigurationProperties(RestClientPoolProperties.class)
public class RestAutoConfigure {
    @Bean
    public RestClientBuilderCustomizer restClientBuilderCustomizer(RestClientPoolProperties poolProperties
           ) {
        return (builder) -> {
            setRequestConfig(builder, poolProperties);

            setHttpClientConfig(builder, poolProperties);
        };
    }

    /**
     * 异步httpclient连接延时配置
     */
    private void setRequestConfig(RestClientBuilder builder, RestClientPoolProperties poolProperties){
        builder.setRequestConfigCallback(requestConfigBuilder -> {
            requestConfigBuilder.setConnectTimeout(poolProperties.getConnectTimeOut())
                    .setSocketTimeout(poolProperties.getSocketTimeOut())
                    .setConnectionRequestTimeout(poolProperties.getConnectionRequestTimeOut());
            return requestConfigBuilder;
        });
    }

    /**
     * 异步httpclient连接数配置
     */
    private void setHttpClientConfig(RestClientBuilder builder, RestClientPoolProperties poolProperties){
        builder.setHttpClientConfigCallback(httpClientBuilder -> {
            httpClientBuilder.setMaxConnTotal(poolProperties.getMaxConnectNum())
                    .setMaxConnPerRoute(poolProperties.getMaxConnectPerRoute());

            PropertyMapper map = PropertyMapper.get().alwaysApplyingWhenNonNull();

            return httpClientBuilder;
        });
    }

    @Bean
    @ConditionalOnMissingBean
    public ElasticsearchRestTemplate elasticsearchRestTemplate(RestHighLevelClient restHighLevelClient) {
        return new ElasticsearchRestTemplate(restHighLevelClient);
    }
}
