package org.galeria.administracion;

import java.sql.SQLException;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.commons.dbutils.QueryRunner;
import org.galeria.utilities.DBUtils;
import org.json.simple.JSONObject;

/**
 *
 * @author esvux
 */
@ManagedBean(name = "lienzo")
@SessionScoped
public class Lienzo {
    
    private Integer id;
    private String titulo;
    private String descripcion;
    private Date fecha;
    private String imagen;

    public Lienzo() {
    }

    public Lienzo(Integer id, String titulo, String comentarios, Date fecha, String imagen) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = comentarios;
        this.fecha = fecha;
        this.imagen = imagen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }    
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }


    public String getFechaConFormato(){
        return (this.fecha == null) ? "Sin fecha" : DBUtils.formateador.format(this.fecha);
    }
    
    public JSONObject toJSONObject(){
        JSONObject obj = new JSONObject();
        obj.put("titulo", titulo);
        obj.put("descripcion", descripcion);
        obj.put("fecha", fecha);
        return obj;
    }

    public boolean save() {
        try {
            QueryRunner run = DBUtils.getInstance().getRun();
            run.update("insert into lienzo (titulo, descripcion, fecha) values (?, ?, ?)",
                    this.titulo,
                    this.descripcion,
                    this.fecha);
            return true;
        } catch (SQLException ex) {
            System.err.println("Error al agregar el lienzo '" + this.titulo + "' a la base de datos. " + ex.getMessage());
            return false;
        }
    }

}
