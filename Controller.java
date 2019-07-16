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
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URL;
import java.security.spec.ECField;
import java.sql.Time;
import java.util.*;

import javafx.concurrent.*;


public class Controller implements Initializable {

    @FXML
    FlowPane FPane, flow;

    @FXML
    BorderPane BPane;

    @FXML
    Pane pane;

    @FXML
    ImageView logo, BG;

    @FXML
    Button btn1, btn2, btn3, btn4, ExtBtn;

    @FXML
    TextField name;

    @FXML
    Label dialog;


    int counter = 0;
    int FightCounter = 0;
    String UsrName;
    String CharacterType;
    String CharacterName;
    int CharacterHealth;
    int BtnPressed = 0;
    int chapter = 0;
    int StrikeHead = 10;
    int StrikeBody = 20;
    int StrikeLegs = 5;
    int EnemyHealth;
    ImageView img3, img2;

    ArrayList<String> ScoutImagePath = new ArrayList<>();
    ArrayList<String> WarriorImagePath = new ArrayList<>();


    public void loadScout() {
        for (int i = 80; i >= 0; i -= 10) {
            ScoutImagePath.add("img/Character Health/Scout/" + i + ".jpg");
        }
    }

    public void loadWarrior() {
        for (int i = 160; i >= 0; i -= 10) {
            WarriorImagePath.add("img/Character Health/Warrior/" + i + ".jpg");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadScout();
        loadWarrior();
        FPane.getChildren().remove(btn2);
        FPane.getChildren().remove(btn3);
        FPane.getChildren().remove(btn4);
        FPane.getChildren().remove(name);
        btn1.setText("Start Game");
        ExtBtn.setText("Exit Game");
    }

    public void HandleBtn(ActionEvent e) {
            String intro = "Hello Adventurer, Welcome to Dragon's Vs Warrior's";
            if (e.getSource() == ExtBtn) {
                Stage stage = (Stage) ExtBtn.getScene().getWindow();
                stage.close();
            } else if (e.getSource() == btn1) {
                if (chapter == 0) {
                    if (BtnPressed == 0) {
                        btn1.setDisable(true);
                        BPane.setTop(null);
                        BG = new ImageView(new Image(Main.class.getResourceAsStream("img/Dungeon.jpg")));
                        Pane pane1 = new Pane(dialog);
                        dialog.setGraphic(BG);
                        BG.fitWidthProperty().bind(BPane.widthProperty());
                        BG.fitHeightProperty().bind(pane1.heightProperty());
                        BPane.setCenter(pane1);
                        TypeWriter.Type(intro, dialog);
                        Run();
                        name.setOnKeyPressed(new EventHandler<KeyEvent>() {
                            @Override
                            public void handle(KeyEvent keyEvent) {
                                if (chapter == 0) {
                                    if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                                        UsrName = name.getText();
                                        String intro3 = "Thanks " + UsrName + ", Please select your character.";
                                        TypeWriter.Type(intro3, dialog);
                                        counter = 1;
                                        Run();
                                    }
                                } else if (chapter == 1) {
                                    if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                                        CharacterName = name.getText();
                                        counter = 3;
                                        Run();
                                    }

                                }

                            }
                        });
                        BtnPressed = 1;
                    } else if (BtnPressed == 1) {
                        CharacterType = btn1.getText();
                        counter = 2;
                        Run();
                        BtnPressed = 0;
                    }
                }else if (chapter == 1) {
                    if (BtnPressed == 0) {
                        Fight();
                        BtnPressed = 1;
                    } else if (BtnPressed == 1) {
                        FightCounter = 1;
                        Fight();
                    }
                }
            } else if (e.getSource() == btn2) {
                if (chapter == 0) {
                    CharacterType = btn2.getText();
                    counter = 2;
                    Run();
                } else if (chapter == 1) {

                }
            } else if (e.getSource() == btn3) {
                if (chapter == 0) {
                    CharacterType = btn3.getText();
                    counter = 2;
                    Run();
                } else if (chapter == 1) {

                }
            } else if (e.getSource() == btn4) {
                if (chapter == 0) {
                    CharacterType = btn4.getText();
                    counter = 2;
                    Run();
                } else if (chapter == 1) {

                }
            }
        }





    public void Run() {
        if (counter == 0) {
            String intro2 = "Please enter your name below.";
            Timeline timer = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    btn1.setDisable(false);
                    TypeWriter.Type(intro2, dialog);
                    System.out.println(counter);
                    Timeline timer = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            FPane.getChildren().remove(btn1);
                            btn1.setText("Confirm");
                            FPane.getChildren().remove(ExtBtn);
                            FPane.getChildren().add(name);
                            name.requestFocus();
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
            Timeline timer = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
//                    dialog.setFont(Font.font(15));
                    if (CharacterType == "Warrior") {
                        BG.setImage(new Image(Main.class.getResourceAsStream("img/Dungeon/Dungeon-Warrior.jpg")));
                    } else if (CharacterType == "Archer") {
                        BG.setImage(new Image(Main.class.getResourceAsStream("img/Dungeon/Dungeon-Archer.jpg")));
                    } else if (CharacterType == "Scout") {
                        BG.setImage(new Image(Main.class.getResourceAsStream("img/Dungeon/Dungeon-Scout.jpg")));
                    } else if (CharacterType == "Mage") {
                        BG.setImage(new Image(Main.class.getResourceAsStream("img/Dungeon/Dungeon-Mage.jpg")));
                    }
                    FPane.getChildren().remove(btn1);
                    FPane.getChildren().remove(btn2);
                    FPane.getChildren().remove(btn3);
                    FPane.getChildren().remove(btn4);
                    TypeWriter.Type(CharacterType + " Cool Let's Go", dialog);
                    Timeline timer = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            if (CharacterType == "Warrior") {
                                BG.setImage(new Image(Main.class.getResourceAsStream("img/Forest/Scene 1/Warrior.jpg")));
                            } else if (CharacterType == "Archer") {
                                BG.setImage(new Image(Main.class.getResourceAsStream("img/Forest/Scene 1/Archer.jpg")));
                            } else if (CharacterType == "Scout") {
                                BG.setImage(new Image(Main.class.getResourceAsStream("img/Forest/Scene 1/Scout.jpg")));
                            } else if (CharacterType == "Mage") {
                                BG.setImage(new Image(Main.class.getResourceAsStream("img/Forest/Scene 1/Mage.jpg")));
                            }
                            dialog.setText("");
                            Timeline timer = new Timeline(new KeyFrame(Duration.seconds(2.5), new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    TypeWriter.Type("Wait do you hear that it's sounds like a Fire Wolf", dialog);
                                    Timeline timer = new Timeline(new KeyFrame(Duration.seconds(2.5), new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent actionEvent) {
                                            if (CharacterType == "Warrior") {
                                                BG.setImage(new Image(Main.class.getResourceAsStream("img/Forest/Scene 2/Warrior.jpg")));
                                            } else if (CharacterType == "Archer") {
                                                BG.setImage(new Image(Main.class.getResourceAsStream("img/Forest/Scene 2/Archer.jpg")));
                                            } else if (CharacterType == "Scout") {
                                                BG.setImage(new Image(Main.class.getResourceAsStream("img/Forest/Scene 2/Scout.jpg")));
                                            } else if (CharacterType == "Mage") {
                                                BG.setImage(new Image(Main.class.getResourceAsStream("img/Forest/Scene 2/Mage.jpg")));
                                            }
                                            TypeWriter.Type(UsrName + " it's time for your first challenge. Good Luck.", dialog);
                                            FPane.getChildren().add(btn1);
                                            btn1.setText("FIGHT...");
                                            EnemyHealth = 80;
                                        }
                                    }));
                                    timer.play();

                                }
                            }));
                            timer.play();
                        }
                    }));
                    timer.play();
                }
            }));
            chapter = 1;
            timer.play();

        } else if (counter == 3) {

            }
        }

        public void Fight() {
            if (CharacterType == "Warrior") {
                if (chapter == 1) {
                    if (FightCounter == 0) {
                        BPane.setTop(flow);
//                        flow.getChildren().add(img);
                        flow.setPrefHeight(50);
                        img2 = new ImageView(new Image(getClass().getResourceAsStream(WarriorImagePath.get(1))));
                        System.out.println(ScoutImagePath.get(0));
                        img3 = new ImageView(new Image(getClass().getResourceAsStream(ScoutImagePath.get(0))));
                        img2.setFitWidth(100);
                        img2.setFitHeight(25);
                        img3.setFitHeight(25);
                        img3.setFitWidth(100);

                        flow.getChildren().add(img2);
                        flow.getChildren().add(img3);
                        flow.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
                        img2.setTranslateX(25);
                        img2.setTranslateY(12.5);
                        img3.setTranslateX(100);
                        img3.setTranslateY(12.5);

                        CharacterHealth = 160;

                        dialog.setText("");
                        FPane.getChildren().add(btn2);
                        FPane.getChildren().add(btn3);
                        FPane.getChildren().add(btn4);
                        btn1.setText("Attack Head");
                        btn2.setText("Attack Body");
                        btn3.setText("Attack Legs");
                        btn4.setText("Items");
                    } else if (FightCounter == 1) {
                        EnemyHealth = EnemyHealth - StrikeHead;
                        flow.getChildren().remove(img3);
                        img3 = new ImageView(new Image(Main.class.getResourceAsStream(ScoutImagePath.get(1))));
                        flow.getChildren().add(img3);
                        img3.setTranslateX(100);
                        img3.setTranslateY(12.5);
                        img3.setFitHeight(25);
                        img3.setFitWidth(100);
                        btn1.setDisable(true);
                        btn2.setDisable(true);
                        btn3.setDisable(true);
                        btn4.setDisable(true);
                        Timeline timer = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                CharacterHealth = CharacterHealth - 10;
                                flow.getChildren().remove(img2);
                                img2 = new ImageView(new Image(getClass().getResourceAsStream(WarriorImagePath.get(1))));
                                img2.setTranslateX(25);
                                img2.setTranslateY(12.5);
                                img2.setFitWidth(100);
                                img2.setFitHeight(25);
                                flow.getChildren().add(img2);
                            }
                        }));
                        timer.play();





                    }
                }
            } else if (CharacterType == "Archer") {

            } else if (CharacterType == "Scout") {

            } else if (CharacterType == "Mage") {

            }
        }
}
