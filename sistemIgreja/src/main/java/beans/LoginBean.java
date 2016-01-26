package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import DAO.IgrejaDAO;
import DTO.UsuarioDTO;
import entidades.Igreja;
import entidades.Usuario;
import util.Util;

@ManagedBean
@ViewScoped
public class LoginBean {
	private Usuario usuario;
	private Igreja igreja;
	private List<Igreja> igrejas;

	@PostConstruct
	public void init() {
		this.usuario = new Usuario();
		listar();
	}
	public void listar(){
		this.igreja = new Igreja();
		this.igrejas = new ArrayList<Igreja>(new IgrejaDAO().obterTodos());
		for (Igreja igreja : igrejas) {
			System.out.println(igreja.getNome());
		}
	}

	public void fazerLogin() {
		Usuario verifica = new UsuarioDTO().buscar(usuario.getUsuario(),usuario.getSenha());
		if (verifica != null) {
			Util.criarObjetoDeSessao(verifica, "usuarioLogado");
			Util.redirecionarPagina("pages/index.xhtml");
		}
	}


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Igreja getIgreja() {
		return igreja;
	}

	public void setIgreja(Igreja igreja) {
		this.igreja = igreja;
	}

	public List<Igreja> getIgrejas() {
		return igrejas;
	}

	public void setIgrejas(List<Igreja> igrejas) {
		this.igrejas = igrejas;
	}



}
