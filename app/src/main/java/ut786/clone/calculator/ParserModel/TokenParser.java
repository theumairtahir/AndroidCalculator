package ut786.clone.calculator.ParserModel;



import java.util.ArrayList;
import java.util.List;

import ut786.clone.calculator.ParserModel.Exceptions.LettersAlreadyAddedException;
import ut786.clone.calculator.ParserModel.Exceptions.MissingEdgeException;
import ut786.clone.calculator.ParserModel.Exceptions.NoInitialStateFoundException;
import ut786.clone.calculator.ParserModel.Exceptions.NoSuchLetterFoundException;

public class TokenParser {
    protected LettersSet letterSet;
    protected DFA dfa;
    protected TokenParser() throws LettersAlreadyAddedException {
        letterSet = new LettersSet();
        letterSet.AddLetters(0, 127); //add all ascii characters to the letter set
        dfa = new DFA(letterSet); //add the letter set to the DFA
    }
    public TokenParser(DFA dfa)
    {
        letterSet = dfa.getLettersSet();
        this.dfa = dfa;
    }
    public boolean VerifyWord(String word) throws MissingEdgeException, NoInitialStateFoundException, NoSuchLetterFoundException {
        //Method to verify a word according to the above DFA
        return dfa.VerifyWord(word);
    }
    public List<Token> GenerateTokens(String s) throws MissingEdgeException, NoInitialStateFoundException, NoSuchLetterFoundException {
        List<Token> lstTokens = new ArrayList<Token>();
        //removes the white space
        s = s.replace(" ", "");
        char[] charATemp = s.toCharArray(); //taking a temporary char array to store the converted string to char array
        //index is a variable which will store the position to read from the char array
        for (int index = 0; index < charATemp.length; index++) {
            String sTemp = ""; //taking a temporary string to make and verify words
            String lastValidWord = null;
            for (int i = index; i < charATemp.length; i++) //this loop will iterate from a specified position to the end of the temp array
            {
                sTemp += charATemp[i]; //adding each char to the string to make a whole word and then verify it
                if (VerifyWord(sTemp)) {
                    //the word is verified then store it as the last valid and proceed to check further
                    //whether it could be some other valid word
                    //every single valid word in this process will overwrite the last valid word
                    lastValidWord = sTemp;
                    index = i; //set the new position to iterate at the end of the verified word
                }
            }
            if (lastValidWord != null) {
                Token t = new Token();
                t.setValue(lastValidWord);
                if (lastValidWord.charAt(0)=='+') {
                    t.setName("OPERATOR");
                    t.setPrecedence(2);
                } else if (lastValidWord.charAt(0)=='-') {
                    t.setName("OPERATOR");
                    t.setPrecedence(1);
                } else if (lastValidWord.charAt(0)=='*') {
                    t.setName("OPERATOR");
                    t.setPrecedence(3);
                } else if (lastValidWord.charAt(0)=='/') {
                    t.setName("OPERATOR");
                    t.setPrecedence(4);
                } else {
                    t.setName("Value");
                    t.setPrecedence(5);
                }
                lstTokens.add(t); //eventually the last valid word will be added to tokens list
            }
        }
        return lstTokens;
    }
}
