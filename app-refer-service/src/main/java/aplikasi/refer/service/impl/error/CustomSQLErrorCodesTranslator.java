/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.peserta.service.impl.error;

import java.sql.SQLException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DeadlockLoserDataAccessException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

/**
 *
 * @author Divisi-TI
 */
public class CustomSQLErrorCodesTranslator extends SQLErrorCodeSQLExceptionTranslator {

    @Override
    protected DataAccessException customTranslate(String task, String sql, SQLException sqlex) {
        if (sqlex.getErrorCode() == -12345) {
            return new DeadlockLoserDataAccessException(task, sqlex);
        }
        return null;
    }
}
