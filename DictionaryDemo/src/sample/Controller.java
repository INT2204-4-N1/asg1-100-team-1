package sample;

import Dictionary.Dic;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

    Dic dic = new Dic();
    @FXML
    WebView webView;
    @FXML
    TextField getText;
    public void Submit (ActionEvent e){
        String text = getText.getText();
        //text = text.toLowerCase();
        webView.getEngine().loadContent(dic.Data.get(text));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dic.readData();
        getText.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if(e.getCode()== KeyCode.ENTER){
                    String text = getText.getText();
                    //text = text.toLowerCase();
                    webView.getEngine().loadContent(dic.Data.get(text));
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
