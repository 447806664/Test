package com.test.serial;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by 123 on 2016/2/1.
 */
public class HumanResourcesApplication {
    private static final Logger log = Logger.getLogger(HumanResourcesApplication.class.getName());
    private static final String SOURCE_CLASS = HumanResourcesApplication.class.getName();

    public static List<Employee> createEmployees() {
        List<Employee> ret = new ArrayList<>();
        Employee e = new Employee("Jon Smith", 45);
        ret.add(e);
        //
        e = new Employee("Jon Jones", 40);
        ret.add(e);
        //
        e = new Employee("Mary Smith", 35);
        ret.add(e);
        //
        e = new Employee("Chris Johnson", 38);
        ret.add(e);
        // Return list of Employees
        return ret;
    }

    public boolean serializeToDisk(String filename, List<Employee> employees) {
        final String METHOD_NAME = "serializeToDisk(String filename, List<Employee> employees)";

        boolean ret = false;// default: failed
        File file = new File(filename);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            log.info("Writing " + employees.size() + " employees to disk (using Serializable)...");
            outputStream.writeObject(employees);
            ret = true;
            log.info("Done.");
        } catch (IOException e) {
            log.logp(Level.SEVERE, SOURCE_CLASS, METHOD_NAME, "Cannot find file " +
                    file.getName() + ", message = " + e.getLocalizedMessage(), e);
        }
        return ret;
    }

    @SuppressWarnings("unchecked")
    public List<Employee> deserializeFromDisk(String filename) {
        final String METHOD_NAME = "deserializeFromDisk(String filename)";

        List<Employee> ret = new ArrayList<>();
        File file = new File(filename);
        int numberOfEmployees = 0;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            List<Employee> employees = (List<Employee>) inputStream.readObject();
            log.info("Deserialized List says it contains " + employees.size() +
                    " objects...");
            for (Employee employee : employees) {
                log.info("Read Employee:" + employee.toString());
                numberOfEmployees++;
            }
            ret = employees;
            log.info("Read " + numberOfEmployees + " employees from disk.");
        } catch (FileNotFoundException e) {
            log.logp(Level.SEVERE, SOURCE_CLASS, METHOD_NAME, "Cannot find file " +
                    file.getName() + ", message = " + e.getLocalizedMessage(), e);
        } catch (IOException e) {
            log.logp(Level.SEVERE, SOURCE_CLASS, METHOD_NAME, "IOException occurred, message = " + e.getLocalizedMessage(), e);
        } catch (ClassNotFoundException e) {
            log.logp(Level.SEVERE, SOURCE_CLASS, METHOD_NAME, "ClassNotFoundException, message = " + e.getLocalizedMessage(), e);
        }
        return ret;
    }
}
