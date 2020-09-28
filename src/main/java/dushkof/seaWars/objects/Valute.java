/**
 * ***********************************************************************
 * Copyright (c) 2017, SAP <sap.com>
 * <p>
 * All portions of the code written by SAP are property of SAP.
 * All Rights Reserved.
 * <p>
 * SAP
 * <p>
 * Moscow, Russian Federation
 * <p>
 * Web: sap.com
 * ***********************************************************************
 */
package dushkof.seaWars.objects;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlValue;

/**
 *
 *
 * @author AUTHOR <AUTHOR@sap.com>
 * @package dushkof.seaWars.objects
 * @link http://sap.com/
 * @copyright 2017 SAP
 */
public class Valute {

    private String ID;

    private String NumCode;

    private String CharCode;

    private String Nominal;

    private String Name;

    private String Value;

    public Valute(){}
    public Valute(String ID, String numCode, String charCode, String nominal, String name, String value) {
        super();
        this.ID = ID;
        NumCode = numCode;
        CharCode = charCode;
        Nominal = nominal;
        Name = name;
        Value = value;
    }
    @XmlAttribute(name = "ID")
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @XmlElement(name = "NumCode")
    public String getNumCode() {
        return NumCode;
    }

    public void setNumCode(String numCode) {
        NumCode = numCode;
    }

    @XmlElement(name = "CharCode")
    public String getCharCode() {
        return CharCode;
    }

    public void setCharCode(String charCode) {
        CharCode = charCode;
    }

    @XmlElement(name = "Nominal")
    public String getNominal() {
        return Nominal;
    }

    public void setNominal(String nominal) {
        Nominal = nominal;
    }

    @XmlElement(name = "Name")
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @XmlElement(name = "Value")
    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    @Override
    public String toString(){
        return "==========\n" + "ID = " + this.ID + "\nNumCode = " + this.NumCode + "\nCharCode = " + this.CharCode +
                 "\nNominal = " + this.Nominal + "\nName = " + this.Name + "\nValue = " + this.Value + "\n==========";
    }
}
