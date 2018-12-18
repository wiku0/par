package com.par.client.Controllers;

import com.par.client.ApiWrapper;
import com.par.client.Protocol;
import com.par.client.config.SceneManager;
import com.par.shared.Classes.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

@Component
public class LoginController implements Initializable {


    @Autowired
    SceneManager sceneManager;

    @Autowired
    MainControllerView mainControllerView;

    @FXML
    TextField loginTextField;

    @FXML
    ComboBox comboBoxProtocol;

    Protocol protocol;

    ApiWrapper apiWrapper;
    User user;

    @FXML
    void loginButton()  {
        try {
            user = apiWrapper.getApi(protocol).newUser(loginTextField.getText());
            HashMap<String,Object> params = new HashMap<>();
            params.put("user",user);
            params.put("protocol",protocol);
            params.put("api",apiWrapper);
            sceneManager.showScene(mainControllerView,params);
        } catch (Exception e) {
            HBox hBox = new HBox();
            Label label = new Label(e.getMessage());
            label.setWrapText(true);
            hBox.getChildren().add(label);
            hBox.setPadding(new Insets(10, 0, 0, 10));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Error occurred!");
            primaryStage.setResizable(false);
            primaryStage.setScene(new Scene(hBox, 200, 100));
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.show();
        }
    }

    @FXML
    void setProtocol() {
        protocol = (Protocol) comboBoxProtocol.getSelectionModel().getSelectedItem();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBoxProtocol.getItems().addAll(Protocol.values());
        comboBoxProtocol.getSelectionModel().select(Protocol.HESSIAN);
        protocol = (Protocol) comboBoxProtocol.getSelectionModel().getSelectedItem();
        try {
            apiWrapper = new ApiWrapper();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


}
