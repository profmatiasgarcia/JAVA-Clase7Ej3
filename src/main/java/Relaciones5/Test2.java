package Relaciones5;
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

public class Test2 {

    public static void main(String[] args) {
        try ( Session session = HibernateUtil.getCurrentSession()) {

        DireccionEntity2 direccion = new DireccionEntity2("Ayacucho", 632, "10mo A", "Balvanera", "CABA");
        ProfesorEntity2 profesor = new ProfesorEntity2("Garcia", "Matias", "32045612");
        profesor.setDireccion(direccion);
        direccion.setProfesor(profesor);

        session.beginTransaction();

        session.persist(profesor);
        session.getTransaction().commit();

        List<DireccionEntity2> result2 = (List<DireccionEntity2>) session.createQuery("from DireccionEntity2").list();

            for (DireccionEntity2 dire : result2) {
                System.out.println(dire);
            }
        
        ProfesorEntity2 profeEncontrado = (ProfesorEntity2) session.createQuery("FROM ProfesorEntity2 where id =:id").setParameter("id", 1).getSingleResult();
        System.out.println(profeEncontrado);
        session.close();

    }
}
}
