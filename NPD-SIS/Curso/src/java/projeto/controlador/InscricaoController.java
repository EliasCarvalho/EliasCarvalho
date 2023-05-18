package projeto.controlador;

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
import projeto.DAO.InscricaoDAO;
import projeto.modelo.Inscricao;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import projeto.DAO.PessoaDAO;
import projeto.modelo.Pessoa;
import utilitarios.SQLUtil;
import utilitarios.Utils;
import utilitarios.ZkUtils;

/**
 *
 * @author equipes
 */
public class InscricaoController extends Window {

    //Definição das variáveis da view
    private ZkUtils zut = new ZkUtils();
    private Window winInscricao;
    private Label cdInscricao;
    private Listbox cdCurso;
    private Textbox cdPessoa;
    private Datebox dtInscricao;
    private Textbox fgCertificado;
    private Textbox nuFrequencia;
    private Textbox vlTextoPesquisaPessoa;
    //Definição das variáveis dos Popups
    private Popup pesquisarInscricao;
    private Popup pesquisarPessoa;
    private Listbox vlCampoPesquisaInscricao;
    private Textbox vlTextoPesquisaInscricao;
    private Listbox resultadosPesquisaInscricao;
    private Listbox resultadosPesquisaPessoa;
    //Chamadas de classes de consultas padrões
    private final Utils ut = new Utils();
    private final SQLUtil sqlUtil = new SQLUtil();
    //Definição do item da pesquisa
    private List _resultadosPesquisa = new ArrayList();
    private List _resultadosPesquisaP = new ArrayList();
    private Inscricao _resultadoSelecionado = new Inscricao();
    private Pessoa _resultadoSelecionadoPessoa = new Pessoa();

    //Inicialização dos mapeamentos ao carregar a página
    public void onCreate() {

        // Mapeamento da(s) window(s)
        this.setWinInscricao((Window) getFellow("winInscricao"));

        // Mapeamento das variáveis do arquivo
        this.setCdInscricao((Label) getFellow("cdInscricao"));
        this.setCdCurso((Listbox) getFellow("cdCurso"));
        this.setDtInscricao((Datebox) getFellow("dtInscricao"));
        this.setCdPessoa((Textbox) getFellow("cdPessoa"));
        this.setFgCertificado((Textbox) getFellow("fgCertificado"));
        this.setNuFrequencia((Textbox) getFellow("nuFrequencia"));

        // Mapeamento das Popups de pesquisa
        this.setPesquisarInscricao((Popup) getFellow("pesquisarInscricao"));
        this.setPesquisarPessoa((Popup) getFellow("pesquisarPessoa"));

        //Campos do Popup de pesquisa        
        this.setResultadosPesquisaInscricao((Listbox) getFellow("resultadosPesquisa"));
        this.setVlCampoPesquisaInscricao((Listbox) getFellow("vlCampo"));
        this.setVlTextoPesquisaInscricao((Textbox) getFellow("vlPesquisa"));

        //Campos do Popup de pesquisa pessoa 
        this.setResultadosPesquisaPessoa((Listbox) getFellow("resultadosPesquisaPessoa"));
        this.setVlTextoPesquisaPessoa((Textbox) getFellow("vlPesquisaPessoa"));

        carregarCursos();
    }

    public void carregarCursos() {

        ZkUtils zk = new ZkUtils();

        InscricaoDAO ent = new InscricaoDAO();

        Object[] obj = new Object[2];
        obj[0] = "0";
        obj[1] = "Selecione";

        List l = ent.consultarCursos();
        l.add(0, obj);

        //Metodo carregar listbox resulta em uma lista populada com 2 campos (Neste caso código e nome)
        zk.carregarListbox(getCdCurso(), l);
    }

    // Limpa os campos do formulário 
    public void limparCampos() {

        ZkUtils zkUtils = new ZkUtils();

        this.getCdInscricao().setValue("-1");
        //Função pronta para selecionar qualquer item da lista
        getZut().selecionarLista(getCdCurso(), 0);
        this.getCdPessoa().setRawValue(null);
        this.getDtInscricao().setRawValue(null);
        this.getFgCertificado().setRawValue(null);
        this.getNuFrequencia().setRawValue(null);
        this.getVlTextoPesquisaPessoa().setRawValue(null);

        //Função pronta para limpar Listbox
        zkUtils.limparListbox(this.getResultadosPesquisaPessoa());
    }

