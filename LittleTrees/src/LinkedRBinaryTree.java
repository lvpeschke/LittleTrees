import java.util.ArrayList;

/**
 * 
 * @author Groupe 10
 * @version octobre 2013
 * 
 * @param <E>
 */

public class LinkedRBinaryTree<E> implements RBinaryTree<E> {

	private int size;
	private RPosition<E> root;
	
	/** CONSTRUCTORS */
	public LinkedRBinaryTree() {
		this.size = 0;
		this.root = null;
	}
	
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
		return this.root.getLeft()==null && this.root.getRight()==null;
	}
	
	/**
	 * @pre root is not null
	 * @post return true if this tree has a left tree
	 */
	public boolean hasLeft() {
		return this.root.getLeft()!=null;
	}
	
	/**
	 * @pre root is not null
	 * @post return true if this tree has a right tree
	 */
	public boolean hasRight() {
		return this.root.getRight()!=null;
	}

	@Override
	public RBinaryTree<E> leftTree() {
		RPosition<E> left = this.root.getLeft();
		int lSize = 0;
		if(left!=null)
			lSize = left.size();
		
		return new LinkedRBinaryTree<E>(lSize, left);
	}

	@Override
	public RBinaryTree<E> rightTree() {
		RPosition<E> right = this.root.getRight();
		int rSize = 0;
		if(right!=null)
			rSize = right.size();
		
		return new LinkedRBinaryTree<E>(rSize, right);
	}

	@Override
	public void setElement(E o) {
		this.root.setElement(o);
	}

	@Override
	public void setLeft(RBinaryTree<E> tree) {
		this.size += tree.size()-this.root.getLeft().size();
		this.root.setLeft((RPosition<E>) tree.root());
	}

	@Override
	public void setRight(RBinaryTree<E> tree) {
		this.size += tree.size()-this.root.getRight().size();
		this.root.setRight((RPosition<E>) tree.root());
	}

	@Override
	public Iterable<Position<E>> positions() {
		ArrayList<Position<E>> positions = new ArrayList<Position<E>>();
		getPositionInorder(positions, this);
		return positions;
	}
	
	/**
	 * @pre pos is not null and T is not null
	 * @post pos contains all the RPositions of T following the inorder traversal of T
	 */
	public void getPositionInorder(ArrayList<Position<E>> pos, LinkedRBinaryTree<E> T) {
		if(T.hasLeft()) 
			getPositionInorder(pos, (LinkedRBinaryTree<E>) T.leftTree());
		
		pos.add(T.root());
		
		if(T.hasRight()) 
			getPositionInorder(pos, (LinkedRBinaryTree<E>) T.rightTree());
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