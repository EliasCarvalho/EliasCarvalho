package utilitarios;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;

public class LdapUtil {

    private String mail;
    private String nomeCompleto;
    private String primeiroNome;
    private String sobrenome;
    private final String msgUsuarioSenhaInvalidos = "ACESSO NEGADO: É NECESSÁRIO UM USUÁRIO E SENHA VÁLIDOS PARA ACESSO AO SISTEMA.";

    public LdapUtil() {
    }

    public void bind(String usuario, String senha) throws Exception {

        int ldapVersion = LDAPConnection.LDAP_V3;
        String ldapHost = "ldap.uem.br";
        int ldapPort = 389;
        String login = "uid=" + usuario + ",ou=People,dc=uem,dc=br";
        String password = senha;

        if (senha.equals("")) {
            throw new Exception("Senha em branco!");
        }

        LDAPConnection connection = new LDAPConnection();
        try {
            connection.connect(ldapHost, ldapPort);
        } catch (LDAPException e) {
            throw new Exception("Nao conseguiu conexao ao servidor LDAP.", e);
        }

        try {
            connection.bind(ldapVersion, login, password.getBytes("UTF-8"));
            buscaAtributos(connection, login);
        } catch (UnsupportedEncodingException e) {
            throw new Exception("Erro no bind/LDAP, encoding nao suportado.", e);
        } catch (LDAPException e) {
            throw new Exception("Usuário ou senha inválida!");
        } finally {
            try {
                connection.disconnect();
            } catch (LDAPException e) {
                throw new Exception("Erro ao desconectar do servidor LDAP.", e);
            }
        }
    }

    private void buscaAtributos(LDAPConnection connection, String login) throws Exception {
        String returnAttrs[] = {"displayName", "cn", "sn", "givenName", "Mail"};
        LDAPEntry entry = null;
        try {
            entry = connection.read(login, returnAttrs);
        } catch (LDAPException e) {
            throw new Exception("Erro ao obter atributos do usuário no LDAP.", e);
        }
        LDAPAttributeSet attributeSet = entry.getAttributeSet();
        Iterator allAttributes = attributeSet.iterator();
        LDAPAttribute attribute = null;
        String attributeName = null;
        String attributeValue = null;
        while (allAttributes.hasNext()) {
            attribute = (LDAPAttribute) allAttributes.next();
            attributeName = attribute.getName();
            if (attributeName.equalsIgnoreCase("cn")) {
                if ((attributeValue = attribute.getStringValue()) != null) {
                    nomeCompleto = attributeValue;
                }
            }
            if (attributeName.equalsIgnoreCase("givenName")) {
                if ((attributeValue = attribute.getStringValue()) != null) {
                    primeiroNome = attributeValue;
                }
            }
            if (attributeName.equalsIgnoreCase("sn")) {
                if ((attributeValue = attribute.getStringValue()) != null) {
                    sobrenome = attributeValue;
                }
            }
            if (attributeName.equalsIgnoreCase("Mail")) {
                if ((attributeValue = attribute.getStringValue()) != null) {
                    mail = attributeValue;
                }
            }
        }
    }

    /**
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @return the nomeCompleto
     */
    public String getNomeCompleto() {
        return nomeCompleto;
    }

    /**
     * @return the primeiroNome
     */
    public String getPrimeiroNome() {
        return primeiroNome;
    }

    /**
     * @return the sobrenome
     */
    public String getSobrenome() {
        return sobrenome;
    }
}
