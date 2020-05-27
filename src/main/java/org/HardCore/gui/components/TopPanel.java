package org.HardCore.gui.components;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;
import org.HardCore.gui.ui.MyUI;
import org.HardCore.model.objects.dto.Student;
import org.HardCore.model.objects.dto.Unternehmen;
import org.HardCore.model.objects.dto.User;
import org.HardCore.process.control.LoginControl;
import org.HardCore.services.util.Roles;
import org.HardCore.services.util.Views;

public class TopPanel extends HorizontalLayout {

    public TopPanel() {
        this.setSizeFull();

        //Logo links oben in der Ecke
        Label headlabel = new Label("HardCore-Logo");
        headlabel.setSizeUndefined();
        this.addComponent(headlabel);
        this.setComponentAlignment(headlabel, Alignment.TOP_LEFT);


        //Willkommenstext oben rechts
        HorizontalLayout hlayout = new HorizontalLayout();
        User user = ( (MyUI)MyUI.getCurrent() ).getUser();
        Label welcome = null;
        if (user == null) {
            welcome = new Label("Willkommen bei HardCore!");
        } else if ( user.hasRole(Roles.STUDENT) && user.getVorname() != null ) {
            welcome = new Label("Willkommen " + user.getVorname() + "!");
        } else if (user.hasRole(Roles.UNTERNEHMEN) && user.getName() != null ) {
            welcome = new Label("Willkommen " + user.getName() + "!");
        } else {
            welcome = new Label("Willkommen bei HardCore!");
        }
        hlayout.addComponent(welcome);
        hlayout.setComponentAlignment(welcome, Alignment.MIDDLE_CENTER);


        //Menü rechts oben
        MenuBar bar = new MenuBar();
        MenuBar.MenuItem item1 = bar.addItem("Menu", null);


        //Gast Menü
        if (user == null) {
            item1.addItem("Login", VaadinIcons.SIGN_IN, new MenuBar.Command() {
                @Override
                public void menuSelected(MenuBar.MenuItem menuItem) {
                    UI.getCurrent().getNavigator().navigateTo(Views.LOGIN);
                }
            });
            item1.addItem("Registrieren", new MenuBar.Command() {
                @Override
                public void menuSelected(MenuBar.MenuItem menuItem) {
                    UI.getCurrent().getNavigator().navigateTo(Views.REGISTRATION);
                }
            });
        }

        //Profil
        if (user != null) {
            item1.addItem("Profil", VaadinIcons.USER, null);

            //Unternehmer Menü
            if ( user.hasRole(Roles.UNTERNEHMEN) ) {
                item1.addItem("Meine Stellenanzeigen", VaadinIcons.FILE_TEXT_O, null);
            }

            //Student Menü
            if ( user.hasRole(Roles.STUDENT) ) {
                item1.addItem("Meine Bewerbungen", VaadinIcons.FILE_TEXT_O, null);
            }

            item1.addItem("Logout", VaadinIcons.SIGN_OUT, new MenuBar.Command() {
                @Override
                public void menuSelected(MenuBar.MenuItem menuItem) {
                    LoginControl.logoutUser();
                }
            });
        }

        //Einfügen
        hlayout.addComponent(bar);
        this.addComponent(hlayout);
        this.setComponentAlignment(hlayout, Alignment.TOP_RIGHT);
    }

}
