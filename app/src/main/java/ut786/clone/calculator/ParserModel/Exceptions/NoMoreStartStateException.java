package ut786.clone.calculator.ParserModel.Exceptions;

public class NoMoreStartStateException extends Exception {
    public NoMoreStartStateException(){
        super("A DFA cannot have more than one start state.");
    }
}
