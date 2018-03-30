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

    private static StackLinkedList<BracketType> stackBrackets = new StackLinkedList<>();  
    
    public static void main(String[] args) {
        
        
        correctBracketsInFile("task_brackets.rtf");
        
        StackLinkedList stack = new StackLinkedList();
        
    }


    
    private static void correctBracketsInFile(String fileName) {
        try {
            FileInputStream fstream = new FileInputStream(fileName);
            BufferedReader buffer = new BufferedReader(
                    new InputStreamReader(fstream));
            String fileLine;
            while ((fileLine = buffer.readLine()) != null) {
                
                fileLine = correctLine(fileLine);
                writeLine(fileName, fileLine);
            }
        } catch (IOException e){
            System.out.println("Error file read: " + e);
        }
    }
    
    private static String correctLine(String fileLine) {
        StringBuilder newLine = new StringBuilder();
        for (char ch : fileLine.toCharArray()) {
            if (ch == '{') stackBrackets.push(BracketType.FIGURE_LEFT);
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
        return namePart + "_correct" + extPart;
    }
    
}
