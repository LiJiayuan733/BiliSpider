package ltd.thzs.bili.sprider;
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
