package com.qxnekoo.yiyan.service;

import com.qxnekoo.yiyan.model.Yiyan;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;


public interface RequestService {
     Yiyan getResponse(CloseableHttpClient httpClient, HttpGet httpGet);
}
