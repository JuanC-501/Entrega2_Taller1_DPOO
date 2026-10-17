package clubseneca.pruebas;

import clubseneca.modelo.*;import clubseneca.servicios.*;import java.time.*;
/** RF-29,37,38; RN-40. */
public class Prueba8 {
 public static void main(String[] args){
 LocalTime ocho=LocalTime.of(8,0),dieciocho=LocalTime.of(18,0);PersonalTurnos servicio=new PersonalTurnos(ocho,dieciocho);
 Entrenador e1=ApoyoPruebas.entrenador(11),e2=ApoyoPruebas.entrenador(12),e3=ApoyoPruebas.entrenador(13);Fisioterapeuta f1=ApoyoPruebas.fisio(14);
 // Cobertura de lunes a domingo para modelar el horario de atencion establecido para esta prueba.
 for(DayOfWeek d:DayOfWeek.values()){servicio.agregar(new Turno(e1,d,ocho,dieciocho));servicio.agregar(new Turno(e2,d,ocho,dieciocho));servicio.agregar(new Turno(e3,d,ocho,dieciocho));servicio.agregar(new Turno(f1,d,ocho,dieciocho));}
 Turno actual=servicio.consultarTurnos().stream().filter(t->t.getEmpleado()==e3&&t.getDiaSemana()==DayOfWeek.MONDAY).findFirst().orElseThrow();
 SolicitudCambioTurno valida=new SolicitudCambioTurno(e3,actual,new Turno(e3,DayOfWeek.MONDAY,LocalTime.of(10,0),dieciocho),"Cambio personal");
 servicio.solicitar(valida);ApoyoPruebas.verificar(servicio.evaluar(ApoyoPruebas.admin(),valida,true),"Debe aprobarse con 2 entrenadores");
 Turno fisioLunes=servicio.consultarTurnos().stream().filter(t->t.getEmpleado()==f1&&t.getDiaSemana()==DayOfWeek.MONDAY).findFirst().orElseThrow();
 SolicitudCambioTurno invalida=new SolicitudCambioTurno(f1,fisioLunes,new Turno(f1,DayOfWeek.MONDAY,LocalTime.of(12,0),dieciocho),"Retirar cobertura matutina");
 servicio.solicitar(invalida);ApoyoPruebas.verificar(!servicio.evaluar(ApoyoPruebas.admin(),invalida,true),"Debe rechazarse por falta de fisioterapeuta");
 ApoyoPruebas.verificar(servicio.consultarTurnos().contains(fisioLunes)&&servicio.consultarTurnos().contains(valida.getTurnoPropuesto()),"Consulta de turnos incorrecta");
 System.out.println("PRUEBA 8 OK: solicitud aprobada, otra rechazada, consulta semanal");
 }
}
