package com.api.test;

import com.api.apiFactory.ApiEndPoints;
import com.api.apiFactory.ApiHeaders;
import com.api.utils.CsvDataProvider;
import com.api.utils.ExtentReportManager;
import com.aventstack.extentreports.Status;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author in-vinaykumar.gupta on 30/08/20
 * @project IntelliJ IDEA
 */
public class GreetApiTest extends  BaseTest{
    RequestSpecification httpRequest = getRequestSpec();

    @Test(enabled = true,description = "Verify whether user is able to get greet message as per the name passed to api end point",dataProvider = "testData",dataProviderClass = CsvDataProvider.class)
    public void getGreetMessage(String name)
    {
        httpRequest.headers(ApiHeaders.defaultHeader());
        Response rs=httpRequest.request(Method.GET, "/"+name);
        ExtentReportManager.getTest().log(Status.INFO,"Status Code : "+rs.getStatusCode());
        ExtentReportManager.getTest().log(Status.INFO,"Response    : "+rs.getBody().prettyPrint().toString());
        Assert.assertTrue(rs.getBody().asString().contains("Vinaykumar"));
    }
}
