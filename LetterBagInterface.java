package project5;

import java.util.ArrayList;
/**
 * LetterBag interface requires methods that finds a list of words that can be found. 
 * 
 * @author Helen Chang, Joanna Klukowska
 */
public interface LetterBagInterface {
	
	/**
	 * This method determines the list of words that can be created
	 * from a given LetterBad object that are present in the
	 * provided Dictionary object dict. 
	 * @param dict the Dictionary object to be used 
	 * @return a list of valid words in alphabetical order 
	 */
	ArrayList<String> getAllWords ( Dictionary dict); 
}
