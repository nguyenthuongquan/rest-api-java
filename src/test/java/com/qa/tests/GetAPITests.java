package com.qa.tests;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.utilities.ReadConfigFile;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetAPITests extends TestBase {
    TestBase testBase;
    String url;
    RestClient restClient;

    private final ReadConfigFile readConfigFile = ConfigFactory.create(ReadConfigFile.class);

    @BeforeMethod
    public void setUp() {
        testBase = new TestBase();
        url = readConfigFile.URL() + readConfigFile.serviceURL();

    }

    @Test
    public void getAPITest() throws IOException {
        restClient = new RestClient();
        restClient.get(url);
    }
}
