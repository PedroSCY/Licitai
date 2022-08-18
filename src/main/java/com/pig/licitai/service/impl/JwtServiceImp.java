package com.pig.licitai.service.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pig.licitai.model.entity.Fornecedor;
import com.pig.licitai.model.entity.OrgaoPublico;
import com.pig.licitai.model.entity.Usuario;
import com.pig.licitai.model.util.ContaAcesso;
import com.pig.licitai.service.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtServiceImp implements JwtService {

	@Value("${jtw.expiracao}")
	private String expiracao;

	@Value("${jwt.chave-assinatura}")
	private String chaveAssinatura;

	@Override
	public String gerarToken(Usuario usuario) {
		long exp = Long.valueOf(expiracao);
		LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(exp);
		Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
		Date data = Date.from(instant);

		String horaExpiracaoToken = dataHoraExpiracao.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"));


		String token = Jwts.builder()
				.setExpiration(data)
				.setSubject(usuario.getContaAcesso().getEmail())
				.claim("horaExpiracao", horaExpiracaoToken)
				.claim("usuario", limparUsuario(usuario))
				.signWith(SignatureAlgorithm.HS512, chaveAssinatura)
				.compact();

		return token;
	}

	@Override
	public Claims obterClaims(String token) throws ExpiredJwtException {
		return Jwts.parser().setSigningKey(chaveAssinatura).parseClaimsJws(token).getBody();
	}

	@Override
	public boolean isTokenValido(String token) {
		try {
			Claims claims = obterClaims(token);
			Date dataEx = claims.getExpiration();
			LocalDateTime dataExpiracao = dataEx.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			boolean dataHoraAtualIsAfterDataExpiracao = LocalDateTime.now().isAfter(dataExpiracao);
			return !dataHoraAtualIsAfterDataExpiracao;
		} catch (ExpiredJwtException e) {
			return false;
		}
	}

	@Override
	public String obterLoginUsuario(String token) {
		Claims claims = obterClaims(token);
		return claims.getSubject();
	}
	
	private Usuario limparUsuario(Usuario usuario) {
		Usuario usuarioSensivel = null;
		
		if (usuario instanceof Fornecedor) {
			
			Fornecedor fornecedor = (Fornecedor) usuario;

			usuarioSensivel = (Usuario) Fornecedor.builder()
										.cnpj(fornecedor.getCnpj())
										.nome(fornecedor.getNome())
										.telefone(fornecedor.getTelefone())									
										.atividade(fornecedor.getAtividade())
										.build();
		} else {
			
			OrgaoPublico orgaopublico = (OrgaoPublico) usuario;

			usuarioSensivel = (Usuario) OrgaoPublico.builder()
										.cnpj(orgaopublico.getCnpj())
										.nome(orgaopublico.getNome())
										.telefone(orgaopublico.getTelefone())
										.anuncioLicitacao(orgaopublico.getAnuncioLicitacao())
										.build();
		}
		
		return usuarioSensivel;
	}

}
