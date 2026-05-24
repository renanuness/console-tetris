package game_engine;
import game_engine.pieces.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Board {
    private BoardSize boardSize;
    private int[][] board;
    private List<Point> lastPointsDrawn;

    private List<Integer> linesToClean = new ArrayList<Integer>();
    private int score;
    private Consumer<Integer> updateScore;

    public Board(BoardSize boardSize, Consumer<Integer> updateScore){
        this.boardSize = boardSize;
        this.board = new int[boardSize.getRows()][boardSize.getColumns()];
        this.updateScore = updateScore;
        cleanBoard();
        score = 0;
    }

    private void cleanBoard(){
        for(int i = 0; i < this.boardSize.rows; i++){
            for(int j = 0; j < this.boardSize.columns; j++){
                this.board[i][j] = 0;
            }
        }
    }

    public int getPointContent(int row, int column){
        return board[row][column];
    }

    public int getBoardSizeRow(){
        return boardSize.rows;
    }

    public int getBoardSizeColumns(){
        return boardSize.columns;
    }

    public int[][] getBoard(){
        return board;
    }

    public void updateState(Piece activePiece){
        if(lastPointsDrawn != null){
            for(var point: lastPointsDrawn){
                board[point.getRow()][point.getColumn()] = 0;
            }
        }

        List<Point> points = activePiece.getPoints();
        lastPointsDrawn = new ArrayList<>();

        for(var point: points){
            if(point.getRow() < 0){
                continue;
            }
            board[point.getRow()][point.getColumn()] = 1;
            lastPointsDrawn.add(point);
        }
    }

    public void consolidatePiece(Piece piece){
        for(var point: piece.getPoints()){
             board[point.getRow()][point.getColumn()] = 2;
        }
        removeCompleteLines();
        updateBoard();
        lastPointsDrawn = null;
     }

    private void removeCompleteLines() {
        for(int i = boardSize.rows-1; i >=0; i--){
            var shouldClear = true;
            for(int j = 0; j < boardSize.columns; j++){
                if(board[i][j] != 2){
                    shouldClear = false;
                    break;
                }
            }
            // clear
            if(shouldClear){
                linesToClean.add(i);
                for(int j = 0; j < boardSize.columns; j++){
                    board[i][j] = 0;
                }
            }
        }
        score += linesToClean.size() * 10;
        updateScore.accept(score);
    }

    private void updateBoard(){
        int linesDown = 0;
        for(int i = boardSize.rows -1; i >= 0; i--){
            if(linesToClean.contains(i)){
                linesDown++;
            }else{
                if(linesDown >= 0){
                    moveLineDown(i, linesDown);
                }
            }
        }
        linesToClean.clear();
    }

    private void moveLineDown(int line, int rows){
        for(int i = 0; i < boardSize.columns; i++){
            board[line+rows][i] = board[line][i];
        }
    }

    public BoardSize getBoardSize() {
        return boardSize;
    }
}