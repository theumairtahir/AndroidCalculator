package ut786.clone.calculator.ParserModel;

import java.util.ArrayList;
import java.util.List;

import ut786.clone.calculator.ParserModel.Exceptions.LetterAlreadyAddedException;
import ut786.clone.calculator.ParserModel.Exceptions.LettersAlreadyAddedException;
import ut786.clone.calculator.ParserModel.Exceptions.NoSuchLetterFoundException;

public class LettersSet {
    //Declared a list of letters where all the letters will be stored
    private List<Character> letters;
    public LettersSet(){
        //initialized the list of letters
        letters=new ArrayList<Character>();
    }
    public void AddLetter(char letter) throws LetterAlreadyAddedException {
        //this method is to add a single letter to the list
        for (char item:letters) {
            //check whether if the letter to be added is already present in the list, if so, then throw an exception
            if (item == letter)
                throw new LetterAlreadyAddedException(letter);
        }
        //if the letter is unique for the list then add it
        letters.add(letter);
    }
    public void AddLetters(char[] letters) throws LettersAlreadyAddedException {
        //this method is to add a set of letters to the list
        List<Character> exLetters = new ArrayList<Character>(); //initialized a list to store the already present letters in the list
        boolean flag = true;
        for (int i = 0; i < letters.length; i++)
        {
            for (char item : this.letters)
            {
                //check presence of each letter into the list
                if (item == letters[i])
                {
                    flag = false; //turn the flag to false if the letter is already present into the list
                    exLetters.add(item); //add that letter to the list of already present letters
                    break; //breaks the foreach loop
                }
            }
            if (flag) //if the letter is not present then add it to the list
                this.letters.add(letters[i]);
            else //otherwise set the flag again to true to check existance of a new letter
                flag = true;

        }
        if (exLetters.size()> 0) //if something is present into the list then throws an exception
            throw new LettersAlreadyAddedException(exLetters);
    }

    public void AddLetters(int fromDec, int toDec) throws LettersAlreadyAddedException {
        //Method to add a range of letters by entering the ASCII
        List<Character> letters = new ArrayList<Character>();
        for (int i = fromDec; i <= toDec; i++)
        {
            letters.add((char)i);
        }
        List<Character> exLetters = new ArrayList<Character>(); //initialized a list to store the already present letters in the list
        boolean flag = true;
        for (int i = 0; i < letters.size(); i++)
        {
            for (char item : this.letters)
            {
                //check presence of each letter into the list
                if (item == letters.get(i))
                {
                    flag = false; //turn the flag to false if the letter is already present into the list
                    exLetters.add(item); //add that letter to the list of already present letters
                    break; //breaks the foreach loop
                }
            }
            if (flag) //if the letter is not present then add it to the list
                this.letters.add(letters.get(i));
            else //otherwise set the flag again to true to check existance of a new letter
                flag = true;

        }
        if (exLetters.size() > 0) //if something is present into the list then throws an exception
            throw new LettersAlreadyAddedException(exLetters);
    }
    public void AddLetters(char from, char to) throws LettersAlreadyAddedException {
        //Method to add a range of letters by entering the character bounds itself
        List<Character> letters = new ArrayList<Character>();
        for (int i = from; i <= to; i++)
        {
            letters.add((char)i);
        }
        List<Character> exLetters = new ArrayList<Character>(); //initialized a list to store the already present letters in the list
        boolean flag = true;
        for (int i = 0; i < letters.size(); i++)
        {
            for (char item : this.letters)
            {
                //check presence of each letter into the list
                if (item == letters.get(i))
                {
                    flag = false; //turn the flag to false if the letter is already present into the list
                    exLetters.add(item); //add that letter to the list of already present letters
                    break; //breaks the foreach loop
                }
            }
            if (flag) //if the letter is not present then add it to the list
                this.letters.add(letters.get(i));
            else //otherwise set the flag again to true to check existence of a new letter
                flag = true;

        }
        if (exLetters.size() > 0) //if something is present into the list then throws an exception
            throw new LettersAlreadyAddedException(exLetters);
    }
    public char[] OtherLetters(char[] letters)
    {
        //Method to get letters other than the selected letters
        List<Character> lstTemp = new ArrayList<Character>();
        List<Character> temp = new ArrayList<Character>();
        for (char item : this.letters)
        {
            //copying the list of letters to the temp list
            lstTemp.add(item);
        }
        for (int i = 0; i < lstTemp.size(); i++)
        {
            for (char item : letters)
            {
                if (item == lstTemp.get(i))
                {
                    lstTemp.remove(i); //if a char from the selected matches with a char in temp list then remove it
                    i--; //get a step back because an item is removed from the array
                }
            }
        }
        //converting lst to array
        char[] tempArray=new char[lstTemp.size()];
        for (int i=0; i<lstTemp.size();i++){
            tempArray[i]=lstTemp.get(i);
        }
        return tempArray;
    }
    public void RemoveLetter(char letter) throws NoSuchLetterFoundException {
        //Method to remove a letter from the set
        boolean flag = true;
        for (int i = 0; i < letters.size(); i++)
        {
            if (letters.get(i) == letter)
            {
                flag = false;
                letters.remove(i); //if the letter is present in the list then remove the occurance of the letter
                break; //breaks the loop after removing
            }
        }
        if (flag) //but if no letter is deleted then the method throws an exception
            throw new NoSuchLetterFoundException(letter);
    }
    public int getIndex(char letter) throws NoSuchLetterFoundException {
        //Method for this letter class, returns the index of the letter
        int i = letters.indexOf(letter);
        if (i >= 0)
            return i;
        else
            throw new NoSuchLetterFoundException(letter);
    }
    public boolean IsLetter(char letter)
    {
        for (char item : letters)
        {
            if (item == letter)
            {
                return true; //if the letter is present in the set then return true
            }
        }
        return false; //otherwise false
    }
    public char[] getLetters(){
        //converting lst to array
        char[] tempArray=new char[letters.size()];
        for (int i=0; i<letters.size();i++){
            tempArray[i]=letters.get(i);
        }
        return tempArray;
    }
    @Override
    public String toString()
    {
        String s = "";
        for (char item : letters)
        {
            s += item;
        }
        return s;
    }
    public char getChar(int i){
        //Method for this letter class, returns the char at the index
        if (i >= 0 && i<letters.size())
            return letters.get(i);
        else
            throw new IndexOutOfBoundsException();
    }
    public short[] ToASCIIArray()
    {
        short[] asciiArray = new short[letters.size()];
        for (int i = 0; i < letters.size(); i++)
            asciiArray[i] = (short) letters.get(i).charValue();
        return asciiArray;
    }
}
