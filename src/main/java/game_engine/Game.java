package game_engine;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;

import java.util.ArrayDeque;

public class Game {
    private GameContext gameState;

    private GlobalKeyListener inputListener;
    private ArrayDeque<Integer> commands;

    private Board board;
    private int frameRate = 32;
    public Game() throws NativeHookException {
        commands = new ArrayDeque<>();

        this.gameState = new GameContext(State.START_MENU, commands);
        inputListener = new GlobalKeyListener(commands);
        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(inputListener);
    }

    public void runGame() throws Exception {
        long lastUpdate = System.currentTimeMillis();
        while(true){
            long now = System.currentTimeMillis();
            long deltaTime = now - lastUpdate;
            if(deltaTime > frameRate) {
                gameState.update(deltaTime);
                gameState.getGameState().draw();
                lastUpdate = now;
            }
        }
    }
}
