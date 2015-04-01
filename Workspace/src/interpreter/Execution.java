package interpreter;

import interpreter.tree.ParseError;
import interpreter.tree.Parser;
import interpreter.tree.Program;

import java.util.function.BooleanSupplier;

import workspace.Console;
import workspace.Workspace;
import bayoubot.core.BayouBot;


/**
 * This class parses and executes a workspace program in a separate thread than the GUI.
 * @author Brandon Oubre
 */
public class Execution implements Runnable {

	private boolean stop = false;
	private Thread thread = null;
	private Workspace workspace;
	private BayouBot bayouBot = null;
	
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
		
		Program program = parse(workspace, bayouBot);
		if (program == null) {
			return; //Do not proceed with execution
		}
		
		if (bayouBot == null) {
			Console.getInstance().appendLine("<span class=\"error\"><b>No BayouBot to run the program for.</b></span>");
			return; //Do not proceed with execution
		}
		Console.getInstance().appendLine("<b>Beginning Execution...</b>");
		
		interpret(program);
		
		Console.getInstance().appendLine("<b>Execution Complete.</b>");
	}
	
	/**
	 * Parse the workspace and return the resulting program.
	 * @param workspace The workspace to parse.
	 * @param BayouBot The BayouBot this program is for.
	 * @return The parsed program, or <tt>null</tt> if there are errors or the program is invalid.
	 */
	public static Program parse(Workspace workspace, BayouBot bayouBot) {
		Console.getInstance().appendLine("<b>Parsing Program...</b>");
		
		Parser parser = new Parser(bayouBot,
				workspace.getPageNamed("Setup").getTopLevelBlocks(),
				workspace.getPageNamed("Main Loop").getTopLevelBlocks(),
				workspace.getPageNamed("Procedures").getTopLevelBlocks());
		
		Program program = parser.parse();
		
		//Check errors/warnings
		if (program == null || !parser.getErrors().isEmpty()) {	
			Console.getInstance().appendLine("<span class=\"error\"><b>Parse could not complete because of " + parser.getErrors().size() + " errors.</b></span>");
			for (ParseError e : parser.getErrors()) {
				Console.getInstance().appendLine("<span class=\"error\">Parse Error: " + e.getMessage() + "</span>");
			}
			return null; //Invalid or erroneous program
		} else if (!parser.getWarnings().isEmpty()) {
			Console.getInstance().appendLine("<span class=\"warning\"><b>Parse completed with " + parser.getWarnings().size() + " warnings.</b></span>");
			for (ParseError e : parser.getWarnings()) {
				Console.getInstance().appendLine("<span class=\"warning\">Parse Warning: " + e.getMessage() + "</span>");
			}	
		}
		
		return program;
	}
	
	/**
	 * Set the BayouBot for this execution to send commands to.
	 * @param bb The BayouBot to send commands to.
	 */
	public void setBayouBot(BayouBot bb) {
		this.bayouBot = bb;
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
	
	/**
	 * Interpret a program.
	 * @param p The program to interpret.
	 */
	private void interpret(Program p) {
		try {
			p.setup.execute(testStop);
			while (!stop) {
				p.mainLoop.execute(testStop);
			}
			bayouBot.stop();
		} catch (ProgramExecutionException e) {
			Console.getInstance().appendLine("<span class=\"error\">Error: Program encountered an unexpected error.</span>");
			e.printStackTrace();
		}	
	}
	
	/**
	 * A method to be passed to procedures and commands that will indicate if they should immediately stop and return.
	 * @return <tt>true</tt> if the program should come to a halt.
	 */
	private BooleanSupplier testStop = () -> {
		return stop;
	};
}
