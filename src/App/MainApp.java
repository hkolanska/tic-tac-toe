package App;

import Algo.AlgoApp;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;

import static App.Symbol.O;
import static App.Symbol.P;
import static App.Symbol.X;

public class MainApp {
    private static Symbol[][] board = new Symbol[20][20];
    private Symbol move = O;


    public MainApp() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                board[i][j] = P;
            }
        }

    }
    public void theGame(Stage primaryStage,Pane root,Scene scene)throws Exception {
        // w planszy false to kółko a true to krzyzyk
        root.getChildren().remove(2,root.getChildren().size());

        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getX()>424 &&mouseEvent.getX()<1127 && mouseEvent.getY()>20 && mouseEvent.getY()<708) {
                    Double wspX = ((mouseEvent.getX() - 424) / 35);
                    int i = wspX.intValue();
                    Double wspY = ((mouseEvent.getY() - 20) / 35);
                    int j = wspY.intValue();
                    System.out.println("mouse click detected on board! " + "X= " + mouseEvent.getX() + "Y= " + mouseEvent.getY()+"In space "+i+" "+j);
                    System.out.println(move);
                    if (move==O){
                        if(board[i][j]==P){
                            setO(i,j);
                            move = X;
                        }
                        try{
                            drawBoard(root);}
                        catch(Exception e){};

                        if (!checkWin() && move==X){

                        AlgoApp algo_xy = new AlgoApp();
                        int[] wsp = algo_xy.algo(board);
                        if(board[wsp[0]][wsp[1]]==P){
                            setX(wsp[0],wsp[1]);
                        move = O;}
                        try{
                        drawBoard(root);}
                        catch(Exception e){};}
                    }

//                    else if (move==X){
//                        if(board[i][j]==P){
//                            setX(i,j);
//                        move = O;}
//                        try{
//                        drawBoard(root);}
//                        catch(Exception e){};


//                    }
                }
                else
                    System.out.println("mouse click detected not on board! " + "X= "+mouseEvent.getX()+"Y= "+mouseEvent.getY());
                if (checkWin()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("KONIEC");
                alert.setHeaderText(null);
                alert.setContentText("WYGRYWA " + move.returnOposite(move));
                alert.showAndWait();
                    for (int i = 0; i < 20; i++) {
                        for (int j = 0; j < 20; j++) {
                            board[i][j] = P;
                        }
                        try{
                        drawBoard(root);}
                        catch(Exception e){};
                        root.getChildren().remove(2,root.getChildren().size());
                    }
                }
            }
        });
        }

    private void drawBoard(Pane root)throws Exception{
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (board[i][j]==X)
                    drawX(i,j,root);
                else if (board[i][j]==O)
                    drawO(i,j,root);
            }
        }
    }
    private void setO(int i, int j){
        board[i][j]=O;
    }
    private void setX(int i, int j){
        board[i][j]=X;

    }
    private void drawX(int i,int j,Pane root)throws Exception{
        Image X = new Image(new File("D:\\MOJEPRYWATNE\\0nauka\\semestr4\\Badania Operacyjne\\OX\\cross.png").toURI().toURL().toExternalForm());
        final ImageView selectedImageX = new ImageView();
        selectedImageX.setImage(X);
        selectedImageX.setX(420+35*i);
        selectedImageX.setY(18+35*j);
        root.getChildren().add(selectedImageX  );
    }
    private void drawO(int i,int j,Pane root)throws  Exception{
        Image O = new Image(new File("D:\\MOJEPRYWATNE\\0nauka\\semestr4\\Badania Operacyjne\\OX\\circle.png").toURI().toURL().toExternalForm());
        final ImageView selectedImageO = new ImageView();
        selectedImageO.setImage(O);
        selectedImageO.setX(420+35*i);
        selectedImageO.setY(20+35*j);
        root.getChildren().add(selectedImageO);

    }
    public void setAllEmpty(Pane root) {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                board[i][j] = P;
            }
        }
        root.getChildren().remove(2,root.getChildren().size());

    }
    private boolean checkWin(){
        for (int i=0;i<16;i++){
            for (int j=0;j<20;j++){
                if(board[i][j]==board[i+1][j] && board[i+2][j]==board[i+3][j] && board[i+3][j]==board[i+4][j] && board[i+3][j]==board[i+1][j] && (board[i+1][j]==O || board[i+1][j]==X))
                    return true;
            }
        }
        for (int i=0;i<20;i++) {
            for (int j = 0; j < 16; j++) {
                if (board[i][j + 1] == board[i][j] && board[i][j + 2] == board[i][j + 3] && board[i][j + 3] == board[i][j + 4] && board[i][j + 3] == board[i][j + 1] && (board[i][j + 1] == O || board[i][j + 1] == X))
                    return true;
            }
        }
            for (int i=0;i<16;i++) {
                for (int j = 0; j < 16; j++) {
                    if (board[i+ 1][j + 1] == board[i][j] && board[i+ 2][j + 2] == board[i+ 3][j + 3] && board[i+ 3][j + 3] == board[i+ 4][j + 4] && board[i+ 3][j + 3] == board[i+ 1][j + 1] && (board[i+ 1][j + 1] == O || board[i+ 1][j + 1] == X))
                        return true;
                }
            }
        for (int i=0;i<16;i++) {
            for (int j = 4; j < 20; j++) {
                if (board[i+ 1][j - 1] == board[i][j] && board[i+ 2][j - 2] == board[i+ 3][j - 3] && board[i+ 3][j - 3] == board[i+ 4][j - 4] && board[i+ 3][j - 3] == board[i+ 1][j - 1] && (board[i+ 1][j - 1] == O || board[i+1][j - 1] == X))
                    return true;
            }
        }
        return false;
    }

    public Symbol[][] getBoard() {
        return this.board;
    }
}
