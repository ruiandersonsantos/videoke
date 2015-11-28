package br.com.ruianderson.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.ruianderson.dao.NoticiaDAO;
import br.com.ruianderson.modelo.Noticia;

public class NoticiaService implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private NoticiaDAO noticiaDao;
	
	public void salvar(Noticia noticia){
		noticiaDao.salvar(noticia);
	}
	
	public List<Noticia> buscarTodas(){
		return noticiaDao.buscarTodas();
	}
}
