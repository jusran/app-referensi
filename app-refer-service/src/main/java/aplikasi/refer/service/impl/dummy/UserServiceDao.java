/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.refer.service.impl.dummy;

import aplikasi.peserta.service.impl.error.CustomSQLErrorCodesTranslator;
import aplikasi.refer.domain.User;
import aplikasi.refer.service.UserService;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Divisi-TI
 */
@Repository("userServiceDao")
@Transactional
public class UserServiceDao implements UserService {

    private JdbcTemplate jdbcTemplate = null;
    private SimpleJdbcTemplate simpleJdbcTemplate = null;

    @Autowired
    public void setDataSource(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);

        // create a custom translator and set the DataSource for the default translation lookup 
        CustomSQLErrorCodesTranslator tr = new CustomSQLErrorCodesTranslator();
        tr.setDataSource(dataSource);
        this.jdbcTemplate.setExceptionTranslator(tr);
//        this.simpleJdbcTemplate.setExceptionTranslator(tr);
    }

    @Override
    public User findUserById(int id) {
        String sql = "select userid, username, firstname, lastname, email from userPassword where userid=?";
        User pst = simpleJdbcTemplate.queryForObject(sql, new UserRowMapper(), id);            
        return pst;
    }

    private class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int arg1) throws SQLException {
            User p = new User();
            p.setId(rs.getInt("userid"));
            p.setUsername(rs.getString("username"));
            p.setFirstname(rs.getString("firstname"));
            p.setLastname(rs.getString("lastname"));
            p.setEmail(rs.getString("email"));
            return p;
        }
    }
}
