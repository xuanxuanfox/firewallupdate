package com.pkq.firewallupdate.app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.TimerTask;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import com.alibaba.fastjson.JSON;

public class UpdateTask extends TimerTask {
	Logger logger = LoggerFactory.getLogger(UpdateTask.class);
	Updater updater;

	public UpdateTask(Updater updater) {
		this.updater = updater;
	}

	public void run() {
		doUpdate();
	}

	void doUpdate() {
		try {
			String url = UpdateAgentApp.newestVersionUrl + "bean.ostype=" + UpdateAgentApp.optype;
			logger.debug("UpdateTask.doUpdate(), url:" + url);
			String jsonString = doHttpRequest(url);
			logger.debug("UpdateTask.doUpdate(), http return jsonString:" + jsonString);

			// Map<String,Object> map =
			// (Map<String,Object>)JSON.parse(jsonString);
			UpdateRequest request = JSON.parseObject(jsonString,
					UpdateRequest.class);
			updater.updateAgent(request);
		} catch (Exception e) {
			logger.error("exception:", e);
		}
	}

	protected String doHttpRequest(String urlPath) throws Exception {
		URL url = new URL(urlPath);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.connect();
		InputStream inputStream = connection.getInputStream();
		// 对应的字符编码转换
		Reader reader = new InputStreamReader(inputStream, "UTF-8");
		BufferedReader bufferedReader = new BufferedReader(reader);
		String str = null;
		StringBuffer sb = new StringBuffer();
		while ((str = bufferedReader.readLine()) != null) {
			sb.append(str);
		}
		reader.close();
		connection.disconnect();
		return sb.toString();
	}
}
