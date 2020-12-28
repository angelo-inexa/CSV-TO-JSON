/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.csvtojson;

import static java.awt.Color.yellow;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

/**
 *
 * @author olivier.baudion
 */
public class Main {
    
    public static String createpath(String directoryname, String filename){
       File directory = new File(directoryname);
       if(!directory.exists()){          
           directory.mkdirs();           
       }
      
        return directoryname.endsWith("/")? directoryname+filename:directoryname+"/"+filename;
       
    }
    public static void main(String[] args) throws IOException  {
       
        TextIO textIO = TextIoFactory.getTextIO();
        
        String csvdirectory = textIO.newStringInputReader()
           .withDefaultValue("D:")
           .read("Emplacement du fichier CSV");
        
        String csvfile = textIO.newStringInputReader()
           .withDefaultValue("fichier")
           .read("Nom du fichier CSV à convertir");

        String jsonfile = textIO.newStringInputReader()
           .withDefaultValue("fichierconverti")
           .read("Nommer le fichier JSON de destination");
        
        String jsondirectory = textIO.newStringInputReader()
           .withDefaultValue("D:")
           .read("Emplacement du fichier JSON");

        TextTerminal terminal = textIO.getTextTerminal();
        
        terminal.printf("\n************************************************");
        terminal.printf("\n Conversion en cours , Veuillez patienter\n");
        
        String csvfilepath=createpath(csvdirectory, csvfile+".csv");
        String jsonfilepath=createpath(jsondirectory, jsonfile+".json");
        JsonConverter jsc=new JsonConverter(csvfilepath, jsonfilepath);
        terminal.printf("%s \n  %s", csvfilepath ,jsonfilepath);
        jsc.createJsonFile();
        terminal.printf("\n ************Conversion Terminée***************** \n");
        jsc.displayJsonFile();

   }
}
