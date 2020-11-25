/**
 * Created by hug.
 */
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Experiments {
    public static void experiment1() {
    	Random r = new Random();
    	List<Double> yValues = new ArrayList<>();
        List<Double> y2Values = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();

        BST<Integer> bst = new BST<>();
        while(bst.size() < 5000){
        	int insert = r.nextInt(1000000);
        	if(!bst.contains(insert)){
				bst.add(insert);
				yValues.add(bst.aver_depth());
        	}
        }
        for(int i = 1; i <= 5000; i++){
        	xValues.add(i);
        	y2Values.add(ExperimentHelper.optimalAverageDepth(i));
        }

         XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("BST", xValues, yValues);
        chart.addSeries("optimal BST", xValues, y2Values);

        new SwingWrapper(chart).displayChart();
    }

    public static void experiment2() {
    	 
    	 List<Integer> xValues = new ArrayList<>();
    	 List<Double> yValues = new ArrayList<>();

    	BST<Integer> bst = new BST<>(); 
    	/**  Initialize, N is 400 */
    	
    	while(bst.size() < 4000){
    		ExperimentHelper.randomInsert(bst);
    	}

        yValues.add(bst.aver_depth());
        xValues.add(0);
        

    	/**  M is 5000 */
    	
    	for(int i = 0; i < 50000; i++){
    	ExperimentHelper.randomDeleteAsy(bst);
    	ExperimentHelper.randomInsert(bst);
    	yValues.add(bst.aver_depth());
    	xValues.add(i + 1);
    }
    	XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("exp2", xValues, yValues);
         new SwingWrapper(chart).displayChart();
         

       }

    public static void experiment3() {
    	 List<Integer> xValues = new ArrayList<>();
    	 List<Double> yValues = new ArrayList<>();

    	BST<Integer> bst = new BST<>(); 
    	/**  Initialize, N is 400 */
    	
    	while(bst.size() < 4000){
    		ExperimentHelper.randomInsert(bst);
    	}

        yValues.add(bst.aver_depth());
        xValues.add(0);
        

    	/**  M is 5000 */
    	
    	for(int i = 0; i < 500000; i++){
    	ExperimentHelper.randomDeleteSy(bst);
    	ExperimentHelper.randomInsert(bst);
    	yValues.add(bst.aver_depth());
    	xValues.add(i + 1);
    }
    	XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("exp2", xValues, yValues);
         new SwingWrapper(chart).displayChart();
    }

    public static void main(String[] args) {
        experiment3();
    }
}
