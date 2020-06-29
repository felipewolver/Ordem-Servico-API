package com.os.api.repository.projection;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.os.api.model.Categoria;
import com.os.api.model.Pessoa;

public class OrdemServicoProjection {
	
	private Long os;
	private Pessoa pessoa;
	private Categoria categoria;
	
	private String equipamento;
	private String descricao;
	private String defeito;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataRecebimento;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataEntrega;
	
	private BigDecimal valor;
	private String laudoTecnico;
	private String garantia;
	
	
	public OrdemServicoProjection(Long os, Pessoa pessoa, Categoria categoria, String equipamento, String descricao,
			String defeito, LocalDate dataRecebimento, LocalDate dataEntrega, BigDecimal valor, String laudoTecnico,
			String garantia) {
		super();
		this.os = os;
		this.pessoa = pessoa;
		this.categoria = categoria;
		this.equipamento = equipamento;
		this.descricao = descricao;
		this.defeito = defeito;
		this.dataRecebimento = dataRecebimento;
		this.dataEntrega = dataEntrega;
		this.valor = valor;
		this.laudoTecnico = laudoTecnico;
		this.garantia = garantia;
	}


	public Long getOs() {
		return os;
	}


	public void setOs(Long os) {
		this.os = os;
	}


	public Pessoa getPessoa() {
		return pessoa;
	}


	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}


	public Categoria getCategoria() {
		return this.categoria;
	}


	public void setServicos(Categoria categoria) {
		this.categoria = categoria;
	}


	public String getEquipamento() {
		return equipamento;
	}


	public void setEquipamento(String equipamento) {
		this.equipamento = equipamento;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getDefeito() {
		return defeito;
	}


	public void setDefeito(String defeito) {
		this.defeito = defeito;
	}


	public LocalDate getDataRecebimento() {
		return dataRecebimento;
	}


	public void setDataRecebimento(LocalDate dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}


	public LocalDate getDataEntrega() {
		return dataEntrega;
	}


	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}


	public BigDecimal getValor() {
		return valor;
	}


	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}


	public String getLaudoTecnico() {
		return laudoTecnico;
	}


	public void setLaudoTecnico(String laudoTecnico) {
		this.laudoTecnico = laudoTecnico;
	}


	public String getGarantia() {
		return garantia;
	}


	public void setGarantia(String garantia) {
		this.garantia = garantia;
	}
	
	
	
	

}
