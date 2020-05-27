package org.HardCore.gui.views;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.HardCore.gui.components.TopPanel;
import org.HardCore.gui.ui.MyUI;
import org.HardCore.model.objects.dto.User;

public class MainView extends VerticalLayout implements View {

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        User user = ( (MyUI)UI.getCurrent() ).getUser();

        this.setUp();
    }

    private void setUp() {
        //Top Layer
        this.addComponent( new TopPanel() );
        Label line = new Label("<hr>", ContentMode.HTML);
        this.addComponent(line);
        line.setSizeFull();

        //Suchfeld
        final TextField suche = new TextField();

        //Button
        Button suchButton = new Button("Suchen", VaadinIcons.SEARCH);

        //Horizontal Layout
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponent(suche);
        horizontalLayout.addComponent(suchButton);
        horizontalLayout.setComponentAlignment(suche, Alignment.MIDDLE_CENTER);
        horizontalLayout.setComponentAlignment(suchButton, Alignment.MIDDLE_CENTER);

        //Darstellen
        this.addComponent(horizontalLayout);
        this.setComponentAlignment(horizontalLayout, Alignment.MIDDLE_CENTER);
        /*
        //Suchbutton Config
        suchButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                String suchtext = suche.getValue();

            }
        });
         */


    }


}
