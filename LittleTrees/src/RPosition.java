/**
 * 
 * @author Groupe 10
 * @version octobre 2013
 * 
 * @param <E>
 */

public class RPosition<E> implements Position<E> {

	private E element;
	private RPosition<E> left, right;
	
	/**
	 *TODO
	 */
	public RPosition() {
		this.element = null;
		this.left = null;
		this.right = null;
	}
	
	/**
	 *TODO
	 */
	public RPosition(E element) {
		this.element = element;
		this.left = null;
		this.right = null;
	}
	
	@Override
	public E element() {
		return element;
	}
	
	/** 
	 * Set the element of this position to elem.
	 * TODO
	 */
	public void setElement(E elem) {
		this.element = elem;
	}
	
	/** 
	 * Getter and setter for left and right.
	 * TODO
	 */
	public RPosition<E> getLeft() {
		return left;
	}
	
	public void setLeft(RPosition<E> left) {
		this.left = left;
	}

	public RPosition<E> getRight() {
		return right;
	}
	public void setRight(RPosition<E> right) {
		this.right = right;
	}
	
	/**
	 * @pre -
	 * @post return the number of nodes this position contains + 1
	 * 
	 * @source http://stackoverflow.com/questions/547622/counting-nodes-in-a-tree-in-java
	 **/
	public int size() {
		int count = 1; // for the current RPosition
		if(left!=null)  count += left.size();
		if(right!=null) count += right.size();
		return count;
	}
}