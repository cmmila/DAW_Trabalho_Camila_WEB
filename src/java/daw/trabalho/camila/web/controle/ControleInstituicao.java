/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.trabalho.camila.web.controle;

import daw.trabalho.camila.model.Instituicao;
import daw.trabalho.camila.web.dao.EspecialidadeDAO;
import daw.trabalho.camila.web.dao.InstituicaoDAO;
import daw.trabalho.camila.web.util.Util;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author camila
 */

@ManagedBean(name= "controleInstituicao")
@SessionScoped
public class ControleInstituicao implements Serializable {
    
   private InstituicaoDAO dao; 
    private Instituicao objeto;
    private EspecialidadeDAO daoEspecialidade;

    public InstituicaoDAO getDao() {
        return dao;
    }

    public void setDao(InstituicaoDAO dao) {
        this.dao = dao;
    }

    public Instituicao getObjeto() {
        return objeto;
    }

    public void setObjeto(Instituicao objeto) {
        this.objeto = objeto;
    }

    public ControleInstituicao() {
        dao = new InstituicaoDAO();
    }
    
//    SimpleDateFormat sdf = new SimpleDateFormat('dd/MM/yyyy');

        
    public String listar(){
        
        return "/privado/instituicao/listar?faces-redirect=true";
    }
    
    public String novo (){
        
        objeto = new Instituicao();
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

