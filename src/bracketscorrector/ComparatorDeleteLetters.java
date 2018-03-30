/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bracketscorrector;

import java.util.Comparator;

/**
 * Implements comparator for {@link BrackerInfo}
 * 
 * @author Alex Kisel
 * @since 2018-03-30
 */
 public class ComparatorDeleteLetters
            implements Comparator<BracketInfo> {
    @Override
    public int compare(BracketInfo info1, BracketInfo info2) {
        if (info1.getLetterNumber() > info2.getLetterNumber()) return -1;
        else if (info1.getLetterNumber() < info2.getLetterNumber()) return 1;
        else return 0;
    }
}
