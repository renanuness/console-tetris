import com.github.kwhat.jnativehook.NativeHookException;
import game_engine.Game;

public class Main {
    public static void main(String[] args) throws Exception {
        Game game = null;
        try {
            game = new Game();
            game.start();
        } catch (NativeHookException e) {
            throw new Exception("", e);
        }catch (Exception e){
            throw new Exception("", e);

        }
    }
}

// Colisão lateral
// --> Entender a execução do código assíncrono do evento de click do botão
// Refactor para usar padrões

