package com.os.api.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.os.api.model.Status;

public class OrdemServicosFilter {
	
	private String equipamento;
	
	private Status status;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataRecebimentoDe;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataRecebimentoAte;

	
	public String getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(String equipamento) {
		this.equipamento = equipamento;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDate getDataRecebimentoDe() {
		return dataRecebimentoDe;
	}

	public void setDataRecebimentoDe(LocalDate dataRecebimentoDe) {
		this.dataRecebimentoDe = dataRecebimentoDe;
	}

	public LocalDate getDataRecebimentoAte() {
		return dataRecebimentoAte;
	}

	public void setDataRecebimentoAte(LocalDate dataRecebimentoAte) {
		this.dataRecebimentoAte = dataRecebimentoAte;
	}
	
	
	

}
