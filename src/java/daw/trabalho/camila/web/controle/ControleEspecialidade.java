/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.trabalho.camila.web.controle;

import daw.trabalho.camila.model.Especialidade;
import daw.trabalho.camila.web.dao.EspecialidadeDAO;
import daw.trabalho.camila.web.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author camila
 */

@ManagedBean(name= "controleEspecialidade")
@SessionScoped
public class ControleEspecialidade implements Serializable {
    
   private EspecialidadeDAO dao; 
    private Especialidade objeto;
    

    public EspecialidadeDAO getDao() {
        return dao;
    }

    public void setDao(EspecialidadeDAO dao) {
        this.dao = dao;
    }

    public Especialidade getObjeto() {
        return objeto;
    }

    public void setObjeto(Especialidade objeto) {
        this.objeto = objeto;
    }

    public ControleEspecialidade() {
        dao = new EspecialidadeDAO();
    }
    
    public String listar(){
        
        return "/privado/especialidade/listar?faces-redirect=true";
    }
    
    public String novo (){
        
        objeto = new Especialidade();
        return "formulario?faces-redirect=true";
    }
    
    public String salvar(){
        
        if(dao.salvar(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
            return "listar?faces-redirect=true";
        
        }else{
            Util.mensagemErro(dao.getMensagem());
            return "formulario?faces-redirect=true";
            
        }
    }
    
    public String cancelar (){
        
        return "listar?faces-redirect=true";
    }
    
    public String editar (Integer id){
        
        objeto = dao.localizar(id);
        return "formulario?faces-redirect=true";
        
    }
  
    public void remover (Integer id){
        
        objeto = dao.localizar(id);
        if (dao.remover(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
        
        }else{
            
            Util.mensagemErro(dao.getMensagem());
        }
    }

    
}

