package org.bit.linc.plugins.plugins;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bit.linc.commons.exception.SysimpleException;

public class PluginsUtil {
	
	public static String getPluginsDir(){
		return System.getProperty("sysimple.plugins.dir");
	}
	
	
	/**
	 * get plugins' list
	 * @return
	 * @throws SysimpleException 
	 */
	public static List<Plugin> getPluginList() throws SysimpleException{		
		List<Plugin> pluginsList=new ArrayList<Plugin>();
		//get the array of files or dirs
		String pluginPath=getPluginsDir();
		String [] files=new File(pluginPath).list();
		//get the dirs with plugin(s) as suffix among array
		for(int i=0;i<files.length;i++){
			if(files[i].endsWith("plugins")||files[i].endsWith("plugin")){
				Plugin plugin=new Plugin(files[i]);
				pluginsList.add(plugin);
			}
		}
		return pluginsList;
	}
	
	/**
	 * get pluginNames' list
	 * @return
	 * @throws SysimpleException
	 */
	public static List<String> getPluginNameList() throws SysimpleException{
		List<String> pluginsList=new ArrayList<String>();
		//get the array of files or dirs
		String [] files=new File(getPluginsDir()).list();
		//get the dirs with plugin(s) as suffix among array
		for(int i=0;i<files.length;i++){
			if(files[i].endsWith("plugins")||files[i].endsWith("plugin")){
				pluginsList.add(files[i]);
			}
		}
		return pluginsList;
	}
	

}