package controller;

import interpreter.DummyBayouBot;
import interpreter.Execution;
import interpreter.tree.Program;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jssc.SerialPortList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import workspace.Console;
import workspace.SearchBar;
import workspace.SearchableContainer;
import workspace.Workspace;
import codeblocks.BlockConnectorShape;
import codeblocks.BlockGenus;
import codeblocks.BlockLinkChecker;
import codeblocks.CommandRule;
import codeblocks.SocketRule;

/**
 * 
 * There are three options in setting the language definition file.  It may be set
 * once and unchanged or it can be set multiple times.  You can set 
 * 
 * @author Ricarose Roque
 */
public class WorkspaceController {

    // XXX never read locally
//    private static final String SAVE_FORMAT_DTD_FILEPATH = "support/save_format.dtd";
    
    /*private static final String workingDirectory = ((System.getProperty("application.home") != null) ?
            System.getProperty("application.home") :
                System.getProperty("user.dir"));*/
                
    private static String LANG_DEF_FILEPATH;
    private static final String IMAGE_FOLDER = "support/images/";
    private static final String VERSION = "1.0";
    
    private static Element langDefRoot;
    
    //flags 
    private boolean isWorkspacePanelInitialized = false;
    /*
    *//** The single instance of the Workspace Controller*//*
    private static WorkspaceController wc = new WorkspaceController();*/
    
    private JFrame frame;
    protected JPanel workspacePanel;
    protected static Workspace workspace;
    protected SearchBar searchBar;
    private JLabel bayouBotStatus;
    private JFileChooser fileChooser;
    private JComboBox<String> serialPortComboBox;
    
    //flag to indicate if a new lang definition file has been set
    private boolean langDefDirty = true;
    
    //flag to indicate if a workspace has been loaded/initialized 
    private boolean workspaceLoaded = false;
    
    private Map<String, Image> loadedImages;
    
    private Execution execution = new Execution();
    
    /**
     * Constructs a WorkspaceController instance that manages the
     * interaction with the codeblocks.Workspace
     *
     */
    public WorkspaceController(){
        workspace = Workspace.getInstance();
    }
    
    
/*    *//**
     * Returns the single instance of this
     * @return the single instance of this
     *//*
    public static WorkspaceController getInstance(){
        return wc;
    }*/
    
    ////////////////////
    //  LANG DEF FILE //
    ////////////////////
    
