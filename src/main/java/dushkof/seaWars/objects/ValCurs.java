package dushkof.seaWars.objects;

import javax.xml.bind.annotation.*;
import java.util.List;

//@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="ValCurs")
public class ValCurs {

    private String Date;

    private String name;

    private List<dushkof.seaWars.objects.Valute> Valute;

    public ValCurs(){}
    public ValCurs(String Date, String name, List<dushkof.seaWars.objects.Valute> Valute){
        super();
        this.Date = Date;
        this.name = name;
        this.Valute = Valute;
    }

    @XmlAttribute(name = "Date")
    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "Valute")
    public List<dushkof.seaWars.objects.Valute> getValute() {
        return Valute;
    }

    public void setValute(List<dushkof.seaWars.objects.Valute> Valute) {
        this.Valute = Valute;
    }
}
