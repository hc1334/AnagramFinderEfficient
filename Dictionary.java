
package project5;

import java.util.ArrayList;

/**
 * The class represent a dictionary of words. 
 * It provides a way of searching through the dictionary.
 * It also can produce a dictionary in which the words are limited
 * to a particular length. 
 * 
 * @author Joanna Klukowska, Helen Chang
 *
 */
public class Dictionary extends BST<String> implements DictionaryInterface{

	/**
	 * Creates an empty Dictionary object (no words).
	 */
	public Dictionary ( ) {
		super();
	}
	
	/**
	 * Creates a Dictionary object containing all words from the 
	 * listOfWords passed as a parameter.
	 * 
	 * @param listOfWords the list of words to be stored in the newly created 
	 * Dictionary object
	 */
	public Dictionary ( ArrayList < String > listOfWords ) {
		ArrayList <String> words = listOfWords;
		if (null == words) {
			words = new ArrayList <String> ();
		}
		createTree(listOfWords, 0, listOfWords.size()-1);
	}
	
	/**
	 * Given a sorted arrayList strings, creates a balanced BST 
	 * @param AL of strings to be placed into BST
	 */
	private void createTree(ArrayList<String> listOfWords, int min, int max){
		if (min > max){
			return;
		}
		int mid = (min + max) / 2;
		this.insert(listOfWords.get(mid));
		
		createTree(listOfWords, min, mid-1);
		createTree(listOfWords, mid+1, max);
	}
	
	
	/**
	 * Creates a new Dictionary object from this Dictionary object that 
	 * contains words of a specified size.
	 * @param size length of the words that should be included in the new 
	 * Dictionary object
	 * @return a new Dictionary object containing only the words of specified 
	 * size
	 */
	public Dictionary getWordsBySize ( int size ) {

		Dictionary wordBySize = new Dictionary();
		//
		getWordsBySize(size, this.getRoot(), wordBySize);
		
		return wordBySize;
	}
	private void getWordsBySize(int size, BSTNode<String> node, Dictionary toAddTo){
		if (node == null){
			return;
		}
		if(node.getData().length() == size){
			toAddTo.insert(node.getData());
		}
		getWordsBySize(size, node.getLeft(), toAddTo);
		getWordsBySize(size, node.getRight(), toAddTo);
	}
	
	 
	/**
	 * Performs (binary) search in this Dictionary object for a given word.
	 * @param word  the word to look for in this Dictionary object. 
	 * @return true if the word is in this Dictionary object, false otherwise
	 */
	public boolean findWord (String word) {
		return contains(word);
	}
	
	/*
	 * The actual method providing recursive implementation of the binary search.
	 * @param word the word to look for in this Dictionary object
	 * @param begin start of the range for the current iteration
	 * @param end   end of the range for the current iteration
	 * @return  true if the word is in this Dictionary object, false otherwise
	 */
//	private boolean isWordInDictionaryRecursive ( String word, BSTNode<String> node) {
//		if (node==null)
//			return false;
//		
//		int half = (begin+end+1) / 2;
//		int comparison = words.get(half).compareToIgnoreCase(word);
//		
//		if ( comparison < 0 )
//			return isWordInDictionaryRecursive( word, half + 1, end );
//		else if ( comparison > 0 )
//			return isWordInDictionaryRecursive( word, begin, half - 1);
//		else
//			return true;
//	}
	
	/*
	 * Performs (binary) search in this Dictionary object for a given prefix.
	 * @param prefix  the prefix to look for in this Dictionary object. 
	 * @return true if at least one word with the specified prefix exists 
	 * in this Dictionary object, false otherwise
	 */
	public boolean findPrefix (String prefix ) {
		return isPrefixInDictionaryRecursive (prefix, this.getRoot());
	}

	/*
	 * The actual method providing recursive implementation of the binary search
	 * for the prefix. 
	 * @param prefix the prefix to look for in this Dictionary object.
	 * @param begin start of the range for the current iteration
	 * @param end end of the range for the current iteration
	 * @return true if at least one word with the specified prefix exists 
	 * in this Dictionary object, false otherwise
	 */
	private boolean isPrefixInDictionaryRecursive(String prefix, BSTNode<String> node) {
		if (node == null)
			return false;
				
		
		int comparison = node.getData().compareToIgnoreCase(prefix);
		boolean isPrefix = node.getData().startsWith(prefix);
		if (isPrefix) 
			return true;
		
		if (comparison < 0 )
			return isPrefixInDictionaryRecursive(prefix, node.getRight());
		else if ( comparison > 0 )
			return isPrefixInDictionaryRecursive( prefix, node.getLeft());
		else  //this case should never happen
			return true;
	}
	
	
	
}
