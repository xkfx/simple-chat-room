package chatroom.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBAccess {
    /**
     * 数据库驱动
      */
    private static final String JDBC_DRIVER = "org.gjt.mm.mysql.Driver";
    /**
     * 数据库地址
     */
    private static final String DB_URL = "jdbc:mysql://localhost:3306/chat";
    /**
     * 用户名与密码
     */
    private static final String USER = "root";
    private static final String PASS = "19971019";
    /**
     * 数据库连接
     */
    private static Connection conn = null;

    // 加载数据库驱动
    static {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return conn;
    }
}