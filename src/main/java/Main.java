import cn.hutool.core.io.resource.ResourceUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(ResourceUtil.getResource("fxml/Main.fxml"));
        primaryStage.setTitle("APT");
        primaryStage.setScene(new Scene(root, 1280, 900));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
