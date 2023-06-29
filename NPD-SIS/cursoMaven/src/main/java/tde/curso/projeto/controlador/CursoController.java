package tde.curso.projeto.controlador;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import tde.curso.projeto.DAO.CursoDAO;
import tde.curso.projeto.modelo.Curso;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import tde.curso.utilitarios.SQLUtil;
import tde.curso.utilitarios.Utils;
import tde.curso.utilitarios.ZkUtils;

/**
 *
 * @author equipes
 */
public class CursoController extends Window {

    //Definição das variáveis da view
    private Window win;
    private Label cdCurso;
    private Textbox deTitulo;
    private Listbox tpCurso;
    private Intbox nuCarga;
    private Datebox dtCursoInicio;
    private Datebox dtCursoFim;
    private Intbox nuVagas;
    private Datebox dtInscricaoInicio;
    private Datebox dtInscricaoFim;
    private Textbox nmAnexo;
    //Definição das variáveis dos Popups
    private Popup pesquisa;
    private Listbox vlCampo;
    private Textbox vlPesquisa;
    private Listbox resultados;
    //Chamadas de classes de consultas padrões
    private Utils ut = new Utils();
    private SQLUtil sqlUtil = new SQLUtil();
    private ZkUtils zut = new ZkUtils();
    //Definição do item da pesquisa instancia modelo
    private Curso _resultadoSelecionado = new Curso();
    private CursoDAO dao = new CursoDAO();

    //Inicialização dos mapeamentos ao carregar a página
    public void onCreate() {

        // Mapeamento da(s) window(s)
        this.setWin((Window) getFellow("win"));

        // Mapeamento das variáveis do arquivo
        this.setCdCurso((Label) getFellow("cdCurso"));
        this.setDeTitulo((Textbox) getFellow("deTitulo"));
        this.setTpCurso((Listbox) getFellow("tpCurso"));
        this.setNuCarga((Intbox) getFellow("nuCarga"));
        this.setDtCursoInicio((Datebox) getFellow("dtCursoInicio"));
        this.setDtCursoFim((Datebox) getFellow("dtCursoFim"));
        this.setNuVagas((Intbox) getFellow("nuVagas"));
        this.setDtInscricaoInicio((Datebox) getFellow("dtInscricaoInicio"));
        this.setDtInscricaoFim((Datebox) getFellow("dtInscricaoFim"));

        this.setNmAnexo((Textbox) getFellow("nmAnexo"));
        // Mapeamento das Popups de pesquisa
        this.setPesquisa((Popup) getFellow("pesquisa"));

        //Campos do Popup de pesquisa        

        this.setVlCampo((Listbox) getFellow("vlCampo"));
        this.setVlPesquisa((Textbox) getFellow("vlPesquisa"));
        this.setResultados((Listbox) getFellow("resultados"));

        limparCampos();

    }

    // Limpa os campos do formulário 
    public void limparCampos() {

        ZkUtils zkUtils = new ZkUtils();

        this.getCdCurso().setValue("-1");

        this.getDeTitulo().setRawValue(null);
        //Função pronta para selecionar qualquer item da lista
        getZut().selecionarLista(getTpCurso(), 0);
        this.getNuCarga().setRawValue(null);
        this.getDtCursoInicio().setRawValue(null);
        this.getDtCursoFim().setRawValue(null);
        this.getNuVagas().setRawValue(null);
        this.getDtInscricaoInicio().setRawValue(null);
        this.getDtInscricaoFim().setRawValue(null);

        this.getNmAnexo().setRawValue(null);

        this.getDeTitulo().setFocus(true);

    }

    public void pesquisar() {

        this.getPesquisa().open(this.getPesquisa());
        this.getVlPesquisa().focus();

    }

