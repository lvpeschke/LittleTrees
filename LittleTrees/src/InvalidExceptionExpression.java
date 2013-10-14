/**
 * Runtime exception thrown if the expression contained in the tree is invalid.
 * 
 * @author Group 10 : Lena Peschke
 * @version October 2013
 */
@SuppressWarnings("serial")
public class InvalidExceptionExpression extends RuntimeException {
	public InvalidExceptionExpression(String err) {
		super(err);
	}

}
