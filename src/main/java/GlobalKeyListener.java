import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import java.util.function.Consumer;

public class GlobalKeyListener implements NativeKeyListener {
    private Consumer onLeftKeyPressed;
    private Consumer onRightKeyPressed;
    private Consumer onDownKeyPressed;
    private Consumer onUpKeyPressed;

    public GlobalKeyListener(Consumer leftKeyPressed, Consumer rightKeyPressed, Consumer upKeyPressed, Consumer downKeyPressed){
        this.onLeftKeyPressed = leftKeyPressed;
        this.onRightKeyPressed = rightKeyPressed;
        this.onDownKeyPressed = downKeyPressed;
        this.onUpKeyPressed = upKeyPressed;
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
    }
}
