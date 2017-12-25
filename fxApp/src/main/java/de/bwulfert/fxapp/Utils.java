package de.bwulfert.fxapp;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class Utils {

    public static Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            Integer columnIndex = GridPane.getColumnIndex(node);
            Integer rowIndex = GridPane.getRowIndex(node);
            if (node != null && columnIndex != null && columnIndex == col && rowIndex != null && rowIndex == row) {
                return node;
            }
        }
        return null;
    }

}
