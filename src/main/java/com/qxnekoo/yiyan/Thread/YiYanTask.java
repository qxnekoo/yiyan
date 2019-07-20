package com.qxnekoo.yiyan.Thread;

import com.qxnekoo.yiyan.model.Yiyan;
import com.qxnekoo.yiyan.service.RequestService;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class YiYanTask implements  Runnable{
    Logger logger = LoggerFactory.getLogger(YiYanTask.class);
    RequestService requestService;
    CloseableHttpClient httpClient;
    HttpGet httpGet;

    @Override
    public void run() {
        Yiyan yiyan = requestService.getResponse(httpClient,httpGet);

        System.out.println(yiyan.getHitokoto()+"  --"+yiyan.getFrom());
        System.out.println();
    }

    public YiYanTask(RequestService requestService, CloseableHttpClient httpClient, HttpGet httpGet) {
        this.requestService = requestService;
        this.httpClient = httpClient;
        this.httpGet = httpGet;
    }
}
