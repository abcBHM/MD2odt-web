package cz.zcu.kiv.md2odt.web.service.log;

import cz.zcu.kiv.md2odt.web.dto.LogEntry;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Stores logs entries into PostgreSQL.
 *
 * @author Patrik Harag
 * @version 2017-04-16
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
    public void add(LogEntry log) {
        String sql = "INSERT INTO logs (\"start-time\", \"end-time\", \"log\", \"exception\") VALUES (?, ?, ?, ?)";
        jdbc.update(sql, log.getStartTime(), log.getEndTime(), log.getLog(), log.getException());
    }

    @Override
    public List<LogEntry> getAll() {
        SqlRowSet rows = jdbc.queryForRowSet("SELECT * FROM logs ORDER BY id DESC LIMIT 100");
        List<LogEntry> logs = new ArrayList<>();

        while (rows.next()) {
            int id = rows.getInt("id");

            long start = rows.getLong("start-time");
            long end = rows.getLong("end-time");

            String log = rows.getString("log");
            String exc = rows.getString("exception");

            logs.add(new LogEntry(id, start, end, log, exc));
        }
        return logs;
    }

}
