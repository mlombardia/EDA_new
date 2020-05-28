public interface NodeTreeInterface<T extends Comparable<? super T>> {

	T getData();

	NodeTreeInterface<T> getLeft();

	NodeTreeInterface<T> getRight();

	void setRight(NodeTreeInterface<T> node);

	void setLeft(NodeTreeInterface<T> node);

	NodeTreeInterface delete(T myData);
}