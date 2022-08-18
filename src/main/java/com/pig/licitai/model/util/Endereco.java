package com.pig.licitai.model.util;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "TB_ENDERECO")
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Size(min = 3, max = 60, message = "A rua deve conter algo entre 3 e 60 caracteres.")
	@Column(name = "RUA")
	private String rua;
	
	@Positive(message = "O número do endereço deve ser maior que 0.")
	@Column(name = "NUMERO")
	private Integer numero;
	
	@Size(min = 3, max = 60, message = "O bairro deve conter algo entre 3 e 60 caracteres.")
	@Column(name = "BAIRRO")
	private String bairro;
	
	@Size(min = 2, max = 3, message = "Selecione um estado válido.")
	@Column(name = "UF")
	private String uf;
	
	@NotBlank
	@Size(min = 3, max = 60, message = "A cidade deve conter algo entre 3 e 60 caracteres.")
	@Column(name = "CIDADE")
	private String cidade;
	
	@NotBlank
	@Size(min = 8, max = 9, message = "O CEP deve conter 8 caracteres.")
	@Column(name = "CEP")
	private String cep;
	
	@Column(name = "COMPLEMENTO")
	private String complemento;
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Endereco newEndereco = (Endereco) obj;
		if ((id == null && newEndereco.id != null) || !id.equals(newEndereco.id))
			return false;
		
		return true;
	
	}
	
	@Override
	public String toString() {
		return "Endereço: {"
			+ "\n	id: " + id + ","
			+ "\n	Rua: " + rua + ","
			+ "\n	Numero: " + numero + ", "
			+ "\n	Bairro: " + bairro + ", "
			+ "\n	UF: " + uf + ", "
			+ "\n	Cidade: " + cidade + ", "
			+ "\n	CEP: " + cep + ", "
			+ "\n	Complemento: " + complemento + ", "
			+ "\n}";
	}

}