    /**
     * Sets the file path for the language definition file, if the 
     * language definition file is located in 
     */
    public void setLangDefFilePath(String filePath){
        
        LANG_DEF_FILEPATH = filePath; //TODO do we really need to save the file path?
        
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document doc;
        try {
            builder = factory.newDocumentBuilder();
            
            String langDefLocation = /*workingDirectory +*/ LANG_DEF_FILEPATH;
            doc = builder.parse(new File(langDefLocation));
            
            langDefRoot = doc.getDocumentElement();
            
            //set the dirty flag for the language definition file 
            //to true now that a new file has been set
            langDefDirty = true;
            
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * Sets the contents of the Lang Def File to the specified 
     * String langDefContents
     * @param langDefContents String contains the specification of a language
     * definition file
     */
    public void setLangDefFileString(String langDefContents){
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document doc;
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(new InputSource(new StringReader(langDefContents)));
            langDefRoot = doc.getDocumentElement();
            
            //set the dirty flag for the language definition file 
            //to true now that a new file has been set
            langDefDirty = true;
            
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
    
    /**
     * Sets the Lang Def File to the specified File langDefFile.  
     * @param langDefFile File contains the specification of the a language 
     * definition file.
     */
    public void setLangDefFile(File langDefFile){
        //LANG_DEF_FILEPATH = langDefFile.getCanonicalPath();
        
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document doc;
        try {
            builder = factory.newDocumentBuilder();
          
            doc = builder.parse(langDefFile);
            
            langDefRoot = doc.getDocumentElement();
            
            //set the dirty flag for the language definition file 
            //to true now that a new file has been set
            langDefDirty = true;
            
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Loads all the block genuses, properties, and link rules of 
     * a language specified in the pre-defined language def file.
     * @param root Loads the language specified in the Element root
     */
    public void loadBlockLanguage(Element root){
        //load connector shapes
        //MUST load shapes before genuses in order to initialize connectors within
        //each block correctly
        BlockConnectorShape.loadBlockConnectorShapes(root);
        
        //load genuses
        BlockGenus.loadBlockGenera(root);
        
        
        //load rules
        BlockLinkChecker.addRule(new CommandRule());
        BlockLinkChecker.addRule(new SocketRule());
        
        //set the dirty flag for the language definition file 
        //to false now that the lang file has been loaded
        langDefDirty = false;
    }
    
    /**
     * Resets the current language within the active
     * Workspace.
     *
     */
    public void resetLanguage(){
        //clear shape mappings
        BlockConnectorShape.resetConnectorShapeMappings();
        //clear block genuses
        BlockGenus.resetAllGenuses();
        //clear all link rules
        BlockLinkChecker.reset();
    }
    
    
    ////////////////////////
    // SAVING AND LOADING //
    ////////////////////////
    
    /**
     * Returns the save string for the entire workspace.  This includes the block workspace, any 
     * custom factories, canvas view state and position, pages
     * @return the save string for the entire workspace.
     */
    public String getSaveString(){
        StringBuffer saveString = new StringBuffer();
        //append the save data
        saveString.append("<?xml version=\"1.0\" encoding=\"ISO-8859\"?>");
        saveString.append("\r\n");
        //dtd file path may not be correct...
        //saveString.append("<!DOCTYPE StarLogo-TNG SYSTEM \""+SAVE_FORMAT_DTD_FILEPATH+"\">");
        //append root node
        saveString.append("<CODEBLOCKS>");
        saveString.append(workspace.getSaveString());
        saveString.append("</CODEBLOCKS>");
        return saveString.toString();
    }
    
    /**
     * Loads a fresh workspace based on the default specifications in the language 
     * definition file.  The block canvas will have no live blocks.   
     */
    public void loadFreshWorkspace(){
        //need to just reset workspace (no need to reset language) unless
        //language was never loaded
        //reset only if workspace actually exists
        if(workspaceLoaded)
            resetWorkspace();
        
        if(langDefDirty)
            loadBlockLanguage(langDefRoot);
        
        workspace.loadWorkspaceFrom(null, langDefRoot);
        
        workspaceLoaded = true;
    }
    
    /**
     * Loads the programming project from the specified file path.  
     * This method assumes that a Language Definition File has already 
     * been specified for this programming project.
     * @param path String file path of the programming project to load
     */ 
    public void loadProjectFromPath(String path){
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document doc;
        try {
            builder = factory.newDocumentBuilder();
            
            doc = builder.parse(new File(path));
            
            Element projectRoot = doc.getDocumentElement();

            //load the canvas (or pages and page blocks if any) blocks from the save file
            //also load drawers, or any custom drawers from file.  if no custom drawers
            //are present in root, then the default set of drawers is loaded from 
            //langDefRoot
            workspace.loadWorkspaceFrom(projectRoot, langDefRoot);

            workspaceLoaded = true;
            
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Loads the programming project specified in the projectContents.   
     * This method assumes that a Language Definition File has already been 
     * specified for this programming project.
     * @param projectContents
     */
    public void loadProject(String projectContents){
        //need to reset workspace and language (only if new language has been set)
        
        //reset only if workspace actually exists
        if(workspaceLoaded)
            resetWorkspace();
        
        if(langDefDirty)
            loadBlockLanguage(langDefRoot);
        
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document doc;
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(new InputSource(new StringReader(projectContents)));
            Element root = doc.getDocumentElement();
            //load the canvas (or pages and page blocks if any) blocks from the save file
            //also load drawers, or any custom drawers from file.  if no custom drawers
            //are present in root, then the default set of drawers is loaded from 
            //langDefRoot
            workspace.loadWorkspaceFrom(root, langDefRoot);

            workspaceLoaded = true;
            
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        
    }
    
    /**
     * Loads the programming project specified in the projectContents String, 
     * which is associated with the language definition file contained in the 
     * specified langDefContents.  All the blocks contained in projectContents
     * must have an associated block genus defined in langDefContents.
     * 
     * If the langDefContents have any workspace settings such as pages or 
     * drawers and projectContents has workspace settings as well, the 
     * workspace settings within the projectContents will override the 
     * workspace settings in langDefContents.  
     * 
     * NOTE: The language definition contained in langDefContents does 
     * not replace the default language definition file set by: setLangDefFilePath() or 
     * setLangDefFile().
     * 
     * @param projectContents
     * @param langDefContents String XML that defines the language of
     * projectContents
     */
    public void loadProject(String projectContents, String langDefContents){
        
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document projectDoc;
        Document langDoc;
        try {
            builder = factory.newDocumentBuilder();
            projectDoc = builder.parse(new InputSource(new StringReader(projectContents)));
            Element projectRoot = projectDoc.getDocumentElement();
            langDoc = builder.parse(new InputSource(new StringReader(projectContents)));
            Element langRoot = langDoc.getDocumentElement();
            
            //need to reset workspace and language (if langDefContents != null)
            //reset only if workspace actually exists
            if(workspaceLoaded)
                resetWorkspace();
            
            if(langDefContents == null)
                loadBlockLanguage(langDefRoot);
            else
                loadBlockLanguage(langRoot);
            //TODO should verify that the roots of the two XML strings are valid
            workspace.loadWorkspaceFrom(projectRoot, langRoot);
            
            workspaceLoaded = true;
            
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
    
    /**
     * Resets the entire workspace.  This includes all blocks, pages, drawers, and trashed blocks.  
     * Also resets the undo/redo stack.  The language (i.e. genuses and shapes) is not reset.
     */
    public void resetWorkspace(){
        //clear all pages and their drawers
        //clear all drawers and their content
        //clear all block and renderable block instances
        workspace.reset();
        //clear action history
        //rum.reset();
        //clear runblock manager data
        //rbm.reset();
    }
    
    
    
    /**
     * This method creates and lays out the entire workspace panel with its 
     * different components.  Workspace and language data not loaded in 
     * this function.
     * Should be call only once at application startup.
     */
    private void initWorkspacePanel(){      
        workspacePanel = new JPanel();
        workspacePanel.setLayout(new BorderLayout());
        workspacePanel.add(workspace, BorderLayout.CENTER);
        workspacePanel.setPreferredSize(new Dimension(800, 400));
        
        isWorkspacePanelInitialized = true;
    }
    
    /**
     * Returns the JComponent of the entire workspace. 
     * @return the JComponent of the entire workspace. 
     */
    public JComponent getWorkspacePanel(){
        if(!isWorkspacePanelInitialized)
            initWorkspacePanel();
        return workspacePanel;
    }
    
    /**
     * Returns a SearchBar instance capable of searching for blocks 
     * within the BlockCanvas and block drawers
     */
    public JComponent getSearchBar(){
        SearchBar searchBar = new SearchBar("Search blocks", "Search for blocks in the drawers and workspace", workspace);
        for(SearchableContainer con : getAllSearchableContainers()){
            searchBar.addSearchableContainer(con);
        }
        
        return searchBar.getComponent();
    }
    
    /**
     * Returns an unmodifiable Iterable of SearchableContainers
     * @return an unmodifiable Iterable of SearchableContainers
     */
    public Iterable<SearchableContainer> getAllSearchableContainers(){
        return workspace.getAllSearchableContainers();
    }
    
    /////////////////////////////////////
    // TESTING CODEBLOCKS SEPARATELY //
    /////////////////////////////////////
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private void createAndShowGUI() {
        System.out.println("Creating GUI...");
        
        //Create and set up the window.
        frame = new JFrame("BayouBot Workspace v" + VERSION);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
        	@Override
        	public void windowClosing(WindowEvent e) {
        		exitRoutine(e.getComponent());
        	}
        });
        
        JPanel content = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        fileChooser = new JFileChooser();
        
        /*
         * Menu
         */
        JMenuBar menuBar = new JMenuBar();
        
        //File Menu
        JMenu menu = new JMenu("File");
        menuBar.add(menu);
        JMenuItem menuItem = new JMenuItem("Save");
        menuItem.addActionListener(ae -> {saveFile();});
        menu.add(menuItem);
        menuItem = new JMenuItem("Load");
        menuItem.addActionListener(ae -> {loadFile();});
        menu.add(menuItem);
        
        //Help Menu
        menu = new JMenu("Help");
        menuBar.add(menu);
        menuItem = new JMenuItem("About");
        menuItem.addActionListener(ae -> {
        	JOptionPane.showMessageDialog(frame,
        		    "Workspace version 1.0.\nThis workspace is based off of MIT's OpenBlocks and was developed by LSU's Robotics Research Lab.",
        		    "About BayouBot Programming Workspace",
        		    JOptionPane.PLAIN_MESSAGE);
        });
        menu.add(menuItem);
        
        frame.setJMenuBar(menuBar);
        
        /*
         * Top Pane
         */
        SearchBar searchBar = new SearchBar("Search blocks", "Search for blocks in the drawers and workspace", workspace);
        for(SearchableContainer con : getAllSearchableContainers()){
            searchBar.addSearchableContainer(con);
        }
        
        JButton executeBtn = new JButton(new ImageIcon(loadedImages.get("play")));
        executeBtn.setPreferredSize(new Dimension(32, 32));
        executeBtn.addActionListener(ae -> {startProgramExecution();});
        
        JButton checkBtn = new JButton(new ImageIcon(loadedImages.get("check")));
        checkBtn.setPreferredSize(new Dimension(32, 32));
        checkBtn.addActionListener(ae -> {checkProgram();});
        
        JButton stopExecBtn = new JButton(new ImageIcon(loadedImages.get("stop")));
        stopExecBtn.setPreferredSize(new Dimension(32, 32));
        stopExecBtn.addActionListener(ae -> {stopProgramExecution();});
        
        JPanel topPane = new JPanel();
        searchBar.getComponent().setPreferredSize(new Dimension(230, 23));
        topPane.add(searchBar.getComponent());
        //topPane.add(saveButton);
        topPane.add(checkBtn);
        topPane.add(executeBtn);
        topPane.add(stopExecBtn);
        
        /*
         * Arrange Everything
         */
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        content.add(topPane, gbc);
        
        gbc.gridy = 1;
        gbc.fill  = GridBagConstraints.BOTH;
        workspace.setMinimumSize(new Dimension(800, 400));
        content.add(getWorkspacePanel(), gbc);
        
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        content.add(Console.getInstance().getPanel(), gbc);
        
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        content.add(makeBayouBotConnectionPanel(), gbc);
        
        frame.setContentPane(content);
        frame.pack();
        frame.setVisible(true);
    }
    
    private JPanel makeBayouBotConnectionPanel() {
    	JPanel panel = new JPanel(new GridBagLayout());
    	
    	JButton refreshBtn = new JButton(new ImageIcon(loadedImages.get("refresh")));
        refreshBtn.setPreferredSize(new Dimension(16, 16));
        refreshBtn.addActionListener(ae -> {refreshSerialPortList();});
        
        serialPortComboBox = new JComboBox<>();
        refreshSerialPortList();
        
        bayouBotStatus = new JLabel("Disconnected.");
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 5, 5, 5);
        panel.add(new JLabel("BayouBot Port:"), gbc);
        
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 0, 5, 10);
        panel.add(serialPortComboBox, gbc);
               
        gbc.gridx = 2;
        panel.add(refreshBtn, gbc);
        
        gbc.gridx = 3;
        gbc.insets = new Insets(0, 0, 5, 0);
        panel.add(bayouBotStatus, gbc);
        
    	return panel;
    }
    
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() { 
                //TODO grab file path from args array
                
                LANG_DEF_FILEPATH = "support/lang_def.xml";
                
                //Create a new WorkspaceController 
                WorkspaceController wc = new WorkspaceController();
                
                wc.loadImages();
                wc.setLangDefFilePath(LANG_DEF_FILEPATH);
                wc.loadFreshWorkspace();
                wc.createAndShowGUI();
                
                wc.execution.setBayouBot(new DummyBayouBot());
            }
        });
    }
	
	public static void initWithLangDefFilePath(final String langDefFilePath) {
	
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() { 
                               
                //Create a new WorkspaceController 
                WorkspaceController wc = new WorkspaceController();
                
				wc.setLangDefFilePath(langDefFilePath);

                wc.loadFreshWorkspace();
                wc.createAndShowGUI();
            }
        });
	}
	
