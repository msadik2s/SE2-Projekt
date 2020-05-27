package org.HardCore.model.dao;

import org.HardCore.model.objects.dto.Role;
import org.HardCore.model.objects.dto.User;
import org.HardCore.services.util.Roles;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO extends AbstractDAO{
    private static RoleDAO dao = null;

    private RoleDAO() {

    }

    public static RoleDAO getInstance() {
        if (dao == null) {
            dao = new RoleDAO();
        }
        return dao;
    }

    public List<Role> getRolesForUser(User user) {
        Statement statement = getStatement();

        ResultSet rs = null;

        try {
            rs = statement.executeQuery("SELECT rolle " +
                    "FROM collhbrs.user_to_rolle " +
                    "WHERE id = \'" + user.getId() + "\'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (rs == null) {
            return null;
        }

        List<Role> liste = new ArrayList<>();
        Role role = null;

        try {
            while (rs.next()) {
                role = new Role();
                role.setBezeichnung(rs.getString(1));
                liste.add(role);
            }
        } catch (SQLException ex) {
            return null;
        }
        return liste;
    }

    public boolean setRolesForStudent(User user) {
        String sql = "INSERT INTO collhbrs.user_to_rolle VALUES (?,?)";
        PreparedStatement statement = this.getPreparedStatement(sql);

        try {
            statement.setInt(1, user.getId());
            statement.setString(2, Roles.STUDENT);
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    public boolean setRolesForUnternehmen(User user) {
        String sql = "INSERT INTO collhbrs.user_to_rolle VALUES (?,?)";
        PreparedStatement statement = this.getPreparedStatement(sql);

        try {
            statement.setInt(1, user.getId());
            statement.setString(2, Roles.UNTERNEHMEN);
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
 }
