package com.ranjitkumarshetty.graphs;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CurryStatsBarChart extends JFrame {

    public CurryStatsBarChart(String title) {
        super(title);

        // Create dataset
        CategoryDataset dataset = createDataset();

        // Create chart
        JFreeChart chart = ChartFactory.createBarChart(
                "Stephen Curry Career Stats",  // Chart title
                "Season",                      // X-axis label
                "Stats per Game",              // Y-axis label
                dataset,                        // Data
                PlotOrientation.VERTICAL,
                true, true, false);

        // Add chart to panel
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String filePath = "stephen_curry_stats.csv";  // CSV file location

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) {  // Skip the header row
                    firstLine = false;
                    continue;
                }

                String[] values = line.split(",");
                String season = values[0];
                double points = Double.parseDouble(values[1]);
                double assists = Double.parseDouble(values[2]);
                double rebounds = Double.parseDouble(values[3]);

                dataset.addValue(points, "Points", season);
                dataset.addValue(assists, "Assists", season);
                dataset.addValue(rebounds, "Rebounds", season);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataset;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CurryStatsBarChart chart = new CurryStatsBarChart("Curry Stats Chart");
            chart.setSize(800, 600);
            chart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            chart.setVisible(true);
        });
    }
}