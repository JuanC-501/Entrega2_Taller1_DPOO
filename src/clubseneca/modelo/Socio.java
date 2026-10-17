package clubseneca.modelo;

import java.time.LocalDate;
public class Socio extends Usuario {
 private final LocalDate fechaNacimiento; private int puntosFidelidad;
 public Socio(int codigo,String nombre,String apellido,String login,String password,LocalDate nacimiento){super(codigo,nombre,apellido,login,password);this.fechaNacimiento=nacimiento;}
 public LocalDate getFechaNacimiento(){return fechaNacimiento;}public int getPuntosFidelidad(){return puntosFidelidad;}
 public void sumarPuntos(int n){if(n<0)throw new IllegalArgumentException();puntosFidelidad+=n;}
 public void redimirPuntos(int n){if(n<0||n>puntosFidelidad)throw new IllegalArgumentException("Puntos insuficientes");puntosFidelidad-=n;}
}
