package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.security.spec.ECField;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.concurrent.*;


public class Controller implements Initializable {

    @FXML
    BorderPane BPane;

    @FXML
    FlowPane FPane;

    @FXML
    ImageView logo;

    @FXML
    Button btn1, btn2, btn3, btn4, ExtBtn;

    @FXML
    TextField name;

    @FXML
    Label dialog;


    int counter = 0;
    String UsrName;
    String CharacterType;
    String CharacterName;
    int BtnPressed = 0;
    int chapter = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FPane.getChildren().remove(btn2);
        FPane.getChildren().remove(btn3);
        FPane.getChildren().remove(btn4);
        FPane.getChildren().remove(name);
        btn1.setText("Start Game");
        ExtBtn.setText("Exit Game");

    }

    public void HandleBtn(ActionEvent e) {
        if (chapter == 0) {
            String intro = "Hello Adventurer, Welcome to Dragon's Vs Warrior's";
            if (e.getSource() == ExtBtn) {
                Stage stage = (Stage) ExtBtn.getScene().getWindow();
                stage.close();
            } else if (e.getSource() == btn1) {
                if (BtnPressed == 0) {
                    TypeWriter(intro);
                    Run();
                    name.setOnKeyPressed(new EventHandler<KeyEvent>() {
                        @Override
                        public void handle(KeyEvent keyEvent) {
                            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                                UsrName = name.getText();
                                String intro3 = "Thanks " + UsrName + ", Please select your character.";
                                TypeWriter(intro3);
                                counter = 1;
                                Run();
                            }
                        }
                    });
                    BtnPressed = 1;
                } else if (BtnPressed == 1) {
                    CharacterType = btn1.getText();
                    counter = 2;
                    Run();
                }
            } else if (e.getSource() == btn2) {
                CharacterType = btn2.getText();
                counter = 2;
                Run();
            } else if (e.getSource() == btn3) {
                CharacterType = btn3.getText();
                counter = 2;
                Run();
            } else if (e.getSource() == btn4) {
                CharacterType = btn4.getText();
                counter = 2;
                Run();
            }
        } else if (chapter == 1) {
            counter = 3;
            dialog.setText("Hello");
        }

    }



    public void Run() {
        if (counter == 0) {
            String intro2 = "Please enter your name below.";
            Timeline timer = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    TypeWriter(intro2);
                    System.out.println(counter);
                    Timeline timer = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            FPane.getChildren().remove(btn1);
                            btn1.setText("Confirm");
                            FPane.getChildren().remove(ExtBtn);
                            FPane.getChildren().add(name);
                            FPane.getChildren().add(btn1);
                        }
                    }));
                    timer.play();
                }
            }));
            timer.play();
        } else if (counter == 1) {
            Timeline timer = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    FPane.getChildren().remove(name);
                    FPane.getChildren().remove(btn1);
                    btn1.setText("Warrior");
                    btn2.setText("Scout");
                    btn3.setText("Archer");
                    btn4.setText("Mage");
                    FPane.getChildren().add(btn1);
                    FPane.getChildren().add(btn2);
                    FPane.getChildren().add(btn3);
                    FPane.getChildren().add(btn4);
                }
            }));
            timer.play();
        } else if (counter == 2) {
            String chapter1 = "Chapter 1";
            dialog.setFont(Font.font(25));
            TypeWriter(chapter1);
            Timeline timer = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    FPane.getChildren().remove(btn1);
                    FPane.getChildren().remove(btn2);
                    FPane.getChildren().remove(btn3);
                    FPane.getChildren().remove(btn4);
                }
            }));
            chapter = 1;
            timer.play();
        }
    }

    public void TypeWriter(String intro) {
        final Animation ani = new Transition() {
            {
                setCycleDuration(Duration.millis(1));
            }

            protected void interpolate(double v) {
                final int length = intro.length();
                final int n = Math.round(length * (float) v);
                dialog.setText(intro.substring(0, n));
            }
        };

        ani.play();
    }
}
