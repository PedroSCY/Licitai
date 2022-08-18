package com.pig.licitai.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pig.licitai.api.dto.ContaAcessoDTO;
import com.pig.licitai.api.dto.FornecedorDTO;
import com.pig.licitai.api.dto.TokenDTO;
import com.pig.licitai.exceptions.ErroAutenticacao;
import com.pig.licitai.exceptions.RegraDeNegocioException;
import com.pig.licitai.model.entity.Fornecedor;
import com.pig.licitai.model.entity.Usuario;
import com.pig.licitai.service.ContaAcessoService;
import com.pig.licitai.service.FornecedorService;
import com.pig.licitai.service.JwtService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

	private final FornecedorService fornecedorservice;
	private final ContaAcessoService contaAcessoService;
	private final JwtService jwtService;
	
	@PostMapping("/autenticar")
	public ResponseEntity<?> autenticar( @RequestBody ContaAcessoDTO dto) {
		try {
			contaAcessoService.autenticar(dto.getEmail(), dto.getSenha());
			
			if(fornecedorservice.obterPorEmail(dto.getEmail()) instanceof Fornecedor) {
				Fornecedor fornecedorAutenticado = fornecedorservice.obterPorEmail(dto.getEmail());
				
				String token = jwtService.gerarToken((Usuario) fornecedorAutenticado);
				TokenDTO tokenDTO = new TokenDTO(token);
				
				return ResponseEntity.ok(tokenDTO);
			}
			return null;
		} catch (ErroAutenticacao e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	@PostMapping
	public ResponseEntity salvarForcecedor( @RequestBody FornecedorDTO dto) {
		
		
		Fornecedor fornecedor = Fornecedor.builder()
				.nome(dto.getNome())
				.contaAcesso(dto.getContaAcesso())
				.cnpj(dto.getCnpj())
				.telefone(dto.getTelefone())
				.atividade(dto.getAtividade())
				.build();
	
		try {
			Fornecedor fornecedorSalvo = fornecedorservice.salvarFornecedor(fornecedor);
			return new ResponseEntity(fornecedorSalvo , HttpStatus.CREATED);
		} catch (RegraDeNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}


}
