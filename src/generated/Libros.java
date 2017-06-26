//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.5-2 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: PM.10.18 a las 12:06:15 PM CEST 
//


package generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element name="Libro" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                 	&lt;element name="Isbn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                  &lt;element name="Titulo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                  &lt;element name="Autor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                  &lt;element name="NEjemplares" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                  &lt;element name="Editorial" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                  &lt;element name="NPaginas" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="publicado_en" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "libro"
})
@XmlRootElement(name = "Libros")
public class Libros {

    @XmlElement(name = "Libro")
    protected List<Libros.Libro> libro;

    /**
     * Gets the value of the libro property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the libro property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLibro().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Libros.Libro }
     * 
     * 
     */
    public List<Libros.Libro> getLibro() {
        if (libro == null) {
            libro = new ArrayList<Libros.Libro>();
        }
        return this.libro;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Isbn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *             &lt;element name="Titulo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *             &lt;element name="Autor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *             &lt;element name="NEjemplares" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *             &lt;element name="Editorial" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *             &lt;element name="NPaginas" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *       &lt;attribute name="publicado_en" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
    	"isbn",
    	"titulo",
        "autor",
        "nejemplares",
        "editorial",
        "npaginas"
    })
    public static class Libro {
    	
    	@XmlElement(name = "Isbn", required = true)
        protected String isbn;
        @XmlElement(name = "Titulo", required = true)
        protected String titulo;
        @XmlElement(name = "Autor", required = true)
        protected String autor;
        @XmlElement(name = "NEjemplares", required = true)
        protected String nejemplares;
        @XmlElement(name = "Editorial", required = true)
        protected String editorial;
        @XmlElement(name = "NPaginas", required = true)
        protected String npaginas;
        @XmlAttribute(name = "publicado_en")
        protected String publicadoEn;
        
        /**
         * Obtiene el valor de la propiedad titulo.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIsbn() {
            return isbn;
        }

        /**
         * Define el valor de la propiedad titulo.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIsbn(String value) {
            this.titulo = value;
        }
        
        /**
         * Obtiene el valor de la propiedad titulo.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTitulo() {
            return titulo;
        }

        /**
         * Define el valor de la propiedad titulo.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTitulo(String value) {
            this.titulo = value;
        }

        /**
         * Obtiene el valor de la propiedad autor.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAutor() {
            return autor;
        }

        /**
         * Define el valor de la propiedad autor.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAutor(String value) {
            this.autor = value;
        }

        /**
         * Obtiene el valor de la propiedad publicadoEn.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        /**
         * Obtiene el valor de la propiedad titulo.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEjemplares() {
            return nejemplares;
        }

        /**
         * Define el valor de la propiedad titulo.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEjemplares(String value) {
            this.titulo = value;
        }
        /**
         * Obtiene el valor de la propiedad titulo.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEditorial() {
            return editorial;
        }

        /**
         * Define el valor de la propiedad titulo.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEditorial(String value) {
            this.titulo = value;
        }
        /**
         * Obtiene el valor de la propiedad titulo.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPaginas() {
            return npaginas;
        }

        /**
         * Define el valor de la propiedad titulo.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPaginas(String value) {
            this.titulo = value;
        }
        public String getPublicadoEn() {
            return publicadoEn;
        }

        /**
         * Define el valor de la propiedad publicadoEn.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPublicadoEn(String value) {
            this.publicadoEn = value;
        }

    }

}
