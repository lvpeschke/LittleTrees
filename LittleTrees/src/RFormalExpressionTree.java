
public class RFormalExpressionTree implements FormalExpressionTree {

	private LinkedRBinaryTree<?> tree;
	
	/** Constructors **/
	public RFormalExpressionTree() {
		tree = new LinkedRBinaryTree<>();
	}
	
	public RFormalExpressionTree(String expression) {
		tree = createTree(expression);
	}
	
	public RFormalExpressionTree(LinkedRBinaryTree<?> LRBinTree) {
		tree = LRBinTree;
	}
	
	/**
	 * Cette methode va creer l'arbre binaire a partir du string de l'expression arithmetique en s'appellant recursivement
	 * sur les sous-arbres gauches et droits de l'arbre que l'on cree et que l'on renvoi. 
	 * @pre expression n'est pas null
	 * @param expression : un expression parfaitement parenthesee
	 * @return un arbre binaire representant l'expression passee en param
	 */
	public LinkedRBinaryTree<String> createTree(String expression) {
		char[] expr = expression.toCharArray();
		if(expr.length==0) return null;
		int index = 0; // index du tableau expr
		
		LinkedRBinaryTree<String> tree = new LinkedRBinaryTree<String>(); 
		String leftExpr = "", rightExpr = ""; // on va identifier les membres de gauches et de droite de l'expression
		
		if(expr[index]=='(') { 
			index++;
			if(index<expr.length) leftExpr = getExpression(expr, index);
			index += leftExpr.length();
			if(index<expr.length) tree.setElement(""+expr[index]);
			index++;
			if(index<expr.length) rightExpr = getExpression(expr, index);
			
			if(!leftExpr.equals("") && rightExpr.equals("") && expr[index-1]==')') { // Cas ou on a cos(x) => (x) pas un expression normale
				tree.setElement(leftExpr);
				return tree;
			}
			
			System.out.println("Expression : "+expression + " | left : "+leftExpr + " | right : "+rightExpr + " | root : "+tree.root().element());
			
			if(!leftExpr.equals("")) tree.setLeft(createTree(leftExpr)); // Appel recursif pour construire les sous-arbres
			if(!rightExpr.equals("")) tree.setRight(createTree(rightExpr));
		}
		else { 
			// Si le string ne commence pas par une parenthese, on a soit un nombre simple a placer
			// dans la racine, soit un sinus ou un cosinus.
			String exp = getExpression(expr, 0);
			if(exp.startsWith("cos") || exp.startsWith("sin")) {
				tree.setElement(exp.substring(0, 3));
				tree.setLeft(createTree(getExpression(expr, index+3))); // 3 = taille du mot "sin" ou "cos"
			}
			else {
				tree.setElement(exp);
			}
		}
		return tree;
	}
	
	/** 
	 * Renvoie l'expression qui se trouve juste au niveau de satrt dans exprTab
	 * Par exemple, si exprTab vaut [( ( x + 1 ) * 2 )] : 
	 *  getExpression(exprTab, 1) donne (x+1) et getExpression(exprTab, 2) donne x
	 * @param exprTab un tableau de char qui represente  une expression arithmetique
	 * @param start l'index de depart de la methode dans le tableau
	 * @return un string qui represente l'expression arithmetique qui commence a 
	 * start dans exprTab
	 */
	private String getExpression(char[] exprTab, int start) {
		String result = "";
		int pCount = 0;
		char first = exprTab[start];
		if(first=='(') { 
			result = "("; 
			pCount++; 
		}
		else if(first=='x') result = "x";
		else if(first=='c') result = "cos"+getExpression(exprTab, start+3); 
		else if(first=='s') result = "sin"+getExpression(exprTab, start+3); 
		else { // Get a number
			for(int i = start; i < exprTab.length && ((exprTab[i] >= '0' && exprTab[i] <= '9') || exprTab[i]=='-'); i++) { 
				result += exprTab[i];
			}
		}
		
		for(int i = start+1; i < exprTab.length && pCount!=0; i++) {
			result += exprTab[i];
			if(exprTab[i]=='(') pCount++; 
			if(exprTab[i]==')') pCount--;
		}
		return result;
	}

	/** Getter and setter for tree **/
	public LinkedRBinaryTree<?> getTree() {
		return tree;
	}

	public void setTree(LinkedRBinaryTree<?> tree) {
		this.tree = tree;
	}
	
	@Override
	public String toString() {
		return stringExpression(tree);
	}
	
	/** 
	 * Renvoie l'expression arithmetique stockee dans l'arbre tree
	 * Cette fonction utilise la recursive pour calculer les sous-expression a gauche 
	 * et a droite de l'arbre.
	 * @param tree l'arbre contenant une expression arithmetique
	 * @return un string representant l'expression arithmetique contenue dans tree
	 */
	private String stringExpression(LinkedRBinaryTree<?> tree) {
		if(tree.isEmpty()) return "";
		if(tree.isLeaf()) return ""+tree.root().element();
		
		String rootElem = ""+tree.root().element();
		if(rootElem.equals("cos")||rootElem.equals("sin")) {
			if(tree.leftTree()!=null && !tree.leftTree().isLeaf())
				return ""+rootElem+stringExpression((LinkedRBinaryTree<?>) tree.leftTree());
			else return ""+rootElem+"("+stringExpression((LinkedRBinaryTree<?>) tree.leftTree())+")";
		}
		
		return "("+stringExpression((LinkedRBinaryTree<?>) tree.leftTree())
				+rootElem
				+stringExpression((LinkedRBinaryTree<?>) tree.rightTree())+")";
	}

	@Override
	public FormalExpressionTree derive() {
		//TODO Depend de l'implementation des expression
		// derivation.derive(tree.root());
		return null;
	}
	/*
	public static void main(String[] args) {
		// TEST
		RFormalExpressionTree formalTree = new RFormalExpressionTree("(cos(x))"); // ("((x+1)-(22*3))"); // (cos(x+1)*2) 
		for(Position<?> p : formalTree.getTree().positions()) {
			System.out.println(p.element());
		}
		//System.out.println(formalTree.toString());
		
		//System.out.println((new RFormalExpressionTree("(((x+2)*4)-(x+1))").toString()));
	}
	*/

}
