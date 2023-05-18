
package utilitarios;

/**
 *
 * @author equipe3
 */
import static utilitarios.HibernateUtil.commit;
import static utilitarios.HibernateUtil.getSessionFactory;
import static utilitarios.HibernateUtil.iniciaTransacao;
import static utilitarios.HibernateUtil.openSessionFactory;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.*;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static SessionFactory sessionFactory = openSessionFactory();

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }

    public static Session open() {
        return HibernateUtil.getSessionFactory().openSession();
    }

    public static SessionFactory openSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                    configuration.getProperties()).build();
            SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);

            return factory;

        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void iniciaTransacao(Session session) {
        session.beginTransaction();

    }

    public static void commit(Session session) {
        if (session.getTransaction().isActive()) {
            session.getTransaction().commit();
        }
    }

    public static void rollback(Session session) {
        if (session.getTransaction().isActive()) {
            session.getTransaction().rollback();
        }
    }

    public static int executeSQL(Session session, String sqlDml) {

        int r = 0;

        iniciaTransacao(session);

        r = session.createSQLQuery(sqlDml).executeUpdate();

        commit(session);


        return r;

    }

    public static int executeSQLLote(Session session, String sqlDml) {


        int r = 0;

        r = session.createSQLQuery(sqlDml).executeUpdate();
        return r;

    }

    public static void close(Session session) {
        session.close();
    }
}