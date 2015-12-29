package project5;
/**
 * The class represents a Binary Search Tree
 * Made up of nodes that holds generic values
 * @author Helen Chang
 */
public class BST<E extends Comparable<E>> implements BSTInterface<E>{
	
	//variables
	private BSTNode<E> root;
	private int size;

	
	//methods
	/**
	 * No-ArgsConstructor
	 * Sets root to null and default size to 0
	 */
	public BST(){
		this.root = null;
		this.size = 0;
	}
//	/*
//	 * Prints out tree using pre-order traversal. This was for debugging purposes
//	 * 
//	 */
//	public void printTree(){
//		printTree(root);
//	}
//	private void printTree(BSTNode<E> node){
//		//pre order traversal
//		if (node == null){
//			return;
//		}
//		System.out.println(node.getData());
//		printTree(node.getLeft());
//		printTree(node.getRight());
//	}


	/**
	* Adds an item to this binary search tree. 
	* @param item the item to be added
	*/
	public void insert(E item) {
		if (root == null){
			root = new BSTNode<E>(item);
		}
		else{
			insert(item, root);
		}
		size++;
	}
	/**
	 * Recursive method for adding item to binary search tree
	 * @param item to add
	 * @param node referencing place in tree traversed to to try to find place to add element
	 */
	private void insert(E item, BSTNode<E> node){
		BSTNode<E> curr = node;

		//going left or right?

		//if going right
		if (curr.compareTo(item) < 0){
			if (curr.getRight() == null){
				curr.setRight(new BSTNode<E>(item));
			}
			else
				insert(item, curr.getRight());
		}
		//if going left
		else
			if (curr.getLeft() == null){
				curr.setLeft(new BSTNode<E>(item));
			}
			else
				insert(item, curr.getLeft());
	}

	/**
	* Removes an item from this binary search tree.
	* If item is not in the tree, the structure is unchanged. 
	* @param item the item to be removed
	*/
	public void remove(E item){
		if (root.equals(item)){
			root = null;
			return;
		}
		
		BSTNode<E> parent = this.findParent(item);
		boolean isLeftChild;
		if (parent.getLeft() != null){
			if (parent.getLeft().equals(item)){
				isLeftChild = true;
			}
			else
				isLeftChild = false;
		}
		else
			isLeftChild = false;
		
		boolean isRightChild;
		if (parent.getRight() != null){
			if (parent.getRight().equals(item)){
				isRightChild = true;
			}
			else
				isRightChild = false;
		}
		else
			isRightChild = false;
		
		if (isLeftChild){
			BSTNode<E> toRemove = parent.getLeft();
			if(toRemove.isLeaf()){	
				parent.setLeft(null);
			}
			//else if toRemove is only child
			else if(toRemove.hasLeft() && !toRemove.hasRight()){
				parent.setLeft(toRemove.getLeft());
			}
			else if(!toRemove.hasLeft() && toRemove.hasRight()){
				parent.setLeft(toRemove.getRight());
			}
			//else if toRemove has 2 children
			else{
				BSTNode<E> predecessor = this.getPredecessor(toRemove);
				E replacementData = predecessor.getData();
				remove(predecessor.getData());
				toRemove.setData(replacementData);
			}	
		}
		
		else if (isRightChild){
			BSTNode<E> toRemove = parent.getRight();
			if(toRemove.isLeaf()){
				parent.setRight(null);
			}
			//else if toRemove is only child
			else if(toRemove.hasLeft() && !toRemove.hasRight()){
				parent.setRight(toRemove.getLeft());
			}
			else if(!toRemove.hasLeft() && toRemove.hasRight()){
				parent.setRight(toRemove.getRight());
			}
			else{
				BSTNode<E> predecessor = this.getPredecessor(toRemove);
				E replacementData = predecessor.getData();
				remove(predecessor.getData());
				toRemove.setData(replacementData);
			}	
		}
	}
	
	/**
	 * Gets a predecessor of an item
	 * @param node containing value user wants to find predecessor of
	 * @return node containing predecessor value
	 */
	public BSTNode<E> getPredecessor(BSTNode<E> node){
		BSTNode<E> toReturn = node.getLeft();
		while (toReturn.getRight()!=null){
			toReturn = toReturn.getRight();
		}
		return toReturn;
	}
	
	/**
	 * Finds the parent node of a given item
	 * @param item user wants to find parent of
	 * @return BSTNode that is the parent of the item placed as argument 
	 */
	public BSTNode<E> findParent(E item){
		if(item.equals(root)){
			System.out.println("There is no parent. Item is root");
			return null;
		}
		else{
			return findParent(item, root);
		}
	}
	/*
	 * Recursive call for find parent method
	 */
	private BSTNode<E> findParent(E item, BSTNode<E> node){
		if (node.compareTo(item) > 0){
			if (node.getLeft() == null){
				return null;
			}
			else if (node.getLeft().equals(item)){
				return node;
			}
			else return findParent(item, node.getLeft());
		}
		else if (node.compareTo(item) < 0){
			if (node.getRight() == null){
				return null;
			}
			else if (node.getRight().equals(item)){
				return node;
			}
			else return findParent(item, node.getRight());
		}
		return null;
	}
	
