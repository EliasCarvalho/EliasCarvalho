package projeto.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author equipes
 */
@Entity
@Table(name = "TDE.SIS_PESSOA")
public class Pessoa implements Serializable {

    //@Id define a chave primária da tabela (Obrigatório)
    @Id
    @Column(name = "CD_PESSOA")
    private long cdPessoa;
    @Column(name = "TP_PESSOA")
    private String tpPessoa;
    @Column(name = "FG_SEXO")
    private String fgSexo;
    @Column(name = "NM_PESSOA")
    private String nmPessoa;
    @Column(name = "NU_RG")
    private String nuRg;
    @Column(name = "NM_RGEXPED")
    private String nmRgExped;
    @Column(name = "NU_CPF_CNPJ")
    private String NuCpfCnpj;
    @Column(name = "DE_ENDERECO")
    private String deEndereco;
    @Column(name = "DE_COMPLEMENTO")
    private String deComplemento;
    @Column(name = "NM_BAIRRO")
    private String nmBairro;
    @Column(name = "NM_CIDADE")
    private String nmCidade;
    @Column(name = "NM_UF")
    private String nmUf;
    @Column(name = "NU_CEP")
    private String nuCep;
    @Column(name = "NU_FONE")
    private String nuFone;
    @Column(name = "NU_CELULAR")
    private String nuCelular;
    @Column(name = "LT_EMAIL")
    private String ltEmail;

    //************ GETTERS E SETTERS DO MODELO ****************************//
    public long getCdPessoa() {
        return cdPessoa;
    }

    public void setCdPessoa(long cdPessoa) {
        this.cdPessoa = cdPessoa;
    }

    public String getTpPessoa() {
        return tpPessoa;
    }

    public void setTpPessoa(String tpPessoa) {
        this.tpPessoa = tpPessoa;
    }

    public String getFgSexo() {
        return fgSexo;
    }

    public void setFgSexo(String fgSexo) {
        this.fgSexo = fgSexo;
    }

    public String getNmPessoa() {
        return nmPessoa;
    }

    public void setNmPessoa(String nmPessoa) {
        this.nmPessoa = nmPessoa;
    }

    public String getNuRg() {
        return nuRg;
    }

    public void setNuRg(String nuRg) {
        this.nuRg = nuRg;
    }

    public String getNmRgExped() {
        return nmRgExped;
    }

    public void setNmRgExped(String nmRgExped) {
        this.nmRgExped = nmRgExped;
    }

    public String getNuCpfCnpj() {
        return NuCpfCnpj;
    }

    public void setNuCpfCnpj(String NuCpfCnpj) {
        this.NuCpfCnpj = NuCpfCnpj;
    }

    public String getDeEndereco() {
        return deEndereco;
    }

    public void setDeEndereco(String deEndereco) {
        this.deEndereco = deEndereco;
    }

    public String getDeComplemento() {
        return deComplemento;
    }

    public void setDeComplemento(String deComplemento) {
        this.deComplemento = deComplemento;
    }

    public String getNmBairro() {
        return nmBairro;
    }

    public void setNmBairro(String nmBairro) {
        this.nmBairro = nmBairro;
    }

    public String getNmCidade() {
        return nmCidade;
    }

    public void setNmCidade(String nmCidade) {
        this.nmCidade = nmCidade;
    }

    public String getNmUf() {
        return nmUf;
    }

    public void setNmUf(String nmUf) {
        this.nmUf = nmUf;
    }

    public String getNuCep() {
        return nuCep;
    }

    public void setNuCep(String nuCep) {
        this.nuCep = nuCep;
    }

    public String getNuFone() {
        return nuFone;
    }

    public void setNuFone(String nuFone) {
        this.nuFone = nuFone;
    }

    public String getNuCelular() {
        return nuCelular;
    }

    public void setNuCelular(String nuCelular) {
        this.nuCelular = nuCelular;
    }

    public String getLtEmail() {
        return ltEmail;
    }

    public void setLtEmail(String ltEmail) {
        this.ltEmail = ltEmail;
    }
}
