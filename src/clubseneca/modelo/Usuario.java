package clubseneca.modelo;

public abstract class Usuario {
 private final int codigo; private final String nombre,apellido,login,password;
 protected Usuario(int codigo,String nombre,String apellido,String login,String password){
  if(nombre==null||nombre.isBlank()||login==null||login.isBlank()||password==null||password.isBlank()) throw new IllegalArgumentException("Datos de usuario invalidos");
  this.codigo=codigo;this.nombre=nombre;this.apellido=apellido;this.login=login;this.password=password;
 }
 public int getCodigo(){return codigo;} public String getNombre(){return nombre;} public String getApellido(){return apellido;}
 public String getLogin(){return login;} public boolean validarPassword(String candidata){return password.equals(candidata);}
 public String consultarPerfil(){return codigo+" - "+nombre+" "+apellido+" ["+login+"]";}
 public boolean puedeReservarPersonalmente(){return true;} public boolean puedeInscribirsePartido(){return true;}
}
