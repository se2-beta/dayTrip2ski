package com.application.views.freeride;

import com.application.views.MainLayout;
import com.application.views.imagelist.SkiResortDetailView;
import com.application.views.imagelist.SkiResortListView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParameters;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Freeride")
@Route(value = "freeride", layout = MainLayout.class)
@AnonymousAllowed
public class FreerideView extends VerticalLayout {

    public FreerideView() {
        setSpacing(false);

        Image img = new Image();
        img.setSrc("https://media0.giphy.com/media/kTPUu2dXsWu4YmcxWz/200w.gif?cid=82a1493bupy9boa59iugssof6bqejpbli496p5uw7i6k3pcq&rid=200w.gif&ct=g");
        img.setWidth("200px");
        img.setHeight("200px");
        add(img);

        Paragraph lawText = new Paragraph("""
                Gesetzliche Grundlagen
                § 33 des Forstgesetzes gibt jedermann das Recht,
                Wald zu Erholungszwecken zu betreten. Das
                schließt an sich das Skilaufen, einschließlich des
                Abfahrens mit Alpinschiern, ein. Der zweite Satz des
                § 33 Abs. 3 verbietet nun das „Abfahren mit Schiern
                im Wald... im Bereich von Aufstiegshilfen“ außerhalb
                von markierten Pisten oder Skirouten. Die Bestimmung soll verhindern,
                dass Benützer von Aufstiegshilfen mehrmals am Tag statt auf den erschlossenen
                Pisten (oder Skirouten) „zur Abwechslung“ durch
                den Wald abfahren, also das typische „Variantenfahren“. Wieweit der „Bereich von Aufstiegshilfen“
                reicht, für den dieses Verbot gilt, ist im einzelnen
                etwas strittig, die obigen umschriebene „Variante“
                fällt jedenfalls auch bei großzügigem Verständnis dieses Begriffs darunter. Gerade auf Varianten
                in dem hier verwendeten Sinn ist also das Abfahren mit Skiern im Wald verboten. Eine Übertretung
                dieses Verbots ist nicht nur mit den zivilrechtlichen
                Folgen sanktioniert, die dem Grundeigentümer
                sonst bei unberechtigtem Betreten seines Grundes
                zustehen (Besitzstörungsklage, Unterlassungsklage,
                Schadenersatzansprüche), sondern auch mit einer
                Verwaltungsstrafe.""");
        lawText.setWidth("80%");

        Paragraph information = new Paragraph("""
                Sollten Sie dennoch Informationen zu Wetterbedingungen, Schneefall, usw. in den jeweiligen Skigebieten benötigen,
                dann können Sie diese gerne unter dem Menüpunkt "Skigebiete" abfragen.
                """);
        information.getElement().getStyle().set("font-weight", "bold");
        information.setWidth("80%");


        Button redirect = new Button("weiterleiten zu Skigebiete");
        redirect.addClickListener(e -> UI.getCurrent().navigate(SkiResortListView.class)
        );


        add(
                new H2("Freeriden ist nur unter bestimmten Bedingungen erlaubt!"),
                lawText,
                information,
                redirect
        );

        setSizeFull();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}
