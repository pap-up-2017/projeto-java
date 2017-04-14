package projeto.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import projeto.business.AprovacaoParticipanteBusiness;
import projeto.dao.FactoryDao;
import projeto.dao.InterfaceDao;
import projeto.dao.InterfaceProjetoDao;
import projeto.entity.AprovacaoParticipante;
import projeto.entity.Projeto;
import projeto.entity.Usuario;
import projeto.util.Datas;

@Path("/projetorest")
public class ProjetoRest {

	// get para puxar todos os projetos do banco..
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Projeto> listarProjetos() {
		InterfaceProjetoDao<Projeto> dao = FactoryDao.createProjetoDao();
		return dao.listar();	
	}
	
	// post para cadastro de um novo projeto.
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/postcad")
	public void cadastrarProjeto(Projeto projeto) {	
		InterfaceProjetoDao<Projeto> dao = FactoryDao.createProjetoDao();
		if (projeto.getIdProjeto() < 1){
			// Registro a quantidade de participantes mais o organizador
			projeto.setParticipantes(projeto.getUsuarios().size()+1);
			projeto.setDataCriacao(Datas.retornaDataAtual());
			projeto.setStatus("Aberto");
			dao.salvar(projeto);		
		}
	}
	
	// post para alterar um projeto no banco ... 
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/postalt")
	public void alterarProjeto(Projeto projeto) {
		InterfaceProjetoDao<Projeto> dao = FactoryDao.createProjetoDao();
		if (projeto.getIdProjeto() > 0){
			dao.alterar(projeto);
		}
	}
	
	// post para excluir um dado no banco ... 
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/postdel")
	public void deletarProjeto(Projeto projeto) {
		InterfaceProjetoDao<Projeto> dao = FactoryDao.createProjetoDao();
		if (projeto.getIdProjeto() > 0){
			dao.excluir(projeto);
		}
	}
	
	// get para puxar todos os meus projetos do banco..
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/user/{id}")
	public List<Projeto> listarMeusProjetos(@PathParam("id") String id) {
		InterfaceProjetoDao<Projeto> dao = FactoryDao.createProjetoDao();
		return dao.listarMeusProjetos(id);
	}
	
	// get para puxar um projeto por id.
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Projeto getProjetoById(@PathParam("id") int id) {
		InterfaceProjetoDao<Projeto> dao = FactoryDao.createProjetoDao();
		return dao.getObjById(id);	
	}
	
	// post solicitar participação no projeto 
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		@Path("/solAprov/{idUsuario}/{idProjeto}")
		public void solicitar(@PathParam("idUsuario") int idUsuario, @PathParam("idProjeto") int idProjeto) {

			InterfaceDao<Usuario> daoUser = FactoryDao.createUsuarioDao();
			Usuario u = daoUser.getObjById(idUsuario);
			InterfaceProjetoDao<Projeto> daoProj = FactoryDao.createProjetoDao();
			Projeto p = daoProj.getObjById(idProjeto);	
			AprovacaoParticipanteBusiness AprovPart = new AprovacaoParticipanteBusiness();
			AprovPart.solicitar(u, p);
			//AprovacaoParticipante AprovPart = new AprovacaoParticipante();
			//AprovPart.setIdUsuarioSolicitante(u.getIdUsuario());
			//AprovPart.setIdProjeto(p.getIdProjeto());
			//AprovPart.setDataCriacao(Datas.retornaDataAtual());
			//InterfaceDao<AprovacaoParticipante> dao = FactoryDao.createAprovacaoParticipanteDao();
			//dao.salvar(AprovPart);
		
		}
	
}
