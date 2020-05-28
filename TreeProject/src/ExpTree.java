import java.util.Scanner;


public class ExpTree {
	
	private Node root;

	public ExpTree(String infija) {
		// token analyzer
	    Scanner inputScanner = new Scanner(infija).useDelimiter("\\n");
	    String line = inputScanner.nextLine();
	    inputScanner.close();
	    
	    buildTree(line);
	}
	
	
	public ExpTree() {
	    System.out.print("Introduzca la expresión en notación infija con todos los parentesis y blancos: ");

		// token analyzer
	    Scanner inputScanner = new Scanner(System.in).useDelimiter("\\n");
	    String line= inputScanner.nextLine();
	    inputScanner.close();

	    buildTree(line);
	}
	
	private void buildTree(String line)
	{	
		  // space separator among tokens
		  Scanner lineScanner = new Scanner(line).useDelimiter("\\s+");
		  root= new Node(lineScanner);
		  lineScanner.close();
	}

	public void preorder(){
		System.out.printf("%s ", root.data);
		printPreBranch(root.left);
		printPreBranch(root.right);
		System.out.println();
	}

	private void printPreBranch(Node node){
		if(node == null){
			return;
		}

		System.out.printf("%s ", node.data);
		if(node.left != null) {
			if (Utils.isConstant(node.left.data)) {
				System.out.printf("%s ", node.left.data);
			} else {
				printPreBranch(node.left);
			}
		}

		if(node.right != null) {
			if (Utils.isConstant(node.right.data)) {
				System.out.printf("%s ", node.right.data);
			} else {
				printPreBranch(node.right);
			}
		}
	}

	public void postorder(){
		printPostBranch(root.left);
		printPostBranch(root.right);
		System.out.printf("%s \n", root.data);
	}

	private void printPostBranch(Node node){
		if (!Utils.isConstant(node.data)) {
			printPostBranch(node.left);
			printPostBranch(node.right);
		}
		System.out.printf("%s ", node.data);
	}

	public void inorder(){
		System.out.print("(");
		printInBranch(root.left);

		System.out.printf(" %s ", root.data);

		printInBranch(root.right);
		System.out.println(")");
	}
    
	private void printInBranch(Node node){
		if(Utils.isConstant(node.data)){
			System.out.printf("%s", node.data);
		}else{
			System.out.print("(");
			printInBranch(node.left);

			System.out.printf(" %s ", node.data);

			printInBranch(node.right);
			System.out.print(")");
		}
	}

	public void eval(){
		System.out.println(doOperation(root.data,evaluateBranch(root.left),evaluateBranch(root.right)));
	}

	private double evaluateBranch(Node node){
		if(Utils.isConstant(node.data)){
			return Double.parseDouble(node.data);
		}
		return doOperation(node.data,evaluateBranch(node.left),evaluateBranch(node.right));
	}

	private double doOperation(String operand, double op1, double op2){
		double result=0;
		switch (operand){
			case "+":
				result = op1 + op2;
				break;
			case "-":
				result = op1 - op2;
				break;
			case "*":
				result = op1 * op2;
				break;
			case "/":
				result = op1 / op2;
				break;
		}
		return result;
	}

	static final class Node {
		private String data;
		private Node left, right;
		
		private Scanner lineScanner;

		public Node(Scanner theLineScanner) {
			lineScanner= theLineScanner;
			
			Node auxi = buildExpression();
			data= auxi.data;
			left= auxi.left;
			right= auxi.right;
			
			if (lineScanner.hasNext() ) 
				throw new RuntimeException("Bad expression");
		}
		
		private Node() 	{
		}
		
		
		
	
		private Node buildExpression() 	{
			Node n = new Node();

			if(lineScanner.hasNext("\\(")){
				lineScanner.next();

				n.left = buildExpression(); // sub-expresion

				//operador
				if(!lineScanner.hasNext())
					throw new RuntimeException("missing or invalid operator");
				n.data = lineScanner.next();
				if(!Utils.isOperator(n.data))
					throw new RuntimeException("missing or invalid operator");

				//sub-expresion
				n.right = buildExpression();

				// ) expected
				if (lineScanner.hasNext("\\)")){
					// lo consumo
					lineScanner.next();
				}else{
					throw new RuntimeException("missing )");
				}

				return n;
			}

			//constant
			if (!lineScanner.hasNext())
				throw new RuntimeException("missing expression");

			n.data = lineScanner.next();
			if (!Utils.isConstant(n.data)){
				throw new RuntimeException(String.format("Illegal term %s", lineScanner));
			}
			return n;
		}
	}  // end Node class
	static final class Utils{
		public static boolean isOperator(String s){
			return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
		}

		public static boolean isConstant(String s){
			return !(isOperator(s) || s.equals("(") || s.equals(")"));
		}
	}
}  // end ExpTree