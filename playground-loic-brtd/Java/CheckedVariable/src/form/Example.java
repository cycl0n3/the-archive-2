package form;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class Example {

    public static void main(String[] args) {
        var f = new JFrame();
        var p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
        p.setBorder(new EmptyBorder(12, 24, 12, 24));

        var pseudo = new CheckedEntry<>("Pseudo", Example::checkPseudo);
        var email = new CheckedEntry<>("Email", Example::checkEmail);
        var start = new CheckedEntry<>("Start", Example::checkStart);
        var end = new CheckedEntry<Date>("End", entry -> checkEnd(start, entry));

        var entries = List.of(pseudo, email, start, end);

        var validate = new JButton("Validate");
        validate.addActionListener(e -> entries.forEach(entry -> System.out.println(entry.getValue())));

        entries.forEach(p::add);
        p.add(validate);

        f.setContentPane(p);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    private static Date parseDate(String text) {
        var dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            return dateFormat.parse(text);
        } catch (ParseException e) {
            return null;
        }

    }

    private static Date checkDate(CheckedEntry<Date> entry) {
        // return type : FieldValue<Date>
        Date date;
        if (entry.getText().isBlank() || (date = parseDate(entry.getText())) == null) {
            entry.setErrorState("Invalid date");
            return null; // return FieldValue.invalid("Invalid date");
        }
        entry.setSuccessState();
        return date; // return FieldValue.valid(date);
    }

    private static Date checkStart(CheckedEntry<Date> entry) {
        return checkDate(entry);
    }

    private static Date checkEnd(CheckedEntry<Date> start, CheckedEntry<Date> end) {
        var endDate = checkDate(end);
        if (endDate == null) {
            end.setErrorState("Invalid date");
            return null;
        }
        var startDate = checkDate(start);
        if (startDate == null) {
            return endDate;
        }
        if (endDate.before(startDate)) {
            end.setErrorState("End should be after start");
        }
        return endDate;
    }

    private static String checkEmail(CheckedEntry<String> entry) {
        if (entry.getText().isBlank()) {
            entry.setErrorState("Email is required");
        } else if (!entry.getText().contains("@")) {
            entry.setErrorState("Invalid email address");
        } else {
            entry.setSuccessState();
            return entry.getText();
        }
        return null;
    }

    private static String checkPseudo(CheckedEntry<String> entry) {
        if (entry.getText().isBlank()) {
            entry.setErrorState("Pseudo is required");
        } else {
            entry.setSuccessState();
            return entry.getText();
        }
        return null;
    }
}
