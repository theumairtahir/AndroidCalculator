package ut786.clone.calculator.ParserModel;

import java.util.ArrayList;
import java.util.List;

import ut786.clone.calculator.ParserModel.Exceptions.MissingEdgeException;
import ut786.clone.calculator.ParserModel.Exceptions.NoInitialStateFoundException;
import ut786.clone.calculator.ParserModel.Exceptions.NoMoreStartStateException;
import ut786.clone.calculator.ParserModel.Exceptions.NoSuchLetterFoundException;

public class DFA {
    private LettersSet lettersSet;
    private List<State> lstStates=new ArrayList<State>();
    public DFA(LettersSet letterSet)
    {
        this.lettersSet = letterSet;
    }
    public void AddState() throws NoMoreStartStateException {
        boolean isStart=false, isFinal=false;
        //Method to add a new state
        State state = new State(lettersSet,isFinal,isStart, "");
        if (lstStates.size() > 0)
        {
            for (State item : lstStates)
            {
                if (item.isStart() && isStart)
                {
                    throw new NoMoreStartStateException(); //A DFA has not more than one start state
                }
            }
        }
        lstStates.add(state);
    }
    public void AddState(boolean isFinal) throws NoMoreStartStateException {
        boolean isStart=false;
        //Method to add a new state
        State state = new State(lettersSet, isFinal, isStart,"");
        if (lstStates.size() > 0)
        {
            for (State item : lstStates)
            {
                if (item.isStart() && isStart)
                {
                    throw new NoMoreStartStateException(); //A DFA has not more than one start state
                }
            }
        }
        lstStates.add(state);
    }
    public void AddState(boolean isFinal, boolean isStart,String tag) throws NoMoreStartStateException {
        //Method to add a new state
        State state = new State(lettersSet, isFinal, isStart,tag);
        if (lstStates.size() > 0)
        {
            for (State item : lstStates)
            {
                if (item.isStart() && isStart)
                {
                    throw new NoMoreStartStateException(); //A DFA has not more than one start state
                }
            }
        }
        lstStates.add(state);
    }
    public void AddTransition(int stateIndex, char letter, int nextState) throws NoSuchLetterFoundException {
        //Method to add transition between states according to the language
        stateIndex--;
        nextState--;
        if (stateIndex >= 0 && stateIndex < lstStates.size())
        {
            lstStates.get(stateIndex).AddTransition(letter, lstStates.get(nextState)); //will throw an exception if next state will not be present
        }
        else
        {
            throw new IndexOutOfBoundsException();
        }
    }
    public boolean VerifyWord(String s) throws NoSuchLetterFoundException, MissingEdgeException, NoInitialStateFoundException {
        //Method to verify the words of the language
        boolean flag = false; //flag is the return value of this method, by default it is false
        State state = getInitialState(); //starting from the initial state
        for (char item : s.toCharArray())
        {
            int i = lstStates.indexOf(state);
            state = state.getStateofLetter(item); //returns the next state according to the transition table
            if (state == null)
                throw new MissingEdgeException(i+1, item);
        }
        if (state.isFinal())
        {
            //if the last state is a final state then the method will return true
            flag = true;
        }
        return flag;
    }
    public State getInitialState() throws NoInitialStateFoundException {
        for (State item : lstStates)
        {
            if (item.isStart())
                return item;
        }
        throw new NoInitialStateFoundException();
    }
    public LettersSet getLettersSet(){
        return lettersSet;
    }
}
