package br.com.ruianderson.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;



import br.com.ruianderson.modelo.Noticia;
import br.com.ruianderson.modelo.StatusNoticia;
import br.com.ruianderson.modelo.TipoNoticia;
import br.com.ruianderson.service.NoticiaService;
import br.com.ruianderson.util.jsf.FacesUtil;

@Named
@ViewScoped
public class NoticiaBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private NoticiaService noticiaService;
	
	private Noticia noticia;
	private List<TipoNoticia> tipoNoticias;
	private List<StatusNoticia> statusNoticias;
	
	@PostConstruct
	public void init() {
		this.limpar();
		this.tipoNoticias =  Arrays.asList(TipoNoticia.values());
		this.statusNoticias =  Arrays.asList(StatusNoticia.values());
	}
	
	public void limpar() {
		this.noticia = new Noticia();
	}
	
	
	public void salvar(){
		noticiaService.salvar(noticia);
		this.limpar();
		FacesUtil.addSuccessMessage("Noticia salva com sucesso!");
	}
	

	public Noticia getNoticia() {
		return noticia;
	}

	public void setNoticia(Noticia noticia) {
		this.noticia = noticia;
	}

	public List<TipoNoticia> getTipoNoticias() {
		return tipoNoticias;
	}

	public void setTipoNoticias(List<TipoNoticia> tipoNoticias) {
		this.tipoNoticias = tipoNoticias;
	}

	public List<StatusNoticia> getStatusNoticias() {
		return statusNoticias;
	}

	public void setStatusNoticias(List<StatusNoticia> statusNoticias) {
		this.statusNoticias = statusNoticias;
	}
	
	
	
}
