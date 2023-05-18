/*
 * Universidade Estadual do Paraná - Unespar
 * Núcleo de Tecnologia da Informação - NTI
 * Copyright (c) 2021 - Todos os direitos reservados.
 */
package dashboard.controller;

import org.ngi.zhighcharts.ZHighCharts;
import org.ngi.zhighcharts.SimpleExtXYModel;


import org.ngi.zhighcharts.ZHighCharts;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

import org.zkoss.zul.SimplePieModel;
import org.zkoss.zul.Window;
import projeto.DAO.InscricaoDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.zkoss.zul.Window;




import projeto.DAO.InscricaoDAO;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Div;
import org.zkoss.zul.Window;

/**
 *
 * @author Maike.Santos - Unespar <maike.santos@unespar.edu.br>
 */
public class GraficosVM extends Window {

    private ZHighCharts grafColuna;
    private SimpleExtXYModel modeloColuna = new SimpleExtXYModel();
    
    private ZHighCharts grafPizza;
    private SimplePieModel modeloPizza = new SimplePieModel();
    InscricaoDAO insc = new InscricaoDAO();

    public GraficosVM() throws Exception {
    }

    public void onCreate() throws Exception {

        this.setGrafColuna((ZHighCharts) getFellow("grafColuna"));
        this.setGrafPizza((ZHighCharts) getFellow("grafPizza"));


        carregarGraficos();
    }

    public void carregarGraficos() throws Exception {

        graficoColuna();
        graficoPizza();
        
        
        

    }

    public void graficoColuna() throws Exception {

       

        this.getGrafColuna().setTitle("Total de Inscritos");
        this.getGrafColuna().setType("column");

        StringBuilder rotulo = new StringBuilder();

        int a = 0;
        long volumeTotal = 0;

        List<Object[]> rows = insc.consultarTotalInscrito();
        
        for (Object[] row : rows) {
            rotulo.append("'");
            rotulo.append(row[0]);
            rotulo.append((a == rows.size() - 1) ? "'" : "', ");
            volumeTotal = volumeTotal + Long.parseLong(row[1].toString());
            a++;
        }

        getGrafColuna().setPlotOptions(
                "{column: { dataLabels: { enabled: true, formatter: function() { return this.y ; } } } }");
        getGrafColuna().setOptions(
                "{lang: {decimalPoint: ',', thousandsSeparator: '.'} }" // [ T , D , B , E ]
                );
        getGrafColuna().setxAxisOptions(
                "{categories: [" + rotulo + "], gridLineWidth: 0, "
                + "labels: {enabled: true, style: {fontSize: 14, fontFamily: 'Verdana, sans-serif', color: '#000'}}}");
        getGrafColuna().setyAxisOptions(
                "{ enabled: true, min: 0, gridLineWidth: 0, lineWidth: 1, overflow: 'justify' }");
        getGrafColuna().setLegend(
                "{enabled: false}");
        getGrafColuna().setTooltipFormatter(
                "function formatTooltip(obj) { "
                + "return obj.x + ' (<b>' + Highcharts.numberFormat((100 * obj.y) / " + volumeTotal + ", 2) +'%</b>)'; }");
        getGrafColuna().setModel(getModeloColuna());

        int x = 0;
        for (Object[] row : rows) {
            getModeloColuna().addValue("Cursos", x, Long.parseLong(row[1].toString()));
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

    public void graficoPizza() throws Exception {
        grafPizza.setTitle("Total de Inscritos");

        grafPizza.setType("pie");

        grafPizza.setTooltipFormatter("function formatTooltip(obj){"
                + "return '<b>' + obj.key + ': '+ Highcharts.numberFormat(obj.percentage, 2) +'%</b>'}");
        grafPizza.setPlotOptions("{pie:{"
                + "allowPointSelect: true, cursor: 'pointer',"
                + "dataLabels: { enabled: true, color: '#000000', connectorColor: '#000000',"
                + "formatter: function() { return '<b>'+ this.point.name +'</b><br>'+ this.y ; }}}}");
        grafPizza.setModel(modeloPizza);


        List<Object[]> rows = insc.consultarTotalInscrito();

        for (Object[] row : rows) {
            modeloPizza.setValue(row[0].toString(), Long.parseLong(row[1].toString()));
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

    /**
     * @return the modeloColuna
     */
    public SimpleExtXYModel getModeloColuna() {
        return modeloColuna;
    }

    /**
     * @param modeloColuna the modeloColuna to set
     */
    public void setModeloColuna(SimpleExtXYModel modeloColuna) {
        this.modeloColuna = modeloColuna;
    }

    /**
     * @return the grafPizza
     */
    public ZHighCharts getGrafPizza() {
        return grafPizza;
    }

    /**
     * @param grafPizza the grafPizza to set
     */
    public void setGrafPizza(ZHighCharts grafPizza) {
        this.grafPizza = grafPizza;
    }

    /**
     * @return the modeloPizza
     */
    public SimplePieModel getModeloPizza() {
        return modeloPizza;
    }

    /**
     * @param modeloPizza the modeloPizza to set
     */
    public void setModeloPizza(SimplePieModel modeloPizza) {
        this.modeloPizza = modeloPizza;
    }
}
