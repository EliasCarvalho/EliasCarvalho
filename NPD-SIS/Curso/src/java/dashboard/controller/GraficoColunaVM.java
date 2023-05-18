/*
 * Universidade Estadual do Paraná - Unespar
 * Núcleo de Tecnologia da Informação - NTI
 * Copyright (c) 2021 - Todos os direitos reservados.
 */
package dashboard.controller;

import org.ngi.zhighcharts.ZHighCharts;
import org.ngi.zhighcharts.SimpleExtXYModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.zkoss.zul.Window;

import projeto.DAO.InscricaoDAO;

/**
 *
 * @author pad>
 */
public class GraficoColunaVM extends Window {
    
    // GRÁFICO DE COLUNAS
    private ZHighCharts grafColuna;
    private SimpleExtXYModel modeloColuna = new SimpleExtXYModel();

    public void onCreate() throws Exception {
        
        InscricaoDAO ent = new InscricaoDAO();
        
        List<Object[]> rows = ent.consultarTotalInscrito();
        
        this.setGrafColuna((ZHighCharts) getFellow("grafColuna"));
        
        this.getGrafColuna().setTitle("Total de Inscritos");
        this.getGrafColuna().setType("column");

        StringBuilder rotulo = new StringBuilder();

        int a = 0;
        long volumeTotal = 0;

        for (Object[] row : rows) {
            rotulo.append("'");
            rotulo.append(row[0]);
            rotulo.append((a == rows.size() - 1) ? "'" : "', ");
            volumeTotal = volumeTotal + Long.parseLong(row[1].toString());
            a++;
        }

        getGrafColuna().setPlotOptions(
            "{column: { dataLabels: { enabled: true, formatter: function() { return this.y ; } } } }"
        );
        getGrafColuna().setOptions(
            "{lang: {decimalPoint: ',', thousandsSeparator: '.'} }" // [ T , D , B , E ]
        ); 
        getGrafColuna().setxAxisOptions(
            "{categories: [" + rotulo + "], gridLineWidth: 0, "
            + "labels: {enabled: true, style: {fontSize: 14, fontFamily: 'Verdana, sans-serif', color: '#000'}}}"
        );
        getGrafColuna().setyAxisOptions(
            "{ enabled: true, min: 0, gridLineWidth: 0, lineWidth: 1, overflow: 'justify' }"
        );
        getGrafColuna().setLegend(
            "{enabled: false}"
        );
        getGrafColuna().setTooltipFormatter(
            "function formatTooltip(obj) { "
            + "return obj.x + ' (<b>' + Highcharts.numberFormat((100 * obj.y) / " + volumeTotal + ", 2) +'%</b>)'; }"
        );
        getGrafColuna().setModel(modeloColuna);

        int x = 0;
        for (Object[] row : rows) {
            modeloColuna.addValue("Cursos", x, Long.parseLong(row[1].toString()));
            x++;
        }

        Map series = new HashMap();
        Map dataLabels = new HashMap();

        dataLabels.put("enabled", true);
        dataLabels.put("color", "#000");

        Map style = new HashMap();

        style.put("fontFamily", "Verdana, sans-serif");
        style.put("fontWeight", "bold");
        style.put("fontSize", 14);
        
        dataLabels.put("style", style);
        series.put("dataLabels", dataLabels);

        for (Object[] row : rows) {
            getGrafColuna().setSeriesOptions("Cursos", series);
        }

    }

    /**
     * @return the grafColuna
     */
    public ZHighCharts getGrafColuna() {
        return grafColuna;
    }

    /**
     * @param grafColuna the grafColuna to set
     */
    public void setGrafColuna(ZHighCharts grafColuna) {
        this.grafColuna = grafColuna;
    }

}