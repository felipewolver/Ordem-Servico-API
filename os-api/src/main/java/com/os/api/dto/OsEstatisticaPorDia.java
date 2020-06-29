package com.os.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.os.api.model.Status;

public class OsEstatisticaPorDia {
	
	private LocalDate dataRecebimento;
	
	private Status status;
	
	private BigDecimal valor;
	
	
	public OsEstatisticaPorDia(LocalDate dataRecebimento, Status status, BigDecimal valor) {
		super();
		this.dataRecebimento = dataRecebimento;
		this.status = status;
		this.valor = valor;
	}

	public LocalDate getDataRecebimento() {
		return dataRecebimento;
	}
	
	public void setDataRecebimento(LocalDate dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public BigDecimal getValor() {
		return valor;
	}
	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	
}
