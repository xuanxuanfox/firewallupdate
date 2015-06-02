package com.pkq.firewallupdate.app;

import java.io.InputStream;
import java.util.Properties;
import java.util.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pkq.firewallupdate.util.OSinfo;

public class UpdateAgentApp {
	public static String newestVersionUrl;
	public static int newestVersionInteval;
	public static String optype = "";
	Updater updater;
	static Logger logger = LoggerFactory.getLogger(UpdateAgentApp.class);

	public static void main(String[] args) {
		UpdateAgentApp app = new UpdateAgentApp();
		try {
			app.init();
		} catch (Exception e) {
			logger.error("start UpdateAgentApp error",e);
			return;
		}
		// ----------启动定时器
		Timer timer;
		long NO_DELAY = 0;
		timer = new Timer("更新代理通知定时器", true);
		timer.schedule(new UpdateTask(app.updater), NO_DELAY, newestVersionInteval * 1000);
		try {
			while (true) {
				//主线程一直sleep，定时器一直运行
				Thread.sleep(100);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 初始化
	void init() throws Exception {
		// 获取操作系统类型
		if (OSinfo.isWindows()) {
			optype = Constant.Optype_windows;
			updater = new WindowsUpdater();
		} else if (OSinfo.isLinux()) {
			optype = Constant.Optype_linux;
			updater = new LinuxUpdater();
		} else {
			updater = null;
		}
		// -
		readConfig();
	}

	void readConfig() throws Exception {
		Properties prop = new Properties();

		String logmsg;
		String pn = "/config.properties";
		InputStream is = this.getClass().getResourceAsStream(pn);
		prop.load(is);
		is.close();
		newestVersionUrl = prop.getProperty("newestVersionUrl").trim();
		newestVersionInteval = Integer.parseInt(prop.getProperty(
				"newestVersionInteval").trim());
		logmsg = String.format(
				"newestVersionInteval:%d second, newestVersionUrl:%s",
				newestVersionInteval, newestVersionUrl);
		logger.info(logmsg);
	}

}
