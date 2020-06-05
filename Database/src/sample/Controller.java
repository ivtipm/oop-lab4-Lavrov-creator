package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.scene.input.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;


public class Controller {
    Timer timer = new Timer("Timer");

    public int count_orders = 1;// Количество заказов
    //Создание массивов для объектов таблицы
    private ObservableList<Tuning_studio> TuningData = FXCollections.observableArrayList();
    private ObservableList<Tuning_studio> TuningData_del = FXCollections.observableArrayList();
    @FXML
    private TableView<Tuning_studio> table = new TableView<>();// Инициализация таблицы текущих заказов
    @FXML
    private TableView<Tuning_studio> table_del = new TableView<>();// Инициализация таблицы завершённых заказов
    //Инициализация столбцов таблицы текущих заказов
    @FXML
    private TableColumn<Tuning_studio, String> idColumn_cur;
    @FXML
    private TableColumn<Tuning_studio, String> dateColumn_cur;
    @FXML
    private TableColumn<Tuning_studio, String> fioColumn_cur;
    @FXML
    private TableColumn<Tuning_studio, String> engine_modColumn_cur;
    @FXML
    private TableColumn<Tuning_studio, String> carColumn_cur;
    @FXML
    private TableColumn<Tuning_studio, String> susp_modColumn_cur;
    @FXML
    private TableColumn<Tuning_studio, String> body_modColumn_cur;
    @FXML
    //Инициализация столбцов таблицы завершённых заказов
    private TableColumn<Tuning_studio, String> idColumn_del;
    @FXML
    private TableColumn<Tuning_studio, String> dateColumn_del;
    @FXML
    private TableColumn<Tuning_studio, String> fioColumn_del;
    @FXML
    private TableColumn<Tuning_studio, String> engine_modColumn_del;
    @FXML
    private TableColumn<Tuning_studio, String> carColumn_del;
    @FXML
    private TableColumn<Tuning_studio, String> susp_modColumn_del;
    @FXML
    private TableColumn<Tuning_studio, String> body_modColumn_del;
    @FXML
    private GridPane current_orders;
    @FXML
    private GridPane new_order;
    @FXML
    private GridPane end_orders;
    @FXML
    private Pane pane_new_order;
    @FXML
    private Pane pane_current_order;
    @FXML
    private Pane pane_end_orders;
    @FXML
    private TextField Text_field_body_mod;
    @FXML
    private TextField Text_field_engine_mod;
    @FXML
    private TextField Text_field_car;
    @FXML
    private TextField Text_field_susp_mod;
    @FXML
    private TextField Text_field_name;
    @FXML
    private TextField Text_field_date;
    @FXML
    private TextField Text_field_surname;
    @FXML
    private TextField Text_field_patronymic;
    @FXML
    private Button Button_delete_order;
    @FXML
    private Button but_open;
    @FXML
    private Button but_save_how;
    @FXML
    private Button Button_end_order;
    @FXML
    private ComboBox<String> Combobox_order;
    @FXML
    private Button Button_change_order;
    @FXML
    private Pane Pane_change;
    @FXML
    private Pane Pane_stub;
    @FXML
    private TextField Text_field_change;
    @FXML
    private Label Label_change;
    @FXML
    private TextField search_field;
    @FXML
    private Button Button_reference;
    @FXML
    private Button Button_info;
    @FXML
    private Text Text_save;

    @FXML
    public void info()// Вызов окна с информацией о программе/разработчике
    {
        Modal_window c = new Modal_window();
        c.set_window_info();
    }
    @FXML
    public void reference()// Вызов окна справки
    {
        Modal_window c = new Modal_window();
        c.set_window_reference();
    }

    public String check_engine() // Проверка на пустоту поля "Доработки двигателя"
    {
        if (Text_field_engine_mod.getText().trim().length() == 0) {
            return "Нет";
        } else
            return Text_field_engine_mod.getText();
    }

    public String check_susp()// Проверка на пустоту поля "Доработки ходовой"
    {
        if (Text_field_susp_mod.getText().trim().length() == 0) {
            return "Нет";
        } else
            return Text_field_susp_mod.getText();
    }

