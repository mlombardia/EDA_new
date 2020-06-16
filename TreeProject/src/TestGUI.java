/*import controller.GraphicsTree;
import core.BST;*/
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

public class TestGUI extends Application {

	public static void main(String[] args) {
		// GUI
		launch(args);
	}

	public void start(Stage stage) {
		stage.setTitle("Drawing the BST");
		StackPane root = new StackPane();
		Scene scene = new Scene(root, 500, 500);

        BST<Integer> myTree = createModel();
		System.out.println(myTree.getHeight());
		System.out.println(myTree.contains(50));
		GraphicsTree<Integer> c = new GraphicsTree<>(myTree);


		c.widthProperty().bind(scene.widthProperty());
		c.heightProperty().bind(scene.heightProperty());
		root.getChildren().add(c);
		stage.setScene(scene);
		stage.show();
		//myTree.preOrder();
		//myTree.postOrder();
		//myTree.inOrder();
		myTree.printByLevels();
		//myTree.setTraversal(BST.Traversal.INORDER);
		for (Integer data : myTree){
			System.out.print(data + " ");
		}
		System.out.println();
		myTree.inRange(0,100);
		System.out.println(myTree.testAVL());
	}

	private BST<Integer> createModel() {
		BST<Integer> myTree = new BST<>();

		myTree.insert(50);
		myTree.insert(30);
		myTree.insert(70);
		myTree.insert(40);
		myTree.insert(10);
		myTree.insert(80);
		myTree.insert(60);



		/*myTree.insert(60);
		myTree.insert(50);
		myTree.insert(80);
		myTree.insert(30);
		myTree.insert(70);
		myTree.insert(40);
		myTree.insert(20);*/

		/*myTree.insert(60);
		myTree.insert(80);
		myTree.insert(40);
		myTree.insert(30);
		myTree.insert(70);
		myTree.insert(50);
		myTree.insert(20);*/


		/*myTree.insert(50);
		myTree.insert(60);
		myTree.insert(80);
		myTree.insert(20);
		myTree.insert(70);
		myTree.insert(40);
		myTree.insert(44);
		myTree.insert(10);
		myTree.insert(40);*/


		/*myTree.insert(new Person(50, "Ana"));
		myTree.insert(new Person(60, "Juan"));
		myTree.insert(new Person(80, "Sergio"));
		myTree.insert(new Person(20, "Lila"));
		myTree.insert(new Person(77, "Ana"));*/
		

		return myTree;
	}



}