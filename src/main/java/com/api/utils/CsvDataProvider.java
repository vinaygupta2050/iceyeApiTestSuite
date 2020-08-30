package com.api.utils;

import org.testng.ITestNGMethod;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CsvDataProvider {
    @DataProvider(name = "testData")
    public Iterator<Object[]> testData(ITestNGMethod method) throws IOException
    {
        Class classes = method.getRealClass(); Method methods = method.getConstructorOrMethod().getMethod();
        String className=classes.getSimpleName().toString();
        String methodName=methods.getName().toString();
        System.out.println("ClassName :"+className);
        System.out.println("MethodName :"+methodName);
        return Helper.parseCsvData(System.getProperty("user.dir")+"/src/test/testData/"+className+"/"+methodName+".csv");
    }
}
