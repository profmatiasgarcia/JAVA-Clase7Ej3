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
import java.time.LocalDate;
import java.util.List;
import org.hibernate.Session;

public class Test {

    public static void main(String[] args) {
        new Test();
        HibernateUtil.closeSession();
    }

    public Test() {
        // Se crea y guarda en bd un evento
        Integer idEv = crearEvento("FLISoL BA", LocalDate.of(2022, 04, 29));
        System.out.println("id evento = " + idEv);
//        // Se crea y guarda en bd una persona
        Integer idPe = crearPersona("Matias", "Garcia", "26888999", 41);
        System.out.println("id persona = " + idPe);

        // Se asocian en bd persona y evento
        addEventoAPersona(idPe, idEv);

        // Se agraga un nuevo evento a la persona
        addNuevoEventoAPersona("Nuevo evento", LocalDate.of(2022, 10, 21), idPe);

        // Se le asocia el e-mail.
        addEmailAPersona(idPe, "direccion@micorreo.com");
        addEmailAPersona(idPe, "otra.direccion@correo.com.ar");
        // Se listan las personas con sus eventos
        listPersonas();
    }

    private void addEmailAPersona(Integer personaId, String emailAddress) {
        try ( Session session = HibernateUtil.getCurrentSession()) {
            session.beginTransaction();

            // Se carga la persona de bd
            Persona2 laPersona = (Persona2) session.get(Persona2.class, personaId);

            // Se le asocia el e-mail
            laPersona.getEmail().add(emailAddress);

            // Y se salva en base de datos.
            session.getTransaction().commit();
        }
    }

    private Integer crearEvento(String titulo, LocalDate fecha) {
        try ( Session session = HibernateUtil.getCurrentSession()) {

            session.beginTransaction();

            Evento2 nuevoEvento = new Evento2();
            nuevoEvento.setTitulo(titulo);
            nuevoEvento.setFecha(fecha);

            session.persist(nuevoEvento);
            session.getTransaction().commit();
            return nuevoEvento.getId();
        } catch (Exception e) {
            System.out.println("Algo salio mal al crear nuevo Evento");
        }
        return null;

    }

    private Integer crearPersona(String nombre, String apellido, String dni, Integer edad) {
        try ( Session session = HibernateUtil.getCurrentSession()) {
            // Creacion de la persona.
            Persona2 nuevaPersona = new Persona2(nombre, apellido, dni, edad);

            // Se guardaen bd y se cierra la sesion
            session.beginTransaction();
            session.persist(nuevaPersona);
            session.getTransaction().commit();
            return nuevaPersona.getIdpersona();
        } catch (Exception e) {
            System.out.println("Ocurrio un error");
        }
        return null;
    }

    private void listPersonas() {
//    private List<Persona> listPersonas() {
        try ( Session session = HibernateUtil.getCurrentSession()) {

            List<Persona2> result = (List<Persona2>) session.createQuery("from Persona2").list();

            // Debe hacerse el listado antes del commit, puesto que el toString()
            // de Person consulta los Event asociados a la persona.
            for (Persona2 persona : result) {
                System.out.println(persona);
            }
        }
        //    return result;
    }

    private void addEventoAPersona(Integer personaId, Integer eventoId) {
        // Sesion
        try ( Session session = HibernateUtil.getCurrentSession()) {
            session.beginTransaction();

            // Se cargan perons y eventos
            Persona2 laPerson = (Persona2) session.get(Persona2.class, personaId);
            Evento2 elEvento = (Evento2) session.get(Evento2.class, eventoId);

            // Se añade el evento a la persona
            laPerson.getEventos().add(elEvento);

            // Se termina la transaccion
            session.getTransaction().commit();
        }
    }

    private void addNuevoEventoAPersona(String tituloEvento, LocalDate fechaEvento,
            Integer personaId) {
        try ( Session session = HibernateUtil.getCurrentSession()) {
            session.beginTransaction();

            // Se carga la persona de bd
            Persona2 laPersona = (Persona2) session.get(Persona2.class, personaId);

            // Se crea un evento y se salva
            Evento2 e = new Evento2();
            e.setTitulo(tituloEvento);
            e.setFecha(fechaEvento);
            session.persist(e);

            // Se añade el evento a la persona y se salva
            laPersona.getEventos().add(e);
            session.persist(laPersona);

            // fin de sesion
            session.getTransaction().commit();
        }
    }
}
