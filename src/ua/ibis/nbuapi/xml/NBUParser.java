package ua.ibis.nbuapi.xml;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ua.ibis.nbuapi.util.MyDialog;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by conti on 01.12.2016.
 */
public class NBUParser {

    public static List<Currency> readXML(String filename) {
        Exchange exch = null;

        try {
            Unmarshaller um = JAXBContext.newInstance(Exchange.class).createUnmarshaller();

            exch = (Exchange) um.unmarshal(new File(filename));
        } catch (JAXBException e) {
            MyDialog.exceptionMessage(e);
        }

        return exch == null ? new ArrayList<>() : exch.getCurrencies();
    }

    public static List<Currency> readJSON(String filename) {

        Gson gson = new GsonBuilder().
                registerTypeAdapter(LocalDate.class, new DateJsonDeserializer()).
                create();

        List<Currency> exch = null;
        try(Reader br = new InputStreamReader(new FileInputStream(filename), "UTF-8")) {
            exch = gson.fromJson(br, new TypeToken<List<Currency>>(){}.getType());
        } catch (IOException e) {
            MyDialog.exceptionMessage(e);
        }
        return exch == null ? new ArrayList<>() : exch;
    }

    public static List<Currency> readURLXml(String urlNbu) {
        Exchange exch = null;

        try {
            Unmarshaller um = JAXBContext.newInstance(Exchange.class).createUnmarshaller();

            exch = (Exchange) um.unmarshal(new URL(urlNbu));
        } catch (JAXBException | MalformedURLException e) {
            MyDialog.exceptionMessage(e);
        }

        return exch == null ? new ArrayList<>() : exch.getCurrencies();
    }

    public static List<Currency> readURLJson(String urlNbu) {
        Gson gson = new GsonBuilder().
                registerTypeAdapter(LocalDate.class, new DateJsonDeserializer()).
                create();

        List<Currency> exch = null;
        try {
            URL url = new URL(urlNbu);
            Reader br = new InputStreamReader(url.openStream(), "UTF-8");
            exch = gson.fromJson(br, new TypeToken<List<Currency>>(){}.getType());

        } catch (IOException e) {
            MyDialog.exceptionMessage(e);
        }
        return exch == null? new ArrayList<>() : exch;
    }

    public static void saveToXML(List<Currency> currencies, String filename) {

        Exchange exch = new Exchange(currencies);

        try {
            Marshaller m = JAXBContext.newInstance(Exchange.class).createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            m.marshal(exch, new File(filename));

        } catch (JAXBException e) {
            MyDialog.exceptionMessage(e);
        }

    }

    public static void saveToJSON(List<Currency> currencies, String filename) {

        Gson gson = new GsonBuilder().
                registerTypeAdapter(LocalDate.class, new DateJsonSerializer()).
                create();
        String info = gson.toJson(currencies);
        try(Writer wf = new OutputStreamWriter(new FileOutputStream(filename), "UTF-8")) {
            wf.write(info);
        } catch (IOException e) {
            MyDialog.exceptionMessage(e);
        }
    }

}
