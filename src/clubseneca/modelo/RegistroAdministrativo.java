package clubseneca.modelo;

import java.time.LocalDate;
/** Registro de lectura/adaptador para modulos deportivos de otras personas. */
public record RegistroAdministrativo(LocalDate fecha,String categoria,String referencia,String detalle) {
 public RegistroAdministrativo {if(fecha==null||categoria==null||referencia==null)throw new IllegalArgumentException();}
}
