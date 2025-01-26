import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NetflixMovieManagementApplication {

    public static void main(String[] args) {
      
        JFrame frame = new JFrame("Netflix Movie Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700);
        frame.getContentPane().setBackground(Color.BLACK);

        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Netflix Movie Management", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.RED);
        frame.add(titleLabel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(15, 4, 10, 10));
        inputPanel.setBackground(Color.BLACK);

        JLabel lblTitle = createLabel("Title:");
        JTextField txtTitle = createTextField();

        JLabel lblGenre = createLabel("Genre:");
        JTextField txtGenre = createTextField();

        JLabel lblYear = createLabel("Release Year:");
        JTextField txtYear = createTextField();

        JLabel lblLanguage = createLabel("Language:");
        JTextField txtLanguage = createTextField();

        JLabel lblDirector = createLabel("Director:");
        JTextField txtDirector = createTextField();

        JLabel lblMainActor = createLabel("Main Actor:");
        JTextField txtMainActor = createTextField();

        JLabel lblMainActress = createLabel("Main Actress:");
        JTextField txtMainActress = createTextField();

        JLabel lblDuration = createLabel("Duration (min):");
        JTextField txtDuration = createTextField();

        JLabel lblRating = createLabel("IMDb Rating:");
        JTextField txtRating = createTextField();

        JLabel lblAgeRestriction = createLabel("Viewer Age Restriction:");
        JTextField txtAgeRestriction = createTextField();

        JLabel lblContentType = createLabel("Content Type:");
        String[] contentTypes = {"Movie", "Series"};
        JComboBox<String> cmbContentType = new JComboBox<>(contentTypes);

        JLabel lblSeasons = createLabel("Number of Seasons:");
        JTextField txtSeasons = createTextField();
        JLabel lblEpisodes = createLabel("Number of Episodes:");
        JTextField txtEpisodes = createTextField();

        inputPanel.add(lblTitle); inputPanel.add(txtTitle);
        inputPanel.add(lblGenre); inputPanel.add(txtGenre);
        inputPanel.add(lblYear); inputPanel.add(txtYear);
        inputPanel.add(lblLanguage); inputPanel.add(txtLanguage);
        inputPanel.add(lblDirector); inputPanel.add(txtDirector);
        inputPanel.add(lblMainActor); inputPanel.add(txtMainActor);
        inputPanel.add(lblMainActress); inputPanel.add(txtMainActress);
        inputPanel.add(lblDuration); inputPanel.add(txtDuration);
        inputPanel.add(lblRating); inputPanel.add(txtRating);
        inputPanel.add(lblAgeRestriction); inputPanel.add(txtAgeRestriction);
        inputPanel.add(lblContentType); inputPanel.add(cmbContentType);
        inputPanel.add(lblSeasons); inputPanel.add(txtSeasons);
        inputPanel.add(lblEpisodes); inputPanel.add(txtEpisodes);
        
        lblSeasons.setVisible(false);
        txtSeasons.setVisible(false);
        lblEpisodes.setVisible(false);
        txtEpisodes.setVisible(false);

        cmbContentType.addActionListener(e -> {
            boolean isSeries = cmbContentType.getSelectedItem().equals("Series");
            lblSeasons.setVisible(isSeries);
            txtSeasons.setVisible(isSeries);
            lblEpisodes.setVisible(isSeries);
            txtEpisodes.setVisible(isSeries);
        });

        frame.add(inputPanel, BorderLayout.CENTER);

    
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        JButton btnSubmit = new JButton("Submit");
        JButton btnClear = new JButton("Clear");

        styleButton(btnSubmit);
        styleButton(btnClear);

        buttonPanel.add(btnSubmit);
        buttonPanel.add(btnClear);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea.setBackground(Color.DARK_GRAY);
        textArea.setForeground(Color.WHITE);
        frame.add(new JScrollPane(textArea), BorderLayout.EAST);

        btnSubmit.addActionListener(e -> {
            try {
                validateInput(txtTitle.getText(), "Title");
                validateInput(txtGenre.getText(), "Genre");
                validateNumber(txtYear.getText(), "Release Year");
                validateNumber(txtDuration.getText(), "Duration");
                validateRating(txtRating.getText());
                validatePositiveNumber(txtAgeRestriction.getText(), "Viewer Age Restriction");

                StringBuilder result = new StringBuilder("Movie Details:\n");
                result.append("Title: ").append(txtTitle.getText()).append("\n");
                result.append("Genre: ").append(txtGenre.getText()).append("\n");
                result.append("Release Year: ").append(txtYear.getText()).append("\n");
                result.append("Language: ").append(txtLanguage.getText()).append("\n");
                result.append("Director: ").append(txtDirector.getText()).append("\n");
                result.append("Main Actor: ").append(txtMainActor.getText()).append("\n");
                result.append("Main Actress: ").append(txtMainActress.getText()).append("\n");
                result.append("Duration: ").append(txtDuration.getText()).append(" minutes\n");
                result.append("IMDb Rating: ").append(txtRating.getText()).append("\n");
                result.append("Viewer Restriction: ").append(txtAgeRestriction.getText()).append("+\n");

                if (cmbContentType.getSelectedItem().equals("Series")) {
                    result.append("Number of Seasons: ").append(txtSeasons.getText()).append("\n");
                    result.append("Number of Episodes: ").append(txtEpisodes.getText()).append("\n");
                }

                textArea.setText(result.toString());
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnClear.addActionListener(e -> {
            for (Component c : inputPanel.getComponents()) {
                if (c instanceof JTextField) {
                    ((JTextField) c).setText("");
                }
            }
            textArea.setText("");
        });

        frame.setVisible(true);
    }

    private static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        return label;
    }

    private static JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setBackground(Color.DARK_GRAY);
        textField.setForeground(Color.WHITE);
        return textField;
    }

    private static void styleButton(JButton button) {
        button.setBackground(Color.RED);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
    }

    private static void validateInput(String input, String fieldName) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty.");
        }
    }

    private static void validateNumber(String input, String fieldName) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " must be a valid number.");
        }
    }

    private static void validateRating(String input) {
        try {
            double rating = Double.parseDouble(input);
            if (rating < 0.0 || rating > 10.0) {
                throw new IllegalArgumentException("IMDb Rating must be between 0.0 and 10.0.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("IMDb Rating must be a valid number between 0.0 and 10.0.");
        }
    }

    private static void validatePositiveNumber(String input, String fieldName) {
        try {
            int value = Integer.parseInt(input);
            if (value < 0) {
                throw new IllegalArgumentException(fieldName + " must be a positive number.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " must be a valid number.");
        }
    }
}
