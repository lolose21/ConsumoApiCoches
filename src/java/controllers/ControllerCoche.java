package controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import javax.xml.bind.JAXBException;
import models.Coche;
import models.ListaCoche;
import services.ServicesCoche;

public class ControllerCoche {

    ServicesCoche service;

    public ControllerCoche() {
        this.service = new ServicesCoche();
    }

    public String getTablaCoches() throws IOException, MalformedURLException, JAXBException {

        ListaCoche lista = this.service.getCoche();
        List<Coche> coches = lista.getCoches();
        String html = "";
        for (Coche c : coches) {

            html += "<tr>";
            html += "<td>" + c.getIdCoche() + "</td>";
            html += "<td>";
            html += "<img src='" + c.getImagen() + "'style='width:300px;height:200px'/>";
            html += "</td>";
            html += "<td>";
            html += "<button type='submit' name='idcoche'"
                    + "value='" + c.getIdCoche() + "'>detalles</button>";
            html += "</td>";
            html += "</tr>";
        }
        return html;
    }

}
