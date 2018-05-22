package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.stage.Stage;



public class Main extends Application {
    public Stage primarySTAGE;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/sample.fxml")); //загрузчик FXML файла, вызывается статичным методом Load
        primaryStage.setTitle("LastCast"); //Title
        primaryStage.setMinHeight(275); //минимальная высота окна
        primaryStage.setMinWidth(450); //минимальная ширина окна
        primaryStage.setResizable(false); //окно не растягивается
        primaryStage.getIcons().add(new Image("Icon/icon.png"));
        Scene scene =  new Scene(root, 581, 503); //создаем объект Scene
        scene.getStylesheets().add(0, "styles/main.css"); //добавляем css файл
        primaryStage.setScene(scene);
        primaryStage.show();

        primarySTAGE = primaryStage;


    }

    /**Ford-Falkerson-algorithm*/
    public static void main(String[] args) {
        launch(args);
    }
}