package com.qa.tests;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.utilities.ReadConfigFile;
import com.qa.utilities.TestUtil;
import org.aeonbits.owner.ConfigFactory;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.HashMap;

public class GetAPITests extends TestBase {
    TestBase testBase;
    String url;
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;

    private final ReadConfigFile readConfigFile = ConfigFactory.create(ReadConfigFile.class);

    @BeforeMethod
    public void setUp() {
        testBase = new TestBase();
        url = readConfigFile.URL() + readConfigFile.serviceURL();
    }

    @Test(priority = 1)
    public void getAPITestWithoutHeaders() throws IOException {
        restClient = new RestClient();
        closeableHttpResponse = restClient.get(url);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        JSONObject responseJson = new JSONObject(responseString);
        System.out.println("\nResponse JSON from API: " + responseJson);
        //Then you can paste the responseJson to https://jsonlint.com/ to get a good-looking format.

        //VP: Verify: Status code
        System.out.println("Status code: " + statusCode);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");

        //Single Value Assertion
        //VP: Verify: Per Page
        String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
        System.out.println("\nValue of per page is " + perPageValue);
        softAssert.assertEquals(Integer.parseInt(perPageValue), 6);

        //VP: Verify: Total
        String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
        System.out.println("\nValue of total is " + totalValue);
        softAssert.assertEquals(Integer.parseInt(totalValue), 12);

        //Get the value from JSON ARRAY at specific index
        int index = 0;
        String lastName = TestUtil.getValueByJPath(responseJson, "/data["+ index +"]/last_name");
        String id = TestUtil.getValueByJPath(responseJson, "/data["+ index +"]/id");
        String avatar = TestUtil.getValueByJPath(responseJson, "/data["+ index +"]/avatar");
        String firstName = TestUtil.getValueByJPath(responseJson, "/data["+ index +"]/first_name");
        System.out.println("\nlastName["+ index +"]: " + lastName);
        System.out.println("\nid["+ index +"]: " + id);
        System.out.println("\navatar["+ index +"]: " + avatar);
        System.out.println("\nfirstName["+ index +"]: " + firstName);


        //c. All headers
        Header[] headersArray = closeableHttpResponse.getAllHeaders();
        HashMap<String, String> allHeaders = new HashMap<>();
        for (Header header : headersArray) {
            allHeaders.put(header.getName(), header.getValue());
        }
        System.out.println("\nHeader array: " + allHeaders);


        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void getAPITestWithHeaders() throws IOException {
        restClient = new RestClient();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Content-Type", "application/json"); //<- this is not compulsory as default.
//        hashMap.put("username", "quan@gmail.com");
//        hashMap.put("password", "test123456");
//        hashMap.put("authToken", "123456");
        closeableHttpResponse = restClient.get(url, hashMap);

        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        JSONObject responseJson = new JSONObject(responseString);
        System.out.println("\nResponse JSON from API: " + responseJson);
        //Then you can paste the responseJson to https://jsonlint.com/ to get a good-looking format.

        //VP: Verify: Status code
        System.out.println("Status code: " + statusCode);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");

        //Single Value Assertion
        //VP: Verify: Per Page
        String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
        System.out.println("\nValue of per page is " + perPageValue);
        softAssert.assertEquals(Integer.parseInt(perPageValue), 6);

        //VP: Verify: Total
        String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
        System.out.println("\nValue of total is " + totalValue);
        softAssert.assertEquals(Integer.parseInt(totalValue), 12);

        //Get the value from JSON ARRAY at specific index
        int index = 0;
        String lastName = TestUtil.getValueByJPath(responseJson, "/data["+ index +"]/last_name");
        String id = TestUtil.getValueByJPath(responseJson, "/data["+ index +"]/id");
        String avatar = TestUtil.getValueByJPath(responseJson, "/data["+ index +"]/avatar");
        String firstName = TestUtil.getValueByJPath(responseJson, "/data["+ index +"]/first_name");
        System.out.println("\nlastName["+ index +"]: " + lastName);
        System.out.println("\nid["+ index +"]: " + id);
        System.out.println("\navatar["+ index +"]: " + avatar);
        System.out.println("\nfirstName["+ index +"]: " + firstName);


        //c. All headers
        Header[] headersArray = closeableHttpResponse.getAllHeaders();
        HashMap<String, String> allHeaders = new HashMap<>();
        for (Header header : headersArray) {
            allHeaders.put(header.getName(), header.getValue());
        }
        System.out.println("\nHeader array: " + allHeaders);


        softAssert.assertAll();
    }
}
