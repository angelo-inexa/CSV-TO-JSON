/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.csvtojson;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author olivier.baudion
 */
public class JsonConverterTest {

    public JsonConverterTest() {
    }

    @Before
    public void setUp() {
        File file = new File("D:/fichier.csv");
        assertTrue(file.exists());
    }

    

    /**
     * Test of createJsonFile method, of class JsonConverter.
     */
    @Test
    public void testCreateJsonFile() throws Exception {
        System.out.println("createJsonFile");

    }

    /**
     * Test of displayJsonFile method, of class JsonConverter.
     */
    @Test
    public void testDisplayJsonFile() throws Exception {

        System.out.println("displayJsonFile");

    }
    @After
    public void tearDown(String path) {
        File file = new File(path);
        assertTrue(file.exists());
    }
}
