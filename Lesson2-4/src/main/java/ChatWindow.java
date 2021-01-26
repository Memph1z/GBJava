import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ChatWindow extends JFrame{
    public ChatWindow() {
        setTitle("HotSous");
        setBounds(new Rectangle(50, 50, 500, 1000));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel center = new JPanel();
        JPanel bottom = new JPanel();

        center.setLayout(new GridLayout());
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setAutoscrolls(true);
        textArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        center.add(scrollPane, BorderLayout.CENTER);

        bottom.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1.0f;
        JTextField textField = new JTextField(34);
        bottom.add(textField, c);
        JButton send = new JButton();
        send.setText("Send");
        bottom.add(send);

        ChatListener cl = new ChatListener(textArea, textField);
        send.addActionListener(cl);
        textField.addActionListener(cl);

        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
        
        setVisible(true);
        textField.requestFocusInWindow();
    }
}
