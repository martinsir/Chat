import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Martin H on 20-02-2017.
 */
public class ChatClient extends Application{

    public static void main(String[] args) {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Views/Login.fxml"));
        primaryStage.setTitle("Chat chat");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}
