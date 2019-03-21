package ut786.clone.calculator.ParserModel.Exceptions;

public class NoInitialStateFoundException extends Exception {
    public NoInitialStateFoundException(){
        super("A DFA must have an Initial State.");
    }
}
