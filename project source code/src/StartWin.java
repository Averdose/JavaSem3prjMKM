import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.UIManager;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import java.awt.Rectangle;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.DropMode;
import java.awt.Component;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.Insets;
import java.awt.Point;
import java.awt.SystemColor;
import javax.swing.JComboBox;

public class StartWin {
	
	private JFrame frmMicromerge;
	private JTextField tfOperationsPreviewWorkPath;
	private int j =0;										//j is the number of selected directories
	private int numberofdirs =0;
	private int mergeMode =0;
	private int shadeMode =0;
	private int BWMode = 0;
	private String baseString = "image";
	private int counter =1;
	private int counterStep = 1;
	private int saveCounter = 1;
	private int saveStep = 1;								//seting up some variables that are modified by the program
	private JButton selectedButton = new JButton();
	private int direction = 8;
	private JButton buttonToSave = new JButton();
	private String type = "jpg";
	private JTextField tfMergedImagesWorkNameCustom;
	private JTextField tfMergedImagesWorkCounterStart;
	private JTextField tfMergedImagesWorkCounterIncrease;
	private boolean viewImagesAfterMerge = true;
	private int bppMode = 24;
	private String lastOpenDirectory = null;
	private String lastSaveDirectory = null;
	private JLabel lblbtnMergedImagesWorkSave;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartWin window = new StartWin();
					window.frmMicromerge.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StartWin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		
		
		final String[] EXTENSIONS = new String[]{
		      "tif","tiff", "TIFF", "TIF", "png", "bmp","jpeg","JPEG","jpg","JPG","BMP", "PNG" // all the formats accepted by the filter
		    };

		final FilenameFilter IMAGE_FILTER = new FilenameFilter() {

	        @Override
	        public boolean accept(final File dir, final String name) {							//creating filter for file choosing
	            for (final String ext : EXTENSIONS) {
	                if (name.endsWith("." + ext)) {
	                    return (true);
	                }
	            }
	            return (false);
	        }
	    };
		
		final List<String> listToMerge = new ArrayList<String>();
		final List<JButton> listImgs = new ArrayList<JButton>();
		final List<JTextField> listDirs = new ArrayList<JTextField>();							//creating containers used to store operating data
		final List<String> listMerged = new ArrayList<String>();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double widthScreen = screenSize.getWidth();
		double heightScreen = screenSize.getHeight();
		int xScreen;
		int yScreen;
		if (widthScreen-1366 <= 0)
		{
			xScreen = 0;
		}
		else 
		{
			xScreen = (int) ((widthScreen-1366)/2);
		}
		if (heightScreen-700 <= 0)
		{
			yScreen = 0;
		}
		else 
		{
			yScreen = (int) ((heightScreen-700)/2);
		}
		frmMicromerge = new JFrame();
		frmMicromerge.setResizable(false);
		frmMicromerge.setIconImage(Toolkit.getDefaultToolkit().getImage(StartWin.class.getResource("/resources/mmlogo.jpg")));
		frmMicromerge.setTitle("MicroMerge");
		frmMicromerge.setBounds(xScreen, yScreen, 1366, 700);
		frmMicromerge.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMicromerge.getContentPane().setLayout(null);
		
