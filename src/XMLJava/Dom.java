package XMLJava;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;
import java.io.FileOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.xml.xpath.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class Dom extends JFrame {
	
	protected static Document doc = null;
	private static JPanel contentPane;
	protected static JTable table;
	protected static DefaultTableModel modelo;
	private static JToolBar toolBar;
	private static Node libro;
	private static String nombreXML;
	private static File archivo_xml;
	protected static boolean nuevoLibro = false;
	private static String isbnOriginal;
	private static JComboBox<String> comboBox;
	private static JTextField textField;
	private static JButton btnFiltrar;
	private static String palabraConsultar;
	private static String texto;

	public Dom() {
		setVisible(true);
		setBounds(100, 100, 707, 403);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount()==2) {
					String isbn=(String) table.getValueAt(table.getSelectedRow(), 0);
					isbnOriginal=(String) table.getValueAt(table.getSelectedRow(), 0);
					String titulo=(String) table.getValueAt(table.getSelectedRow(), 1);
					String anno=(String) table.getValueAt(table.getSelectedRow(), 2);
					String autor=(String) table.getValueAt(table.getSelectedRow(), 3);
					String nEjemplares=(String) table.getValueAt(table.getSelectedRow(), 4);
					String editorial=(String) table.getValueAt(table.getSelectedRow(), 5);
					String nPaginas=(String) table.getValueAt(table.getSelectedRow(), 6);
					new mostrarLibro(isbn, titulo, anno, autor, nEjemplares, editorial, nPaginas, true);
				}
			}
		});
		table.setShowGrid(false);
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(true);
		table.setAlignmentY(CENTER_ALIGNMENT);
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
		
		revalidate();
		repaint();
		
		Menu menuArchivo = new Menu();
		setJMenuBar(menuArchivo);
		
		
		Menu.abrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Dom.seleccionarXML();
			}
		});	
	}    
        
    public static int abrir_XML_DOM(File fichero){
    	nuevoLibro=false;
        try{
            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder builder=factory.newDocumentBuilder();

            doc=builder.parse(fichero); 
            
            return 0;
                                                            
        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }
    
    public static void recorrerDOMyMostrar(){
    	modelo.setRowCount(0);
    	String datos_nodo[]=null;
        Node node;
        Node raiz=doc.getFirstChild();
        NodeList nodelist=raiz.getChildNodes();
       for (int i=0; i<nodelist.getLength(); i++){ 
           node = nodelist.item(i);
           
           if(node.getNodeType()==Node.ELEMENT_NODE){ 
        	   datos_nodo=procesarLibro(node);
               modelo.addRow(new Object[] {datos_nodo[1], datos_nodo[2], datos_nodo[0], datos_nodo[3], datos_nodo[4], datos_nodo[5], datos_nodo[6]});
            }
        }                
    }
   
    public static String[] procesarLibro(Node n){
        String datos[]= new String[7]; 
        Node ntemp=null;
        int contador=1;
        datos[0]=n.getAttributes().item(0).getNodeValue();
        NodeList nodos=n.getChildNodes();
         for (int i=0; i<nodos.getLength(); i++){
             ntemp = nodos.item(i);
             if(ntemp.getNodeType()==Node.ELEMENT_NODE){
            	datos[contador]=ntemp.getChildNodes().item(0).getNodeValue();
                contador++;
             }
          }
        
        return datos;
    }
   
    public static int guardar(){
    	try{
    		if(nuevoLibro==true){
	    		JFileChooser file=new JFileChooser();
	        	file.showSaveDialog(null);
	        	archivo_xml =file.getSelectedFile();
	        	nombreXML=archivo_xml.getName();
	        	System.out.println(archivo_xml.getName());
        	}
	        	//Crea un fichero llamado salida.xml
	            archivo_xml = new File(nombreXML+".xml");
        	
            //Especifica el formato de salida
            OutputFormat format = new OutputFormat(doc);
            //Especifica que la salida esté indentada.
            format.setIndenting(true); 
            
            
            //Escribe el contenido en el FILE
            XMLSerializer serializer = new XMLSerializer(new FileOutputStream(archivo_xml), format);

            serializer.serialize(doc);
            
            recorrerDOMyMostrar();
            nuevoLibro=false;
            
            return 0;
             }catch(Exception e) {
               
               return -1;
            }
    	 }
    
    public static void eliminarLibro(String isbn){
    	
	    	Node n;
	    	Node padre;
	    	n=buscarLibro(isbn);
	    	padre=n.getParentNode();
	    	padre.removeChild(n);
	    	guardar();
    	
    }
    
    public static void seleccionarXML(){
		JFileChooser file=new JFileChooser();
    	file.showOpenDialog(null);
    	File archivo_xml=file.getSelectedFile();
    	String[] nombre=archivo_xml.getName().split(".xml");
    	nombreXML=nombre[0];
    	abrir_XML_DOM(archivo_xml);
    	recorrerDOMyMostrar();    	
    }
    
    public static void nuevoXML(){
    	File nFile=new File("PlantillaXML.xml");
		abrir_XML_DOM(nFile);
		nuevoLibro=true;
		modelo.setRowCount(0);
		new mostrarLibro(null, null, null, null, null, null, null, false);
    }
    
    public static Node buscarLibro(String isbn){
    	boolean control=false;
    	
        Node node;

        Node raiz=doc.getFirstChild();

        NodeList nodelist=raiz.getChildNodes();
        for (int i=0; i<nodelist.getLength(); i++){ 
           node = nodelist.item(i);
           
           if(node.getNodeType()==Node.ELEMENT_NODE){
        	   String isbnControl = procesarIsbnLibro(node);
               if(isbn==isbnControl){
                libro=node;
                control=true;
               }
            }
            if(control==true){
            	break;
            }
        }
        return libro;             
    }
    
    public static String procesarIsbnLibro(Node n){
        String controlIsbn = null;
        Node ntemp=null;
        
        NodeList nodos=n.getChildNodes();
        for (int i=0; i<nodos.getLength(); i++){
        	ntemp = nodos.item(i);

            if(ntemp.getNodeType()==Node.ELEMENT_NODE){
            	if(ntemp.getNodeName().equals("Isbn")){
            		controlIsbn=ntemp.getChildNodes().item(0).getNodeValue();
            	}
            }
        }
        return controlIsbn;
    }

    public static int modificarLibro(String isbn, String titulo, String anno, String autor, String nuEjemplares, String editorial, String nuPaginas){
        Node n=buscarLibro(isbnOriginal);
    	Node ntemp=null;
    	((Element)n).setAttribute("publicado_en",anno);
    	 NodeList nodos=n.getChildNodes();
         for (int i=0; i<nodos.getLength(); i++){
        	 ntemp = nodos.item(i);
             
        	 if(ntemp.getNodeName().equals("Isbn")){
                ntemp.getChildNodes().item(0).setNodeValue(isbn); 
        	 }
             if(ntemp.getNodeName().equals("Titulo")){
            	ntemp.getChildNodes().item(0).setNodeValue(titulo);
             }
             if(ntemp.getNodeName().equals("Autor")){
                ntemp.getChildNodes().item(0).setNodeValue(autor); 
             }
             if(ntemp.getNodeName().equals("NEjemplares")){
                ntemp.getChildNodes().item(0).setNodeValue(nuEjemplares); 
             }
             if(ntemp.getNodeName().equals("Editorial")){
                ntemp.getChildNodes().item(0).setNodeValue(editorial); 
             }
             if(ntemp.getNodeName().equals("NPaginas")){
                ntemp.getChildNodes().item(0).setNodeValue(nuPaginas); 
             }
         }
         return 1;
         }
    
    public static int annadirLibro(String isbn, String titulo, String anno, String autor, String nuEjemplares, String editorial, String nuPaginas){
    	try{
	       
    		Node nIsbn=doc.createElement("Isbn");
			Node nIsbn_text=doc.createTextNode(isbn);
			nIsbn.appendChild(nIsbn_text);
			    
		    Node ntitulo=doc.createElement("Titulo");
		    Node ntitulo_text=doc.createTextNode(titulo);
		    ntitulo.appendChild(ntitulo_text);
		     
		    Node nautor=doc.createElement("Autor");
		    Node nautor_text=doc.createTextNode(autor);
		    nautor.appendChild(nautor_text);
		        
		    Node nEjemplares=doc.createElement("NEjemplares");
			Node nEjemplares_text=doc.createTextNode(nuEjemplares);
			nEjemplares.appendChild(nEjemplares_text);
			    
			Node nEditorial=doc.createElement("Editorial");
			Node nEditorial_text=doc.createTextNode(editorial);
			nEditorial.appendChild(nEditorial_text);
			    
			Node nPaginas=doc.createElement("NPaginas");
			Node nPaginas_text=doc.createTextNode(nuPaginas);
			nPaginas.appendChild(nPaginas_text);
		        
		    Node nlibro=doc.createElement("Libro");
		        
		    ((Element)nlibro).setAttribute("publicado_en",anno);
		        
		    nlibro.appendChild(nIsbn);
		    nlibro.appendChild(ntitulo);
		    nlibro.appendChild(nautor);
		    nlibro.appendChild(nEjemplares);
		    nlibro.appendChild(nEditorial);
		    nlibro.appendChild(nPaginas);
		    
		    Node raiz=doc.getChildNodes().item(0);
		    raiz.appendChild(nlibro);
		        
		    return 0;
	        
		}catch(Exception e){
		    e.printStackTrace();
		    return -1;
	    }
    }

    public static void pintaFiltrar(){
        	        	
        	toolBar = new JToolBar();
    		contentPane.add(toolBar, BorderLayout.NORTH);
    		comboBox = new JComboBox<String>();
    		comboBox.addItem("--Selecciona para filtrar--");
    		comboBox.addItem("Mostrar todos");
    		comboBox.addItem("Titulo");
    		comboBox.addItem("Autor");
    		comboBox.addItem("Editorial");
    		comboBox.addItem("Fecha de Lanzamiento");
    		toolBar.add(comboBox);
    		ButtonGroup bg= new ButtonGroup();
    		JRadioButton jrb=new JRadioButton(">");
    		JRadioButton jrb2=new JRadioButton("<");
    		JRadioButton jrb3=new JRadioButton("=");
    		
    		toolBar.add(jrb);
    		toolBar.add(jrb2);
    		toolBar.add(jrb3);
    		bg.add(jrb);
    		bg.add(jrb2);
    		bg.add(jrb3);
    		textField = new JTextField();
    		textField.setColumns(20);
    		toolBar.add( textField, BorderLayout.NORTH );
    		btnFiltrar = new JButton("Filtrar");
    		comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
                	jrb.setEnabled(false);
            		jrb2.setEnabled(false);
            		jrb3.setEnabled(false);
            		
                	String item=comboBox.getSelectedItem().toString();
        			switch (item) {      				
        			case "Fecha de Lanzamiento":
        				jrb.setEnabled(true);
        				jrb2.setEnabled(true);
        				jrb3.setEnabled(true);
        			}
    			}
            });  
			
    		btnFiltrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					String item=comboBox.getSelectedItem().toString();
					texto=textField.getText();
					if((texto==null) || (item=="--Selecciona para filtrar--"))item="Mostrar todos";
					switch (item) {
					case "Mostrar todos":
    					palabraConsultar = "/Libros/Libro[Isbn!='null']/Autor/text()";
    				break;
    				
        			case "Titulo":
        					palabraConsultar = "/Libros/Libro[contains(Titulo,\""+texto+"\")]/Titulo/text()";
        				break;

        			case "Autor":
        					palabraConsultar = "/Libros/Libro[contains(Autor,\""+texto+"\")]/Autor/text()";						
        				break;
        				
        			case "Editorial":
        					palabraConsultar = "/Libros/Libro[contains(Editorial,\""+texto+"\")]/Editorial/text()";
        					System.out.println(texto);
        				break;
        				
        			case "Fecha de Lanzamiento":
        					if(jrb.isSelected())palabraConsultar = "/Libros/Libro[@publicado_en>"+texto+"]/Titulo/text()";
        					if(jrb2.isSelected())palabraConsultar = "/Libros/Libro[@publicado_en<"+texto+"]/Titulo/text()";
        					if(jrb3.isSelected())palabraConsultar = "/Libros/Libro[@publicado_en="+texto+"]/Titulo/text()";
        	           break;
					}
					textField.setText("");
					modelo.setRowCount(0);
					Filtrar();
				}
    		});
    		btnFiltrar.setFont(new Font("Tahoma", Font.BOLD, 14));
    		toolBar.add(btnFiltrar);
    		contentPane.updateUI();
    }
        	
    public static void Filtrar(){
    	
    	try {
                 		
           XPath xpath = XPathFactory.newInstance().newXPath();
        			
           XPathExpression consulta = xpath.compile(palabraConsultar);

           Object result = consulta.evaluate(doc, XPathConstants.NODESET);
        			
           NodeList nodes =(NodeList) result;
           for(int x=0; x<nodes.getLength(); x++){	
        	   Node n=nodes.item(x).getParentNode().getParentNode();
        	   procesarFiltrado(n);
           }
           } catch (Exception e) {
        	   e.printStackTrace();
           }
    	}
    
    public static void procesarFiltrado(Node n){
    	String datos[]= new String[7]; 
    	Node ntemp=null;
    	int contador=1;
    	datos[0]=n.getAttributes().item(0).getNodeValue();
    	NodeList nodos=n.getChildNodes();
    	for (int i=0; i<nodos.getLength(); i++){
    	  ntemp = nodos.item(i);
    	  if(ntemp.getNodeType()==Node.ELEMENT_NODE){
    		  datos[contador]=ntemp.getChildNodes().item(0).getNodeValue();
    		  contador++;        
    	  }
    	}
    	modelo.addRow(new Object[] {datos[1], datos[2], datos[0], datos[3], datos[4], datos[5], datos[6]});
    }     
}