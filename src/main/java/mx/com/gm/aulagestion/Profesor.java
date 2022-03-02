
package mx.com.gm.aulagestion;

import javax.lang.model.SourceVersion;


public class Profesor extends Persona{
    private Long cursoId;
    private Long personaId;
    
    public Profesor(){
    }
    
    public Profesor(String nombre, String apellido, Long edad, String dni, Long cursoId){
        super(nombre, apellido, edad, dni);
        this.cursoId = cursoId;
      
    }
    

    public Long getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Long personaId) {
        this.personaId = personaId;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }

    
    @Override
    public String toString() {
        return "Profesor{" + "personaId=" + personaId + ", cursoId=" + cursoId + '}';
    }


}
