package cz.vsb.p4.testapp;

import com.google.common.collect.Maps;
import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ruz76 on 26.10.2016.
 */
@Service
public class CounterDAO {

    private final JdbcDataSource dataSource;

    @Autowired
    public CounterDAO(@Value("${jdbc.connection}") String jdbcConnection,
                      @Value("${jdbc.name}") String jdbcName,
                      @Value("${jdbc.password}") String jdbcPassword) {
        this.dataSource = new JdbcDataSource();
        dataSource.setPassword(jdbcPassword);
        dataSource.setUser(jdbcName);
        dataSource.setUrl(jdbcConnection);
    }

    @PostConstruct
    public void initStructure() throws Exception {
        try (Connection c = dataSource.getConnection()) {
            try (PreparedStatement st = c.prepareStatement("create table if not exists counter_values (key text, val long, kvet text)")) {
                st.executeUpdate();
            }
        }
    }

    /**
     * Store key value to database
     *
     * @param key   key name
     * @param value key value
     */
    public void storeKeyValue(String key, long value, String kvet) throws Exception {
        try (Connection c = dataSource.getConnection()) {
            try (PreparedStatement st = c.prepareStatement("MERGE INTO counter_values KEY(key) VALUES (?,?,?)")) {
                st.setString(1, key);
                st.setLong(2, value);
                st.setString(2, kvet);
                st.executeUpdate();
            }
        }
    }

    /**
     * Load all key values from database to memory
     *
     * @return map of key -> value
     */
    public Map<String, Long> loadKeyValues() throws Exception {
        Map<String, Long> map = Maps.newHashMap();
        try (Connection c = dataSource.getConnection()) {
            try (PreparedStatement st = c.prepareStatement("select key, val from counter_values")) {
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        String key = rs.getString(1);
                        Long val = rs.getLong(2);
                        map.put(key, val);
                    }
                }
            }
        }
        return map;
    }

}