		final JPanel plMenu = new JPanel();
		plMenu.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 153, 0)));
		plMenu.setBounds(0, 20, 1366, 40);
		frmMicromerge.getContentPane().add(plMenu);
		plMenu.setBackground(Color.WHITE);
		plMenu.setLayout(null);
		
		final JButton btnOperationsOperations = new JButton("OPERATIONS");
		btnOperationsOperations.setBackground(Color.WHITE);
		btnOperationsOperations.setBorder(null);
		btnOperationsOperations.setContentAreaFilled(false);
		btnOperationsOperations.setFocusPainted(false);
		btnOperationsOperations.setFont(new Font("STHeiti", Font.PLAIN, 17));
		btnOperationsOperations.setForeground(Color.WHITE);
		btnOperationsOperations.setBounds(0, 0, 683, 38);
		plMenu.add(btnOperationsOperations);
		
		final JButton btnOperationsMergedImages = new JButton("MERGED IMAGES");
		btnOperationsMergedImages.setBorder(null);
		btnOperationsMergedImages.setBackground(Color.WHITE);
		btnOperationsMergedImages.setContentAreaFilled(false);
		btnOperationsMergedImages.setFocusPainted(false);
		btnOperationsMergedImages.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnOperationsMergedImages.setMargin(new Insets(0, 0, 0, 0));
		btnOperationsMergedImages.setHorizontalTextPosition(SwingConstants.CENTER);
		btnOperationsMergedImages.setFont(new Font("STHeiti", Font.PLAIN, 17));
		btnOperationsMergedImages.setForeground(new Color(0, 0, 0));
		btnOperationsMergedImages.setBounds(683, 0, 683, 38);
		plMenu.add(btnOperationsMergedImages);
		
		final JLabel lblbtnOperationsMergedImages = new JLabel("");
		lblbtnOperationsMergedImages.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnMergedImages.jpg")));
		lblbtnOperationsMergedImages.setBounds(683, 0, 683, 38);
		plMenu.add(lblbtnOperationsMergedImages);
		
		final JLabel lblbtnOperationsOperations = new JLabel("");
		lblbtnOperationsOperations.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnOperationsA.jpg")));
		lblbtnOperationsOperations.setBounds(0, 0, 683, 38);
		plMenu.add(lblbtnOperationsOperations);
		
		ButtonGroup mMode = new ButtonGroup();
		
		ButtonGroup shadingMode = new ButtonGroup();
		
		ButtonGroup shadingDirections = new ButtonGroup();

		ButtonGroup bowMode = new ButtonGroup();
		
		ButtonGroup nameMode = new ButtonGroup();
		
		ButtonGroup saveBpp = new ButtonGroup();
		
		ButtonGroup saveAs = new ButtonGroup();
				
		final JPanel plMain = new JPanel();
		plMain.setBounds(0, 60, 1366, 678);
		frmMicromerge.getContentPane().add(plMain);
		plMain.setLayout(new CardLayout(0, 0));
		
		final JPanel plOperations = new JPanel();
		plOperations.setBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 153, 0)));
		plOperations.setVisible(false);
		plMain.add(plOperations, "name_77612886309962");
		plOperations.setLayout(null);
		
		
		final JPanel plOperationsPreviewWork = new JPanel();
		plOperationsPreviewWork.setBorder(new MatteBorder(2, 2, 0, 0, (Color) new Color(0, 153, 0)));
		plOperationsPreviewWork.setVisible(false);
		
		final JPanel plOperationsPreview = new JPanel();
		plOperationsPreview.setBorder(new MatteBorder(2, 0, 0, 2, (Color) new Color(0, 153, 0)));
		plOperationsPreview.setVisible(false);
		
		final JPanel plOperationsWork = new JPanel();
		plOperationsWork.setBorder(new MatteBorder(2, 2, 0, 0, (Color) new Color(0, 153, 0)));
		plOperationsWork.setBackground(Color.WHITE);
		plOperationsWork.setBounds(1092, 0, 274, 640);
		plOperations.add(plOperationsWork);
		plOperationsWork.setLayout(null);
		
		final JButton btnOperationsWorkLoad = new JButton("LOAD");
		btnOperationsWorkLoad.setFocusPainted(false);
		btnOperationsWorkLoad.setContentAreaFilled(false);
		btnOperationsWorkLoad.setForeground(new Color(0, 153, 0));
		btnOperationsWorkLoad.setFont(new Font("STHeiti", Font.PLAIN, 20));
		btnOperationsWorkLoad.setBorder(new LineBorder(new Color(0, 153, 0), 2));
		
		btnOperationsWorkLoad.setBounds(6, 6, 259, 40);
		plOperationsWork.add(btnOperationsWorkLoad);
		
		final JButton btnOperationsWorkRefresh = new JButton("Refresh");
		btnOperationsWorkRefresh.setFocusPainted(false);
		btnOperationsWorkRefresh.setContentAreaFilled(false);
		btnOperationsWorkRefresh.setFont(new Font("STHeiti", Font.PLAIN, 17));
		btnOperationsWorkRefresh.setBorder(new LineBorder(new Color(0, 153, 0), 2));
		btnOperationsWorkRefresh.setBounds(6, 52, 165, 35);
		plOperationsWork.add(btnOperationsWorkRefresh);
		
		final JButton btnOperationsWorkMerge = new JButton("MERGE");
		btnOperationsWorkMerge.setFocusPainted(false);
		btnOperationsWorkMerge.setContentAreaFilled(false);
		btnOperationsWorkMerge.setForeground(new Color(0, 153, 0));
		btnOperationsWorkMerge.setFont(new Font("STHeiti", Font.PLAIN, 25));
		btnOperationsWorkMerge.setBorder(new LineBorder(new Color(0, 153, 0), 4));
		btnOperationsWorkMerge.setBounds(6, 470, 259, 120);
		plOperationsWork.add(btnOperationsWorkMerge);
		
		final JButton btnOperationsWorkMergeAll = new JButton("Merge all");
		btnOperationsWorkMergeAll.setFocusPainted(false);
		btnOperationsWorkMergeAll.setContentAreaFilled(false);
		btnOperationsWorkMergeAll.setFont(new Font("STHeiti", Font.PLAIN, 17));
		btnOperationsWorkMergeAll.setBorder(new LineBorder(new Color(0, 153, 0), 2));
		btnOperationsWorkMergeAll.setBounds(6, 414, 259, 50);
		plOperationsWork.add(btnOperationsWorkMergeAll);
		
		JPanel plOperationsWorkMode = new JPanel();
		plOperationsWorkMode.setBackground(Color.WHITE);
		plOperationsWorkMode.setBorder(new TitledBorder(new LineBorder(new Color(0, 153, 0)), "Mode", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 153, 0)));
		plOperationsWorkMode.setBounds(6, 93, 262, 60);
		plOperationsWork.add(plOperationsWorkMode);
		plOperationsWorkMode.setLayout(null);
		
		JRadioButton rdbtnOperationsWorkModeAnd = new JRadioButton("AND");
		rdbtnOperationsWorkModeAnd.setFont(new Font("STHeiti", Font.PLAIN, 13));
		rdbtnOperationsWorkModeAnd.setBackground(Color.WHITE);
		rdbtnOperationsWorkModeAnd.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnOperationsWorkModeAnd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				mergeMode = 2;
			}
		});
		rdbtnOperationsWorkModeAnd.setBounds(10, 20, 80, 30);
		plOperationsWorkMode.add(rdbtnOperationsWorkModeAnd);
		
		JRadioButton rdbtnOperationsWorkModeOr = new JRadioButton("OR");
		rdbtnOperationsWorkModeOr.setFont(new Font("STHeiti", Font.PLAIN, 13));
		rdbtnOperationsWorkModeOr.setBackground(Color.WHITE);
		rdbtnOperationsWorkModeOr.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnOperationsWorkModeOr.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {												//simple ActionListeners used to change the merge mode
				mergeMode = 1;
			}
		});
		rdbtnOperationsWorkModeOr.setBounds(90, 20, 80, 30);
		plOperationsWorkMode.add(rdbtnOperationsWorkModeOr);
		
		JRadioButton rdbtnOperationsWorkModeXor = new JRadioButton("XOR");
		rdbtnOperationsWorkModeXor.setFont(new Font("STHeiti", Font.PLAIN, 13));
		rdbtnOperationsWorkModeXor.setBackground(Color.WHITE);
		rdbtnOperationsWorkModeXor.setSelected(true);
		rdbtnOperationsWorkModeXor.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnOperationsWorkModeXor.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				mergeMode = 0;
			}
		});
		rdbtnOperationsWorkModeXor.setBounds(170, 20, 81, 30);
		plOperationsWorkMode.add(rdbtnOperationsWorkModeXor);
		mMode.add(rdbtnOperationsWorkModeAnd);
		mMode.add(rdbtnOperationsWorkModeOr);
		mMode.add(rdbtnOperationsWorkModeXor);
		
		JPanel plOperationsWorkShading = new JPanel();
		plOperationsWorkShading.setFont(new Font("STHeiti", Font.PLAIN, 13));
		plOperationsWorkShading.setBackground(Color.WHITE);
		plOperationsWorkShading.setBorder(new TitledBorder(new LineBorder(new Color(0, 153, 0)), "Shading", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 153, 0)));
		plOperationsWorkShading.setBounds(6, 159, 100, 120);
		plOperationsWork.add(plOperationsWorkShading);
		plOperationsWorkShading.setLayout(null);
		
		JRadioButton rdbtnOperationsWorkShadingNormal = new JRadioButton("NORMAL");
		rdbtnOperationsWorkShadingNormal.setFont(new Font("STHeiti", Font.PLAIN, 13));
		rdbtnOperationsWorkShadingNormal.setBackground(Color.WHITE);
		rdbtnOperationsWorkShadingNormal.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnOperationsWorkShadingNormal.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				shadeMode = 2;
			}
		});
		rdbtnOperationsWorkShadingNormal.setBounds(3, 20, 93, 30);
		plOperationsWorkShading.add(rdbtnOperationsWorkShadingNormal);
		
		JRadioButton rdbtnOperationsWorkShadingFading = new JRadioButton("FADING");
		rdbtnOperationsWorkShadingFading.setFont(new Font("STHeiti", Font.PLAIN, 13));
		rdbtnOperationsWorkShadingFading.setBackground(Color.WHITE);
		rdbtnOperationsWorkShadingFading.setSelected(true);
		rdbtnOperationsWorkShadingFading.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnOperationsWorkShadingFading.addActionListener(new ActionListener(){						//similar to above Action Listeners used to change shade mode
			public void actionPerformed(ActionEvent e) {
				shadeMode = 0;
			}
		});
		rdbtnOperationsWorkShadingFading.setBounds(3, 50, 94, 30);
		plOperationsWorkShading.add(rdbtnOperationsWorkShadingFading);
		
		JRadioButton rdbtnOperationsWorkShadingShading = new JRadioButton("SHADING");
		rdbtnOperationsWorkShadingShading.setFont(new Font("STHeiti", Font.PLAIN, 13));
		rdbtnOperationsWorkShadingShading.setBackground(Color.WHITE);
		rdbtnOperationsWorkShadingShading.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnOperationsWorkShadingShading.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				shadeMode = 1;
			}
		});
		rdbtnOperationsWorkShadingShading.setBounds(3, 80, 94, 30);
		plOperationsWorkShading.add(rdbtnOperationsWorkShadingShading);
		shadingMode.add(rdbtnOperationsWorkShadingNormal);
		shadingMode.add(rdbtnOperationsWorkShadingFading);
		shadingMode.add(rdbtnOperationsWorkShadingShading);
		
		JPanel plOperationsWorkShadingDirections = new JPanel();
		plOperationsWorkShadingDirections.setBackground(Color.WHITE);
		plOperationsWorkShadingDirections.setBorder(new TitledBorder(new LineBorder(new Color(0, 153, 0)), "Shading Directions", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 153, 0)));
		plOperationsWorkShadingDirections.setBounds(106, 159, 162, 120);
		plOperationsWork.add(plOperationsWorkShadingDirections);
		plOperationsWorkShadingDirections.setLayout(null);
		
		JRadioButton rdbtnOperationsWorkShadingDirectionsTL = new JRadioButton("TL");
		rdbtnOperationsWorkShadingDirectionsTL.setFont(new Font("STHeiti", Font.PLAIN, 13));
		rdbtnOperationsWorkShadingDirectionsTL.setBackground(Color.WHITE);
		rdbtnOperationsWorkShadingDirectionsTL.setToolTipText("From top left corner");
		rdbtnOperationsWorkShadingDirectionsTL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				direction = 1;
			}
		});
		rdbtnOperationsWorkShadingDirectionsTL.setBounds(3, 20, 51, 30);
		plOperationsWorkShadingDirections.add(rdbtnOperationsWorkShadingDirectionsTL);
		
		JRadioButton rdbtnOperationsWorkShadingDirectionsTC = new JRadioButton("TC");
		rdbtnOperationsWorkShadingDirectionsTC.setFont(new Font("STHeiti", Font.PLAIN, 13));
		rdbtnOperationsWorkShadingDirectionsTC.setBackground(Color.WHITE);
		rdbtnOperationsWorkShadingDirectionsTC.setToolTipText("From top");
		rdbtnOperationsWorkShadingDirectionsTC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				direction = 2;
			}
		});
		rdbtnOperationsWorkShadingDirectionsTC.setBounds(54, 20, 51, 30);
		plOperationsWorkShadingDirections.add(rdbtnOperationsWorkShadingDirectionsTC);
		
		JRadioButton rdbtnOperationsWorkShadingDirectionsTR = new JRadioButton("TR");
		rdbtnOperationsWorkShadingDirectionsTR.setFont(new Font("STHeiti", Font.PLAIN, 13));
		rdbtnOperationsWorkShadingDirectionsTR.setBackground(Color.WHITE);
		rdbtnOperationsWorkShadingDirectionsTR.setToolTipText("From top right corner");
		rdbtnOperationsWorkShadingDirectionsTR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				direction = 3;
			}
		});
		rdbtnOperationsWorkShadingDirectionsTR.setBounds(105, 20, 51, 30);
		plOperationsWorkShadingDirections.add(rdbtnOperationsWorkShadingDirectionsTR);
		
		JRadioButton rdbtnOperationsWorkShadingDirectionsCL = new JRadioButton("CL");
		rdbtnOperationsWorkShadingDirectionsCL.setFont(new Font("STHeiti", Font.PLAIN, 13));
		rdbtnOperationsWorkShadingDirectionsCL.setBackground(Color.WHITE);
		rdbtnOperationsWorkShadingDirectionsCL.setToolTipText("From left side");
		rdbtnOperationsWorkShadingDirectionsCL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				direction = 0;
			}
		});
		rdbtnOperationsWorkShadingDirectionsCL.setBounds(3, 50, 51, 30);
		plOperationsWorkShadingDirections.add(rdbtnOperationsWorkShadingDirectionsCL);
		
		JRadioButton rdbtnOperationsWorkShadingDirectionsCC = new JRadioButton("CC");
		rdbtnOperationsWorkShadingDirectionsCC.setFont(new Font("STHeiti", Font.PLAIN, 13));
		rdbtnOperationsWorkShadingDirectionsCC.setBackground(Color.WHITE);
		rdbtnOperationsWorkShadingDirectionsCC.setSelected(true);
		rdbtnOperationsWorkShadingDirectionsCC.setToolTipText("From center");
		rdbtnOperationsWorkShadingDirectionsCC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				direction = 8;
			}
		});
		rdbtnOperationsWorkShadingDirectionsCC.setBounds(54, 50, 54, 30);
		plOperationsWorkShadingDirections.add(rdbtnOperationsWorkShadingDirectionsCC);
		
		JRadioButton rdbtnOperationsWorkShadingDirectionsCR = new JRadioButton("CR");
		rdbtnOperationsWorkShadingDirectionsCR.setFont(new Font("STHeiti", Font.PLAIN, 13));
		rdbtnOperationsWorkShadingDirectionsCR.setBackground(Color.WHITE);
		rdbtnOperationsWorkShadingDirectionsCR.setToolTipText("From right side");
		rdbtnOperationsWorkShadingDirectionsCR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				direction = 4;
			}
		});
		rdbtnOperationsWorkShadingDirectionsCR.setBounds(105, 50, 51, 30);
		plOperationsWorkShadingDirections.add(rdbtnOperationsWorkShadingDirectionsCR);
		
		JRadioButton rdbtnOperationsWorkShadingDirectionsBL = new JRadioButton("BL");
		rdbtnOperationsWorkShadingDirectionsBL.setFont(new Font("STHeiti", Font.PLAIN, 13));
		rdbtnOperationsWorkShadingDirectionsBL.setBackground(Color.WHITE);
		rdbtnOperationsWorkShadingDirectionsBL.setToolTipText("From bottom left corner");
		rdbtnOperationsWorkShadingDirectionsBL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {										//Action listeners used to change direction from which we shade/fade
				direction = 7;
			}
		});
		rdbtnOperationsWorkShadingDirectionsBL.setBounds(3, 80, 51, 30);
		plOperationsWorkShadingDirections.add(rdbtnOperationsWorkShadingDirectionsBL);
		
		JRadioButton rdbtnOperationsWorkShadingDirectionsBC = new JRadioButton("BC");
		rdbtnOperationsWorkShadingDirectionsBC.setFont(new Font("STHeiti", Font.PLAIN, 13));
		rdbtnOperationsWorkShadingDirectionsBC.setBackground(Color.WHITE);
		rdbtnOperationsWorkShadingDirectionsBC.setToolTipText("From bottom");
		rdbtnOperationsWorkShadingDirectionsBC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				direction = 6;
			}
		});
		rdbtnOperationsWorkShadingDirectionsBC.setBounds(54, 80, 51, 30);
		plOperationsWorkShadingDirections.add(rdbtnOperationsWorkShadingDirectionsBC);
		
		JRadioButton rdbtnOperationsWorkShadingDirectionsBR = new JRadioButton("BR");
		rdbtnOperationsWorkShadingDirectionsBR.setFont(new Font("STHeiti", Font.PLAIN, 13));
		rdbtnOperationsWorkShadingDirectionsBR.setBackground(Color.WHITE);
		rdbtnOperationsWorkShadingDirectionsBR.setToolTipText("From bottom right corner");
		rdbtnOperationsWorkShadingDirectionsBR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				direction = 5;
			}
		});
		rdbtnOperationsWorkShadingDirectionsBR.setBounds(105, 80, 51, 30);
		plOperationsWorkShadingDirections.add(rdbtnOperationsWorkShadingDirectionsBR);
		shadingDirections.add(rdbtnOperationsWorkShadingDirectionsTL);
		shadingDirections.add(rdbtnOperationsWorkShadingDirectionsTC);
		shadingDirections.add(rdbtnOperationsWorkShadingDirectionsTR);
		shadingDirections.add(rdbtnOperationsWorkShadingDirectionsCL);
		shadingDirections.add(rdbtnOperationsWorkShadingDirectionsCC);
		shadingDirections.add(rdbtnOperationsWorkShadingDirectionsCR);
		shadingDirections.add(rdbtnOperationsWorkShadingDirectionsBL);
		shadingDirections.add(rdbtnOperationsWorkShadingDirectionsBC);
		shadingDirections.add(rdbtnOperationsWorkShadingDirectionsBR);
		
		JPanel plOperationsWorkBow = new JPanel();
		plOperationsWorkBow.setBackground(Color.WHITE);
		plOperationsWorkBow.setBorder(new TitledBorder(new LineBorder(new Color(0, 153, 0)), "BOW Mode", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 153, 0)));
		plOperationsWorkBow.setBounds(6, 285, 262, 90);
		plOperationsWork.add(plOperationsWorkBow);
		plOperationsWorkBow.setLayout(null);
		
		JRadioButton rdbtnOperationsWorkBowWob = new JRadioButton("WHITE ON BLACK");
		rdbtnOperationsWorkBowWob.setFont(new Font("STHeiti", Font.PLAIN, 13));
		rdbtnOperationsWorkBowWob.setBackground(Color.WHITE);
		rdbtnOperationsWorkBowWob.setSelected(true);
		rdbtnOperationsWorkBowWob.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnOperationsWorkBowWob.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				BWMode = 0;
			}
		});
		rdbtnOperationsWorkBowWob.setBounds(10, 20, 242, 30);										//change if the shading/fading is performed black on white or white on black
		plOperationsWorkBow.add(rdbtnOperationsWorkBowWob);
		
		JRadioButton rdbtnOperationsWorkBowBow = new JRadioButton("BLACK ON WHITE");
		rdbtnOperationsWorkBowBow.setFont(new Font("STHeiti", Font.PLAIN, 13));
		rdbtnOperationsWorkBowBow.setBackground(Color.WHITE);
		rdbtnOperationsWorkBowBow.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnOperationsWorkBowBow.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				BWMode = 1;
			}
		});
		rdbtnOperationsWorkBowBow.setBounds(10, 50, 242, 30);
		plOperationsWorkBow.add(rdbtnOperationsWorkBowBow);
		bowMode.add(rdbtnOperationsWorkBowWob);
		bowMode.add(rdbtnOperationsWorkBowBow);
		
		JCheckBox chckbxOperationsWorkViewAfterMerge = new JCheckBox("View images after merge");
		chckbxOperationsWorkViewAfterMerge.setFont(new Font("STHeiti", Font.PLAIN, 13));
		chckbxOperationsWorkViewAfterMerge.setBackground(Color.WHITE);
		chckbxOperationsWorkViewAfterMerge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {											//changing viewImagesAfterMerge boolean  flag
				viewImagesAfterMerge = !viewImagesAfterMerge;
			}
		});
		chckbxOperationsWorkViewAfterMerge.setSelected(true);
		chckbxOperationsWorkViewAfterMerge.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxOperationsWorkViewAfterMerge.setBounds(6, 375, 262, 30);
		plOperationsWork.add(chckbxOperationsWorkViewAfterMerge);
		
		final JButton btnOperationsWorkClearAll = new JButton("Clear all");
		btnOperationsWorkClearAll.setFocusPainted(false);
		btnOperationsWorkClearAll.setContentAreaFilled(false);
		btnOperationsWorkClearAll.setFont(new Font("STHeiti", Font.PLAIN, 17));
		btnOperationsWorkClearAll.setBorder(new LineBorder(new Color(0, 153, 0), 2));
		btnOperationsWorkClearAll.setBounds(177, 52, 88, 35);
		plOperationsWork.add(btnOperationsWorkClearAll);
		
		final JLabel lblbtnOperationsWorkLoad = new JLabel("");
		lblbtnOperationsWorkLoad.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnLoad.jpg")));
		lblbtnOperationsWorkLoad.setBounds(6, 6, 259, 40);
		plOperationsWork.add(lblbtnOperationsWorkLoad);
		
		final JLabel lblbtnOperationsWorkRefresh = new JLabel("");
		lblbtnOperationsWorkRefresh.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnRefresh.jpg")));
		lblbtnOperationsWorkRefresh.setBounds(6, 52, 165, 35);
		plOperationsWork.add(lblbtnOperationsWorkRefresh);
		
		final JLabel lblbtnOperationsWorkClearAll = new JLabel("");
		lblbtnOperationsWorkClearAll.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnClearAll.jpg")));
		lblbtnOperationsWorkClearAll.setBounds(177, 52, 88, 35);
		plOperationsWork.add(lblbtnOperationsWorkClearAll);
		
		final JLabel lblbtnOperationsWorkMergeAll = new JLabel("");
		lblbtnOperationsWorkMergeAll.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnMergeAll.jpg")));
		lblbtnOperationsWorkMergeAll.setBounds(6, 414, 259, 50);
		plOperationsWork.add(lblbtnOperationsWorkMergeAll);
		
		final JLabel lblbtnOperationsWorkMerge = new JLabel("");
		lblbtnOperationsWorkMerge.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnMerge.jpg")));
		lblbtnOperationsWorkMerge.setBounds(6, 470, 259, 120);
		plOperationsWork.add(lblbtnOperationsWorkMerge);
		
		
		final JPanel plOperationsImages = new JPanel();
		plOperationsImages.setBorder(new MatteBorder(2, 0, 0, 2, (Color) new Color(0, 153, 0)));
		plOperationsImages.setBackground(Color.WHITE);
		plOperationsImages.setBounds(0, 0, 1092, 640);
		plOperations.add(plOperationsImages);
		plOperationsImages.setLayout(null);
		
		final JPanel plOperationsListImagesOptions = new JPanel();
		plOperationsListImagesOptions.setVisible(false);
		plOperationsListImagesOptions.setBackground(new Color(0, 153, 0));
		plOperationsListImagesOptions.setBounds(312, 363, 774, 52);
		plOperationsImages.add(plOperationsListImagesOptions);
		plOperationsListImagesOptions.setLayout(null);
		
		final JButton btnOperationsListImageAdd = new JButton("Add to merge");
		btnOperationsListImageAdd.setBorderPainted(false);
		btnOperationsListImageAdd.setContentAreaFilled(false);
		btnOperationsListImageAdd.setFocusPainted(false);
		btnOperationsListImageAdd.setFont(new Font("STHeiti", Font.PLAIN, 17));
		
		btnOperationsListImageAdd.setBounds(210, 8, 168, 35);
		plOperationsListImagesOptions.add(btnOperationsListImageAdd);
		
		final JButton btnOperationsListImagePreview = new JButton("Preview");
		btnOperationsListImagePreview.setFocusPainted(false);
		btnOperationsListImagePreview.setContentAreaFilled(false);
		btnOperationsListImagePreview.setBorderPainted(false);
		btnOperationsListImagePreview.setFont(new Font("STHeiti", Font.PLAIN, 17));
		btnOperationsListImagePreview.setBounds(396, 8, 168, 35);
		plOperationsListImagesOptions.add(btnOperationsListImagePreview);
		
		final JLabel lblbtnOperationsListImageAdd = new JLabel("");
		lblbtnOperationsListImageAdd.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnDelete.jpg")));
		lblbtnOperationsListImageAdd.setBounds(210, 8, 168, 35);
		plOperationsListImagesOptions.add(lblbtnOperationsListImageAdd);
		
		final JLabel lblbtnOperationsListImagePreview = new JLabel("f");
		lblbtnOperationsListImagePreview.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnDelete.jpg")));
		lblbtnOperationsListImagePreview.setBounds(396, 8, 168, 35);
		plOperationsListImagesOptions.add(lblbtnOperationsListImagePreview);
		
		final JScrollPane scrollPaneListImages = new JScrollPane();
		scrollPaneListImages.setBounds(312, 6, 774, 409);
		plOperationsImages.add(scrollPaneListImages);
		
		final JToolBar tbListImages = new JToolBar();
		tbListImages.setBackground(Color.WHITE);
		tbListImages.setFloatable(false);
		scrollPaneListImages.setViewportView(tbListImages);
		
		JScrollPane scrollPaneListToMarge = new JScrollPane();
		scrollPaneListToMarge.setBorder(new TitledBorder(new LineBorder(new Color(0, 153, 0)), "Added to merge:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 153, 0)));
		scrollPaneListToMarge.setBounds(6, 420, 1080, 170);
		plOperationsImages.add(scrollPaneListToMarge);
		
		final JToolBar tbListToMerge = new JToolBar();
		tbListToMerge.setBackground(Color.WHITE);
		tbListToMerge.setFloatable(false);
		scrollPaneListToMarge.setViewportView(tbListToMerge);
		
		JPanel plOperationsListPath = new JPanel();
		plOperationsListPath.setBorder(new TitledBorder(new LineBorder(new Color(0, 153, 0)), "Path:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 153, 0)));
		plOperationsListPath.setBackground(Color.WHITE);
		plOperationsListPath.setBounds(6, 6, 300, 409);
		plOperationsImages.add(plOperationsListPath);
		plOperationsListPath.setLayout(new GridLayout(20, 1, 0, 0));
		
		
		
		plOperationsPreview.setBackground(Color.WHITE);
		plOperationsPreview.setBounds(0, 0, 1092, 640);
		plOperations.add(plOperationsPreview);
		plOperationsPreview.setLayout(null);
		
		final JButton imgOperationsPreview = new JButton("ImageToView");
		imgOperationsPreview.setBounds(146, 5, 800, 545);
		plOperationsPreview.add(imgOperationsPreview);
		
		tfOperationsPreviewWorkPath = new JTextField();
		tfOperationsPreviewWorkPath.setBorder(new TitledBorder(new LineBorder(new Color(0, 153, 0)), "Path of the image:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 153, 0)));
		tfOperationsPreviewWorkPath.setFont(new Font("STHeiti", Font.PLAIN, 13));
		tfOperationsPreviewWorkPath.setBounds(146, 555, 800, 40);
		plOperationsPreview.add(tfOperationsPreviewWorkPath);
		tfOperationsPreviewWorkPath.setEditable(false);
		tfOperationsPreviewWorkPath.setHorizontalAlignment(SwingConstants.CENTER);
		tfOperationsPreviewWorkPath.setText("Path of the image");
		tfOperationsPreviewWorkPath.setColumns(10);
		plOperationsPreviewWork.setBackground(Color.WHITE);
		plOperationsPreviewWork.setBounds(1092, 0, 274, 640);
		plOperations.add(plOperationsPreviewWork);
		plOperationsPreviewWork.setLayout(null);
		
		final JButton btnOperationsPreviewWorkBack = new JButton("Close the preview");
		btnOperationsPreviewWorkBack.setBackground(Color.WHITE);
		btnOperationsPreviewWorkBack.setFont(new Font("STHeiti", Font.PLAIN, 17));
		btnOperationsPreviewWorkBack.setFocusPainted(false);
		btnOperationsPreviewWorkBack.setContentAreaFilled(false);
		btnOperationsPreviewWorkBack.setBorder(new LineBorder(new Color(0, 153, 0), 2));
		btnOperationsPreviewWorkBack.setBounds(6, 280, 259, 50);
		plOperationsPreviewWork.add(btnOperationsPreviewWorkBack);
		
		final JLabel lblbtnOperationsPreviewWorkBack = new JLabel("");
		lblbtnOperationsPreviewWorkBack.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnMergeAll.jpg")));
		lblbtnOperationsPreviewWorkBack.setBounds(6, 280, 259, 50);
		plOperationsPreviewWork.add(lblbtnOperationsPreviewWorkBack);
		
		final JPanel plMergedImages = new JPanel();
		plMergedImages.setVisible(false);
		plMain.add(plMergedImages, "name_77604441665075");
		plMergedImages.setLayout(null);
		
		final JPanel plMergedImagesView = new JPanel();
		plMergedImagesView.setBackground(Color.WHITE);
		plMergedImagesView.setBorder(new MatteBorder(2, 0, 0, 2, (Color) new Color(0, 153, 255)));
		plMergedImagesView.setBounds(0, 0, 1092, 640);
		plMergedImages.add(plMergedImagesView);
		plMergedImagesView.setLayout(null);
		
		final JButton btnMergedImagesViewView = new JButton("");
		btnMergedImagesViewView.setContentAreaFilled(false);
		btnMergedImagesViewView.setFocusPainted(false);
		btnMergedImagesViewView.setBackground(Color.WHITE);
		btnMergedImagesViewView.setBounds(6, 6, 1080, 524);
		plMergedImagesView.add(btnMergedImagesViewView);
		
		JScrollPane scrollPaneListMergedImages = new JScrollPane();
		scrollPaneListMergedImages.setBorder(new TitledBorder(new LineBorder(new Color(0, 153, 255)), "Merged Images:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 153, 255)));
		scrollPaneListMergedImages.setBounds(6, 536, 1080, 70);
		plMergedImagesView.add(scrollPaneListMergedImages);
		
		final JToolBar tbListMergedImages = new JToolBar();
		tbListMergedImages.setBackground(Color.WHITE);
		tbListMergedImages.setFloatable(false);
		scrollPaneListMergedImages.setViewportView(tbListMergedImages);
		
		JPanel plMergedImagesWork = new JPanel();
		plMergedImagesWork.setBorder(new MatteBorder(2, 2, 0, 0, (Color) new Color(0, 153, 255)));
		plMergedImagesWork.setBounds(1092, 0, 274, 640);
		plMergedImages.add(plMergedImagesWork);
		plMergedImagesWork.setBackground(Color.WHITE);
		plMergedImagesWork.setLayout(null);
		

		
		JPanel plMergedImagesWorkName = new JPanel();
		plMergedImagesWorkName.setBorder(new TitledBorder(new LineBorder(new Color(0, 153, 255)), "Name", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 153, 255)));
		plMergedImagesWorkName.setBackground(Color.WHITE);
		plMergedImagesWorkName.setBounds(6, 6, 262, 95);
		plMergedImagesWork.add(plMergedImagesWorkName);
		plMergedImagesWorkName.setLayout(null);
		
		tfMergedImagesWorkNameCustom = new JTextField();
		tfMergedImagesWorkNameCustom.setFont(new Font("STHeiti", Font.PLAIN, 13));
		tfMergedImagesWorkNameCustom.setBorder(new LineBorder(new Color(0, 153, 255)));
		tfMergedImagesWorkNameCustom.setText("image");
		tfMergedImagesWorkNameCustom.setBounds(10, 55, 242, 30);
		plMergedImagesWorkName.add(tfMergedImagesWorkNameCustom);
		tfMergedImagesWorkNameCustom.setColumns(10);
		
		final JComboBox cbMergedImagesWorkNameFromFile = new JComboBox();
		cbMergedImagesWorkNameFromFile.setVisible(false);
		cbMergedImagesWorkNameFromFile.setBackground(Color.WHITE);
		cbMergedImagesWorkNameFromFile.setBounds(10, 55, 242, 30);
		plMergedImagesWorkName.add(cbMergedImagesWorkNameFromFile);
		
		JRadioButton rdbtnMergedImagesWorkNameCustom = new JRadioButton("Custom");
		rdbtnMergedImagesWorkNameCustom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbMergedImagesWorkNameFromFile.setVisible(false);
				tfMergedImagesWorkNameCustom.setVisible(true);
			}
		});
		rdbtnMergedImagesWorkNameCustom.setFont(new Font("STHeiti", Font.PLAIN, 13));
		rdbtnMergedImagesWorkNameCustom.setBackground(Color.WHITE);
		rdbtnMergedImagesWorkNameCustom.setSelected(true);
		rdbtnMergedImagesWorkNameCustom.setBounds(10, 20, 121, 23);
		plMergedImagesWorkName.add(rdbtnMergedImagesWorkNameCustom);
		
		JRadioButton rdbtnMergedImagesWorkNameFromFile = new JRadioButton("Get from file");
		rdbtnMergedImagesWorkNameFromFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbMergedImagesWorkNameFromFile.setVisible(true);
				tfMergedImagesWorkNameCustom.setVisible(false);
			}
		});
		rdbtnMergedImagesWorkNameFromFile.setFont(new Font("STHeiti", Font.PLAIN, 13));
		rdbtnMergedImagesWorkNameFromFile.setBackground(Color.WHITE);
		rdbtnMergedImagesWorkNameFromFile.setBounds(131, 20, 121, 23);
		plMergedImagesWorkName.add(rdbtnMergedImagesWorkNameFromFile);
		nameMode.add(rdbtnMergedImagesWorkNameCustom);
		nameMode.add(rdbtnMergedImagesWorkNameFromFile);
		
		
		JPanel plMergedImagesWorkCounter = new JPanel();
		plMergedImagesWorkCounter.setFont(new Font("STHeiti", Font.PLAIN, 13));
		plMergedImagesWorkCounter.setBorder(new TitledBorder(new LineBorder(new Color(0, 153, 255)), "Counter", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 153, 255)));
		plMergedImagesWorkCounter.setBackground(Color.WHITE);
		plMergedImagesWorkCounter.setBounds(6, 107, 262, 95);
		plMergedImagesWork.add(plMergedImagesWorkCounter);
		plMergedImagesWorkCounter.setLayout(null);
		
		tfMergedImagesWorkCounterStart = new JTextField();
		tfMergedImagesWorkCounterStart.setFont(new Font("STHeiti", Font.PLAIN, 13));
		tfMergedImagesWorkCounterStart.setBorder(new LineBorder(new Color(0, 153, 255)));
		tfMergedImagesWorkCounterStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveCounter = Integer.parseInt(tfMergedImagesWorkCounterStart.getText());
			}
		});
		tfMergedImagesWorkCounterStart.setText("1");
		tfMergedImagesWorkCounterStart.setBounds(100, 20, 152, 30);
		plMergedImagesWorkCounter.add(tfMergedImagesWorkCounterStart);
		tfMergedImagesWorkCounterStart.setColumns(10);
		
		tfMergedImagesWorkCounterIncrease = new JTextField();
		tfMergedImagesWorkCounterIncrease.setFont(new Font("STHeiti", Font.PLAIN, 13));
		tfMergedImagesWorkCounterIncrease.setHorizontalAlignment(SwingConstants.LEFT);
		tfMergedImagesWorkCounterIncrease.setBorder(new LineBorder(new Color(0, 153, 255)));
		tfMergedImagesWorkCounterIncrease.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveStep = Integer.parseInt(tfMergedImagesWorkCounterIncrease.getText());
			}
		});
		tfMergedImagesWorkCounterIncrease.setText("1");
		tfMergedImagesWorkCounterIncrease.setBounds(100, 55, 152, 30);
		plMergedImagesWorkCounter.add(tfMergedImagesWorkCounterIncrease);
		tfMergedImagesWorkCounterIncrease.setColumns(10);
		
		JLabel lblMergedImagesWorkCounterStart = new JLabel("Start value:");
		lblMergedImagesWorkCounterStart.setFont(new Font("STHeiti", Font.PLAIN, 13));
		lblMergedImagesWorkCounterStart.setBackground(Color.WHITE);
		lblMergedImagesWorkCounterStart.setBounds(10, 20, 80, 30);
		plMergedImagesWorkCounter.add(lblMergedImagesWorkCounterStart);
		
		JLabel lblMergedImagesWorkCounterIncrease = new JLabel("Increase by:");
		lblMergedImagesWorkCounterIncrease.setFont(new Font("STHeiti", Font.PLAIN, 13));
		lblMergedImagesWorkCounterIncrease.setBackground(Color.WHITE);
		lblMergedImagesWorkCounterIncrease.setBounds(10, 55, 80, 30);
		plMergedImagesWorkCounter.add(lblMergedImagesWorkCounterIncrease);
		
		final JButton btnMergedImagesWorkSave = new JButton("SAVE");
		btnMergedImagesWorkSave.setContentAreaFilled(false);
		btnMergedImagesWorkSave.setFocusPainted(false);
		btnMergedImagesWorkSave.setFont(new Font("STHeiti", Font.BOLD, 25));
		btnMergedImagesWorkSave.setForeground(new Color(0, 153, 255));
		btnMergedImagesWorkSave.setBorder(new LineBorder(new Color(0, 153, 255), 4));
		btnMergedImagesWorkSave.setBounds(6, 440, 259, 100);
		plMergedImagesWork.add(btnMergedImagesWorkSave);
		
		final JButton btnMergedImagesWorkDelete = new JButton("Delete");
		btnMergedImagesWorkDelete.setContentAreaFilled(false);
		btnMergedImagesWorkDelete.setFocusPainted(false);
		btnMergedImagesWorkDelete.setFont(new Font("STHeiti", Font.PLAIN, 17));
		btnMergedImagesWorkDelete.setBorder(new LineBorder(new Color(0, 153, 255), 2));
		btnMergedImagesWorkDelete.setToolTipText("Delete chosen image");
		btnMergedImagesWorkDelete.setBounds(6, 546, 165, 40);
		plMergedImagesWork.add(btnMergedImagesWorkDelete);
		
		JPanel plMergedImagesWorkBpp = new JPanel();
		plMergedImagesWorkBpp.setBorder(new TitledBorder(new LineBorder(new Color(0, 153, 255)), "BPP", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 153, 255)));
		plMergedImagesWorkBpp.setBackground(Color.WHITE);
		plMergedImagesWorkBpp.setBounds(137, 208, 131, 180);
		plMergedImagesWork.add(plMergedImagesWorkBpp);
		plMergedImagesWorkBpp.setLayout(null);
		
		JRadioButton rdbtnMergedImagesWorkBpp1 = new JRadioButton("1");
		rdbtnMergedImagesWorkBpp1.setFont(new Font("STHeiti", Font.PLAIN, 13));
		rdbtnMergedImagesWorkBpp1.setBackground(Color.WHITE);
		rdbtnMergedImagesWorkBpp1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bppMode = 1;
			}
		});
		rdbtnMergedImagesWorkBpp1.setBounds(10, 20, 110, 30);
		plMergedImagesWorkBpp.add(rdbtnMergedImagesWorkBpp1);
		
		final JRadioButton rdbtnMergedImagesWorkBpp8 = new JRadioButton("8");
		rdbtnMergedImagesWorkBpp8.setFont(new Font("STHeiti", Font.PLAIN, 13));
		rdbtnMergedImagesWorkBpp8.setBackground(Color.WHITE);
		rdbtnMergedImagesWorkBpp8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bppMode = 8;
			}
		});
		rdbtnMergedImagesWorkBpp8.setBounds(10, 50, 110, 30);
		plMergedImagesWorkBpp.add(rdbtnMergedImagesWorkBpp8);
		
		final JRadioButton rdbtnMergedImagesWorkBpp16 = new JRadioButton("16");
		rdbtnMergedImagesWorkBpp16.setFont(new Font("STHeiti", Font.PLAIN, 13));
		rdbtnMergedImagesWorkBpp16.setBackground(Color.WHITE);
		rdbtnMergedImagesWorkBpp16.setEnabled(false);
		rdbtnMergedImagesWorkBpp16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bppMode = 16;
			}
		});
		rdbtnMergedImagesWorkBpp16.setBounds(10, 80, 110, 30);
		plMergedImagesWorkBpp.add(rdbtnMergedImagesWorkBpp16);
		
		final JRadioButton rdbtnMergedImagesWorkBpp24 = new JRadioButton("24");
		rdbtnMergedImagesWorkBpp24.setFont(new Font("STHeiti", Font.PLAIN, 13));
		rdbtnMergedImagesWorkBpp24.setBackground(Color.WHITE);
		rdbtnMergedImagesWorkBpp24.setSelected(true);
		rdbtnMergedImagesWorkBpp24.addActionListener(new ActionListener() {						//changing the bpp with which images will be saved
			public void actionPerformed(ActionEvent e) {
				bppMode = 24;
			}
		});
		rdbtnMergedImagesWorkBpp24.setBounds(10, 110, 110, 30);
		plMergedImagesWorkBpp.add(rdbtnMergedImagesWorkBpp24);
		
		final JRadioButton rdbtnMergedImagesWorkBpp32 = new JRadioButton("32");
		rdbtnMergedImagesWorkBpp32.setFont(new Font("STHeiti", Font.PLAIN, 13));
		rdbtnMergedImagesWorkBpp32.setEnabled(false);
		rdbtnMergedImagesWorkBpp32.setBackground(Color.WHITE);
		rdbtnMergedImagesWorkBpp32.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bppMode = 32;
			}
		});
		rdbtnMergedImagesWorkBpp32.setBounds(10, 140, 110, 30);
		plMergedImagesWorkBpp.add(rdbtnMergedImagesWorkBpp32);
		saveBpp.add(rdbtnMergedImagesWorkBpp1);
		saveBpp.add(rdbtnMergedImagesWorkBpp8);
		saveBpp.add(rdbtnMergedImagesWorkBpp16);
		saveBpp.add(rdbtnMergedImagesWorkBpp24);
		saveBpp.add(rdbtnMergedImagesWorkBpp32);
		
		
		JPanel plMergedImagesWorkType = new JPanel();
		plMergedImagesWorkType.setFont(new Font("STHeiti", Font.PLAIN, 13));
		plMergedImagesWorkType.setBorder(new TitledBorder(new LineBorder(new Color(0, 153, 255)), "Save as", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 153, 255)));
		plMergedImagesWorkType.setBackground(Color.WHITE);
		plMergedImagesWorkType.setBounds(6, 208, 131, 180);
		plMergedImagesWork.add(plMergedImagesWorkType);
		plMergedImagesWorkType.setLayout(null);
		
		JRadioButton rdbtnMergedImagesWorkTypeJPG = new JRadioButton(".JPG");
		rdbtnMergedImagesWorkTypeJPG.setFont(new Font("STHeiti", Font.PLAIN, 13));
		rdbtnMergedImagesWorkTypeJPG.setBackground(Color.WHITE);
		rdbtnMergedImagesWorkTypeJPG.setSelected(true);
		rdbtnMergedImagesWorkTypeJPG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				type = "JPG";
				if (bppMode == 32 ) {
					bppMode = 24;
					rdbtnMergedImagesWorkBpp32.setSelected(false);
					rdbtnMergedImagesWorkBpp24.setSelected(true);
				}
				if (bppMode == 16 ) {
					bppMode = 8;
					rdbtnMergedImagesWorkBpp16.setSelected(false);
					rdbtnMergedImagesWorkBpp8.setSelected(true);
				}
				rdbtnMergedImagesWorkBpp32.setEnabled(false);
				rdbtnMergedImagesWorkBpp16.setEnabled(false);
			}
		});
		rdbtnMergedImagesWorkTypeJPG.setBounds(10, 20, 110, 30);
		plMergedImagesWorkType.add(rdbtnMergedImagesWorkTypeJPG);
		
		JRadioButton rdbtnMergedImagesWorkTypeJPEG = new JRadioButton(".JPEG");
		rdbtnMergedImagesWorkTypeJPEG.setFont(new Font("STHeiti", Font.PLAIN, 13));
		rdbtnMergedImagesWorkTypeJPEG.setBackground(Color.WHITE);
		rdbtnMergedImagesWorkTypeJPEG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				type = "JPEG";
				if (bppMode == 32) {
					bppMode = 24;												//choosing type in which images will be saved for jpg and bmp we also change bpp mode because
					rdbtnMergedImagesWorkBpp32.setSelected(false);				//they do not support such bpp mode
					rdbtnMergedImagesWorkBpp24.setSelected(true);
				}
				if (bppMode == 16 ) {
					bppMode = 8;
					rdbtnMergedImagesWorkBpp16.setSelected(false);
					rdbtnMergedImagesWorkBpp8.setSelected(true);
				}
				rdbtnMergedImagesWorkBpp32.setEnabled(false);
				rdbtnMergedImagesWorkBpp16.setEnabled(false);
			}
		});
		rdbtnMergedImagesWorkTypeJPEG.setBounds(10, 50, 110, 30);
		plMergedImagesWorkType.add(rdbtnMergedImagesWorkTypeJPEG);
		
		JRadioButton rdbtnMergedImagesWorkTypeBMP = new JRadioButton(".BMP");
		rdbtnMergedImagesWorkTypeBMP.setFont(new Font("STHeiti", Font.PLAIN, 13));
		rdbtnMergedImagesWorkTypeBMP.setBackground(Color.WHITE);
		rdbtnMergedImagesWorkTypeBMP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				type = "BMP";
				if (bppMode == 32) {
					bppMode = 24;
					rdbtnMergedImagesWorkBpp32.setSelected(false);
					rdbtnMergedImagesWorkBpp24.setSelected(true);
				}
				System.out.println("BMP action performed");
				rdbtnMergedImagesWorkBpp32.setEnabled(false);
				rdbtnMergedImagesWorkBpp16.setEnabled(true);
			}
		});
		rdbtnMergedImagesWorkTypeBMP.setBounds(10, 80, 110, 30);
		plMergedImagesWorkType.add(rdbtnMergedImagesWorkTypeBMP);
		
		JRadioButton rdbtnMergedImagesWorkTypePNG = new JRadioButton(".PNG");
		rdbtnMergedImagesWorkTypePNG.setFont(new Font("STHeiti", Font.PLAIN, 13));
		rdbtnMergedImagesWorkTypePNG.setBackground(Color.WHITE);
		rdbtnMergedImagesWorkTypePNG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				type = "PNG";
				rdbtnMergedImagesWorkBpp32.setEnabled(true);
				rdbtnMergedImagesWorkBpp16.setEnabled(true);
			}
		});
		rdbtnMergedImagesWorkTypePNG.setBounds(10, 110, 110, 30);
		plMergedImagesWorkType.add(rdbtnMergedImagesWorkTypePNG);
		
		JRadioButton rdbtnMergedImagesWorkTypeTIFF = new JRadioButton(".TIFF");
		rdbtnMergedImagesWorkTypeTIFF.setFont(new Font("STHeiti", Font.PLAIN, 13));
		rdbtnMergedImagesWorkTypeTIFF.setBackground(Color.WHITE);
		rdbtnMergedImagesWorkTypeTIFF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				type = "TIFF";
				rdbtnMergedImagesWorkBpp32.setEnabled(true);
				rdbtnMergedImagesWorkBpp16.setEnabled(true);
			}
		});
		rdbtnMergedImagesWorkTypeTIFF.setBounds(10, 140, 110, 30);
		plMergedImagesWorkType.add(rdbtnMergedImagesWorkTypeTIFF);
		saveAs.add(rdbtnMergedImagesWorkTypeJPG);
		saveAs.add(rdbtnMergedImagesWorkTypeJPEG);
		saveAs.add(rdbtnMergedImagesWorkTypeBMP);
		saveAs.add(rdbtnMergedImagesWorkTypePNG);
		saveAs.add(rdbtnMergedImagesWorkTypeTIFF);
		
		
		final JButton btnMergedImagesWorkDeleteAll = new JButton("Delete all");
		btnMergedImagesWorkDeleteAll.setContentAreaFilled(false);
		btnMergedImagesWorkDeleteAll.setFocusPainted(false);
		btnMergedImagesWorkDeleteAll.setFont(new Font("STHeiti", Font.PLAIN, 17));
		btnMergedImagesWorkDeleteAll.setBorder(new LineBorder(new Color(0, 153, 255), 2));
		btnMergedImagesWorkDeleteAll.setBounds(177, 546, 88, 40);
		plMergedImagesWork.add(btnMergedImagesWorkDeleteAll);
		
		final JButton btnMergedImagesWorkSaveAll = new JButton("Save all");
		btnMergedImagesWorkSaveAll.setContentAreaFilled(false);
		btnMergedImagesWorkSaveAll.setFocusPainted(false);
		btnMergedImagesWorkSaveAll.setFont(new Font("STHeiti", Font.PLAIN, 17));
		btnMergedImagesWorkSaveAll.setBorder(new LineBorder(new Color(0, 153, 255), 2));
		btnMergedImagesWorkSaveAll.setBounds(6, 399, 259, 35);
		plMergedImagesWork.add(btnMergedImagesWorkSaveAll);
		
		lblbtnMergedImagesWorkSave = new JLabel("");
		lblbtnMergedImagesWorkSave.setBounds(6, 440, 259, 100);
		plMergedImagesWork.add(lblbtnMergedImagesWorkSave);
		lblbtnMergedImagesWorkSave.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnSave.jpg")));
		
		final JLabel lblbtnMergedImagesWorkDelete = new JLabel("");
		lblbtnMergedImagesWorkDelete.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnDelete.jpg")));
		lblbtnMergedImagesWorkDelete.setBounds(6, 546, 165, 40);
		plMergedImagesWork.add(lblbtnMergedImagesWorkDelete);
		
		final JLabel lblbtnMergedImagesWorkDeleteAll = new JLabel("");
		lblbtnMergedImagesWorkDeleteAll.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnDeleteAll.jpg")));
		lblbtnMergedImagesWorkDeleteAll.setBounds(177, 546, 88, 40);
		plMergedImagesWork.add(lblbtnMergedImagesWorkDeleteAll);
		
		final JLabel lblbtnMergedImagesWorkSaveAll = new JLabel("");
		lblbtnMergedImagesWorkSaveAll.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnSave.jpg")));
		lblbtnMergedImagesWorkSaveAll.setBounds(6, 399, 259, 35);
		plMergedImagesWork.add(lblbtnMergedImagesWorkSaveAll);
		
		
		final JPanel plShade = new JPanel();
		plShade.setVisible(false);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		menuBar.setBounds(0, 0, 1366, 20);
		frmMicromerge.getContentPane().add(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mnMenu.add(mntmNew);
		
		JMenuItem mntmExit = new JMenuItem("Exit");

		mnMenu.add(mntmExit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmFaq = new JMenuItem("FAQ");
		mnHelp.add(mntmFaq);
		
		JMenuItem mntmBugs = new JMenuItem("Bugs");
		mnHelp.add(mntmBugs);
		
		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
		JMenuItem mntmCreators = new JMenuItem("Authors");
		mnAbout.add(mntmCreators);
		
		JMenuItem mntmThanks = new JMenuItem("Thanks");
		mnAbout.add(mntmThanks);
		
		JMenuItem mntmProgram = new JMenuItem("MicroMerge");
		mnAbout.add(mntmProgram);
		
		
		plShade.setBounds(0, 20, 1366, 660);
		frmMicromerge.getContentPane().add(plShade);
		plShade.setBackground(SystemColor.inactiveCaption);
		plShade.setLayout(null);
		
		final JPanel plShadeData = new JPanel();
		plShadeData.setBorder(new LineBorder(new Color(0, 153, 255), 4));
		plShadeData.setBackground(Color.WHITE);
		plShadeData.setVisible(false);
		
		final JButton btnShadeCancel = new JButton("CANCEL");
		btnShadeCancel.setBorder(new LineBorder(new Color(0, 153, 255), 2));
		btnShadeCancel.setFocusPainted(false);
		btnShadeCancel.setContentAreaFilled(false);
		btnShadeCancel.setBackground(Color.WHITE);
		btnShadeCancel.setForeground(new Color(0, 153, 255));
		btnShadeCancel.setBounds(1086, 280, 259, 50);
		plShade.add(btnShadeCancel);
		
		final JLabel lblbtnShadeCancel  = new JLabel();
		lblbtnShadeCancel.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnSave.jpg")));
		lblbtnShadeCancel.setSize(259, 50);
		lblbtnShadeCancel.setLocation(1086, 280);
		lblbtnShadeCancel.setVisible(true);
		plShade.add(lblbtnShadeCancel);
		plShadeData.setBounds(0, 60, 1366, 480);
		plShade.add(plShadeData);
		plShadeData.setLayout(null);
		
		final JLabel lblShadeTitle = new JLabel("");
		lblShadeTitle.setBackground(Color.WHITE);
		lblShadeTitle.setBounds(6, 6, 1080, 50);
		plShadeData.add(lblShadeTitle);
		lblShadeTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblShadeTitle.setForeground(Color.WHITE);
		lblShadeTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		
		final JTextPane txtpnShadeDataFaq = new JTextPane();
		txtpnShadeDataFaq.setBorder(new LineBorder(new Color(153, 0, 0), 2));
		txtpnShadeDataFaq.setVisible(false);
		txtpnShadeDataFaq.setText("Q: What does these letters in Shading Direction mean?\nA: Hover mouse over each abbreviation, you will see a tip.");
		txtpnShadeDataFaq.setEditable(false);
		txtpnShadeDataFaq.setBounds(296, 80, 500, 380);
		plShadeData.add(txtpnShadeDataFaq);
		
		final JTextPane txtpnShadeDataBugs = new JTextPane();
		txtpnShadeDataBugs.setBorder(new LineBorder(new Color(153, 0, 0), 2));
		txtpnShadeDataBugs.setText("There are no known bugs.\nIf you notice any, please, wrtie to us.");
		txtpnShadeDataBugs.setEditable(false);
		txtpnShadeDataBugs.setVisible(false);
		txtpnShadeDataBugs.setBounds(296, 80, 500, 380);
		plShadeData.add(txtpnShadeDataBugs);
		
		final JTextPane txtpnShadeDataAboutUs = new JTextPane();
		txtpnShadeDataAboutUs.setBorder(new LineBorder(new Color(0, 153, 255), 2));
		txtpnShadeDataAboutUs.setVisible(false);
		txtpnShadeDataAboutUs.setEditable(false);
		txtpnShadeDataAboutUs.setText("Students of Warsaw University of Technology:\nMikołaj Słoń\nMateusz Szperna\nKornel Żaba");
		txtpnShadeDataAboutUs.setBounds(296, 80, 500, 380);
		plShadeData.add(txtpnShadeDataAboutUs);
		
		final JTextPane txtpnShadeDataProgram = new JTextPane();
		txtpnShadeDataProgram.setBorder(new LineBorder(new Color(0, 153, 255), 2));
		txtpnShadeDataProgram.setVisible(false);
		txtpnShadeDataProgram.setText("MicroMerge Beta 0.99");
		txtpnShadeDataProgram.setEditable(false);
		txtpnShadeDataProgram.setBounds(296, 80, 500, 380);
		plShadeData.add(txtpnShadeDataProgram);
		
		final JTextPane txtpnShadeDataThanks = new JTextPane();
		txtpnShadeDataThanks.setBorder(new LineBorder(new Color(0, 153, 255), 2));
		txtpnShadeDataThanks.setVisible(false);
		txtpnShadeDataThanks.setText("Special thanks to our friends who helped us to create this wonderful program - MicroMerge! :)");
		txtpnShadeDataThanks.setEditable(false);
		txtpnShadeDataThanks.setBounds(296, 80, 500, 380);
		plShadeData.add(txtpnShadeDataThanks);
		
		JButton btnShadeBackUp = new JButton("");
		btnShadeBackUp.setFocusPainted(false);
		btnShadeBackUp.setBorderPainted(false);
		btnShadeBackUp.setContentAreaFilled(false);
		btnShadeBackUp.setFocusable(false);
		btnShadeBackUp.setRequestFocusEnabled(false);
		btnShadeBackUp.setBackground(new Color(105, 105, 105));
		btnShadeBackUp.setBounds(0, 0, 1366, 60);
		plShade.add(btnShadeBackUp);
		
		JButton btnShadeBackBottom = new JButton("");
		btnShadeBackBottom.setBackground(Color.WHITE);
		btnShadeBackBottom.setBorderPainted(false);
		btnShadeBackBottom.setRequestFocusEnabled(false);
		btnShadeBackBottom.setFocusable(false);
		btnShadeBackBottom.setBounds(0, 540, 1366, 100);
		plShade.add(btnShadeBackBottom);
		
		for(;numberofdirs<20;numberofdirs++)
		{
			listDirs.add(new JTextField());
			plOperationsListPath.add(listDirs.get(numberofdirs));
			listDirs.get(numberofdirs).setColumns(10);
			listDirs.get(numberofdirs).setVisible(false);
		}

btnOperationsOperations.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		plOperations.setVisible(true);
		plMergedImages.setVisible(false);
		btnOperationsOperations.setForeground(new Color(255, 255, 255));
		btnOperationsMergedImages.setForeground(new Color(0,0,0));
		lblbtnOperationsOperations.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnOperationsA.jpg")));	
		lblbtnOperationsMergedImages.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnMergedImages.jpg")));	
		plMenu.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 153, 0)));
	}
});


