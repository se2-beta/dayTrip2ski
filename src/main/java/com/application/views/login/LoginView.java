package com.application.views.login;

import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Login")
@Route(value = "login")
public class LoginView extends Main {

    public LoginView() {

        setSizeFull();
        getElement().getStyle().set("background-image", "url('https://www.tyrol.com/portal/image.php?idPart=vwahqircnxy-')").set("background-size", "cover");

        LoginI18n i18n = LoginI18n.createDefault();
        LoginI18n.Form form = i18n.getForm();
        form.setTitle("daytrip2Ski");
        form.setUsername("Benutzername");
        form.setPassword("Passwort");
        form.setSubmit("Anmelden");
        i18n.setForm(form);
        LoginI18n.ErrorMessage error = new LoginI18n.ErrorMessage();
        error.setMessage("Bitte geben sie ihren Benutzernamen und ihr Passwort ein!");
        i18n.setErrorMessage(error);
        i18n.setAdditionalInformation("© Team Beta - Annina Ecker, Andreas Schöffel, David Zollitsch, Markus Russold, Julian Hirschberger, Lukas Gerber");

        LoginForm lf = new LoginForm();
        lf.setAction("login");
        lf.setError(true);
        lf.setForgotPasswordButtonVisible(false);
        lf.setI18n(i18n);

        FlexLayout flexLayout = new FlexLayout(lf);
        flexLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        flexLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        flexLayout.setHeightFull();

        add(
                flexLayout

        );

    }

}

