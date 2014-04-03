package it.cascino.h8.entity;

import java.io.Serializable;
import javax.persistence.*;
import org.jboss.logging.Logger;
import java.sql.Timestamp;
import java.util.Date;

/**
 * The persistent class for the tipi database table.
 * 
 */
@Entity
@NamedQuery(name = "Tipi.findAll", query = "SELECT t FROM Tipi t")
public class Tipi implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
	private static Logger log = Logger.getLogger(Tipi.class.getName());

	private Integer id;
	private String nome;
	private String descrizione;
	private Tipi tipoPadre;
	private Date updtime;
	
	public Tipi(){
	}
	
	public Tipi(Integer id, String nome, String descrizione, Tipi tipoPadre, Timestamp updtime){
		super();
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.tipoPadre = tipoPadre;
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
	
//	 @Column(name = "padre")
	// uni-directional one-to-one association to Tipi
	@OneToOne(cascade = {CascadeType.MERGE})//.PERSIST})
	@JoinColumn(name = "padre")
	public Tipi getTipoPadre(){
		return this.tipoPadre;
	}
	
	public void setTipoPadre(Tipi tipi){
		this.tipoPadre = tipi;
	}
	
	@Transient
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdtime(){
		return this.updtime;
	}
	
	public void setUpdtime(Date updtime){
		this.updtime = updtime;
	}
	
	public String toString(){
//		log.info("toString: " + id);
		if(id == null){
			log.warn("toString: " + "id==null");
			return "1";
		}
		return Integer.toString(id);
	}
	
	@Override
	public boolean equals(Object obj){
//		log.info("equals: " + obj);
		if(obj instanceof Tipi){
			if(this.id == ((Tipi)obj).id){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	
	public int hashCode(){
		log.info("hashCode: ");
		return id != null ? this.getClass().hashCode() + id.hashCode() : super.hashCode();
	}
}