    public void pesquisarInscricao() {
        //Abre o Popup na posição central
        this.getPesquisarInscricao().open(this.pesquisarInscricao, "middle_center");
        //Coloca o foco no campo texto da pesquisa de inscricao
        this.getVlTextoPesquisaInscricao().focus();
    }

    public void pesquisarPessoa() {
        //Abre o Popup na posição central
        this.getPesquisarPessoa().open(this.pesquisarPessoa, "middle_center");
        //Coloca o foco no campo de texto da pesquisa pessoa
        this.getVlTextoPesquisaPessoa().focus();
    }

    public void executarPesquisaInscricao() {
        executarPesquisaInscricao(getVlCampoPesquisaInscricao().getSelectedItem().getValue().toString(), getVlTextoPesquisaInscricao().getValue());
    }

    public void executarPesquisaInscricao(String campo, String valor) {

        if (getVlCampoPesquisaInscricao().getSelectedCount() != 0 && getVlTextoPesquisaInscricao().getValue() != null) {
            //Limpa o listbox para gerar a nova consulta
            getResultadosPesquisaInscricao().getItems().clear();

            InscricaoDAO inscDAO = new InscricaoDAO();
            setResultadosPesquisa(inscDAO.pesquisar(campo, valor));
            int s = 0;

            //Iteração sobre o array que recebeu o resultado da pesquisa
            for (Iterator i = this._resultadosPesquisa.iterator(); i.hasNext();) {
                Object[] obj = (Object[]) i.next();

                Listitem item = new Listitem();
                Listcell cellCdInscricao = new Listcell();
                Listcell cellDeCurso = new Listcell();
                Listcell cellNmAluno = new Listcell();

                cellCdInscricao.setLabel(obj[0].toString());
                cellDeCurso.setLabel(obj[1].toString());
                cellNmAluno.setLabel(obj[2].toString());

                item.appendChild(cellCdInscricao);
                item.appendChild(cellNmAluno);
                item.appendChild(cellDeCurso);
                item.setValue(obj[0].toString());

                if (s == 0) {
                    item.setSelected(true);
                    s = 1;
                }

                item.setParent(getResultadosPesquisaInscricao());

            }

        }
    }

    public void executarPesquisarPessoa() {
        executarPesquisaPessoa(getVlTextoPesquisaPessoa().getValue());
    }

    public void executarPesquisaPessoa(String nome) {

        int s = 0;
        ZkUtils zk = new ZkUtils();

        PessoaDAO pessoaDao = new PessoaDAO();
        this.setResultadosPesquisaP(pessoaDao.pesquisarNome(nome));

        //Limpa Listbox dos resultados da pesquisa
        zk.limparListbox(this.getResultadosPesquisaPessoa());
        for (Iterator i = this._resultadosPesquisaP.iterator(); i.hasNext();) {
            Object[] obj = (Object[]) i.next();

            Listitem item = new Listitem();
            Listcell cellCdPessoa = new Listcell();
            Listcell cellNmPessoa = new Listcell();

            cellCdPessoa.setLabel(obj[0].toString());
            cellNmPessoa.setLabel(obj[1].toString());

            item.appendChild(cellCdPessoa);
            item.appendChild(cellNmPessoa);
            item.setValue(obj[0].toString());

            if (s == 0) {
                item.setSelected(true);
                s = 1;
            }
            item.setParent(getResultadosPesquisaPessoa());
        }
    }

    public void selecionarInscricao() {

        this._resultadoSelecionado.setCdInscricao(Long.parseLong(getResultadosPesquisaInscricao().getSelectedItem().getValue().toString()));

        InscricaoDAO inscDAO = new InscricaoDAO();
        this._resultadoSelecionado = (Inscricao) inscDAO.pesquisar(this._resultadoSelecionado, this._resultadoSelecionado.getCdInscricao());

        String nome = ((Listcell) this.getResultadosPesquisaInscricao().getSelectedItem().getChildren().get(1)).getLabel();

        this.getCdPessoa().setValue(nome);

        popularCampos();

        fecharPesquisaInscricao();

    }

