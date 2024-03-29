package com.pig.licitai.model.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.boot.context.properties.ConstructorBinding;

import com.pig.licitai.model.util.Atividade;
import com.pig.licitai.model.util.ContaAcesso;
import com.pig.licitai.model.util.Endereco;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
public abstract class Usuario {

	@Column(name = "NOME")
	private String nome;
	
	@Id
	@Column(name = "CNPJ")
	private String cnpj;
	
	@Column(name = "TELEFONE")
	private long telefone;
	
	@OneToOne
	private ContaAcesso contaAcesso;
	
	@OneToOne
	private Endereco endereco;
}