btnOperationsMergedImages.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		plOperations.setVisible(false);
		plMergedImages.setVisible(true);
		btnOperationsOperations.setForeground(new Color(0,0,0));
		btnOperationsMergedImages.setForeground(new Color(255, 255, 255));
		lblbtnOperationsOperations.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnMergedImages.jpg")));	
		lblbtnOperationsMergedImages.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnMergedImagesA.jpg")));	
		plMenu.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 153, 255)));
	}
});

mntmExit.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		frmMicromerge.dispose();
	}
});

mntmFaq.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		plMain.setVisible(false);
		plMenu.setVisible(false);
		plShade.setVisible(true);
		plShadeData.setVisible(true);
		plShadeData.setBorder(new LineBorder(new Color(153, 0, 0), 4));
		btnShadeCancel.setForeground(new Color(153, 0, 0));
		btnShadeCancel.setBorder(new LineBorder(new Color(153, 0, 0), 2));
		txtpnShadeDataFaq.setVisible(true);
		txtpnShadeDataBugs.setVisible(false);
		txtpnShadeDataAboutUs.setVisible(false);
		txtpnShadeDataThanks.setVisible(false);
		txtpnShadeDataProgram.setVisible(false);
		lblShadeTitle.setText("FAQ");
		lblShadeTitle.setForeground(new Color(153, 0, 0));
	}
});

