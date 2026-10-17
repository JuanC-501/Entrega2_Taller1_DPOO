package clubseneca.servicios;

import java.util.*;import java.time.*;import clubseneca.modelo.*;
public class PersonalTurnos {
 private final List<Turno> turnos=new ArrayList<>();private final List<SolicitudCambioTurno> solicitudes=new ArrayList<>();
 private final LocalTime apertura,cierre;
 public PersonalTurnos(LocalTime apertura,LocalTime cierre){if(apertura==null||cierre==null||!apertura.isBefore(cierre))throw new IllegalArgumentException();this.apertura=apertura;this.cierre=cierre;}
 public void agregar(Turno t){turnos.add(Objects.requireNonNull(t));}
 public List<Turno> consultarTurnos(){return List.copyOf(turnos);}
 public void solicitar(SolicitudCambioTurno s){if(!turnos.contains(s.getTurnoActual()))throw new IllegalArgumentException("Turno inexistente");solicitudes.add(s);}
 public List<SolicitudCambioTurno> consultarSolicitudes(){return List.copyOf(solicitudes);}
 public boolean evaluar(Administrador admin,SolicitudCambioTurno s,boolean aprobar){Objects.requireNonNull(admin);if(!solicitudes.contains(s))throw new IllegalArgumentException("Solicitud no registrada");
 if(!aprobar){s.evaluar(false,"Rechazada por administrador");return false;}
 List<Turno> propuesta=new ArrayList<>(turnos);propuesta.remove(s.getTurnoActual());propuesta.add(s.getTurnoPropuesto());
 if(!coberturaValida(propuesta)){s.evaluar(false,"Cobertura minima: 2 entrenadores y 1 fisioterapeuta");return false;}
 turnos.clear();turnos.addAll(propuesta);s.evaluar(true,"Aprobada");return true;
 }
 /** Verifica cada segmento entre limites de turnos, sin discretizacion horaria. */
 public boolean coberturaValida(List<Turno> propuesta){
  for(DayOfWeek dia:DayOfWeek.values()){
   List<LocalTime> cortes=new ArrayList<>(List.of(apertura,cierre));
   for(Turno t:propuesta)if(t.getDiaSemana()==dia){if(t.getHoraInicio().isAfter(apertura)&&t.getHoraInicio().isBefore(cierre))cortes.add(t.getHoraInicio());if(t.getHoraFin().isAfter(apertura)&&t.getHoraFin().isBefore(cierre))cortes.add(t.getHoraFin());}
   cortes=cortes.stream().distinct().sorted().toList();
   for(int i=0;i<cortes.size()-1;i++){
    LocalTime momento=cortes.get(i);long entrenadores=propuesta.stream().filter(t->t.cubre(dia,momento)&&t.getEmpleado().getRolEmpleado()==RolEmpleado.ENTRENADOR).map(t->t.getEmpleado().getCodigo()).distinct().count();
    long fisios=propuesta.stream().filter(t->t.cubre(dia,momento)&&t.getEmpleado().getRolEmpleado()==RolEmpleado.FISIOTERAPEUTA).map(t->t.getEmpleado().getCodigo()).distinct().count();
    if(entrenadores<2||fisios<1)return false;
   }
  }return true;
 }
}
