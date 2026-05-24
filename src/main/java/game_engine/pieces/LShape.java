package game_engine.pieces;

import game_engine.Board;
import game_engine.Point;
import game_engine.BoardSize;
import java.util.ArrayList;
import java.util.List;

public class LShape extends Shape{
    public LShape(int row, int column, BoardSize boardSize){
        super(row, column, boardSize);
    }

    public void update(){
        this.position.move(1, 0);
    }

    public void move(Board board, int dir){
        if(dir == 0){
            if(!canMoveDown(board)) return;
            this.position.move(1, 0);
            return;
        }

        if(!canMove(board, dir)) return;
        this.position.move(0, dir);
    }

    @Override
    public List<Point> getPoints() {
        List<Point> points = new ArrayList<>();

        var nextRotation = (rotation + 1) % 4;
        if(rotation == 0) {
            points.add(new Point(position.getRow()-1, position.getColumn()-1));
            points.add(new Point(position.getRow(), position.getColumn() - 1));
            points.add(new Point(position.getRow(), position.getColumn()));
            points.add(new Point(position.getRow(), position.getColumn() + 1));
        }

        if(rotation == 1) {
            points.add(new Point(position.getRow()+1, position.getColumn()));
            points.add(new Point(position.getRow()-1, position.getColumn()));
            points.add(new Point(position.getRow()-1, position.getColumn()+1));
            points.add(new Point(position.getRow(), position.getColumn()));
        }


        if(rotation == 2) {
            points.add(new Point(position.getRow() + 1, position.getColumn() + 1));
            points.add(new Point(position.getRow(), position.getColumn()-1));
            points.add(new Point(position.getRow(), position.getColumn()));
            points.add(new Point(position.getRow(), position.getColumn() + 1));
        }

        if(rotation == 3) {
            points.add(new Point(position.getRow()+1, position.getColumn()+1));
            points.add(new Point(position.getRow()-1, position.getColumn()+1));
            points.add(new Point(position.getRow(), position.getColumn()+1));
            points.add(new Point(position.getRow()+1, position.getColumn()));
        }

        return points;
    }

    @Override
    public List<Point> getPointsNextRotation() {
        List<Point> points = new ArrayList<>();

        var nextRotation = rotation + 1;

        if(nextRotation == 0) {
            points.add(new Point(position.getRow()-1, position.getColumn()-1));
            points.add(new Point(position.getRow(), position.getColumn() - 1));
            points.add(new Point(position.getRow(), position.getColumn()));
            points.add(new Point(position.getRow(), position.getColumn() + 1));
        }

        if(nextRotation == 1) {
            points.add(new Point(position.getRow()+1, position.getColumn()));
            points.add(new Point(position.getRow()-1, position.getColumn()));
            points.add(new Point(position.getRow()-1, position.getColumn()+1));
            points.add(new Point(position.getRow(), position.getColumn()));
        }


        if(nextRotation == 2) {
            points.add(new Point(position.getRow() + 1, position.getColumn() + 1));
            points.add(new Point(position.getRow(), position.getColumn()-1));
            points.add(new Point(position.getRow(), position.getColumn()));
            points.add(new Point(position.getRow(), position.getColumn() + 1));
        }

        if(nextRotation == 3) {
            points.add(new Point(position.getRow()+1, position.getColumn()+1));
            points.add(new Point(position.getRow()-1, position.getColumn()+1));
            points.add(new Point(position.getRow(), position.getColumn()+1));
            points.add(new Point(position.getRow()+1, position.getColumn()));
        }

        return points;
    }

    /*
    A peça tem formato:
    piece = int[][]
    piece[0][0] = 1
    piece[0][1] = 1
    piece[0][2] = 1
    piece[1][2] = 1

    a peça tem uma rotação:
    0   90   180
    * */
}
