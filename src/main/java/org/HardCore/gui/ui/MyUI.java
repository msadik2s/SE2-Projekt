package org.HardCore.gui.ui;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import org.HardCore.gui.views.LoginView;
import org.HardCore.gui.views.MainView;
import org.HardCore.gui.views.ProfilView;
import org.HardCore.gui.views.RegistrationView;
import org.HardCore.model.objects.dto.User;
import org.HardCore.services.util.Views;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Title("HardCOre")
@PreserveOnRefresh
public class MyUI extends UI {
    private User user= null;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Navigator navi = new Navigator(this,this);

        navi.addView(Views.MAIN, MainView.class);
        navi.addView(Views.PROFILE, ProfilView.class);
        navi.addView(Views.REGISTRATION, RegistrationView.class);
        navi.addView(Views.LOGIN, LoginView.class);

        UI.getCurrent().getNavigator().navigateTo(Views.MAIN);
    }

    public  MyUI getMyUI() {
        return (MyUI) UI.getCurrent();
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
