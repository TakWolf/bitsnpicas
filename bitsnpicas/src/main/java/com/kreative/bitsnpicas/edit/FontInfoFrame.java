package com.kreative.bitsnpicas.edit;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.kreative.bitsnpicas.Font;

public class FontInfoFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private final Font<?> font;
	private final FontInfoPanel panel;
	private final SaveManager sm;
	
	public FontInfoFrame(Font<?> font, SaveManager sm) {
		super("Font Info");
		this.font = font;
		this.panel = new FontInfoPanel();
		this.panel.readFrom(this.font);
		this.sm = sm;
		
		JButton cancelButton = new JButton("Cancel");
		JButton okButton = new JButton("OK");
		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(cancelButton);
		buttonPanel.add(okButton);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(this.panel, BorderLayout.CENTER);
		mainPanel.add(buttonPanel, BorderLayout.PAGE_END);
		mainPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
		setContentPane(mainPanel);
		SwingUtils.setDefaultButton(getRootPane(), okButton);
		SwingUtils.setCancelButton(getRootPane(), cancelButton);
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FontInfoFrame.this.dispose();
			}
		});
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FontInfoFrame.this.panel.writeTo(FontInfoFrame.this.font);
				FontInfoFrame.this.dispose();
				FontInfoFrame.this.sm.setChanged();
			}
		});
		
		setSize(700, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
