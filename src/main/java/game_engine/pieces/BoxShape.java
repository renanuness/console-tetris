package game_engine.pieces;

import game_engine.Board;
import game_engine.BoardSize;
import game_engine.Point;

import java.util.ArrayList;
import java.util.List;

public class BoxShape extends Shape{
    public BoxShape(int row, int column, BoardSize boardSize){
        super(row, column, boardSize);
    }

    @Override
    public void move(Board board, int dir) {
        if(dir == 0){
            if(!canMoveDown(board)) return;
            position.move(1, 0);
            return;
        }

        if(!canMove(board, dir)) return;
        position.move(0, dir);
    }

    @Override
    public void update() {
        position.move(1,0);
    }

    @Override
    public List<Point> getPoints() {
        var points = new ArrayList<Point>();

        points.add(new Point(position.getRow(), position.getColumn()));
        points.add(new Point(position.getRow(), position.getColumn()+1));
        points.add(new Point(position.getRow()-1, position.getColumn()));
        points.add(new Point(position.getRow()-1, position.getColumn()+1));

        return points;

    }

    @Override
    public List<Point> getPointsNextRotation() {
        List<Point> points = new ArrayList<>();

        points.add(new Point(position.getRow(), position.getColumn()));
        points.add(new Point(position.getRow(), position.getColumn()+1));
        points.add(new Point(position.getRow()-1, position.getColumn()));
        points.add(new Point(position.getRow()-1, position.getColumn()+1));

        return points;
    }
}
