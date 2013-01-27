package com.gamelion.assetbite.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NameChangeWindow extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8927754120833837518L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JButton okButton;
	private JButton cancelButton;
	private JLabel lblNewJgoodiesTitle;

	/**
	 * Create the dialog.
	 */
	public NameChangeWindow() {
		init();
	}
	
	private final void init() {
		setUndecorated(true);
		setBounds(100, 100, 258, 107);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			textField = new JTextField();
			textField.setColumns(10);
		}
		{
			okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			okButton.setHorizontalAlignment(SwingConstants.LEFT);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					NameChangeWindow.this.dispose();
				}
			});
			cancelButton.setHorizontalAlignment(SwingConstants.RIGHT);
			cancelButton.setActionCommand("Cancel");
		}
		
		lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("title");
		lblNewJgoodiesTitle.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addComponent(okButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cancelButton)
							.addGap(57))
						.addComponent(lblNewJgoodiesTitle, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewJgoodiesTitle)
					.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(okButton)
						.addComponent(cancelButton))
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	
	
	public NameChangeWindow(Frame owner) {
		super(owner);
		init();
	}

	public void setText(String text) {
		textField.setText(text);
	}
	
	public String getText() {
		return textField.getText();
	}
	
	public void setTitle(String text) {
		lblNewJgoodiesTitle.setText(text);
	}
	public JButton getOkButton() {
		return okButton;
	}
	public JTextField getTextField() {
		return textField;
	}
}
