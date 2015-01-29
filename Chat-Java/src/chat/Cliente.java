package chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicSplitPaneUI.KeyboardDownRightHandler;

public class Cliente extends JFrame implements ActionListener {

	JPanel painel;
	JButton send;
	JTextArea area;
	JLabel label1, label2;
	JTextField input;
	JScrollPane scroll;

	public Cliente() {
		super("Chat");
		painel = new JPanel();
		send = new JButton("Enviar");

		area = new JTextArea(18, 39);
		area.setWrapStyleWord(true);
		area.setLineWrap(true);
		area.setEditable(false);
		area.setFocusable(false);

		scroll = new JScrollPane();

		scroll.setViewportView(area);

		input = new JTextField(30);

		send.addActionListener(this);
		

		painel.add(scroll);
		// painel.add(area);
		painel.add(input);
		painel.add(send);

		this.add(painel);
		this.pack();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == send) {
			// Date data = new Date();
			// String d = data.getHours() + ":" + data.getMinutes() + ":"
			// + data.getSeconds();

			area.setText(area.getText() + "\n" + input.getText() + "\n");// +d+"\n");

			input.setText("");
			input.requestFocus();
		}
	}
}
