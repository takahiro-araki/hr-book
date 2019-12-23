package com.example.demo.domain;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author yuma.watanebe
 *
 */
public class Orders {
	private Integer orderId;
	private Integer humanId;
	private Integer orderStatus;
	private Integer actStatus;
	private Integer versionNum;
	private String register;
	private Date registDate;
	private String renewer;
	private LocalDate renewDate;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getHumanId() {
		return humanId;
	}

	public void setHumanId(Integer humanId) {
		this.humanId = humanId;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getActStatus() {
		return actStatus;
	}

	public void setActStatus(Integer actStatus) {
		this.actStatus = actStatus;
	}

	public Integer getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	public String getRenewer() {
		return renewer;
	}

	public void setRenewer(String renewer) {
		this.renewer = renewer;
	}

	public LocalDate getRenewDate() {
		return renewDate;
	}

	public void setRenewDate(LocalDate renewDate) {
		this.renewDate = renewDate;
	}

	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", humanId=" + humanId + ", orderStatus=" + orderStatus + ", actStatus="
				+ actStatus + ", versionNum=" + versionNum + ", register=" + register + ", registDate=" + registDate
				+ ", renewer=" + renewer + ", renewDate=" + renewDate + "]";
	}

}
