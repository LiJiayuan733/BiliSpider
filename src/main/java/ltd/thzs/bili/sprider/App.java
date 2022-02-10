package ltd.thzs.bili.sprider;

import com.sun.jna.Native;
import ltd.thzs.bili.sprider.liblink.Test4Interface;
import ltd.thzs.bili.sprider.utils.SHMUtils;

import java.util.logging.Logger;

/**
 * 	Spider
 */
public class App 
{
    /**
     * 调用外部c动态库
     * 为啥用忽略( > _ < )
     * public interface TestInterface extends Library {
     *      void helloWorld();
     * }
     * public static TestInterface instance = Native.load(ConfigData.DIR_BASE+"/lib/test.dylib", TestInterface.class);
     */
    public static Test4Interface instance = Native.load(ConfigData.DIR_BASE+"/lib/test4.dylib", Test4Interface.class);
    public static void main( String[] args )
    {
        Step.loadData(args);
        Step.CheckDir();
        Step.LoadLib();
        Step.AddFWPD(4);
        SHMUtils shm=new SHMUtils();
        shm.createSharedMemory(2015);
        byte[] s=shm.Get(15);
        shm.Close();
        shm.Remove();
        Logger.getLogger("Test").info(new String(s));
        if(ConfigData.createGUI) {
            Step.createGUI();
        }
    }
}
