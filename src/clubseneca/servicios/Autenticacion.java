package clubseneca.servicios;

import java.util.*;import clubseneca.modelo.*;
public class Autenticacion {
 private final Map<String,Usuario> usuarios=new HashMap<>();
 public void registrar(Usuario u){if(usuarios.putIfAbsent(u.getLogin(),u)!=null)throw new IllegalArgumentException("Login duplicado");}
 public Optional<Usuario> iniciarSesion(String login,String password){Usuario u=usuarios.get(login);return u!=null&&u.validarPassword(password)?Optional.of(u):Optional.empty();}
 public String consultarMiPerfil(Usuario autenticado){if(autenticado==null||usuarios.get(autenticado.getLogin())!=autenticado)throw new SecurityException("Sesion no valida");return autenticado.consultarPerfil();}
 public void verificarReserva(Usuario u){if(u==null||!u.puedeReservarPersonalmente())throw new SecurityException("Rol no puede reservar");}
 public void verificarInscripcionPartido(Usuario u){if(u==null||!u.puedeInscribirsePartido())throw new SecurityException("Rol no puede inscribirse");}
}
