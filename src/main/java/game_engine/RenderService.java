package game_engine;

public class RenderService {
    // para renderizar eu preciso do board
    // eu preciso do score
    // eu preciso do level
    // precisa de uma referência a um objeto que tenha como entregar essas informações
    private GameState gameState;

    public RenderService(GameState gameState){
        this.gameState = gameState;
    }

    public void render() throws Exception {
        switch (gameState.getState()){
            case State.GAME:
                renderGame();
            break;
            case START_MENU:
                renderStartMenu();
                break;
            case State.GAME_OVER:
                renderGameOver();
                break;
            default:
                throw new Exception("Game should be in one state");
        }
    }

    private void renderStartMenu() {
        System.out.println("START MENU");
    }

    public void renderGame(){
        // HEADER
        System.out.print(String.format("\033[%d;%dH", 0, 0));
        System.out.print("Score: " + gameState.getScore());

        System.out.print(String.format("\033[%d;%dH", 0, 20));
        System.out.print("Level: "+ 1);
        System.out.println();

        // UPPER BOARD
        System.out.print(String.format("\033[%d;%dH", 2, 1));
        System.out.print("\u250C");
        for(int i = 0; i < this.gameState.getBoard().getBoardSizeColumns(); i++){
            System.out.print("\u2500");
        }
        System.out.print("\u2510");
        System.out.println();

        // BOARD CONTENT
        for(int i = 1; i < this.gameState.getBoard().getBoardSizeRow(); i++){
            System.out.print("\u2502");
            for(int j = 0; j < this.gameState.getBoard().getBoardSizeColumns(); j++){
                String c = "\u23F9";
                if(gameState.getBoard().getPointContent(i,j) == 0){
                    c = "\u25A1";
                }
                System.out.print(c);
            }
            System.out.print("\u2502");
            System.out.println();
        }

        //System.out.print(String.format("\033[%d;%dH", 2, 1));
        System.out.print("\u2514");
        for(int i = 0; i < this.gameState.getBoard().getBoardSizeColumns(); i++){
            System.out.print("\u2500");
        }
        System.out.print("\u2518");
        System.out.println();
    }

    private void renderGameOver(){
        System.out.println("Game Over!");
        System.out.println("Pontuação Final: " + gameState.getScore());
    }
}

