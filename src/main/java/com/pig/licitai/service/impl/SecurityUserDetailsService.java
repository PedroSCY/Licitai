package com.pig.licitai.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pig.licitai.model.repository.ContaAcessoRepository;
import com.pig.licitai.model.util.ContaAcesso;



@Service
public class SecurityUserDetailsService implements UserDetailsService{
	
	private ContaAcessoRepository contaAcessoRepository;

	public SecurityUserDetailsService(ContaAcessoRepository contaAcessoRepository) {
		this.contaAcessoRepository = contaAcessoRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		ContaAcesso contaAcessoEncontrada = contaAcessoRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email não cadastrado"));
	
		return User.builder()
				.username(contaAcessoEncontrada.getEmail())
				.password(contaAcessoEncontrada.getSenha())
				.roles("USER")
				.build();
	}

}
