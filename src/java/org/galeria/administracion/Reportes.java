package org.galeria.administracion;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.galeria.utilities.DBUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author esvux
 */
@ManagedBean(name = "reportes")
@SessionScoped
public class Reportes {

    @ManagedProperty(value = "#{lienzo}")
    private Lienzo lienzo;
    private List<Lienzo> lienzos;
    private String filtro;

    private static final String CONSULTA_CLASICOS
            = "select * from lienzo order by lienzo.fecha asc";
    private static final String CONSULTA_RECIENTES
            = "select * from lienzo order by lienzo.fecha desc";
    private static final String CONSULTA_ABC
            = "select * from lienzo order by titulo asc";
    private static final String CONSULTA_ZYX
            = "select * from lienzo order by titulo desc";
    private static final String CONSULTA_DEFAULT
            = "select * from lienzo";
    private static final String CONSULTA_LIENZO_POR_ID
            = "select * from lienzo where id = ?";

    public Reportes() {
        this.filtro = "Default";
        init();
    }

    public List<Lienzo> getLienzos() {
        return lienzos;
    }

    public void setLienzos(List<Lienzo> lienzos) {
        this.lienzos = lienzos;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public Lienzo getLienzo() {
        return lienzo;
    }

    public void setLienzo(Lienzo lienzo) {
        this.lienzo = lienzo;
    }

    public String getConsulta() {
        switch (filtro) {
            case "Recientes":
                return CONSULTA_RECIENTES;
            case "Clasicos":
                return CONSULTA_CLASICOS;
            case "TituloABC":
                return CONSULTA_ABC;
            case "TituloZYX":
                return CONSULTA_ZYX;
        }
        return CONSULTA_DEFAULT;
    }

    private void init() {
        try {
            QueryRunner run = DBUtils.getInstance().getRun();
            System.out.println("Reporte inicial...");
            this.lienzos = run.query(getConsulta(), new BeanListHandler<>(Lienzo.class));
        } catch (SQLException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String generar() {
        String consulta = getConsulta();
        try {
            QueryRunner run = DBUtils.getInstance().getRun();
            System.out.println("Generando reporte...");
            this.lienzos = run.query(consulta, new BeanListHandler<>(Lienzo.class));
        } catch (SQLException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return filtro;
    }

    public String detalle() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String buscado = params.get("seleccionado");
        System.err.println("DETALLE: Lienzo " + buscado);
        try {
            QueryRunner run = DBUtils.getInstance().getRun();
            List<Lienzo> temp = run.query(CONSULTA_LIENZO_POR_ID, new BeanListHandler<>(Lienzo.class), buscado);
            System.err.println("ENCONTRADOS: " + temp.size());
            copiarLienzo(temp.get(0));
        } catch (SQLException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "detalle";
    }

    private void copiarLienzo(Lienzo otro){
        this.lienzo.setId(otro.getId());
        this.lienzo.setTitulo(otro.getTitulo());
        this.lienzo.setDescripcion(otro.getDescripcion());
        this.lienzo.setFecha(otro.getFecha());
        this.lienzo.setImagen(otro.getImagen());
    }
    
    @Override
    public String toString() {
        JSONObject obj = new JSONObject();
        obj.put("filtro", this.filtro);
        JSONArray arr = new JSONArray();
        for (Lienzo lnz : this.lienzos) {
            arr.add(lnz.toJSONObject());
        }
        obj.put("lienzos", arr);
        return obj.toJSONString();
    }

}