    public String check_body()//// Проверка на пустоту поля "Доработки кузова"
    {
        if (Text_field_body_mod.getText().trim().length() == 0) {
            return "Нет";
        } else
            return Text_field_body_mod.getText();
    }

    public void prompts() //Установка всплывающих подсказок
    {
        but_open.setTooltip(new Tooltip("Открыть файл"));
        but_save_how.setTooltip(new Tooltip("Сохранить файл как..."));
        Button_end_order.setTooltip(new Tooltip("Завершить заказ"));
        Button_delete_order.setTooltip(new Tooltip("Удалить заказ"));
        Button_change_order.setTooltip(new Tooltip("Изменить выделенный заказ"));
        Button_info.setTooltip(new Tooltip("Сведения о программе/разработчике"));
        Button_reference.setTooltip(new Tooltip("Справка"));
        Text_field_date.setTooltip(new Tooltip("Формат даты ДД.ММ.ГГГГ"));
    }

    public void init_table() //Инициализация таблицы текущих заказов
    {
        idColumn_cur.setCellValueFactory(new PropertyValueFactory<Tuning_studio, String>("id"));
        dateColumn_cur.setCellValueFactory(new PropertyValueFactory<Tuning_studio, String>("date"));
        fioColumn_cur.setCellValueFactory(new PropertyValueFactory<Tuning_studio, String>("fio"));
        carColumn_cur.setCellValueFactory(new PropertyValueFactory<Tuning_studio, String>("car"));
        engine_modColumn_cur.setCellValueFactory(new PropertyValueFactory<Tuning_studio, String>("engine_mod"));
        body_modColumn_cur.setCellValueFactory(new PropertyValueFactory<Tuning_studio, String>("body_mod"));
        susp_modColumn_cur.setCellValueFactory(new PropertyValueFactory<Tuning_studio, String>("susp_mod"));
        table.setItems(TuningData);
    }

    public void init_table_del() //Инициализация таблицы завершённых заказов
    {
        idColumn_del.setCellValueFactory(new PropertyValueFactory<Tuning_studio, String>("id"));
        dateColumn_del.setCellValueFactory(new PropertyValueFactory<Tuning_studio, String>("date"));
        fioColumn_del.setCellValueFactory(new PropertyValueFactory<Tuning_studio, String>("fio"));
        carColumn_del.setCellValueFactory(new PropertyValueFactory<Tuning_studio, String>("car"));
        engine_modColumn_del.setCellValueFactory(new PropertyValueFactory<Tuning_studio, String>("engine_mod"));
        body_modColumn_del.setCellValueFactory(new PropertyValueFactory<Tuning_studio, String>("body_mod"));
        susp_modColumn_del.setCellValueFactory(new PropertyValueFactory<Tuning_studio, String>("susp_mod"));
        table_del.setItems(TuningData_del);
    }



