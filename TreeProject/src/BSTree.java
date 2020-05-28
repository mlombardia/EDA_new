public class BSTree<T extends Comparable<? super T>> /*implements BSTreeInterface*/{
/*
  private Node root;

    public BSTree(){
    }

    @Override
    public void insert(Comparable myData) {
        if (root == null){
            root = new Node((T)myData);
        }
        searchPlaceAndInsert(root, (T)myData);
    }

    private void searchPlaceAndInsert(Node node, T elem){
        if (elem.compareTo(node.data) < 0){
            if (node.left == null){
                node.left = new Node(elem);
                return;
            }
            searchPlaceAndInsert(node.left,elem);
        }else if(elem.compareTo(node.data) > 0){
            if (node.right == null){
                node.right = new Node(elem);
                return;
            }
            searchPlaceAndInsert(node.right,elem);
        }
    }

    @Override
    public void delete(Comparable myData) {

    }

    @Override
    public boolean contains(Comparable myData) {
        return false;
    }

    @Override
    public void preOrder() {
        /*System.out.printf("%s ", root.data);
        printPreBranch(root.left);
        printPreBranch(root.right);
        System.out.println();*/
    //}
/*
    private void printPreBranch(Node node){
        if(node == null){
            return;
        }

        System.out.printf("%s ", node.data);
        if(node.left != null) {
            if (ExpTree.Utils.isConstant(node.left.data)) {
                System.out.printf("%s ", node.left.data);
            } else {
                printPreBranch(node.left);
            }
        }

        if(node.right != null) {
            if (ExpTree.Utils.isConstant(node.right.data)) {
                System.out.printf("%s ", node.right.data);
            } else {
                printPreBranch(node.right);
            }
        }
    }

    @Override
    public void postOrder() {

    }

    @Override
    public void inOrder() {

    }

    @Override
    public NodeTreeInterface getRoot() {
        return null;
    }

    final class Node implements NodeTreeInterface{
        private T data;
        private Node left,right;
        Node(T data){
            this.data = data;
            this.left = null;
            this.right = null;
        }

        @Override
        public Comparable getData() {
            return data;
        }

        @Override
        public NodeTreeInterface getLeft() {
            return left;
        }

        @Override
        public NodeTreeInterface getRight() {
            return right;
        }
    }
    */
}