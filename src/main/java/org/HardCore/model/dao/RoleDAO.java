package org.HardCore.model.dao;

import org.HardCore.model.objects.dto.Role;
import org.HardCore.model.objects.dto.User;

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



    public List<Role> getRolesForUser(User user ) {
        Statement statement = getStatement();

        ResultSet rs = null;

        try {
            rs = statement.executeQuery("SELECT *" +
                    "FROM collhbrs.user_to_rolle " +
                    "WHERE email = \'" + user.getEmail() + "\'");
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
                role.setBezeichnung(rs.getString(2));
                liste.add(role);
            }
        } catch (SQLException ex) {
            return null;
        }
        return liste;
    }
}
