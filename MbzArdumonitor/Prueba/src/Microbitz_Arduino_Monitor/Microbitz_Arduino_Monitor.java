package Microbitz_Arduino_Monitor;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.fazecast.jSerialComm.SerialPort;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;


public class Microbitz_Arduino_Monitor {

    /**
     * @param args the command line arguments
     */
    
	static SerialPort chosenPort;
	static int x1 = 0;
	static int x2 = 0;
	static int x3 = 0;
	static int x4 = 0;
        static int Pg = 1;
	static String value;
    
    public static void main(String[] args) {

       		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		
		
		//Declaracion de ventene Principal
				JFrame window = new JFrame("Microbitz Serial Monitor Plotter");
				window.setVisible(true);
				window.setSize(1000, 650);
				window.setResizable(false);
				window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				JPanel panel = new JPanel();
			    panel.setLayout(null);
			    
			    
		//Generar Grafica
			    
		        XYSeries series1 = new XYSeries("S1");
		        XYSeriesCollection dataset1 = new XYSeriesCollection(series1);
		        JFreeChart chart1= ChartFactory.createXYLineChart("Signal_01", "(Time Secs)", " ", dataset1); 
		        XYPlot plot1 = chart1.getXYPlot();
                        plot1.setBackgroundPaint(Color.BLACK);
                        NumberAxis domain1 = (NumberAxis) plot1.getDomainAxis();
                        domain1.setRange(0.00, 1200.00);
                        NumberAxis range1 = (NumberAxis) plot1.getRangeAxis();
                        range1.setRange(0.0, 100.0);
		        XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer(true, false);
			    renderer1.setSeriesPaint(0, Color.BLUE);
		        plot1.setRenderer(renderer1);
			    
		        XYSeries series2 = new XYSeries("S2");
		        XYSeriesCollection dataset2 = new XYSeriesCollection(series2);
		        JFreeChart chart2= ChartFactory.createXYLineChart("Signal_02", "(Time Secs)", " ", dataset2); 
		        XYPlot plot2 = chart2.getXYPlot();
                        plot2.setBackgroundPaint(Color.BLACK);
                        NumberAxis domain2 = (NumberAxis) plot2.getDomainAxis();
                        domain2.setRange(0.00, 1200.00);
                        NumberAxis range2 = (NumberAxis) plot2.getRangeAxis();
                        range2.setRange(0.0, 100.0);
		        XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer(true, false);
			    renderer2.setSeriesPaint(0, Color.GREEN);
		        plot2.setRenderer(renderer2);
		        
		        XYSeries series3 = new XYSeries("S3");
		        XYSeriesCollection dataset3 = new XYSeriesCollection(series3);
		        JFreeChart chart3= ChartFactory.createXYLineChart("Signal_03", "(Time Secs)", " ", dataset3); 
		        XYPlot plot3 = chart3.getXYPlot();
                        plot3.setBackgroundPaint(Color.BLACK);
                        NumberAxis domain3 = (NumberAxis) plot3.getDomainAxis();
                        domain3.setRange(0.00, 1200.00);
                        NumberAxis range3 = (NumberAxis) plot3.getRangeAxis();
                        range3.setRange(0.0, 100.0);
		        XYLineAndShapeRenderer renderer3 = new XYLineAndShapeRenderer(true, false);
			    renderer3.setSeriesPaint(0, Color.YELLOW);
		        plot3.setRenderer(renderer3);
		        
		        XYSeries series4 = new XYSeries("S4");
		        XYSeriesCollection dataset4 = new XYSeriesCollection(series4);
		        JFreeChart chart4= ChartFactory.createXYLineChart("Signal_04", "(Time Secs)", " ", dataset4); 
		        XYPlot plot4 = chart4.getXYPlot();
                        plot4.setBackgroundPaint(Color.BLACK);
                        NumberAxis domain4 = (NumberAxis) plot4.getDomainAxis();
                        domain4.setRange(0.00, 1200.00);
                        NumberAxis range4 = (NumberAxis) plot4.getRangeAxis();
                        range4.setRange(0.0, 100.0);
		        XYLineAndShapeRenderer renderer4 = new XYLineAndShapeRenderer(true, false);
			    renderer4.setSeriesPaint(0, Color.RED);
		        plot4.setRenderer(renderer4);
		        
		        ChartPanel chartPanel1 = new ChartPanel(chart1);
		        ChartPanel chartPanel2 = new ChartPanel(chart2);
		        ChartPanel chartPanel3 = new ChartPanel(chart3);
		        ChartPanel chartPanel4 = new ChartPanel(chart4);
		        
		        chartPanel1.setBounds(0, 0, 400, 300);
		        chartPanel2.setBounds(0, 300, 400, 300);
		        chartPanel3.setBounds(400, 0, 400, 300);
		        chartPanel4.setBounds(400, 300, 400, 300);
		        
		        window.add(chartPanel1);
		        window.add(chartPanel2);
		        window.add(chartPanel3);
		        window.add(chartPanel4);
			    
		//Dibujar Opciones de conexion
		        JLabel label = new JLabel();
		        JLabel label1 = new JLabel("",SwingConstants.CENTER);
		        JLabel label2 = new JLabel("",SwingConstants.CENTER);
		        JLabel label3 = new JLabel("",SwingConstants.CENTER);
		        JLabel label4 = new JLabel("",SwingConstants.CENTER);
		        JLabel label5 = new JLabel("",SwingConstants.CENTER);
		        JLabel label6 = new JLabel("",SwingConstants.CENTER);
		        JLabel label7 = new JLabel("",SwingConstants.CENTER);
		        JLabel label8 = new JLabel("",SwingConstants.CENTER);
                        JLabel label9 = new JLabel("",SwingConstants.CENTER);
		        JLabel labelV1 = new JLabel("Ch0:");
		        JLabel labelV2 = new JLabel("Ch1:");
		        JLabel labelV3 = new JLabel("Ch2:");
		        JLabel labelV4 = new JLabel("Ch3:");
		        JLabel labelV5 = new JLabel("Ch4:");
		        JLabel labelV6 = new JLabel("Ch5:");
		        JLabel labelV7 = new JLabel("Value:");
		        JLabel labelV8 = new JLabel("Freq:");   
		        JLabel labelV9 = new JLabel("I2c:"); 
                        JLabel labelIN = new JLabel("RX:");
		        
		        JButton connectButton = new JButton();
		        JComboBox<String> portlist = new JComboBox<String>();
		        connectButton.setBounds(850, 20, 130, 20);
		        
                        JButton Page1Button = new JButton();
                        Page1Button.setText("P1");
                        Page1Button.setBounds(850, 390, 65, 20);
                        
                        JButton Page2Button = new JButton();
                        Page2Button.setText("P2");
                        Page2Button.setBounds(915, 390, 65, 20);
                        
		        label.setBounds(20, 603, 500, 20);
		        label.setBackground(Color.WHITE);
		        label.setOpaque(true);
		        
		        label1.setBounds(900, 120, 60, 20);
		        label1.setBackground(Color.WHITE);
		        label1.setOpaque(true);
		        
		        label2.setBounds(900, 150, 60, 20);
		        label2.setBackground(Color.WHITE);
		        label2.setOpaque(true);
		        
		        label3.setBounds(900, 180, 60, 20);
		        label3.setBackground(Color.WHITE);
		        label3.setOpaque(true);
		        
		        label4.setBounds(900, 210, 60, 20);
		        label4.setBackground(Color.WHITE);
		        label4.setOpaque(true);
		        
		        label5.setBounds(900, 240, 60, 20);
		        label5.setBackground(Color.WHITE);
		        label5.setOpaque(true);
		        
		        label6.setBounds(900, 270, 60, 20);
		        label6.setBackground(Color.WHITE);
		        label6.setOpaque(true);
		        
		        label7.setBounds(900, 300, 60, 20);
		        label7.setBackground(Color.WHITE);
		        label7.setOpaque(true);
		        
		        label8.setBounds(900, 330, 60, 20);
		        label8.setBackground(Color.WHITE);
		        label8.setOpaque(true);
                        
                        label9.setBounds(900, 360, 60, 20);
		        label9.setBackground(Color.WHITE);
		        label9.setOpaque(true);
		        
		        labelV1.setBounds(850, 120, 60, 20);
		        
		        labelV2.setBounds(850, 150, 60, 20);
		        
		        labelV3.setBounds(850, 180, 60, 20);
		        
		        labelV4.setBounds(850, 210, 60, 20);
		        
		        labelV5.setBounds(850, 240, 60, 20);
		        
		        labelV6.setBounds(850, 270, 60, 20);
		        
		        labelV7.setBounds(850, 300, 60, 20);
		        
		        labelV8.setBounds(850, 330, 60, 20);
                        
                        labelIN.setBounds(0, 603, 60, 20);
		        
		        portlist.setBounds(850, 80, 130, 20);

		        connectButton.setText("Connect");
		        panel.add(connectButton);
                        panel.add(Page1Button);
                        panel.add(Page2Button);
		        panel.add(label);
		        panel.add(label1);
		        panel.add(label2);
		        panel.add(label3);
		        panel.add(label4);
		        panel.add(label5);
		        panel.add(label6);
		        panel.add(label7);
		        panel.add(label8);
                        panel.add(labelIN);
		        panel.add(labelV1);
		        panel.add(labelV2);
		        panel.add(labelV3);
		        panel.add(labelV4);
		        panel.add(labelV5);
		        panel.add(labelV6);
		        panel.add(labelV7);
		        panel.add(labelV8);
		        panel.add(portlist);
				
		        window.add(panel);
		        

		        
		//Selecionar ComPort
		        
		        SerialPort[] portNames = SerialPort.getCommPorts();
		        for(int i = 0; i < portNames.length; i++)
		        portlist.addItem(portNames[i].getSystemPortName());
		        
		//Cuando Presiono el boton
		      //Boton de Conectar
		 connectButton.addActionListener(new ActionListener(){
			@Override public void actionPerformed(ActionEvent arg0) {
				if(connectButton.getText().equals("Connect")) {
					// attempt to connect to the serial port
					chosenPort = SerialPort.getCommPort(portlist.getSelectedItem().toString());
					chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
					if(chosenPort.openPort()) {
						connectButton.setText("Disconnect");
						portlist.setEnabled(false);
					}
					
					// create a new thread that listens for incoming text and populates the graph
					Thread thread = new Thread(){
						@Override public void run() {
                                                   
							Scanner scanner = new Scanner(chosenPort.getInputStream());
							while(scanner.hasNextLine()) {
								try {
									String line = scanner.nextLine();
									//int number = Integer.parseInt(line);
									 label.setText(scanner.next());
                                                                         String[] results = label.getText().split(",");
                                                                         label1.setText(results[0]);
                                                                         label2.setText(results[1]);
                                                                         label3.setText(results[2]);
                                                                         label4.setText(results[3]);
                                                                         label5.setText(results[4]);
                                                                         label6.setText(results[5]);
                                                                         label7.setText(results[8]);
                                                                         label8.setText(results[9]);
                                                                         //Graficar
                                                                         if (Pg == 1){
		        				        int number1 = Integer.parseInt(label1.getText());
		        				        series1.add(x1++,number1 );
		        				        
		        				        int number2 = Integer.parseInt(label2.getText());
		        				        series2.add(x2++,number2 );
		        				        
		        				        int number3 = Integer.parseInt(label3.getText());
		        				        series3.add(x3++,number3 );
		        				        
		        				        int number4 = Integer.parseInt(label4.getText());
		        				        series4.add(x4++,number4 );
                                                                         }
                                                                         
                                                                         if (Pg == 2){
		        				        int number5 = Integer.parseInt(label5.getText());
		        				        series1.add(x1++,number5 );
		        				        
		        				        int number6 = Integer.parseInt(label6.getText());
		        				        series2.add(x2++,number6 );
		        				        
		        				        int number7 = Integer.parseInt(label7.getText());
		        				        series3.add(x3++,number7 );
		        				        
		        				        int number8 = Integer.parseInt(label8.getText());
		        				        series4.add(x4++,number8 );
                                                                         }
                                                                         
                                                                if (x1 == 1200){
                                                                  series1.clear();
                                                                  x1 = 0;
                                                                }
                                                                if (x2 == 1200){
                                                                  series2.clear();
                                                                  x2 = 0;
                                                                }
                                                                if (x3 == 1200){
                                                                  series3.clear();
                                                                  x3 = 0;
                                                                }
                                                                if (x4 == 1200){
                                                                  series4.clear();
                                                                  x4 = 0;
                                                                }
                                                                         /*labelV9.setText(results[8]);
                                                                         Inbufferj.setText(results[9]);
                                                                         Inbufferk.setText(results[10]);
                                                                         Inbufferl.setText(results[11]);
                                                                         Inbufferm.setText(results[12]);
                                                                         Inbuffern.setText(results[13]);
                                                                         Inbuffero.setText(results[14]); 
                                                                         Inbufferp.setText(results[15]);*/  
									window.repaint();
								} catch(Exception e) {}
							}
							scanner.close();
						}
					};
					thread.start();
				} else {
					// disconnect from the serial port
					chosenPort.closePort();
					portlist.setEnabled(true);
					connectButton.setText("Connect");
					label1.setText("");
							label2.setText("");
							label3.setText("");
							label4.setText("");
		        			x1 = 0;
		        			x2 = 0;
		        			x3 = 0;
		        			x4 = 0;
					//x = 0;
				}
			}
		});
		
		// show the window
		window.setVisible(true);
                
                Page1Button.addActionListener(new ActionListener(){
			@Override public void actionPerformed(ActionEvent arg0) {
				
                                 Pg = 1;
                                 series1.clear();
                                 x1 = 0;
                                 series2.clear();
                                 x2 = 0;
                                 series3.clear();
                                 x3 = 0;
                                 series4.clear();
                                 x4 = 0;
			}
		});
                Page2Button.addActionListener(new ActionListener(){
			@Override public void actionPerformed(ActionEvent arg0) {
				
                                 Pg = 2;
                                 series1.clear();
                                 x1 = 0;
                                 series2.clear();
                                 x2 = 0;
                                 series3.clear();
                                 x3 = 0;
                                 series4.clear();
                                 x4 = 0;
			}
		});
        }
    
}
