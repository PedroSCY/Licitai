package com.pig.licitai.model.util;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pig.licitai.exceptions.RegraDeNegocioException;
import com.pig.licitai.model.entity.AnuncioLicitacao;
import com.pig.licitai.model.entity.EmailModel;
import com.pig.licitai.model.entity.Fornecedor;
import com.pig.licitai.model.enuns.StatusEmail;
import com.pig.licitai.service.FornecedorService;
import com.pig.licitai.service.impl.EmailService;

@Component
public class GerenciadorNotificacao {

	@Autowired
	private EmailService emailService;
	 
	@Autowired
	private FornecedorService fornecedorService;
	 
	@Async
	public void LancarAnuncioLicitacao(AnuncioLicitacao anuncio, String tipoEnvio) {
		if(tipoEnvio.equals("PorAtividade")) {
			lancarAnuncioPorAtividade(anuncio);
		}else if(tipoEnvio.equals("PorSegmento")) {
			lancarAnuncioPorSegmento(anuncio);
		}else {
			throw new RegraDeNegocioException("Tipo de Envio Invalido");
		}
	}
	
	@Async
	private void lancarAnuncioPorAtividade(AnuncioLicitacao anuncio) {
		ArrayList<Fornecedor> fornecedores = fornecedorService.obterPorAtividade(anuncio.getAtividade().getCodigoAtividade());
		if(!fornecedores.isEmpty()) {
			lancarEmMassa(fornecedores, anuncio);			
		}
	}
	
	@Async
	private void lancarAnuncioPorSegmento(AnuncioLicitacao anuncio) {
		ArrayList<Fornecedor> fornecedores = fornecedorService.obterPorSegmento((anuncio.getAtividade().getSiglaSegmento()));

		if(!fornecedores.isEmpty()) {
			lancarEmMassa(fornecedores, anuncio);			
		}
	}
	
	@Scheduled(fixedDelay = 30000) //21600000
	public void reenvioPosErro() {
		try {
			ArrayList<EmailModel> emailSemExito = emailService.obterPorStatus(StatusEmail.ERROR.ordinal());
			
			if(!emailSemExito.isEmpty()) {
				for(EmailModel email : emailSemExito) {
					if(email.getResolut() < 3) {
						System.out.println("RELANCAMENTO: " + LancarEmail(email).toString());
					}else {
						emailService.deletarPorId(email);
						System.err.println("CANCELAMENTO: " + email.toString());
					}
				}
			}
		} catch (Exception e) {
			return;
		}
		
		
	}
	
	@Async
	private void lancarEmMassa(ArrayList<Fornecedor> fornecedores, AnuncioLicitacao anuncio) {
		for(Fornecedor fornecedor: fornecedores) {
			EmailModel email = createEmailModel(anuncio.getTitulo(), fornecedor.getContaAcesso().getEmail(), anuncio.getTitulo(), anuncio.getText(), fornecedor.getNome());	
			System.out.println("LANCAMENTO: " + LancarEmail(email));			
		}
	}
	
	@Async
	private EmailModel LancarEmail(EmailModel email) {
		return emailService.sendEmail(email);
	}
	
	@Async
	private EmailModel createEmailModel(String ownerRef,String emailTo, String subject,String text, String nomeDestinatario) {
		
		EmailModel email = EmailModel.builder()
				.ownerRef(ownerRef)
				.emailFrom("licitaiservice@gmail.com")
				.emailTo(emailTo)
				.subject(subject)
				.resolut(0)
				.text(text)
				.nomeDestinatario(nomeDestinatario).build();
		
		return email;
	}
	
	
//	@Scheduled(fixedDelay = 10000)
//	public void teste() {
//		System.out.println(StatusEmail.ERROR);
//		System.out.println(StatusEmail.ERROR.toString());
//		System.out.println(StatusEmail.ERROR.ordinal());
//		System.out.println(StatusEmail.SENT);
//		System.out.println(StatusEmail.SENT.toString());
//		System.out.println(StatusEmail.SENT.ordinal());
//		
//	}
	
}
