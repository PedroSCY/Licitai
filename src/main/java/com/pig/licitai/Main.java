package com.pig.licitai;

import com.pig.licitai.model.entity.Fornecedor;
import com.pig.licitai.model.entity.Usuario;
import com.pig.licitai.model.util.Atividade;
import com.pig.licitai.model.util.ContaAcesso;
import com.pig.licitai.model.util.Endereco;

public class Main {

	public static void main(String[]args ) {
		
		ContaAcesso ca = ContaAcesso.builder().email("user@user.com").senha("senha").isAdmin(true).build();
		
		Atividade at = Atividade.builder().nome("Atividade 1 ").codigo("215354832-4565").build();
		
		Endereco en = Endereco.builder().bairro("Centro").cep(00000000).complemento("nenhum").logradouro("Logradouro 1").numero(10).build();
		
		Fornecedor f = Fornecedor.builder().atividade(at).cnpj("11111111111111").contaAcesso(ca).endereco(en).nome("Fornecedo 1").telefone(0000000000).build();
	
		System.out.println(f.toString());
	}
}
