import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;

public class Game {
    private boolean ended;
    private GlobalKeyListener inputListener;
    private Board board;
    private int frameRate = 500;
    private int tickRate = 250;
    private int score;
    public Game() throws NativeHookException {
        this.ended = false;
        inputListener = new GlobalKeyListener(
                x->this.moveLeft(),
                x->this.moveRight(),
                x -> this.rotate(),
                x->this.moveDown()
        );

        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(inputListener);
        this.board = new Board(21,10, x->this.endGame(x));
    }
    public void start(){
        long lastUpdate = System.currentTimeMillis();
        while(!ended){
            long now = System.currentTimeMillis();
            if(now - lastUpdate > frameRate) {
                readInput();
                updateStates();
                clearScreen();
                draw();
                lastUpdate = now;
            }
        }

        if(ended){
            showEnd();
        }
    }

    private void showEnd(){
        System.out.println("Game Over!");
        System.out.println("Pontuação Final: " + score);
    }

    private void endGame(int score){
        ended =true;
        this.score = score;
    }
    private void readInput(){

    }

    private void updateStates(){
        board.updateState();
    }

    private void draw(){
        board.draw();
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
