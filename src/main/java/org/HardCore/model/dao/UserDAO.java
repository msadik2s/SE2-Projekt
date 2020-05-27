package org.HardCore.model.dao;

import org.HardCore.model.objects.dto.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO extends AbstractDAO {
    private static UserDAO dao = null;

    private UserDAO() {

    }

    public static UserDAO getInstance() {
        if (dao == null) {
            dao = new UserDAO();
        }
        return dao;
    }

    public int getMaxID() {
        Statement statement = getStatement();
        ResultSet rs = null;

        try {
            rs = statement.executeQuery("SELECT max(id)" +
                    "FROM collhbrs.user");
        } catch (SQLException throwables) {
            System.out.println("Fehler 1 bei addStudent");
        }

        int currentValue = 0;

        try {
            rs.next();
            currentValue = rs.getInt(1);
        } catch (SQLException throwables) {
            System.out.println("Fehler 2 bei addStudent");
        }
        return currentValue;
    }
}