    public void selecionarPessoa() {
        this._resultadoSelecionadoPessoa.setCdPessoa(Long.parseLong(this.getResultadosPesquisaPessoa().getSelectedItem().getValue().toString()));

        //Instância a classe pessoa e coloca o resultado da pesquisa na lista do tipo pessoa 
        PessoaDAO pessoaDAO = new PessoaDAO();
        this._resultadoSelecionadoPessoa = (Pessoa) pessoaDAO.pesquisar(this._resultadoSelecionadoPessoa, this._resultadoSelecionadoPessoa.getCdPessoa());

        //Pega o segundo campo pesquisado para popular o campo pesquisado
        String nome = ((Listcell) this.getResultadosPesquisaPessoa().getSelectedItem().getChildren().get(1)).getLabel();
        this.getCdPessoa().setValue(nome);
        //this.getCdPessoa().setValue(this.getResultadosPesquisaPessoa().getSelectedItem().getValue().toString());

        fecharPesquisaPessoa();
    }

    public void fecharPesquisaPessoa() {
        this.pesquisarPessoa.close();
    }

    public void fecharPesquisaInscricao() {
        this.pesquisarInscricao.close();

    }

    //Função p/ preencher campos de um item pesquisado
    public void popularCampos() {

        if (getResultadoSelecionado().getCdInscricao() > 0) {
            getCdInscricao().setValue(Long.toString(getResultadoSelecionado().getCdInscricao()));
            getZut().selecionarLista(getCdCurso(), this.getResultadoSelecionado().getCdCurso());

            this.getDtInscricao().setValue(this.getResultadoSelecionado().getDtInscricao());
            getFgCertificado().setValue(this.getResultadoSelecionado().getFgCertificado());
            getNuFrequencia().setValue(this.getResultadoSelecionado().getNuFrequencia().toString());

        }

    }

    public void gravar() throws ParseException {

        if (validarCampos()) {
            //criação da instancia das classes.
            
            Inscricao insc = new Inscricao();
            InscricaoDAO inscDao = new InscricaoDAO();

            insc.setCdCurso(Integer.parseInt(getCdCurso().getSelectedItem().getValue().toString()));
            insc.setCdPessoa(Integer.parseInt(String.valueOf(this.getResultadoSelecionadoPessoa().getCdPessoa())));
            insc.setDtInscricao(ut.strToDate(getDtInscricao().getText()));
            insc.setFgCertificado(getFgCertificado().getValue().toUpperCase());
            insc.setNuFrequencia(Integer.parseInt(getNuFrequencia().getValue()));

            if (Long.parseLong(getCdInscricao().getValue()) == -1) {
                long cdInscricaoProx = inscDao.pesquisarProxChave(insc);
                insc.setCdInscricao(cdInscricaoProx);
                inscDao.incluir(insc);
                Messagebox.show("Inscrição efetuada com sucesso", "Inscrição", Messagebox.OK, Messagebox.INFORMATION);

                this.getCdInscricao().setValue(String.valueOf(cdInscricaoProx));
            } else {
                insc.setCdInscricao(Long.parseLong(getCdInscricao().getValue()));
                inscDao.atualizar(insc);
                Messagebox.show("Inscrição atualizada com sucesso", "Inscrição", Messagebox.OK, Messagebox.INFORMATION);
            }

        }
    }

    public boolean validarCampos() throws ParseException {

        if (this.getCdCurso().getSelectedIndex() == 0) {
            Messagebox.show("Selecione o curso", "Validar", Messagebox.OK | Messagebox.CANCEL, Messagebox.INFORMATION);
            getCdCurso().focus();
            return false;
        }

        if (!zut.validarCampo(this.cdPessoa)) {
            return false;
        }

        return true;
    }

