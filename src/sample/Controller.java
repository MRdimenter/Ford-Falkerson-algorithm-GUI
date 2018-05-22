package sample;

import Algoritms.MaxFlow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class Controller {


    MaxFlow maxFlow = new MaxFlow(); //создание объекта MaxFlow

    @FXML
    private Button addGraph; //запуск
    @FXML
    private TextArea textArea; //текстовое окно
    @FXML
    private Button saveTXT; //вывод кнопка
    @FXML
    private Label getOutputText; //вывод текста
    @FXML
    private VBox Vbox;
    @FXML
    private Spinner spinnerCout = new Spinner(); //Число вершин
    @FXML
    private TextField netINPUT; //исток сети
    @FXML
    private TextField netOUTPUT; //сток сети
    @FXML
    private Button editWork;

    @FXML
    private void initialize() {
        // Value factory.
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 15, 3);
        spinnerCout.setValueFactory(valueFactory);
    }


    /**
     * Getters
     */
    public Spinner getSpinnerCout() {
        return spinnerCout;
    }

    public Button getAddGraph() {
        return addGraph;
    }

    public Button getOutput() {
        return saveTXT;
    }

    public Label getGetOutputText() {
        return getOutputText;
    }

    public VBox getVbox() {
        return Vbox;
    }


    /**
     * Один метод для отслеживания нажатия всех кнопок
     */
    public void showDialog(ActionEvent actionEvent) {

        Object source = actionEvent.getSource();

        //если нажата не кнопка, выходим из метода
        if (!(source instanceof Button)) { //если переменная source не содержит тип Button
            return;                       //выходим из метода
        }

        Button clickedButton = (Button) source; //нисходящее приведение

        switch (clickedButton.getId()) {
            case "addTxtfile": {

                textArea.setText(maxFlow.openFiletxt()); //открытие файла в текстовом окне
            }

            case "addGraph": {

                try {
                    getOutputText.setText("Output: " + String.valueOf(maxFlow.GETfordFalcerson((Integer) spinnerCout.getValue(), textArea.getText(), netINPUT.getText(), netOUTPUT.getText())));
                } catch (NumberFormatException e) {
                    getOutputText.setText("Output: Некоректный ввод данных");
                } catch (ArrayIndexOutOfBoundsException e) {
                    getOutputText.setText("Output: Выход за пределы массива");
                }
                break;
            }
            case "saveTXT": {
                maxFlow.saveFiletxt();
                break;
            }

            case "editWork": {
                System.out.println("Работает");
                try {
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("../fxml/edit.fxml"));
                    stage.setTitle("Как это работает?!");
                    stage.setMinHeight(359);
                    stage.setMinWidth(505);
                    stage.getIcons().add(new Image("Icon/icon.png"));
                    stage.setResizable(false); //нельзя растягивать окно
                    stage.setScene(new Scene(root));
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
