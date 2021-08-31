package com.qa.client;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class RestClient {

    //1. Get Method without Headers
    public CloseableHttpResponse get(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url); //http get request
        return httpClient.execute(httpGet);
    }

    //2. Get Method with Headers
    public CloseableHttpResponse get(String url, HashMap<String, String> hashMap) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url); //http get request
        for (Map.Entry<String, String> entry: hashMap.entrySet()) {
            httpGet.addHeader(entry.getKey(), entry.getValue());
        }
        return httpClient.execute(httpGet);
    }
}
