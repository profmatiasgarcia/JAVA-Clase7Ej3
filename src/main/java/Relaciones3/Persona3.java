package Relaciones3;
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

public class Persona3 {

    private Integer idpersona;
    private String nombre;
    private String apellido;
    private String dni;
    private Integer edad;

    private Set eventos = new HashSet();

    public Persona3() {
    }

    public Persona3(String nombre, String apellido, String dni, Integer edad) {
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

    //Se hace este metodo protegido, para que nadie pueda cambiar el conjunto de eventos sin control
    protected Set getEventos() {
        return eventos;
    }

    //Se hace este metodo protegido, para que nadie pueda cambiar el conjunto de eventos sin control
    protected void setEventos(Set eventos) {
        this.eventos = eventos;
    }

// Al anadir un evento y ser la asociacion bidireccional, hay que asociar el evento a esta persona y luego asociar esta persona al evento.
    public void addToEvent(Evento3 evento) {
        this.getEventos().add(evento);
        evento.getParticipantes().add(this);
    }

    // Al borrar un evento y ser la asocacion bidireccional, hay que eliminar el evento de esta persona y luego eliminar esta persona del evento.
    public void removeFromEvent(Evento3 evento) {
        this.getEventos().remove(evento);
        evento.getParticipantes().remove(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Persona: " + this.idpersona + " - " + this.apellido + " - " + this.nombre + " - " + this.edad);
        Iterator<Evento3> eventos = getEventos().iterator();
        while (eventos.hasNext()) {
            sb.append(System.getProperty("line.separator") + "   Evento : "
                    + eventos.next().toString());
        }
        return sb.toString();
    }

}
