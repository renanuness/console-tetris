package game_engine;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import java.util.ArrayDeque;
import java.util.function.Consumer;

public class GlobalKeyListener implements NativeKeyListener {
    ArrayDeque<Integer> commands;

    public GlobalKeyListener(ArrayDeque<Integer> commands){
        this.commands = commands;
    }

    public void nativeKeyPressed(NativeKeyEvent e){

        if(e.getKeyCode() == NativeKeyEvent.VC_LEFT){
            //onLeftKeyPressed.accept("LEFT");
            commands.add(-1);
        }

        if(e.getKeyCode() == NativeKeyEvent.VC_RIGHT){
            //onRightKeyPressed.accept("RIGHT");
            commands.add(1);
        }

        if(e.getKeyCode() == NativeKeyEvent.VC_UP){
            //onUpKeyPressed.accept("UP");
            commands.add(2);
        }

        if(e.getKeyCode() == NativeKeyEvent.VC_DOWN){
            //onDownKeyPressed.accept("DOWN");
            commands.add(0);
        }

        if(e.getKeyCode() == NativeKeyEvent.VC_ENTER){
            commands.add(10);
        }
    }
}
