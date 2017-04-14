package cz.zcu.kiv.md2odt.web.service.log;

import cz.zcu.kiv.md2odt.web.dto.Log;
import cz.zcu.kiv.md2odt.web.service.LogStorage;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Patrik Harag
 * @version 2017-04-15
 */
public class DatabaseLogStorage implements LogStorage {

    private static final Logger LOGGER = Logger.getLogger(DatabaseLogStorage.class);
    private static final String INIT_SCRIPT = "init.sql";
    private static final Charset INIT_SCRIPT_CHARSET = StandardCharsets.UTF_8;


    private final JdbcTemplate jdbc;

    public DatabaseLogStorage(DataSource dataSource) {
        this.jdbc = prepare(dataSource);
    }

    private JdbcTemplate prepare(DataSource dataSource) {
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);
        jdbc.execute(loadInitScript());
        return jdbc;
    }

    private String loadInitScript() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(INIT_SCRIPT);
             InputStreamReader isr = new InputStreamReader(is, INIT_SCRIPT_CHARSET);
             BufferedReader br = new BufferedReader(isr)){

            return br.lines().collect(Collectors.joining("\n"));

        } catch (Exception e) {
            LOGGER.error(e);
            return "";
        }
    }

    @Override
    public boolean isPersistent() {
        return true;
    }

    @Override
    public void add(Log log) {
        String sql = "INSERT INTO logs (log, exc) VALUES (?, ?)";
        jdbc.update(sql, log.getLog(), log.getException());
    }

    @Override
    public List<Log> getAll() {
        SqlRowSet rows = jdbc.queryForRowSet("SELECT * FROM logs");
        List<Log> logs = new ArrayList<>();

        while (rows.next()) {
            int id = rows.getInt("id");
            Timestamp time = rows.getTimestamp("time");
            String log = rows.getString("log");
            String exc = rows.getString("exc");

            logs.add(new Log(id, time, log, exc));
        }
        return logs;
    }

}
