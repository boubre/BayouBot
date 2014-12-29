package interpreter;

import interpreter.tree.Parser;
import workspace.Console;
import workspace.Workspace;

public class Execution implements Runnable {

	private boolean stop = false;
	private Thread thread = null;
	private Workspace workspace;
	
	public Execution() {
		workspace = Workspace.getInstance();
	}
	
	@Override
	public void run() {
		stop = false;
		
		Console.getInstance().appendLine("<b>Parsing Program...</b>");
		//TODO: Parse
		Parser parser = new Parser(workspace.getTopLevelBlocks());
		
		Console.getInstance().appendLine("<b>Beginning Execution...</b>");
		//TODO: Run Interpreter
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Console.getInstance().appendLine("<b>Execution Complete.</b>");
	}
	
	public void execute() {
		if (isRunning()) {
			Console.getInstance().appendLine("<span class=\"error\">Error: Program already running. Please stop it before starting a new execution.</span>");
			return;
		}
		
		Console.getInstance().clear();
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		Console.getInstance().appendLine("<b>Halting Execution...</b>");
		stop = true;
	}
	
	public synchronized boolean isRunning() {
		return thread != null && thread.isAlive();
	}
}
