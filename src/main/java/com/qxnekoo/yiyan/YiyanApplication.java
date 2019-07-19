package com.qxnekoo.yiyan;

import com.qxnekoo.yiyan.model.Yiyan;
import com.qxnekoo.yiyan.service.RequestService;
import com.qxnekoo.yiyan.service.impl.RequestServiceImpl;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.io.IOException;

@SpringBootApplication
public class YiyanApplication implements CommandLineRunner {
    @Resource
    RequestService requestService;
    public static void main(String[] args) {
        SpringApplication.run(YiyanApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Yiyan yiyan = requestService.getResponse();
        System.out.println();
        System.out.println(yiyan.getHitokoto()+"  --"+yiyan.getFrom());
    }
}
