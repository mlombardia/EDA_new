import java.util.Iterator;
import java.util.LinkedList;

public class BST<T extends Comparable<? super T>> implements BSTreeInterface<T> {

    enum Traversal{BYLEVEL, PREORDER, POSORDER, INORDER}

    private NodeTreeInterface<T> root;
    private Traversal aTraversal = Traversal.BYLEVEL;

    @Override
    public void insert(T myData) {
        if (root == null){
            root = new Node<>(myData);
            return;
        }
        searchPlaceAndInsert(root, myData);
    }

    private void searchPlaceAndInsert(NodeTreeInterface<T> node, T elem){

        if(elem.compareTo(node.getData())<0){
            if (node.getLeft() == null){
                node.setLeft(new Node<>(elem));
                return;
            }
            searchPlaceAndInsert(node.getLeft(), elem);
        }else if(elem.compareTo(node.getData())>0){
            if (node.getRight() == null){
                node.setRight(new Node<>(elem));
                return;
            }
            searchPlaceAndInsert(node.getRight(), elem);
        }else{
            if(node.getRight() == null){
                node.setRight(new Node<>(elem));
            }else if(node.getLeft() == null){
                node.setLeft(new Node<>(elem));
            }
        }
    }

    @Override
    public boolean contains(T myData) {
        if (root.getData().equals(myData)){
            return true;
        }
        return (root.getRight() != null && checkBranch(root.getRight(), myData)) || (root.getLeft() != null && checkBranch(root.getLeft(), myData));
    }

    private boolean checkBranch(NodeTreeInterface<T> node, T myData){
        if (node.getData().equals(myData)){
            return true;
        }
        return (node.getRight() != null && checkBranch(node.getRight(), myData)) || (node.getLeft() != null && checkBranch(node.getLeft(), myData));
    }

    @Override
    public void delete(T myData) {
        if(myData == null)
            throw new RuntimeException("No data is provided");
        if(root!=null)
            root = root.delete(myData);
    }

    @Override
    public void preOrder() {
        System.out.printf("%s ", root.getData());

        if(root.getLeft() != null)
            printPreBranch(root.getLeft());
        if(root.getRight() != null)
            printPreBranch(root.getRight());

        System.out.println();
    }

    private void printPreBranch(NodeTreeInterface<T> node){
        if(node == null){
            return;
        }

        System.out.printf("%s ", node.getData());

        printPreBranch(node.getLeft());
        printPreBranch(node.getRight());
    }

    @Override
    public void postOrder() {
        if(root.getLeft() != null)
            printPostBranch(root.getLeft());
        if(root.getRight() != null)
            printPostBranch(root.getRight());

        System.out.printf("%s \n", root.getData());
    }

    private void printPostBranch(NodeTreeInterface<T> node){
        if(node == null){
            return;
        }

        printPostBranch(node.getLeft());
        printPostBranch(node.getRight());
        System.out.printf("%s ", node.getData());
    }

    @Override
    public void inOrder() {
        if(root.getLeft() != null)
            printInBranch(root.getLeft());

        System.out.printf("%s ", root.getData());

        if(root.getRight() != null)
            printInBranch(root.getRight());
        System.out.println();
    }

    private void printInBranch(NodeTreeInterface<T> node){
        if(node == null){
            return;
        }

        printInBranch(node.getLeft());

        System.out.printf("%s ", node.getData());

        printInBranch(node.getRight());
    }

