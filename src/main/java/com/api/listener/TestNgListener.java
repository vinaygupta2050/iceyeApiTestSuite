package com.api.listener;

import com.api.utils.ExtentReportManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * @author in-vinaykumar.gupta on 29/08/20
 * @project IntelliJ IDEA
 */
public class TestNgListener implements ITestListener {
    //Extent Report Declarations
    private static ExtentReports extent = ExtentReportManager.createInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public synchronized void onStart(ITestContext context) {
        System.out.println("Extent Reports Version 3 Test Suite started!");
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        System.out.println(("Extent Reports Version 3  Test Suite is ending!"));
        extent.flush();
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
        ExtentReportManager.setTest(extentTest);
        System.out.println((result.getMethod().getMethodName() + " started!"));
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " passed!"));
        ExtentReportManager.getTest().log(Status.PASS, "Test Pass Successfully!!!!");
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " failed!"));
        ExtentReportManager.getTest().log(Status.FAIL, result.getThrowable());
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " skipped!"));
        ExtentReportManager.getTest().log(Status.SKIP, result.getThrowable());
    }

    @Override
    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
    }


}
