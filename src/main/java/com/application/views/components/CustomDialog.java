package com.application.views.components;

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.function.SerializableConsumer;

public class CustomDialog extends Dialog {

    private static final String SET_PROPERTY_IN_OVERLAY_JS = "this.$.overlay.$.overlay.style[$0]=$1";

    public void setPosition(Position position) {
        enablePositioning(true);
        //getElement().executeJs(SET_PROPERTY_IN_OVERLAY_JS, "left", position.getLeft());
        getElement().executeJs(SET_PROPERTY_IN_OVERLAY_JS, "right", position.getRight());
        getElement().executeJs(SET_PROPERTY_IN_OVERLAY_JS, "top", position.getTop());
    }

    private void enablePositioning(boolean positioningEnabled) {
        getElement()
                .executeJs(SET_PROPERTY_IN_OVERLAY_JS, "align-self", positioningEnabled ? "flex-end" : "unset");
        getElement()
                .executeJs(SET_PROPERTY_IN_OVERLAY_JS, "position", positioningEnabled ? "absolute" : "relative");
    }

    public void getPosition(SerializableConsumer<Position> consumer) {
        getElement()
                .executeJs(
                        "return [" + "this.$.overlay.$.overlay.style['top'], this.$.overlay.$.overlay.style['right']" + "]"
                )
                .then(
                        String.class,
                        s -> {
                            String[] split = s.split(","); // StringUtils.split(s, ',')
                            Notification.show(split[0] + " " + split[1]);
                            if (split.length == 2 && split[0] != null && split[1] != null) {
                                Position position = new Position(split[0], split[1]);
                                consumer.accept(position);
                            }
                        }
                );
    }

    public static class Position {
        private String top;
        private String right;

        public Position(String top, String right) {
            this.top = top;
            this.right = right;
        }

        public String getTop() {
            return top;
        }

        public void setTop(String top) {
            this.top = top;
        }

        public String getRight() {
            return right;
        }

        public void setRight(String right) {
            this.right = right;
        }

    }

}
