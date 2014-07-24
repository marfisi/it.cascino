package it.cascino.model;

import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.*;
import org.jboss.logging.Logger;
import java.sql.Timestamp;

/**
 * The persistent class for the userloginrole database table.
 * 
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "Userloginrole.findAll", query = "SELECT u FROM Userloginrole u"),
		@NamedQuery(name = "Userloginrole.findByLoginPsw", query = "SELECT u FROM Userloginrole u WHERE u.login = :user and u.password = :password"),
		@NamedQuery(name = "Userloginrole.countByLoginPsw", query = "SELECT count(u) FROM Userloginrole u WHERE u.login = :user and u.password = :password")
})
public class Userloginrole implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
	@Inject
	private Logger log;
	
	private Integer id;
	private String login;
	private String password;
	private String role;
	private Timestamp updtime;
	
	public Userloginrole(){
	}
	
	public Userloginrole(Integer id, String login, String password, String role, Timestamp updtime){
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.role = role;
		this.updtime = updtime;
	}
	
	@Id
	@SequenceGenerator(name = "USERLOGINROLE_ID_GENERATOR", sequenceName = "USERLOGINROLE_ID_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERLOGINROLE_ID_GENERATOR")
	public Integer getId(){
		return this.id;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public String getLogin(){
		return this.login;
	}
	
	public void setLogin(String login){
		this.login = login;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	// grant
	public String getRole(){
		return this.role;
	}
	
	public void setRole(String role){
		this.role = role;
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
		// log.info("toString: " + id);
		if(id == null){
			// log.warn("toString: " + "id==null");
			return "unknown";
		}
		return "login = " + login + ", password = " + password + ", role = " + role;
	}
	
	@Override
	public boolean equals(Object obj){
		// log.info("equals: " + obj);
		if(obj instanceof Userloginrole){
			if(this.id == ((Userloginrole)obj).id){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		log.info("hashCode: ");
		return id != null ? this.getClass().hashCode() + id.hashCode() : super.hashCode();
	}
}