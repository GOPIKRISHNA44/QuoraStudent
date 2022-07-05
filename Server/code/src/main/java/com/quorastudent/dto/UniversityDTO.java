package com.quorastudent.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "universities")
public class UniversityDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String unvcode;

	public String getUnvcode() {
		return unvcode;
	}

	public void setUnvcode(String unvcode) {
		this.unvcode = unvcode;
	}

	public String getUnvname() {
		return unvname;
	}

	public void setUnvname(String unvname) {
		this.unvname = unvname;
	}

	public String getUnvaddress() {
		return unvaddress;
	}

	public void setUnvaddress(String unvaddress) {
		this.unvaddress = unvaddress;
	}

	public String getUnvmobile() {
		return unvmobile;
	}

	public void setUnvmobile(String unvmobile) {
		this.unvmobile = unvmobile;
	}

	private String unvname;
	private String unvaddress;
	private String unvmobile;

}
