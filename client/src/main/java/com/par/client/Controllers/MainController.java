package com.par.client.Controllers;

import com.par.client.ApiWrapper;
import com.par.client.Protocol;
import com.par.shared.Classes.Messages;
import com.par.shared.Classes.User;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HorizontalDirection;
import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class MainController implements Initializable {

    Protocol protocol;
    User user;

    @FXML
    ComboBox comboBoxProtocol;

    @FXML
    TextFlow textFlow;

    @FXML
    TextField textField;

    @FXML
    ScrollPane scrollPane;

    Timer timer;

    ApiWrapper apiWrapper;


    @FXML
    void setProtocol() {
        protocol = (Protocol) comboBoxProtocol.getSelectionModel().getSelectedItem();
    }

    @FXML
    void sendMessage() throws Exception {
        if (textField.getText() != null && textField.getText().trim().length()!=0)
            apiWrapper.getApi(protocol).sendMessage(user, textField.getText());
        textField.setText(null);
    }

    public void onTransition(Object param) {
        HashMap<String, Object> hashMap = (HashMap<String, Object>) param;
        user = (User) hashMap.get("user");
        protocol = (Protocol) hashMap.get("protocol");
        apiWrapper = (ApiWrapper) hashMap.get("api");
        comboBoxProtocol.getSelectionModel().select(protocol);
        scrollPane.vvalueProperty().bind(textFlow.heightProperty());
        getMessages();
    }

    private void getMessages() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            List<Messages> messages;
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

                    @Override
                    public void run() {
                        try {
                            messages = apiWrapper.getApi(protocol).getMessages(user);
                            List nodes = messagesToNodes(messages);
                            addNodesToTextFlow(nodes);


                        } catch (Exception e) {
                            e.printStackTrace();
                            timer.cancel();
                            Platform.exit();
                        }
                    }

                    private List messagesToNodes(List<Messages> messages) {

                        List listNodes = new ArrayList();
                        if (messages.size() != 0) {
                            for (int i = 0; i < messages.size(); i++) {
                                Text userText = new Text(messages.get(i).getUser().getName() + ": ");
                                Text dateText = new Text(simpleDateFormat.format(messages.get(i).getDate().getTime()) + "\n");
                                Text msgText = new Text((messages.get(i).getMsg()));
                                userText.setFill(Color.GRAY);
                                listNodes.add(dateText);
                                listNodes.add(userText);
                                listNodes.add(msgText);

                            }
                            return listNodes;
                        } else
                            return listNodes;
                    }

                    private void addNodesToTextFlow(List nodes) {
                        if (nodes.size() != 0) {
                            if (textFlow.getChildren().size() != 0) {
                                Text text = new Text("\n");
                                nodes.add(0, text);
                                textFlow.getChildren().addAll(nodes);
                            } else {
                                textFlow.getChildren().addAll(nodes);
                            }
                        }
                    }
                    @PreDestroy
                    public void destory(){
                        Platform.exit();
                    }
                });

            }

        }, 200, 250);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBoxProtocol.getItems().addAll(Protocol.values());
    }

    @PreDestroy
    public void predestroy() throws Exception {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
        if(user!=null)
            apiWrapper.getApi(protocol).logOut(user);

    }


}
