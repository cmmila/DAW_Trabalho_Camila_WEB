package daw.trabalho.camila.web.converter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import daw.trabalho.camila.jpa.EntityManagerUtil;
import daw.trabalho.camila.model.Especialidade;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author camila
 */

@FacesConverter(value = "converterEspecialidade")
public class ConverterEspecialidade implements Serializable, Converter{

    @Override // metodo que converte da tela para o controle 
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        
       if (string == null || string.equals("Selecione um registro")){
           return null;
       }
       try{
           return EntityManagerUtil.getEntityManager().find(Especialidade.class, Integer.parseInt(string));
       }catch(Exception e){
           return null; 
       }
    }

    
    @Override // converte do controle para a tela 
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        
        if (o == null){
            return null;
            
        }
        Especialidade obj = (Especialidade) o;
        System.out.println(obj.getId().toString());
        return obj.getId().toString();
    }
    
}
