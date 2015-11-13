package br.com.ruianderson.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ruianderson.modelo.FilaNoEvento;
import br.com.ruianderson.modelo.Participante;
import br.com.ruianderson.service.FilaNoEventoService;


@Named
@ViewScoped
public class FilaNoEventoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FilaNoEventoService filanoeventoservice;
	
	private FilaNoEvento fila;
	
	private List<FilaNoEvento> listaParaCantar;
	
	public void entrarNoEvento(Participante participante){
		
		this.fila = new FilaNoEvento();
		fila.setParticipante(participante);
		fila.setCodigomusica(participante.getPrimeiramusica());
		fila.setStatus(new Long(0));
		
		this.filanoeventoservice.adicionarNoEvento(fila);
		atualizaListaParacantar();
		
	}
	
	@PostConstruct
	public void init() {
		listaParaCantar = new ArrayList<FilaNoEvento>();
		atualizaListaParacantar();
	}

	private void atualizaListaParacantar() {
		listaParaCantar = this.filanoeventoservice.buscarFila();
		
	}

	public List<FilaNoEvento> getListaParaCantar() {
		return listaParaCantar;
	}

	public void setListaParaCantar(List<FilaNoEvento> listaParaCantar) {
		this.listaParaCantar = listaParaCantar;
	}

	
	
	
}
