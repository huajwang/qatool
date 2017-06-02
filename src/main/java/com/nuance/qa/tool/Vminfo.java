package com.nuance.qa.tool;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vminfo")
public class Vminfo {
	
	@Id
	@Column(name = "userid")
	private String userid;
	
	@Column(name = "vmip")
	private String vmip;
	
	protected Vminfo() {}
	
	public Vminfo(String userId, String vmip) {
		this.userid = userId;
		this.vmip = vmip;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getVmip() {
		return vmip;
	}

	public void setVmip(String vmip) {
		this.vmip = vmip;
	}
}
