package Algo;

import App.Symbol;

import java.util.ArrayList;
import java.util.Arrays;

import static App.Symbol.O;
import static App.Symbol.P;
import static App.Symbol.X;

public class AlgoApp {
    private Integer[][] boardOfValues = new Integer[20][20];
    private Boolean[][] moorArea = new Boolean[20][20];
    private ArrayList<Symbol[][]> predictedBoards = new ArrayList<>();


    public int[] algo(Symbol[][] board){
        int x=0;
        int y=0;
        for (int i=0;i<20;i++) {
            for (int j = 0; j < 20; j++) {
                boardOfValues[i][j] = 0;
            }
        }
        for (int i=0;i<20;i++) {
            for (int j = 0; j < 20; j++) {
                if (20 - i < 5 || i < 4) {
                    if (20 - i < i)
                        boardOfValues[i][j] = -5 + (20 - i);
                    else
                        boardOfValues[i][j] = -5+i;
                }
                if (20 - j < 5 || j < 4) {
                    if (20 - j < j)
                        boardOfValues[i][j] = -5 + (20 - j);
                    else
                        boardOfValues[i][j] = -5+j;
                }
            }
        }
        createMoorArea(board);
        for (int i=0;i<20;i++)
            System.out.println(Arrays.toString(moorArea[i]));
        int possMoves = countPossMoves();
        System.out.println(possMoves);
        Symbol move = X;
        for (int k=0; k<possMoves;k++){
            predictedBoards.add(createMoves(board,k,move));
            System.out.println(k);
            move = move.returnOposite(move);
            for(int i=0;i<20;i++){
                for(int j=0;j<20;j++){
                    System.out.print(predictedBoards.get(k)[i][j].toString());
                }
                System.out.print("\n");
            }
        }
        for (int k=1;k<5;k++){
            for (int l=0;l<possMoves-k;l++){
               predictedBoards.add(createMoves(board,l,move));
                move = move.returnOposite(move);
            }

        }
        int[] wsp = findBest(predictedBoards,board);

        return wsp;
    }

