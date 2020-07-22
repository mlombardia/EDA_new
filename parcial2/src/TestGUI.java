import controller.GraphicsTree;
import core.BST;
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

        FiboTree myTree = createModel();
		GraphicsTree<Integer> c = new GraphicsTree<>(myTree);


		c.widthProperty().bind(scene.widthProperty());
		c.heightProperty().bind(scene.heightProperty());
		root.getChildren().add(c);
		stage.setScene(scene);
		stage.show();
		//myTree.preOrder();

	}

	private FiboTree createModel() {
		FiboTree myTree = new FiboTree();
		return myTree;
	}



}