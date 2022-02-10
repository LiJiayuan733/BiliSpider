package ltd.thzs.bili.sprider;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;

import java.nio.charset.Charset;

public class UtilsInstance {
    public static Pointer buildStrPointer(String str) {
        byte[] bytes;
        bytes = str.getBytes(Charset.defaultCharset());
        Pointer pointer = new Memory(bytes.length);
        pointer.write(0, bytes, 0, bytes.length);
        return pointer;
    }
}
