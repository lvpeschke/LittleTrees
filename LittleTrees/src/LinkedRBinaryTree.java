import java.util.ArrayList;

/**
 * A LinkedRBinaryTree is a binary tree implemented as a linked structure
 * using RPositions. It knows its size and has a reference to its root.
 * 
 * @author Group 10 : Guillaume Moyson and Lena Peschke
 * @version October 2013
 * 
 * @param <E>
 */

public class LinkedRBinaryTree<E> implements RBinaryTree<E> {

	private int size;
	private RPosition<E> root;
	
	/**
	 * Constructor
	 * @pre -
	 * @post size is set to 0 and root to null
	 */
	public LinkedRBinaryTree() {
		this.size = 0;
		this.root = null;
	}
	
	/**
	 * Constructor
	 * @pre size >= 0
	 * @post this.size is set to size and this.root to root
	 */
	public LinkedRBinaryTree(int size, RPosition<E> root) {
		this.size = size;
		this.root = root;
	}

	@Override
	public boolean isEmpty() {
		return this.size==0;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public Position<E> root() {
		return this.root;
	}

	@Override
	public boolean isLeaf() {
		return (this.root.getLeft()==null) && (this.root.getRight()==null);
	}
	
	/**
	 * @pre root is not null
	 * @post return true if this tree has a left tree
	 * @throws EmptyTreeException if root is null
	 */
	public boolean hasLeft() {
		if (this.isEmpty())
			throw new EmptyTreeException("Empty tree: no left subtree");
		return this.root.getLeft()!=null;
	}
	
	/**
	 * @pre root is not null
	 * @post return true if this tree has a right tree
	 * @throws EmptyTreeException if root is null
	 */
	public boolean hasRight() {
		if (this.isEmpty())
			throw new EmptyTreeException("Empty tree: no right subtree");
		return this.root.getRight()!=null;
	}

	@Override
	/**
	 * @throws EmptyTreeException if root is null
	 */
	public RBinaryTree<E> leftTree() {
		if (this.isEmpty())
			throw new EmptyTreeException("Empty tree: no left subtree");
		
		RPosition<E> left = this.root.getLeft();
		int lSize = 0;
		if (left!=null)
			lSize = left.size();
		
		return new LinkedRBinaryTree<E>(lSize, left);
	}

	@Override
	/**
	 * @throws EmptyTreeException if root is null
	 */
	public RBinaryTree<E> rightTree() {
		if (this.isEmpty())
			throw new EmptyTreeException("Empty tree: no right subtree");
		
		RPosition<E> right = this.root.getRight();
		int rSize = 0;
		if (right!=null)
			rSize = right.size();
		
		return new LinkedRBinaryTree<E>(rSize, right);
	}

	@Override
	/**
	 * @throws EmptyTreeException if root is null
	 */
	public void setElement(E o) {
		if (this.isEmpty()) {
			root = new RPosition<E>(); 
			size = 1;
		}
		this.root.setElement(o);
	}

	@Override
	/**
	 * @throws EmptyTreeException if root is null
	 */
	public void setLeft(RBinaryTree<E> tree) {
		if (this.isEmpty())
			throw new EmptyTreeException("Empty tree: no left element to be set");

		if(this.root.getLeft()==null) this.size += tree.size();
		else this.size += tree.size()-this.root.getLeft().size();
		this.root.setLeft((RPosition<E>) tree.root());
	}

	@Override
	/**
	 * @throws EmptyTreeException if root is null
	 */
	public void setRight(RBinaryTree<E> tree) {
		if (this.isEmpty())
			throw new EmptyTreeException("Empty tree: no right element to be set");

		if(this.root.getRight()==null) this.size += tree.size();
		else this.size += tree.size()-this.root.getRight().size();
		this.root.setRight((RPosition<E>) tree.root());
	}

	/**
	 * @pre pos is not null and T is not null
	 * @post pos contains all the RPositions of T following the inorder traversal of T
	 */
	public void getPositionInorder(ArrayList<Position<E>> pos, LinkedRBinaryTree<E> T) {
		if (T.hasLeft()) 
			getPositionInorder(pos, (LinkedRBinaryTree<E>) T.leftTree());
		pos.add(T.root());
		
		if (T.hasRight()) 
			getPositionInorder(pos, (LinkedRBinaryTree<E>) T.rightTree());
	}
	
	@Override
	public Iterable<Position<E>> positions() {
		ArrayList<Position<E>> positions = new ArrayList<Position<E>>();
		getPositionInorder(positions, this);
		return positions;
	}
	
	/*
	public static void main(String[] args) {
		// Tests
		RPosition<Integer> root = new RPosition<Integer>();
		System.out.println("Root size = "+root.size());
		root.setLeft(new RPosition<Integer>(1));
		
		RPosition<Integer> subRight = new RPosition<Integer>(4);
		subRight.setLeft(new RPosition<Integer>(3));
		subRight.setRight(new RPosition<Integer>(5));
		root.setRight(subRight);
		
		LinkedRBinaryTree<Integer> tree =
            new LinkedRBinaryTree<Integer>(root.size(), root);
		System.out.println("Tree size = "+tree.size());
		tree.setElement(2);
		
		System.out.println("Is leaf "+tree.isLeaf());
		System.out.println("Tree has left ? "+tree.hasLeft());
		System.out.println("Element right tree : "+
                           tree.rightTree().root().element());
		
		for(Position<Integer> p : tree.positions()) {
			System.out.println("Element : "+p.element());
		}
	}*/
}