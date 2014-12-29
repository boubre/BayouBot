package workspace;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;

/**
 * A console window at the bottom of the workspace for program output.
 * @author Brandon Oubre
 */
public class Console {
	private static Console instance = new Console();
	private JTextPane textPane;
	private JPanel panel;
	private StringBuilder content;
	
	/**
	 * Used to retrieve a singleton instance of this class.
	 * @return The singleton instance of this class.
	 */
	public static Console getInstance() {
		return instance;
	}
	
	/**
	 * Create a new console.
	 */
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
	
	/**
	 * @return The outer JPanel of the console. (Use to add the console to the workspace.)
	 */
	public JPanel getPanel() {
		return panel;
	}
	
	/**
	 * Clear the console text.
	 * (This method will correctly invoke later on the event dispatcher thread.)
	 */
	public void clear() {
		SwingUtilities.invokeLater(() -> { invokeClear(); });
	}
	
	/**
	 * Implementation of clear(). Should only be called from the event dispatcher thread.
	 */
	private void invokeClear() {
		content.setLength(0);
		content.append("<html><head><style type=\"text/css\"> .error { color: red; } </style></head><body>");
		textPane.setText(content.toString());
	}
	
	/**
	 * Append the given html string to the console.
	 * (This method will correctly invoke later on the event dispatcher thread.)
	 * @param s The html string to append.
	 */
	public void append(String s) {
		SwingUtilities.invokeLater(() -> { invokeAppend(s); });
	}
	
	/**
	 * Implementation of append(s). Should only be called from the event dispatcher thread.
	 * @param s The html string to append.
	 */
	private void invokeAppend(String s) {
		content.append(s);
		textPane.setText(content.toString());
	}
	
	/**
	 * Append the given html string to the console, followed by a new line (html br tag).
	 * (This method will correctly invoke later on the event dispatcher thread.)
	 * @param s The html string to append.
	 */
	public void appendLine(String s) {
		SwingUtilities.invokeLater(() -> { invokeAppendLine(s); });
	}
	
	/**
	 * Implementation of appendLine(s). Should only be called from the event dispatcher thread.
	 * @param s The html string to append.
	 */
	private void invokeAppendLine(String s) {
		content.append(s);
		content.append("<br />");
		textPane.setText(content.toString());
	}
}
