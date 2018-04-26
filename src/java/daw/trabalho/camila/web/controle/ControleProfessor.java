/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.trabalho.camila.web.controle;

import daw.trabalho.camila.model.Professor;
import daw.trabalho.camila.web.dao.EspecialidadeDAO;
import daw.trabalho.camila.web.dao.ProfessorDAO;
import daw.trabalho.camila.web.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author camila
 */

@ManagedBean(name= "controleProfessor")
@SessionScoped
public class ControleProfessor implements Serializable {
    
   private ProfessorDAO dao; 
    private Professor objeto;
    private EspecialidadeDAO daoEspecialidade;

    public ProfessorDAO getDao() {
        return dao;
    }

    public void setDao(ProfessorDAO dao) {
        this.dao = dao;
    }

    public Professor getObjeto() {
        return objeto;
    }

    public void setObjeto(Professor objeto) {
        this.objeto = objeto;
    }

    public ControleProfessor() {
        dao = new ProfessorDAO();
    }

    public EspecialidadeDAO getDaoEspecialidade() {
        return daoEspecialidade;
    }

    public void setDaoEspecialidade(EspecialidadeDAO daoEspecialidade) {
        this.daoEspecialidade = daoEspecialidade;
    }
    
    public String listar(){
        
        return "/privado/professor/listar?faces-redirect=true";
    }
    
    public String novo (){
        
        objeto = new Professor();
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

