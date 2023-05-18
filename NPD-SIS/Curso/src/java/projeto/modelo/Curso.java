/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author tde
 */
@Entity
@Table(name = "TDE.SIS_CURSO")

public class Curso implements Serializable {
    @Id
    @Basic(optional = false)
    @Column(name = "CD_CURSO")
    private long cdCurso;
    @Column(name = "DE_TITULO")
    private String deTitulo;
    @Column(name = "TP_CURSO")
    private Short tpCurso;
    @Column(name = "NU_CARGA")
    private Short nuCarga;
    @Column(name = "DT_CURSO_INICIO")
    @Temporal(TemporalType.DATE)
    private Date dtCursoInicio;
    @Column(name = "DT_CURSO_FIM")
    @Temporal(TemporalType.DATE)
    private Date dtCursoFim;
    @Column(name = "NU_VAGAS")
    private Short nuVagas;
    @Column(name = "DT_INSCRICAO_INICIO")
    @Temporal(TemporalType.DATE)
    private Date dtInscricaoInicio;
    @Column(name = "DT_INSCRICAO_FIM")
    @Temporal(TemporalType.DATE)
    private Date dtInscricaoFim;

    @Column(name = "NM_ANEXO")
    private String nmAnexo;
    
    public Curso() {
    }

    public long getCdCurso() {
        return cdCurso;
    }

    public void setCdCurso(long cdCurso) {
        this.cdCurso = cdCurso;
    }

    public String getDeTitulo() {
        return deTitulo;
    }

    public void setDeTitulo(String deTitulo) {
        this.deTitulo = deTitulo;
    }

    public Short getTpCurso() {
        return tpCurso;
    }

    public void setTpCurso(Short tpCurso) {
        this.tpCurso = tpCurso;
    }

    public Short getNuCarga() {
        return nuCarga;
    }

    public void setNuCarga(Short nuCarga) {
        this.nuCarga = nuCarga;
    }

    public Date getDtCursoInicio() {
        return dtCursoInicio;
    }

    public void setDtCursoInicio(Date dtCursoInicio) {
        this.dtCursoInicio = dtCursoInicio;
    }

    public Date getDtCursoFim() {
        return dtCursoFim;
    }

    public void setDtCursoFim(Date dtCursoFim) {
        this.dtCursoFim = dtCursoFim;
    }

    public Short getNuVagas() {
        return nuVagas;
    }

    public void setNuVagas(Short nuVagas) {
        this.nuVagas = nuVagas;
    }

    public Date getDtInscricaoInicio() {
        return dtInscricaoInicio;
    }

    public void setDtInscricaoInicio(Date dtInscricaoInicio) {
        this.dtInscricaoInicio = dtInscricaoInicio;
    }

    public Date getDtInscricaoFim() {
        return dtInscricaoFim;
    }

    public void setDtInscricaoFim(Date dtInscricaoFim) {
        this.dtInscricaoFim = dtInscricaoFim;
    }

    /**
     * @return the nmAnexo
     */
    public String getNmAnexo() {
        return nmAnexo;
    }

    /**
     * @param nmAnexo the nmAnexo to set
     */
    public void setNmAnexo(String nmAnexo) {
        this.nmAnexo = nmAnexo;
    }
    
}
