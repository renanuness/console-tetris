package game_engine.renders;

import game_engine.Board;

public interface GameRender{
    void draw(Board board, int score, int level);
}
