package it.cascino.dao;

import java.util.List;
import org.primefaces.model.TreeNode;
import it.cascino.model.Tipi;

public interface TipiDao{
	void salva(TreeNode n);
	
	void aggiorna(TreeNode n);
	
	void elimina(TreeNode n);
	
	List<Tipi> getAll();
	
	Tipi getTipoDaIdTipo(Integer idTipo);
	
	String getNomeTipoDaIdArticolo(Integer idArticolo);
	
	List<Integer> getIdTipoDaLikeNomeTipo(String nomeTipo);
	
	Boolean getTipoDiscendeDaTipo(Integer idTipo, Integer idTipoPadre);
}
