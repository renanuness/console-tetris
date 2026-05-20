package game_engine;

import game_engine.pieces.BoxShape;
import game_engine.pieces.LShape;
import game_engine.pieces.Piece;
import game_engine.pieces.TShape;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Board {
    private BoardSize boardSize;
    private int[][] board;
    private Piece activePiece;
    private List<Point> lastPointsDrawn;
    private int pieceControl;
    private List<Integer> linesToClean = new ArrayList<Integer>();
    private int score;
    private Consumer<Integer> endGame;

    public Board(int rows, int columns, Consumer<Integer> endGame){
        pieceControl = 0;
        this.endGame = endGame;
        this.boardSize = new BoardSize(rows, columns);
        this.board = new int[rows][columns];
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
    public void instantiatePiece(){
        if(pieceControl % 3 == 0) {
            Piece newPiece = new LShape(1, 4, boardSize);
            this.activePiece = newPiece;
        }else if(pieceControl % 3 == 1) {
            Piece newPiece = new TShape(1, 4, boardSize);
            this.activePiece = newPiece;
        }else{
            Piece newPiece = new BoxShape(1, 4, boardSize);
            this.activePiece = newPiece;
        }
        pieceControl++;

        if(isColliding(activePiece)){
           endGame.accept(score);
        }
    }

    public void addPiece(Piece piece){

    }

    public int[][] getBoard(){
        return board;
    }

    public void updateState(){
        if(activePiece == null){
            instantiatePiece();


            return;
        }
        // pegar a posição
        // pegar o formato
        // aplicar o formato no board utilizando a posição
        activePiece.update();

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

        // TESTAR COLISÃO
        if(isColliding(activePiece)){
            consolidatePiece(points);
            lastPointsDrawn = null;
            instantiatePiece();
        }
    }

    public boolean isColliding(Piece piece){
        var points = piece.getPoints();
        for(var point: points) {
            if (point.getRow() == boardSize.getRows() - 1 || board[point.getRow() + 1][point.getColumn()] == 2) {
                return true;
            }
        }
        return false;
    }

    public void consolidatePiece(List<Point> points){
         for(var point: points){
             board[point.getRow()][point.getColumn()] = 2;
         }
         removeCompleteLines();
         updateBoard();
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
    }
    // Rearrange board
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

    // Commands
    public void moveLeft(){
        if(activePiece != null){
            var points = activePiece.getPoints();
            for(var point: points) {
                point.move(0, -1);
                if(board[point.getRow()][point.getColumn()] == 2){
                    return;
                }
            }
            activePiece.move(-1);
        }
    }

    public void moveRight(){
        if(activePiece != null) {
            var points = activePiece.getPoints();
            for(var point: points) {
                point.move(0, 1);
                if(board[point.getRow()][point.getColumn()] == 2){
                    return;
                }
            }
            activePiece.move(1);
        }
    }

    public void rotate(){
        if(activePiece != null) {
            var points = activePiece.getPointsNextRotation();
            for(var point: points){
                var resultingColumn = point.getColumn();
                var resultingRow = point.getRow();
                if(board[resultingRow][resultingColumn] == 2){
                    return;
                }
                if(resultingColumn < 0 || resultingColumn >= boardSize.getColumns()) {
                    return;
                }

                if(resultingRow < 0 || resultingRow >= boardSize.getRows()) {
                    return;
                }
            }

            activePiece.rotate();
        }
    }

    public void moveDown() {
        if(activePiece != null) {
            activePiece.move(0);
        }
    }

    public void draw(){
        // HEADER
        System.out.print(String.format("\033[%d;%dH", 0, 0));
        System.out.print("Score: " + score);

        System.out.print(String.format("\033[%d;%dH", 0, 20));
        System.out.print("Level: "+ 1);
        System.out.println();

        // UPPER BOARD
        System.out.print(String.format("\033[%d;%dH", 2, 1));
        System.out.print("\u250C");
        for(int i = 0; i < this.boardSize.columns; i++){
            System.out.print("\u2500");
        }
        System.out.print("\u2510");
        System.out.println();

        // BOARD CONTENT
        for(int i = 1; i < this.boardSize.rows; i++){
            System.out.print("\u2502");
            for(int j = 0; j < this.boardSize.columns; j++){
                String c = "\u23F9";
                if(board[i][j] == 0){
                    c = "\u25A1";
                }
                System.out.print(c);
            }
            System.out.print("\u2502");
            System.out.println();
        }

        //System.out.print(String.format("\033[%d;%dH", 2, 1));
        System.out.print("\u2514");
        for(int i = 0; i < this.boardSize.columns; i++){
            System.out.print("\u2500");
        }
        System.out.print("\u2518");
        System.out.println();
    }
}
