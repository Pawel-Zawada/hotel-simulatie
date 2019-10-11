package drawing.statistics;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import simulation.IObserver;

/**
 * Update the chart data on every tick event.
 */
public class StatisticsObserver implements IObserver {
    private AbstractChartData[] chartData;
    private XChartPanel xChartPanel;

    public StatisticsObserver(XChartPanel xChartPanel, AbstractChartData[] chartData) {
        this.xChartPanel = xChartPanel;
        this.chartData = chartData;
    }

    @Override
    public void observe() {
        for (AbstractChartData chartData : chartData) {
            // Update the x & y data variables with the newest values...
            chartData.updateData();
            // ...and push them into the chart series
            chartData.updateChart((XYChart) xChartPanel.getChart());
        }

        // Repaint the chart's panel
        xChartPanel.revalidate();
        xChartPanel.repaint();
    }
}
