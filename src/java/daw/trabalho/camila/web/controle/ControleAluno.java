/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.trabalho.camila.web.controle;

import daw.trabalho.camila.model.Aluno;
import daw.trabalho.camila.web.dao.AlunoDAO;
import daw.trabalho.camila.web.util.Util;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author camila
 */

@ManagedBean(name= "controleAluno")
@SessionScoped
public class ControleAluno implements Serializable {
    
   private AlunoDAO dao; 
    private Aluno objeto; 
    public SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public AlunoDAO getDao() {
        return dao;
    }

    public void setDao(AlunoDAO dao) {
        this.dao = dao;
    }

    public Aluno getObjeto() {
        return objeto;
    }

    public void setObjeto(Aluno objeto) {
        this.objeto = objeto;
    }

    public ControleAluno() {
        dao = new AlunoDAO();
    }
    
    public String listar(){
        
        return "/privado/aluno/listar?faces-redirect=true";
    }
    
    public String novo (){
        
        objeto = new Aluno();
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

