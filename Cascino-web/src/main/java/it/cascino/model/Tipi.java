package it.cascino.model;

import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.*;
import org.jboss.logging.Logger;
import java.sql.Timestamp;

/**
 * The persistent class for the tipi database table.
 * 
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "Tipi.findAll", query = "SELECT t FROM Tipi t"),
		@NamedQuery(name = "Tipi.findById", query = "SELECT t FROM Tipi t WHERE t.id = :id") // ,
// @NamedQuery(name = "Tipi.findByIdTipo", query = "SELECT f FROM Foto f WHERE f.id = (SELECT t.foto FROM Tipi t WHERE t.id = :id)")
})
public class Tipi implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
	@Inject
	private Logger log;
	
	private Integer id;
	private String nome;
	private String descrizione;
	private Tipi tipoPadre;
	private Integer idFoto;
	private Timestamp updtime;
	
	public Tipi(){
	}
	
	public Tipi(Integer id, String nome, String descrizione, Tipi tipoPadre, Integer idFoto, Timestamp updtime){
		super();
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.tipoPadre = tipoPadre;
		this.idFoto = idFoto;
		this.updtime = updtime;
	}
	
	@Id
	@SequenceGenerator(name = "TIPI_ID_GENERATOR", sequenceName = "TIPI_ID_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIPI_ID_GENERATOR")
	public Integer getId(){
		return this.id;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public String getDescrizione(){
		return this.descrizione;
	}
	
	public void setDescrizione(String descrizione){
		this.descrizione = descrizione;
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	// @Column(name = "padre")
	// uni-directional one-to-one association to Tipi
	@OneToOne(cascade = {CascadeType.MERGE})
	// .PERSIST})
	@JoinColumn(name = "padre")
	public Tipi getTipoPadre(){
		return this.tipoPadre;
	}
	
	public void setTipoPadre(Tipi tipi){
		this.tipoPadre = tipi;
	}
	
	@Column(name = "foto")
	public Integer getIdFoto(){
		return idFoto;
	}
	
	public void setIdFoto(Integer idFoto){
		this.idFoto = idFoto;
	}
	
	@Transient
	@Temporal(TemporalType.TIMESTAMP)
	public Timestamp getUpdtime(){
		return this.updtime;
	}
	
	public void setUpdtime(Timestamp updtime){
		this.updtime = updtime;
	}
	
	@Override
	public String toString(){
		if(log != null){
			log.info("tmpDEBUGtmp: " + "> " + "toString(" + ")");
			log.info("tmpDEBUGtmp: " + "id: " + id);
		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(this.getClass().getName().substring(this.getClass().getName().lastIndexOf(".") + 1));
		stringBuilder.append("[");
		if(id != null){
			stringBuilder.append("id=" + id).append(", ");
			stringBuilder.append("nome=" + nome).append(", ");
			stringBuilder.append("descrizione=" + descrizione).append(", ");
			stringBuilder.append("tipoPadre=" + ((tipoPadre != null) ? tipoPadre.getId() : "")).append(", ");
			stringBuilder.append("idFoto=" + idFoto);
		}else{
			stringBuilder.append("id=1");
		}
		stringBuilder.append("]");
		if(log != null){
			log.info("tmpDEBUGtmp: " + "< " + "toString");
		}
		return stringBuilder.toString();
	}
	
	@Override
	public boolean equals(Object obj){
		if(log != null){
			log.info("tmpDEBUGtmp: " + "> " + "equals(" + obj + ")");
			log.info("tmpDEBUGtmp: " + "id: " + id);
		}
		if(obj instanceof Tipi){
			if(this.id == ((Tipi)obj).id){
				return true;
			}else{
				return false;
			}
		}
		if(log != null){
			log.info("tmpDEBUGtmp: " + "< " + "equals");
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		if(log != null){
			log.info("tmpDEBUGtmp: " + "> " + "hashCode(" + ")");
			log.info("tmpDEBUGtmp: " + "id: " + id);
		}
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + ((tipoPadre == null) ? 0 : tipoPadre.hashCode());
		result = prime * result + ((idFoto == null) ? 0 : idFoto.hashCode());
		if(log != null){
			log.info("tmpDEBUGtmp: " + "< " + "hashCode");
		}
		return result;
	}
	
	public int compareTo(Tipi t){
		if(log != null){
			log.info("tmpDEBUGtmp: " + "> " + "compareTo(" + t + ")");
			log.info("tmpDEBUGtmp: " + "id: " + id);
			log.info("compareTo: " + this.id + "-" + t.id);
		}
		if(this.id < t.id){
			return -1;
		}else if(this.id > t.id){
			return 1;
		}
		if(log != null){
			log.info("tmpDEBUGtmp: " + "< " + "compareTo");
		}
		return 0;
	}
}