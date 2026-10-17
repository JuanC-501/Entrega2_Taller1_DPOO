package clubseneca.pruebas;

import clubseneca.modelo.*;import clubseneca.servicios.*;
/** RF-42,43,44; RN-39. */
public class Prueba10 {
 public static void main(String[] args){
 Autenticacion servicio=new Autenticacion();Administrador a=ApoyoPruebas.admin();Socio s=ApoyoPruebas.socio();servicio.registrar(a);servicio.registrar(s);
 ApoyoPruebas.verificar(servicio.iniciarSesion("admin","clave123").orElseThrow()==a,"Login valido debe funcionar");
 ApoyoPruebas.verificar(servicio.iniciarSesion("admin","incorrecta").isEmpty(),"Login incorrecto no debe funcionar");
 ApoyoPruebas.verificar(servicio.consultarMiPerfil(s).contains("Sofia"),"Perfil debe consultarse");
 ApoyoPruebas.falla(()->servicio.verificarReserva(a),"Admin no debe reservar");
 ApoyoPruebas.falla(()->servicio.verificarInscripcionPartido(a),"Admin no debe inscribirse");
 servicio.verificarReserva(s);servicio.verificarInscripcionPartido(s);
 System.out.println("PRUEBA 10 OK: login, perfil y restricciones del administrador");
 }
}