    public void focused()//Снять выделение строки при нажатии на текстовое поле, и некоторые кнопки
    {
        table.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) {
                if(search_field.isFocused()||Combobox_order.isFocused()||Button_reference.isFocused()||Button_info.isFocused()){
                    table.getSelectionModel().clearSelection();
                    Pane_change.setVisible(false);
                    Pane_stub.setVisible(true);
                }

            }
        });
    }
    public void focused_buttons()// Делает кнопки недоступными и наоборот, при необходимости, в разделе "Текущие заказы"
    {
        TableView.TableViewSelectionModel<Tuning_studio> selectionModel = table.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<Tuning_studio>() {
            @Override
            public void changed(ObservableValue<? extends Tuning_studio> observableValue, Tuning_studio tuning_studio, Tuning_studio t1) {
                Button_end_order.setDisable(false);
                Button_change_order.setDisable(false);
                if (table.getSelectionModel().getSelectedItem() == null) {
                    Button_end_order.setDisable(true);
                    Button_change_order.setDisable(true);
                    Pane_change.setVisible(false);
                    Pane_stub.setVisible(true);
                }
            }
        });
    }
    public void focused_buttons_del()// Делает кнопки недоступными и наоборот, при необходимости, в разделе "Завершённые заказы"
    {
        TableView.TableViewSelectionModel<Tuning_studio> selectionModel_del = table_del.getSelectionModel();
        selectionModel_del.selectedItemProperty().addListener(new ChangeListener<Tuning_studio>() {
            @Override
            public void changed(ObservableValue<? extends Tuning_studio> observableValue, Tuning_studio tuning_studio, Tuning_studio t1) {
                Button_delete_order.setDisable(false);
                if (table_del.getSelectionModel().getSelectedItem() == null) {
                    Button_delete_order.setDisable(true);
                }
            }
        });
    }
    @FXML
    private void initialize()//Инициализация формы
    {
        Combobox_order.getItems().addAll(" ", "Дата", "ФИО", "Автомобиль", "Доработки двигателя", "Доработки ходовой", "Доработки кузова");
        prompts();
        init_table();
        init_table_del();
        focused();
        focused_buttons();
        focused_buttons_del();
        search_order();
        stop_timer();

    }
    @FXML
    public void change_order() //Изменить поле заказа
    {

        if ((Combobox_order.getValue() == null) || (Combobox_order.getValue() == " ")) {

            Modal_window c = new Modal_window();
            c.set_window_change();
        } else {
            Pane_change.setVisible(true);
            Pane_stub.setVisible(false);
        }
        TableView.TableViewSelectionModel<Tuning_studio> selectionModel = table.getSelectionModel();
        switch (Combobox_order.getValue()) {
            case "Дата":
                Label_change.setText(Combobox_order.getValue());
                Text_field_change.setText(selectionModel.getSelectedItem().getDate());
                break;
            case "ФИО":
                Label_change.setText(Combobox_order.getValue());
                Text_field_change.setText(selectionModel.getSelectedItem().getFio());
                break;
            case "Автомобиль":
                Label_change.setText(Combobox_order.getValue());
                Text_field_change.setText(selectionModel.getSelectedItem().getCar());
                break;
            case "Доработки двигателя":
                Label_change.setText(Combobox_order.getValue());
                Text_field_change.setText(selectionModel.getSelectedItem().getEngine_mod());
                break;
            case "Доработки ходовой":
                Label_change.setText(Combobox_order.getValue());
                Text_field_change.setText(selectionModel.getSelectedItem().getSusp_mod());
                break;
            case "Доработки кузова":
                Label_change.setText(Combobox_order.getValue());
                Text_field_change.setText(selectionModel.getSelectedItem().getBody_mod());
                break;
        }


    }
    @FXML
    public void accept_change() //Принять изменение поля заказа
    {
        TableView.TableViewSelectionModel<Tuning_studio> selectionModel = table.getSelectionModel();
        String num = new String();
        num = selectionModel.getSelectedItem().getId();
        int row = 0;
        switch (Combobox_order.getValue()) {
            case "Дата":
                Label_change.setText(Combobox_order.getValue());
                TuningData.add(new Tuning_studio(num, Text_field_change.getText(), selectionModel.getSelectedItem().fio, selectionModel.getSelectedItem().car, selectionModel.getSelectedItem().engine_mod, selectionModel.getSelectedItem().susp_mod, selectionModel.getSelectedItem().body_mod));
                row = table.getSelectionModel().getSelectedIndex();
                TuningData.remove(row);
                table.getItems().remove(row);
                break;
            case "ФИО":
                Label_change.setText(Combobox_order.getValue());
                TuningData.add(new Tuning_studio(num, selectionModel.getSelectedItem().date, Text_field_change.getText(), selectionModel.getSelectedItem().car, selectionModel.getSelectedItem().engine_mod, selectionModel.getSelectedItem().susp_mod, selectionModel.getSelectedItem().body_mod));
                row = table.getSelectionModel().getSelectedIndex();
                TuningData.remove(row);
                table.getItems().remove(row);
                break;
            case "Автомобиль":
                Label_change.setText(Combobox_order.getValue());
                TuningData.add(new Tuning_studio(num, selectionModel.getSelectedItem().date, selectionModel.getSelectedItem().fio, Text_field_change.getText(), selectionModel.getSelectedItem().engine_mod, selectionModel.getSelectedItem().susp_mod, selectionModel.getSelectedItem().body_mod));
                row = table.getSelectionModel().getSelectedIndex();
                TuningData.remove(row);
                table.getItems().remove(row);
                break;
            case "Доработки двигателя":
                Label_change.setText(Combobox_order.getValue());
                if( Text_field_change.getText().equals(""))
                {
                    TuningData.add(new Tuning_studio(num, selectionModel.getSelectedItem().date, selectionModel.getSelectedItem().fio, selectionModel.getSelectedItem().car, "Нет", selectionModel.getSelectedItem().susp_mod, selectionModel.getSelectedItem().body_mod));

                }else
                    TuningData.add(new Tuning_studio(num, selectionModel.getSelectedItem().date, selectionModel.getSelectedItem().fio, selectionModel.getSelectedItem().car, Text_field_change.getText(), selectionModel.getSelectedItem().susp_mod, selectionModel.getSelectedItem().body_mod));
                row = table.getSelectionModel().getSelectedIndex();
                TuningData.remove(row);
                table.getItems().remove(row);
                break;
            case "Доработки ходовой":
                Label_change.setText(Combobox_order.getValue());
                if( Text_field_change.getText().equals(""))
                {
                    TuningData.add(new Tuning_studio(num, selectionModel.getSelectedItem().date, selectionModel.getSelectedItem().fio, selectionModel.getSelectedItem().car, selectionModel.getSelectedItem().engine_mod, "Нет", selectionModel.getSelectedItem().body_mod));

                }else
                    TuningData.add(new Tuning_studio(num, selectionModel.getSelectedItem().date, selectionModel.getSelectedItem().fio, selectionModel.getSelectedItem().car, selectionModel.getSelectedItem().engine_mod, Text_field_change.getText(), selectionModel.getSelectedItem().body_mod));
                row = table.getSelectionModel().getSelectedIndex();
                TuningData.remove(row);
                table.getItems().remove(row);
                break;
            case "Доработки кузова":
                Label_change.setText(Combobox_order.getValue());
                if( Text_field_change.getText().equals(""))
                {
                    TuningData.add(new Tuning_studio(num, selectionModel.getSelectedItem().date, selectionModel.getSelectedItem().fio, selectionModel.getSelectedItem().car, selectionModel.getSelectedItem().engine_mod, selectionModel.getSelectedItem().susp_mod, "Нет"));

                }else
                    TuningData.add(new Tuning_studio(num, selectionModel.getSelectedItem().date, selectionModel.getSelectedItem().fio, selectionModel.getSelectedItem().car, selectionModel.getSelectedItem().engine_mod, selectionModel.getSelectedItem().susp_mod, Text_field_change.getText()));
                row = table.getSelectionModel().getSelectedIndex();
                TuningData.remove(row);
                table.getItems().remove(row);
                break;


        }

    }

    @FXML
    public void new_order() //Перейти к разделу "Новый заказ"
    {
        table.getSelectionModel().clearSelection();
        Button_end_order.setDisable(true);
        Button_change_order.setDisable(true);
        new_order.toFront();
        new_order.setVisible(true);
        current_orders.setVisible(false);
        end_orders.setVisible(false);
        pane_new_order.setVisible(true);
        pane_current_order.setVisible(false);
        pane_end_orders.setVisible(false);
    }

    @FXML
    public void current_orders() //Перейти к разделу "Текущие заказы"
    {
        table.getSelectionModel().clearSelection();
        current_orders.toFront();
        current_orders.setVisible(true);
        end_orders.setVisible(false);
        new_order.setVisible(false);
        pane_new_order.setVisible(false);
        pane_current_order.setVisible(true);
        pane_end_orders.setVisible(false);
    }

    @FXML
    public void end_orders() //Перейти к разделу "Завершённые заказы"
    {
        table.getSelectionModel().clearSelection();
        Button_end_order.setDisable(true);
        Button_change_order.setDisable(true);
        end_orders.toFront();
        end_orders.setVisible(true);
        current_orders.setVisible(false);
        new_order.setVisible(false);
        pane_new_order.setVisible(false);
        pane_current_order.setVisible(false);
        pane_end_orders.setVisible(true);
    }

    @FXML
    public void add_order() throws IOException //Добавить заказ
    {
        String fio = new String();
        if ((Text_field_date.getText().trim().length() == 0) || (Text_field_surname.getText().trim().length() == 0) || (Text_field_name.getText().trim().length() == 0) || (Text_field_car.getText().trim().length() == 0) || ((Text_field_engine_mod.getText().trim().length() == 0) && (Text_field_susp_mod.getText().trim().length() == 0) && (Text_field_body_mod.getText().trim().length() == 0))) {
            Modal_window c = new Modal_window();
            c.set_window_order();
        } else {
            fio = Text_field_surname.getText() + " " + Text_field_name.getText() + " " + Text_field_patronymic.getText();
            TuningData.add(new Tuning_studio(Integer.toString(count_orders), Text_field_date.getText(), fio, Text_field_car.getText(), check_engine(), check_susp(), check_body()));
            count_orders++;
            Text_field_surname.clear();
            Text_field_patronymic.clear();
            Text_field_name.clear();
            Text_field_car.clear();
            Text_field_date.clear();
            Text_field_body_mod.clear();
            Text_field_engine_mod.clear();
            Text_field_susp_mod.clear();
            current_orders();
        }

    }


    @FXML
    public void end_order() //Завершить заказ(переместить его в раздел "Завершённые заказы")
    {
        Tuning_studio selectedTuning = table.getSelectionModel().getSelectedItem();
        TuningData_del.add(new Tuning_studio(selectedTuning.getId(), selectedTuning.date, selectedTuning.fio, selectedTuning.car, selectedTuning.engine_mod, selectedTuning.susp_mod, selectedTuning.body_mod));
        int row = table.getSelectionModel().getSelectedIndex();
        TuningData.remove(row);
        table.getItems().remove(row);
        table.getSelectionModel().clearSelection();


    }

    @FXML
    public void delete_order() //Удалить заказ
    {
        Button_delete_order.setDisable(true);
        Tuning_studio selectedTuning = (Tuning_studio) table_del.getSelectionModel().getSelectedItem();
        int row = table_del.getSelectionModel().getSelectedIndex();
        TuningData_del.remove(row);
        table_del.getItems().remove(row);
    }

    public void save_file() throws IOException//Сохранить файл
     {
         File f = new File("Autosave.dbts");
         if(!f.exists()){
             f.createNewFile();
         }
         PrintWriter out = new PrintWriter(f.getAbsoluteFile());
         out.print("Заказы\n");
            for(int i = 0; i < count_orders-1; i++){
             Tuning_studio t =  TuningData.get(i);
             out.print(t.getId());
             out.print("*");
             out.print(t.getDate());
             out.print("*");
             out.print(t.getFio());
             out.print("*");
             out.print(t.getCar());
             out.print("*");
             out.print(t.getEngine_mod());
             out.print("*");
             out.print(t.getSusp_mod());
             out.print("*");
             out.print(t.getBody_mod());
             out.print("\n");
         }
         out.close();
     }
    @FXML
    private void hndlOpenFile() throws FileNotFoundException//Открыть файл
    {

        FileChooser fileChooser = new FileChooser();//Класс работы с диалогом выборки и сохранения
        fileChooser.setTitle("Открыть файл");//Заголовок диалога
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Data Base Tuning Studio (*.dbts)", "*.dbts");//Расширение
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(Main.getPrimaryStage());//Указываем текущую сцену CodeNote.mainStage
        int k = 0;
        int j = 0;
        int count_orders_file = 0;
        String Id = new String();
        String date = new String();
        String fio = new String();
        String car = new String();
        String eng_mod = new String();
        String susp_mod = new String();
        String body_mod = new String();
        String s = new String();
        try {
            FileReader f = new FileReader(file);
            BufferedReader reader = new BufferedReader(f);
            String line;
            if (file != null) {
                while ((line = reader.readLine()) != null) {
                    char[] res = line.toCharArray();
                    for (int i = 0; i < res.length; i++) {
                        if ((res[i] == '*') || (res[i] == '\n')) {
                            j++;
                            k = k + j;
                        } else {
                            s = s + res[i];
                        }
                        switch (k) {
                            case 1:
                                Id = s;
                                s = "";
                                break;
                            case 2:
                                date = s;
                                s = "";
                                break;
                            case 3:
                                fio = s;
                                s = "";
                                break;
                            case 4:
                                car = s;
                                s = "";
                                break;
                            case 5:
                                eng_mod = s;
                                s = "";
                                break;
                            case 6:
                                susp_mod = s;
                                s = "";
                                break;
                        }
                        if (i == (res.length - 1) && (!s.equals("Заказы") && (!s.equals("Детали")))) {
                            body_mod = s;
                            s = "";
                        }
                        k = 0;
                    }
                    j = 0;
                    if (s.equals("Заказы")) {
                        s = "";
                    } else {

                        int id2 = 0;
                        id2 = Integer.parseInt(Id);
                        TuningData.add(new Tuning_studio(Integer.toString(id2+count_orders-1), date, fio, car, eng_mod, susp_mod, body_mod));
                        count_orders_file++;
                    }
                }

            }

        } catch (IOException ex) {
            System.out.println("IOException");
        }
        current_orders();
        count_orders = count_orders + count_orders_file;
    }

    @FXML
    private void hndlSaveFile() throws IOException //Сохранить файл как...
    {
        FileChooser fileChooser = new FileChooser();//Класс работы с диалогом выборки и сохранения
        fileChooser.setTitle("Сохранить файл");//Заголовок диалога
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Data Base Tuning Studio (*.dbts)", "*.dbts");//Расширение
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(Main.getPrimaryStage());//Указываем текущую сцену CodeNote.mainStage
        if (file != null) {
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());
            out.print("Заказы\n");
            for (int i = 0; i < count_orders - 1; i++) {
                //int i=0;
                Tuning_studio t = TuningData.get(i);
                out.print(t.getId());
                out.print("*");
                out.print(t.getDate());
                out.print("*");
                out.print(t.getFio());
                out.print("*");
                out.print(t.getCar());
                out.print("*");
                out.print(t.getEngine_mod());
                out.print("*");
                out.print(t.getSusp_mod());
                out.print("*");
                out.print(t.getBody_mod());
                out.print("\n");

            }
            out.close();
        }
    }

    public void search_order() //Поиск по всем полям
    {

        FilteredList<Tuning_studio> filteredData = new FilteredList<>(TuningData, b -> true);
        search_field.setOnKeyPressed(e -> {
            search_field.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(Tuning_studio -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (Tuning_studio.getId().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else if (Tuning_studio.getDate().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else if (Tuning_studio.getFio().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else if (Tuning_studio.getCar().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else if (Tuning_studio.getEngine_mod().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else if (Tuning_studio.getBody_mod().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else if (Tuning_studio.getSusp_mod().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else
                        return false;
                });
            });
        });
        SortedList<Tuning_studio> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
    }

    @FXML
    private void handleKeyPressed(KeyEvent ke)//Настройка горячих клавиш
    {
        switch (ke.getCode().getName()) {
            case "F1":
                reference();
                break;
            case "F2":
                try {
                    hndlOpenFile();
                } catch (FileNotFoundException f) {
                }
                break;
            case "F3":
                try {
                    hndlSaveFile();
                }catch (IOException e){}
                break;
            case "F4":
                new_order();
                break;
            case "F5":
                current_orders();
                break;
            case "F6":
                end_orders();
                break;
            case "F7":
                info();
                break;

        }

    }

    //Настройка таймера, для автосохранения
    public void stop_timer()
    {
        TimerTask repeatedTask = new TimerTask() {
            public void run() {
                try {
                    save_file();
                } catch (IOException e){}
                String s = new String("Время последнего сохранения: " + new Date().toString());
                Text_save.setText(s);
            }
        };
        long delay  = 300000L;
        long period = 300000L;
        timer.scheduleAtFixedRate(repeatedTask, delay, period);

    }
    private javafx.event.EventHandler<WindowEvent> closeEventHandler = new javafx.event.EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent event) {
           timer.cancel();
        }
    };

    public javafx.event.EventHandler<WindowEvent> getCloseEventHandler(){
        return closeEventHandler;
    }
}

