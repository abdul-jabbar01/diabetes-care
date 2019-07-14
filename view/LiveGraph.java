package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import controller.Simulation;

//Graph that shows the Live BGL Values over the time
public class LiveGraph extends JFrame implements ActionListener {

	
	private TimeSeries series;
	private TimeSeries injectedSeries;
	private TimeSeries lowerLimitSeries;
	private TimeSeries upperLimitSeries;
	int day= 8;
	Simulation simulation;
	private Timer timer;
	int index=0;
	
	// Parameterized Constructor
	public LiveGraph(final String title,Simulation simulation) {

		super(title);
		timer= new Timer(simulation.getSimulationDelay(), this);
		
		this.simulation=simulation;

		this.series = new TimeSeries("Current BGL");
		this.injectedSeries = new TimeSeries("BGL with Injection");
		this.lowerLimitSeries=new TimeSeries("Lower Limit");
		this.upperLimitSeries=new TimeSeries("Upper Limit");
		
		final TimeSeriesCollection dataset = new TimeSeriesCollection(this.upperLimitSeries);
		dataset.addSeries(injectedSeries);
		dataset.addSeries(lowerLimitSeries);
//		dataset.addSeries(this.series);		

		final JFreeChart chart = createChart(dataset);

		timer.setInitialDelay(1000);

		//Sets background color of chart
		chart.setBackgroundPaint(Color.LIGHT_GRAY);

		//Created JPanel to show graph on screen
		final JPanel content = new JPanel(new BorderLayout());

		//Created Chartpanel for chart area
		final ChartPanel chartPanel = new ChartPanel(chart);

		//Added chartpanel to main panel
		content.add(chartPanel);

		//Sets the size of whole window (JPanel)
		chartPanel.setPreferredSize(new java.awt.Dimension(800, 500));

		//Puts the whole content on a Frame
		setContentPane(content);

		timer.start();

	}

	// Create chart
	private JFreeChart createChart(final XYDataset dataset) {
		final JFreeChart result = ChartFactory.createTimeSeriesChart(
				"Blood Sugar Level over time",
				"Time",
				"BGL",
				dataset,
				true,
				true,
				false
				);

		final XYPlot plot = result.getXYPlot();

		plot.setBackgroundPaint(new Color(0xffffe0));
		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.lightGray);
		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.lightGray);

		ValueAxis xaxis = plot.getDomainAxis();
		xaxis.setAutoRange(true);

		//Domain axis would show data of 60 seconds for a time
		//        xaxis.setFixedAutoRange(0);  // 60 seconds
		//        xaxis.setVerticalTickLabels(true);

		ValueAxis yaxis = plot.getRangeAxis();
		yaxis.setRange(60, 250.0);

		return result;
	}
	
	//Action on each time interval
	public void actionPerformed(final ActionEvent e) {

		if(this.index<this.simulation.BGLValues.size()) {

			double currentBGL=this.simulation.BGLValues.get(this.index);
			double currentInjectedBGL=this.simulation.injectedBGL.get(this.index);

			if(this.simulation.timeList.get(this.index).equals("00:00"))
				this.day++;

			Hour hour = new Hour(Integer.parseInt(this.simulation.timeList.get(this.index).split(":")[0]), new Day(day, 7, 2019));
			int minute=Integer.parseInt(this.simulation.timeList.get(this.index).split(":")[1]);


			this.series.add(new Minute(minute, hour), (int) currentBGL);
			this.injectedSeries.add(new Minute(minute, hour), currentInjectedBGL);
			this.lowerLimitSeries.add(new Minute(minute, hour),80);
			this.upperLimitSeries.add(new Minute(minute, hour),120);
			
			this.index++;
			
//			System.out.println("Current Time = " + new Minute(minute, hour).toString()+", Current BGL : "+(int) currentBGL);	
		}
	}

	
//	public static void main(final String[] args) {
//
//		Simulation simulation=new Simulation(140);
//		simulation.setBreakfast(100, 103);
//		simulation.setLunch(40, 52);
//		simulation.setDinner(75, 75);
//		simulation.setTotalDays(2);
//		simulation.simulate();
//		final LiveGraph demo = new LiveGraph("Blood Sugar Level over time",simulation);
//		demo.pack();
//		demo.setVisible(true);
//
//	}

}  

