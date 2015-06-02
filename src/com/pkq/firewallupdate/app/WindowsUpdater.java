package com.pkq.firewallupdate.app;

public class WindowsUpdater extends Updater{
	public WindowsUpdater(){
		//updateShellFile = "..\\agentUpdate\\update.bat";
		updateShellFile = new String[2];
		updateShellFile[0] = "C:\\pkqfirewall\\agentUpdate\\update1.bat";
		updateShellFile[1] = "C:\\pkqfirewall\\agentUpdate\\update2.bat";
		versionFile = "C:\\pkqfirewall\\pkqfirewallAgent\\pf.version";
	}
}
