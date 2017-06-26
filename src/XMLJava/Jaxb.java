package XMLJava;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import generated.Libros;

public class Jaxb extends JFrame {
	Libros misLibros;
    private JTable table;
    private JPanel contentPane;
	public static DefaultTableModel modelo;
    
    public Jaxb() {
		setVisible(true);
		setBounds(100, 100, 707, 403);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setShowGrid(false);
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(true);
		modelo=new DefaultTableModel(new Object [][]{}, new String[]{"ISBN", "Titulo", "Año publicacion", "Autor", "Nº Ejemplares","Editorial","Nº Paginas"});
		
		table.setModel(modelo);
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(2).setPreferredWidth(90);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		table.setFillsViewportHeight(true);
		table.setRowHeight(30);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setDefaultEditor(Object.class, null);
		table.setAutoCreateRowSorter(true);
		
		scrollPane.setViewportView(table);
		
		Menu menu = new Menu();
		setJMenuBar(menu);
		Menu.nuevo.setEnabled(false);
		Menu.abrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirXMLJAXB();
				modelo.setRowCount(0);
				recorrerJAXB();
			}
		});
		Menu.eliminar.setEnabled(false);
		Menu.guardar.setEnabled(false);
		Menu.guardarComo.setEnabled(false);
		Menu.annadir.setEnabled(false);
		Menu.filtrar.setEnabled(false);
				
		revalidate();
		repaint();

	}
    
    public int abrirXMLJAXB(){
        JAXBContext context;
        
        try{
        	JFileChooser file=new JFileChooser();
         	file.showOpenDialog(this);
         	File archivo_xml=file.getSelectedFile();
         	
             context = JAXBContext.newInstance(Libros.class);
             Unmarshaller u = context.createUnmarshaller();
             misLibros = (Libros) u.unmarshal(archivo_xml);             
            return 0;
        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    
    
    
    public void recorrerJAXB(){
        
        List<Libros.Libro> listaLibros = misLibros.getLibro();
        
        for(int i = 0; i<listaLibros.size(); i++){
            Libros.Libro libro = listaLibros.get(i);
            modelo.addRow(new Object[] {libro.getIsbn(), libro.getTitulo(), libro.getPublicadoEn(), libro.getAutor(), libro.getEjemplares(), libro.getEditorial(), libro.getPaginas()});
        }
    }
}