    public void executarPesquisa() {

        if (getVlPesquisa().getValue() != null) {

            getResultados().getItems().clear();
            int s = 0;

            List query = null;
            query = dao.pesquisar(getVlCampo().getSelectedItem().getValue().toString(), getVlPesquisa().getValue());

            for (Iterator i = query.iterator(); i.hasNext();) {

                Object[] obj = (Object[]) i.next();

                Listitem item = new Listitem();
                Listcell cellCdCurso = new Listcell();
                Listcell cellDeTitulo = new Listcell();
                Listcell cellNmAnexo = new Listcell();

                cellCdCurso.setLabel(obj[0].toString());
                cellDeTitulo.setLabel(obj[1].toString());


                cellNmAnexo.setLabel(obj[2].toString());


                item.appendChild(cellCdCurso);
                item.appendChild(cellDeTitulo);
                item.appendChild(cellNmAnexo);

                item.setValue(obj[0].toString());

                if (s == 0) {
                    item.setSelected(true);
                    s = 1;
                }

                item.setParent(getResultados());
            }

        } else {
            try {
                Messagebox.show("Campo e/ou valor da pesquisa inválido", "Pesquisar Registro", Messagebox.OK | Messagebox.CANCEL, Messagebox.INFORMATION);
            } catch (Exception ex) {
                Logger.getLogger(CursoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void selecionar() {

        this.getResultadoSelecionado().setCdCurso(Long.parseLong(getResultados().getSelectedItem().getValue().toString()));

        this.setResultadoSelecionado((Curso) dao.pesquisar(this.getResultadoSelecionado(), this.getResultadoSelecionado().getCdCurso()));

        fecharPesquisa();

        popularCampos();

    }

    public void fecharPesquisa() {
        this.pesquisa.close();

    }

    //Função p/ preencher campos de um item pesquisado
    public void popularCampos() {

        limparCampos();
        
        if (getResultadoSelecionado().getCdCurso() > 0) {

            zut.popularCampo(this.getCdCurso(), (Object) getResultadoSelecionado().getCdCurso());
            zut.popularCampo(this.getDeTitulo(), (Object) getResultadoSelecionado().getDeTitulo());

            zut.selecionarLista(this.getTpCurso(), getResultadoSelecionado().getTpCurso().toString());

            zut.popularCampo(this.getNuCarga(), (Object) getResultadoSelecionado().getNuCarga());
            zut.popularCampo(this.getDtCursoInicio(), (Object) getResultadoSelecionado().getDtCursoInicio());
            zut.popularCampo(this.getDtCursoFim(), (Object) getResultadoSelecionado().getDtCursoFim());
            zut.popularCampo(this.getNuVagas(), (Object) getResultadoSelecionado().getNuVagas());
            zut.popularCampo(this.getDtInscricaoInicio(), (Object) getResultadoSelecionado().getDtInscricaoInicio());
            zut.popularCampo(this.getDtInscricaoFim(), (Object) getResultadoSelecionado().getDtInscricaoFim());

            zut.popularCampo(this.getNmAnexo(), (Object) getResultadoSelecionado().getNmAnexo());

        }

    }

    public void gravar() throws ParseException {

        if (validarCampos()) {
            //criação da instancia das classes.

            Curso ent = new Curso();
            CursoDAO entDao = new CursoDAO();

            ent.setDeTitulo(getDeTitulo().getValue());
            ent.setTpCurso(Short.parseShort(getTpCurso().getSelectedItem().getValue().toString()));
            ent.setNuCarga(getNuCarga().getValue().shortValue());
            ent.setDtCursoInicio(getDtCursoInicio().getValue());
            ent.setDtCursoFim(getDtCursoFim().getValue());
            ent.setNuVagas(getNuVagas().getValue().shortValue());
            ent.setDtInscricaoInicio(getDtInscricaoInicio().getValue());
            ent.setDtInscricaoFim(getDtInscricaoFim().getValue());

            ent.setNmAnexo(getNmAnexo().getValue());

            if (Long.parseLong(getCdCurso().getValue()) == -1) {
                long cdCursoProx = entDao.pesquisarProxChave(ent);
                ent.setCdCurso(cdCursoProx);
                entDao.incluir(ent);
                Messagebox.show("Curso cadastrado com sucesso", "Cadastro", Messagebox.OK, Messagebox.INFORMATION);

                this.getCdCurso().setValue(String.valueOf(ent.getCdCurso()));
            } else {
                ent.setCdCurso(Long.parseLong(getCdCurso().getValue()));
                entDao.atualizar(ent);
                Messagebox.show("Curso atualizado com sucesso", "Alteração", Messagebox.OK, Messagebox.INFORMATION);
            }

        }
    }

    public boolean validarCampos() throws ParseException {

        if (!zut.validarCampo(this.deTitulo)) {
            return false;
        }

        if (this.getTpCurso().getSelectedIndex() == 0) {
            Messagebox.show("Selecione o Tipo de Curso", "Validar", Messagebox.OK | Messagebox.CANCEL, Messagebox.INFORMATION);
            getTpCurso().focus();
            return false;
        }

        if (!zut.validarCampo(this.nmAnexo)) {
            return false;
        }

        return true;
    }

    public void excluir() {
        try {
            // Messagebox.show mostra a mensagem na tela do usuário, é possível utilizar para obter resposta para uma ação
            if (Messagebox.show("Deseja excluir o curso atual?", "Excluir Curso", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION) == Messagebox.YES) {
                if (!getCdCurso().getValue().equals("-1")) {

                    Curso ent = new Curso();
                    CursoDAO dao = new CursoDAO();

                    dao.excluir(ent, Long.parseLong(getCdCurso().getValue()));
                    Messagebox.show("Curso excluído com sucesso", "Curso", Messagebox.OK, Messagebox.INFORMATION);

                    limparCampos();
                } else {
                    Messagebox.show("Curso inválido", "Curso", Messagebox.OK, Messagebox.INFORMATION);
                }
            }

        } catch (NumberFormatException ex) {
            Logger.getLogger(CursoController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Getters e Setters de cada componente zul
    /**
     * @return the zut
     */
    public ZkUtils getZut() {
        return zut;
    }

    /**
     * @param zut the zut to set
     */
    public void setZut(ZkUtils zut) {
        this.zut = zut;
    }

    /**
     * @return the winCurso
     */
    public Window getWin() {
        return win;
    }

    /**
     * @param win the winCurso to set
     */
    public void setWin(Window win) {
        this.win = win;
    }

    /**
     * @return the cdCurso
     */
    public Label getCdCurso() {
        return cdCurso;
    }

    /**
     * @param cdCurso the cdCurso to set
     */
    public void setCdCurso(Label cdCurso) {
        this.cdCurso = cdCurso;
    }

    /**
     * @return the deTitulo
     */
    public Textbox getDeTitulo() {
        return deTitulo;
    }

    /**
     * @param deTitulo the deTitulo to set
     */
    public void setDeTitulo(Textbox deTitulo) {
        this.deTitulo = deTitulo;
    }

    /**
     * @return the tpCurso
     */
    public Listbox getTpCurso() {
        return tpCurso;
    }

    /**
     * @param tpCurso the tpCurso to set
     */
    public void setTpCurso(Listbox tpCurso) {
        this.tpCurso = tpCurso;
    }

    /**
     * @return the nuCarga
     */
    public Intbox getNuCarga() {
        return nuCarga;
    }

    /**
     * @param nuCarga the nuCarga to set
     */
    public void setNuCarga(Intbox nuCarga) {
        this.nuCarga = nuCarga;
    }

    /**
     * @return the dtCursoInicio
     */
    public Datebox getDtCursoInicio() {
        return dtCursoInicio;
    }

    /**
     * @param dtCursoInicio the dtCursoInicio to set
     */
    public void setDtCursoInicio(Datebox dtCursoInicio) {
        this.dtCursoInicio = dtCursoInicio;
    }

    /**
     * @return the dtCursoFim
     */
    public Datebox getDtCursoFim() {
        return dtCursoFim;
    }

    /**
     * @param dtCursoFim the dtCursoFim to set
     */
    public void setDtCursoFim(Datebox dtCursoFim) {
        this.dtCursoFim = dtCursoFim;
    }

    /**
     * @return the nuVagas
     */
    public Intbox getNuVagas() {
        return nuVagas;
    }

    /**
     * @param nuVagas the nuVagas to set
     */
    public void setNuVagas(Intbox nuVagas) {
        this.nuVagas = nuVagas;
    }

    /**
     * @return the dtInscricaoInicio
     */
    public Datebox getDtInscricaoInicio() {
        return dtInscricaoInicio;
    }

    /**
     * @param dtInscricaoInicio the dtInscricaoInicio to set
     */
    public void setDtInscricaoInicio(Datebox dtInscricaoInicio) {
        this.dtInscricaoInicio = dtInscricaoInicio;
    }

    /**
     * @return the dtInscricaoFim
     */
    public Datebox getDtInscricaoFim() {
        return dtInscricaoFim;
    }

    /**
     * @param dtInscricaoFim the dtInscricaoFim to set
     */
    public void setDtInscricaoFim(Datebox dtInscricaoFim) {
        this.dtInscricaoFim = dtInscricaoFim;
    }

    /**
     * @return the ut
     */
    public Utils getUt() {
        return ut;
    }

    /**
     * @param ut the ut to set
     */
    public void setUt(Utils ut) {
        this.ut = ut;
    }

    /**
     * @return the sqlUtil
     */
    public SQLUtil getSqlUtil() {
        return sqlUtil;
    }

    /**
     * @param sqlUtil the sqlUtil to set
     */
    public void setSqlUtil(SQLUtil sqlUtil) {
        this.sqlUtil = sqlUtil;
    }

    /**
     * @return the pesquisar
     */
    public Popup getPesquisa() {
        return pesquisa;
    }

    /**
     * @param pesquisa the pesquisar to set
     */
    public void setPesquisa(Popup pesquisa) {
        this.pesquisa = pesquisa;
    }

    /**
     * @return the vlCampo
     */
    public Listbox getVlCampo() {
        return vlCampo;
    }

    /**
     * @param vlCampo the vlCampo to set
     */
    public void setVlCampo(Listbox vlCampo) {
        this.vlCampo = vlCampo;
    }

    /**
     * @return the vlPesquisa
     */
    public Textbox getVlPesquisa() {
        return vlPesquisa;
    }

    /**
     * @param vlPesquisa the vlPesquisa to set
     */
    public void setVlPesquisa(Textbox vlPesquisa) {
        this.vlPesquisa = vlPesquisa;
    }

    /**
     * @return the resultados
     */
    public Listbox getResultados() {
        return resultados;
    }

    /**
     * @param resultados the resultados to set
     */
    public void setResultados(Listbox resultados) {
        this.resultados = resultados;
    }

    /**
     * @return the _resultadoSelecionado
     */
    public Curso getResultadoSelecionado() {
        return _resultadoSelecionado;
    }

    /**
     * @param resultadoSelecionado the _resultadoSelecionado to set
     */
    public void setResultadoSelecionado(Curso resultadoSelecionado) {
        this._resultadoSelecionado = resultadoSelecionado;
    }

    /**
     * @return the dao
     */
    public CursoDAO getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(CursoDAO dao) {
        this.dao = dao;
    }

    /**
     * @return the nmAnexo
     */
    public Textbox getNmAnexo() {
        return nmAnexo;
    }

    /**
     * @param nmAnexo the nmAnexo to set
     */
    public void setNmAnexo(Textbox nmAnexo) {
        this.nmAnexo = nmAnexo;
    }
}
