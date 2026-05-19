public abstract class Shape implements Piece{
    protected int rotation;
    protected Point position;
    protected BoardSize boardSize;

    public Shape(int row, int column, BoardSize boardSize){
        this.rotation = 0;
        this.boardSize = boardSize;
        this.position = new Point(row, column);
    }

    public void rotate() {
        if(!canRotate()) return;
        rotation++;
        if(rotation > 3){
            rotation = 0;
        }
    }

    public boolean canRotate() {
        var points = getPointsNextRotation();
        for(var point: points){
            var resultingColumn = point.getColumn();
            var resultingRow = point.getRow();
            if(resultingColumn < 0 || resultingColumn >= boardSize.getColumns()) {
                return false;
            }

            if(resultingRow < 0 || resultingRow >= boardSize.getRows()) {
                return false;
            }
        }
        return true;
    }

    public boolean canMove(int dir) {
        var points = getPoints();
        for(var point: points){
            point.move(0, dir);
            var resultingColumn = point.getColumn();
            if(resultingColumn < 0 || resultingColumn >= boardSize.getColumns()) {
                return false;
            }
        }
        return true;
    }

    public boolean canMoveDown() {
        var points = getPoints();
        for(var point: points){
            point.move(1, 0);
            var resultingRow = point.getRow();
            if(resultingRow >= boardSize.getColumns()) {
                return false;
            }
        }
        return true;
    }
}
