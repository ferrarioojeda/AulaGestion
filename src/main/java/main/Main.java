
package main;

import mx.com.gm.aulagestion.Persona;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import mx.com.gm.aulagestion.Curso;
import mx.com.gm.aulagestion.Profesor;


public class Main {
    public static void main(String[] args) {
    
       //Persona persona = new Persona("pepe", "argento", 32L, "30100200");
       //System.out.println("hola pepe");
        
     // Alumno alumno = new Alumno("Carla", "Lopez", 20L, "45100300", "" )
       Statement stmt = conexion();
       buscarAlumnosYProfesorDeUnCurso(stmt,"ingles");
//       Curso curso = new Curso("Ingles");
//    
//       Long materia = buscarCursoPorNombre(stmt, "matematicas");
//       Profesor profesor = new Profesor("Pablo", "Aguirre", 50L, "18333111", materia); 
//       
//       System.out.println(buscarCursoPorNombre(stmt, "matematicas"));
//       insertProfesor(stmt, profesor, "matematicas");
//       
//       insertCurso(stmt, curso, profesor);
      /* try{
        ResultSet rs=stmt.executeQuery("select * from persona;");
        Persona persona1= null;
        
        while(rs.next()){
        persona1 = new Persona(rs.getString("nombre"), rs.getString("apellido"), rs.getLong("edad"), rs.getString("dni"));

        }
        System.out.println(persona1.toString());
        }catch(Exception e){
       
       }*/
       
    }
    
    public static Statement conexion(){
      try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/aulagestion","root","1234");
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);           
          return stmt;  
        }
        catch(Exception e)
        {
            System.out.println(e);
        } 
      return null;
    } 

    private static void insertPersona(Statement stmt, Persona persona) {
        try{
         String sql = "INSERT INTO persona (nombre, apellido, edad, dni) VALUES ('"+persona.getNombre()+"', 'argento', 32, '30100200')";
         stmt.executeUpdate(sql);
        }catch(Exception e){
         
         }
        
    }
    
    private static void insertProfesor(Statement stmt, Profesor profesor, String nombre) {
        try{
            String sql = "INSERT INTO persona (nombre, apellido, edad, dni) VALUES ('"+profesor.getNombre()+"', '"+profesor.getApellido()+"', '"+profesor.getEdad()+"', '"+profesor.getDni()+"')";
            stmt.executeUpdate(sql);
            
            sql = "SELECT id FROM aulagestion.persona WHERE dni ='"+profesor.getDni()+"'";
            ResultSet rs=stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getInt("id"));
                int resultado = rs.getInt("id");
                Long materia = buscarCursoPorNombre(stmt, nombre);
                sql = "INSERT INTO profesor (id_persona, id_curso) VALUES ('"+resultado+"', '"+materia+"')";
            stmt.executeUpdate(sql);
            }
            System.out.println(rs);
            
           
        }catch(Exception e){
         
         }
        
    }
    
    private static void insertCurso(Statement stmt, Curso curso, Profesor profesor) {
        try{
         String sql = "INSERT INTO curso (nombre) VALUES ('"+curso.getNombre()+"')";
         stmt.executeUpdate(sql);
         sql = "SELECT id FROM aulagestion.persona WHERE dni ='"+profesor.getDni()+"'";
            ResultSet rs=stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getInt("id"));
                int resultado = rs.getInt("id");
                Long materia = buscarCursoPorNombre(stmt, curso.getNombre());
                sql = "INSERT INTO profesor (id_persona, id_curso) VALUES ('"+resultado+"', '"+materia+"')";
            stmt.executeUpdate(sql);
            }
            System.out.println(rs);
        
        }
        catch(Exception e){
         
         }
        
    }
     private static Long buscarCursoPorNombre(Statement stmt, String nombre) {
        try{
         String sql = "SELECT id_curso FROM aulagestion.curso WHERE nombre = '"+nombre+"'";
         ResultSet rs=stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getInt("id_curso"));
               Long resultado = rs.getLong("id_curso");
               return resultado;
            }
        }
        
        catch(Exception e){
         
            return (10000L);
         }
        return 20000L;
    }
     
     private static void buscarAlumnosYProfesorDeUnCurso(Statement stmt, String nombre) {
        try{
         String sql = "SELECT id_curso FROM aulagestion.curso WHERE nombre = '"+nombre+"'";
         ResultSet rs=stmt.executeQuery(sql);
         Long resultado = null;
            while (rs.next()) {
                
                resultado = rs.getLong("id_curso");
            }
            
        sql = "SELECT DISTINCT persona.nombre, persona.apellido, persona.edad, persona.dni FROM aulagestion.alumnos \n" +
              "INNER JOIN aulagestion.persona WHERE persona.id = alumnos.id_persona AND alumnos.id_curso ="+resultado;
        rs=stmt.executeQuery(sql);
            System.out.println("Alumnos:\n");
            while (rs.next()){
                System.out.println(rs.getString("nombre"));
                System.out.println(rs.getString("apellido"));
                System.out.println(rs.getInt("edad"));
                System.out.println(rs.getInt("dni"));
            }
            sql = "SELECT DISTINCT persona.nombre, persona.apellido, persona.edad, persona.dni FROM aulagestion.profesor \n" +
              "INNER JOIN aulagestion.persona WHERE persona.id = profesor.id_persona AND profesor.id_curso ="+resultado;
        rs=stmt.executeQuery(sql);
            System.out.println("\nProfesor:\n");
            while (rs.next()){
                System.out.println(rs.getString("nombre"));
                System.out.println(rs.getString("apellido"));
                System.out.println(rs.getInt("edad"));
                System.out.println(rs.getInt("dni"));
            }
        }catch(Exception e){
              
         }
       
    }
}
