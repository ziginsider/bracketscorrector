package bracketscorrector;

/**
 *
 * @author zigin
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
    
    public BracketType getType() {
        return type;
    }
    
    public int getLineNumber() {
        return lineNumber;
    }
    
    public int getLetterNumber() {
        return letterNumber;
    }
}
