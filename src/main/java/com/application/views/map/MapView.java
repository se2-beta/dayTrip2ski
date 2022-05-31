package com.application.views.map;

import com.application.views.MainLayout;
import com.vaadin.flow.component.map.Map;
import com.vaadin.flow.component.map.configuration.Coordinate;
import com.vaadin.flow.component.map.configuration.View;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import javax.annotation.security.RolesAllowed;

@PageTitle("Map")
@Route(value = "map", layout = MainLayout.class)
@RolesAllowed("USER")
public class MapView extends VerticalLayout {

    private Map map = new Map();

    public MapView() {
        setSizeFull();
        setPadding(false);
        map.getElement().setAttribute("theme", "borderless");
        View view = map.getView();
        view.setCenter(Coordinate.fromLonLat(10.0, 55.0));
        view.setZoom(4);
        addAndExpand(map);
    }
}
