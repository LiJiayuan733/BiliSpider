package ltd.thzs.bili.sprider;

import java.awt.Color;
import java.io.*;
import java.net.URL;
import java.util.logging.Logger;

import javax.swing.UIManager;

import com.formdev.flatlaf.intellijthemes.FlatDarkFlatIJTheme;

import ltd.thzs.bili.sprider.JComponent.MenuJFrame;

public class Step {
	public static void loadData(String[] args) {
		if(new File(ConfigData.CONFIG_DEF_PATH).exists()) {
			ConfigData.proInit(ConfigData.CONFIG_DEF_PATH);
		}
		for(int i=0;i<args.length;i++) {
        	switch (args[i]) {
			case "--nogui":
				ConfigData.createGUI=false;
				break;
			case "-C":
				ConfigData.Cookie=args[i+1];
				i++;
				break;
			case "--debug":
				ConfigData.Debug=true;
				break;
			case "-Config":
				i++;
				ConfigData.proInit(args[i]);
				break;
			default:
				break;
			}
        }
	}
	public static void createGUI() {
		FlatDarkFlatIJTheme.setup();
		UIManager.put("FrameTopBorderColor", new Color(102,204,255));
		
		new MenuJFrame();
	}
	//备用
	protected static String[] LibPath=new String[]{
			"/lib/libopencv_java455.dylib",
			"/lib/test.dylib"
	};
	protected static String[] DirPath=new String[]{
			"/lib"
	};
	public static void CheckDir(){
		for (String i:DirPath){
			File f=new File(ConfigData.DIR_BASE+i);
			if(!f.exists()){
				f.mkdirs();
			}
		}
	}
	public static void LoadLib(){
		for(String i:LibPath){
			File f=new File(ConfigData.DIR_BASE+i);
			if(!f.exists()){
				URL url=Step.class.getResource(i);
				if(url!=null){
					try {
						f.createNewFile();
						FileOutputStream fw=new FileOutputStream(f);
						InputStream is=Step.class.getResourceAsStream(i);
						byte[] temp=new byte[10240];
						int read;
						while((read = is.read(temp)) != -1){
							fw.write(temp,0,read);
						}
						fw.close();
						is.close();
						System.load(f.getAbsolutePath());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else{
					Logger.getLogger("[LOAD LIB] ").warning("未找到: "+i);
				}
			}else{
				System.load(f.getPath());
			}
		}
	}
	public static void AddFWPD(int size){
		while(size!=-1){
			Thread t=new Thread(new JFrameWebPicDownloader());
			t.setDaemon(true);
			t.start();
			size--;
		}
	}
}
