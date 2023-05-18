/*
 * Universidade Estadual do Paraná - Unespar
 * Núcleo de Tecnologia da Informação - NTI
 * Copyright (c) 2021 - Todos os direitos reservados.
 */
package dashboard.controller;

import org.ngi.zhighcharts.ZHighCharts;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

import org.zkoss.zul.SimplePieModel;
import org.zkoss.zul.Window;
import projeto.DAO.InscricaoDAO;

/**
 *
 * @author Maike.Santos - Unespar <maike.santos@unespar.edu.br>
 */
@SuppressWarnings("serial")
public class GraficoPizzaVM extends Window {

    // GRÁFICO DE PIZZA
    private ZHighCharts grafPizza;
    private SimplePieModel modeloPizza = new SimplePieModel();

    public void onCreate() throws Exception {

        grafPizza = (ZHighCharts) getFellow("graficoPizza");
        grafPizza.setTitle("Usuários por <i>Campus</i>");

        grafPizza.setType("pie");

        grafPizza.setTooltipFormatter("function formatTooltip(obj){"
                + "return '<b>' + obj.key + ': '+ Highcharts.numberFormat(obj.percentage, 2) +'%</b>'}");
        grafPizza.setPlotOptions("{pie:{"
                + "allowPointSelect: true, cursor: 'pointer',"
                + "dataLabels: { enabled: true, color: '#000000', connectorColor: '#000000',"
                + "formatter: function() { return '<b>'+ this.point.name +'</b><br>'+ this.y ; }}}}");
        grafPizza.setModel(modeloPizza);

        InscricaoDAO ent = new InscricaoDAO();

        List<Object[]> rows = ent.consultarTotalInscrito();

        for (Object[] row : rows) {
            modeloPizza.setValue((String) row[0], (long) row[1]);
        }
    }
}
