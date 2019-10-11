package drawing.statistics;

import org.knowm.xchart.XYChart;

import java.util.ArrayList;

public abstract class AbstractChartData {
    public ArrayList<Double> xData = new ArrayList<>();
    public ArrayList<Double> yData = new ArrayList<>();
    private String seriesName; // Used for updating the corresponding chart series.

    public AbstractChartData(String seriesName) {
        this.seriesName = seriesName;
        updateData();
    }

    /**
     * Add the latest x & y values to the local storage variables.
     */
    void updateData() {
        xData.add(getXData());
        yData.add(getYData());
    }

    public abstract double getYData();

    public abstract double getXData();

    /**
     * Update the chart's corresponding series with the currently stored data.
     */
    void updateChart(XYChart chart) {
        chart.updateXYSeries(seriesName, xData, yData, null);
    }
}