mntmBugs.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		plMain.setVisible(false);
		plMenu.setVisible(false);
		plShade.setVisible(true);
		plShadeData.setVisible(true);
		plShadeData.setBorder(new LineBorder(new Color(153, 0, 0), 4));
		btnShadeCancel.setForeground(new Color(153, 0, 0));
		btnShadeCancel.setBorder(new LineBorder(new Color(153, 0, 0), 2));
		txtpnShadeDataFaq.setVisible(false);
		txtpnShadeDataBugs.setVisible(true);
		txtpnShadeDataAboutUs.setVisible(false);
		txtpnShadeDataThanks.setVisible(false);
		txtpnShadeDataProgram.setVisible(false);
		lblShadeTitle.setText("Bugs");
		lblShadeTitle.setForeground(new Color(153, 0, 0));
	}
});

mntmCreators.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		plMain.setVisible(false);
		plMenu.setVisible(false);
		plShade.setVisible(true);
		plShadeData.setVisible(true);
		plShadeData.setBorder(new LineBorder(new Color(0, 153, 255), 4));
		btnShadeCancel.setForeground(new Color(0, 153, 255));
		btnShadeCancel.setBorder(new LineBorder(new Color(0, 153, 255), 2));
		txtpnShadeDataFaq.setVisible(false);
		txtpnShadeDataBugs.setVisible(false);
		txtpnShadeDataAboutUs.setVisible(true);
		txtpnShadeDataThanks.setVisible(false);
		txtpnShadeDataProgram.setVisible(false);
		lblShadeTitle.setText("About Us");
		lblShadeTitle.setForeground(new Color(0, 153, 255));
	}
});

