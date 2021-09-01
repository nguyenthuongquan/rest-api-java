package com.qa.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;
import com.qa.utilities.ReadConfigFile;
import org.aeonbits.owner.ConfigFactory;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class PostAPITests extends TestBase {
    private final ReadConfigFile readConfigFile = ConfigFactory.create(ReadConfigFile.class);
    TestBase testBase;
    String url;
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;

    @BeforeMethod
    public void setUp() {
        testBase = new TestBase();
        url = readConfigFile.URL() + readConfigFile.serviceURL();
    }

    @Test
    public void postAPITest() throws IOException {
        restClient = new RestClient();
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");

        //1. Prepare java object (user), then marshalling it to Json string object
        ObjectMapper mapper = new ObjectMapper();
        Users users = new Users("Quan Nguyen", "QA");   //expected users object
        mapper.writeValue(new File("src/main/java/com/qa/data/users.json"), users);
        String usersJsonString = mapper.writeValueAsString(users); //marshalling
        System.out.println("1. Prepare Request payload in Json format is: " + usersJsonString);

        //2. Call the post API request to create the user
        closeableHttpResponse = restClient.post(url, usersJsonString, headerMap);
        System.out.println("2. The Request is posted!");

        //3. Verify Status code from response
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_201);
        System.out.println("3. The Response status code is: " + statusCode);

        //4. Print the response payload
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        System.out.println("4a. The Response payload in Json string format is: " + responseString);
        JSONObject responseJson = new JSONObject(responseString);
        System.out.println("4b. The Response payload in Json object format is: " + responseJson);

        //5. Un-marshalling, convert the Response payload from Json string object back to java object (user):
        Users usersResObj = mapper.readValue(responseString, Users.class);
        System.out.println("5. Now the User object is: " + usersResObj);
        softAssert.assertEquals(usersResObj.getName(), users.getName());
        softAssert.assertEquals(usersResObj.getJob(), users.getJob());
        System.out.println("5a. The UserObject.getName is : " + usersResObj.getName());
        System.out.println("5b. The UserObject.getJob is  : " + usersResObj.getJob());
        System.out.println("5a. The UserObject.getId is   : " + usersResObj.getId());
        System.out.println("5b. The UserObject.getCreatedAt() is  : " + usersResObj.getCreatedAt());
        softAssert.assertAll();
    }
}
