package Relaciones6;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;

public class Test {

    public static void main(String[] args) {
        Profesor3 profesor = new Profesor3("Gutierrez", "Sara", "12456987");
        Set<CorreoElectronico> correosElectronicos = new HashSet<>();
        correosElectronicos.add(new CorreoElectronico("sara@yahoo.com", profesor));
        correosElectronicos.add(new CorreoElectronico("sara@hotmail.com", profesor));
        correosElectronicos.add(new CorreoElectronico("sara@gmail.com", profesor));

        profesor.setCorreosElectronicos(correosElectronicos);

        try ( Session session = HibernateUtil.getCurrentSession()) {
            session.beginTransaction();

            session.persist(profesor);

            session.getTransaction().commit();

            List<Profesor3> result = (List<Profesor3>) session.createQuery("from Profesor3").list();

            for (Profesor3 profe : result) {
                System.out.println(profe);
            }
            session.close();

        }
    }
}
