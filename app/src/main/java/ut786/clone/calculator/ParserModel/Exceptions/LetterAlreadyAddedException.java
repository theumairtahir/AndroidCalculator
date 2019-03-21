package ut786.clone.calculator.ParserModel.Exceptions;

public class LetterAlreadyAddedException extends Exception {
    public LetterAlreadyAddedException(char letter){
        super(letter + " is already present in the list. The DFA couldn't contain duplicate letters.");
    }
}
