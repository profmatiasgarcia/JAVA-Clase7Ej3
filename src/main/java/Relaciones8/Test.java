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
import org.hibernate.Session;

public class Test {

    public static void main(String[] args) {
        Profesor5 profesor1 = new Profesor5("Fuentes", "Cristian", "12456987");
        Profesor5 profesor2 = new Profesor5("Petro", "Sara", "45698127");

        Materia mat1 = new Materia("Sistemas Operativos");
        Materia mat2 = new Materia("Programacion III");
        Materia mat3 = new Materia("Sistemas Informáticos");

        profesor1.getMaterias().add(mat1);
        profesor1.getMaterias().add(mat2);
        profesor2.getMaterias().add(mat3);

        mat1.getProfesores().add(profesor1);
        mat2.getProfesores().add(profesor1);
        mat3.getProfesores().add(profesor2);

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(profesor1);
        session.save(profesor2);
        session.getTransaction().commit();
        session.close();

    }
}
