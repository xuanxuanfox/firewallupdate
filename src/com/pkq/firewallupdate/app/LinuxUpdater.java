package com.pkq.firewallupdate.app;

public class LinuxUpdater extends Updater{
	public LinuxUpdater(){
		updateShellFile = new String[1];
		updateShellFile[0] =  "/opt/agentUpdate/update.sh";
		versionFile = "/opt/pkqfwagent/pf.version";
	}
}
