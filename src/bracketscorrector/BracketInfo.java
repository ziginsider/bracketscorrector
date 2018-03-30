package bracketscorrector;

/**
 * Keeps iformation of bracket's type and location in file
 * 
 * @author Alex Kisel
 * @since 2018-03-30
 */
public class BracketInfo {
    private BracketType type;
    private int lineNumber;
    private int letterNumber;
    
    BracketInfo(BracketType type, int lineNumber, int letterNumber) {
        this.type = type;
        this.lineNumber = lineNumber;
        this.letterNumber = letterNumber;
    }
   
    /**
     * Get type of bracket. See {@link BracketType}
     * 
     * @return type of bracket 
     */
    public BracketType getType() {
        return type;
    }
    
    /**
     * Get number of line bracket in file
     * 
     * @return number of line
     */
    public int getLineNumber() {
        return lineNumber;
    }
    
    /**
     * Get number of letter in line
     * 
     * @return number of letter
     */
    public int getLetterNumber() {
        return letterNumber;
    }
}
