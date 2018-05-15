package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
    public Stage primarySTAGE;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/sample.fxml")); //загрузчик FXML файла, вызывается статичным методом Load
        primaryStage.setTitle("LastCast"); //Title
        primaryStage.setMinHeight(275); //минимальная высота окна
        primaryStage.setMinWidth(450); //минимальная ширина окна
        Scene scene =  new Scene(root, 581, 503); //создаем объект Scene
        scene.getStylesheets().add(0, "styles/main.css"); //добавляем css файл
        primaryStage.setScene(scene);
        primaryStage.show();

        primarySTAGE = primaryStage;


    }

    /**методы решения задач о максимальном потоке*/
    public static void main(String[] args) {
        launch(args);
    }
}