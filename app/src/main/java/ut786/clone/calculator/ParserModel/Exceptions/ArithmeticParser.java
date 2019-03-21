package ut786.clone.calculator.ParserModel.Exceptions;

import ut786.clone.calculator.ParserModel.TokenParser;

public class ArithmeticParser extends TokenParser {
    public ArithmeticParser() throws LettersAlreadyAddedException, NoMoreStartStateException, NoSuchLetterFoundException {
        super();
        //add states to the dfa
        dfa.AddState(false,true,""); //1
        dfa.AddState(true);//2
        dfa.AddState(true); //3
        dfa.AddState(true); //4
        dfa.AddState(); //5
        //add transitions to the dfa, firstly for acceptable letters
        dfa.AddTransition(1,'-',4);
        dfa.AddTransition(1,'+',4);
        dfa.AddTransition(1,'*',4);
        dfa.AddTransition(1,'/',4);
        dfa.AddTransition(2,'.',3);
        dfa.AddTransition(1,'.',3);
        for (int i = 0; i < 10; i++)
        {
            dfa.AddTransition(1, Integer.toString(i).charAt(0), 2);
            dfa.AddTransition(2, Integer.toString(i).charAt(0), 2);
            dfa.AddTransition(3, Integer.toString(i).charAt(0), 3);
        }
        //add transition for others
        for (char item : letterSet.OtherLetters(new char[] { '-', '+', '/', '*','0','1','2','3','4','5','6','7','8','9','.' }))
        {
            dfa.AddTransition(1, item, 5);
        }
        for (char item : letterSet.OtherLetters(new char[] { '-', '+', '/', '*' }))
        {
            dfa.AddTransition(4, item, 5);
        }
        for (char item : letterSet.OtherLetters(new char[] { '0','1','2','3','4','5','6','7','8','9','.' }))
        {
            dfa.AddTransition(2, item, 5);
        }
        for (char item : letterSet.OtherLetters(new char[] { '0','1','2','3','4','5','6','7','8','9' }))
        {
            dfa.AddTransition(3, item, 5);
        }
        //add transitions for all
        for (char item : letterSet.getLetters())
        {
            dfa.AddTransition(5, item, 5);
        }
    }

}
