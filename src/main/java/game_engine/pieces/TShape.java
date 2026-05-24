package game_engine.pieces;

import game_engine.Board;
import game_engine.BoardSize;
import game_engine.Point;

import java.util.*;

public class TShape extends Shape {
    public TShape(int row, int column, BoardSize boardSize){
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
        this.position.move(1, 0);
    }

    @Override
    public List<Point> getPoints() {
        List<Point> points = new ArrayList<>();

        if(rotation == 0) {
            points.add(new Point(position.getRow()-1, position.getColumn()));
            points.add(new Point(position.getRow(), position.getColumn() - 1));
            points.add(new Point(position.getRow(), position.getColumn()));
            points.add(new Point(position.getRow(), position.getColumn() + 1));
        }

        if(rotation == 1) {
            points.add(new Point(position.getRow()+1, position.getColumn()));
            points.add(new Point(position.getRow()-1, position.getColumn()));
            points.add(new Point(position.getRow(), position.getColumn()+1));
            points.add(new Point(position.getRow(), position.getColumn()));
        }

        if(rotation == 2) {
            points.add(new Point(position.getRow() + 1, position.getColumn()));
            points.add(new Point(position.getRow(), position.getColumn()-1));
            points.add(new Point(position.getRow(), position.getColumn()));
            points.add(new Point(position.getRow(), position.getColumn() + 1));
        }

        if(rotation == 3) {
            points.add(new Point(position.getRow()+1, position.getColumn()+1));
            points.add(new Point(position.getRow()-1, position.getColumn()+1));
            points.add(new Point(position.getRow(), position.getColumn()+1));
            points.add(new Point(position.getRow(), position.getColumn()));
        }

        return points;
    }

    @Override
    public List<Point> getPointsNextRotation() {
        List<Point> points = new ArrayList<>();

        var nextRotation = (rotation + 1) % 4;
        if(nextRotation == 0) {
            points.add(new Point(position.getRow()-1, position.getColumn()));
            points.add(new Point(position.getRow(), position.getColumn() - 1));
            points.add(new Point(position.getRow(), position.getColumn()));
            points.add(new Point(position.getRow(), position.getColumn() + 1));
        }

        if(nextRotation == 1) {
            points.add(new Point(position.getRow()+1, position.getColumn()));
            points.add(new Point(position.getRow()-1, position.getColumn()));
            points.add(new Point(position.getRow(), position.getColumn()+1));
            points.add(new Point(position.getRow(), position.getColumn()));
        }

        if(nextRotation == 2) {
            points.add(new Point(position.getRow() + 1, position.getColumn()));
            points.add(new Point(position.getRow(), position.getColumn()-1));
            points.add(new Point(position.getRow(), position.getColumn()));
            points.add(new Point(position.getRow(), position.getColumn() + 1));
        }

        if(nextRotation == 3) {
            points.add(new Point(position.getRow()+1, position.getColumn()+1));
            points.add(new Point(position.getRow()-1, position.getColumn()+1));
            points.add(new Point(position.getRow(), position.getColumn()+1));
            points.add(new Point(position.getRow(), position.getColumn()));
        }

        return points;
    }
}
