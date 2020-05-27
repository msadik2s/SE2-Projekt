package org.HardCore.model.dao;

import org.HardCore.model.objects.dto.User;

import java.sql.*;

public class RegisterDAO extends AbstractDAO {
    private static RegisterDAO dao = null;

    private RegisterDAO() {

    }

    public static RegisterDAO getInstance() {
        if (dao == null) {
            dao = new RegisterDAO();
        }
        return dao;
    }

    public boolean addUser(User user) {
        String sql = "INSERT INTO collhbrs.user VALUES (default,?,?)";
        PreparedStatement statement = this.getPreparedStatement(sql);

        try {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

}
