package com.os.api.dto;

import java.math.BigDecimal;

import com.os.api.model.Status;

public class OsEstatisticaPorStatus {
	
	private Status status;
	
	private BigDecimal total;

	public OsEstatisticaPorStatus(Status status, BigDecimal total) {
		super();
		this.status = status;
		this.total = total;
	}
  
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	
	

}
