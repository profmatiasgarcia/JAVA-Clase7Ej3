package Relaciones8;
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
        ProfesorEntity5 profesor1 = new ProfesorEntity5("Garcia", "Matias", "28456987");
        ProfesorEntity5 profesor2 = new ProfesorEntity5("Lopez", "Saul", "5612912");

        MateriaEntity mat1 = new MateriaEntity("Tecnicas Digitales");
        MateriaEntity mat2 = new MateriaEntity("Programacion I");
        MateriaEntity mat3 = new MateriaEntity("Diseño de Sistemas");

        profesor1.getMaterias().add(mat1);
        profesor1.getMaterias().add(mat2);
        profesor2.getMaterias().add(mat3);

        mat1.getProfesores().add(profesor1);
        mat2.getProfesores().add(profesor1);
        mat3.getProfesores().add(profesor2);

        try ( Session session = HibernateUtil.getCurrentSession()) {
            session.beginTransaction();

            session.persist(profesor1);
            session.persist(profesor2);
            session.getTransaction().commit();
            
            List<ProfesorEntity5> result = (List<ProfesorEntity5>) session.createQuery("from ProfesorEntity5").list();

            for (ProfesorEntity5 profe : result) {
                System.out.println(profe);
            }

            session.close();
        }
    }
}
