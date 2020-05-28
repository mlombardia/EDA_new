public class ExpTreeTester {
    public static void main(String args[]){
        ExpTree expTree = new ExpTree("( ( 2 + 3.5 ) * -10 )\n");
        expTree.preorder();
        expTree.postorder();
        expTree.inorder();
        expTree.eval();
    }
}
