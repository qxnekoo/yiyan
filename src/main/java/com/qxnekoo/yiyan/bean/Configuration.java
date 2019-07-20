package com.qxnekoo.yiyan.bean;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
@ConfigurationProperties(prefix = "yiyan")
public class Configuration {
   private String url;
   private int corePoolSize=2;
   private int maxPoolSize=2;
    @Bean
    public CloseableHttpClient httpClient() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        return httpClient;
    }
    @Bean
    public HttpGet httpGet() {
        HttpGet httpGet = new HttpGet(url);
        return httpGet;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    @Bean
    public ExecutorService executorService() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("get-info-%d").build();
        ExecutorService executor = new ThreadPoolExecutor(corePoolSize,maxPoolSize,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>(),
                namedThreadFactory,new ThreadPoolExecutor.AbortPolicy());
        return executor;
    }
}
