package projeto.DAO;

import projeto.modelo.Inscricao;
import java.util.List;
import utilitarios.GenericDAO;
import utilitarios.SQLUtil;
import utilitarios.Utils;

/**
 *
 * @author equipes
 */
public class InscricaoDAO extends GenericDAO<Inscricao, Integer> {

    public InscricaoDAO() {

        //construtor da classe inscricao(modelo) herança.
        super(Inscricao.class);

    }

    public List pesquisar(String valorCampo, String valorPesquisa) {
        Utils Ut = new Utils();
        SQLUtil sqlu = new SQLUtil();

        String sql = "select a.cd_inscricao, b.de_titulo, c.nm_pessoa "
                + "from tde.sis_inscricao a left join tde.sis_curso b on a.cd_curso = b.cd_curso,"
                + " tde.sis_pessoa c where "
                + " a.cd_pessoa = c.cd_pessoa and "
                + valorCampo;

        if (valorCampo.equals("cd_inscricao")) {
            int num = 0;
            try {
                num = Integer.parseInt(valorPesquisa); // Converte o valor do campo texto para variável do tipo int

            } catch (NumberFormatException ex) {
                num = 0; // caso não seja possível (se o valor do campo texto for vazio ou uma letra) a variável vai receber 0.

                valorPesquisa = "0";
            }
            sql += "=" + valorPesquisa;
        } else if (valorCampo.contains("cd_curso")) {
            sql += "=" + valorPesquisa;
        }

        return sqlu.pesquisar(sql);
    }

    public List consultarCursos() {
        SQLUtil sqlu = new SQLUtil();

        String sql = "select CD_CURSO, DE_TITULO from TDE.SIS_CURSO ORDER BY DE_TITULO";

        return sqlu.pesquisar(sql);
    }

    public List consultarTotalInscrito() {
        SQLUtil sqlu = new SQLUtil();

        String sql = "select UPPER(B.DE_TITULO), COUNT(A.CD_INSCRICAO) from TDE.SIS_INSCRICAO A, TDE.SIS_CURSO B "
                + "WHERE A.CD_CURSO = B.CD_CURSO "
                + "GROUP BY B.DE_TITULO";
                

        return sqlu.pesquisar(sql);
    }
}
