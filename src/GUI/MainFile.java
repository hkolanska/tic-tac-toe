package GUI;

import App.MainApp;
import javafx.application.Application;
        import javafx.event.ActionEvent;
        import javafx.event.EventHandler;
        import javafx.scene.Group;
        import javafx.scene.Scene;
        import javafx.scene.control.Alert;
        import javafx.scene.control.Button;
        import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
        import javafx.scene.text.Font;
        import javafx.scene.text.FontWeight;
        import javafx.scene.text.Text;
        import javafx.stage.*;

        import java.awt.Desktop;
        import java.io.File;
        import java.io.IOException;
        import java.util.Arrays;
        import java.util.List;

        import java.awt.*;
        import javafx.application.Application;
        import javafx.event.ActionEvent;
        import javafx.event.EventHandler;
        import javafx.geometry.Insets;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.TextArea;
        import javafx.scene.image.Image;
import javafx.stage.FileChooser;
        import javafx.stage.Stage;
        import java.io.File;
        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.Arrays;
import static java.lang.Thread.sleep;



public class MainFile extends Application {
    boolean  startapp = false;
    private Desktop desktop = Desktop.getDesktop();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{


        Group group = new Group();
        //StackPane root = new StackPane();
        Pane root = new Pane();

        Scene scene = new Scene(root ,1141, 735);
        javafx.scene.image.Image Background = new javafx.scene.image.Image(new File("D:\\MOJEPRYWATNE\\0nauka\\semestr4\\Badania Operacyjne\\OX\\plansza.png").toURI().toURL().toExternalForm());
        BackgroundImage myBI= new BackgroundImage(Background, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBI));
        primaryStage.setScene(scene);
        primaryStage.setTitle("OX Game");
        javafx.scene.control.Button btn = new javafx.scene.control.Button();
        btn.setText("User vs. Algorithm");
        btn.setLayoutY(300);
        btn.setLayoutX(130);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            boolean gameflag = false;
            @Override
            public void handle(ActionEvent event) { //napisz co ma się dziać po naciśnięciu rozpocznij
                try{
                    MainApp game = new MainApp();
                    if (!gameflag){

                    game.theGame(primaryStage,root,scene);}
                    else{
                        game.setAllEmpty(root);
                    }
                    gameflag = true;
                }
                catch(Exception e){}
            }});



        javafx.scene.control.Button btn2 = new javafx.scene.control.Button();
        btn2.setText("Algorithm vs. Algorithm");
        btn2.setLayoutY(350);
        btn2.setLayoutX(115);


        root.getChildren().add(btn);
        root.getChildren().add(btn2);

        primaryStage.show();
    }

    }



