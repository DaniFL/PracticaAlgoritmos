package modulo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import org.jfree.chart.ChartFactory;
import java.text.NumberFormat;
import dominio.*;

    public class Grafico {
    public static void main(String[] args) throws Exception {
        Lectura lectura = new Lectura();
        int [][] arrayTiemposTemp = new int [7][9];
        int[][] tiempos = lectura.leerTiempos();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 7; j++) {
                arrayTiemposTemp[j][i] = tiempos[j][i];
            }
        }

        DefaultXYDataset dataset = new DefaultXYDataset();
        dataset.addSeries("Aleatorio", new double[][] {{arrayTiemposTemp[0][0],arrayTiemposTemp[0][3],arrayTiemposTemp[0][6]}, { 100, 1000, 10000 }});
        dataset.addSeries("Ordenado", new double[][] {{ arrayTiemposTemp[0][1], arrayTiemposTemp[0][4], arrayTiemposTemp[0][7]    }, { 100, 1000, 10000 }});
        dataset.addSeries("Inverso", new double[][] {{ arrayTiemposTemp[0][2], arrayTiemposTemp[0][5], arrayTiemposTemp[0][8] }, { 100, 1000, 10000 }});
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.ORANGE);
        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesPaint(2, Color.GREEN);
        renderer.setSeriesStroke(0, new BasicStroke(2));
        renderer.setSeriesStroke(1, new BasicStroke(2));
        renderer.setSeriesStroke(2, new BasicStroke(2));
        JFreeChart chart = ChartFactory.createXYLineChart("BubbleSort", "Time", "Size", dataset);
        chart.getXYPlot().getRangeAxis().setRange(0, 10000);
        ((NumberAxis) chart.getXYPlot().getRangeAxis()).setNumberFormatOverride(new DecimalFormat("#'0'"));
        chart.getXYPlot().setRenderer(renderer);
        BufferedImage image = chart.createBufferedImage(600, 400);
        ImageIO.write(image, "png", new File("BubbleSort.png"));

        DefaultXYDataset dataset2 = new DefaultXYDataset();
        dataset2.addSeries("firefox", new double[][] {{ 100, 1000, 10000 }, {  }});
        dataset2.addSeries("ie", new double[][] {{ 100, 1000, 10000 }, { 67.7, 63.1, 60.2, 50.6, 41.1, 31.8, 27.6, 20.4, 17.3, 12.3, 8.1 }});
        dataset2.addSeries("chrome", new double[][] {{ 100, 1000, 10000 }, { 0.2, 6.4, 14.6, 25.3, 30.1, 34.3, 43.2, 47.3, 58.4 }});
        XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer();
        renderer2.setSeriesPaint(0, Color.ORANGE);
        renderer2.setSeriesPaint(1, Color.BLUE);
        renderer2.setSeriesPaint(2, Color.GREEN);
        renderer2.setSeriesStroke(0, new BasicStroke(2));
        renderer2.setSeriesStroke(1, new BasicStroke(2));
        renderer2.setSeriesStroke(2, new BasicStroke(2));
        JFreeChart chart2 = ChartFactory.createXYLineChart("QuickSort", "Size", "Time", dataset2);
        chart2.getXYPlot().getRangeAxis().setRange(0, 100);
        ((NumberAxis) chart2.getXYPlot().getRangeAxis()).setNumberFormatOverride(new DecimalFormat("#'%'"));
        chart2.getXYPlot().setRenderer(renderer2);
        BufferedImage image2 = chart2.createBufferedImage(600, 400);
        ImageIO.write(image2, "png", new File("QuickSort.png"));
        }
    }
