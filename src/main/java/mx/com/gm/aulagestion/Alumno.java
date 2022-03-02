
package mx.com.gm.aulagestion;


public class Alumno extends Persona{
   
    private long alumnoId;
    private Long cursoId;
    
    public Alumno(){
    }
    
    public Alumno(String nombre, String apellido, Long edad, Long personaId, Long cursoId, String dni){
        super(nombre, apellido, edad, dni);
        this.cursoId = cursoId;
       
    }

   

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }

    @Override
    public String toString() {
        return "Alumno{" + "alumnoId=" + alumnoId + ", cursoId=" + cursoId + '}';
    }

    
    
    
}
