package game_engine.pieces;

import java.util.List;

import game_engine.Board;
import game_engine.Point;

public interface Piece {
    void move(Board board, int dir);
    void update();
    boolean canMove(Board board, int dir);
    boolean canMoveDown(Board board);
    List<Point> getPoints();
    List<Point> getPointsNextRotation();
    void rotate(Board board);
}