    public void printByLevels(){
        LinkedList<NodeTreeInterface<T>> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()){
            NodeTreeInterface<T> aux = queue.removeFirst();

            if(aux != null){
                System.out.print(aux.getData() + " ");
                queue.add(aux.getLeft());
                queue.add(aux.getRight());
            }
        }
        System.out.println();
    }

    public boolean testAVL(){
        if (root == null)
            return true;
        return testAVL(root);
    }

    private boolean testAVL(NodeTreeInterface<T> node){
        if (node == null)
            return true;
        int aux1 = getAVLHeight(node.getLeft());
        int aux2 = getAVLHeight(node.getRight());
        int aux_resul = Math.abs(aux1-aux2);
        return (aux_resul <= 1) && testAVL(node.getLeft()) && testAVL(node.getRight());
    }

    private int getAVLHeight(NodeTreeInterface<T> node){
        if (node == null)
            return -1;
        int i = goAVLBranch(node.getLeft());
        int j = goAVLBranch(node.getRight());
        return 1 + Math.max(i,j);
    }

    private int goAVLBranch(NodeTreeInterface<T> node){
        if (node == null)
            return -1;
        return 1 + Math.max(goAVLBranch(node.getLeft()), goAVLBranch(node.getRight()));
    }

    public int getHeight(){
        return getHeight(root);
    }

    private int getHeight(NodeTreeInterface<T> node){
        if (node == null)
            return 0;
        int i = goBranch(node.getRight());
        int j = goBranch(node.getLeft());
        return Math.max(i, j);
    }

    private int goBranch(NodeTreeInterface<T> node){
        if((node == null)){
            return 0;
        }
        int aux1=0,aux2=0;
        if(node.getRight() != null)
            aux1 = goBranch(node.getRight());

        if(node.getLeft() != null)
            aux2 = goBranch(node.getLeft());
        return 1+Math.max(aux1, aux2);
    }

    @Override
    public NodeTreeInterface<T> getRoot() {
        return root;
    }

    public void inRange(T min, T max){
        if (max.compareTo(min)<0)
            return;
        System.out.print("[ ");
        if (root != null){
            goFurtherInBranch(root.getLeft(), min, max);
            if(isInRange(root.getData(),min,max)) {
                System.out.print(root.getData() + " ");
            }else{
                System.out.println("]");
                return;
            }
            goFurtherInBranch(root.getRight(), min, max);
            System.out.println("]");
        }
    }

    private void goFurtherInBranch(NodeTreeInterface<T> node, T min, T max) {
        if (node == null)
            return;
        goFurtherInBranch(node.getLeft(), min, max);
        if (isInRange(node.getData(), min, max)) {
            System.out.print(node.getData() + " ");
        }else{
            return;
        }
        goFurtherInBranch(node.getRight(), min, max);
    }

    private boolean isInRange(T data, T min, T max){
        return (data.compareTo(min)>=0 && data.compareTo(max)<=0);
    }

    public void setTraversal(Traversal traversal){
        aTraversal = traversal;
    }

    @Override
    public Iterator<T> iterator() {
        switch (aTraversal){
            case BYLEVEL:
                return new BSTTreeByLevelIterator();
            case PREORDER:
                return new BSTTreePreOrderIterator();
            case POSORDER:
                return new BSTTreePosOrderIterator();
            case INORDER:
                return new BSTTreeInOrderIterator();
        }
        return null;
    }

    private class BSTTreePreOrderIterator implements Iterator<T>{
        private LinkedList<NodeTreeInterface<T>> queue;

        public BSTTreePreOrderIterator(){
            queue = new LinkedList<>();
            if(root != null){
                queue.add(root);
                addBranchElems(root.getLeft());
                addBranchElems(root.getRight());
            }
        }

        private void addBranchElems(NodeTreeInterface<T> node){
            if (node == null)
                return;
            queue.add(node);
            addBranchElems(node.getLeft());
            addBranchElems(node.getRight());
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public T next() {
            NodeTreeInterface<T> aux = queue.removeFirst();
            return aux.getData();
        }
    }

    private class BSTTreePosOrderIterator implements Iterator<T>{
        private LinkedList<NodeTreeInterface<T>> queue;

        public BSTTreePosOrderIterator(){
            queue = new LinkedList<>();
            if(root != null){
                addBranchElems(root.getLeft());
                addBranchElems(root.getRight());
                queue.add(root);
            }
        }

        private void addBranchElems(NodeTreeInterface<T> node){
            if(node == null)
                return;
            addBranchElems(node.getLeft());
            addBranchElems(node.getRight());
            queue.add(node);
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public T next() {
            NodeTreeInterface<T> aux = queue.removeFirst();
            return aux.getData();
        }
    }

    private class BSTTreeInOrderIterator implements Iterator<T>{

        private LinkedList<NodeTreeInterface<T>> queue;

        public BSTTreeInOrderIterator(){
            queue = new LinkedList<>();
            if(root != null){
                addBranchElems(root.getLeft());
                queue.add(root);
                addBranchElems(root.getRight());
            }
        }

        private void addBranchElems(NodeTreeInterface<T> node){
            if (node == null){
                return;
            }
            addBranchElems(node.getLeft());
            queue.add(node);
            addBranchElems(node.getRight());
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public T next() {
            NodeTreeInterface<T> aux = queue.removeFirst();
            return aux.getData();
        }
    }

    private class BSTTreeByLevelIterator implements Iterator<T>{

        private LinkedList<NodeTreeInterface<T>> queue;

        public BSTTreeByLevelIterator(){
            queue = new LinkedList<>();

            if(root != null){
                queue.add(root);
            }
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public T next() {
            NodeTreeInterface<T> aux = queue.removeFirst();

            if (aux.getLeft() != null){
                queue.add(aux.getLeft());
            }
            if (aux.getRight() != null){
                queue.add(aux.getRight());
            }

            return aux.getData();
        }
    }

    private static class Node<T extends Comparable<? super T>> implements NodeTreeInterface<T>{

        private T data;
        private NodeTreeInterface<T> right;
        private NodeTreeInterface<T> left;

        public Node(T data){
            this.data = data;
            this.right = null;
            this.left = null;
        }

        @Override
        public T getData() {
            return this.data;
        }

        @Override
        public NodeTreeInterface<T> getLeft() {
            return this.left;
        }

        @Override
        public NodeTreeInterface<T> getRight() {
            return this.right;
        }

        @Override
        public void setRight(NodeTreeInterface<T> node) {
            this.right = node;
        }

        @Override
        public void setLeft(NodeTreeInterface<T> node) {
            this.left = node;
        }

        @Override
        public NodeTreeInterface<T> delete(T myData) {
            if(myData.compareTo(this.data)==0) {
                if (left == null) {
                    return right;
                }
                if (right == null) {
                    return left;
                }
                data = lexiAdjacent(left);
                left = left.delete(this.data);
            }

            if (myData.compareTo(this.data) < 0) {
                if (left != null) {
                    left = left.delete(myData);
                }
            }else {
                if (right != null){
                    right = right.delete(myData);
                }
            }
            return this;
        }

        private T lexiAdjacent(NodeTreeInterface<T> candidate){
            NodeTreeInterface<T> auxi = candidate;
            while (auxi.getRight() != null){
                auxi = auxi.getRight();
            }
            return auxi.getData();
        }
    }
}
