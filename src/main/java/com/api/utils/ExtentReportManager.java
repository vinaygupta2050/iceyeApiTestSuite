package com.api.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {

    private static ExtentReports reports;
    private static String platform;
    private static String reportFileName = "IceyeApiTestExecutionResult.html";
    private static String macPath = System.getProperty("user.dir")+ "/test-output/Report";
    private static String windowsPath = System.getProperty("user.dir")+ "\\test-output\\Report";
    private static String macReportFileLoc = macPath + "/" + reportFileName;
    private static String winReportFileLoc = windowsPath + "\\" + reportFileName;
    public static ThreadLocal<ExtentTest>  extentTestThreadSafe = new ThreadLocal<ExtentTest>();

    public static synchronized ExtentTest getTest()
    {
        return extentTestThreadSafe.get();
    }
    public static void setTest(ExtentTest tst)
    {
        System.out.println("Set Test Method Called");
        extentTestThreadSafe.set(tst);
    }

    public synchronized static ExtentReports getInstance() {
        if (reports == null)
            createInstance();
        return reports;
    }

    //Create an extent report instance
    public static ExtentReports createInstance() {
        platform = getCurrentPlatform();
        String fileName = getReportFileLocation(platform);
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);
        htmlReporter.config().setDocumentTitle("Title of the Report Comes here ");
        htmlReporter.config().setReportName("API Test Execution Results");
        htmlReporter.config().setTheme(Theme.STANDARD);
        reports = new ExtentReports();
        reports.attachReporter(htmlReporter);
        return reports;
    }

    //Select the extent report file location based on platform
    private static String getReportFileLocation (String platform) {
        String reportFileLocation = null;
        switch (platform) {
            case "MAC":
                reportFileLocation = macReportFileLoc;
                createReportPath(macPath);
                System.out.println("ExtentReport Path for MAC: " + macPath + "\n");
                break;
            case "WINDOWS":
                reportFileLocation = winReportFileLoc;
                createReportPath(windowsPath);
                System.out.println("ExtentReport Path for WINDOWS: " + windowsPath + "\n");
                break;
            default:
                System.out.println("ExtentReport path has not been set! There is a problem!\n");
                break;
        }
        return reportFileLocation;
    }

    //Create the report path if it does not exist
    private static void createReportPath (String path) {
        File testDirectory = new File(path);
        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {
                System.out.println("Directory: " + path + " is created!" );
            } else {
                System.out.println("Failed to create directory: " + path);
            }
        } else {
            System.out.println("Directory already exists: " + path);
        }
    }

    //Get current platform
    private static String getCurrentPlatform () {
        if (platform == null) {
            String operSys = System.getProperty("os.name").toLowerCase();
            if (operSys.contains("win")) {
                platform = "WINDOWS";
            } else if (operSys.contains("nix") || operSys.contains("nux")
                    || operSys.contains("aix")) {
                platform = "LINUX";
            } else if (operSys.contains("mac")) {
                platform = "MAC";
            }
        }
        return platform;
    }
}
