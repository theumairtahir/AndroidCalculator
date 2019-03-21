package ut786.clone.calculator.ParserModel.Exceptions;

import java.util.List;

public class LettersAlreadyAddedException extends Exception {
    String message="";
    public LettersAlreadyAddedException(List<Character> letters)
    {
        for (char item : letters)
        {
            message = message + item + " ";
        }
        message += "is/are already present in the list. The DFA couldn't contain duplicate letters.";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
