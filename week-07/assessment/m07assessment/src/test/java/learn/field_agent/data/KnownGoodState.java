package learn.field_agent.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

@Component
public class KnownGoodState {

    @Autowired
    JdbcClient jdbcClient;

    static boolean hasRun = false;

    void set() {
        if (!hasRun) {
//            hasRun = true;
            jdbcClient.sql("call set_known_good_state();").update();
        }
    }
}
