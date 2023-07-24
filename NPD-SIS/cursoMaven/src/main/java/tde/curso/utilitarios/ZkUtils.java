/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tde.curso.utilitarios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timebox;

/**
 *
 * @author walter
 */
public class ZkUtils {

    private String caminhoReal = "";

    public void selecionarLista(Listbox lb, long valor) {

        for (int i = 0; i < lb.getItemCount(); i++) {
            Listitem li = lb.getItemAtIndex(i);
            try {
                if (li.getValue().toString().equals(Long.toString(valor))) {
                    lb.selectItem(li);
                    return;
                }

            } catch (Exception ex) {
            }
        }

    }

    public void selecionarLista(Listbox lb, String valor) {

        for (int i = 0; i < lb.getItemCount(); i++) {
            Listitem li = lb.getItemAtIndex(i);
            try {
                if (li.getValue().equals(valor)) {
                    lb.selectItem(li);
                    return;
                }

            } catch (Exception ex) {
            }
        }

    }

    public void selecionarListaLabel(Listbox lb, String valor) {

        for (int i = 0; i < lb.getItemCount(); i++) {
            Listitem li = lb.getItemAtIndex(i);
            Listcell cell = (Listcell) li.getChildren().get(0);
            String scell = cell.getLabel().substring(0, 10);
            try {
                if (scell.equals(valor)) {
                    lb.selectItem(li);
                    return;
                }

            } catch (Exception ex) {
            }
        }

    }

    public void limparListbox(Listbox lb) {

        lb.getItems().clear();

    }

    public void carregarListbox(Listbox lb, List resultadosPesquisa) {
        int s = 0;
        for (Iterator i = resultadosPesquisa.iterator(); i.hasNext();) {
            Object[] obj = (Object[]) i.next();

            Listitem item = new Listitem();
            Listcell cellCodigo = new Listcell();
            Listcell cellDescricao = new Listcell();
            cellDescricao.setStyle("line-height: 100%;");

            cellCodigo.setLabel(obj[0].toString());
            cellDescricao.setLabel(obj[1].toString());

            item.appendChild(cellCodigo);
            item.appendChild(cellDescricao);
            item.setValue(obj[0].toString());
            item.setLabel(obj[1].toString());

            if (s == 0) {
                item.setSelected(true);
                s = 1;
            }

            item.setParent(lb);

        }

    }

    public void lerArquivo(org.zkoss.util.media.Media media, String nomeDoArquivo, Listbox lb) throws Exception, FileNotFoundException, IOException {

        File arquivo;

        arquivo = new File(nomeDoArquivo);
        FileOutputStream fos = new FileOutputStream(arquivo);
        String texto = media.getStringData();
        fos.write(texto.getBytes());
        fos.close();

        File arq = new File(nomeDoArquivo); //Criamos um nome para o arquivo  
        try {
            //Indicamos o arquivo que será lido
            FileReader fileReader = new FileReader(arq);

            //Criamos o objeto bufferReader que nos
            // oferece o método de leitura readLine()
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //String que irá receber cada linha do arquivo
            String linha = "";

            //Fazemos um loop linha a linha no arquivo,
            // enquanto ele seja diferente de null.
            //O método readLine() devolve a linha na
            // posicao do loop para a variavel linha.
            while ((linha = bufferedReader.readLine()) != null) {
                //Aqui imprimimos a linha
                lb.appendItem(linha, linha);
            }

            //liberamos o fluxo dos objetos ou fechamos o arquivo
            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void gravarArquivo(org.zkoss.util.media.Media media, String nomeDoArquivo) throws Exception, FileNotFoundException, IOException {

        File file = new File(nomeDoArquivo); //Criamos um nome para o arquivo  

        InputStream inputStream = media.getStreamData();
        OutputStream out = new FileOutputStream(file);
        byte buf[] = new byte[1024];
        int len;
        while ((len = inputStream.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        out.close();
        inputStream.close();

    }

    public boolean validarCampo(Component c) {
        boolean r = true;
        String classe = c.getWidgetClass();

        if (classe.contains("Textbox")) {
            Textbox tb = (Textbox) c;
            if (tb.getValue() == null || tb.getValue().equals("")) {
                Messagebox.show("Campo obrigatório!", "Validação", Messagebox.OK | Messagebox.CANCEL, Messagebox.INFORMATION);
                tb.focus();
                return false;
            }
        }

        if (classe.contains("Intbox")) {
            Intbox tb = (Intbox) c;
            if (tb.getValue() == null) {
                Messagebox.show("Campo obrigatório!", "Validação", Messagebox.OK | Messagebox.CANCEL, Messagebox.INFORMATION);
                tb.focus();
                return false;
            }
        }

        if (classe.contains("Longbox")) {
            Longbox tb = (Longbox) c;
            if (tb.getValue() == null) {
                Messagebox.show("Campo obrigatório!", "Validação", Messagebox.OK | Messagebox.CANCEL, Messagebox.INFORMATION);
                tb.focus();
                return false;
            }
        }

        if (classe.contains("Decimalbox")) {
            Decimalbox tb = (Decimalbox) c;
            if (tb.getValue() == null) {
                Messagebox.show("Campo obrigatório!", "Validação", Messagebox.OK | Messagebox.CANCEL, Messagebox.INFORMATION);
                tb.focus();
                return false;
            }
        }

        if (classe.contains("Datebox")) {
            Datebox tb = (Datebox) c;
            if (tb.getText() == null || tb.getText().equals("")) {
                Messagebox.show("Campo obrigatório!", "Validação", Messagebox.OK | Messagebox.CANCEL, Messagebox.INFORMATION);
                tb.focus();
                return false;
            }
        }

        if (classe.contains("Timebox")) {
            Timebox tb = (Timebox) c;
            if (tb.getText() == null || tb.getText().equals("")) {
                Messagebox.show("Campo obrigatório!", "Validação", Messagebox.OK | Messagebox.CANCEL, Messagebox.INFORMATION);
                tb.focus();
                return false;
            }
        }

        return r;
    }

    public void popularCampo(Component c, Object vlr) {
        Utils ut = new Utils();

        try {
            String valor = (String) vlr.toString();

            if (valor != null && !valor.equals("")) {

                String classe = c.getWidgetClass();

                if (classe.contains("Textbox")) {
                    Textbox tb = (Textbox) c;
                    tb.setValue(valor);
                }

                if (classe.contains("Longbox")) {
                    Longbox tb = (Longbox) c;
                    tb.setValue(Long.parseLong(valor));
                }

                if (classe.contains("Intbox")) {
                    Intbox tb = (Intbox) c;
                    tb.setValue(Integer.parseInt(valor));
                }

                if (classe.contains("Decimalbox")) {
                    Decimalbox tb = (Decimalbox) c;
                    tb.setValue(BigDecimal.valueOf(Double.parseDouble(valor)));
                }

                if (classe.contains("Datebox")) {
                    Datebox tb = (Datebox) c;
                    tb.setValue(ut.strToDate(ut.dataNormal(valor)));
                }

                if (classe.contains("Timebox")) {
                    Timebox tb = (Timebox) c;
                    tb.setText(valor);
                }
                if (classe.contains("Label")) {
                    Label tb = (Label) c;
                    tb.setValue(valor);
                }


            }
        } catch (Exception e) {
        }
    }

    public String getCaminhoReal() {
        return caminhoReal;
    }

    public void setCaminhoReal(String caminhoReal) {
        this.caminhoReal = caminhoReal;
    }

    public boolean verificaData(String data) {
        return false;
    }

    public boolean verificaEmail(String email) {
        return false;
    }
}
