package game_engine.states;

import game_engine.GameContext;
import game_engine.renders.ConsoleGameRender;
import game_engine.renders.StartMenuRenderer;

public class StartMenuState implements GameState{
    private StartMenuRenderer renderer;

    public StartMenuState(){
        this.renderer = new StartMenuRenderer();
    }

    @Override
    public GameState processCommand(int command, GameContext context) {
        if(command == 10){
            return new InGameState(new ConsoleGameRender());
        }
        return this;
    }

    @Override
    public GameState processTick(long deltaTime, GameContext context) {
        return this;
    }

    @Override
    public void draw() {
        renderer.draw();
    }
}
