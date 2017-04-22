package projeto.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

//rest
@XmlRootElement
//tag para o hibernate
@Entity
public class RespostaUsuario {
	
	// tag para indicar o id da classe
	@Id
	// gera��o de ids
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idRespostaUsuario;
	
	@OneToOne	
	private Usuario usuario;
	
	@OneToOne	
	private RespostaAtividade respostaAtividade;

	private Date dataResposta;

	public int getIdRespostaUsuario() {
		return idRespostaUsuario;
	}

	public void setIdRespostaUsuario(int idRespostaUsuario) {
		this.idRespostaUsuario = idRespostaUsuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public RespostaAtividade getRespostaAtividade() {
		return respostaAtividade;
	}

	public void setRespostaAtividade(RespostaAtividade respostaAtividade) {
		this.respostaAtividade = respostaAtividade;
	}

	public Date getDataResposta() {
		return dataResposta;
	}

	public void setDataResposta(Date dataResposta) {
		this.dataResposta = dataResposta;
	}
	
}