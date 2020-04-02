package day15.util;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author ：LCQJOYCE
 * @date ：Created in 2020/2/29 17:45
 * @description：
 * @version: $
 */
public class Test {
    private static Logger logger = Logger.getLogger(JdbcUtil.class);
    public static void main(String[] args) {

    Connection con=JdbcUtil.getConnection();
        ResultSet rs=null;
        try {
            PreparedStatement Statement=con.prepareStatement("select * from t_user");
           rs=Statement.executeQuery();
            if(rs.next()){
                System.out.println(rs.getObject(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
