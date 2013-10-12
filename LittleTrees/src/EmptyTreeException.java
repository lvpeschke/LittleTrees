/**
 * Runtime exception thrown if one tries to access subtrees of an empty tree.
 * 
 * @author Group 10 : Lena Peschke
 * @version October 2013
 */
@SuppressWarnings("serial")
public class EmptyTreeException extends RuntimeException {
	public EmptyTreeException(String err) {
		super(err);
	}
}
