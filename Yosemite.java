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
	private JTextPane textArea;

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
		setBounds(100, 100, 1068, 794);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		startPoint = new JTextField();
		startPoint.setFont(new Font("Tahoma", Font.PLAIN, 22));
		startPoint.setBounds(392, 198, 185, 75);
		contentPane.add(startPoint);
		startPoint.setColumns(10);

		lblStartPoint = new JLabel("Start Point");
		lblStartPoint.setFont(new Font("Tahoma", Font.PLAIN, 33));
		lblStartPoint.setBounds(392, 157, 185, 42);
		contentPane.add(lblStartPoint);

		title = new JLabel("Yosemite Shortest Path");
		title.setFont(new Font("Tahoma", Font.PLAIN, 73));
		title.setBounds(143, 11, 947, 126);
		contentPane.add(title);

		btnGo = new JButton("Go!");
		btnGo.setFont(new Font("Tahoma", Font.PLAIN, 27));
		btnGo.setBounds(595, 206, 98, 54);
		contentPane.add(btnGo);

		lblEnterAStarting = new JLabel("Enter a Starting Point from the map of Yosemite");
		lblEnterAStarting.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblEnterAStarting.setBounds(224, 113, 563, 54);
		contentPane.add(lblEnterAStarting);

		textArea = new JTextPane();
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textArea.setEditable(false);
		textArea.setBounds(10, 284, 1032, 461);
		contentPane.add(textArea);

		ListenForButton l = new ListenForButton();
		btnGo.addActionListener(l);

	}

	private class ListenForButton implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btnGo) {
				// Create graph of Yosemite hiking trails
				AdjacencyMatrix a = new AdjacencyMatrix(20);
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

				Dijkstra.dijkstra(a, a.findNode(startPoint.getText()));
				textArea.setText(d.f.substring(4, d.f.length()));
			}
		}
	}
}
