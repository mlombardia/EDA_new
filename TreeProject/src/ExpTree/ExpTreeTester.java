package ExpTree;

public class ExpTreeTester {
    public static void main(String args[]){
        ExpTree expTree = new ExpTree("( ( %.2d + 3.5 ) * -10 )\n", 2);
        expTree.preorder();
        expTree.postorder();
        expTree.inorder();
        expTree.eval();
    }
}
