package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyGUI extends JFrame {
    private JTable table;
    private JScrollPane scrollPane;
    private JButton getDataBtn, selectRowsBtn;
    // private Holiday holidays;

    public MyGUI() {
        // Set the layout to BorderLayout
        setLayout(new BorderLayout());

        // Create the table and add it to the center of the JFrame
        table = new JTable();
        add(table, BorderLayout.CENTER);
        table.setRowHeight(30);
        // Create the JScrollPane and add the table to it
        scrollPane = new JScrollPane(table);

        // Add the JScrollPane to the center of the JFrame
        add(scrollPane, BorderLayout.CENTER);

        // Create the JPanel and add it to the south of the JFrame
        JPanel panel = new JPanel();
        add(panel, BorderLayout.SOUTH);

        // Create the "Get Data" button and add it to the JPanel
        getDataBtn = new JButton("Get Data");
        getDataBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Holiday> holidays = JsoupTest.loadedHolidays();

                // Retrieve the scrapped data and populate the table with it
                // List<String[]> data = getData();
                String[] columnNames = {"Date", "Day", "Holiday","State"};
                table.setModel(new MyTableModel(columnNames, holidays));
                table.setDefaultRenderer(String.class, new WrapTextRenderer());

            }
        });
        panel.add(getDataBtn);

        // Create the "Select Rows" button and add it to the JPanel
        selectRowsBtn = new JButton("Select Rows");
        selectRowsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve the selected rows from the table and store them in a list
                net.fortuna.ical4j.model.Calendar calendar = CreateCalendar.creatCalendar();
                List<String[]> selectedRows = getSelectedRows();
                for (int i = 0; i < selectedRows.size(); i++) {

                    // Print all elements of List
                    System.out.println(selectedRows.get(i)[0]);
                    CreateCalendar.addEvent(calendar, selectedRows.get(i)[0], selectedRows.get(i)[2]);
                }
                // Create the calendar
                try {
                    CreateCalendar.createICSFile(calendar);
                } catch (IOException e1) {
                    System.out.print("Error creating Calendar");
                    e1.printStackTrace();
                }
            }
        });
        panel.add(selectRowsBtn);

        // Set the size and visibility of the JFrame
        setSize(500, 500);
        setVisible(true);
    }

    // private List<String[]> getData() {
    // Retrieve the scrapped data and return it as a list of String arrays
    // List<String[]> data = new ArrayList<>();
    // // Code to retrieve scrapped data goes here...
    // data.add(new String[]{"Data 1", "Data 2", "Data 3"});
    // data.add(new String[]{"Data 4", "Data 5", "Data 6"});
    // data.add(new String[]{"Data 7", "Data 8", "Data 9"});
    // return data;
    // }

    private List<String[]> getSelectedRows() {
        // Retrieve the selected rows from the table and return them as a list of String arrays
        List<String[]> selectedRows = new ArrayList<>();
        int[] rows = table.getSelectedRows();
        System.out.println("printing selected rows");
        // System.out.println(rows);
        for (int i = 0; i < rows.length; i++) {

            String[] rowData = new String[table.getColumnCount()];
            // System.out.println(rowData.length);
            for (int j = 0; j < rowData.length; j++) {
                rowData[j] = table.getValueAt(rows[i], j).toString();
            }
            selectedRows.add(rowData);

        }
        // System.out.println(selectedRows);
        return selectedRows;
    }

    private class MyTableModel extends DefaultTableModel {
        public MyTableModel(String[] columnNames, List<Holiday> holidays) {
            super(columnNames, 0);
            for (Holiday holiday : holidays) {
                addRow(new Object[]{holiday.getDate(), holiday.getDay(), holiday.getHoliday(), holiday.getState()});
            }
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            // Make the table cells non-editable
            return false;
        }
    }

    public static void main(String[] args) {
        // Create an instance of the MyGUI class
        MyGUI gui = new MyGUI();
        // Make the JFrame visible
        gui.setVisible(true);
    }
}
