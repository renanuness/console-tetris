package game_engine.states;

import game_engine.Board;
import game_engine.BoardSize;
import game_engine.GameContext;
import game_engine.pieces.BoxShape;
import game_engine.pieces.LShape;
import game_engine.pieces.Piece;
import game_engine.pieces.TShape;
import game_engine.renders.ConsoleGameRender;

public class InGameState implements GameState {
    private Piece activePiece;
    private long lastUpdate;
    private long tickRate;
    private int pieceControl;
    private Board board;
    private int level;
    private ConsoleGameRender render;

    private int score;

    public InGameState(ConsoleGameRender render){
        var boardSize = new BoardSize(21, 10);
        this.board = new Board(boardSize, x-> this.setScore(x));
        lastUpdate = System.currentTimeMillis();
        tickRate = (1000/6);
        this.render = render;
    }

    @Override
    public GameState processCommand(int command, GameContext context) {
        if (command == 0) {
            moveDown(board);
        } else if (command == 1) {
            moveRight(board);
        } else if (command == -1) {
            moveLeft(board);
        } else if (command == 2) {
            rotate(board);
        }
        return this;
    }

    @Override
    public GameState processTick(long deltaTime, GameContext context) {
        if(activePiece == null){
            activePiece = instantiatePiece(board);
            if(activePiece == null){
                return new GameOverState(score);
            }
        }

        var now = System.currentTimeMillis();
        if(now - lastUpdate >= tickRate){
            moveDown(board);
            lastUpdate = now;
            board.updateState(activePiece);
            if(!activePiece.canMoveDown(board)){
                board.consolidatePiece(activePiece);
                instantiatePiece(board);
            }
        }
        return this;
    }

    @Override
    public void draw() {
        render.draw(board, score, level);
    }

    public Piece instantiatePiece(Board board){
        if(pieceControl % 3 == 0) {
            Piece newPiece = new LShape(1, 4, board.getBoardSize());
            this.activePiece = newPiece;
        }else if(pieceControl % 3 == 1) {
            Piece newPiece = new TShape(1, 4, board.getBoardSize());
            this.activePiece = newPiece;
        }else{
            Piece newPiece = new BoxShape(1, 4, board.getBoardSize());
            this.activePiece = newPiece;
        }
        pieceControl++;

        if(!activePiece.canMoveDown(board)){
            activePiece = null;
        }
        return activePiece;
    }

    //region Commands
    public void moveLeft(Board board){
        if(activePiece != null){
            activePiece.move(board,-1);
        }
    }

    public void moveRight(Board board){
        if(activePiece != null) {
            activePiece.move(board,1);
        }
    }

    public void rotate(Board board){
        if(activePiece != null) {
            activePiece.rotate(board);
        }
    }

    public void moveDown(Board board) {
        if(activePiece != null) {
            activePiece.move(board,0);
        }
    }
    //endregion

    //region Getters & Setters
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    //endregion
}
