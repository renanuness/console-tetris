package game_engine;

public class GameState{
    private int score;
    private int level;
    private Board board;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    //private boolean ended;
    private State state;

    public GameState(State state, Board board){
        this.state = state;
        this.board = board;
        this.level = 1;
        this.score = 0;
    }


    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }



    public boolean isEnded() {
        return state == State.GAME_OVER;
    }

    public void setEnded() {
        this.state = State.GAME_OVER;
    }


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


}
