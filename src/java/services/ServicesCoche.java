package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import models.ListaCoche;

public class ServicesCoche {

    String url;

    public ServicesCoche() {

        this.url = "https://apicochesisma.azurewebsites.net/";
    }

    private String leerRespuestaApi(InputStream stream) throws IOException {
        //necesitamos un lector del flujo
        BufferedReader buffer = new BufferedReader(new InputStreamReader(stream));
        String linea = "";
        StringBuffer data = new StringBuffer();
        String separador = "";
        while ((linea = buffer.readLine()) != null) {
            //añadimos el contenido de los datos a data
            data.append(separador + linea);
            separador = "/n";
        }
        //recuperamos los datos como String
        String response = data.toString();
        return response;
    }

    //metodo para leer el servicio
    private ListaCoche getRequestCoche(String request) throws MalformedURLException, IOException, JAXBException {
        //las conexiones Http se realizan a traves de objetos url
        URL peticion = new URL(this.url + request);
        HttpURLConnection conexion = (HttpURLConnection) peticion.openConnection();
        conexion.setRequestMethod("GET");
        conexion.setRequestProperty("Accept", "application/xml");
        if (conexion.getResponseCode() == 200) {
            //capturamos el flujo de datos de la respusta
            InputStream stream = conexion.getInputStream();
            //convertimos el flujo en String xml
            String data = this.leerRespuestaApi(stream);
            //deserializamos el contenido en clases
            //convertimos un texto xml en clases java de forma automatica
            //este  objeto indica el tipo de clase que debe mapear
            JAXBContext context
                    = JAXBContext.newInstance(ListaCoche.class);
            //creamos un deserializador que le pasaremos el String
            //y nos devuelve la clase y propiedes asignadas
            Unmarshaller serial = context.createUnmarshaller();
            //para deserializar , marhall necesita un Stringreader
            StringReader reader = new StringReader(data);
            //automaticamente el marshall recupera la clase
            //mediante el reader y la mapea
            ListaCoche coche = (ListaCoche) serial.unmarshal(reader);
            return coche;
        } else {
            return null;
        }
    }

    public ListaCoche getCoche() throws MalformedURLException, IOException, JAXBException {

        String request = "api/coche";
        URL peticion = new URL(this.url + request);
        return this.getRequestCoche(request);
    }

    public ListaCoche getDetallesCoche(String idcoche) throws MalformedURLException {
        //debemos indicar el metodo donde leemos
        String request = "api/coche/" + idcoche;
        //las conexiones Http se realizan a traves de objetos url
        URL peticion = new URL(this.url + request);
        return this.getDetallesCoche(idcoche);

    }
}
