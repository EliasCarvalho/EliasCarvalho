/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tde.curso.projeto.controlador;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Window;
import tde.curso.utilitarios.Utils;
import tde.curso.utilitarios.ZkUtils;
import tde.curso.utilitarios.ConexaoDB2;

/**
 *
 * @author equipes
 */
public class CursoListaRelatorios extends Window {
    //*** CRIAÇÃO DAS VÁRIAVEIS PARA POPULAR OS COMBOS ************//
    //variavel necessaria para criar o combo para listar as classes e vincular com grupos
    //VARIAVEIS PARA CRIAR A LISTA DE RESULTADOS DA PESQUISA

    //DECLARAÇÃO DOS CAMPOS ORIUNDOS DO FORMULÁRIO
    private Window win;
    //CLASSE BÁSICA DE PERSISTÊNCIA ASSOCIADA A ESSE CONTROLADOR ESPECÍFICO
    private final Utils ut = new Utils();
    private final ZkUtils zut = new ZkUtils();
    private String caminhoReal = "";

    //CONSTRUTOR DA CLASSE - É EXECUTADO QDO A CLASSE É INSTANCIADA
    public CursoListaRelatorios() {
    }

    //METODO EXECUTADO AO CRIAR O FORMULARIO ZK
    public void onCreate() {

        //ASSOCIAÇÃO DAS VARIAVEIS COM OS CAMPOS DO FORMULARIO 
        this.setWin((Window) getFellow("win"));

    }

    // CARGA DOS DADOS INICIAIS PARA O FORMULARIO
    //****************** EVENTOS DE MANUTENÇÃO DOS DADOS NO BANCO DE DADOS ***********************
    public void relatorioCursoLista() throws ServletException, FileNotFoundException {
        String arquivoPdf = "";
        HttpServletRequest request = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
        String caminhoReal = request.getRealPath("");
        String destino = caminhoReal + "/relatorios/";
        
        String destinoBanner = caminhoReal + "/images/";

        try {

            //Conexão Padrão DB2
            ConexaoDB2 cb = new ConexaoDB2();

            //Caminho Padrão - Não mexer
            //Nome do relatório compilado - que deve estar dentro da pasta
            String arquivoJasper = destino + "cursoLista.jasper";
            //Procedimento padrão para atribuir o caminho do relatório compilado para o sistema
            // determina o nome do arquivo temporario
            arquivoPdf = "relCursoLista" + "_" + ut.hora();

            //Variável para a criação dos parâmetros do relatório
            HashMap parametro = new HashMap();
            //Parâmetros Padrões para as Imagens do relatório - logo da UEM e o Paraná

            //parametro.put("banner", destino + "banner.jpg");
            parametro.put("banner", destinoBanner + "header.png");

            //******** INÍCIO DA DEFINIÇÃO DOS PARÂMETROS DO RELATÓRIO*****************************

            Connection con = cb.conectaDB2();

            JasperPrint impressao = JasperFillManager.fillReport(arquivoJasper, parametro, con);

            arquivoPdf = arquivoPdf + ".pdf";
            JasperExportManager.exportReportToPdfFile(impressao, destino + arquivoPdf);

            con.close();
            arquivoPdf = "/relatorios/" + arquivoPdf;

        } catch (Exception e) {

            throw new ServletException(e);
        }

        
//        Executions.getCurrent().sendRedirect(arquivoPdf, "relatorios");
        Filedownload.save(arquivoPdf, null);
    }

    /**
     * @return the winInscricaoRel
     */
    public Window getWin() {
        return win;
    }

    /**
     * @param win the winInscricaoRel to set
     */
    public void setWin(Window win) {
        this.win = win;
    }

    /**
     * @return the caminhoReal
     */
    public String getCaminhoReal() {
        return caminhoReal;
    }

    /**
     * @param caminhoReal the caminhoReal to set
     */
    public void setCaminhoReal(String caminhoReal) {
        this.caminhoReal = caminhoReal;
    }
}
