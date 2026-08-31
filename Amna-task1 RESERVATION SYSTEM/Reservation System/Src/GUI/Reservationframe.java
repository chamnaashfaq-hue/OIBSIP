package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import DAO.TrainDAO;
import DAO.ReservationDAO;
import Model.reservation;

public class Reservationframe extends JFrame {

    private JTextField nameField, trainNumberField, dateField, sourceField, destField;
    private JLabel trainNameLabel;
    private JComboBox<String> classBox;

    private final Color GREEN = new Color(0, 100, 0);
    private final Color CREAM = new Color(255, 253, 240);

    public Reservationframe() {
        setTitle("Book Ticket");
        setSize(450, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(CREAM);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel titleLabel = new JLabel("Reservation Form");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(GREEN);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(titleLabel, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Passenger Name:"), gbc);
        nameField = new JTextField(15);
        gbc.gridx = 1;
        add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Train Number:"), gbc);
        trainNumberField = new JTextField(15);
        gbc.gridx = 1;
        add(trainNumberField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Train Name:"), gbc);
        trainNameLabel = new JLabel("(auto-filled)");
        gbc.gridx = 1;
        add(trainNameLabel, gbc);

        JButton fetchButton = new JButton("Fetch Train Name");
        fetchButton.setBackground(GREEN);
        fetchButton.setForeground(Color.WHITE);
        gbc.gridx = 1; gbc.gridy = 4;
        add(fetchButton, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        add(new JLabel("Class:"), gbc);
        classBox = new JComboBox<>(new String[]{"Economy", "Business", "First Class"});
        gbc.gridx = 1;
        add(classBox, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        add(new JLabel("Journey Date (YYYY-MM-DD):"), gbc);
        dateField = new JTextField(15);
        gbc.gridx = 1;
        add(dateField, gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        add(new JLabel("Source Station:"), gbc);
        sourceField = new JTextField(15);
        gbc.gridx = 1;
        add(sourceField, gbc);

        gbc.gridx = 0; gbc.gridy = 8;
        add(new JLabel("Destination Station:"), gbc);
        destField = new JTextField(15);
        gbc.gridx = 1;
        add(destField, gbc);

        JButton bookButton = new JButton("Book Ticket");
        bookButton.setBackground(GREEN);
        bookButton.setForeground(Color.WHITE);
        bookButton.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = 9; gbc.gridwidth = 2;
        add(bookButton, gbc);

        // Train name fetch karo
        fetchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String trainNum = trainNumberField.getText();
                TrainDAO trainDAO = new TrainDAO();
                String name = trainDAO.getTrainNameByNumber(trainNum);

                if (name != null) {
                    trainNameLabel.setText(name);
                } else {
                    trainNameLabel.setText("Not found");
                }
            }
        });

        // Booking karo
        bookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reservation res = new reservation();
                res.setPassengerName(nameField.getText());
                res.setTrainNumber(trainNumberField.getText());
                res.setTrainName(trainNameLabel.getText());
                res.setClassType((String) classBox.getSelectedItem());
                res.setJourneyDate(dateField.getText());
                res.setSourceStation(sourceField.getText());
                res.setDestinationStation(destField.getText());

                ReservationDAO dao = new ReservationDAO();
                int pnr = dao.bookReservation(res);

                if (pnr != -1) {
                    JOptionPane.showMessageDialog(null,
                        "Booking Confirmed!\nPNR: " + pnr +
                        "\nPassenger: " + res.getPassengerName() +
                        "\nTrain: " + res.getTrainName() +
                        "\nDate: " + res.getJourneyDate());
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Booking failed. Try again.");
                }
            }
        });

        setVisible(true);
    }
}