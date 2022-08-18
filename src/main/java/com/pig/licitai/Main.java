package com.pig.licitai;

import com.pig.licitai.model.entity.Fornecedor;
import com.pig.licitai.model.entity.Usuario;
import com.pig.licitai.model.util.Atividade;
import com.pig.licitai.model.util.ContaAcesso;
import com.pig.licitai.model.util.Endereco;
import com.pig.licitai.service.impl.FornecedorServiceImpl;

public class Main {

	
	public static void main(String[]args ) {
		
		ContaAcesso ca = ContaAcesso.builder().email("user@user.com").senha("senha").isAdmin(true).build();
		
		Atividade at = Atividade.builder().nomeAtividade("Atividade 1 ").codigoAtividade("215354832-4565").build();
		
		Endereco en = Endereco.builder().bairro("Centro").complemento("nenhum").cidade("Prata").numero(10).rua("Rua 1").uf("PB").build();
		
		Fornecedor f = Fornecedor.builder().cnpj("11111111111111").contaAcesso(ca).nome("Fornecedo 1").telefone(0000000000).build();
	
		System.out.println(f.toString());
		
		
	}
}
