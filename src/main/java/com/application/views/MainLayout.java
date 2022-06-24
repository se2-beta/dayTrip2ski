package com.application.views;

import com.application.data.entity.User;
import com.application.security.AuthenticatedUser;
import com.application.views.about.AboutView;
import com.application.views.freeride.FreerideView;
import com.application.views.imagelist.SkiResortListView;
import com.application.views.map.MapView;
import com.application.views.masterdetail.MasterDetailView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;

import java.util.Optional;

public class MainLayout extends AppLayout {

    public static class MenuItemInfo extends ListItem {
        private final Class<? extends Component> view;

        public MenuItemInfo(String menuTitle, String iconClass, Class<? extends Component> view) {
            this.view = view;
            RouterLink link = new RouterLink();
            link.addClassNames("menu-item-link");
            link.setRoute(view);

            Span text = new Span(menuTitle);
            text.addClassNames("text-l", "pl-s");

            HorizontalLayout iconText = new HorizontalLayout(new LineAwesomeIcon(iconClass), text);
            iconText.setAlignItems(FlexComponent.Alignment.CENTER);
            iconText.setSpacing(false);

            link.add(iconText);
            add(link);
        }

        public Class<?> getView() {
            return view;
        }

        @NpmPackage(value = "line-awesome", version = "1.3.0")
        public static class LineAwesomeIcon extends Span {
            public LineAwesomeIcon(String lineawesomeClassnames) {
                addClassNames("menu-item-icon");
                if (!lineawesomeClassnames.isEmpty()) {
                    addClassNames(lineawesomeClassnames);
                }
            }
        }
    }

    private H1 viewTitle;

    private AuthenticatedUser authenticatedUser;
    private AccessAnnotationChecker accessChecker;

    public MainLayout(AuthenticatedUser authenticatedUser, AccessAnnotationChecker accessChecker) {
        this.authenticatedUser = authenticatedUser;
        this.accessChecker = accessChecker;

        setPrimarySection(Section.DRAWER);
        addToNavbar(true, createHeaderContent());
        addToDrawer(createDrawerContent());
    }

    private Component createHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.addClassNames("view-toggle");
        toggle.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        toggle.getElement().setAttribute("aria-label", "Menu toggle");

        viewTitle = new H1();
        viewTitle.addClassNames("view-title");

        Nav nav = new Nav();
        nav.addClassNames("gap-s", "overflow-auto", "px-m");
        nav.setWidth("100%");


        // Wrap the links in a list; improves accessibility
        UnorderedList list = new UnorderedList();
        list.addClassNames("flex", "list-none", "m-0", "p-0", "justify-center");
        list.setWidth("93%");
        nav.add(list);

        for (MenuItemInfo menuItem : createTopMenuItems()) {
            list.add(menuItem);

        }

        Header header = new Header(toggle, viewTitle, nav);
        header.addClassNames("view-header");
        return header;
    }


    private Component createDrawerContent() {
        Image appLogo = new Image("images/daytrip2Ski_logo_transp_white_text.png", "logo");
        appLogo.addClassNames("pb-m");

        com.vaadin.flow.component.html.Section section = new com.vaadin.flow.component.html.Section(appLogo,
                createNavigation(), createFooter());
        section.addClassNames("drawer-section");
        return section;
    }

    private Nav createNavigation() {
        Nav nav = new Nav();
        nav.addClassNames("menu-item-container");
        nav.getElement().setAttribute("aria-labelledby", "views");

        // Wrap the links in a list; improves accessibility
        UnorderedList list = new UnorderedList();
        list.addClassNames("navigation-list");
        nav.add(list);

        for (MenuItemInfo menuItem : createMenuItems()) {
            if (accessChecker.hasAccess(menuItem.getView())) {
                list.add(menuItem);
            }

        }
        return nav;
    }

    private MenuItemInfo[] createTopMenuItems() {
        return new MenuItemInfo[]{ //
                new MenuItemInfo("Pisten", "la la-skiing-nordic", SkiResortListView.class), //
                new MenuItemInfo("Freeride", "la la-skiing", FreerideView.class), //
        };
    }

    private MenuItemInfo[] createMenuItems() {
        return new MenuItemInfo[]{ //
                new MenuItemInfo("Skigebiete", "la la-skiing-nordic", SkiResortListView.class), //
                new MenuItemInfo("Karte", "la la-map", MapView.class), //
                new MenuItemInfo("Admin-Bereich", "la la-tasks", MasterDetailView.class), //
                new MenuItemInfo("About", "la la-question", AboutView.class), //
                new MenuItemInfo("Debug", "la la-bug", DebugView.class), //
        };
    }

    private Footer createFooter() {
        Footer layout = new Footer();
        layout.addClassNames("footer");

        Optional<User> maybeUser = authenticatedUser.get();
        if (maybeUser.isPresent()) {
            User user = maybeUser.get();

            Avatar avatar = new Avatar(user.getName(), user.getProfilePictureUrl());
            avatar.addClassNames("me-xs");

            ContextMenu userMenu = new ContextMenu(avatar);
            userMenu.setOpenOnClick(true);
            userMenu.addItem("Settings", e -> Notification.show("Einstellungen sind noch nicht verfügbar!"));
            userMenu.addItem("Logout", e -> authenticatedUser.logout());

            Span name = new Span(user.getName());
            name.addClassNames("font-medium", "text-s", "text-secondary");

            layout.add(avatar, name);
        } else {
            Anchor loginLink = new Anchor("login", "Sign in");
            layout.add(loginLink);
        }

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }

    public static MainLayout get() {
        return (MainLayout) UI.getCurrent().getChildren()
                .filter(component -> component.getClass() == MainLayout.class)
                .findFirst().get();
    }
}

