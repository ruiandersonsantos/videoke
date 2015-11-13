package br.com.ruianderson.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ruianderson.modelo.Participante;
import br.com.ruianderson.service.NegocioException;
import br.com.ruianderson.service.ParticipanteService;
import br.com.ruianderson.util.jsf.FacesUtil;


@Named
@ViewScoped
public class ParticipanteBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private ParticipanteService participanteService;
	
	private Participante participante;
	
	public void salvar() {
		try {
			this.participanteService.salvar(participante);
			FacesUtil.addSuccessMessage("Participante salvo com sucesso!");
			
			this.limpar();
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	@PostConstruct
	public void init() {
		this.limpar();
	}
	
	public void limpar() {
		this.participante = new Participante();
	}

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	
}
