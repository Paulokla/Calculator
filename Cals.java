package gui;

import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Cals extends JFrame implements ActionListener{

	Font font;
	Color bgColor, fgColor, panelColor;
	JTextField textField;
	JButton btn;
	JRadioButton off;
	String operators = "*/-+";
	double num1 = 0, num2 = 0, res;

	Cals() {

		setLayout(new FlowLayout());

		font = new Font("Verdana", Font.BOLD, 20);
		bgColor = Color.black;
		fgColor = Color.yellow;
		panelColor = Color.black;

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6,3));
		add(panel,BorderLayout.CENTER);
		
		textField = new JTextField("0", 10);
		textField.setFont(font);
		panel.add(textField);
		

		JRadioButton on = new JRadioButton("ON");
		on.addActionListener(this);
		on.setSelected(true);

		off = new JRadioButton("OFF");
		off.addActionListener(this);

		ButtonGroup bg = new ButtonGroup();
		bg.add(off);
		bg.add(on);

		panel.add(off);
		panel.add(on);

//		Buttons Panel
		for (int i = 0; i <= 9; i++) {
			JButton btn = createButton(i + "");
			panel.add(btn);
		}
		panel.add(createButton("."));

		char[] ops = operators.toCharArray();
		for (char op : ops) {
			panel.add(createButton(op + ""));
		}

		add(panel);

		add(panel);
		addWindowListener(new BackToPlayGames());
		setTitle("Calculator GUI");
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);;
		pack();
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		new Cals();
	}

	private JButton createButton(String text) {
		btn = new JButton(text);
		btn.setBackground(bgColor);
		btn.setForeground(fgColor);
		btn.addActionListener(this);
		btn.setFocusable(false);

		return btn;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();

		
		if (action.equalsIgnoreCase("off")) {

			textField.setEnabled(false);
			textField.setText("");
			btn.setEnabled(false);
			textField.setEditable(false);
		} else if (action.equalsIgnoreCase("on")) {
			
			btn.setEnabled(true);
			textField.setEnabled(true);
			textField.setText("0");
		} else {

			if (off.isSelected()) {
				return;
			} else if (action.equals(".")) {
				if (textField.getText().contains("."))
					return;
				else
					textField.setText(textField.getText() + action);
			} else if (textField.getText().equals("0")) {
				textField.setText(action);
			} else {

				if (action.contains(operators)) {
					if (num1 == 0)
						num1 = Double.parseDouble(textField.getText());
					else if (num2 == 0)
						num2 = Double.parseDouble(textField.getText());
					System.out.format("num1: %s, num2: %s\n", num1, num2);
					
					if (operators.equals("+"))
						res = Double.parseDouble(textField.getText());
						res = num1 + num2;
						
					
					textField.setText(num1 + operators + num2 + '=' + res);
				} else

					textField.setText(textField.getText() + action);
			}
		}

	}
}
