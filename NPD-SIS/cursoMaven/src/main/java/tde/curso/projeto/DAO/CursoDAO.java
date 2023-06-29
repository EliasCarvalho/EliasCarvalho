package tde.curso.projeto.DAO;

import java.util.List;
import tde.curso.projeto.modelo.Curso;
import tde.curso.utilitarios.GenericDAO;
import tde.curso.utilitarios.SQLUtil;
import tde.curso.utilitarios.Utils;

/**
 *
 * @author equipes
 */
public class CursoDAO extends GenericDAO<Curso, Integer> {

    public CursoDAO() {
        super(Curso.class);

    }

    public List pesquisar(String valorCampo, String valorPesquisa) {
        Utils Ut = new Utils();
        SQLUtil sqlu = new SQLUtil();

        String sql = "select cd_curso, de_titulo,coalesce(nm_anexo,'')as nm_anexo from tde.sis_curso "
                + " where " + "upper(" + valorCampo + ")";

        if (valorCampo.equals("cd_curso")) {
            int num = 0;
            try {
                num = Integer.parseInt(valorPesquisa); // Converte o valor do campo texto para variável do tipo int
            } catch (NumberFormatException ex) {
                valorPesquisa = "0";
            }
            sql += "=" + valorPesquisa;
        } else if (valorCampo.equals("de_titulo")) {
            sql += " like " + Ut.plic("%" + valorPesquisa.toUpperCase() + "%");
        }
        sql += " order by " + valorCampo;

        return sqlu.pesquisar(sql);
    }
}
