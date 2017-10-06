package certain.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import certain.domain.Authority;

@Repository
public class AuthorityRepository {

    @Autowired
    private DataSource                 dataSource;
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Resource(name = "certainDB")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public ArrayList<Authority> getAuthorities(String username) {

        String sql = "select * from authority where username=:uname";
        ArrayList<Authority> aus = new ArrayList<Authority>();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("uname", username);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, params);
        for (Map<String, Object> row : rows) {

            Authority a = new Authority();
            a.setName(row.get("name").toString());
            aus.add(a);
        }
        return aus;
    }


}
