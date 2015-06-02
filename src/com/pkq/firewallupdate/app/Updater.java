package com.pkq.firewallupdate.app;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pkq.firewallupdate.util.SystemUtil;
import com.pkq.firewallupdate.util.FileOp;
 
public abstract class Updater {
	Logger logger = LoggerFactory.getLogger(Updater.class);
	String version;
	int versionIndex;
	String[] updateShellFile;
	String versionFile;
	
	void readVersionInfo()throws Exception{

		String vs = FileOp.readTextFile(versionFile);
		logger.debug("readVersionInfo:" + vs);
		String[] versions = vs.split(",");
		versionIndex = Integer.parseInt(versions[0].trim());
		version = versions[1].trim();
	}
	/**
	 * 更新新版本
	 * 
	 * @param request
	 */
	public String updateAgent(UpdateRequest request) throws Exception {
		readVersionInfo(); // 读取当前版本信息
		String message;
		//如果当前版本大于要更新的版本，不更新
		if( versionIndex >= request.getVersionIndex()){
			message = "need not update, versionidex new";
			logger.debug(message);
		}
		//如果不是本操作系统类型，不更新
		else if(!UpdateAgentApp.optype.equals(request.getOstype())){
			message = "need not update, ostype different";
			logger.debug(message);
		}else{
			for(int i=0;i<updateShellFile.length;i++){
				String strCmd = updateShellFile[i] + " " + request.getDownUrl();
				message = "do update: " + strCmd;
				logger.debug(message);
				SystemUtil.runCommandPrint(strCmd);
				Thread.sleep(1000); //暂停1秒，等待运行完毕
			}
		}
		return "";
	}

}
