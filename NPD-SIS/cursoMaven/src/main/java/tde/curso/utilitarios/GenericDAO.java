package tde.curso.utilitarios;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.internal.SessionImpl;

/**
 *
 * @author equipes
 * @param <T> Recebe a classe ex: Pessoa, Inscricao
 * @param <I>
 */
public abstract class GenericDAO<T, I extends Serializable> {

    private Class<T> persistedClass;

    protected GenericDAO() {
    }

    protected GenericDAO(Class<T> persistedClass) {
        this();
        this.persistedClass = persistedClass;

    }

    public long incluir(T entidade) {
  
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            HibernateUtil.iniciaTransacao(sessao);
            long id = incluir(sessao, entidade);
            HibernateUtil.commit(sessao);
            return id;
        } catch (HibernateException e) {
            HibernateUtil.rollback(sessao);
            return 0;
        } finally {
            sessao.close();
        }

    }

    public long incluir(Session session, T entidade) {
        return (Long) session.save(entidade);
    }

    public boolean excluir(T entidade) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sessao.beginTransaction();
        try {
            sessao.delete(entidade);
            sessao.flush();
            tx.commit();
            sessao.close();
            return true;
        } catch (HibernateException e) {
            tx.rollback();
            sessao.close();
            return false;
        }
    }

    public boolean excluir(T entidade, long codigo) {
        Session session = HibernateUtil.open();
        HibernateUtil.iniciaTransacao(session);
        boolean r = excluir(session, entidade, codigo);
        if (r) {
            HibernateUtil.commit(session);
        } else {
            HibernateUtil.rollback(session);
        }
        session.close();
        return r;
    }

    public boolean excluir(Session session, T entidade, long codigo) {
        boolean r = true;
        try {
            T gene = (T) session.get(entidade.getClass(), codigo);
            if (gene.getClass().getName() != null) {
                session.delete(gene);
            }
        } catch (Exception e) {
            r = false;
        }
        return r;
    }

    public boolean atualizar(T entidade) {
        Session session = HibernateUtil.open();
        HibernateUtil.iniciaTransacao(session);
        try {

            session.update(entidade);

            session.flush();
            HibernateUtil.commit(session);
            session.close();
            return true;
        } catch (HibernateException e) {
            HibernateUtil.rollback(session);
            session.close();
            return false;
        }

    }

    public Object procuraPorId(Integer id, T entidade) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            String nmChave = acharPrimaria(entidade);
            Query query = sessao.createQuery("from " + entidade.getClass().getName() + " WHERE " + nmChave + " = :id ");
            query.setParameter("id", id);
            T item = (T) query.uniqueResult();
            return item;
        } catch (Exception ex) {
            return null;
        } finally {
            sessao.close();
        }
    }



    public String acharPrimaria(T entidade) {

        String primaria = "";
        for (Field field : entidade.getClass().getDeclaredFields()) {

            if (field.getAnnotation(Id.class) != null) {
                primaria = field.getAnnotation(Column.class).name();

            }
        }

        return primaria;
    }

    public Object pesquisar(T entidade, long codigo) {
        Session session = HibernateUtil.open();
        T query = (T) session.get(entidade.getClass(), codigo);
        session.close();
        return query;
    }

    public List pesquisar(T entidade, String sql) {
        Session session = HibernateUtil.open();
        List query = session.createSQLQuery(sql).addEntity(entidade.getClass()).list();

        session.close();
        return query;
    }

    public ResultSet listar(T entidade) {
        try {
            String sql = "select * from ";
            SessionImpl session = (SessionImpl) HibernateUtil.getSessionFactory().openSession();
            Connection connection = session.connection();
            Statement statement = connection.createStatement();
            String tabela = entidade.getClass().getAnnotation(Table.class).name();
            sql += tabela;
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            return null;
        }

    }

    public long pesquisarProxChave(T entidade) {

        String primaria = "";
        String[] nomeIdClasse = entidade.getClass().getAnnotation(Table.class).name().split("\\.");

        for (Field field : entidade.getClass().getDeclaredFields()) {

            if (field.getAnnotation(Id.class) != null) {
                primaria = field.getAnnotation(Column.class).name();

            }
        }

        long cdInscricaoProx = autoIncremento(nomeIdClasse[0] + "." + nomeIdClasse[1], primaria);

        return cdInscricaoProx;
    }

    public long autoIncremento(String tabela, String campoAuto) {
        SQLUtil sqlUtil = new SQLUtil();

        long r = 0;
        String vsql = "select max(" + campoAuto + ") + 1 as id from " + tabela;
        try {
            r = Long.parseLong(sqlUtil.resultado(sqlUtil.pesquisar(vsql)));
        } catch (Exception e) {
            r = 1;
        }

        return r;
    }
}
