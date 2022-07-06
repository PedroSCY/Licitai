package com.pig.licitai.service;

import com.pig.licitai.model.entity.Usuario;
import com.pig.licitai.model.util.ContaAcesso;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

public interface JwtService {

	public String gerarToken(Usuario Usuario);
	
	public Claims obterClaims(String token) throws ExpiredJwtException;
	
	public boolean isTokenValido(String token);
	
	public String obterLoginUsuario(String token);
}