	/**
	 * Load images for the workspace controller.
	 */
	public void loadImages() {
		assert loadedImages == null : "Images already loaded.";
		loadedImages = new HashMap<>();
		try {
			Image img = ImageIO.read(new File(IMAGE_FOLDER + "Play.png"));
			img = img.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
			loadedImages.put("play", img);
			
			img = ImageIO.read(new File(IMAGE_FOLDER + "Stop.png"));
			img = img.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
			loadedImages.put("stop", img);
			
			img = ImageIO.read(new File(IMAGE_FOLDER + "Check.png"));
			img = img.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
			loadedImages.put("check", img);
			
			img = ImageIO.read(new File(IMAGE_FOLDER + "Refresh.jpg"));
			img = img.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
			loadedImages.put("refresh", img);
		} catch (IOException ex) {
			System.err.println("Failed to load image.");
			ex.printStackTrace();
			System.exit(1);
		}
	}
	
	/**
	 * Start a program execution on a new thread.
	 */
	private void startProgramExecution() {	
		execution.execute();
	}
	
	/**
	 * Stop an existing program execution.
	 */
	private void stopProgramExecution() {
		execution.stop();
	}
	
	/**
	 * Parse the current program with no intention of running it to perform checks.
	 */
	private void checkProgram() {
		if (execution.isRunning()) 
			return;
		
		Console.getInstance().clear();
		Program p = Execution.parse(workspace, null);
		System.out.println(p == null ? "No program returned." : p.parseDump());
	}
	
