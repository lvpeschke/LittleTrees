/**
 * RPosition implements the interface Position.
 * A RPosition tree possesses an element <E> and 2 subtrees,
 * right and left, also RPositions.
 * 
 * @author Group 10 : Guillaume Moyson and Lena Peschke
 * @version October 2013
 * 
 * @param <E>
 */

public class RPosition<E> implements Position<E> {

	private E element;
	private RPosition<E> left, right;
	
	/**
	 * Constructor
	 * @pre -
	 * @post element, left and right are set to null
	 */
	public RPosition() {
		this.element = null;
		this.left = null;
		this.right = null;
	}
	
	/**
	 * Constructor
	 * @pre -
	 * @post element is set to elem, left and right are set to null
	 */
	public RPosition(E elem) {
		this.element = elem;
		this.left = null;
		this.right = null;
	}
	
	@Override
	public E element() {
		return element;
	}
	
	/** 
	 * Set the element of this RPosition to elem.
	 * @pre -
	 * @post element is set to elem
	 */
	public void setElement(E elem) {
		this.element = elem;
	}
	
	/** 
	 * Get the left subtree stored at this RPosition.
	 * @pre -
	 * @post returns left, which can be null
	 */
	public RPosition<E> getLeft() {
		return left;
	}
	
	/** 
	 * Set the left subtree stored at this RPosition to left.
	 * @pre -
	 * @post this.left is set to left
	 */
	public void setLeft(RPosition<E> left) {
		this.left = left;
	}

	/** 
	 * Get the right subtree stored at this RPosition.
	 * @pre -
	 * @post returns right, which can be null
	 */
	public RPosition<E> getRight() {
		return right;
	}
	
	/** 
	 * Set the right subtree stored at this RPosition to right.
	 * @pre -
	 * @post this.right is set to right
	 */
	public void setRight(RPosition<E> right) {
		this.right = right;
	}
	
	/**
	 * Get the size of the entire tree from this RPosition on.
	 * @pre -
	 * @post return the number of subtrees this position contains + 1,
	 * 		 i.e. the total number of nodes of the tree, considering
	 * 		 this as the root
	 * 
	 * @source http://stackoverflow.com/questions/547622/counting-nodes-in-a-tree-in-java
	 **/
	public int size() {
		int count = 1; // counts the current RPosition
		if(left!=null)
			count += left.size();
		if(right!=null)
			count += right.size();
		return count;
	}
}