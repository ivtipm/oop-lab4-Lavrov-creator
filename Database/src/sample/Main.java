package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.FileNotFoundException;

import static javafx.fxml.FXMLLoader.load;

public class Main extends Application {
    private static Stage pStage;
    @Override
    public void start(Stage primaryStage) throws Exception
    {

        Parent root = load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("База данных Тюнинг-Ателье");
        primaryStage.setScene(new Scene(root, 1100, 650));
        root.setStyle("-fx-background-image: url('sample/Dutsun_280Z_ADV.1_438525.jpg')");// Установка фона программы
        pStage = primaryStage;
        primaryStage.setMinWidth(1120);
        primaryStage.setMinHeight(690);
        primaryStage.getIcons().add(new Image("file:icon.png"));// Установка иконки программы
        Controller controller = new Controller();
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> { // Событие закрытия формы
            controller.getCloseEventHandler();
            Platform.exit();
            System.exit(0);
        });

    }
    public static Stage getPrimaryStage() {
        return pStage;
    }

    private void setPrimaryStage(Stage pStage) {
        Main.pStage = pStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
