package org.galeria.webservices;

import java.util.Date;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import org.galeria.administracion.Lienzo;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author esvux
 */
@WebService(serviceName = "GestorLienzos")
@Stateless()
public class GestorLienzos {

    /**
     * WebService que atiende la petición de crear un nuevo lienzo
     * @param txt JSON que contiene el título, el comentario, la fecha y la imagen.
     * @return Un mensaje del estado de la creación (almacenamiento) del lienzo.
     */
    @WebMethod(operationName = "nuevoLienzo")
    public String nuevoLienzo(@WebParam(name = "detalleLienzoJson") String txt) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject recibido = (JSONObject)parser.parse(txt);
            Lienzo lienzo = new Lienzo();
            lienzo.setTitulo(recibido.get("titulo").toString());
            lienzo.setDescripcion(recibido.get("descripcion").toString());
            lienzo.setFecha(new Date(Long.parseLong(recibido.get("fecha").toString())));
            return (lienzo.save()) ? 
                    "El lienzo '" + lienzo.getTitulo() + "' fue publicado con éxito." :
                    "El lienzo '" + lienzo.getTitulo() + "' no pudo ser publicado.";
        } catch (ParseException ex) {
            return "Ha ocurrido un problema con el parseo del JSON recibido, " + ex.getMessage() + ".";
        }
    }
}
