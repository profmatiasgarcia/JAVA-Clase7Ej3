package Relaciones4;
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
import java.util.List;
import org.hibernate.Session;

public class Test {

    public static void main(String[] args) {
        try ( Session session = HibernateUtil.getCurrentSession()) {
            Direccion direccion = new Direccion("San Martin", 808, "-", "Palermo", "CABA");
            Profesor profesor = new Profesor("Citterio", "Angie", "40456123");
            profesor.setDireccion(direccion);

            session.beginTransaction();

            session.persist(profesor);
            session.getTransaction().commit();

            List<Profesor> result = (List<Profesor>) session.createQuery("from Profesor").list();

            for (Profesor profe : result) {
                System.out.println(profe);
            }

            session.close();

        }
    }
}
