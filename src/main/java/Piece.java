import java.util.List;

public interface Piece {
    void draw();
    void move(int dir);
    void update();
    boolean canMove(int dir);
    boolean canMoveDown();
    List<Point> getPoints();
    List<Point> getPointsNextRotation();
    void rotate();
}
