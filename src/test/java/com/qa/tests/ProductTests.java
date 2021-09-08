package com.qa.tests;

import com.qa.base.TestBase;
import com.qa.pojo.Product;
import com.qa.utilities.POJOUtil;
import org.apache.juneau.parser.ParseException;
import org.apache.juneau.serializer.SerializeException;
import org.testng.annotations.Test;

public class ProductTests extends TestBase {

    @Test
    public void productTest() throws SerializeException, ParseException {
        String[] sellerNames = {"sellerNames1", "sellerNames2", "sellerNames3"};
        Product product = new Product("Macbook Pro", 1000, "White", sellerNames);

        //1. SERIALIZATION
        //POJO --> JSON
        String json = POJOUtil.getJsonFromPOJO(product);
        System.out.println("Json: " + json);

        //POJO --> XML
        String xml = POJOUtil.getXMLFromPOJO(product);
        System.out.println("Xml: " + xml);

        //POJO --> HTML
        System.out.println("Html: " + POJOUtil.getHTMLFromPOJO(product));

        //2. DE-SERIALIZATION
        //JSON --> POJO
        Product product2 = POJOUtil.getPOJOFromJson(json);
        System.out.println("Pojo from Json: " + product2);
        System.out.println("Pojo.Name: " + product2.getName());
        System.out.println("Pojo.Price: " + product2.getPrice());
    }
}

