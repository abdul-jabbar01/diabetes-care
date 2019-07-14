package view;


import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import controller.Simulation;

public class Graph extends JFrame {

  private static final long serialVersionUID = 1L;
  private Simulation simulation;
  
  public Graph(String title,Simulation simulation) {
    super(title);
    this.simulation=simulation;
    setSize(800, 400);
    setLocationRelativeTo(null);
    setVisible(true);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
    // Create dataset
    XYDataset dataset = createDataset();
    
    // Create chart
    JFreeChart chart = ChartFactory.createTimeSeriesChart(
        "Blood Glucose Level Across the Day", // Chart
        "Time", // X-Axis Label
        "BGL", // Y-Axis Label
        dataset);

    //Changes background color
    XYPlot plot = (XYPlot)chart.getPlot();
    plot.setBackgroundPaint(new Color(255,228,196));
    
    ChartPanel panel = new ChartPanel(chart);
    setContentPane(panel);
  }

  private XYDataset createDataset() {
    TimeSeriesCollection dataset = new TimeSeriesCollection();
    TimeSeries series1 = new TimeSeries("Real BGL");
    TimeSeries series2 = new TimeSeries("BGL after Injection");
    
   
	int day=8;
	for(int i=0;i<simulation.BGLValues.size();i++) {
			
		if(simulation.timeList.get(i).equals("00:00"))
			day++;
		Hour hour = new Hour(Integer.parseInt(simulation.timeList.get(i).split(":")[0]), new Day(day, 1, 2017));
		int minute=Integer.parseInt(simulation.timeList.get(i).split(":")[1]);

		
//		System.out.println(simulation.BGLValues.get(i));
//		System.out.println(simulation.injectedBGL.get(i));
//		System.out.println(simulation.timeList.get(i));
//		System.out.println(day);
//		System.out.println(hour);
		
		
		 series1.add(new Minute(minute, hour), (int) Math.round(simulation.BGLValues.get(i)));
		 series2.add(new Minute(minute, hour), (int) Math.round(simulation.injectedBGL.get(i)));
	}
	
    dataset.addSeries(series1);
    dataset.addSeries(series2);


    return dataset;
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
    	 Simulation simulation=new Simulation(140);
    		simulation.setBreakfast(100, 103);
    		simulation.setLunch(40, 52);
    		simulation.setDinner(75, 75);
    		simulation.simulate();
      Graph example = new Graph("Blood Sugar Level Chart ",simulation);
    });
  }
}