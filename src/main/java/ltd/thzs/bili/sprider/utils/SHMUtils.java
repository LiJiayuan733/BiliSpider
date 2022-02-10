package ltd.thzs.bili.sprider.utils;


import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import ltd.thzs.bili.sprider.ConfigData;
import ltd.thzs.bili.sprider.liblink.SharedMemory;

import java.util.logging.Logger;

public class SHMUtils {
    public Logger log=Logger.getLogger(getClass().toString());
    public SharedMemory shm;
    public SHMUtils(){
        shm=Native.load(ConfigData.DIR_BASE+"/lib/SharedMemory.dylib",SharedMemory.class);
    }
    public void createSharedMemory(int skey){
        int i=shm.GetKey(skey);
        if(i!=1){
            log.info("获取KEY失败"+i);
            return;
        }
        i=shm.GetMemory();
        if(i!=1){
            log.info("获取Memory失败"+i);
            return;
        }
        i=shm.GetSharedMemory();
        if(i!=1){
            log.info("获取ShareMemory失败"+i);
        }
    }
    /**
     * @param size 页数 一页=4KB
     * */
    public void createSharedMemoryOfSize(int skey,int size){
        int i=shm.GetKey(skey);
        if(i!=1){
            log.info("获取KEY失败: "+i);
            return;
        }
        i=shm.GetMemoryOfSize(size*4096);
        if(i!=1){
            log.info("获取Memory失败"+i);
            return;
        }
        i=shm.GetSharedMemory();
        if(i!=1){
            log.info("获取ShareMemory失败"+i);
        }
    }
    public void connectSharedMemory(int skey){
        createSharedMemoryOfSize(skey,1);
    }
    public void Clear(){
        shm.ClearMemoryAll();
    }
    public void Clear(int Start,int End){
        shm.ClearMemory(Start,End-Start);
    }
    /**
     * <h3 color="#FF0000">Remove前记得退出(Close)</h3>
     * */
    public void Remove(){
        shm.RemoveMemory();
    }
    public void Close(){
        shm.Close();
    }
    public byte[] Get(){
        return Get(0,shm.GetSize());
    }
    public byte[] Get(int Size){
        return  Get(0,Size);
    }
    public byte[] Get(int Start,int Size){
        byte[] bytes;
        Pointer pointer=shm.GetByte();
        bytes=pointer.getByteArray(Start,Size);
        return bytes;
    }
    public void Set(int index,byte[] text){
        Pointer pointer=new Memory(text.length);
        pointer.write(0,text,0,text.length);
        shm.SetMemory(index,pointer);
    }
    public void Set(int index,String text){
        Set(index,text.getBytes());
    }
}
