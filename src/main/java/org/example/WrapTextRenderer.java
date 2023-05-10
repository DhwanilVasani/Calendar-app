package org.example;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;

class WrapTextRenderer extends JTextArea implements TableCellRenderer {

    public WrapTextRenderer() {
        setLineWrap(true);
        setWrapStyleWord(true);
    }

    public java.awt.Component getTableCellRendererComponent(JTable table, Object value,
                                                            boolean isSelected, boolean hasFocus, int row, int column) {
        setText((String) value);
        setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);
        if (isSelected) {
            setBackground(table.getSelectionBackground());
        } else {
            setBackground(table.getBackground());
        }
        return this;
    }
}



