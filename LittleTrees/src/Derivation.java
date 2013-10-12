/**
 * Interface representing an expression derivation performed on a RPosition.
 * 
 * @author Group 10 : Lena Peschke
 * @version October 2013
 * 
 * @param <E>
 */
public interface Derivation<E> {

	/**
	 * Derives the expression contained in tree.
	 * @pre tree is not empty and contains enough parameters to be derived
	 * @post returns an RPosition representing the derivative of the expression in tree
	 * @throws EmptyTreeException if tree is empty
	 * @throws InvalidExpressionException if tree does not represent a valid expression
	 */
	RPosition<E> derive(RPosition<E> tree);
}
