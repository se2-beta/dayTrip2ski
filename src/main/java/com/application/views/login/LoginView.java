package com.application.views.login;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Login")
@Route(value = "login")
public class LoginView extends Main {

    LoginForm loginForm = new LoginForm();

    public LoginView() {

        setSizeFull();
        getElement().getStyle()
                .set("background-image", "url('https://www.tyrol.com/portal/image.php?idPart=vwahqircnxy-')")
                .set("background-size", "cover");

        Image titleLogo = new Image("images/daytrip2Ski_logo_transp.png", "logo");
        titleLogo.setWidth("250px");

        LoginI18n i18n = LoginI18n.createDefault();
        LoginI18n.Form form = i18n.getForm();
        form.setUsername("Benutzername");
        form.setPassword("Passwort");
        form.setSubmit("Anmelden");
        i18n.setForm(form);
        i18n.setAdditionalInformation("© Team Beta - " +
                "Annina Ecker, " +
                "Andreas Schöffel, " +
                "David Zollitsch, " +
                "Julian Hirschberger, " +
                "Lukas Gerber, " +
                "Markus Russold");

        loginForm.setAction("login");
        loginForm.setForgotPasswordButtonVisible(false);
        loginForm.setI18n(i18n);

        VerticalLayout layout = new VerticalLayout(titleLogo, loginForm);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setHeightFull();

        add(layout);

    }
}