    public void excluir() {

        try {
            // Messagebox.show mostra a mensagem na tela do usuário, é possível utilizar para obter resposta para uma ação
            if (Messagebox.show("Deseja excluir a inscrição atual?", "Excluir Inscrição", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION) == Messagebox.YES) {
                if (!"-1".equals(getCdInscricao().getValue())) {

                    InscricaoDAO inscDao = new InscricaoDAO();
                    Inscricao insc = new Inscricao();
                    inscDao.excluir(insc, Long.parseLong(getCdInscricao().getValue()));
                    Messagebox.show("Inscrição excluída com sucesso", "Inscrição", Messagebox.OK, Messagebox.INFORMATION);

                    limparCampos();
                } else {

                    Messagebox.show("Número de inscrição inválida", "Inscrição", Messagebox.OK, Messagebox.INFORMATION);



                }
            }

        } catch (NumberFormatException ex) {
            Logger.getLogger(InscricaoController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Getters e Setters de cada componente zul
    public Window getWinInscricao() {
        return winInscricao;
    }

    public void setWinInscricao(Window winInscricao) {
        this.winInscricao = winInscricao;
    }

    public Label getCdInscricao() {
        return cdInscricao;
    }

    public void setCdInscricao(Label cdInscricao) {
        this.cdInscricao = cdInscricao;
    }

    public Listbox getCdCurso() {
        return cdCurso;
    }

    public void setCdCurso(Listbox cdCurso) {
        this.cdCurso = cdCurso;
    }

    public Textbox getCdPessoa() {
        return cdPessoa;
    }

    public void setCdPessoa(Textbox cdPessoa) {
        this.cdPessoa = cdPessoa;
    }

    public Datebox getDtInscricao() {
        return dtInscricao;
    }

    public void setDtInscricao(Datebox dtInscricao) {
        this.dtInscricao = dtInscricao;
    }

    public Popup getPesquisarInscricao() {
        return pesquisarInscricao;
    }

    public void setPesquisarInscricao(Popup pesquisarInscricao) {
        this.pesquisarInscricao = pesquisarInscricao;
    }

    public Listbox getResultadosPesquisaInscricao() {
        return resultadosPesquisaInscricao;
    }

    public void setResultadosPesquisaInscricao(Listbox resultadosPesquisaInscricao) {
        this.resultadosPesquisaInscricao = resultadosPesquisaInscricao;
    }

    public Inscricao getResultadoSelecionado() {
        return _resultadoSelecionado;
    }

    public void setResultadoSelecionado(Inscricao _resultadoSelecionado) {
        this._resultadoSelecionado = _resultadoSelecionado;
    }

    public Listbox getVlCampoPesquisaInscricao() {
        return vlCampoPesquisaInscricao;
    }

    public void setVlCampoPesquisaInscricao(Listbox vlCampoPesquisaInscricao) {
        this.vlCampoPesquisaInscricao = vlCampoPesquisaInscricao;
    }

    public Textbox getVlTextoPesquisaInscricao() {
        return vlTextoPesquisaInscricao;
    }

    public void setVlTextoPesquisaInscricao(Textbox vlTextoPesquisaInscricao) {
        this.vlTextoPesquisaInscricao = vlTextoPesquisaInscricao;
    }

    public List getResultadosPesquisa() {
        return _resultadosPesquisa;
    }

    public void setResultadosPesquisa(List _resultadosPesquisa) {
        this._resultadosPesquisa = _resultadosPesquisa;
    }

    public Textbox getFgCertificado() {
        return fgCertificado;
    }

    public void setFgCertificado(Textbox fgCertificado) {
        this.fgCertificado = fgCertificado;
    }

    public Textbox getNuFrequencia() {
        return nuFrequencia;
    }

    public void setNuFrequencia(Textbox nuFrequencia) {
        this.nuFrequencia = nuFrequencia;
    }

    public ZkUtils getZut() {
        return zut;
    }

    public void setZut(ZkUtils zut) {
        this.zut = zut;
    }

    public Popup getPesquisarPessoa() {
        return pesquisarPessoa;
    }

    public void setPesquisarPessoa(Popup pesquisarPessoa) {
        this.pesquisarPessoa = pesquisarPessoa;
    }

    public Listbox getResultadosPesquisaPessoa() {
        return resultadosPesquisaPessoa;
    }

    public void setResultadosPesquisaPessoa(Listbox resultadosPesquisaPessoa) {
        this.resultadosPesquisaPessoa = resultadosPesquisaPessoa;
    }

    public List getResultadosPesquisaP() {
        return _resultadosPesquisaP;
    }

    public void setResultadosPesquisaP(List _resultadosPesquisaP) {
        this._resultadosPesquisaP = _resultadosPesquisaP;
    }

    public Textbox getVlTextoPesquisaPessoa() {
        return vlTextoPesquisaPessoa;
    }

    public void setVlTextoPesquisaPessoa(Textbox vlTextoPesquisaPessoa) {
        this.vlTextoPesquisaPessoa = vlTextoPesquisaPessoa;
    }

    public Pessoa getResultadoSelecionadoPessoa() {
        return _resultadoSelecionadoPessoa;
    }

    public void setResultadoSelecionadoPessoa(Pessoa _resultadoSelecionadoPessoa) {
        this._resultadoSelecionadoPessoa = _resultadoSelecionadoPessoa;
    }
}
