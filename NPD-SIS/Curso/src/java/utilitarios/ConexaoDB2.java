package utilitarios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import org.hibernate.internal.SessionImpl;

/**
 *
 * @author equipes
 */
public class ConexaoDB2 {

    public void conexaoDB2() {
    }

    public Connection conectaDB2() {

        Connection con;

        try {

            SessionImpl sessao = (SessionImpl) HibernateUtil.getSessionFactory().openSession();
            con = sessao.connection();

        } catch (Exception e) {
            return null;
        }
        return con;

    }

    public Connection conectaDB2JDBC() {

        Connection con;
        Statement stm;
        try {
            //Cria conexão outra conexão
            Class.forName("com.ibm.db2.jcc.DB2Driver");
            con = DriverManager.getConnection("jdbc:db2://desenvol3.uem.br:50000/desenvol3", "", "");

            stm = con.createStatement();

        } catch (Exception e) {
            return null;
        }
        return con;

    }

    public void executarSQL(String sql) {

        Connection Db2Conn = conectaDB2JDBC();
        PreparedStatement Db2Stmt = null;

        try {

            // Executa o SQL

            Db2Stmt = Db2Conn.prepareStatement(sql);
            Db2Stmt.executeUpdate();

        } // Segura qualquer tipo de erro
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (Db2Stmt != null) {
                try {
                    Db2Stmt.close();
                } catch (Exception e) {
                }
            }
            if (Db2Conn != null) {
                try {
                    Db2Conn.close();
                } catch (Exception e) {
                }
            }
        }

    }
}
