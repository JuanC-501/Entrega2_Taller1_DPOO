package clubseneca.modelo;

import java.time.*;
public class SolicitudCambioTurno {
 private final Empleado solicitante;private final Turno turnoActual;private final Turno turnoPropuesto;private final String motivo;
 private final LocalDateTime fechaSolicitud=LocalDateTime.now();private LocalDateTime fechaEvaluacion;private String observacionEvaluacion;
 private EstadoSolicitud estado=EstadoSolicitud.PENDIENTE;
 public SolicitudCambioTurno(Empleado e,Turno actual,Turno propuesta,String motivo){if(e==null||actual==null||propuesta==null||actual.getEmpleado()!=e||propuesta.getEmpleado()!=e)throw new IllegalArgumentException();solicitante=e;turnoActual=actual;turnoPropuesto=propuesta;this.motivo=motivo;}
 public Empleado getSolicitante(){return solicitante;}public Turno getTurnoActual(){return turnoActual;}public Turno getTurnoPropuesto(){return turnoPropuesto;}
 public String getMotivo(){return motivo;}public EstadoSolicitud getEstado(){return estado;}
 public LocalDateTime getFechaSolicitud(){return fechaSolicitud;}public LocalDateTime getFechaEvaluacion(){return fechaEvaluacion;}
 public String getObservacionEvaluacion(){return observacionEvaluacion;}
 public void evaluar(boolean aprobar,String observacion){if(estado!=EstadoSolicitud.PENDIENTE)throw new IllegalStateException("Solicitud evaluada");estado=aprobar?EstadoSolicitud.APROBADA:EstadoSolicitud.RECHAZADA;observacionEvaluacion=observacion;fechaEvaluacion=LocalDateTime.now();}
}
