package Relaciones2;
/**
 * @author Prof Matias Garcia.
 * <p> Copyright (C) 2021 para <a href = "https://www.profmatiasgarcia.com.ar/"> www.profmatiasgarcia.com.ar </a>
 * - con licencia GNU GPL3.
 * <p> Este programa es software libre. Puede redistribuirlo y/o modificarlo bajo los términos de la
 * Licencia Pública General de GNU según es publicada por la Free Software Foundation, 
 * bien con la versión 3 de dicha Licencia o bien (según su elección) con cualquier versión posterior. 
 * Este programa se distribuye con la esperanza de que sea útil, pero SIN NINGUNA GARANTÍA, 
 * incluso sin la garantía MERCANTIL implícita o sin garantizar la CONVENIENCIA PARA UN PROPÓSITO
 * PARTICULAR. Véase la Licencia Pública General de GNU para más detalles.
 * Debería haber recibido una copia de la Licencia Pública General junto con este programa. 
 * Si no ha sido así ingrese a <a href = "http://www.gnu.org/licenses/"> GNU org </a>
 */
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Persona2 {

    private Integer idpersona;
    private String nombre;
    private String apellido;
    private String dni;
    private Integer edad;
    private Set eventos = new HashSet();

    private Set email = new HashSet();

    public Persona2() {
    }

    public Persona2(String nombre, String apellido, String dni, Integer edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.edad = edad;
    }

    public Integer getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(Integer idpersona) {
        this.idpersona = idpersona;
    }

    public Set getEventos() {
        return eventos;
    }

    public void setEventos(Set eventos) {
        this.eventos = eventos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Set getEmail() {
        return email;
    }

    public void setEmail(Set email) {
        this.email = email;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Persona: " + this.idpersona + " - " + this.apellido + " - " + this.nombre + " - " + this.edad);

        Iterator<String> direcciones = getEmail().iterator();
        while (direcciones.hasNext()) {
            sb.append(System.getProperty("line.separator") + " Mail : "
                    + direcciones.next());
        }

        Iterator<Evento2> eventos = getEventos().iterator();
        while (eventos.hasNext()) {
            sb.append(System.getProperty("line.separator") + "   Evento : "
                    + eventos.next().toString());
        }
        return sb.toString();
    }

}
