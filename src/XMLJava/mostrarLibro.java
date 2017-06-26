package XMLJava;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class mostrarLibro extends JFrame {
	
	private JPanel contentPane;
	private JTextField textTitulo;
	private JTextField textAutor;
	private JTextField textAnno;
	private JTextField textIsbn;
	private JTextField textNEjemplares;
	private JTextField textEditorial;
	private JTextField textNPaginas;
	private JButton btnGuardar;
	
	public mostrarLibro(String isbn, String titulo, String anno, String autor, String nEjemplares, String editorial, String nPaginas, boolean op){
		
		setVisible(true);
		setBounds(100, 100, 812, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Titulo:");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(84, 103, 56, 16);
		contentPane.add(lblTitulo);
		
		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAutor.setBounds(84, 157, 56, 16);
		contentPane.add(lblAutor);
		
		JLabel lblPublicadoEn = new JLabel("Publicado en:");
		lblPublicadoEn.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPublicadoEn.setBounds(30, 210, 110, 16);
		contentPane.add(lblPublicadoEn);
		
		textTitulo = new JTextField();
		textTitulo.setBounds(175, 101, 191, 22);
		contentPane.add(textTitulo);
		textTitulo.setColumns(10);
		textTitulo.setText(titulo);
		
		
		textAutor = new JTextField();
		textAutor.setBounds(175, 155, 191, 22);
		contentPane.add(textAutor);
		textAutor.setColumns(10);
		textAutor.setText(autor);
		
		textAnno = new JTextField();
		textAnno.setBounds(175, 208, 191, 22);
		contentPane.add(textAnno);
		textAnno.setColumns(10);
		textAnno.setText(anno);
		
		JLabel lblIsbn = new JLabel("ISBN:");
		lblIsbn.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblIsbn.setBounds(283, 35, 46, 16);
		contentPane.add(lblIsbn);
		
		textIsbn = new JTextField();
		textIsbn.setBounds(364, 33, 191, 22);
		contentPane.add(textIsbn);
		textIsbn.setColumns(10);
		textIsbn.setText(isbn);
		
		JLabel lblNewLabel = new JLabel("N\u00BA Ejemplares:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(432, 105, 123, 16);
		contentPane.add(lblNewLabel);
		
		textNEjemplares = new JTextField();
		textNEjemplares.setBounds(567, 103, 191, 22);
		contentPane.add(textNEjemplares);
		textNEjemplares.setColumns(10);
		textNEjemplares.setText(nEjemplares);
		
		JLabel lblNewLabel_1 = new JLabel("Editorial:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(480, 152, 75, 16);
		contentPane.add(lblNewLabel_1);
		
		textEditorial = new JTextField();
		textEditorial.setBounds(567, 150, 191, 22);
		contentPane.add(textEditorial);
		textEditorial.setColumns(10);
		textEditorial.setText(editorial);
		
		JLabel lblNewLabel_2 = new JLabel("N\u00BA Paginas:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(458, 206, 97, 16);
		contentPane.add(lblNewLabel_2);
		
		textNPaginas = new JTextField();
		textNPaginas.setBounds(567, 204, 191, 22);
		contentPane.add(textNPaginas);
		textNPaginas.setColumns(10);
		textNPaginas.setText(nPaginas);
				
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textIsbn.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Tienes que rellenar el ISBN");
				}else{
					if(op==false){
						Dom.annadirLibro(textIsbn.getText(), textTitulo.getText(), textAnno.getText(), textAutor.getText(), textNEjemplares.getText(), textEditorial.getText(), textNPaginas.getText());
					}else{
						Dom.modificarLibro(textIsbn.getText(), textTitulo.getText(), textAnno.getText(), textAutor.getText(), textNEjemplares.getText(), textEditorial.getText(), textNPaginas.getText());
					}
					limpiar();
					Dom.guardar();
				}
			}
		});
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnGuardar.setBounds(381, 276, 97, 25);
		contentPane.add(btnGuardar);
				
		revalidate();
		repaint();
	}
	
	public void limpiar(){
		textIsbn.setText("");
		textTitulo.setText("");
		textAnno.setText("");
		textAutor.setText("");
		textNEjemplares.setText("");
		textEditorial.setText("");
		textNPaginas.setText("");
	}
}