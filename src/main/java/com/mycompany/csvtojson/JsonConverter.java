/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.csvtojson;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import static org.beryx.textio.TextIoFactory.getTextTerminal;
import org.beryx.textio.TextTerminal;

/**
 *
 * @author olivier.baudion
 */
public class JsonConverter {
    String csvFilepath;
    String jsonFilepath;
    TextTerminal terminal =getTextTerminal();
    public JsonConverter(String csvFilename, String jsonFilename) {
        this.csvFilepath= csvFilename;
        this.jsonFilepath = jsonFilename;
    }
    public void createJsonFile() throws IOException{
        try {
            Stream<String> lignecsv=Files.lines(Paths.get(csvFilepath));
            FileWriter file = new FileWriter(jsonFilepath);
            file.append("[ \n\t { ");
            lignecsv.skip(1).forEach((x)->{
                try {
                    String[] contenu=x.split(";");
                    file.append("\n\t\t DEVELOPPEUR {\n");
                    for (String detail : contenu) {
                        file.append("\t\t\t" + detail + "\n");
                    }
                    file.append("\t\t},");
                } catch (IOException ex) {
                    Logger.getLogger(JsonConverter.class.getName()).log(Level.SEVERE, null, ex);
                    
                    terminal.printf("\n Fichier Introuvable");
                }
        });
        file.append("\n\t } \n ] ");
        file.flush();
        } catch (Exception e) {
            
            terminal.printf("\n Veuillez Verifier si le fichier specifié existe sur le disque");
        }
        
        
    }
    
    public void displayJsonFile() throws IOException{
        Stream<String> lignejson=Files.lines(Paths.get(jsonFilepath));
        
        lignejson.forEach((x)->terminal.printf("%s \n",x));
    }
}