mntmThanks.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		plMain.setVisible(false);
		plMenu.setVisible(false);
		plShade.setVisible(true);
		plShadeData.setVisible(true);
		plShadeData.setBorder(new LineBorder(new Color(0, 153, 255), 4));
		btnShadeCancel.setForeground(new Color(0, 153, 255));
		btnShadeCancel.setBorder(new LineBorder(new Color(0, 153, 255), 2));
		txtpnShadeDataFaq.setVisible(false);
		txtpnShadeDataBugs.setVisible(false);
		txtpnShadeDataAboutUs.setVisible(false);
		txtpnShadeDataThanks.setVisible(true);
		txtpnShadeDataProgram.setVisible(false);
		lblShadeTitle.setText("Special thanks");
		lblShadeTitle.setForeground(new Color(0, 153, 255));
	}
});

mntmProgram.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		plMain.setVisible(false);
		plMenu.setVisible(false);
		plShade.setVisible(true);
		plShadeData.setVisible(true);
		plShadeData.setBorder(new LineBorder(new Color(0, 153, 255), 4));
		btnShadeCancel.setForeground(new Color(0, 153, 255));
		btnShadeCancel.setBorder(new LineBorder(new Color(0, 153, 255), 2));
		txtpnShadeDataFaq.setVisible(false);
		txtpnShadeDataBugs.setVisible(false);
		txtpnShadeDataAboutUs.setVisible(false);
		txtpnShadeDataThanks.setVisible(false);
		txtpnShadeDataProgram.setVisible(true);
		lblShadeTitle.setText("MicroMerge");
		lblShadeTitle.setForeground(new Color(0, 153, 255));
	}
});


mntmNew.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		for(int i =0 ; i<tbListMergedImages.getComponentCount();i++)
		{
			JButton button =(JButton)tbListMergedImages.getComponent(i);
			BufferedImage img = (BufferedImage)button.getClientProperty("image");		//clearing resources
			img.flush();
		}
		tbListImages.removeAll();
		tbListToMerge.removeAll();
		listImgs.clear();													//removing not needed content
		listToMerge.clear();
		tbListMergedImages.removeAll();
		btnMergedImagesViewView.setIcon(null);
		j=0;
		for(int i =0;i<listDirs.size();i++)
		{
			listDirs.get(i).setText("");
			listDirs.get(i).setVisible(false);								//clearing directory view
		}
		plOperationsListImagesOptions.setVisible(false);
		scrollPaneListImages.setBounds(312, 6, 774, 409);
		frmMicromerge.revalidate();
		frmMicromerge.repaint();
	}
});


btnShadeBackUp.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		plMain.setVisible(true);
		plMenu.setVisible(true);
		plShade.setVisible(false);
	}
});

btnShadeBackBottom.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		plMain.setVisible(true);
		plMenu.setVisible(true);
		plShade.setVisible(false);
	}
});

btnShadeCancel.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		plMain.setVisible(true);
		plMenu.setVisible(true);
		plShade.setVisible(false);
	}
});


