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

    public boolean addStudent(User user) {
        String sql = "INSERT INTO collhbrs.student(id) VALUES (?)";
        PreparedStatement statement = this.getPreparedStatement(sql);

        try {
            statement.setInt(1, user.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean addUnternehmen(User user) {
        String sql = "INSERT INTO collhbrs.unternehmen(id) VALUES (?)";
        PreparedStatement statement = this.getPreparedStatement(sql);

        try {
            statement.setInt(1, user.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
}
