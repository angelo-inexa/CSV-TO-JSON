/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.csvtojson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
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
    public void performConversion() throws IOException{
        try {
            Stream<String> lignecsv=readCsvFile();
            Optional<FileWriter> fileOpt =createEmptyJsonFile();
            if(fileOpt.isPresent()){
                FileWriter file=fileOpt.get();
                createNodeJsonFile(file, lignecsv);
            }else{
               terminal.printf("Aucune Instance de fichier trouvé ");
            }
            
            
        } catch (Exception e) {
            
            terminal.printf("\n Veuillez Verifier si le fichier specifié existe sur le disque");
        }
        
        
    }
   
    private Optional<FileWriter> createEmptyJsonFile() throws IOException{
        try{
            FileWriter file = new FileWriter(jsonFilepath);
            return Optional.of(file);
        }catch(IOException exc){
            terminal.printf("\n Imposssible de creer le fichier");
            return Optional.empty();
        }
        
        
    }
    private Stream<String> readCsvFile() throws IOException{
        try{
            Stream<String> lignecsv=Files.lines(Paths.get(csvFilepath));
            return lignecsv;
        }catch(IOException exc){
            terminal.printf("\n Fichier Introuvable");
            return Stream.empty();
        }
               
    }
    
    private void firstLineJsonFile(FileWriter file) throws IOException{
        try{
            file.append("[ \n\t { ");
        }catch(IOException exc){
            terminal.printf("\n Fichier Introuvable");
           
        }
        
    }
    private void lastLineJsonFile(FileWriter file) throws IOException{
        try{
            file.append("\n\t } \n ] ");
        }catch(IOException exc){
            terminal.printf("\n Fichier Introuvable");          
        }
        
    }
    private void openNodeJsonFile(FileWriter file) throws IOException{
        try{
            file.append("\n\t\t DEVELOPPEUR {\n");
        }catch(IOException exc){
            terminal.printf("\n Fichier Introuvable");          
        }
        
    }
    private void writeNodeJsonFile(FileWriter file, String infos) throws IOException{
        try{
            file.append("\t\t\t" + infos + "\n");
        }catch(IOException exc){
            terminal.printf("\n Fichier Introuvable");          
        }
        
    }
     private void closeNodeJsonFile(FileWriter file) throws IOException{
        try{
            file.append("\t\t},");
        }catch(IOException exc){
            terminal.printf("\n Fichier Introuvable");          
        }
        
    }
    private void createNodeJsonFile(FileWriter file, Stream<String> lignecsv) throws IOException{
        //file.append("[ \n\t { ");
        firstLineJsonFile(file);
        lignecsv.skip(1).forEach((x)->{
                try {
                    String[] contenu=x.split(";");
                    //file.append("\n\t\t DEVELOPPEUR {\n");
                    openNodeJsonFile(file);
                    for (String detail : contenu) {
                        //file.append("\t\t\t" + detail + "\n");
                        writeNodeJsonFile(file, detail);
                    }
                    //file.append("\t\t},");
                    closeNodeJsonFile(file);
                } catch (IOException ex) {
                    Logger.getLogger(JsonConverter.class.getName()).log(Level.SEVERE, null, ex);                    
                    terminal.printf("\n Fichier Introuvable");
                }
        });
        //file.append("\n\t } \n ] ");
        lastLineJsonFile(file);
        file.flush();
    }
    
    public void displayJsonFile() throws IOException{
        //Stream<String> lignejson=readJsonFile();
        
        readJsonFile().forEach((x)->terminal.printf("%s \n",x));
    }
    private Stream<String> readJsonFile() throws IOException{
        return Files.lines(Paths.get(jsonFilepath));
        
    }
}