	/**
	* Determines if an item is located in this binary search tree. 
	* @param item the item to be located
	* @return true if the item is in the tree, false otherwise
	*/
	public boolean contains(E item) {
		return contains(item, root);
	}
	/*
	 * Recursive method that determines of item is located in BST
	 * @param item the item to be located
	 * @param node of traversal place in tree
	 * @return true if found, false otherwise
	 */
	public boolean contains(E item, BSTNode<E> node) {
		if(node == null){
			return false;
		}
		else if (node.equals(item)){
			return true;
		}
		else if(node.compareTo(item) > 0){
			return contains(item, node.getLeft());
		}
		else return contains(item, node.getRight());
	}
	
	//Generated getters and setters for private variables
	/**
	 * getter method for root node
	 * @return the root
	 */
	public BSTNode<E> getRoot() {
		return root;
	}
	/**
	 * Setter method for root
	 * @param root the root to set
	 */
	public void setRoot(BSTNode<E> root) {
		this.root = root;
	}

	/**
	 * getter method for size
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	/**
	 * setter method for size
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}
}


/**
 * The class represents a Binary Search Tree Node
 * Holds a generic value and references to at most 2 children nodes
 * @author Helen Chang
 */

class BSTNode<E extends Comparable<E>> implements Comparable<BSTNode<E>>{
	
	private BSTNode<E> left;
	private BSTNode<E> right;
	private E data;

	/**
	 * Constructor
	 * Creates a new BSTNode object that holds a specified data
	 * @param data of data to set node
	 */
	public BSTNode (E data){
		this.data = data;
		left = null;
		right = null;
	}
	
	/**
	 * Constructor
	 * Creates a new BSTNode object that holds a specified data and specified children
	 * @param data of data to set node
	 * @param left BSTNode to be set as left child
	 * @param right BSTNode to be set as right child
	 */
	public BSTNode(E data, BSTNode<E> left, BSTNode<E> right){
		this.data = data;
		this.left = left;
		this.right = right;
	}
	//getters and setters
	
	/**
	 * Setter method for left child
	 * @param BSTNode representing left child
	 */
	public void setLeft(BSTNode<E> left){
		this.left = left;
	}
	/**
	 * Getter method for left child
	 * @return BSTNode representing left child
	 */
	public BSTNode<E> getLeft(){
		return this.left;
	}
	/**
	 * Setter method for right child
	 * @param BSTNode representing right child
	 */
	public void setRight(BSTNode<E> right){
		this.right = right;
	}
	/**
	 * Getter method for left child
	 * @return BSTNode representing left child
	 */
	public BSTNode<E> getRight(){
		return this.right;
	}
	/**
	 * Setter method for data
	 * @param data the BSTNode holds
	 */
	public void setData(E data){
		this.data = data;
	}
	/**
	 * Getter method for data
	 * @param data the BSTNode holds
	 */
	public E getData(){
		return this.data;
	}
	/**
	 * Checks if BSTNode has right child
	 * @return true if node has right child, false otherwise
	 */
	public boolean hasRight(){
		if (this.getRight()==null){
			return false;
		}
		return true;
	}
	
	/**
	 * Checks if BSTNode has left child
	 * @return true if node has left child, false otherwise
	 */
	public boolean hasLeft(){
		if (this.getLeft()==null){
			return false;
		}
		return true;
	}
	
	/**
	 * Checks if BSTNode is a leaf
	 * @return true if node is a leaf, false otherwise
	 */
	public boolean isLeaf(){
		
		if (this.hasLeft()){
			return false;
		}
		if (this.hasRight()){
			return false;
		}
		return true;
		
	}
	/**
	 * Checks if 2 BSTNodes are equal
	 * @param BSTnode to compare to
	 * @return true if nodes are equal, false otherwise
	 */
	public boolean equals(BSTNode<E> node){
		if (node.getData().equals(this.data)){
			return true;
		}
		else 
			return false;
	}
	
	/**
	 * Checks if value is equal to current BSTNode
	 * @param item whose value is compared to data current BSTNode holds
	 * @return true if node values are equal, false otherwise
	 */
	public boolean equals(E item){
		if (this.getData().equals(item)){
			return true;
		}
		else return false;
	}
	
	/**
	 * Compares the data of 2 BSTNodes
	 * @param data to compare to
	 * @return -1 if data to compare to is smaller than current node, 1 if larger, 0 if same
	 */
	public int compareTo(E data){
		return this.data.compareTo(data);

	}
	
	/**
	 * Compares the data of 2 BSTNodes
	 * @param BSTNode to compare to
	 * @return -1 if argument node is smaller than current node, 1 if larger, 0 if same
	 */
	public int compareTo(BSTNode <E> other){
		return this.data.compareTo(other.getData());
	}
}







