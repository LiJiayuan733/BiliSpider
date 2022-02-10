package ltd.thzs.bili.sprider.liblink;

import com.sun.jna.Library;
import com.sun.jna.Pointer;

public interface Test4Interface extends Library {
    void play(Pointer url);
}
