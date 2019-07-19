package com.qxnekoo.yiyan.service.impl;

import com.alibaba.fastjson.JSON;
import com.qxnekoo.yiyan.model.Yiyan;
import com.qxnekoo.yiyan.service.RequestService;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class RequestServiceImpl implements RequestService {
    @Autowired
    CloseableHttpClient httpClient;
    @Autowired
    HttpGet httpGet;
    @Override
    public Yiyan getResponse() {
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println();
            System.out.println("响应状态为:" + response.getStatusLine());

            if (responseEntity != null) {
                String res = EntityUtils.toString(responseEntity);

                Yiyan yiyan = JSON.parseObject(res,Yiyan.class);
                return yiyan;
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
