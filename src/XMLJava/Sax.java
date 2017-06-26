package XMLJava;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Sax extends JFrame {

	private JPanel contentPane;
	SAXParser parser;
    ManejadorSAX manejador;
    File ficheroXML;
    private JTable table;
	public static DefaultTableModel modelo;

	public Sax() {
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
				abrirXMLSAX();
				modelo.setRowCount(0);
				recorrexSAX();
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
	public int abrirXMLSAX() {
	        try {
	            SAXParserFactory factory = SAXParserFactory.newInstance();
	           factory.setNamespaceAware(false);
	            parser = factory.newSAXParser();
	            
	            manejador = new ManejadorSAX();
	            JFileChooser file=new JFileChooser();
	        	file.showOpenDialog(this);
	        	File archivo_xml=file.getSelectedFile();
	            ficheroXML = archivo_xml;

	            return 0;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return -1;
	        }
	    }
	    
	    
	    
	    public String recorrexSAX(){
	        try{
	            parser.parse(ficheroXML, manejador);
	            return manejador.cadenaResultado;
	        } catch(Exception e){
	            e.printStackTrace();
	            return "Error al procesar el XML con SAX";
	        }
	    }

	}

class ManejadorSAX extends DefaultHandler {

	    int ultimoElemento;
	    String cadenaResultado = "";
	    boolean printLogs;
		private int contador;
		public String datos[]= new String[7];

	    public ManejadorSAX() {
	        ultimoElemento = 0;
	        printLogs = false;
	    }
	    
	    @Override
	    public void startElement(String url, String localName, String qName, Attributes atts) throws SAXException {
	        if (printLogs){
	            System.out.println("He abierto el elemento: "+qName);
	        }
	    	if (qName.equals("Libro")) {
	    		//contador=0;
		        String key = atts.getQName(0);
		        datos[contador]=atts.getValue(key);
		        ultimoElemento = 1;
		        contador++;
	    	}else if (qName.equals("Isbn")) {
	            ultimoElemento = 2;
	        } else if (qName.equals("Titulo")) {
	            ultimoElemento = 3;
	        } else if (qName.equals("Autor")) {
	            ultimoElemento = 4;
	        } else if (qName.equals("NEjemplares")) {
	            ultimoElemento = 5;
	        } else if (qName.equals("Editorial")) {
	            ultimoElemento = 6;
	        } else if (qName.equals("NPaginas")) {
	            ultimoElemento = 7;
	        }
	    }
	    
	    @Override
	    public void endElement(String url, String localName, String qName) throws SAXException {
	        if (printLogs){
	            System.out.println("He cerrado el elemento: "+qName);
	        }
	        
	        
	    }

	    @Override 
	    public void characters(char[] ch, int start, int length) throws SAXException {
	        if (printLogs){
	            System.out.println("He encontrado "+length+" caracteres");
	        }
	        
	        if(ultimoElemento==2||ultimoElemento==3 ||ultimoElemento==4 ||ultimoElemento==5 ||ultimoElemento==6 ||ultimoElemento==7){
	            //Estamos procesando un título o un autor  
	        	for(int i = start; i < start+length; i++){
	                cadenaResultado += ch[i];
	                cadenaResultado.trim();
	            }
	        	//if(!cadenaResultado.startsWith("\"")){
	        	 	datos[contador]=cadenaResultado;
		            cadenaResultado="";
		            contador ++;
	        	//}
	            if(contador==7){
	            	Sax.modelo.addRow(new Object[] {datos[1], datos[2], datos[0], datos[3], datos[4], datos[5], datos[6]});
	            	contador=0;
	            }
	        }
	    }

}
