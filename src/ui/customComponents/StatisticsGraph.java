package ui.customComponents;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class StatisticsGraph extends JPanel {
    public StatisticsGraph() {
        CategoryDataset dataset = createDataset();

        // Create a JFreeChart using the dataset
        JFreeChart chart = ChartFactory.createBarChart(
                "Sample Chart", // Chart title
                "Category",      // X-axis label
                "Value",         // Y-axis label
                dataset           // Dataset
        );

        // Create a ChartPanel and set the chart
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 370));

        // Add the ChartPanel to the JPanel
        add(chartPanel);
    }

    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Add some sample data
        dataset.addValue(1.0, "Series1", "Category1");
        dataset.addValue(4.0, "Series1", "Category2");
        dataset.addValue(3.0, "Series1", "Category3");
        dataset.addValue(5.0, "Series2", "Category1");
        dataset.addValue(7.0, "Series2", "Category2");
        dataset.addValue(2.0, "Series2", "Category3");

        return dataset;
    }
}
