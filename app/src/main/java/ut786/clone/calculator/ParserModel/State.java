package ut786.clone.calculator.ParserModel;

import ut786.clone.calculator.ParserModel.Exceptions.NoSuchLetterFoundException;

public class State {
    private boolean isStart, isFinal;
    private State[] transitionStates;
    private LettersSet lettersSet;
    private String tag;
    public State(LettersSet lettersSet, boolean isFinal, boolean isStart,String tag){
        this.isStart=isStart;
        this.isFinal=isFinal;
        this.lettersSet=lettersSet;
        transitionStates=new State[lettersSet.getLetters().length];
        this.tag=tag;
    }
    public void AddTransition(char letter, State nextState) throws NoSuchLetterFoundException {
        //Method to add transitions to a state
        transitionStates[lettersSet.getIndex(letter)] = nextState; //the logic is to get the index of the letter and on the same index store the next state
    }
    public void RemoveTransitionAt(char letter) throws NoSuchLetterFoundException {
        //Method to remove a transition at a certain letter
        transitionStates[lettersSet.getIndex(letter)] = null;
    }
    public boolean isStart(){
        return isStart;
    }
    public boolean isFinal(){
        return isFinal;
    }
    public State getStateofLetter(char letter) throws NoSuchLetterFoundException {
        //this Method will return the next state on the given letter
        return transitionStates[lettersSet.getIndex(letter)];
    }
}
