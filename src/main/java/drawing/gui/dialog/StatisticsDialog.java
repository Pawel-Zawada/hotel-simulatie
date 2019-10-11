package drawing.gui.dialog;

import drawing.gui.HTESlider;
import drawing.statistics.AbstractChartData;
import drawing.statistics.StatisticsObserver;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import simulation.Hotel;
import system.DataCollector;

import java.awt.*;

/**
 * Display statistical data on various hotel elements, like guest count, task queue
 */
public class StatisticsDialog extends Dialog {
    private DataCollector dataCollector;
    private Hotel hotel;

    // Task data storage for the x & y coordinates
    private TaskData taskData;
    private GuestData guestData;

    private XYChart chart;
    private XChartPanel xChartPanel; // Used to render the chart into

    public StatisticsDialog(Frame userInterface, Hotel hotel) {
        super("Statistics", userInterface);

        this.hotel = hotel;
        dataCollector = new DataCollector(hotel);

        taskData = new TaskData();
        guestData = new GuestData();

        // Slider making possible the changing the HTE value of the simulation.
        HTESlider hteSlider = new HTESlider(hotel);
        hteSlider.setAlignmentX(CENTER_ALIGNMENT);
        add(hteSlider);

        // Create chart with initial data
        chart = QuickChart.getChart("Total per tick", "Tick", "Tasks", "tasks", taskData.xData, taskData.yData);
        chart.addSeries("guests", guestData.xData, guestData.yData);

        // Display chart panel in dialog
        xChartPanel = new XChartPanel<>(chart);
        add(xChartPanel); // Add the panel into the dialog

        // Update statistics every tick
        hotel.getHotelTimer().addObserver(new StatisticsObserver(xChartPanel, new AbstractChartData[]{taskData, guestData}));
    }

    private class TaskData extends AbstractChartData {
        TaskData() {
            super("tasks");
        }

        @Override
        public double getYData() {
            return dataCollector.getNumberOfTasks();
        }

        @Override
        public double getXData() {
            return hotel.getHotelTimer().getHTEIteration();
        }
    }

    private class GuestData extends AbstractChartData {
        GuestData() {
            super("guests");
        }

        @Override
        public double getYData() {
            return dataCollector.getTotalOfGuests();
        }

        @Override
        public double getXData() {
            return hotel.getHotelTimer().getHTEIteration();
        }
    }
}