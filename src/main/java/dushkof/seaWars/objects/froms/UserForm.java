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
package dushkof.seaWars.objects.froms;

/**
 *
 *
 * @author AUTHOR <AUTHOR@sap.com>
 * @package dushkof.seaWars.objects.froms
 * @link http://sap.com/
 * @copyright 2017 SAP
 */
public class UserForm {
    public String name;
    public String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
