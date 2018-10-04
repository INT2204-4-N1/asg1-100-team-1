package sample;

import Dictionary.Dictionary;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.util.Duration;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private int hour;
    private int minute;
    private int second;
    private int year;
    private int month;
    private int day;
    @FXML
    private Label ClockDisplay;
    @FXML
    private Label CalendarDisplay;
    @FXML
    private Button closeButton;
    @FXML
    ListView listView;

    Dictionary dictionary = new Dictionary();
    @FXML
    WebView webView;
    @FXML
    TextField search;

    //Bắt sự kiện cho nút search
    public void Submit (ActionEvent e){
        String text = search.getText();
        //text = text.toLowerCase();
        webView.getEngine().loadContent(dictionary.Data.get(text));
    }

    //hàm hiện từ lên listview và gợi ý từ tìm kiếm
    @FXML public void searchWord(){
        ObservableList<String> listWord = FXCollections.observableArrayList(dictionary.Word);
        FilteredList<String> filteredData = new FilteredList<>(listWord, s -> true);
        listView.setItems(filteredData);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(s ->{
                if(newValue ==null|| newValue.isEmpty()){
                    return true;
                }
                String tolower= newValue.toLowerCase();
                if(s.toLowerCase().startsWith(tolower)){
                    return true;
                }
                return false;
            });
            listView.setItems(filteredData);
        });
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //đọc dữ liệu
        dictionary.readData();

        //hiện data word lên listview
        searchWord();

        //bắt sự kiện cho chuột khi nhấn vào từ trong listword
        listView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.PRIMARY){

                    String text = (String) listView.getSelectionModel().getSelectedItem();
                    webView.getEngine().loadContent(dictionary.Data.get(text));
                }
            }
        });

        // bắt sự kiện tìm kiếm nhấn Enter cho textfield
        search.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if(e.getCode()== KeyCode.ENTER){
                    String text = search.getText();
                    //text = text.toLowerCase();
                    webView.getEngine().loadContent(dictionary.Data.get(text));
                }
            }
        });

        //TODO : Set clock cho Label Clock
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, (ActionEvent e) -> {
            second = LocalDateTime.now().getSecond();
            minute = LocalDateTime.now().getMinute();
            hour = LocalDateTime.now().getHour();
            if (hour >= 0 && hour <= 12)
                ClockDisplay.setText(String.valueOf(hour) + ":" + String.valueOf(minute) + ":" + String.valueOf(second) + " AM");
            else
                ClockDisplay.setText(String.valueOf(hour) + ":" + String.valueOf(minute) + ":" + String.valueOf(second) + " PM");
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
        //Kết thúc Set clock
        //TODO: Set calendar cho Label CalendarDisplay

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DATE);
        CalendarDisplay.setText(String.valueOf(day) + "/" + String.valueOf(month+1) + "/" + String.valueOf(year)+" ICT");
        //Kết thúc Set Calendar
    }
}
