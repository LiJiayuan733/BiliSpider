package ltd.thzs.bili.sprider.liblink;

import com.sun.jna.Library;
import com.sun.jna.Pointer;

public interface SharedMemory extends Library {
    //查
    int GetKey(int skey);
    int GetMemory();
    int GetMemoryOfSize(int size);
    int GetSharedMemory();
    Pointer GetByte();
    int GetSize();

    //改
    void SetMemory(int index, Pointer object);

    //删
    void ClearMemoryAll();
    void ClearMemory(int index,int size);
    void RemoveMemory();

    void Close();

}
