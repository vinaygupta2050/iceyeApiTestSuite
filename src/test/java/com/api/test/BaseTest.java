package com.api.test;

import com.api.utils.PropertyFileReader;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

/**
 * @author in-vinaykumar.gupta on 30/08/20
 * @project IntelliJ IDEA
 */
public class BaseTest {
    @BeforeClass
    public RequestSpecification getRequestSpec()
    {
        RestAssured.baseURI = PropertyFileReader.getAllProperties().get("serverUrl");
        System.out.println(PropertyFileReader.getAllProperties().get("serverUrl"));
        return RestAssured.given();
    }

}
