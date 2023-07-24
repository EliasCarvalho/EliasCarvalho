package tde.curso.projeto.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author equipes
 */
@Entity
@Table(name = "TDE.SIS_INSCRICAO")
public class Inscricao implements Serializable {

    //@Id define a chave primária da tabela (Obrigatório)
    @Id
    @Column(name = "CD_INSCRICAO")
    private long cdInscricao;
    @Column(name = "CD_CURSO")
    private Integer cdCurso;
    @Column(name = "CD_PESSOA")
    private Integer cdPessoa;
    @Column(name = "DT_INSCRICAO")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dtInscricao;
    @Column(name = "NU_FREQUENCIA")
    private Integer nuFrequencia;
    @Column(name = "FG_CERTIFICADO")
    private String fgCertificado;

    //************ GETTERS E SETTERS DO MODELO ****************************//
    public long getCdInscricao() {
        return cdInscricao;
    }

    public void setCdInscricao(long cdInscricao) {
        this.cdInscricao = cdInscricao;
    }

    public Integer getCdCurso() {
        return cdCurso;
    }

    public void setCdCurso(Integer cdCurso) {
        this.cdCurso = cdCurso;
    }

    public Integer getCdPessoa() {
        return cdPessoa;
    }

    public void setCdPessoa(Integer cdPessoa) {
        this.cdPessoa = cdPessoa;
    }

    public Date getDtInscricao() {
        return dtInscricao;
    }

    public void setDtInscricao(Date dtInscricao) {
        this.dtInscricao = dtInscricao;
    }

    public Integer getNuFrequencia() {
        return nuFrequencia;
    }

    public void setNuFrequencia(Integer nuFrequencia) {
        this.nuFrequencia = nuFrequencia;
    }

    public String getFgCertificado() {
        return fgCertificado;
    }

    public void setFgCertificado(String fgCertificado) {
        this.fgCertificado = fgCertificado;
    }
}
