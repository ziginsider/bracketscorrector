/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bracketscorrector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 *
 * @author zigin
 */
public class BracketsCorrector {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        correctBrackets("task_brackets.rtf");
        
    }


    
    private static void correctBrackets(String fileName) {
        try {
            FileInputStream fstream = new FileInputStream(fileName);
            BufferedReader buffer = new BufferedReader(
                    new InputStreamReader(fstream));
            String fileLine;
            while ((fileLine = buffer.readLine()) != null) {
                writeLine(fileName, fileLine);
            }
        } catch (IOException e){
            System.out.println("Error file read: " + e);
        }
    }
    
    private static void writeLine(String fileName, String fileLine) {
        OutputStream stream = null;
        try {
            fileLine += System.lineSeparator();
            stream = new FileOutputStream(
                    new File(generateNewFileName(fileName)), true);
            stream.write(fileLine.getBytes(), 0, fileLine.length());
        } catch (IOException e) {
            System.out.println("Error file write: " + e);
        }finally{
            try {
                stream.close();
            } catch (IOException e) {
                System.out.println("Error file close: " + e);
            }
        }
    }
    
    private static String generateNewFileName(String oldFileName) {
        int indexDot = oldFileName.lastIndexOf('.');
        String namePart = oldFileName.substring(0, indexDot - 1);
        String extPart = oldFileName.substring(indexDot);
        return namePart + "_correct1" + extPart;
    }
    
}
