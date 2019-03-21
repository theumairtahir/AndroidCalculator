package ut786.clone.calculator.ParserModel.Exceptions;

public class MissingEdgeException extends Exception {
    public MissingEdgeException(int stateIndex, char letter)
    {
        super("There is a missing edge on " + stateIndex + " state for " + letter + ". A DFA cannot have a missing edge.");
    }
}
