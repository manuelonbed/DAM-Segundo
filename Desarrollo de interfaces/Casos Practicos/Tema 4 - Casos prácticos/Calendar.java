package cal;

import java.awt.BorderLayout;
import com.toedter.calendar.JDateChooser;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import java.awt.Color;

public class Calendar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calendar frame = new Calendar();
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
	public Calendar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		table = new JTable();
		contentPane.add(table);
		

		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
		rdbtnNewRadioButton.setForeground(new Color(255, 176, 239));
		contentPane.add(rdbtnNewRadioButton);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setForeground(new Color(126, 255, 158));
		contentPane.add(btnNewButton);
		
		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane);
	}

}
