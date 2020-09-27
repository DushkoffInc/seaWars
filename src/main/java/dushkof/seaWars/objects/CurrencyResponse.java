package dushkof.seaWars.objects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Map;

@XmlRootElement
public class CurrencyResponse {

    public String date;

    public String name;

    public List<Map<String, String>> currency;

    public String getDate() {
        return date;
    }
    @XmlElement
    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }
    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public List<Map<String, String>> getCurrency() {
        return currency;
    }
    @XmlElement
    public void setCurrency(List<Map<String, String>> currency) {
        this.currency = currency;
    }
}
