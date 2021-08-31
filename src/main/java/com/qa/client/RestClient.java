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


public class RestClient {

    //1. Get Method
    public void get(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url); //http get request
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet); //hit the get URL

        //a. Status code
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("Status code: " + statusCode);

        //b. Json string
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        JSONObject responseJson = new JSONObject(responseString);
        System.out.println("\nResponse JSON from API: " + responseJson);
        //Then you can paste the responseJson to https://jsonlint.com/ to get a good-looking format.

        //c. All headers
        Header[] headersArray = closeableHttpResponse.getAllHeaders();
        HashMap<String, String> allHeaders = new HashMap<>();
        for (Header header : headersArray) {
            allHeaders.put(header.getName(), header.getValue());
        }
        System.out.println("\nHeader array: " + allHeaders);

    }
}
