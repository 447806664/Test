package com.test.java.serial;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by 123 on 2016/2/1.
 */
public class HumanResourcesApplicationTest {
    private HumanResourcesApplication classUnderTest;
    private List<Employee> testData;

    @Before
    public void setUp() {
        classUnderTest = new HumanResourcesApplication();
        testData = HumanResourcesApplication.createEmployees();
    }

    @Test
    public void testSerializeToDisk() {
        String filename = "employees-Junit-" + System.currentTimeMillis() + ".ser";
        boolean status = classUnderTest.serializeToDisk(filename, testData);
        Assert.assertTrue(status);
    }

    @Test
    public void testDeserializeFromDisk() {
        String filename = "employees-Junit-" + System.currentTimeMillis() + ".ser";
        int expectedNumberOfObjects = testData.size();
        classUnderTest.serializeToDisk(filename, testData);
        List<Employee> employees = classUnderTest.deserializeFromDisk(filename);
        Assert.assertEquals(expectedNumberOfObjects, employees.size());
    }
}
