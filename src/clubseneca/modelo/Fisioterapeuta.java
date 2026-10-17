package clubseneca.modelo;

public class Fisioterapeuta extends Empleado {
 public Fisioterapeuta(int codigo,String nombre,String apellido,String login,String password,String cupon){super(codigo,nombre,apellido,login,password,cupon);}
 @Override public RolEmpleado getRolEmpleado(){return RolEmpleado.FISIOTERAPEUTA;}
}
