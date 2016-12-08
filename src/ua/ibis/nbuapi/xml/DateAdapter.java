package ua.ibis.nbuapi.xml;

import ua.ibis.nbuapi.util.Formatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by conti on 01.12.2016.
 */
public class DateAdapter extends XmlAdapter<String, LocalDate> {

    @Override
    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(v, Formatter.nbuFileDate);
    }

    @Override
    public String marshal(LocalDate  v) throws Exception {
        return v.format(Formatter.nbuFileDate);
    }
}
