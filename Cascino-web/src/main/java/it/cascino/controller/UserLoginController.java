package it.cascino.controller;

import it.cascino.dao.UserLoginDao;
import it.cascino.model.Userloginrole;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.jboss.logging.Logger;

@Named
@SessionScoped
public class UserLoginController implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
	@Inject
	private Logger log;
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private UserLoginDao userLoginDao;
	
	private Userloginrole userLoginoSel = new Userloginrole();
	
	private String esito;
	
	private String user;
	private String password;
	
	private Boolean canAccess = false;
	private Boolean confirmRegistration = false;
	
	public String getUser(){
		log.info("tmpDEBUGtmp: " + "> " + "getUser(" + ")");
		log.info("tmpDEBUGtmp: " + "< " + "getUser");
		return user;
	}
	
	public void setUser(String user){
		log.info("tmpDEBUGtmp: " + "> " + "setUser(" + user + ")");
		this.user = user.toLowerCase();
		log.info("tmpDEBUGtmp: " + "< " + "setUser");
	}
	
	public String getPassword(){
		log.info("tmpDEBUGtmp: " + "> " + "getPassword(" + ")");
		log.info("tmpDEBUGtmp: " + "< " + "getPassword");
		return password;
	}
	
	public void setPassword(String password){
		log.info("tmpDEBUGtmp: " + "> " + "setPassword(" + password + ")");
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		this.password = Integer.toString(result);
		log.info("tmpDEBUGtmp: " + "< " + "setPassword");
	}
	
	public void salva(){
		log.info("tmpDEBUGtmp: " + "> " + "salva(" + ")");
		userLoginDao.salva(userLoginoSel);
		if((userLoginoSel != null) && (userLoginoSel.getId() != null)){
			esito = "utente " + userLoginoSel.getLogin() + " e' stato registrato";
			showGrowlInsMessage();
		}else{
			esito = "utente " + userLoginoSel.getLogin() + " NON e' stato registrato";
			showGrowlErrorMessage();
		}
		log.info("tmpDEBUGtmp: " + "< " + "salva");
	}
	
	public void aggiorna(){
		log.info("tmpDEBUGtmp: " + "> " + "aggiorna(" + ")");
		log.info("tmpDEBUGtmp: " + "< " + "aggiorna");
	}
	
	public void elimina(){
		log.info("tmpDEBUGtmp: " + "> " + "elimina(" + ")");
		log.info("tmpDEBUGtmp: " + "< " + "elimina");
	}
	
	public String getEsito(){
		log.info("tmpDEBUGtmp: " + "> " + "getEsito(" + ")");
		log.info("tmpDEBUGtmp: " + "< " + "getEsito");
		return esito;
	}
	
	private void showGrowlInfoMessage(String message){
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlUpdMessage(){
		String message = "Aggiornato con successo - " + esito + " >" + userLoginoSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlInsMessage(){
		String message = "Inserito con successo - " + esito + " >" + userLoginoSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlDelMessage(){
		String message = "Eliminato con successo - " + esito + " >" + userLoginoSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo", message));
		log.info(message);
	}
	
	private void showGrowlErrorMessage(){
		String message = "Operazione fallita - " + esito + " >" + userLoginoSel + "<";
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", message));
		log.error(message);
	}
	
	public Boolean getCanAccess(){
		log.info("tmpDEBUGtmp: " + "> " + "getCanAccess(" + ")");
		// if(canAccess == false){
		canAccess = userLoginDao.canAccess(user, password);
		// }
		if((canAccess != null) && (canAccess)){
			esito = "utente " + user + " e' autorizzato";
			showGrowlInfoMessage(esito);
		}else{
			esito = "utente " + user + " NON e' autorizzato (o non e' inserito)";
			showGrowlErrorMessage();
		}
		log.info("tmpDEBUGtmp: " + "< " + "getCanAccess");
		return canAccess;
	}
	
	public void setCanAccess(Boolean canAccess){
		log.info("tmpDEBUGtmp: " + "> " + "setCanAccess(" + canAccess + ")");
		this.canAccess = canAccess;
		log.info("tmpDEBUGtmp: " + "< " + "setCanAccess");
	}
	
	public Boolean getConfirmRegistration(){
		log.info("tmpDEBUGtmp: " + "> " + "getConfirmRegistration(" + ")");
		
		if(confirmRegistration == false){
			userLoginoSel = new Userloginrole();
			userLoginoSel.setLogin(user);
			userLoginoSel.setPassword(password);
			userLoginoSel.setRole("none");
			
			salva();
			
			if(userLoginoSel.getId() != null){
				confirmRegistration = true;
			}
		}
		log.info("tmpDEBUGtmp: " + "< " + "getConfirmRegistration");
		return confirmRegistration;
	}
	
	public void setConfirmRegistration(Boolean confirmRegistration){
		log.info("tmpDEBUGtmp: " + "> " + "setConfirmRegistration(" + confirmRegistration + ")");
		this.confirmRegistration = confirmRegistration;
		log.info("tmpDEBUGtmp: " + "< " + "setConfirmRegistration");
	}
}