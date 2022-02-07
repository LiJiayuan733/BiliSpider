package ltd.thzs.bili.sprider;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Properties;

import org.apache.commons.io.output.FileWriterWithEncoding;

public class ConfigData {
	public static boolean createGUI = true;
	public static int hasGUIView = -1;
	public static final int DEFALUT_GUIVIEW = -1;

	public static String Cookie = null;

	public static boolean Debug = false;

	public static Properties pro = null;
	public static String ConfigPath = null;
	public static final String DIR_BASE = "/Applications/BiliSP.app/Contents/app";
	public static final String CONFIG_DEF_PATH = DIR_BASE + "/lib/BKConfig.properties";

	public static void proInit(String FilePath) {
		ConfigData.pro = new Properties();
		try {
			ConfigData.pro.load(new FileReader(FilePath));
			ConfigPath = FilePath;

			Cookie = pro.getProperty("Cookie");
		}  catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void proSave(String savePath) {
		File f = new File(savePath);
		if(pro==null) {
			pro=new Properties();
			pro.put("Cookie", Cookie);
		}
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			pro.store(new FileWriterWithEncoding(new File(savePath), Charset.defaultCharset()), "Spider Config");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
