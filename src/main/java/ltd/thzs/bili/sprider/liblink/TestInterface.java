package ltd.thzs.bili.sprider.liblink;

import com.sun.jna.Library;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;

public interface TestInterface extends Library {
    void helloWorld();
    void PTTest(Pointer a, Pointer b);
    void PIntTest(IntByReference a);
    void PIntChange(int a);
}
