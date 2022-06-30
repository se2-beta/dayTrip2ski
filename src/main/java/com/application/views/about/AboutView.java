package com.application.views.about;

import com.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("About")
@Route(value = "about", layout = MainLayout.class)
@AnonymousAllowed
public class AboutView extends VerticalLayout {

    public AboutView() {

        setSizeFull();
        setSpacing(false);

        H1 aboutTitle = new H1("About");
        aboutTitle.addClassNames("pb-s", "mt-0", "pt-m");

        Image logo = new Image("images/daytrip2Ski_logo.png", "logo");
        logo.setMaxWidth("150px");
        logo.setMaxHeight("150px");

        VerticalLayout title = new VerticalLayout(logo, aboutTitle);
        title.setSpacing(false);
        title.setWidthFull();
        title.setJustifyContentMode(JustifyContentMode.CENTER);
        title.setAlignItems(Alignment.CENTER);

        Label aboutText = new Label(
                "Mithilfe der dayTrip2Ski-App wird es Ihnen ermöglicht, das ideale Skigebiet für ihre Ansprüche zu finden. \n" +
                        "Geben sie hierfür in der Skigebiete-Ansicht einfach ihre Präferenzen an und der ausgeklügelte Algorithmus berechnet ihnen ihr Traum-Skigebiet. " +
                        "Natürlich können Sie auch nach einem Skigebiet sowohl in der Kartenansicht als auch in der Liste suchen.");
        aboutText.setMaxWidth("80%");

        HorizontalLayout aboutLayout = new HorizontalLayout(aboutText);
        aboutLayout.setWidthFull();
        aboutLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        aboutLayout.setAlignItems(Alignment.CENTER);

        H1 titleText = new H1("Team Beta");
        titleText.addClassNames("pb-s");

        HorizontalLayout teamTitle = new HorizontalLayout(titleText);
        teamTitle.setWidthFull();
        teamTitle.setJustifyContentMode(JustifyContentMode.CENTER);
        teamTitle.setAlignItems(Alignment.CENTER);

        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setHeightFull();
        mainLayout.setSpacing(false);
        mainLayout.setJustifyContentMode(JustifyContentMode.AROUND);
        mainLayout.add(
                threeMemberLayout(
                        teamMember("images/Annina_Ecker.jpg", "Annina Ecker", "Build Engineer"),
                        teamMember("images/Lukas_Gerber.jpg", "Lukas Gerber", "Frontend Dev"),
                        teamMember("images/Markus_Russold_ws.jpg", "Markus Russold", "Backend Dev")

                ),
                threeMemberLayout(
                        teamMember("images/David_Zollitsch_ws.jpg", "David Zollitsch", "Quality Engineer"),
                        teamMember("images/Andreas_Schoffel.jpg", "Andreas Schöffel", "Frontend Dev"),
                        teamMember("images/Julian_Hirschberger_ws.jpg", "Julian Hirschberger", "Backend Dev")
                )
        );

        add(
                title,
                aboutLayout,
                teamTitle,
                mainLayout
        );
    }

    private Component teamMember(String image, String fullname, String role) {

        Image img = new Image();
        img.setSrc(image);
        img.setWidth("100px");
        img.setHeight("100px");
        img.getElement().getStyle().set("border-radius", "50%").set("object-fit", "cover");

        H3 name = new H3(fullname);
        name.addClassNames("m-0");
        Label position = new Label(role);

        VerticalLayout namePosition = new VerticalLayout(name, position);
        namePosition.setSpacing(false);
        namePosition.addClassNames("p-0", "m-0");
        namePosition.setAlignItems(Alignment.CENTER);

        VerticalLayout layout = new VerticalLayout(img, namePosition);
        layout.addClassNames("pt-m", "pl-m");
        layout.setWidth("33%");
        layout.setAlignItems(Alignment.CENTER);
        return layout;
    }

    private Component threeMemberLayout(Component member1, Component member2, Component member3) {

        HorizontalLayout layout = new HorizontalLayout(
                member1,
                member2,
                member3
        );
        layout.setWidthFull();
        layout.setJustifyContentMode(JustifyContentMode.BETWEEN);

        return layout;
    }

}