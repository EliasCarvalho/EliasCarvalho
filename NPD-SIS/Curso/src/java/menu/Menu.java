/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import org.zkoss.zul.Include;
import org.zkoss.zul.Window;


/**
 *
 * @author equipes
 */
public class Menu extends Window {
    //DECLARAÇÃO DOS CAMPOS DO FORMULÁRIO USE.ZUL

    private Window winMenu;
    private Include conteudo;

    //CONSTRUTOR DA CLASSE
    public Menu() {
    }

    //METODO EXECUTADO AO CRIAR O FORMULARIO ZK
    public void onCreate() {
        //ASSOCIAÇÃO DAS VARIAVEIS DA CLASSE COM OS COMPONENTES DO FORMULARIO ZK
        this.setWinMenu((Window) getFellow("winMenu"));
        this.setConteudo((Include) getFellow("conteudo"));
    }

    //METODO EXECUTADO AO CLICAR NO BOTAO
    
    public void abrirCurso() {
        this.getConteudo().setSrc("/paginas/projeto/cadastroCurso.zul");
    }
    
    public void abrirInscricao() {
        this.getConteudo().setSrc("/paginas/projeto/cadastroInscricao.zul");
    }

    public void abrirInscricaoRelatorio() {
        this.getConteudo().setSrc("/paginas/projeto/relatorioInscricao.zul");
    }

    
    public void abrirCursoListaRelatorio() {
        this.getConteudo().setSrc("/paginas/projeto/relatorioCursoLista.zul");
    }
    
    public void abrirCursoTipoRelatorio() {
        this.getConteudo().setSrc("/paginas/projeto/relatorioCursoTipo.zul");
    }
    
     public void abrirCursoPessoaRelatorio() {
        this.getConteudo().setSrc("/paginas/projeto/relatorioPessoaCurso.zul");
    }
    
    //************ GETTERS E SETTERS DA APLICAÇÃO ****************************//
    public Window getWinMenu() {
        return winMenu;
    }

    public void setWinMenu(Window winMenu) {
        this.winMenu = winMenu;
    }

    /**
     * @return the conteudo
     */
    public Include getConteudo() {
        return conteudo;
    }

    /**
     * @param conteudo the conteudo to set
     */
    public void setConteudo(Include conteudo) {
        this.conteudo = conteudo;
    }

}
