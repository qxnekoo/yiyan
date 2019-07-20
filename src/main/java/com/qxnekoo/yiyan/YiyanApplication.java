package com.qxnekoo.yiyan;

import com.qxnekoo.yiyan.Thread.YiYanTask;

import com.qxnekoo.yiyan.service.RequestService;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

@SpringBootApplication
public class YiyanApplication implements CommandLineRunner {
    public static final CountDownLatch latch = new CountDownLatch(4);

    @Autowired
    ExecutorService executorService;
    @Resource
    RequestService requestService;
    @Resource
    CloseableHttpClient httpClient;
    @Resource
    HttpGet httpGet;
    public static void main(String[] args) {
        SpringApplication.run(YiyanApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println();
        for (int i = 1; i <= 4; i++) {
            executorService.execute(new YiYanTask(requestService,httpClient,httpGet));
        }
       executorService.shutdown();
        latch.await();
        if (httpClient != null) {
            httpClient.close();

        }

    }
}
