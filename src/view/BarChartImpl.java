package view;

import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.ui.ApplicationFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;

public class BarChartImpl extends JFrame implements BarChart {
  private final String xLabel;
  private final String yLabel;
  private final DefaultCategoryDataset dataset;
  private final String title;

  public BarChartImpl(String title, String xLabel, String yLabel) {
    super(title);
    this.title = title;
    this.xLabel = xLabel;
    this.yLabel = yLabel;
    dataset = new DefaultCategoryDataset();
  }

  public void createDataset(String xAxisValue, double yAxisValue, String pfName) {
    this.dataset.addValue(yAxisValue, pfName, xAxisValue);
  }

  public void drawGraph() {
    JFreeChart chart = ChartFactory.createBarChart(
            this.title, // Chart title
            this.xLabel, // X-Axis Label
            this.yLabel, // Y-Axis Label
            this.dataset
    );

    ChartPanel chartPanel = new ChartPanel(chart);
    CategoryPlot plot = (CategoryPlot) chart.getPlot();
    CategoryAxis domainAxis = plot.getDomainAxis();
    domainAxis.setCategoryLabelPositions(
            CategoryLabelPositions.createUpRotationLabelPositions(
                    Math.PI / 6.0));
    chartPanel.setPreferredSize(new java.awt.Dimension(1000, 500));
    this.setContentPane(chartPanel);
    this.pack();
    RefineryUtilities.centerFrameOnScreen(this);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setVisible(true);
  }

}
