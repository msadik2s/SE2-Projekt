package org.HardCore.process.control;

import com.vaadin.ui.UI;
import org.HardCore.gui.ui.MyUI;
import org.HardCore.gui.windows.ConfirmationWindow;
import org.HardCore.model.dao.RegisterDAO;
import org.HardCore.model.dao.RoleDAO;
import org.HardCore.model.dao.UserDAO;
import org.HardCore.model.objects.dto.Student;
import org.HardCore.model.objects.dto.Unternehmen;
import org.HardCore.model.objects.dto.User;
import org.HardCore.process.control.exceptions.DatabaseException;
import org.HardCore.process.control.exceptions.EmailInUseException;
import org.HardCore.process.control.exceptions.NoEqualPasswordException;
import org.HardCore.services.db.JDBCConnection;
import org.HardCore.services.util.Roles;
import org.HardCore.services.util.Views;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegistrationControl {

    public static void checkValid(String email, String password1, String password2) throws NoEqualPasswordException, DatabaseException, EmailInUseException {

        //Passwortcheck
        if ( !password1.equals(password2) ) {
            throw new NoEqualPasswordException("Passwörter stimmen nicht überein!");
        }

        //DB Zugriff Emailcheck
        ResultSet set = null;
        Statement statement = JDBCConnection.getInstance().getStatement();

        try {
            set = statement.executeQuery("SELECT email " +
                    "FROM collhbrs.user " +
                    "WHERE email = \'" + email + "\'");
        } catch (SQLException throwables) {

        }

        try {
            if (set.next()) {
                throw new EmailInUseException("Die Email wird bereits benutzt!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DatabaseException("Fehler bei set: Bitte den Programmierer informieren!");
        }
    }

    //User registrieren
    public static void registerUser( String email, String password, String regAs ) throws DatabaseException {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        boolean registerUser = RegisterDAO.getInstance().addUser(user);
        user.setId(UserDAO.getInstance().getMaxID());

        if (regAs.equals(Roles.STUDENT)) {
            registerUser = RegisterDAO.getInstance().addStudent(user);
            registerUser = RoleDAO.getInstance().setRolesForStudent(user);
        } else {
            registerUser = RegisterDAO.getInstance().addUnternehmen(user);
            registerUser = RoleDAO.getInstance().setRolesForUnternehmen(user);
        }

        if (registerUser == true) {
            UI.getCurrent().addWindow( new ConfirmationWindow("Registration erfolgreich!") );
            ( (MyUI)UI.getCurrent() ).setUser(user);
            UI.getCurrent().getNavigator().navigateTo(Views.MAIN);
        } else {
            throw new DatabaseException("Fehler bei Abschluß der Registration");
        }

    }
}