btnShadeCancel.addMouseListener(new MouseAdapter() {
	@Override
	public void mouseEntered(MouseEvent e) {
		if ((txtpnShadeDataFaq.isVisible() == false) && (txtpnShadeDataBugs.isVisible() == false))
		{
			btnShadeCancel.setForeground(new Color(0, 102, 204));
			lblbtnShadeCancel.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnSaveH.jpg")));
		}
		else
		{
			btnShadeCancel.setForeground(new Color(0, 0, 0));
			lblbtnShadeCancel.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnBackShadeH.jpg")));
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		if ((txtpnShadeDataFaq.isVisible() == false) && (txtpnShadeDataBugs.isVisible() == false))
		{
			btnShadeCancel.setForeground(new Color(0, 153, 255));
			lblbtnShadeCancel.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnSave.jpg")));
		}
		else
		{
			btnShadeCancel.setForeground(new Color(153, 0, 0));
			lblbtnShadeCancel.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnSave.jpg")));
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if ((txtpnShadeDataFaq.isVisible() == false) && (txtpnShadeDataBugs.isVisible() == false))
		{
			btnShadeCancel.setForeground(new Color(255, 255, 255));
			lblbtnShadeCancel.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnSaveC.jpg")));
		}
		else
		{
			btnShadeCancel.setForeground(new Color(153, 0, 0));
			lblbtnShadeCancel.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnBackShadeC.jpg")));
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if ((txtpnShadeDataFaq.isVisible() == false) && (txtpnShadeDataBugs.isVisible() == false))
		{
			btnShadeCancel.setForeground(new Color(0, 153, 255));
			lblbtnShadeCancel.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnSave.jpg")));
		}
		else
		{
			btnShadeCancel.setForeground(new Color(153, 0, 0));
			lblbtnShadeCancel.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnSave.jpg")));
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if ((txtpnShadeDataFaq.isVisible() == false) && (txtpnShadeDataBugs.isVisible() == false))
		{
			btnShadeCancel.setForeground(new Color(0, 153, 255));
			lblbtnShadeCancel.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnSave.jpg")));
		}
		else
		{
			btnShadeCancel.setForeground(new Color(0, 0, 0));
			lblbtnShadeCancel.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnSave.jpg")));
		}
	}
});


btnOperationsMergedImages.addMouseListener(new MouseAdapter() {
	@Override
	public void mouseEntered(MouseEvent e) {
		if (plMergedImages.isVisible() == false)
		{
			btnOperationsMergedImages.setForeground(new Color(0, 102, 204));
			lblbtnOperationsMergedImages.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnMergedImagesH.jpg")));
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		if (plMergedImages.isVisible() == false)
		{
			btnOperationsMergedImages.setForeground(new Color(0, 0, 0));
			lblbtnOperationsMergedImages.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnMergedImages.jpg")));
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if (plMergedImages.isVisible() == false)
		{
			btnOperationsMergedImages.setForeground(new Color(255, 255, 255));
			lblbtnOperationsMergedImages.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnMergedImagesC.jpg")));
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if (plMergedImages.isVisible() == false)
		{
			btnOperationsMergedImages.setForeground(new Color(0, 0, 0));
			lblbtnOperationsMergedImages.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnMergedImages.jpg")));
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if (plMergedImages.isVisible() == false)
		{
			btnOperationsMergedImages.setForeground(new Color(0, 0, 0));
			lblbtnOperationsMergedImages.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnMergedImages.jpg")));
		}
	}
});

btnOperationsOperations.addMouseListener(new MouseAdapter() {
	@Override
	public void mouseEntered(MouseEvent e) {
		if (plOperations.isVisible() == false)
		{
			btnOperationsOperations.setForeground(new Color(0, 102, 51));
			lblbtnOperationsOperations.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnOperationsH.jpg")));
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		if (plOperations.isVisible() == false)
		{
			btnOperationsOperations.setForeground(new Color(0, 0, 0));
			lblbtnOperationsOperations.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnMergedImages.jpg")));
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if (plOperations.isVisible() == false)
		{
			btnOperationsOperations.setForeground(new Color(255, 255, 255));
			lblbtnOperationsOperations.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnOperationsC.jpg")));
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if (plOperations.isVisible() == false)
		{
			btnOperationsOperations.setForeground(new Color(0, 0, 0));
			lblbtnOperationsOperations.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnMergedImages.jpg")));
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if (plOperations.isVisible() == false)
		{
			btnOperationsOperations.setForeground(new Color(0, 0, 0));
			lblbtnOperationsOperations.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnMergedImages.jpg")));
		}
	}
});


btnOperationsWorkLoad.addMouseListener(new MouseAdapter() {
	@Override
	public void mouseEntered(MouseEvent e) {
		btnOperationsWorkLoad.setBorder(new LineBorder(new Color(230, 230, 230), 1));
		btnOperationsWorkLoad.setForeground(new Color(0, 102, 51));
		lblbtnOperationsWorkLoad.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnLoadH.jpg")));
	}
	@Override
	public void mouseExited(MouseEvent e) {
		btnOperationsWorkLoad.setBorder(new LineBorder(new Color(0, 153, 0), 2));
		btnOperationsWorkLoad.setForeground(new Color(0, 153, 0));
		lblbtnOperationsWorkLoad.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnLoad.jpg")));
	}
	@Override
	public void mousePressed(MouseEvent e) {
		btnOperationsWorkLoad.setBorder(new LineBorder(new Color(0, 102, 51), 2));
		btnOperationsWorkLoad.setForeground(new Color(255,255,255));
		lblbtnOperationsWorkLoad.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnLoadC.jpg")));
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		btnOperationsWorkLoad.setBorder(new LineBorder(new Color(0, 153, 0), 2));
		btnOperationsWorkLoad.setForeground(new Color(0, 153, 0));
		lblbtnOperationsWorkLoad.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnLoad.jpg")));
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		btnOperationsWorkLoad.setBorder(new LineBorder(new Color(0, 153, 0), 2));
		btnOperationsWorkLoad.setForeground(new Color(0, 153, 0));
		lblbtnOperationsWorkLoad.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnLoad.jpg")));
	}
	
});

btnOperationsWorkRefresh.addMouseListener(new MouseAdapter() {
	@Override
	public void mouseEntered(MouseEvent e) {
		btnOperationsWorkRefresh.setBorder(new LineBorder(new Color(230, 230, 230), 1));
		btnOperationsWorkRefresh.setForeground(new Color(0, 102, 51));
		lblbtnOperationsWorkRefresh.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnRefreshH.jpg")));
	}
	@Override
	public void mouseExited(MouseEvent e) {
		btnOperationsWorkRefresh.setBorder(new LineBorder(new Color(0, 153, 0), 2));
		btnOperationsWorkRefresh.setForeground(new Color(0, 0, 0));
		lblbtnOperationsWorkRefresh.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnRefresh.jpg")));
	}
	@Override
	public void mousePressed(MouseEvent e) {
		btnOperationsWorkRefresh.setBorder(new LineBorder(new Color(0, 102, 51), 2));
		btnOperationsWorkRefresh.setForeground(new Color(255,255,255));
		lblbtnOperationsWorkRefresh.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnRefreshC.jpg")));
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		btnOperationsWorkRefresh.setBorder(new LineBorder(new Color(0, 153, 0), 2));
		btnOperationsWorkRefresh.setForeground(new Color(0, 0, 0));
		lblbtnOperationsWorkRefresh.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnRefresh.jpg")));
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		btnOperationsWorkRefresh.setBorder(new LineBorder(new Color(0, 153, 0), 2));
		btnOperationsWorkRefresh.setForeground(new Color(0, 0, 0));
		lblbtnOperationsWorkRefresh.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnRefresh.jpg")));
	}
	
});
btnOperationsWorkClearAll.addMouseListener(new MouseAdapter() {
	@Override
	public void mouseEntered(MouseEvent e) {
		btnOperationsWorkClearAll.setBorder(new LineBorder(new Color(230, 230, 230), 1));
		btnOperationsWorkClearAll.setForeground(new Color(0, 102, 51));
		lblbtnOperationsWorkClearAll.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnClearAllH.jpg")));
	}
	@Override
	public void mouseExited(MouseEvent e) {
		btnOperationsWorkClearAll.setBorder(new LineBorder(new Color(0, 153, 0), 2));
		btnOperationsWorkClearAll.setForeground(new Color(0, 0, 0));
		lblbtnOperationsWorkClearAll.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnClearAll.jpg")));
	}
	@Override
	public void mousePressed(MouseEvent e) {
		btnOperationsWorkClearAll.setBorder(new LineBorder(new Color(0, 102, 51), 2));
		btnOperationsWorkClearAll.setForeground(new Color(255,255,255));
		lblbtnOperationsWorkClearAll.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnClearAllC.jpg")));
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		btnOperationsWorkClearAll.setBorder(new LineBorder(new Color(0, 153, 0), 2));
		btnOperationsWorkClearAll.setForeground(new Color(0, 0, 0));
		lblbtnOperationsWorkClearAll.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnClearAll.jpg")));
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		btnOperationsWorkClearAll.setBorder(new LineBorder(new Color(0, 153, 0), 2));
		btnOperationsWorkClearAll.setForeground(new Color(0, 0, 0));
		lblbtnOperationsWorkClearAll.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnClearAll.jpg")));
	}
	
});

btnOperationsWorkMergeAll.addMouseListener(new MouseAdapter() {
	@Override
	public void mouseEntered(MouseEvent e) {
		btnOperationsWorkMergeAll.setBorder(new LineBorder(new Color(230, 230, 230), 1));
		btnOperationsWorkMergeAll.setForeground(new Color(0, 102, 51));
		lblbtnOperationsWorkMergeAll.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnMergeAllH.jpg")));
	}
	@Override
	public void mouseExited(MouseEvent e) {
		btnOperationsWorkMergeAll.setBorder(new LineBorder(new Color(0, 153, 0), 2));
		btnOperationsWorkMergeAll.setForeground(new Color(0, 0, 0));
		lblbtnOperationsWorkMergeAll.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnMergeAll.jpg")));
	}
	@Override
	public void mousePressed(MouseEvent e) {
		btnOperationsWorkMergeAll.setBorder(new LineBorder(new Color(0, 102, 51), 2));
		btnOperationsWorkMergeAll.setForeground(new Color(255,255,255));
		lblbtnOperationsWorkMergeAll.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnMergeAllC.jpg")));
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		btnOperationsWorkMergeAll.setBorder(new LineBorder(new Color(0, 153, 0), 2));
		btnOperationsWorkMergeAll.setForeground(new Color(0, 0, 0));
		lblbtnOperationsWorkMergeAll.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnMergeAll.jpg")));
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		btnOperationsWorkMergeAll.setBorder(new LineBorder(new Color(0, 153, 0), 2));
		btnOperationsWorkMergeAll.setForeground(new Color(0, 0, 0));
		lblbtnOperationsWorkMergeAll.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnMergeAll.jpg")));
	}
});

btnOperationsWorkMerge.addMouseListener(new MouseAdapter() {
	@Override
	public void mouseEntered(MouseEvent e) {
		btnOperationsWorkMerge.setBorder(new LineBorder(new Color(230, 230, 230), 1));
		btnOperationsWorkMerge.setForeground(new Color(0, 102, 51));
		lblbtnOperationsWorkMerge.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnMergeH.jpg")));
	}
	@Override
	public void mouseExited(MouseEvent e) {
		btnOperationsWorkMerge.setBorder(new LineBorder(new Color(0, 153, 0), 2));
		btnOperationsWorkMerge.setForeground(new Color(0, 153, 0));
		lblbtnOperationsWorkMerge.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnMerge.jpg")));
	}
	@Override
	public void mousePressed(MouseEvent e) {
		btnOperationsWorkMerge.setBorder(new LineBorder(new Color(0, 102, 51), 2));
		btnOperationsWorkMerge.setForeground(new Color(255,255,255));
		lblbtnOperationsWorkMerge.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnMergeC.jpg")));
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		btnOperationsWorkMerge.setBorder(new LineBorder(new Color(0, 153, 0), 2));
		btnOperationsWorkMerge.setForeground(new Color(0, 153, 0));
		lblbtnOperationsWorkMerge.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnMerge.jpg")));
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		btnOperationsWorkMerge.setBorder(new LineBorder(new Color(0, 153, 0), 2));
		btnOperationsWorkMerge.setForeground(new Color(0, 153, 0));
		lblbtnOperationsWorkMerge.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnMerge.jpg")));
	}	
});


btnOperationsListImageAdd.addMouseListener(new MouseAdapter() {
	@Override
	public void mouseEntered(MouseEvent e) {
		btnOperationsListImageAdd.setBorder(new LineBorder(new Color(230, 230, 230), 1));
		btnOperationsListImageAdd.setForeground(new Color(0, 102, 51));
		lblbtnOperationsListImageAdd.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnRefreshH.jpg")));
	}
	@Override
	public void mouseExited(MouseEvent e) {
		btnOperationsListImageAdd.setBorder(new LineBorder(new Color(0, 153, 255), 2));
		btnOperationsListImageAdd.setForeground(new Color(0, 0, 0));
		lblbtnOperationsListImageAdd.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnRefresh.jpg")));
	}
	@Override
	public void mousePressed(MouseEvent e) {
		btnOperationsListImageAdd.setBorder(new LineBorder(new Color(0, 104, 204), 2));
		btnOperationsListImageAdd.setForeground(new Color(255,255,255));
		lblbtnOperationsListImageAdd.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnRefreshC.jpg")));
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		btnOperationsListImageAdd.setBorder(new LineBorder(new Color(0, 153, 255), 2));
		btnOperationsListImageAdd.setForeground(new Color(0, 0, 0));
		lblbtnOperationsListImageAdd.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnRefresh.jpg")));
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		btnOperationsListImageAdd.setBorder(new LineBorder(new Color(0, 153, 255), 2));
		btnOperationsListImageAdd.setForeground(new Color(0, 0, 0));
		lblbtnOperationsListImageAdd.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnRefresh.jpg")));
	}	
});

btnOperationsListImagePreview.addMouseListener(new MouseAdapter() {
	@Override
	public void mouseEntered(MouseEvent e) {
		btnOperationsListImagePreview.setBorder(new LineBorder(new Color(230, 230, 230), 1));
		btnOperationsListImagePreview.setForeground(new Color(0, 102, 51));
		lblbtnOperationsListImagePreview.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnRefreshH.jpg")));
	}
	@Override
	public void mouseExited(MouseEvent e) {
		btnOperationsListImagePreview.setBorder(new LineBorder(new Color(0, 153, 255), 2));
		btnOperationsListImagePreview.setForeground(new Color(0, 0, 0));
		lblbtnOperationsListImagePreview.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnRefresh.jpg")));
	}
	@Override
	public void mousePressed(MouseEvent e) {
		btnOperationsListImagePreview.setBorder(new LineBorder(new Color(0, 104, 204), 2));
		btnOperationsListImagePreview.setForeground(new Color(255,255,255));
		lblbtnOperationsListImagePreview.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnRefreshC.jpg")));
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		btnOperationsListImagePreview.setBorder(new LineBorder(new Color(0, 153, 255), 2));
		btnOperationsListImagePreview.setForeground(new Color(0, 0, 0));
		lblbtnOperationsListImagePreview.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnRefresh.jpg")));
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		btnOperationsListImagePreview.setBorder(new LineBorder(new Color(0, 153, 255), 2));
		btnOperationsListImagePreview.setForeground(new Color(0, 0, 0));
		lblbtnOperationsListImagePreview.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnRefresh.jpg")));
	}
	
});

btnOperationsPreviewWorkBack.addMouseListener(new MouseAdapter() {
	@Override
	public void mouseEntered(MouseEvent e) {
		btnOperationsPreviewWorkBack.setBorder(new LineBorder(new Color(230, 230, 230), 1));
		btnOperationsPreviewWorkBack.setForeground(new Color(0, 102, 51));
		lblbtnOperationsPreviewWorkBack.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnMergeAllH.jpg")));
	}
	@Override
	public void mouseExited(MouseEvent e) {
		btnOperationsPreviewWorkBack.setBorder(new LineBorder(new Color(0, 153, 0), 2));
		btnOperationsPreviewWorkBack.setForeground(new Color(0, 0, 0));
		lblbtnOperationsPreviewWorkBack.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnMergeAll.jpg")));
	}
	@Override
	public void mousePressed(MouseEvent e) {
		btnOperationsPreviewWorkBack.setBorder(new LineBorder(new Color(0, 102, 51), 2));
		btnOperationsPreviewWorkBack.setForeground(new Color(255,255,255));
		lblbtnOperationsPreviewWorkBack.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnMergeAllC.jpg")));
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		btnOperationsPreviewWorkBack.setBorder(new LineBorder(new Color(0, 153, 0), 2));
		btnOperationsPreviewWorkBack.setForeground(new Color(0, 0, 0));
		lblbtnOperationsPreviewWorkBack.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnMergeAll.jpg")));
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		btnOperationsPreviewWorkBack.setBorder(new LineBorder(new Color(0, 153, 0), 2));
		btnOperationsPreviewWorkBack.setForeground(new Color(0, 0, 0));
		lblbtnOperationsPreviewWorkBack.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnMergeAll.jpg")));
	}
});





btnMergedImagesWorkSave.addMouseListener(new MouseAdapter() {
	@Override
	public void mouseEntered(MouseEvent e) {
		btnMergedImagesWorkSave.setBorder(new LineBorder(new Color(230, 230, 230), 2));
		btnMergedImagesWorkSave.setForeground(new Color(0, 104, 204));
		lblbtnMergedImagesWorkSave.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnSaveH.jpg")));
	}
	@Override
	public void mouseExited(MouseEvent e) {
		btnMergedImagesWorkSave.setBorder(new LineBorder(new Color(0, 153, 255), 4));
		btnMergedImagesWorkSave.setForeground(new Color(0, 153, 255));
		lblbtnMergedImagesWorkSave.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnSave.jpg")));
	}
	@Override
	public void mousePressed(MouseEvent e) {
		btnMergedImagesWorkSave.setBorder(new LineBorder(new Color(0, 104, 204), 4));
		btnMergedImagesWorkSave.setForeground(new Color(255,255,255));
		lblbtnMergedImagesWorkSave.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnSaveC.jpg")));
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		btnMergedImagesWorkSave.setBorder(new LineBorder(new Color(0, 153, 255), 4));
		btnMergedImagesWorkSave.setForeground(new Color(0, 153, 255));
		lblbtnMergedImagesWorkSave.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnSave.jpg")));
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		btnMergedImagesWorkSave.setBorder(new LineBorder(new Color(0, 153, 255), 4));
		btnMergedImagesWorkSave.setForeground(new Color(0, 153, 255));
		lblbtnMergedImagesWorkSave.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnSave.jpg")));
	}
});

btnMergedImagesWorkSaveAll.addMouseListener(new MouseAdapter() {
	@Override
	public void mouseEntered(MouseEvent e) {
		btnMergedImagesWorkSaveAll.setBorder(new LineBorder(new Color(230, 230, 230), 1));
		btnMergedImagesWorkSaveAll.setForeground(new Color(0, 104, 204));
		lblbtnMergedImagesWorkSaveAll.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnSaveAllH.jpg")));
	}
	@Override
	public void mouseExited(MouseEvent e) {
		btnMergedImagesWorkSaveAll.setBorder(new LineBorder(new Color(0, 153, 255), 2));
		btnMergedImagesWorkSaveAll.setForeground(new Color(0, 0, 0));
		lblbtnMergedImagesWorkSaveAll.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnSaveAll.jpg")));
	}
	@Override
	public void mousePressed(MouseEvent e) {
		btnMergedImagesWorkSaveAll.setBorder(new LineBorder(new Color(0, 104, 204), 2));
		btnMergedImagesWorkSaveAll.setForeground(new Color(255,255,255));
		lblbtnMergedImagesWorkSaveAll.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnSaveAllC.jpg")));
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		btnMergedImagesWorkSaveAll.setBorder(new LineBorder(new Color(0, 153, 255), 2));
		btnMergedImagesWorkSaveAll.setForeground(new Color(0, 0, 0));
		lblbtnMergedImagesWorkSaveAll.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnSaveAll.jpg")));
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		btnMergedImagesWorkSaveAll.setBorder(new LineBorder(new Color(0, 153, 255), 2));
		btnMergedImagesWorkSaveAll.setForeground(new Color(0, 0, 0));
		lblbtnMergedImagesWorkSaveAll.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnSaveAll.jpg")));
	}
	
});

btnMergedImagesWorkDelete.addMouseListener(new MouseAdapter() {
	@Override
	public void mouseEntered(MouseEvent e) {
		btnMergedImagesWorkDelete.setBorder(new LineBorder(new Color(230, 230, 230), 1));
		btnMergedImagesWorkDelete.setForeground(new Color(0, 104, 204));
		lblbtnMergedImagesWorkDelete.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnDeleteH.jpg")));
	}
	@Override
	public void mouseExited(MouseEvent e) {
		btnMergedImagesWorkDelete.setBorder(new LineBorder(new Color(0, 153, 255), 2));
		btnMergedImagesWorkDelete.setForeground(new Color(0, 0, 0));
		lblbtnMergedImagesWorkDelete.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnDelete.jpg")));
	}
	@Override
	public void mousePressed(MouseEvent e) {
		btnMergedImagesWorkDelete.setBorder(new LineBorder(new Color(0, 104, 204), 2));
		btnMergedImagesWorkDelete.setForeground(new Color(255,255,255));
		lblbtnMergedImagesWorkDelete.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnDeleteC.jpg")));
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		btnMergedImagesWorkDelete.setBorder(new LineBorder(new Color(0, 153, 255), 2));
		btnMergedImagesWorkDelete.setForeground(new Color(0, 0, 0));
		lblbtnMergedImagesWorkDelete.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnDelete.jpg")));
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		btnMergedImagesWorkDelete.setBorder(new LineBorder(new Color(0, 153, 255), 2));
		btnMergedImagesWorkDelete.setForeground(new Color(0, 0, 0));
		lblbtnMergedImagesWorkDelete.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnDelete.jpg")));
	}
	
});


btnMergedImagesWorkDeleteAll.addMouseListener(new MouseAdapter() {
	@Override
	public void mouseEntered(MouseEvent e) {
		btnMergedImagesWorkDeleteAll.setBorder(new LineBorder(new Color(230, 230, 230), 1));
		btnMergedImagesWorkDeleteAll.setForeground(new Color(0, 104, 204));
		lblbtnMergedImagesWorkDeleteAll.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnDeleteAllH.jpg")));
	}
	@Override
	public void mouseExited(MouseEvent e) {
		btnMergedImagesWorkDeleteAll.setBorder(new LineBorder(new Color(0, 153, 255), 2));
		btnMergedImagesWorkDeleteAll.setForeground(new Color(0, 0, 0));
		lblbtnMergedImagesWorkDeleteAll.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnDeleteAll.jpg")));
	}
	@Override
	public void mousePressed(MouseEvent e) {
		btnMergedImagesWorkDeleteAll.setBorder(new LineBorder(new Color(0, 104, 204), 2));
		btnMergedImagesWorkDeleteAll.setForeground(new Color(255,255,255));
		lblbtnMergedImagesWorkDeleteAll.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnDeleteAllC.jpg")));
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		btnMergedImagesWorkDeleteAll.setBorder(new LineBorder(new Color(0, 153, 255), 2));
		btnMergedImagesWorkDeleteAll.setForeground(new Color(0, 0, 0));
		lblbtnMergedImagesWorkDeleteAll.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnDeleteAll.jpg")));
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		btnMergedImagesWorkDeleteAll.setBorder(new LineBorder(new Color(0, 153, 255), 2));
		btnMergedImagesWorkDeleteAll.setForeground(new Color(0, 0, 0));
		lblbtnMergedImagesWorkDeleteAll.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnDeleteAll.jpg")));
	}
	
});



//funkcje

		btnOperationsWorkRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plOperationsListImagesOptions.setVisible(false);
				scrollPaneListImages.setBounds(312, 6, 774, 409);
				tbListImages.removeAll();									//first i remove all previously loaded images
				listImgs.clear();
				File dir;
				int numberOfDirector =j;
				for(j =0;j<numberOfDirector;j++)							//i restore the number of directories to their previous state
				{
					String dirPath = listDirs.get(j).getText();
					dir = new File(dirPath);
						if (dir.isDirectory()) {
						for (final File f : dir.listFiles(IMAGE_FILTER)) {  //i open each folder seperately and find all images that matches the extensions
							System.out.println(f.getAbsolutePath());
							
							final JButton button = new JButton();
							BufferedImage img = null;
							button.setToolTipText(f.getAbsolutePath());
							try {
								img = ImageIO.read(f);
							} catch (IOException err) {
								// TODO Auto-generated catch block
								err.printStackTrace();
							}
							
							
							BufferedImage resized = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
							Graphics2D g = resized.createGraphics();
							g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);	//I resize the image so it fits nicely into to scrollbar
							g.drawImage(img, 0, 0, 400, 400, 0, 0, img.getWidth(),img.getHeight(), null);
							g.dispose();
							
							
							button.setIcon(new ImageIcon(resized));																//i set the image as an icon of a button
							
							listImgs.add(button);
							
							button.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {													//I add actionlisteners to the created button so that they can be added to merging by clicking on them
									if (plOperationsListImagesOptions.isVisible())
									{
										plOperationsListImagesOptions.setVisible(false);
										scrollPaneListImages.setBounds(312, 6, 774, 409);
									}
									else																						//opening and closing the context menu for images
									{
										plOperationsListImagesOptions.setVisible(true);
										scrollPaneListImages.setBounds(312, 6, 774, 358);
									}
									selectedButton = button;
									int in = (Integer)selectedButton.getClientProperty("selected");
									if(in ==1)																					//used only if the image is selcted to merge
									{
										plOperationsListImagesOptions.setVisible(false);
										BufferedImage img = new BufferedImage(
												selectedButton.getIcon().getIconWidth(),
												selectedButton.getIcon().getIconHeight(),
											    BufferedImage.TYPE_INT_RGB);
											Graphics gr = img.createGraphics();
											// paint the Icon to the BufferedImage.
											selectedButton.getIcon().paintIcon(null, gr, 0,0);
											gr.dispose();
										
										BufferedImage resized = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
										Graphics2D g = resized.createGraphics();
										g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);		//the point here is to get the image back to not selected images, to do so i need to resize it again
										g.drawImage(img, 0, 0, 400, 400, 0, 0, img.getWidth(),img.getHeight(), null);
										g.dispose();
										
										selectedButton.setIcon(new ImageIcon(resized));

										
										tbListImages.add(button);																		//i add the button back to tbListImages
										for ( int i = 0;  i < listToMerge.size(); i++){
								            String tempName = listToMerge.get(i);														//i remove the button from to be merged list
								            if(tempName.equals(selectedButton.getToolTipText())){
								                listToMerge.remove(i);
								            }
								        }
										frmMicromerge.revalidate();
										frmMicromerge.repaint();
										selectedButton.putClientProperty("selected", new Integer(0));
									}
								}
							});
								
							
							resized.flush();
							img.flush();																//i take care of unused resources
							
							
							tbListImages.add(button);	
							
							frmMicromerge.revalidate();															//i update the display
							frmMicromerge.repaint();
							button.putClientProperty("selected", new Integer(0));
						
						}
					}
				}
			}
		});
		
		btnOperationsWorkLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plOperationsListImagesOptions.setVisible(false);
				scrollPaneListImages.setBounds(312, 6, 774, 409);
				JFileChooser fileChooser = new JFileChooser(lastOpenDirectory);
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.setAcceptAllFileFilterUsed(false);				    
					
				
				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					
					File dir = fileChooser.getSelectedFile();								//i use file chooser for selecting the file
					lastOpenDirectory = dir.getAbsolutePath();								//setting this directory to be the new open directory
					
					//System.out.println("  "+j+"  "+j+"  "+j+"  "+j+"  "+j+"  "+j+"  "+j+"  "+j+"  "+j+"  "+j);
					listDirs.get(j).setText(dir.getAbsolutePath());
					listDirs.get(j).setVisible(true);										//i display the selected directory
					j++;
					
					
					if (dir.isDirectory()) {
						for (final File f : dir.listFiles(IMAGE_FILTER)) {
							System.out.println(f.getAbsolutePath());						
							
							final JButton button = new JButton();
							BufferedImage img = null;
							button.setToolTipText(f.getAbsolutePath());						//for every file that matches extensions i read the image as buffered image
							try {
								img = ImageIO.read(f);
							} catch (IOException err) {
								// TODO Auto-generated catch block
								err.printStackTrace();
							}
							
							
							BufferedImage resized = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
							Graphics2D g = resized.createGraphics();
							g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);		//i resize the image
							g.drawImage(img, 0, 0, 400, 400, 0, 0, img.getWidth(),img.getHeight(), null);
							g.dispose();
							
							
							button.setIcon(new ImageIcon(resized));					//i add the image to displayed images
							listImgs.add(button);
							
							
							button.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									if (plOperationsListImagesOptions.isVisible())
									{
										plOperationsListImagesOptions.setVisible(false);
										scrollPaneListImages.setBounds(312, 6, 774, 409);
									}
									else
									{
										plOperationsListImagesOptions.setVisible(true);
										scrollPaneListImages.setBounds(312, 6, 774, 358);
									}
									selectedButton = button;												//selectedButton stores the curently pressed button
									int in = (Integer)selectedButton.getClientProperty("selected");
									if(in ==1)
									{
										plOperationsListImagesOptions.setVisible(false);
										scrollPaneListImages.setBounds(312, 6, 774, 409);
										BufferedImage img = new BufferedImage(
												selectedButton.getIcon().getIconWidth(),
												selectedButton.getIcon().getIconHeight(),
											    BufferedImage.TYPE_INT_RGB);
											Graphics gr = img.createGraphics();
											// paint the Icon to the BufferedImage.
											selectedButton.getIcon().paintIcon(null, gr, 0,0);
											gr.dispose();
										
										BufferedImage resized = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
										Graphics2D g = resized.createGraphics();
										g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);	//resizing the image
										g.drawImage(img, 0, 0, 400, 400, 0, 0, img.getWidth(),img.getHeight(), null);
										g.dispose();
										
										selectedButton.setIcon(new ImageIcon(resized));

										
										tbListImages.add(button);												//i add the button back to not choosen images
										for ( int i = 0;  i < listToMerge.size(); i++){				
								            String tempName = listToMerge.get(i);
								            if(tempName.equals(selectedButton.getToolTipText())){
								                listToMerge.remove(i);											//i remove the button from to be merged list
								            }
								        }
										selectedButton.putClientProperty("selected", new Integer(0));
										frmMicromerge.revalidate();
										frmMicromerge.repaint();														//i update the frame
									}
								}
							});
							
							
							
							
							resized.flush();
							img.flush();																		//i take care of unsused resources
							
							tbListImages.add(button);	
							button.putClientProperty("selected", new Integer(0));								//noting the the image is not selected
							frmMicromerge.revalidate();
							frmMicromerge.repaint();
						}
					}
			   }
			}
		});
		
		
		btnOperationsListImageAdd.addActionListener(new ActionListener() {										//an action listener used to add an image to the "to merge list
			public void actionPerformed(ActionEvent e) {
		
				BufferedImage img = new BufferedImage(
						selectedButton.getIcon().getIconWidth(),
						selectedButton.getIcon().getIconHeight(),
					    BufferedImage.TYPE_INT_RGB);
					Graphics gr = img.createGraphics();
					// paint the Icon to the BufferedImage.
					selectedButton.getIcon().paintIcon(null, gr, 0,0);
					gr.dispose();
				
				BufferedImage resized = new BufferedImage(170, 170, BufferedImage.TYPE_INT_RGB);
				Graphics2D g = resized.createGraphics();
				g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);		//resizing the image
				g.drawImage(img, 0, 0, 170, 170, 0, 0, img.getWidth(),img.getHeight(), null);
				g.dispose();
				
				selectedButton.setIcon(new ImageIcon(resized));															//setting the newly resized image
				
				tbListToMerge.add(selectedButton);
				listToMerge.add(selectedButton.getToolTipText());
				selectedButton.putClientProperty("selected",new Integer(1));
				plOperationsListImagesOptions.setVisible(false);
				scrollPaneListImages.setBounds(312, 6, 774, 409);
				
				frmMicromerge.revalidate();																						//I update the frame
				frmMicromerge.repaint();

			}
		});
		
		
		//starting a preview of selected image
		btnOperationsListImagePreview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			plOperationsImages.setVisible(false);
			plOperationsWork.setVisible(false);
			plOperationsPreview.setVisible(true);																		//opening the preview window
			plOperationsPreviewWork.setVisible(true);
				
			BufferedImage img = new BufferedImage(
					selectedButton.getIcon().getIconWidth(),
					selectedButton.getIcon().getIconHeight(),
				    BufferedImage.TYPE_INT_RGB);
				Graphics gr = img.createGraphics();
				// paint the Icon to the BufferedImage.
				selectedButton.getIcon().paintIcon(null, gr, 0,0);
				gr.dispose();
				
			BufferedImage resized = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = resized.createGraphics();
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);			//resizing the image 
			g.drawImage(img, 0, 0, 800, 600, 0, 0, img.getWidth(),img.getHeight(), null);
			g.dispose();
					
			imgOperationsPreview.setIcon(new ImageIcon(resized));
			imgOperationsPreview.setText("");													
			tfOperationsPreviewWorkPath.setText(selectedButton.getToolTipText());										//displaying the directory
					
			frmMicromerge.revalidate();
			frmMicromerge.repaint();

					
			}
		});
		
		//returning from preview
		btnOperationsPreviewWorkBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plOperationsImages.setVisible(true);
				plOperationsWork.setVisible(true);
				plOperationsPreview.setVisible(false);												//self explanatory
				plOperationsPreviewWork.setVisible(false);
					

				frmMicromerge.revalidate();
				frmMicromerge.repaint();
			}
		});
		
		
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i =0 ; i<tbListMergedImages.getComponentCount();i++)
				{
					JButton button =(JButton)tbListMergedImages.getComponent(i);
					BufferedImage img = (BufferedImage)button.getClientProperty("image");		//clearing resources
					img.flush();
				}
				tbListImages.removeAll();
				tbListToMerge.removeAll();
				listImgs.clear();													//removing not needed content
				listToMerge.clear();
				tbListMergedImages.removeAll();
				btnMergedImagesViewView.setIcon(null);
				j=0;
				for(int i =0;i<listDirs.size();i++)
				{
					listDirs.get(i).setText("");
					listDirs.get(i).setVisible(false);								//clearing directory view
				}
				plOperationsListImagesOptions.setVisible(false);
				scrollPaneListImages.setBounds(312, 6, 774, 409);
				frmMicromerge.revalidate();
				frmMicromerge.repaint();
			}
		});
		
		btnOperationsWorkClearAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tbListImages.removeAll();
				tbListToMerge.removeAll();
				listImgs.clear();
				listToMerge.clear();
				j=0;
				for(int i =0;i<listDirs.size();i++)
				{
					listDirs.get(i).setText("");									//similar to function New, but removing less things
					listDirs.get(i).setVisible(false);
				}
				plOperationsListImagesOptions.setVisible(false);
				scrollPaneListImages.setBounds(312, 6, 774, 409);
				frmMicromerge.revalidate();
				frmMicromerge.repaint();
			}
		});
		
		
		btnOperationsWorkMerge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plOperationsListImagesOptions.setVisible(false);
				scrollPaneListImages.setBounds(312, 6, 774, 409);
				ImageMerger imgMerger1 = new ImageMerger(mergeMode);								//creating an instance of Image Merger
				BufferedImage img = null;
				if(!listToMerge.isEmpty())
				{
					if(shadeMode ==2)
						img = imgMerger1.merge(listToMerge);
					else if(shadeMode == 0)
					{
						if(BWMode ==1)
						img = imgMerger1.mergeAndFade(listToMerge, true, direction);	
						else
						img = imgMerger1.mergeAndFade(listToMerge, false, direction);
					}
					else																		//choosing appropriate modes
					{
						if(BWMode == 1)
						img = imgMerger1.mergeAndShade(listToMerge, true, direction);
						else
						img = imgMerger1.mergeAndShade(listToMerge, false, direction);
					}
					BufferedImage resized = new BufferedImage(700, 700, BufferedImage.TYPE_INT_RGB);
					Graphics2D g = resized.createGraphics();
					g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);		//resizing the image for preview after merge
					g.drawImage(img, 0, 0, 700, 700, 0, 0, img.getWidth(),img.getHeight(), null);
					g.dispose();
					
					String name = baseString + counter;
					counter += counterStep;															//creating name
					
					btnMergedImagesViewView.setIcon(new ImageIcon(resized));						//displaying image
					listMerged.add(name);															//adding to list of merged images
					final JButton button = new JButton(name);
					button.putClientProperty("image", img);											//giving the button a property with actual merged image
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							BufferedImage image = (BufferedImage)button.getClientProperty("image");
							BufferedImage res = new BufferedImage(700, 700, BufferedImage.TYPE_INT_RGB);
							Graphics2D g = res.createGraphics();																	//enabling the choice which image to see
							g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);		//again resizing
							g.drawImage(image, 0, 0, 700, 700, 0, 0, image.getWidth(),image.getHeight(), null);
							g.dispose();
							btnMergedImagesViewView.setIcon(new ImageIcon(res));
							image.flush();																	//setting image and updating frome
							res.flush();
							frmMicromerge.revalidate();
							frmMicromerge.repaint();
							buttonToSave = button;
						}
					});
					
					tbListMergedImages.add(button);
					buttonToSave = button;
					if(viewImagesAfterMerge)
					{
						plOperations.setVisible(false);
						plMergedImages.setVisible(true);
						btnOperationsOperations.setForeground(new Color(0,0,0));
						btnOperationsMergedImages.setForeground(new Color(255, 255, 255));
						lblbtnOperationsOperations.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnMergedImages.jpg")));	
						lblbtnOperationsMergedImages.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnMergedImagesA.jpg")));	
						plMenu.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 153, 255)));													//functionality of "view after merge button
					}
					resized.flush();
					img.flush();
					frmMicromerge.revalidate();																		//clearing resources
					frmMicromerge.repaint();
			}
			}
		});
		
		btnOperationsWorkMergeAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {													//very simmilar Action listener to Merge i note just a few differences
				plOperationsListImagesOptions.setVisible(false);
				scrollPaneListImages.setBounds(312, 6, 774, 409);
				int i;
				ImageMerger imgMerger1 = new ImageMerger(mergeMode);
				List<String> listStrings = new ArrayList<String>();
				if(!listDirs.isEmpty())
				{
					for(int p =0; p< listDirs.size();p++)
						listStrings.add(listDirs.get(p).getText());
					
					List<BufferedImage> img = new ArrayList<BufferedImage>();
					
					if(shadeMode ==2)
						img = imgMerger1.directoryMerge(listStrings);
					else if(shadeMode == 0)
					{
						if(BWMode ==1)
						img = imgMerger1.directoryMergeAndFade(listStrings, true, direction);	
						else
						img = imgMerger1.directoryMergeAndFade(listStrings, false, direction);			//i use special functions for directory merging
					}
					else
					{
						if(BWMode == 1)
						img = imgMerger1.directoryMergeAndShade(listStrings, true, direction);
						else
						img = imgMerger1.directoryMergeAndShade(listStrings, false, direction);
					}
					
					
					
					for(i =0; i<img.size();i++)										//everything happens in a loop
					{
						
						String name = baseString + counter;
						counter += counterStep;
						
						
						listMerged.add(name);
						final JButton button = new JButton(name);
						button.putClientProperty("image", img.get(i));
						button.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								BufferedImage image = (BufferedImage)button.getClientProperty("image");
								BufferedImage res = new BufferedImage(700, 700, BufferedImage.TYPE_INT_RGB);
								Graphics2D g = res.createGraphics();
								g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
								g.drawImage(image, 0, 0, 700, 700, 0, 0, image.getWidth(),image.getHeight(), null);
								g.dispose();
								btnMergedImagesViewView.setIcon(new ImageIcon(res));
								image.flush();
								res.flush();
								frmMicromerge.revalidate();
								frmMicromerge.repaint();
								buttonToSave = button;
							}
						});
						
						tbListMergedImages.add(button);
						buttonToSave = button;
						
						img.get(i).flush();
							
						
					}
					
					BufferedImage resized = new BufferedImage(700, 700, BufferedImage.TYPE_INT_RGB);
					Graphics2D g = resized.createGraphics();
					g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
					g.drawImage(img.get(i-1), 0, 0, 700, 700, 0, 0, img.get(i-1).getWidth(),img.get(i-1).getHeight(), null);
					g.dispose();
					btnMergedImagesViewView.setIcon(new ImageIcon(resized));
					resized.flush();
					if(viewImagesAfterMerge)
					{
						plOperations.setVisible(false);
						plMergedImages.setVisible(true);
						btnOperationsOperations.setForeground(new Color(0,0,0));
						btnOperationsMergedImages.setForeground(new Color(255, 255, 255));
						lblbtnOperationsOperations.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnMergedImages.jpg")));	
						lblbtnOperationsMergedImages.setIcon(new ImageIcon(StartWin.class.getResource("/resources/btnMergedImagesA.jpg")));	
						plMenu.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 153, 255)));
					}
					frmMicromerge.revalidate();
					frmMicromerge.repaint();
				}
			}
		});
		
		btnMergedImagesWorkSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ImageMerger imgmer = new ImageMerger();
				File dir;
				JFileChooser fileChooser = new JFileChooser(lastSaveDirectory);
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);			//i use filechooser to get selected directory
				fileChooser.setAcceptAllFileFilterUsed(false);				  
				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
				{
					dir =fileChooser.getSelectedFile();
					lastSaveDirectory = dir.getAbsolutePath();								//setting this directory to be the new open directory
					String path = dir.getAbsolutePath();
					//System.out.println("the type choosen is"+type);
					imgmer.saveImage((BufferedImage)buttonToSave.getClientProperty("image"),tfMergedImagesWorkNameCustom.getText()+saveCounter,type,bppMode,path);
					saveCounter+= saveStep;													//i use a dedicated function for saving and increase the naming counter
				}
				
			}
		});
		btnMergedImagesWorkDeleteAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tbListMergedImages.removeAll();													//removing all buttons
				for(int i =0 ; i<tbListMergedImages.getComponentCount();i++)
				{
					JButton button =(JButton)tbListMergedImages.getComponent(i);
					BufferedImage img = (BufferedImage)button.getClientProperty("image");		//clearing resources
					img.flush();
				}
				btnMergedImagesViewView.setIcon(null);
				frmMicromerge.revalidate();																//updating frame
				frmMicromerge.repaint();
			}
		});
		btnMergedImagesWorkDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i;
				for(i =0; i<=listMerged.size();i++)
				{
					if(listMerged.get(i)==buttonToSave.getText())										//i find which in the list is the image we want to delete
						break;
				}
				BufferedImage img = (BufferedImage)buttonToSave.getClientProperty("image");
				img.flush();																			//i clear its resources
				tbListMergedImages.remove(buttonToSave);
				if(i!=0)
					buttonToSave = (JButton)tbListMergedImages.getComponent(i-2);						//I set a new image as the displayed image
				else
					buttonToSave = (JButton)tbListMergedImages.getComponent(i+1);
				img = (BufferedImage)buttonToSave.getClientProperty("image");
				BufferedImage resized = new BufferedImage(700, 700, BufferedImage.TYPE_INT_RGB);
				Graphics2D g = resized.createGraphics();
				g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);		//i resize the new image
				g.drawImage(img, 0, 0, 700, 700, 0, 0, img.getWidth(),img.getHeight(), null);
				g.dispose();
				btnMergedImagesViewView.setIcon(new ImageIcon(resized));
				frmMicromerge.revalidate();
				frmMicromerge.repaint();
			}
		});
		btnMergedImagesWorkSaveAll.addActionListener(new ActionListener() {				//again very similar function to save
			public void actionPerformed(ActionEvent arg0) {
				ImageMerger imgmer = new ImageMerger();
				File dir;
				JButton button;
				JFileChooser fileChooser = new JFileChooser(lastSaveDirectory);
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.setAcceptAllFileFilterUsed(false);				  
				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
				{
					dir =fileChooser.getSelectedFile();
					lastSaveDirectory = dir.getAbsolutePath();
					String path = dir.getAbsolutePath();
					//System.out.println("the tpye choosen is"+type);
					for(int i =0; i < tbListMergedImages.getComponentCount();i++)		//this time everything happens in a loop
					{
						button = (JButton)tbListMergedImages.getComponent(i);
						imgmer.saveImage((BufferedImage)button.getClientProperty("image"),tfMergedImagesWorkNameCustom.getText()+saveCounter,type,bppMode,path);
						saveCounter+= saveStep;
					}
				}
			}
		});	
	}
}

