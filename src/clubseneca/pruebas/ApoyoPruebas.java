package clubseneca.pruebas;

import clubseneca.modelo.*;import clubseneca.servicios.*;import java.time.*;import java.util.*;
public final class ApoyoPruebas {
 private ApoyoPruebas(){}
 public static void verificar(boolean condicion,String mensaje){if(!condicion)throw new AssertionError(mensaje);}
 public static void casi(double esperado,double valor){if(Math.abs(esperado-valor)>.001)throw new AssertionError("Esperado "+esperado+" y obtenido "+valor);}
 public static Administrador admin(){return new Administrador(1,"Ana","Admin","admin","clave123");}
 public static Socio socio(){return new Socio(2,"Sofia","Socio","sofia","secreta",LocalDate.of(2004,1,1));}
 public static Entrenador entrenador(int id){return new Entrenador(id,"Entrenador"+id,"Club","e"+id,"clave","CUP"+id);}
 public static Fisioterapeuta fisio(int id){return new Fisioterapeuta(id,"Fisio"+id,"Club","f"+id,"clave","CUP"+id);}
 public static void falla(Runnable accion,String mensaje){boolean fallo=false;try{accion.run();}catch(IllegalArgumentException|SecurityException|IllegalStateException ex){fallo=true;}verificar(fallo,mensaje);}
}
