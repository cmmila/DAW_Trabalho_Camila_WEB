/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.trabalho.camila.web.dao;

import daw.trabalho.camila.jpa.EntityManagerUtil;
import daw.trabalho.camila.model.Professor;
import daw.trabalho.camila.web.util.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author camila
 */
public class ProfessorDAO implements Serializable {
    
    private String mensagem = "";
    private EntityManager em;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public ProfessorDAO() {
        
        em = EntityManagerUtil.getEntityManager();
    }
    
    public List<Professor>getLista(){
        
        return em.createQuery("from Professor order by nome").getResultList();
    }
    
    public boolean salvar (Professor obj){
        try{
            em.getTransaction().begin();
            if (obj.getId()== null){
                em.persist(obj);
            
            }else{
                em.merge(obj);
            }
            em.getTransaction().commit();
            mensagem = "Objeto foi persistido com sucesso!";
            return true;
        
        }catch (Exception e ){
            
            if (em.getTransaction().isActive()==false){
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            mensagem = "Erro ao persistir objeto";
                Util.getMensagemErro(e);
            return false;
        }
              
    }
    
    public boolean remover (Professor obj){
        try{
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
            mensagem = "Objeto removido com sucesso!";
            return true;
        }catch (Exception e ){
            
            if (em.getTransaction().isActive()==false){
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            mensagem = "Erro ao remover objeto";
                Util.getMensagemErro(e);
            return false;
        }
    }
    
    public Professor localizar (Object id){
        
        return em.find(Professor.class, id);
    }
      
}
