import cn.hutool.core.io.resource.ResourceUtil;
import java.util.Objects;
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
        Scene scene = new Scene(root,1280,910);
        scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("/css/main.css")).toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
