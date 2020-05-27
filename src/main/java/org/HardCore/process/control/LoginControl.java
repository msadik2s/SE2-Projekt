package org.HardCore.process.control;

import com.vaadin.ui.UI;
import org.HardCore.gui.ui.MyUI;
import org.HardCore.model.dao.UserDAO;
import org.HardCore.model.objects.dto.User;
import org.HardCore.process.control.exceptions.DatabaseException;
import org.HardCore.process.control.exceptions.NoSuchUserOrPassword;
import org.HardCore.services.db.JDBCConnection;
import org.HardCore.services.util.Views;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginControl {

    public static void checkAuthentification( String email, String password) throws NoSuchUserOrPassword, DatabaseException {

        //DB User abfrage
        ResultSet rs = null;
        Statement statement = JDBCConnection.getInstance().getStatement();
        try {
            rs = statement.executeQuery("SELECT id " +
                    "FROM collhbrs.user " +
                    "WHERE email = \'" + email + "\' " +
                    "AND password = \'" + password + "\'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DatabaseException("Fehler im SQL-Befehl: Bitte den Programmierer informieren!");
        }

        User user = null;

        try {
            if( rs.next() ) {
                user = new User();
                user.setId(rs.getInt(1));
            }
            else {
                throw new NoSuchUserOrPassword();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            JDBCConnection.getInstance().closeConnection();
        }
        ((MyUI) UI.getCurrent()).setUser(user);
        UI.getCurrent().getNavigator().navigateTo(Views.MAIN);
    }

    public static void logoutUser() {
        UI.getCurrent().close();
        UI.getCurrent().getPage().setLocation("/HardCore");
    }
}
