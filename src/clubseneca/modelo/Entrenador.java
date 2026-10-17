package clubseneca.modelo;

public class Entrenador extends Empleado {
 public Entrenador(int codigo,String nombre,String apellido,String login,String password,String cupon){super(codigo,nombre,apellido,login,password,cupon);}
 @Override public RolEmpleado getRolEmpleado(){return RolEmpleado.ENTRENADOR;}
}
