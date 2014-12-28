package workspace;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;

/**
 * A console window at the bottom of the workspace for program output.
 * @author Brandon Oubre
 */
public class Console {
	private static Console instance = null;
	private JTextPane textPane;
	private JPanel panel;
	private StringBuilder content;
	
	public static Console getInstance() {
		if (instance == null)
			instance = new Console();
		return instance;
	}
	
	private Console() {
		content = new StringBuilder();
		
		textPane = new JTextPane();
		textPane.setContentType("text/html");
		
		clear();
		
		JScrollPane scroll  = new JScrollPane(textPane);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		panel = new JPanel(new GridLayout(1, 1));
		panel.setPreferredSize(new Dimension(800, 200));
		panel.add(scroll);
		panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	public void clear() {
		content.setLength(0);
		content.append("<html><head></head><body>");
		textPane.setText(content.toString());
	}
	
	public void append(String s) {
		content.append(s);
		textPane.setText(content.toString());
	}
	
	public void appendLine(String s) {
		content.append(s);
		content.append("<br />");
		textPane.setText(content.toString());
	}
}
