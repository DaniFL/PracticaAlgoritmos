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
        dataset.addSeries("BubbleSort", new double[][] {{arrayTiemposTemp[0][0],arrayTiemposTemp[0][3],arrayTiemposTemp[0][6]}, { 100, 1000, 10000 }});
        dataset.addSeries("MergeSort", new double[][] {{arrayTiemposTemp[4][0], arrayTiemposTemp[4][3], arrayTiemposTemp[4][6]}, { 100, 1000, 10000 }});
        dataset.addSeries("SelectSort", new double[][] {{arrayTiemposTemp[1][0], arrayTiemposTemp[1][3], arrayTiemposTemp[1][6] }, { 100, 1000, 10000 }});
        dataset.addSeries("QuickSort", new double[][] {{arrayTiemposTemp[3][0],arrayTiemposTemp[3][3],arrayTiemposTemp[3][6]}, { 100, 1000, 10000 }});
        dataset.addSeries("DirectInsertion", new double[][] {{arrayTiemposTemp[2][0],arrayTiemposTemp[2][3],arrayTiemposTemp[2][6]}, { 100, 1000, 10000 }});
        dataset.addSeries("BusquedaLineal", new double[][] {{arrayTiemposTemp[5][0],arrayTiemposTemp[5][3],arrayTiemposTemp[5][6]}, { 100, 1000, 10000 }});
        dataset.addSeries("BusquedaBinaria", new double[][] {{arrayTiemposTemp[6][0],arrayTiemposTemp[6][3],arrayTiemposTemp[6][6]}, { 100, 1000, 10000 }});
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesPaint(0, Color.ORANGE);
        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesPaint(2, Color.GREEN);
        renderer.setSeriesPaint(3, Color.RED);
        renderer.setSeriesPaint(4, Color.YELLOW);
        renderer.setSeriesPaint(5, Color.CYAN);
        renderer.setSeriesPaint(6, Color.MAGENTA);

        renderer.setSeriesStroke(0, new BasicStroke(2));
        renderer.setSeriesStroke(1, new BasicStroke(2));
        renderer.setSeriesStroke(2, new BasicStroke(2));
        renderer.setSeriesStroke(3, new BasicStroke(2));
        renderer.setSeriesStroke(4, new BasicStroke(2));
        renderer.setSeriesStroke(5, new BasicStroke(2));
        renderer.setSeriesStroke(6, new BasicStroke(2));

        JFreeChart chart = ChartFactory.createXYLineChart("Aleatorio", "Time", "Size", dataset);
        chart.getXYPlot().getRangeAxis().setRange(0, 10000);
        ((NumberAxis) chart.getXYPlot().getRangeAxis()).setNumberFormatOverride(new DecimalFormat("#'0'"));
        chart.getXYPlot().setRenderer(renderer);
        BufferedImage image = chart.createBufferedImage(600, 400);
        ImageIO.write(image, "png", new File("Aleatorio.png"));

        DefaultXYDataset dataset2 = new DefaultXYDataset();
        dataset2.addSeries("BubbleSort", new double[][] {{arrayTiemposTemp[0][1],arrayTiemposTemp[0][4],arrayTiemposTemp[0][7]}, { 100, 1000, 10000 }});
        dataset2.addSeries("MergeSort", new double[][] {{arrayTiemposTemp[4][1], arrayTiemposTemp[4][4], arrayTiemposTemp[4][7]}, { 100, 1000, 10000 }});
        dataset2.addSeries("SelectSort", new double[][] {{arrayTiemposTemp[1][1], arrayTiemposTemp[1][4], arrayTiemposTemp[1][7] }, { 100, 1000, 10000 }});
        dataset2.addSeries("QuickSort", new double[][] {{arrayTiemposTemp[3][1],arrayTiemposTemp[3][4],arrayTiemposTemp[3][7]}, { 100, 1000, 10000 }});
        dataset2.addSeries("DirectInsertion", new double[][] {{arrayTiemposTemp[2][1],arrayTiemposTemp[2][4],arrayTiemposTemp[2][7]}, { 100, 1000, 10000 }});
        dataset2.addSeries("BusquedaLineal", new double[][] {{arrayTiemposTemp[5][1],arrayTiemposTemp[5][4],arrayTiemposTemp[5][7]}, { 100, 1000, 10000 }});
        dataset2.addSeries("BusquedaBinaria", new double[][] {{arrayTiemposTemp[6][1],arrayTiemposTemp[6][4],arrayTiemposTemp[6][7]}, { 100, 1000, 10000 }});
        XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer();

        renderer2.setSeriesPaint(0, Color.ORANGE);
        renderer2.setSeriesPaint(1, Color.BLUE);
        renderer2.setSeriesPaint(2, Color.GREEN);
        renderer2.setSeriesPaint(3, Color.RED);
        renderer2.setSeriesPaint(4, Color.YELLOW);
        renderer2.setSeriesPaint(5, Color.CYAN);
        renderer2.setSeriesPaint(6, Color.MAGENTA);

        renderer2.setSeriesStroke(0, new BasicStroke(2));
        renderer2.setSeriesStroke(1, new BasicStroke(2));
        renderer2.setSeriesStroke(2, new BasicStroke(2));
        renderer2.setSeriesStroke(3, new BasicStroke(2));
        renderer2.setSeriesStroke(4, new BasicStroke(2));
        renderer2.setSeriesStroke(5, new BasicStroke(2));
        renderer2.setSeriesStroke(6, new BasicStroke(2));

        JFreeChart chart2 = ChartFactory.createXYLineChart("Ordenado", "Time", "Size", dataset2);
        chart2.getXYPlot().getRangeAxis().setRange(0, 10000);
        ((NumberAxis) chart2.getXYPlot().getRangeAxis()).setNumberFormatOverride(new DecimalFormat("#'0'"));
        chart2.getXYPlot().setRenderer(renderer2);
        BufferedImage image2 = chart2.createBufferedImage(600, 400);
        ImageIO.write(image2, "png", new File("Ordenado.png"));

        DefaultXYDataset dataset3 = new DefaultXYDataset();
        dataset3.addSeries("BubbleSort", new double[][] {{arrayTiemposTemp[0][2],arrayTiemposTemp[0][5],arrayTiemposTemp[0][8]}, { 100, 1000, 10000 }});
        dataset3.addSeries("MergeSort", new double[][] {{arrayTiemposTemp[4][2], arrayTiemposTemp[4][5], arrayTiemposTemp[4][8]}, { 100, 1000, 10000 }});
        dataset3.addSeries("SelectSort", new double[][] {{arrayTiemposTemp[1][2], arrayTiemposTemp[1][5], arrayTiemposTemp[1][8] }, { 100, 1000, 10000 }});
        dataset3.addSeries("QuickSort", new double[][] {{arrayTiemposTemp[3][2],arrayTiemposTemp[3][5],arrayTiemposTemp[3][8]}, { 100, 1000, 10000 }});
        dataset3.addSeries("DirectInsertion", new double[][] {{arrayTiemposTemp[2][2],arrayTiemposTemp[2][5],arrayTiemposTemp[2][8]}, { 100, 1000, 10000 }});
        dataset3.addSeries("BusquedaLineal", new double[][] {{arrayTiemposTemp[5][2],arrayTiemposTemp[5][5],arrayTiemposTemp[5][8]}, { 100, 1000, 10000 }});
        dataset3.addSeries("BusquedaBinaria", new double[][] {{arrayTiemposTemp[6][2],arrayTiemposTemp[6][5],arrayTiemposTemp[6][8]}, { 100, 1000, 10000 }});
        XYLineAndShapeRenderer renderer3 = new XYLineAndShapeRenderer();

        renderer3.setSeriesPaint(0, Color.ORANGE);
        renderer3.setSeriesPaint(1, Color.BLUE);
        renderer3.setSeriesPaint(2, Color.GREEN);
        renderer3.setSeriesPaint(3, Color.RED);
        renderer3.setSeriesPaint(4, Color.YELLOW);
        renderer3.setSeriesPaint(5, Color.CYAN);
        renderer3.setSeriesPaint(6, Color.MAGENTA);

        renderer3.setSeriesStroke(0, new BasicStroke(2));
        renderer3.setSeriesStroke(1, new BasicStroke(2));
        renderer3.setSeriesStroke(2, new BasicStroke(2));
        renderer3.setSeriesStroke(3, new BasicStroke(2));
        renderer3.setSeriesStroke(4, new BasicStroke(2));
        renderer3.setSeriesStroke(5, new BasicStroke(2));
        renderer3.setSeriesStroke(6, new BasicStroke(2));

        JFreeChart chart3 = ChartFactory.createXYLineChart("Inverso", "Time", "Size", dataset3);
        chart3.getXYPlot().getRangeAxis().setRange(0, 10000);
        ((NumberAxis) chart3.getXYPlot().getRangeAxis()).setNumberFormatOverride(new DecimalFormat("#'0'"));
        chart3.getXYPlot().setRenderer(renderer3);
        BufferedImage image3 = chart3.createBufferedImage(600, 400);
        ImageIO.write(image3, "png", new File("Inverso.png"));

        DefaultXYDataset dataset4 = new DefaultXYDataset();
        dataset4.addSeries("BubbleSort", new double[][] {{ 100, 1000, 10000 },{136136, 3749879, 149560610}});
        dataset4.addSeries("MergeSort", new double[][] {{ 100, 1000, 10000 },{138103, 4601685,55290600}});
        dataset4.addSeries("SelectSort", new double[][] {{ 100, 1000, 10000 },{ 162648, 3339684, 77944782}});
        dataset4.addSeries("QuickSort", new double[][] {{ 100, 1000, 10000 }, {47015, 238818, 1214372}});
        dataset4.addSeries("DirectInsertion", new double[][] {{ 100, 1000, 10000}, {13264, 781030, 88632230}});
        dataset4.addSeries("BusquedaLineal", new double[][] {{ 100, 1000, 10000}, {10978, 15375, 131428}});
        dataset4.addSeries("BusquedaBinaria", new double[][] {{ 100, 1000, 10000}, {239434, 278822, 2752523}});
        XYLineAndShapeRenderer renderer4 = new XYLineAndShapeRenderer();

        renderer4.setSeriesPaint(0, Color.ORANGE);
        renderer4.setSeriesPaint(1, Color.BLUE);
        renderer4.setSeriesPaint(2, Color.GREEN);
        renderer4.setSeriesPaint(3, Color.RED);
        renderer4.setSeriesPaint(4, Color.YELLOW);
        renderer4.setSeriesPaint(5, Color.CYAN);
        renderer4.setSeriesPaint(6, Color.MAGENTA);

        renderer4.setSeriesStroke(0, new BasicStroke(2));
        renderer4.setSeriesStroke(1, new BasicStroke(2));
        renderer4.setSeriesStroke(2, new BasicStroke(2));
        renderer4.setSeriesStroke(3, new BasicStroke(2));
        renderer4.setSeriesStroke(4, new BasicStroke(2));
        renderer4.setSeriesStroke(5, new BasicStroke(2));
        renderer4.setSeriesStroke(6, new BasicStroke(2));

        JFreeChart chart4 = ChartFactory.createXYLineChart("Prueba", "Size", "Time", dataset4);
        chart4.getXYPlot().getRangeAxis().setRange(0, 1000000);
        ((NumberAxis) chart4.getXYPlot().getRangeAxis()).setNumberFormatOverride(new DecimalFormat("#'0'"));
        chart4.getXYPlot().setRenderer(renderer4);
        BufferedImage image4 = chart4.createBufferedImage(600, 400);
        ImageIO.write(image4, "png", new File("Prueba.png"));
        }
    }

