package Relaciones9;
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
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class Test {

    public static void main(String[] args) {
        cargarProfesores();

        try ( Session session = HibernateUtil.getCurrentSession()) {

            {
                System.out.println("----------- Navegación por un campo -----------");
                String ape = (String) session.createQuery("SELECT p.apellido FROM Profesor6 p WHERE id=8").uniqueResult();
                System.out.println("Apellido = " + ape);
            }

            {
                System.out.println("----------- Navegar por varios campos enlazados-----------");
                Query query = session.createQuery("SELECT p.apellido,p.direccion.municipio.barrio FROM Profesor6 p WHERE p.id IN (3,4,5,6)");
                List<Object[]> listDatos = (List<Object[]>) query.list();
                for (Object[] datos : listDatos) {
                    System.out.println("El profesor " + datos[0] + " vive en " + datos[1]);
                }
            }

            {
                System.out.println("----------- Función SIZE en colecciones -----------");
                Query query = session.createQuery("SELECT p.apellido,SIZE(p.correosElectronicos) FROM Profesor6 p WHERE p.id IN (5,6,7,8) Group by p.apellido");
                List<Object[]> listDatos = (List<Object[]>) query.list();
                for (Object[] datos : listDatos) {
                    System.out.println("El profesor " + datos[0] + " tiene  " + datos[1] + " direcciones de correo electrónico");
                }
            }

            {
                System.out.println("----------- Función IS NULL -----------");
                Query query = session.createQuery("SELECT p.apellido FROM Profesor6 p WHERE p.direccion.dpto IS NULL"); //EMPTY
                List<Object> listDatos = (List<Object>) query.list();
                for (Object datos : listDatos) {
                    System.out.println("El profesor " + datos + " no vive en departamento");
                }
            }

            {
                System.out.println("----------- Problema n+1 SELECT -----------");
                Query query = session.createQuery("SELECT p FROM Profesor6 p");
                List<Profesor6> profesores = query.list();

                for (Profesor6 profesor : profesores) {
                    System.out.println(profesor.toString());
                    for (CorreoElectronico3 correoElectronico : profesor.getCorreosElectronicos()) {
                        System.out.println("\t" + correoElectronico);
                    }
                }
            }

            {
                System.out.println("----------- SOLUCION al n+1 SELECT con join -----------");
                Query query = session.createQuery("SELECT p FROM Profesor6 p LEFT JOIN FETCH p.correosElectronicos");
                List<Profesor6> profesores = query.list();

                Set<Profesor6> profesoresSinDuplicar = new HashSet<Profesor6>(profesores);
                profesores.clear();
                profesores.addAll(profesoresSinDuplicar);

                for (Profesor6 profesor : profesores) {
                    System.out.println(profesor);
                    for (CorreoElectronico3 correoElectronico : profesor.getCorreosElectronicos()) {
                        System.out.println("\t" + correoElectronico);
                    }
                }
            }

            {
                System.out.println("----------- consulta nativa en SQL (nombres de tablas segun BD -----------");
                Query query = session.createNativeQuery("SELECT idMunicipio,barrio,localidad FROM municipio");
                List<Object[]> listDatos = query.list();
                System.out.println("ID\tBarrio\t\tLocalidad");
                for (Object[] datos : listDatos) {
                    System.out.println(datos[0] + "\t" + datos[1] + "    \t" + datos[2]);
                }
            }

            {
                System.out.println("----------- Consultas peronalizadas para INSERT,  UPDATE y DELETE -----------");
                Profesor6 profesor;

                session.beginTransaction();
                profesor = new Profesor6("Pose", "Mariano", "25874110");
                session.persist(profesor);
                session.getTransaction().commit();

                session.beginTransaction();
                profesor.setNombre("Juan Carlos");
                session.merge(profesor);
                session.getTransaction().commit();

//            session.beginTransaction();
//            session.delete(profesor);
//            session.getTransaction().commit();
            }
            session.close();
        }

    }

    public static void cargarProfesores() {
        Profesor6 profesor = new Profesor6("Meiland", "Lucas", "29871589");
        Set<CorreoElectronico3> correosElectronicos = new HashSet<>();
        correosElectronicos.add(new CorreoElectronico3("lucas@yahoo.com", profesor));
        correosElectronicos.add(new CorreoElectronico3("lucas@hotmail.com", profesor));
        correosElectronicos.add(new CorreoElectronico3("lucas@gmail.com", profesor));
        profesor.setCorreosElectronicos(correosElectronicos);
        Municipio lugar = new Municipio("Balvanera", "CABA", "Buenos Aires");
        Direccion3 dire = new Direccion3("Av Corrientes", 5808, "7B", lugar);
        profesor.setDireccion(dire);

        Profesor6 profesor2 = new Profesor6("Garcia", "Matias", "26987456");
        Set<CorreoElectronico3> correosElectronicos2 = new HashSet<>();
        correosElectronicos2.add(new CorreoElectronico3("matias@gmail.com", profesor2));
        correosElectronicos2.add(new CorreoElectronico3("matias@mimail.com", profesor2));
        profesor2.setCorreosElectronicos(correosElectronicos2);
        Municipio lugar2 = new Municipio("Olivos", "Vte Lopez", "Buenos Aires");
        Direccion3 dire2 = new Direccion3("J.A. Roca", 808, null, lugar2);
        profesor2.setDireccion(dire2);

        Profesor6 profesor3 = new Profesor6("Lopez", "Rocio", "38714589");
        Set<CorreoElectronico3> correosElectronicos3 = new HashSet<>();
        correosElectronicos3.add(new CorreoElectronico3("rocio@yahoo.com", profesor3));
        correosElectronicos3.add(new CorreoElectronico3("lrocio@gmail.com", profesor3));
        profesor3.setCorreosElectronicos(correosElectronicos3);
        Municipio lugar3 = new Municipio("Adrogué", "Almirante Brown", "Buenos Aires");
        Direccion3 dire3 = new Direccion3("Vega", 1083, null, lugar3);
        profesor3.setDireccion(dire3);

        Profesor6 profesor4 = new Profesor6("Juarez", "Debora", "31871589");
        Set<CorreoElectronico3> correosElectronicos4 = new HashSet<>();
        correosElectronicos4.add(new CorreoElectronico3("deb@yahoo.com", profesor4));
        correosElectronicos4.add(new CorreoElectronico3("devdeb@hotmail.com", profesor4));
        correosElectronicos4.add(new CorreoElectronico3("debora@gmail.com", profesor4));
        profesor4.setCorreosElectronicos(correosElectronicos4);
        Municipio lugar4 = new Municipio("Lanús", "Lanús", "Buenos Aires");
        Direccion3 dire4 = new Direccion3("San Martin", 1509, null, lugar4);
        profesor4.setDireccion(dire4);

        Profesor6 profesor5 = new Profesor6("Miranda", "Roberto", "19871912");
        Set<CorreoElectronico3> correosElectronicos5 = new HashSet<>();
        correosElectronicos5.add(new CorreoElectronico3("mirrob@hotmail.com", profesor5));
        correosElectronicos5.add(new CorreoElectronico3("robmiran@gmail.com", profesor5));
        profesor5.setCorreosElectronicos(correosElectronicos5);
        Municipio lugar5 = new Municipio("Dock Sud", "Avellaneda", "Buenos Aires");
        Direccion3 dire5 = new Direccion3("Miller", 708, "7B", lugar5);
        profesor5.setDireccion(dire5);

        Profesor6 profesor6 = new Profesor6("Garcia", "Brianna", "40871587");
        Set<CorreoElectronico3> correosElectronicos6 = new HashSet<>();
        correosElectronicos6.add(new CorreoElectronico3("bri@gmail.com", profesor6));
        profesor6.setCorreosElectronicos(correosElectronicos6);
        Municipio lugar6 = new Municipio("Munro", "Vte Lopez", "Buenos Aires");
        Direccion3 dire6 = new Direccion3("Ravignani", 6108, "4toA", lugar6);
        profesor6.setDireccion(dire6);

        Profesor6 profesor7 = new Profesor6("Perez", "Adrian", "23871510");
        Set<CorreoElectronico3> correosElectronicos7 = new HashSet<>();
        correosElectronicos7.add(new CorreoElectronico3("adri@yahoo.com", profesor7));
        correosElectronicos7.add(new CorreoElectronico3("perez@gmail.com", profesor7));
        profesor7.setCorreosElectronicos(correosElectronicos7);
        Municipio lugar7 = new Municipio("Palermo", "CABA", "Buenos Aires");
        Direccion3 dire7 = new Direccion3("Goya", 5408, "1C", lugar7);
        profesor7.setDireccion(dire7);

        Profesor6 profesor8 = new Profesor6("Cisneros", "Diego", "19912345");
        Set<CorreoElectronico3> correosElectronicos8 = new HashSet<>();
        correosElectronicos8.add(new CorreoElectronico3("diego@yahoo.com", profesor8));
        correosElectronicos8.add(new CorreoElectronico3("dicis@hotmail.com", profesor8));
        profesor8.setCorreosElectronicos(correosElectronicos8);
        Municipio lugar8 = new Municipio("Recoleta", "CABA", "Buenos Aires");
        Direccion3 dire8 = new Direccion3("Pasteur", 812, "2doB", lugar8);
        profesor8.setDireccion(dire8);

        Profesor6 profesor9 = new Profesor6("Filgueras", "Azul", "32871449");
        Set<CorreoElectronico3> correosElectronicos9 = new HashSet<>();
        correosElectronicos9.add(new CorreoElectronico3("azul@yahoo.com", profesor9));
        correosElectronicos9.add(new CorreoElectronico3("filazul@hotmail.com", profesor9));
        profesor9.setCorreosElectronicos(correosElectronicos9);
        Municipio lugar9 = new Municipio("Vte Lopez", "Vte Lopez", "Buenos Aires");
        Direccion3 dire9 = new Direccion3("Yrigoyen", 1808, null, lugar9);
        profesor9.setDireccion(dire9);

        Profesor6 profesor10 = new Profesor6("Lopez", "Tamara", "20851189");
        Set<CorreoElectronico3> correosElectronicos10 = new HashSet<>();
        correosElectronicos10.add(new CorreoElectronico3("tamara@gmail.com", profesor10));
        profesor10.setCorreosElectronicos(correosElectronicos10);
        Municipio lugar10 = new Municipio("Avellaneda", "Avellaneda", "Buenos Aires");
        Direccion3 dire10 = new Direccion3("Salguero", 1583, null, lugar10);
        profesor10.setDireccion(dire10);

        try ( Session session = HibernateUtil.getCurrentSession()) {
            session.beginTransaction();

            session.persist(lugar);
            session.persist(lugar2);
            session.persist(lugar3);
            session.persist(lugar4);
            session.persist(lugar5);
            session.persist(lugar6);
            session.persist(lugar7);
            session.persist(lugar8);
            session.persist(lugar9);
            session.persist(lugar10);
            session.persist(profesor);
            session.persist(profesor2);
            session.persist(profesor3);
            session.persist(profesor4);
            session.persist(profesor5);
            session.persist(profesor6);
            session.persist(profesor7);
            session.persist(profesor8);
            session.persist(profesor9);
            session.persist(profesor10);

            session.getTransaction().commit();
            session.close();
        }
    }
}
