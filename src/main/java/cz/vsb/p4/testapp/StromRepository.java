package cz.vsb.p4.testapp;

import com.google.common.collect.Maps;
import org.jvnet.hk2.internal.Collector;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by ruz76 on 2.11.2016.
 */
@Service
public class StromRepository {

    public StromRepository() throws Exception {
        fill();
    }


    private final Map<String, Strom>    stromy = Maps.newHashMap();
    private List<Strom>                 result = new ArrayList<>();

    public Strom getStrom(String nazev) {
        return stromy.get(nazev);
    }

    public void deleteStrom(String nazev) {
        final Strom strom = stromy.get(nazev);
        stromy.remove(nazev);
        result.remove(strom);
    }

    public void saveStrom(Strom strom) {
        stromy.put(strom.getNazev(), strom);
        result.add(strom);
    }

    public void filter(Integer updatclass, String townname) {
        System.out.println("StromRepostitory.filter " + stromy.values().size() + " " + result.size() + " " + updatclass + " " + townname);
        result = stromy.values().stream().filter(strom -> {
            System.out.println(strom);
            System.out.println((updatclass == null || strom.getUpdateClass() == updatclass));
            System.out.println((townname.isEmpty() || strom.getTownName().equals(townname)));

            return (updatclass == null || strom.getUpdateClass() == updatclass)
                && (townname.isEmpty() || strom.getTownName().equals(townname));
        }).collect(Collectors.toList());
    }

    public void resetFilter() {
        result = new ArrayList<>(stromy.values());
    }

    public List<Strom> getAll() {
        return result;
    }

    public void fill() throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/diplomka", "postgres", "5930back");
        Statement s1 = connection.createStatement();
        ResultSet r1 = s1.executeQuery("SELECT * FROM data_26_7_2016");
        String count11;
        while (r1.next()) {
            this.saveStrom(new Strom("Dopravní data: " + r1.getString(" id") + "  " + r1.getString("town_name") +  " " + r1.getString("txucel"), r1.getString("town_name"), r1.getInt("updateclass")));
        }
        r1.close();
        connection.close();

    }


}

