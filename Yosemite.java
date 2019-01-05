import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextPane;

public class Yosemite extends JFrame {

	private JPanel contentPane;
	private JTextField startPoint;
	private JLabel lblStartPoint;
	private JLabel title;
	private JButton btnGo;
	private JLabel lblEnterAStarting;
	private String start;
	private JTextField endPoint;
	private JTextPane pathPane;
	private JLabel lblDistance;
	private JTextPane distancePane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Yosemite frame = new Yosemite();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Yosemite() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1068, 666);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		startPoint = new JTextField();
		startPoint.setFont(new Font("Tahoma", Font.PLAIN, 22));
		startPoint.setBounds(65, 198, 185, 75);
		contentPane.add(startPoint);
		startPoint.setColumns(10);

		lblStartPoint = new JLabel("Start Point");
		lblStartPoint.setFont(new Font("Tahoma", Font.PLAIN, 33));
		lblStartPoint.setBounds(65, 148, 185, 42);
		contentPane.add(lblStartPoint);

		title = new JLabel("Yosemite Shortest Route");
		title.setFont(new Font("Tahoma", Font.PLAIN, 73));
		title.setBounds(143, 11, 947, 126);
		contentPane.add(title);

		btnGo = new JButton("Go!");
		btnGo.setFont(new Font("Tahoma", Font.PLAIN, 27));
		btnGo.setBounds(452, 206, 98, 54);
		contentPane.add(btnGo);

		lblEnterAStarting = new JLabel("Enter a Starting Point and Ending Point from the map of Yosemite");
		lblEnterAStarting.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblEnterAStarting.setBounds(135, 106, 786, 54);
		contentPane.add(lblEnterAStarting);
		
		endPoint = new JTextField();
		endPoint.setFont(new Font("Tahoma", Font.PLAIN, 22));
		endPoint.setColumns(10);
		endPoint.setBounds(796, 198, 185, 75);
		contentPane.add(endPoint);
		
		JLabel lblEndPoint = new JLabel("End Point");
		lblEndPoint.setFont(new Font("Tahoma", Font.PLAIN, 33));
		lblEndPoint.setBounds(796, 148, 185, 42);
		contentPane.add(lblEndPoint);
		
		JLabel lblPath = new JLabel("Route");
		lblPath.setFont(new Font("Tahoma", Font.PLAIN, 33));
		lblPath.setBounds(452, 501, 101, 42);
		contentPane.add(lblPath);
		
		 pathPane = new JTextPane();
		pathPane.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pathPane.setEditable(false);
		pathPane.setBounds(10, 554, 1032, 75);
		contentPane.add(pathPane);
		
		lblDistance = new JLabel("Distance");
		lblDistance.setFont(new Font("Tahoma", Font.PLAIN, 33));
		lblDistance.setBounds(422, 318, 131, 42);
		contentPane.add(lblDistance);
		
		distancePane = new JTextPane();
		distancePane.setFont(new Font("Tahoma", Font.PLAIN, 20));
		distancePane.setEditable(false);
		distancePane.setBounds(422, 366, 164, 64);
		contentPane.add(distancePane);

		ListenForButton l = new ListenForButton();
		btnGo.addActionListener(l);

	}

	private class ListenForButton implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btnGo) {
				// Create graph of Yosemite hiking trails
				AdjacencyMatrix a = new AdjacencyMatrix(19);
				Dijkstra d = new Dijkstra();
				a.insertNode("Clouds Rest");
				a.insertNode("Nevada Fall");
				a.insertNode("Little Yosemite Valley", "Clouds Rest", 0.6);
				a.insertNode("Clouds Rest","Nevada Fall",  1.2);
				a.insertNode("Clark Point", "Nevada Fall", 1.8);
				a.insertNode("Clouds Rest", "Clark Point", 2.7);
				a.insertNode("Glacier Point", "Nevada Fall", 5.9);
				a.insertNode("Sentinel Dome", "Glacier Point", 1.0);
				a.insertNode("Taft Point", "Sentinel Dome", 2.1);
				a.insertNode("Dewey Point","Taft Point",  5.5);
				a.insertNode("Stanford Point", "Dewey Point", 1.3);
				a.insertNode("Inspiration Point", "Stanford Point", 3);
				a.insertNode("Half Dome", "Clouds Rest", 2);
				a.insertNode("Nature Center", "Clark Point", 1.2);
				a.insertNode("Half Dome Village", "Nature Center", 0.9);
				a.insertNode("Mirror Lake", "Nature Center", 1.8);
				a.insertNode("Yosemite Valley Lodge", "Half Dome Village", 1.5);
				a.insertNode("Yosemite Point", "Yosemite Valley Lodge", 3.2);
				a.insertNode("North Dome", "Yosemite Point", 4.2);
				a.insertNode("Eagle Peak", "Yosemite Point", 2.9);
				a.insertNode("El Capitan", "Eagle Peak", 2.2); 

				//Run Dijkstra on graph
				d.dijkstra(a, a.findNode(startPoint.getText()));
				d.printOnePath(a.findNode(endPoint.getText()), d.parents, a);
				
				//Display Results
				pathPane.setText(d.s.substring(4, d.s.length()-4));
				distancePane.setText(Double.toString(d.shortestDistances[a.findNode(endPoint.getText())]/10.0)+ " miles");
			}
		}
	}
}
