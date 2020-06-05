package sample;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcons;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Modal_window
{

    public void set_window_order() //Параметры окна ошибки при заполнении заказа
    {
        String er1 = "Ошибка заполнения заказа";
        String er2 = "Должны быть заполнены : Дата заказа, ФИО, Автомобиль и хотя бы один вид доработок ";
        form_setup_alert(er1,er2);
    }
    public void set_window_change()//Параметры окна ошибки при изменении поля заказа
    {
        String er1 = "Ошибка изменения поля";
        String er2 = "Выберите поле которое хотите изменить";
        form_setup_alert(er1,er2);
    }
    private void form_setup_alert(String er1,String er2) //Общие параметры окна ошибки
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText(er1);
        alert.initStyle(StageStyle.UTILITY);
        alert.setContentText(er2);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.showAndWait();

    }
    public void set_window_info()//Параметры окна информации о программе/разработчике
    {
        String s = new String("Информация");
        FontAwesomeIcon f = new FontAwesomeIcon();
        f.setIcon(FontAwesomeIcons.INFO_CIRCLE);
        Text text = new Text("Сведения о программе:\n" +
                "Название: База данных Тюнинг Ателье\n" +
                "Версия: 1.0\n" +
                "Дата релиза: 06.06.2020\n\n" +
                "Сведения о разработчике:\n" +
                "Лавров Роман Викторович\n" +
                "Студент 2-го курса\n" +
                "Энергетического факультета ЗабГУ\n" +
                "Группа ИВТ-18-1\n");
        Stage stage = new Stage();
        Scene scene = new Scene(window(stage,text,f,s,200,300),500,350);
        stage.setScene(scene);
        stage.showAndWait();
    }
    public void set_window_reference()//Параметры окна справки
    {
        FontAwesomeIcon f = new FontAwesomeIcon();
        f.setIcon(FontAwesomeIcons.QUESTION_CIRCLE);
        String s = new String("Справка");
        Text text = new Text("Краткая справка:\n" +
                "1) Чтобы изменить какое-либо поле,\n" +
                "сперва выберете его в выпадающем списке,\n" +
                "рядом с полем поиска.\n" +
                "2) Для сортировки нажмите на заголовок столбца.\n"+
                "3) Автосохранение производится каждые 5 минут,\n"+
                "Данные сохраняются в файл:\n"+
                "C:\\...\\Database\\Autosave.dbts\n"+
                "4) Горячие клавиши:\n" +
                "F1 - Вызвать cправку\n" +
                "F2 - Открыть файл\n" +
                "F3 - Сохранить файл\n" +
                "F4 - Перейти к разделу 'Новый заказ'\n" +
                "F5 - Перейти к разделу 'Текущие заказы'\n" +
                "F6 - Перейти к разделу 'Завершённые заказы'\n"+
                "F7 - Открыть сведения о программе/разработчике");
        Stage stage = new Stage();
        Scene scene = new Scene(window(stage,text,f,s,240,380),600,450);
        stage.setScene(scene);
        stage.showAndWait();

    }
    private Group window(Stage stage, Text text,FontAwesomeIcon f,String s,int x,int y)//Общие параметры окна для справки и информации
    {

        Text text_info = text;
        text_info.setLayoutX(100);
        text_info.setLayoutY(60);
        text_info.setFont(new Font("Arial Black",14));
        f.setLayoutX(45);
        f.setLayoutY(80);
        f.setScaleX(5);
        f.setScaleY(5);
        Button btn = new Button("OK");
        btn.setLayoutX(x);
        btn.setLayoutY(y);
        btn.setPrefWidth(100);
        btn.setPrefHeight(35);
        stage.setTitle(s);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        stage.setResizable(false);
        btn.setOnAction(ActionEvent->stage.close());
        Group group = new Group(text_info,f,btn);
        return group;
    }
}
