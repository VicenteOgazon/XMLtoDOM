package XMLJava;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class XMLSelect extends JFrame {

	private JPanel contentPane;

	public XMLSelect() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnDom = new JButton("DOM");
		btnDom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Dom();
			}
		});
		btnDom.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDom.setBounds(100, 60, 93, 56);
		contentPane.add(btnDom);
		
		JButton btnSax = new JButton("SAX");
		btnSax.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Sax();
			}
		});
		btnSax.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSax.setBounds(239, 60, 93, 56);
		contentPane.add(btnSax);
		
		JButton btnJaxb = new JButton("JAXB");
		btnJaxb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Jaxb();
			}
		});
		btnJaxb.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnJaxb.setBounds(171, 151, 93, 56);
		contentPane.add(btnJaxb);
		
		revalidate();
		repaint();
	}
}
