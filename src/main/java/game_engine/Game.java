package game_engine;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import game_engine.Board;
import game_engine.GameState;
import game_engine.GlobalKeyListener;

public class Game {
    private RenderService renderService;
    private GameState gameState;

    private GlobalKeyListener inputListener;


    private Board board;
    private int frameRate = 500;
    private int tickRate = 250;
    public Game() throws NativeHookException {


        this.board = new Board(21,10, x->this.endGame(x));

        this.gameState = new GameState(State.START_MENU, this.board);
        this.renderService = new RenderService(gameState);
        inputListener = new GlobalKeyListener(
                x->this.moveLeft(),
                x->this.moveRight(),
                x-> this.rotate(),
                x->this.moveDown(),
                x ->this.startGame()
        );
        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(inputListener);
    }

    private void startGame() {
        gameState.setState(State.GAME);
    }

    public void start() throws Exception {
        long lastUpdate = System.currentTimeMillis();
        while(!gameState.isEnded()){
            long now = System.currentTimeMillis();
            if(now - lastUpdate > frameRate) {
                readInput();
                updateStates();
                clearScreen();
                draw();
                lastUpdate = now;
            }
        }
    }


    private void endGame(int score){
        gameState.setEnded();
        gameState.setScore(score);
    }
    private void readInput(){

    }

    private void updateStates(){
        if(gameState.getState() == State.GAME) {
            board.updateState();
        }
    }

    private void draw() throws Exception {
        //board.draw();
        renderService.render();
    }

    public void moveLeft(){
        board.moveLeft();
    }

    public void moveRight(){
        board.moveRight();
    }

    public void moveDown(){
        board.moveDown();
    }

    public void rotate(){
        board.rotate();
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
