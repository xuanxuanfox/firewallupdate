package com.pkq.firewallupdate.app;

public class UpdateRequest {
	String msgType = Constant.Update; //消息类型
	int versionIndex; //版本索引，用于判断是否需要更新(当前版本小于此值的才需要更新)
	String version; //版本名称
	String ostype; //操作系统类型
	String downUrl; //下载地址
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public int getVersionIndex() {
		return versionIndex;
	}
	public void setVersionIndex(int versionIndex) {
		this.versionIndex = versionIndex;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getDownUrl() {
		return downUrl;
	}
	public void setDownUrl(String downUrl) {
		this.downUrl = downUrl;
	}
	public String getOstype() {
		return ostype;
	}
	public void setOstype(String optype) {
		this.ostype = optype;
	}
	
}
