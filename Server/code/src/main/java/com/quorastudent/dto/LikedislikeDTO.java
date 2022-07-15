package com.quorastudent.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "likedislike")
public class LikedislikeDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long parentid;
	private int updwnvt;
	private String ctype;
	private Date updatedon;
	private Long userid;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the parentid
	 */
	
	/**
	 * @return the updnvt
	 */
	
	
	/**
	 * @return the updatedon
	 */
	public Date getUpdatedon() {
		return updatedon;
	}
	/**
	 * @param updatedon the updatedon to set
	 */
	public void setUpdatedon(Date updatedon) {
		this.updatedon = updatedon;
	}
	/**
	 * @return the userid
	 */
	public Long getUserid() {
		return userid;
	}
	/**
	 * @param userid the userid to set
	 */
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getCtype() {
		return ctype;
	}
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	public Long getParentid() {
		return parentid;
	}
	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}
	public int getUpdwnvt() {
		return updwnvt;
	}
	public void setUpdwnvt(int updwnvt) {
		this.updwnvt = updwnvt;
	}
	

}