    private void createMoorArea(Symbol[][] board){
        for (int i=0;i<20;i++){
            for (int j=0;j<20;j++){
                if(symbolIsNear(board,i,j))
                    moorArea[i][j]=true;
                else{
                    moorArea[i][j]=false;

                }

            }
        }
    }
    private boolean symbolIsNear(Symbol[][] board,int i, int j){
        if (i==0){
            if (j==0){
                if ((board[i+1][j]==O || board[i+1][j]==X || board[i+1][j+1]==O ||board[i+1][j+1]==X ||  board[i][j+1]==O ||board[i][j+1]==X )&& board[i][j]==P)
                    return true;
            }
            else if(j==19){
                if ((board[i+1][j]==O || board[i+1][j]==X || board[i+1][j-1]==O ||board[i+1][j-1]==X || board[i][j-1]==O ||board[i][j-1]==X)&& board[i][j]==P)
                    return true;
            }
            else{
                if ((board[i+1][j]==O || board[i+1][j]==X || board[i+1][j+1]==O ||board[i+1][j+1]==X ||  board[i][j+1]==O ||board[i][j+1]==X || board[i][j-1]==O ||board[i][j-1]==X || board[i+1][j-1]==O ||board[i+1][j-1]==X)&& board[i][j]==P)
                    return true;
            }
        }
        else if (i==19){
                if (j==0){
                    if ((board[i-1][j]==O || board[i-1][j]==X || board[i-1][j+1]==O ||board[i-1][j+1]==X ||  board[i][j+1]==O ||board[i][j+1]==X )&& board[i][j]==P)
                        return true;
                }
                else if(j==19){
                    if ((board[i-1][j]==O || board[i-1][j]==X || board[i-1][j-1]==O ||board[i-1][j-1]==X || board[i][j-1]==O ||board[i][j-1]==X)&& board[i][j]==P)
                        return true;
                }
                else{
                    if ((board[i-1][j]==O || board[i-1][j]==X || board[i-1][j+1]==O ||board[i-1][j+1]==X ||  board[i][j+1]==O ||board[i][j+1]==X || board[i][j-1]==O ||board[i][j-1]==X || board[i-1][j-1]==O ||board[i-1][j-1]==X)&& board[i][j]==P)
                        return true;
                }
            }
        else if (j==0){

                if ((board[i+1][j]==O || board[i+1][j]==X || board[i+1][j+1]==O ||board[i+1][j+1]==X ||  board[i][j+1]==O ||board[i][j+1]==X || board[i-1][j]==O ||board[i-1][j]==X || board[i-1][j+1]==O ||board[i-1][j+1]==X)&& board[i][j]==P)
                    return true;
        }
        else if (j==19){

                if ((board[i-1][j]==O || board[i-1][j]==X || board[i-1][j-1]==O ||board[i-1][j-1]==X ||  board[i][j-1]==O ||board[i][j-1]==X || board[i+1][j]==O ||board[i+1][j]==X || board[i+1][j-1]==O ||board[i+1][j-1]==X)&& board[i][j]==P)
                    return true;
            }
            else{
            if ((board[i-1][j]==O || board[i-1][j]==X || board[i-1][j-1]==O ||board[i-1][j-1]==X ||  board[i][j-1]==O ||board[i][j-1]==X || board[i+1][j]==O ||board[i+1][j]==X || board[i+1][j-1]==O ||board[i+1][j-1]==X || (board[i-1][j]==O || board[i-1][j]==X || board[i-1][j+1]==O ||board[i-1][j+1]==X ||  board[i][j+1]==O ||board[i][j+1]==X || board[i][j-1]==O ||board[i][j-1]==X || board[i-1][j-1]==O ||board[i-1][j-1]==X))&& board[i][j]==P)
            return true;
        }

        return false;
    }
    private int countPossMoves(){
        int counter=0;

        for (int i=0;i<20;i++) {
            for (int j = 0; j < 20; j++) {
                if (moorArea[i][j])
                    counter++;
            }
        }
            return counter;
    }
    private Symbol[][] createMoves(Symbol[][] board,int k, Symbol move){

        Symbol[][] boardMove = new Symbol[20][20];
        for (int i=0;i<20;i++){
            for (int j=0;j<20;j++){
                boardMove[i][j] = board[i][j];
            }
        }
        int wsp1true[] = {0,0};
        for (int i=0;i<20;i++){
            for (int j=0;j<20;j++){
                if(moorArea[i][j]){
                    wsp1true[0]=i;
                wsp1true[1]=j;}
            }
        }
        while (true) {
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 20; j++) {
                    if (moorArea[i][j]) {
                        wsp1true[0] = i;
                        wsp1true[1] = j;
                        k--;
                    }
                }
                if (k < 0) {
                    break;
                }
                if (k < 0) {
                    break;
                }
            }
            if (k<0){
                break;}
        }
        boardMove[wsp1true[0]][wsp1true[1]]  = move;

        return boardMove;
    }
    private int[] findBest(ArrayList<Symbol[][]> boards, Symbol[][] board){
        Symbol[][] bestboard = findBestBoard(boards);
        int[] wsp = new int[2];
        for (int i=0;i<20;i++){
            for (int j=0;j<20;j++){
                if (bestboard[i][j]!=board[i][j]){
                    wsp[0] =i;
                    wsp[1] = j;}
            }
        }

        return wsp;
    }
    private Symbol[][] findBestBoard(ArrayList<Symbol[][]> boards){
        int[] howfar = new int[boards.size()];
        for (int k=0;k<boards.size();k++) {
            howfar[k]=howWin(boards.get(k));
        }
        int best = 0;
        for (int k=0; k<boards.size();k++){
            if (howfar[k]>howfar[best])
                best=k;
        }
        return boards.get(best);
    }
    private int howWin(Symbol[][] board) {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 20; j++) {
                if (board[i][j] == board[i + 1][j] && board[i + 2][j] == board[i + 3][j] && board[i + 3][j] == board[i + 4][j] && board[i + 3][j] == board[i + 1][j] && board[i + 1][j] == X)
                    return (int) Math.pow(5,5);
            }
        }
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 16; j++) {
                if (board[i][j + 1] == board[i][j] && board[i][j + 2] == board[i][j + 3] && board[i][j + 3] == board[i][j + 4] && board[i][j + 3] == board[i][j + 1] && board[i][j + 1] == X)
                    return (int) Math.pow(5,5);
            }
        }
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (board[i + 1][j + 1] == board[i][j] && board[i + 2][j + 2] == board[i + 3][j + 3] && board[i + 3][j + 3] == board[i + 4][j + 4] && board[i + 3][j + 3] == board[i + 1][j + 1] && board[i + 1][j + 1] == X)
                    return (int) Math.pow(5,5);
            }
        }
        for (int i = 0; i < 16; i++) {
            for (int j = 4; j < 20; j++) {
                if (board[i + 1][j - 1] == board[i][j] && board[i + 2][j - 2] == board[i + 3][j - 3] && board[i + 3][j - 3] == board[i + 4][j - 4] && board[i + 3][j - 3] == board[i + 1][j - 1] && board[i + 1][j - 1] == X)
                    return (int) Math.pow(5,5);
            }
        }


        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 20; j++) {
                if (board[i][j] == board[i + 1][j] && board[i + 2][j] == board[i + 3][j]  && board[i + 3][j] == board[i + 1][j] && board[i + 1][j] == X)
                    return (int) Math.pow(5,4);
            }
        }
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 17; j++) {
                if (board[i][j + 1] == board[i][j] && board[i][j + 2] == board[i][j + 3] && board[i][j + 3] == board[i][j + 1] && board[i][j + 1] == X)
                    return (int) Math.pow(5,4);
            }
        }
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 17; j++) {
                if (board[i + 1][j + 1] == board[i][j] && board[i + 2][j + 2] == board[i + 3][j + 3]  && board[i + 3][j + 3] == board[i + 1][j + 1] && board[i + 1][j + 1] == X)
                    return (int) Math.pow(5,4);
            }
        }
        for (int i = 0; i < 17; i++) {
            for (int j = 3; j < 20; j++) {
                if (board[i + 1][j - 1] == board[i][j] && board[i + 2][j - 2] == board[i + 3][j - 3] && board[i + 3][j - 3] == board[i + 1][j - 1] && board[i + 1][j - 1] == X)
                    return (int) Math.pow(5,4);
            }
        }



        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 20; j++) {
                if (board[i][j] == board[i + 1][j]   && board[i + 2][j] == board[i + 1][j] && board[i + 1][j] == X)
                    return (int) Math.pow(5,3);
            }
        }
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 17; j++) {
                if (board[i][j + 1] == board[i][j]  && board[i][j + 2] == board[i][j + 1] && board[i][j + 1] == X)
                    return (int) Math.pow(5,3);
            }
        }
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 17; j++) {
                if (board[i + 1][j + 1] == board[i][j]   && board[i + 2][j + 2] == board[i + 1][j + 1] && board[i + 1][j + 1] == X)
                    return (int) Math.pow(5,3);
            }
        }
        for (int i = 0; i < 17; i++) {
            for (int j = 3; j < 20; j++) {
                if (board[i + 1][j - 1] == board[i][j]  && board[i + 2][j - 2] == board[i + 1][j - 1] && board[i + 1][j - 1] == X)
                    return (int) Math.pow(5,3);
            }
        }


        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 20; j++) {
                if (board[i][j] == board[i + 1][j]   && board[i + 1][j] == X)
                    return (int) Math.pow(5,2);
            }
        }
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 17; j++) {
                if (board[i][j + 1] == board[i][j] && board[i][j + 1] == X)
                    return (int) Math.pow(5,2);
            }
        }
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 17; j++) {
                if (board[i + 1][j + 1] == board[i][j]  && board[i + 1][j + 1] == X)
                    return (int) Math.pow(5,2);
            }
        }
        for (int i = 0; i < 17; i++) {
            for (int j = 3; j < 20; j++) {
                if (board[i + 1][j - 1] == board[i][j] && board[i + 1][j - 1] == X)
                    return (int) Math.pow(5,2);
            }
        }






        return 1;

    }
}
