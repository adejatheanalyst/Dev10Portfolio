package learn.data;

import learn.data.mappers.ResourceMapper;
import learn.models.Resources;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResourceClientRepository implements ResourceRepository{
    private final JdbcClient jdbcClient;

    public ResourceClientRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }
    private static final String selectAll = "select resourceId, title, resourceUrl, moodId from resources";

    @Override
    public List<Resources> findAll() {
       return jdbcClient.sql(selectAll)
               .query(new ResourceMapper())
               .list();
    }
}