	/**
	 * Refresh the serial port drop down box.
	 */
	private void refreshSerialPortList() {
		try {
		serialPortComboBox.removeAllItems();
		for (String port : SerialPortList.getPortNames())
			serialPortComboBox.addItem(port);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Called when the program is closed.
	 * @param c The program frame.
	 */
	private void exitRoutine(Component c) {
		Console.getInstance().appendLine("<b>Shutting down workspace and ending programs...</b>");
		System.out.println("Shutting Down...");
		stopProgramExecution();
		c.setVisible(false);
		System.exit(0);
	}
	
	/**
	 * This method is called when a save button is clicked.
	 */
	private void saveFile() {
		int retVal = fileChooser.showSaveDialog(frame);
		if (retVal == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();			
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			} 
			if (file.isFile()) {
				try (FileWriter writer = new FileWriter(file, false)) {
					writer.write(getSaveString());
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * This method is called when a load button is clicked.
	 */
	private void loadFile() {
		int retVal = fileChooser.showOpenDialog(frame);
		if (retVal == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();			
			if (file.exists() && file.isFile()) {
				try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
					StringBuilder sb = new StringBuilder();
					reader.lines().forEach(line -> sb.append(line));
					loadProject(sb.toString());
				} catch (FileNotFoundException ex) {
					Console.getInstance().appendLine("<span class=\"error\"><b>Could not load file because it could not be found.</b></span>");
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
}
