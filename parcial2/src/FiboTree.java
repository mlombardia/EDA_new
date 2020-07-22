public class FiboTree {
    private Node root;
    private int num;

    public Node getRoot() {
        return root;
    }

    public FiboTree(int n) {
        generateFibo(n);
    }

    private void generateFibo(int n){
        if (n > 0) {
            root = new Node();
            root.left = generateFiboTree(n-1);
            root.right = generateFiboTree(n-2);
            root.data = "*";
        }
    }

    private Node generateFiboTree(int n){
        if (n <= 0)
            return null;
        Node node = new Node();
        node.left = generateFiboTree(n-1);
        node.right = generateFiboTree(n-2);
        node.data = "*";
        return node;
    }

    private class Node implements NodeFiboInterface{
        private String data;
        private Node left;
        private Node right;

        @Override
        public String getData() {
            return data;
        }

        @Override
        public NodeFiboInterface getLeft() {
            return left;
        }

        @Override
        public NodeFiboInterface getRight() {
            return right;
        }
    }

}
