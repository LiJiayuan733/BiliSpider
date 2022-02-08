package ltd.thzs.bili.sprider;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * 	Spider
 */
public class App 
{
    public interface TestInterface extends Library {
        void helloWorld();
    }
    public static TestInterface instance = Native.load(ConfigData.DIR_BASE+"/lib/test.dylib", TestInterface.class);
    public static void main( String[] args )
    {
        Step.loadData(args);
        Step.CheckDir();
        Step.LoadLib();
        Step.AddFWPD(4);
        if(ConfigData.createGUI) {
            Step.createGUI();
        }
    }
}
