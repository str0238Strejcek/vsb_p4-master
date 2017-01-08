package cz.vsb.p4.testapp;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by ruz76 on 2.11.2016.
 */
public class Strom {

    private String nazev;
    private final String    townName;

    private final int       updateClass;



    public Strom(String nazev, String townName, int updateClass) {
        this.nazev = nazev;
        this.townName = townName;
        this.updateClass = updateClass;
    }



    public String getNazev() {
        return nazev;
    }

    public int getUpdateClass()
    {
        return updateClass;
    }

    public String getTownName()
    {
        return townName;
    }

    @Override
    public String toString()
    {
        return "Strom{" +
                "nazev='" + nazev + '\'' +
                ", townName='" + townName + '\'' +
                ", updateClass=" + updateClass +

                '}';
    }

}
