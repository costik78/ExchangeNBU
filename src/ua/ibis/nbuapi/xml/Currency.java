package ua.ibis.nbuapi.xml;

import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by conti on 01.12.2016.
 */
@XmlRootElement(name = "currency")
public class Currency {

    int r030;

    @SerializedName("txt")
    String nameOfCurrency;

    BigDecimal rate;

    @SerializedName("cc")
    String imgOfCurrency;

    @SerializedName("exchangedate")
    LocalDate date;

    @XmlElement(name = "r030")
    public int getR030() {
        return r030;
    }

    public void setR030(int r030) {
        this.r030 = r030;
    }

    @XmlElement(name = "txt")
    public String getNameOfCurrency() {
        return nameOfCurrency;
    }

    public void setNameOfCurrency(String nameOfCurrency) {
        this.nameOfCurrency = nameOfCurrency;
    }

    @XmlElement(name = "rate")
    @XmlJavaTypeAdapter(RateAdapter.class)
    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @XmlElement(name = "cc")
    public String getImgOfCurrency() {
        return imgOfCurrency;
    }

    public void setImgOfCurrency(String imgOfCurrency) {
        this.imgOfCurrency = imgOfCurrency;
    }

    @XmlElement(name = "exchangedate")
    @XmlJavaTypeAdapter(DateAdapter.class)
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "r030=" + r030 +
                ", nameOfCurrency='" + nameOfCurrency + '\'' +
                ", rate=" + rate +
                ", imgOfCurrency='" + imgOfCurrency + '\'' +
                ", date=" + date +
                '}';
    }
}
