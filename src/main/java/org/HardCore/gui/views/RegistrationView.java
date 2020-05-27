package org.HardCore.gui.views;

import com.vaadin.data.Binder;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.HardCore.gui.components.TopPanel;
import org.HardCore.model.objects.dto.User;
import org.HardCore.process.control.RegistrationControl;
import org.HardCore.process.control.exceptions.DatabaseException;
import org.HardCore.process.control.exceptions.EmailInUseException;
import org.HardCore.process.control.exceptions.NoEqualPasswordException;
import org.HardCore.services.util.Roles;

public class RegistrationView extends VerticalLayout implements View {

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.setUp();
    }

    private void setUp() {
        this.addComponent( new TopPanel() );
        Label line = new Label("<hr>", ContentMode.HTML);
        this.addComponent(line);
        line.setSizeFull();

        //Eingabefelder
        final TextField fieldEmail = new TextField("Email:");
        fieldEmail.setRequiredIndicatorVisible(true);
        Binder<User> binder = new Binder<>();
        binder.forField(fieldEmail)
                .withValidator(new EmailValidator("Biite geben Sie eine korrekte Emailadresse ein!"))
                .bind(User::getEmail, User::setEmail);
        final PasswordField fieldPassword1 = new PasswordField("Passwort:");
        fieldPassword1.setRequiredIndicatorVisible(true);
        final PasswordField fieldPassword2 = new PasswordField("Passwort wiederholen:");
        fieldPassword2.setRequiredIndicatorVisible(true);

        //Checkbox
        RadioButtonGroup<String> radioButtonGroup = new RadioButtonGroup("Registrieren als:");
        radioButtonGroup.setItems("Student", "Unternehmen");

        //Register Button
        Button registerButton = new Button("Registrieren");
        registerButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                String email = fieldEmail.getValue();
                String password1 = fieldPassword1.getValue();
                String password2 = fieldPassword2.getValue();
                String regAs = radioButtonGroup.getValue();

                try {
                    RegistrationControl.checkValid( email, password1, password2 );
                    RegistrationControl.registerUser( email, password1, regAs );
                } catch (NoEqualPasswordException e) {
                    Notification.show("Passwort-Fehler", e.getReason(), Notification.Type.WARNING_MESSAGE);
                } catch (DatabaseException e) {
                    Notification.show("DB-Fehler", e.getReason(), Notification.Type.ERROR_MESSAGE);
                } catch (EmailInUseException e) {
                    Notification.show("Email-Fehler", e.getReason(), Notification.Type.ERROR_MESSAGE);
                }
            }
        });

        //Vertical Layout
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addComponent(fieldEmail);
        verticalLayout.addComponent(fieldPassword1);
        verticalLayout.addComponent(fieldPassword2);
        verticalLayout.addComponent(radioButtonGroup);
        verticalLayout.addComponent(registerButton);

        //Panel
        Panel panel = new Panel( "Bitte geben Sie ihre Daten ein:");
        panel.setContent(verticalLayout);
        panel.setSizeUndefined();

        //Einfügen
        this.addComponent(panel);
        this.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
    }

}
