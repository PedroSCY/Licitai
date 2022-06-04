package com.pig.licitai.model.util;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "TB_CONTA")
public class ContaAcesso {

	@Id
	@Email(message = "Imforme um email valido.")
	@NotNull(message = "O e-mail deve ser informado.")
	@Column(name = "EMAIL")
	private String email;
	
	@Size(min = 8, message = "A senha deve ter, no minimo, 8 caracteres.")
	@Column(name = "SENHA", columnDefinition = "TEXT")
	private String senha;
	
	@NotNull(message = "Informe se esta conta é de administrador")
	@Column(name = "ADMINISTRADOR")
	private boolean isAdmin;
	
}
