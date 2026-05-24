package game_engine.states;

import game_engine.GameContext;

public interface GameState  {
    GameState processCommand(int command, GameContext context);
    GameState processTick(long deltaTime, GameContext context);
    void draw();
}
