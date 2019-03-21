package ut786.clone.calculator.ParserModel.Exceptions;

public class NoSuchLetterFoundException extends Exception {
    public NoSuchLetterFoundException(char letter)
    {
        super("No such letter found. " + letter + " is not present in the set of defined letters.");
    }
}
