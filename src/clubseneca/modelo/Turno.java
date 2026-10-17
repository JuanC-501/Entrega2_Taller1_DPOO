package clubseneca.modelo;

import java.time.*;
public class Turno {
 private final Empleado empleado;private final DayOfWeek diaSemana;private final LocalTime horaInicio,horaFin;
 public Turno(Empleado empleado,DayOfWeek dia,LocalTime inicio,LocalTime fin){if(empleado==null||dia==null||inicio==null||fin==null||!inicio.isBefore(fin))throw new IllegalArgumentException("Turno invalido");this.empleado=empleado;diaSemana=dia;horaInicio=inicio;horaFin=fin;}
 public Empleado getEmpleado(){return empleado;} public DayOfWeek getDiaSemana(){return diaSemana;}
 public LocalTime getHoraInicio(){return horaInicio;}public LocalTime getHoraFin(){return horaFin;}
 public boolean cubre(DayOfWeek dia,LocalTime instante){return dia==diaSemana&&!instante.isBefore(horaInicio)&&instante.isBefore(horaFin);}
}
