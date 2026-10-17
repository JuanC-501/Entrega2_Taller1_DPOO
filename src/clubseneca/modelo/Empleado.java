package clubseneca.modelo;

public abstract class Empleado extends Usuario {
 private final String codigoDescuento;
 protected Empleado(int codigo,String nombre,String apellido,String login,String password,String descuento){super(codigo,nombre,apellido,login,password);this.codigoDescuento=descuento;}
 public String getCodigoDescuento(){return codigoDescuento;}
 public abstract RolEmpleado getRolEmpleado();
}
