package ua.ibis.nbuapi.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by conti on 01.12.2016.
 */
@XmlRootElement(name = "exchange")
public class Exchange {

    List<Currency> currencies;

    @XmlElement(name = "currency")
    public List<Currency> getCurrencies() {
        return currencies;
    }

    public Exchange() {
        currencies = new ArrayList<>();
    }

    public Exchange(List<Currency> currencies) {
        this.currencies = currencies;
    }

}
