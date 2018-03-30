package bracketscorrector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zigin
 */
public class BracketsCorrector {
    private static StackLinkedList<BracketInfo> stackBrackets
            = new StackLinkedList<>();
    private static List<StringBuilder> lines = new ArrayList<>();
    private static List<BracketInfo> toDeleteBrackets = new ArrayList<>();
    private static int currentLine;

    public static void main(String[] args) {
        correctBracketsInFile("task_brackets.rtf");
        //orrectBracketsInFile("check.txt");
    }

    private static void correctBracketsInFile(String fileName) {
        try {
            FileInputStream fstream = new FileInputStream(fileName);
            BufferedReader buffer = new BufferedReader(
                    new InputStreamReader(fstream));
            String fileLine;
            currentLine = 0;
            while ((fileLine = buffer.readLine()) != null) {
                lines.add(new StringBuilder(fileLine));
                findExcessBrackets(fileLine);
                currentLine++;
            }
        } catch (IOException e) {
            System.out.println("Error file read: " + e);
        }
        clearStack();
        deleteWrongBrackets();
        writeToCorrectFile(fileName);
        //For *.rtf files
        correctIfRtfFile(fileName);
    }

    private static void writeToCorrectFile(String oldFileName) {
        for (StringBuilder line : lines) {
            writeLine(oldFileName, line.toString());
        }
    }

    private static void deleteWrongBrackets() {
        Collections.sort(toDeleteBrackets, new ComparatorDeleteLetters());
        for (BracketInfo wrong : toDeleteBrackets) {
            int lineNumber = wrong.getLineNumber();
            int letterNumber = wrong.getLetterNumber();
            StringBuilder newline =
                    lines.get(lineNumber).deleteCharAt(letterNumber);
            lines.set(lineNumber, newline);
        }
    }
    
    private static void findExcessBrackets(String fileLine) {
        char[] letters = fileLine.toCharArray();
        for (int i = 0; i < letters.length; i++) {
            switch (letters[i]) {
                case '{':
                    stackBrackets.push(new BracketInfo(
                            BracketType.FIGURE_LEFT,
                            currentLine,
                            i
                    ));
                    break;
                case '(':
                    stackBrackets.push(new BracketInfo(
                            BracketType.SIMPLE_LEFT,
                            currentLine,
                            i
                    ));
                    break;
                case '}':
                    bracketsResolve(new BracketInfo(
                            BracketType.FIGURE_RIGHT,
                            currentLine,
                            i
                    ));
                    break;
                case ')':
                    bracketsResolve(new BracketInfo(
                            BracketType.SIMPLE_RIGHT,
                            currentLine,
                            i
                    ));
                    break;
            }
        }
    }

    private static void bracketsResolve(BracketInfo currentLetter) {
        if (stackBrackets.readTop() == null) {
            toDeleteBrackets.add(currentLetter);
            return;
        }
        BracketType opposite = stackBrackets.readTop().getType();
        BracketType current = currentLetter.getType();
        if (isAccord(opposite, current)) {
            stackBrackets.pop();
        } else {
            while (!isAccord(opposite, current)) {
                toDeleteBrackets.add(stackBrackets.pop());
                if (stackBrackets.readTop() == null) {
                    toDeleteBrackets.add(currentLetter);
                    break;
                }
                opposite = stackBrackets.readTop().getType();
            }
            stackBrackets.pop();
        }
    }

    private static void clearStack() {
        while (stackBrackets.readTop() != null) {
            toDeleteBrackets.add(stackBrackets.pop());
        }
    }

    private static boolean isAccord(BracketType opposite,
                                    BracketType current) {
        return (opposite == BracketType.FIGURE_LEFT
                && current == BracketType.FIGURE_RIGHT)
                || (opposite == BracketType.SIMPLE_LEFT
                && current == BracketType.SIMPLE_RIGHT);
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
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                System.out.println("Error file close: " + e);
            }
        }
    }
    
    private static void correctIfRtfFile(String fileName) {
        int indexDot = fileName.lastIndexOf('.');
        String extPart = fileName.substring(indexDot);
        if (extPart.equals(".rtf")) writeLine(fileName, "}");
    }

    private static String generateNewFileName(String oldFileName) {
        int indexDot = oldFileName.lastIndexOf('.');
        String namePart = oldFileName.substring(0, indexDot);
        String extPart = oldFileName.substring(indexDot);
        return namePart + "_correct" + extPart;
    }
}