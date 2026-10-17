package clubseneca.modelo;

import java.time.LocalDate;
public class Mensualidad {
 private final Socio socio;private final double valor;private final LocalDate fechaPago;private final String periodo;
 public Mensualidad(Socio socio,double valor,LocalDate fechaPago,String periodo){if(socio==null||!Double.isFinite(valor)||valor<0||fechaPago==null||periodo==null||periodo.isBlank())throw new IllegalArgumentException();this.socio=socio;this.valor=valor;this.fechaPago=fechaPago;this.periodo=periodo;}
 public Socio getSocio(){return socio;}public double getValor(){return valor;}public LocalDate getFechaPago(){return fechaPago;}public String getPeriodo(){return periodo;}
}
