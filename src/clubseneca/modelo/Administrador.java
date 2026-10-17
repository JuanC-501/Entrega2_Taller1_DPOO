package clubseneca.modelo;

public class Administrador extends Usuario {
 public Administrador(int codigo,String nombre,String apellido,String login,String password){super(codigo,nombre,apellido,login,password);}
 @Override public boolean puedeReservarPersonalmente(){return false;}
 @Override public boolean puedeInscribirsePartido(){return false;}
}
