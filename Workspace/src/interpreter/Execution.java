package interpreter;

import interpreter.tree.ParseError;
import interpreter.tree.Parser;
import interpreter.tree.Program;
import workspace.Console;
import workspace.Workspace;

/**
 * This class parses and executes a workspace program in a separate thread than the GUI.
 * @author Brandon Oubre
 */
public class Execution implements Runnable {

	private boolean stop = false;
	private Thread thread = null;
	private Workspace workspace;
	
	/**
	 * Create a new execution.
	 */
	public Execution() {
		workspace = Workspace.getInstance();
	}
	
	/**
	 * Parse the program, then if the program can be run interpret it.
	 */
	@Override
	public void run() {
		stop = false;
		
		Console.getInstance().appendLine("<b>Parsing Program...</b>");
		//TODO: Parse
		Parser parser = new Parser(null,
				workspace.getPageNamed("Setup").getTopLevelBlocks(),
				workspace.getPageNamed("Main Loop").getTopLevelBlocks(),
				workspace.getPageNamed("Procedures").getTopLevelBlocks());
		Program program = parser.parse();
		if (program == null || !parser.getErrors().isEmpty()) {	
			Console.getInstance().appendLine("<span class=\"error\"><b>Parse could not complete because of " + parser.getErrors().size() + " errors.</b></span>");
			for (ParseError e : parser.getErrors()) {
				Console.getInstance().appendLine("<span class=\"error\">Parse Error: " + e.getMessage() + "</span>");
			}
			return; //Do not proceed with execution
		} else if (!parser.getWarnings().isEmpty()) {
			Console.getInstance().appendLine("<span class=\"warning\"><b>Parse completed with " + parser.getWarnings().size() + " warnings.</b></span>");
			for (ParseError e : parser.getWarnings()) {
				Console.getInstance().appendLine("<span class=\"warning\">Parse Warning: " + e.getMessage() + "</span>");
			}	
		}
		
		Console.getInstance().appendLine("<b>Beginning Execution...</b>");
		//TODO: Run Interpreter
		
		System.out.println(program.parseDump());
		
		Console.getInstance().appendLine("<b>Execution Complete.</b>");
	}
	
	/**
	 * Begin the parse/execution process by spawning off a new execution thread.
	 * Only one of these threads can be active at a time.
	 */
	public void execute() {
		if (isRunning()) {
			Console.getInstance().appendLine("<span class=\"error\">Error: Program already running. Please stop it before starting a new execution.</span>");
			return;
		}
		
		Console.getInstance().clear();
		thread = new Thread(this);
		thread.start();
	}
	
	/**
	 * Raise a flag that tells the thread to stop execution as soon as possible.
	 */
	public synchronized void stop() {
		if (!stop && isRunning()) {
			Console.getInstance().appendLine("<b>Halting Execution...</b>");
			stop = true;
		}
	}
	
	/**
	 * @return <tt>true</tt> if the program is currently parsing/running. (ie the thread is alive.)
	 */
	public synchronized boolean isRunning() {
		return thread != null && thread.isAlive();
	}
}
