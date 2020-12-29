/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.csvtojson;

import java.io.File;
import java.io.FileWriter;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author olivier.baudion
 */
//@RunWith(Parameterized.class)
public class JsonConverterTest {
   

    @BeforeEach
    public void given() {
        File file = new File("D:/fichier.csv");
        assertTrue(file.exists());
    }

    

    /**
     * Test of createJsonFile method, of class JsonConverter.
     */
    @Test
    public void testCreateJsonFile() throws Exception {
        
        FileWriter file = new FileWriter("D:/emp.json");

    }

    /**
     * Test of displayJsonFile method, of class JsonConverter.
     */
    @Test
    public void testDisplayJsonFile()  {

        System.out.println("displayJsonFile");

    }
    @AfterEach
    public void then() {
        File file = new File("D:/inexa/fichierconverti.json");
        assertTrue(file.exists());
    }
}
