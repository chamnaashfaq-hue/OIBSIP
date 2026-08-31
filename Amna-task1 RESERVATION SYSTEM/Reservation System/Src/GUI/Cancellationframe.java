package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import DAO.ReservationDAO;
import Model.reservation;

public class Cancellationframe extends JFrame {

    private JTextField pnrField;
    private JTextArea detailsArea;
    private int currentPnr = -1;

    private final Color GREEN = new Color(0, 100, 0);
    private final Color CREAM = new Color(255, 253, 240);

    public Cancellationframe() {
        setTitle("Cancel Ticket");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(CREAM);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Cancellation Form");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(GREEN);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(titleLabel, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Enter PNR:"), gbc);
        pnrField = new JTextField(15);
        gbc.gridx = 1;
        add(pnrField, gbc);

        JButton fetchButton = new JButton("Fetch Booking");
        fetchButton.setBackground(GREEN);
        fetchButton.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        add(fetchButton, gbc);

        detailsArea = new JTextArea(8, 30);
        detailsArea.setEditable(false);
        gbc.gridy = 3;
        add(new JScrollPane(detailsArea), gbc);

        JButton cancelButton = new JButton("Cancel Booking");
        cancelButton.setBackground(new Color(178, 34, 34));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridy = 4;
        add(cancelButton, gbc);

        // PNR se booking dhundo
        fetchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int pnr = Integer.parseInt(pnrField.getText());
                    ReservationDAO dao = new ReservationDAO();
                    reservation res = dao.getReservationByPnr(pnr);

                    if (res != null) {
                        currentPnr = pnr;
                        detailsArea.setText(
                            "PNR: " + res.getPnr() +
                            "\nPassenger: " + res.getPassengerName() +
                            "\nTrain: " + res.getTrainName() +
                            "\nClass: " + res.getClassType() +
                            "\nDate: " + res.getJourneyDate() +
                            "\nFrom: " + res.getSourceStation() +
                            "\nTo: " + res.getDestinationStation()
                        );
                    } else {
                        detailsArea.setText("No booking found with this PNR.");
                        currentPnr = -1;
                    }
                } catch (NumberFormatException ex) {
                    detailsArea.setText("Please enter a valid PNR number.");
                }
            }
        });

        // Cancel karo (confirmation ke saath)
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentPnr == -1) {
                    JOptionPane.showMessageDialog(null, "Please fetch a valid booking first.");
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to cancel this booking?",
                    "Confirm Cancellation", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    ReservationDAO dao = new ReservationDAO();
                    boolean success = dao.cancelReservation(currentPnr);

                    if (success) {
                        JOptionPane.showMessageDialog(null, "Booking cancelled successfully.");
                        detailsArea.setText("");
                        pnrField.setText("");
                        currentPnr = -1;
                    } else {
                        JOptionPane.showMessageDialog(null, "Cancellation failed.");
                    }
                }
            }
        });

        setVisible(true);
    }
}