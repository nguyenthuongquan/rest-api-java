package com.qa.utilities;

import com.qa.pojo.Product;
import org.apache.juneau.html.HtmlSerializer;
import org.apache.juneau.json.JsonParser;
import org.apache.juneau.json.JsonSerializer;
import org.apache.juneau.parser.ParseException;
import org.apache.juneau.serializer.SerializeException;
import org.apache.juneau.xml.XmlSerializer;

public class POJOUtil {

    //SERIALIZATION
    //POJO to JSON
    public static String getJsonFromPOJO(Object object) throws SerializeException {
        JsonSerializer jsonSerializer = JsonSerializer.DEFAULT_READABLE;
        return jsonSerializer.serialize(object);
    }

    //POJO to XML
    public static String getXMLFromPOJO(Object object) throws SerializeException {
        XmlSerializer xmlSerializer = XmlSerializer.DEFAULT_NS_SQ_READABLE;
        return xmlSerializer.serialize(object);
    }

    //POJO to HTML
    public static String getHTMLFromPOJO(Object object) throws SerializeException {
        HtmlSerializer htmlSerializer = HtmlSerializer.DEFAULT_SQ_READABLE;
        return htmlSerializer.serialize(object);
    }

    //DE-SERIALIZATION
    //JSON to POJO
    public static Product getPOJOFromJson(String json) throws ParseException {
        JsonParser jsonParser = JsonParser.DEFAULT;
        return jsonParser.parse(json, Product.class);
    }
}
