package utilitarios;

/**
 *
 * @author equipes
 */
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.*;

public class Email {

    private String de = "";
    private String para = "";
    private String assunto = "";
    private String texto = "";
    private String caminho = "";
    private String nomeAnexo = "";

    public void sendEmail() {
        try {
            enviaEmailSimples();  

        } catch (EmailException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void enviaEmailSimples() throws EmailException {

        SimpleEmail email = new SimpleEmail();
        email.setHostName("smtp.uem.br"); // o servidor SMTP para envio do e-mail
        email.addTo(getPara(), getPara()); //destinatário
        email.setFrom(getDe(), "Universidade Estadual de Maringá"); // remetente           
        email.setSubject(getAssunto()); // assunto do e-mail  
        email.setMsg(getTexto()); //conteudo do e-mail  
        email.setSmtpPort(25);
        email.setCharset("UTF-8");
        email.send();
            
    }

    /**
     * envia email com arquivo anexo
     * @throws EmailException
     */
    public void enviaEmailComAnexo() throws EmailException {

        // cria o anexo 1.
        EmailAttachment anexo1 = new EmailAttachment();
        anexo1.setPath(getCaminho() + getNomeAnexo());
        anexo1.setDisposition(EmailAttachment.ATTACHMENT);
        anexo1.setDescription("Email que envia anexo");
        anexo1.setName(getNomeAnexo());

        // configura o email
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName("smtp.uem.br"); // o servidor SMTP para envio do e-mail

        email.addTo(getPara(), getPara()); //destinatário
        email.setFrom(getDe(), "Universidade Estadual de Maringá"); // remetente           
        email.setSubject(getAssunto()); // assunto do e-mail  
        email.setMsg(getTexto()); //conteudo do e-mail           

        email.setSmtpPort(25);
        email.setCharset("UTF-8");

        // adiciona arquivo(s) anexo(s)
        email.attach(anexo1);
        // envia o email
        email.send();
    }

    /**
     * Envia email no formato HTML
     * @throws EmailException
     * @throws MalformedURLException
     */
    public void enviaEmailFormatoHtml() throws EmailException, MalformedURLException {

        HtmlEmail email = new HtmlEmail();

        // adiciona uma imagem ao corpo da mensagem e retorna seu id
        URL url = new URL("http://www.apache.org/images/asf_logo_wide.gif");
        String cid = email.embed(url, "Apache logo");

        // configura a mensagem para o formato HTML
        email.setHtmlMsg("<html>Logo do Apache - <img ></html>");

        // configure uma mensagem alternativa caso o servidor não suporte HTML
        email.setTextMsg("Seu servidor de e-mail não suporta mensagem HTML");
        email.setHostName("smtp.uem.br"); // o servidor SMTP para envio do e-mail

        email.addTo(getPara(), getPara()); //destinatário
        email.setFrom(getDe(), "Universidade Estadual de Maringá"); // remetente           
        email.setSubject(getAssunto()); // assunto do e-mail  
        email.setMsg(getTexto()); //conteudo do e-mail           

        email.setSmtpPort(25);
        email.setCharset("UTF-8");
        email.send();

    }

    /**
     * @return the de
     */
    public String getDe() {
        return de;
    }

    /**
     * @param de the de to set
     */
    public void setDe(String de) {
        this.de = de;
    }

    /**
     * @return the para
     */
    public String getPara() {
        return para;
    }

    /**
     * @param para the para to set
     */
    public void setPara(String para) {
        this.para = para;
    }

    /**
     * @return the assunto
     */
    public String getAssunto() {
        return assunto;
    }

    /**
     * @param assunto the assunto to set
     */
    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    /**
     * @return the texto
     */
    public String getTexto() {
        return texto;
    }

    /**
     * @param texto the texto to set
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }

    /**
     * @return the caminho
     */
    public String getCaminho() {
        return caminho;
    }

    /**
     * @param caminho the caminho to set
     */
    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    /**
     * @return the nomeAnexo
     */
    public String getNomeAnexo() {
        return nomeAnexo;
    }

    /**
     * @param nomeAnexo the nomeAnexo to set
     */
    public void setNomeAnexo(String nomeAnexo) {
        this.nomeAnexo = nomeAnexo;
    }
}
