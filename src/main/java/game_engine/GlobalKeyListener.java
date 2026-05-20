package game_engine;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import java.util.function.Consumer;

public class GlobalKeyListener implements NativeKeyListener {
    private Consumer onLeftKeyPressed;
    private Consumer onRightKeyPressed;
    private Consumer onDownKeyPressed;
    private Consumer onUpKeyPressed;
    private Consumer onEnterPressed;

    public GlobalKeyListener(Consumer leftKeyPressed, Consumer rightKeyPressed, Consumer upKeyPressed, Consumer downKeyPressed, Consumer enterPressed){
        this.onLeftKeyPressed = leftKeyPressed;
        this.onRightKeyPressed = rightKeyPressed;
        this.onDownKeyPressed = downKeyPressed;
        this.onUpKeyPressed = upKeyPressed;
        this.onEnterPressed = enterPressed;
    }

    public void nativeKeyPressed(NativeKeyEvent e){
        if(e.getKeyCode() == NativeKeyEvent.VC_LEFT){
            onLeftKeyPressed.accept("LEFT");
        }

        if(e.getKeyCode() == NativeKeyEvent.VC_RIGHT){
            onRightKeyPressed.accept("RIGHT");
        }

        if(e.getKeyCode() == NativeKeyEvent.VC_UP){
            onUpKeyPressed.accept("UP");
        }

        if(e.getKeyCode() == NativeKeyEvent.VC_DOWN){
            onDownKeyPressed.accept("DOWN");
        }

        if(e.getKeyCode() == NativeKeyEvent.VC_ENTER){
            onEnterPressed.accept("ENTER");
        }
    }
}
