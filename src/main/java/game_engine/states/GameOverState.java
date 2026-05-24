package game_engine.states;

import game_engine.GameContext;
import game_engine.State;
import game_engine.renders.GameOverRenderer;
import jdk.dynalink.linker.GuardingTypeConverterFactory;

public class GameOverState implements GameState{
    private GameOverRenderer renderer;
    private int score;

    public GameOverState(int score){
        renderer = new GameOverRenderer();
        this.score = score;
    }

    @Override
    public GameState processCommand(int command, GameContext context) {
        if (command == 10) {
            return new StartMenuState();
        }
        return this;
    }

    @Override
    public GameState processTick(long deltaTime, GameContext context) {
        return this;
    }

    @Override
    public void draw() {
        renderer.draw(score);
    }
}
