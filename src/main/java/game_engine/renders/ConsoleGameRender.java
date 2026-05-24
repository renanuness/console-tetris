package game_engine.renders;

import game_engine.Board;

public class ConsoleGameRender implements GameRender {

    public void draw(Board board, int score, int level){
        // HEADER
        System.out.print(String.format("\033[%d;%dH", 0, 0));
        System.out.print("Score: " + score);

        System.out.print(String.format("\033[%d;%dH", 0, 20));
        System.out.print("Level: "+ level);
        System.out.println();

        // UPPER BOARD
        System.out.print(String.format("\033[%d;%dH", 2, 1));
        System.out.print("\u250C");
        for(int i = 0; i < board.getBoardSizeColumns(); i++){
            System.out.print("\u2500");
        }
        System.out.print("\u2510");
        System.out.println();

        // BOARD CONTENT
        for(int i = 1; i < board.getBoardSizeRow(); i++){
            System.out.print("\u2502");
            for(int j = 0; j < board.getBoardSizeColumns(); j++){
                String c = "\u23F9";
                if(board.getPointContent(i,j) == 0){
                    c = "\u25A1";
                }
                System.out.print(c);
            }
            System.out.print("\u2502");
            System.out.println();
        }

        //System.out.print(String.format("\033[%d;%dH", 2, 1));
        System.out.print("\u2514");
        for(int i = 0; i < board.getBoardSizeColumns(); i++){
            System.out.print("\u2500");
        }
        System.out.print("\u2518");
        System.out.println();
    }
}
