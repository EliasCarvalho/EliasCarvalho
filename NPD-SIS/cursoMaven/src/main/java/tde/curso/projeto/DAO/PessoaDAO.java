package tde.curso.projeto.DAO;

import java.util.List;
import tde.curso.projeto.modelo.Pessoa;
import tde.curso.utilitarios.GenericDAO;
import tde.curso.utilitarios.SQLUtil;

/**
 *
 * @author equipes
 */
public class PessoaDAO extends GenericDAO<Pessoa, Integer> {

    public PessoaDAO() {
        super(Pessoa.class);

    }

    public List pesquisarNome(String nome) {
        String sql = "select CD_PESSOA, NM_PESSOA from TDE.SIS_PESSOA where upper(NM_PESSOA) like " + "'%" + nome.toUpperCase() + "%'" + " order by NM_PESSOA fetch first 100 rows only";
        SQLUtil sqlu = new SQLUtil();
        return sqlu.pesquisar(sql);
    }
}
