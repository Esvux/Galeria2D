package org.galeria.utilities;

/**
 *
 * @author esvux
 */
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.text.SimpleDateFormat;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;

public class DBUtils {

    private final String url, user, password;
    private static DBUtils dbutils = null;
    public static final SimpleDateFormat formateador = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");
            
    public static DBUtils getInstance(){
        if(dbutils == null)
            dbutils = new DBUtils();
        return dbutils;
    }
    
    private DBUtils() {
        this.url = "jdbc:mysql://127.0.0.1:3306/lienzos";
        this.user = "root";
        this.password = "";
    }

    public DataSource getDataSource() {
        MysqlDataSource mysqlDS = new MysqlDataSource();
        mysqlDS.setURL(url);
        mysqlDS.setUser(user);
        mysqlDS.setPassword(password);
        return mysqlDS;
    }
    
    public QueryRunner getRun(){
        QueryRunner run = new QueryRunner(this.getDataSource());
        return run;
    }
    
}