package sample;

import Dictionary.Dic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    Dic dic = new Dic();
    @FXML
    WebView webView;
    @FXML
    TextField Text;
    public void Submit (ActionEvent e){
        String text = Text.getText();
        text = text.toLowerCase();
        webView.getEngine().loadContent(dic.Data.get(text));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dic.readData();
    }
}
