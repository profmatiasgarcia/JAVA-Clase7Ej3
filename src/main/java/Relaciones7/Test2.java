package Relaciones7;
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
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

public class Test2 {

    public static void main(String[] args) {
        ProfesorEntity4 profesor = new ProfesorEntity4("Deitel", "Jorge", "19875123");
        List<CorreoElectronicoEntity2> correosElectronicos = new ArrayList<>();
        correosElectronicos.add(new CorreoElectronicoEntity2("jorge@yahoo.com", profesor));
        correosElectronicos.add(new CorreoElectronicoEntity2("jorge@hotmail.com", profesor));
        correosElectronicos.add(new CorreoElectronicoEntity2("jorge@gmail.com", profesor));

        profesor.setCorreosElectronicos(correosElectronicos);

        try ( Session session = HibernateUtil.getCurrentSession()) {
            session.beginTransaction();

            session.persist(profesor);

            session.getTransaction().commit();
                        
            List<ProfesorEntity4> result = (List<ProfesorEntity4>) session.createQuery("from ProfesorEntity4").list();

            for (ProfesorEntity4 profe : result) {
                System.out.println(profe);
            }
            session.close();
        }
    }
}
