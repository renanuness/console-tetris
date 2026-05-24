package game_engine;

import game_engine.states.GameState;
import game_engine.states.StartMenuState;

import java.util.ArrayDeque;

public class GameContext {
    private ArrayDeque<Integer> commands;

    private GameState gameState;

    public GameContext(State state, ArrayDeque<Integer> commands){
        this.gameState = new StartMenuState();
        this.commands = commands;
    }

    public void update(Long deltaTime){
        while(!commands.isEmpty()) {
            var command = commands.pop();
            gameState = gameState.processCommand(command, this);
        }
        gameState = gameState.processTick(deltaTime, this);
    }

    //region Getter & Setters
    public GameState getGameState(){
        return gameState;
    }
    //endregion
}
