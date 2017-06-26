package XMLJava;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Menu extends JMenuBar {
	public static JMenu mnNewMenuArchivo;
	public static JMenu mnNewMenuEditar;
	public static JMenu mnNewMenuFiltrar;
	public static JMenuItem nuevo;
	public static JMenuItem abrir;
	public static JMenuItem guardar;
	public static JMenuItem guardarComo;
	public static JMenuItem eliminar;
	public static JMenuItem annadir;
	public static JMenuItem filtrar;
	
	public Menu() {

		mnNewMenuArchivo = new JMenu("Archivo");
		add(mnNewMenuArchivo);
		nuevo = new JMenuItem("Nuevo XML");
		mnNewMenuArchivo.add(nuevo);
		abrir = new JMenuItem("Abrir XML");
		mnNewMenuArchivo.add(abrir);
		guardar = new JMenuItem("Guardar");
		mnNewMenuArchivo.add(guardar);
		guardarComo = new JMenuItem("Guardar como..");
		mnNewMenuArchivo.add(guardarComo);
		mnNewMenuEditar = new JMenu("Editar");
		add(mnNewMenuEditar);
		annadir = new JMenuItem("Añadir libro");
		mnNewMenuEditar.add(annadir);
		eliminar = new JMenuItem("Eliminar libro");
		mnNewMenuEditar.add(eliminar);
		mnNewMenuFiltrar = new JMenu("Filtrar");
		add(mnNewMenuFiltrar);
		filtrar = new JMenuItem("Filtrar con XPath");
		mnNewMenuFiltrar.add(filtrar);
		
		nuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Dom.nuevoXML();
			}
		});
		
		guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Dom.modelo.getRowCount()==0){
					JOptionPane.showMessageDialog(null, "Debes abrir o crear un DOM");
				}else{
					Dom.nuevoLibro=false;
					Dom.guardar();
				}
			}
		});
		
		guardarComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Dom.modelo.getRowCount()==0){
					JOptionPane.showMessageDialog(null, "Debes abrir o crear un DOM");
				}else{
					Dom.nuevoLibro=true;
					Dom.guardar();
				}
			}
		});
		
		annadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Dom.modelo.getRowCount()==0){
					JOptionPane.showMessageDialog(null, "Debes abrir o crear un DOM");
				}else{
					new mostrarLibro(null, null, null, null, null, null, null, false);
				}
			}
		});
		
		eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Dom.modelo.getRowCount()==0){
					JOptionPane.showMessageDialog(null, "No hay nada que eliminar");
				}else{
					try {
						String isbn=(String) Dom.table.getValueAt(Dom.table.getSelectedRow(), 0);
						Dom.eliminarLibro(isbn);
					}catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Selecciona un libro para borrar");
						e.printStackTrace();
					}
				}
			}
		});
		
		filtrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Dom.modelo.getRowCount()==0){
					JOptionPane.showMessageDialog(null, "Debes abrir o crear un DOM");
				}else{
					Dom.pintaFiltrar();
				}
			}
		});
	}

}