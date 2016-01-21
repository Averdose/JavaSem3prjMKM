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

public class StartWin {
	
	private JFrame frame;
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
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartWin window = new StartWin();
					window.frame.setVisible(true);
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
		frame = new JFrame();
		frame.setBounds(xScreen, yScreen, 1366, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		final JPanel plMenu = new JPanel();
		plMenu.setBounds(0, 20, 1366, 40);
		frame.getContentPane().add(plMenu);
		plMenu.setBackground(new Color(192, 192, 192));
		plMenu.setLayout(null);
		
		final JButton btnOperationsOperations = new JButton("Operations");
		btnOperationsOperations.setForeground(new Color(0, 153, 0));
		btnOperationsOperations.setBounds(0, 0, 683, 40);
		plMenu.add(btnOperationsOperations);
		
		final JButton btnOperationsMergedImages = new JButton("Merged Images");
		btnOperationsMergedImages.setForeground(new Color(0, 0, 0));
		btnOperationsMergedImages.setBounds(683, 0, 683, 40);
		plMenu.add(btnOperationsMergedImages);
		
		final JPanel plMain = new JPanel();
		plMain.setBounds(0, 60, 1366, 678);
		frame.getContentPane().add(plMain);
		plMain.setLayout(new CardLayout(0, 0));
		
		final JPanel plOperations = new JPanel();
		plOperations.setVisible(false);
		plMain.add(plOperations, "name_77612886309962");
		plOperations.setLayout(null);
		
		final JPanel plOperationsImages = new JPanel();
		plOperationsImages.setBackground(new Color(192, 192, 192));
		plOperationsImages.setBounds(0, 0, 1092, 640);
		plOperations.add(plOperationsImages);
		plOperationsImages.setLayout(null);
		
		final JPanel plOperationsListImagesOptions = new JPanel();
		plOperationsListImagesOptions.setVisible(false);
		plOperationsListImagesOptions.setBackground(new Color(102, 204, 51));
		plOperationsListImagesOptions.setBounds(312, 363, 774, 52);
		plOperationsImages.add(plOperationsListImagesOptions);
		plOperationsListImagesOptions.setLayout(null);
		
		JButton btnOperationsListImageAdd = new JButton("Add to merge");
		
		btnOperationsListImageAdd.setBounds(228, 6, 150, 40);
		plOperationsListImagesOptions.add(btnOperationsListImageAdd);
		
		JButton btnOperationsListImagePreview = new JButton("Preview");
		btnOperationsListImagePreview.setBounds(396, 6, 150, 40);
		plOperationsListImagesOptions.add(btnOperationsListImagePreview);
		
		final JScrollPane scrollPaneListImages = new JScrollPane();
		scrollPaneListImages.setBounds(312, 6, 774, 409);
		plOperationsImages.add(scrollPaneListImages);
		
		final JToolBar tbListImages = new JToolBar();
		tbListImages.setFloatable(false);
		scrollPaneListImages.setViewportView(tbListImages);
		
		JScrollPane scrollPaneListToMarge = new JScrollPane();
		scrollPaneListToMarge.setBounds(6, 420, 1080, 170);
		plOperationsImages.add(scrollPaneListToMarge);
		
		final JToolBar tbListToMerge = new JToolBar();
		tbListToMerge.setFloatable(false);
		scrollPaneListToMarge.setViewportView(tbListToMerge);
		
		JPanel plOperationsListPath = new JPanel();
		plOperationsListPath.setBounds(6, 6, 300, 409);
		plOperationsImages.add(plOperationsListPath);
		plOperationsListPath.setLayout(new GridLayout(20, 1, 0, 0));
		
		final JPanel plOperationsPreview = new JPanel();
		plOperationsPreview.setVisible(false);
		
		final JPanel plOperationsWork = new JPanel();
		plOperationsWork.setBackground(new Color(102, 204, 51));
		plOperationsWork.setBounds(1092, 0, 274, 640);
		plOperations.add(plOperationsWork);
		plOperationsWork.setLayout(null);
		
		JButton btnOperationsWorkLoad = new JButton("LOAD");
		
		btnOperationsWorkLoad.setBounds(6, 6, 262, 50);
		plOperationsWork.add(btnOperationsWorkLoad);
		
		JButton btnOperationsWorkRefresh = new JButton("Refresh");
		btnOperationsWorkRefresh.setBounds(6, 56, 174, 30);
		plOperationsWork.add(btnOperationsWorkRefresh);
		
		JButton btnOperationsWorkMerge = new JButton("MERGE");
		btnOperationsWorkMerge.setBounds(6, 470, 262, 120);
		plOperationsWork.add(btnOperationsWorkMerge);
		
		JButton btnOperationsWorkMergeAll = new JButton("Merge all");
		btnOperationsWorkMergeAll.setBounds(6, 420, 262, 50);
		plOperationsWork.add(btnOperationsWorkMergeAll);
		
		JPanel plOperationsWorkMode = new JPanel();
		plOperationsWorkMode.setBackground(new Color(102, 204, 51));
		plOperationsWorkMode.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Mode", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		plOperationsWorkMode.setBounds(6, 89, 262, 60);
		plOperationsWork.add(plOperationsWorkMode);
		plOperationsWorkMode.setLayout(null);
		
		JRadioButton rdbtnOperationsWorkModeAnd = new JRadioButton("AND");
		rdbtnOperationsWorkModeAnd.setBackground(new Color(102, 204, 51));
		rdbtnOperationsWorkModeAnd.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnOperationsWorkModeAnd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				mergeMode = 2;
			}
		});
		rdbtnOperationsWorkModeAnd.setBounds(10, 20, 80, 30);
		plOperationsWorkMode.add(rdbtnOperationsWorkModeAnd);
		
		JRadioButton rdbtnOperationsWorkModeOr = new JRadioButton("OR");
		rdbtnOperationsWorkModeOr.setBackground(new Color(102, 204, 51));
		rdbtnOperationsWorkModeOr.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnOperationsWorkModeOr.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {												//simple ActionListeners used to change the merge mode
				mergeMode = 1;
			}
		});
		rdbtnOperationsWorkModeOr.setBounds(90, 20, 80, 30);
		plOperationsWorkMode.add(rdbtnOperationsWorkModeOr);
		
		JRadioButton rdbtnOperationsWorkModeXor = new JRadioButton("XOR");
		rdbtnOperationsWorkModeXor.setBackground(new Color(102, 204, 51));
		rdbtnOperationsWorkModeXor.setSelected(true);
		rdbtnOperationsWorkModeXor.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnOperationsWorkModeXor.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				mergeMode = 0;
			}
		});
		rdbtnOperationsWorkModeXor.setBounds(170, 20, 81, 30);
		plOperationsWorkMode.add(rdbtnOperationsWorkModeXor);
			
		ButtonGroup mMode = new ButtonGroup();
		mMode.add(rdbtnOperationsWorkModeAnd);
		mMode.add(rdbtnOperationsWorkModeOr);
		mMode.add(rdbtnOperationsWorkModeXor);
		
		JPanel plOperationsWorkShading = new JPanel();
		plOperationsWorkShading.setBackground(new Color(102, 204, 51));
		plOperationsWorkShading.setBorder(new TitledBorder(null, "Shading Mode", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		plOperationsWorkShading.setBounds(6, 155, 108, 120);
		plOperationsWork.add(plOperationsWorkShading);
		plOperationsWorkShading.setLayout(null);
		
		JRadioButton rdbtnOperationsWorkShadingNormal = new JRadioButton("NORMAL");
		rdbtnOperationsWorkShadingNormal.setBackground(new Color(102, 204, 51));
		rdbtnOperationsWorkShadingNormal.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnOperationsWorkShadingNormal.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				shadeMode = 2;
			}
		});
		rdbtnOperationsWorkShadingNormal.setBounds(6, 20, 96, 30);
		plOperationsWorkShading.add(rdbtnOperationsWorkShadingNormal);
		
		JRadioButton rdbtnOperationsWorkShadingFading = new JRadioButton("FADING");
		rdbtnOperationsWorkShadingFading.setBackground(new Color(102, 204, 51));
		rdbtnOperationsWorkShadingFading.setSelected(true);
		rdbtnOperationsWorkShadingFading.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnOperationsWorkShadingFading.addActionListener(new ActionListener(){						//similar to above Action Listeners used to change shade mode
			public void actionPerformed(ActionEvent e) {
				shadeMode = 0;
			}
		});
		rdbtnOperationsWorkShadingFading.setBounds(6, 50, 96, 30);
		plOperationsWorkShading.add(rdbtnOperationsWorkShadingFading);
		
		JRadioButton rdbtnOperationsWorkShadingShading = new JRadioButton("SHADING");
		rdbtnOperationsWorkShadingShading.setBackground(new Color(102, 204, 51));
		rdbtnOperationsWorkShadingShading.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnOperationsWorkShadingShading.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				shadeMode = 1;
			}
		});
		rdbtnOperationsWorkShadingShading.setBounds(6, 80, 96, 30);
		plOperationsWorkShading.add(rdbtnOperationsWorkShadingShading);
		
		ButtonGroup shadingMode = new ButtonGroup();
		shadingMode.add(rdbtnOperationsWorkShadingNormal);
		shadingMode.add(rdbtnOperationsWorkShadingFading);
		shadingMode.add(rdbtnOperationsWorkShadingShading);
		
		JPanel plOperationsWorkShadingDirections = new JPanel();
		plOperationsWorkShadingDirections.setBackground(new Color(102, 204, 51));
		plOperationsWorkShadingDirections.setBorder(new TitledBorder(null, "Shading Directions", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		plOperationsWorkShadingDirections.setBounds(114, 155, 154, 120);
		plOperationsWork.add(plOperationsWorkShadingDirections);
		plOperationsWorkShadingDirections.setLayout(null);
		
		JRadioButton rdbtnOperationsWorkShadingDirectionsTL = new JRadioButton("TL");
		rdbtnOperationsWorkShadingDirectionsTL.setBackground(new Color(102, 204, 51));
		rdbtnOperationsWorkShadingDirectionsTL.setToolTipText("From top left corner");
		rdbtnOperationsWorkShadingDirectionsTL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				direction = 1;
			}
		});
		rdbtnOperationsWorkShadingDirectionsTL.setBounds(3, 20, 48, 30);
		plOperationsWorkShadingDirections.add(rdbtnOperationsWorkShadingDirectionsTL);
		
		JRadioButton rdbtnOperationsWorkShadingDirectionsTC = new JRadioButton("TC");
		rdbtnOperationsWorkShadingDirectionsTC.setBackground(new Color(102, 204, 51));
		rdbtnOperationsWorkShadingDirectionsTC.setToolTipText("From top");
		rdbtnOperationsWorkShadingDirectionsTC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				direction = 2;
			}
		});
		rdbtnOperationsWorkShadingDirectionsTC.setBounds(51, 20, 49, 30);
		plOperationsWorkShadingDirections.add(rdbtnOperationsWorkShadingDirectionsTC);
		
		JRadioButton rdbtnOperationsWorkShadingDirectionsTR = new JRadioButton("TR");
		rdbtnOperationsWorkShadingDirectionsTR.setBackground(new Color(102, 204, 51));
		rdbtnOperationsWorkShadingDirectionsTR.setToolTipText("From top right corner");
		rdbtnOperationsWorkShadingDirectionsTR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				direction = 3;
			}
		});
		rdbtnOperationsWorkShadingDirectionsTR.setBounds(100, 20, 48, 30);
		plOperationsWorkShadingDirections.add(rdbtnOperationsWorkShadingDirectionsTR);
		
		JRadioButton rdbtnOperationsWorkShadingDirectionsCL = new JRadioButton("CL");
		rdbtnOperationsWorkShadingDirectionsCL.setBackground(new Color(102, 204, 51));
		rdbtnOperationsWorkShadingDirectionsCL.setToolTipText("From left side");
		rdbtnOperationsWorkShadingDirectionsCL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				direction = 0;
			}
		});
		rdbtnOperationsWorkShadingDirectionsCL.setBounds(3, 50, 48, 30);
		plOperationsWorkShadingDirections.add(rdbtnOperationsWorkShadingDirectionsCL);
		
		JRadioButton rdbtnOperationsWorkShadingDirectionsCC = new JRadioButton("CC");
		rdbtnOperationsWorkShadingDirectionsCC.setBackground(new Color(102, 204, 51));
		rdbtnOperationsWorkShadingDirectionsCC.setSelected(true);
		rdbtnOperationsWorkShadingDirectionsCC.setToolTipText("From center");
		rdbtnOperationsWorkShadingDirectionsCC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				direction = 8;
			}
		});
		rdbtnOperationsWorkShadingDirectionsCC.setBounds(51, 50, 50, 30);
		plOperationsWorkShadingDirections.add(rdbtnOperationsWorkShadingDirectionsCC);
		
		JRadioButton rdbtnOperationsWorkShadingDirectionsCR = new JRadioButton("CR");
		rdbtnOperationsWorkShadingDirectionsCR.setBackground(new Color(102, 204, 51));
		rdbtnOperationsWorkShadingDirectionsCR.setToolTipText("From right side");
		rdbtnOperationsWorkShadingDirectionsCR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				direction = 4;
			}
		});
		rdbtnOperationsWorkShadingDirectionsCR.setBounds(100, 50, 49, 30);
		plOperationsWorkShadingDirections.add(rdbtnOperationsWorkShadingDirectionsCR);
		
		JRadioButton rdbtnOperationsWorkShadingDirectionsBL = new JRadioButton("BL");
		rdbtnOperationsWorkShadingDirectionsBL.setBackground(new Color(102, 204, 51));
		rdbtnOperationsWorkShadingDirectionsBL.setToolTipText("From bottom left corner");
		rdbtnOperationsWorkShadingDirectionsBL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {										//Action listeners used to change direction from which we shade/fade
				direction = 7;
			}
		});
		rdbtnOperationsWorkShadingDirectionsBL.setBounds(3, 80, 48, 30);
		plOperationsWorkShadingDirections.add(rdbtnOperationsWorkShadingDirectionsBL);
		
		JRadioButton rdbtnOperationsWorkShadingDirectionsBC = new JRadioButton("BC");
		rdbtnOperationsWorkShadingDirectionsBC.setBackground(new Color(102, 204, 51));
		rdbtnOperationsWorkShadingDirectionsBC.setToolTipText("From bottom");
		rdbtnOperationsWorkShadingDirectionsBC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				direction = 6;
			}
		});
		rdbtnOperationsWorkShadingDirectionsBC.setBounds(51, 80, 48, 30);
		plOperationsWorkShadingDirections.add(rdbtnOperationsWorkShadingDirectionsBC);
		
		JRadioButton rdbtnOperationsWorkShadingDirectionsBR = new JRadioButton("BR");
		rdbtnOperationsWorkShadingDirectionsBR.setBackground(new Color(102, 204, 51));
		rdbtnOperationsWorkShadingDirectionsBR.setToolTipText("From bottom right corner");
		rdbtnOperationsWorkShadingDirectionsBR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				direction = 5;
			}
		});
		rdbtnOperationsWorkShadingDirectionsBR.setBounds(100, 80, 47, 30);
		plOperationsWorkShadingDirections.add(rdbtnOperationsWorkShadingDirectionsBR);
		
		ButtonGroup shadingDirections = new ButtonGroup();
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
		plOperationsWorkBow.setBackground(new Color(102, 204, 51));
		plOperationsWorkBow.setBorder(new TitledBorder(null, "BOW Mode", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		plOperationsWorkBow.setBounds(6, 281, 262, 90);
		plOperationsWork.add(plOperationsWorkBow);
		plOperationsWorkBow.setLayout(null);
		
		JRadioButton rdbtnOperationsWorkBowWob = new JRadioButton("WHITE ON BLACK");
		rdbtnOperationsWorkBowWob.setBackground(new Color(102, 204, 51));
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
		rdbtnOperationsWorkBowBow.setBackground(new Color(102, 204, 51));
		rdbtnOperationsWorkBowBow.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnOperationsWorkBowBow.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				BWMode = 1;
			}
		});
		rdbtnOperationsWorkBowBow.setBounds(10, 50, 242, 30);
		plOperationsWorkBow.add(rdbtnOperationsWorkBowBow);

		ButtonGroup bowMode = new ButtonGroup();
		bowMode.add(rdbtnOperationsWorkBowWob);
		bowMode.add(rdbtnOperationsWorkBowBow);
		
		JCheckBox chckbxOperationsWorkViewAfterMerge = new JCheckBox("View images after merge");
		chckbxOperationsWorkViewAfterMerge.setBackground(new Color(102, 204, 51));
		chckbxOperationsWorkViewAfterMerge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {											//changing viewImagesAfterMerge boolean  flag
				viewImagesAfterMerge = !viewImagesAfterMerge;
			}
		});
		chckbxOperationsWorkViewAfterMerge.setSelected(true);
		chckbxOperationsWorkViewAfterMerge.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxOperationsWorkViewAfterMerge.setBounds(0, 384, 274, 30);
		plOperationsWork.add(chckbxOperationsWorkViewAfterMerge);
		
		JButton btnOperationsWorkClearAll = new JButton("Clear all");
		btnOperationsWorkClearAll.setBounds(180, 56, 88, 30);
		plOperationsWork.add(btnOperationsWorkClearAll);
		
		final JPanel plOperationsPreviewWork = new JPanel();
		plOperationsPreviewWork.setVisible(false);
		plOperationsPreviewWork.setBackground(new Color(102, 204, 51));
		plOperationsPreviewWork.setBounds(1092, 0, 274, 640);
		plOperations.add(plOperationsPreviewWork);
		plOperationsPreviewWork.setLayout(null);
		
		JButton btnOperationsPreviewWorkBack = new JButton("Close the preview");
		btnOperationsPreviewWorkBack.setBounds(6, 420, 262, 60);
		plOperationsPreviewWork.add(btnOperationsPreviewWorkBack);
		
		tfOperationsPreviewWorkPath = new JTextField();
		tfOperationsPreviewWorkPath.setEditable(false);
		tfOperationsPreviewWorkPath.setHorizontalAlignment(SwingConstants.CENTER);
		tfOperationsPreviewWorkPath.setText("Path of the imagesfssdasdasmdkasngkasngaksmvlmsA");
		tfOperationsPreviewWorkPath.setBounds(6, 36, 262, 30);
		plOperationsPreviewWork.add(tfOperationsPreviewWorkPath);
		tfOperationsPreviewWorkPath.setColumns(10);
		plOperationsPreview.setBackground(Color.WHITE);
		plOperationsPreview.setBounds(0, 0, 1092, 640);
		plOperations.add(plOperationsPreview);
		plOperationsPreview.setLayout(null);
		
		final JButton imgOperationsPreview = new JButton("ImageToView");
		imgOperationsPreview.setBounds(146, 5, 800, 590);
		plOperationsPreview.add(imgOperationsPreview);
		
		final JPanel plMergedImages = new JPanel();
		plMergedImages.setVisible(false);
		plMain.add(plMergedImages, "name_77604441665075");
		plMergedImages.setLayout(null);
		
		final JPanel plMergedImagesView = new JPanel();
		plMergedImagesView.setBounds(0, 0, 1092, 640);
		plMergedImages.add(plMergedImagesView);
		plMergedImagesView.setLayout(null);
		
		final JButton btnMergedImagesViewView = new JButton("");
		btnMergedImagesViewView.setBounds(6, 6, 1080, 534);
		plMergedImagesView.add(btnMergedImagesViewView);
		
		JScrollPane scrollPaneListMergedImages = new JScrollPane();
		scrollPaneListMergedImages.setBounds(6, 546, 1080, 40);
		plMergedImagesView.add(scrollPaneListMergedImages);
		
		final JToolBar tbListMergedImages = new JToolBar();
		tbListMergedImages.setFloatable(false);
		scrollPaneListMergedImages.setViewportView(tbListMergedImages);
		
		JPanel plMergedImagesWork = new JPanel();
		plMergedImagesWork.setBounds(1092, 0, 274, 640);
		plMergedImages.add(plMergedImagesWork);
		plMergedImagesWork.setBackground(new Color(102, 153, 255));
		plMergedImagesWork.setLayout(null);
		
		JPanel plMergedImagesWorkName = new JPanel();
		plMergedImagesWorkName.setBorder(new TitledBorder(null, "Name", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		plMergedImagesWorkName.setBackground(new Color(102, 153, 255));
		plMergedImagesWorkName.setBounds(6, 12, 262, 95);
		plMergedImagesWork.add(plMergedImagesWorkName);
		plMergedImagesWorkName.setLayout(null);
		
		tfMergedImagesWorkNameCustom = new JTextField();
		tfMergedImagesWorkNameCustom.setText("image");
		tfMergedImagesWorkNameCustom.setBounds(10, 55, 242, 30);
		plMergedImagesWorkName.add(tfMergedImagesWorkNameCustom);
		tfMergedImagesWorkNameCustom.setColumns(10);
		
		JRadioButton rdbtnMergedImagesWorkNameCustom = new JRadioButton("Custom");
		rdbtnMergedImagesWorkNameCustom.setBackground(new Color(102, 153, 255));
		rdbtnMergedImagesWorkNameCustom.setSelected(true);
		rdbtnMergedImagesWorkNameCustom.setBounds(10, 20, 121, 23);
		plMergedImagesWorkName.add(rdbtnMergedImagesWorkNameCustom);
		
		JRadioButton rdbtnMergedImagesWorkNameFromFile = new JRadioButton("Get from file");
		rdbtnMergedImagesWorkNameFromFile.setBackground(new Color(102, 153, 255));
		rdbtnMergedImagesWorkNameFromFile.setBounds(131, 20, 121, 23);
		plMergedImagesWorkName.add(rdbtnMergedImagesWorkNameFromFile);
		
		ButtonGroup nameMode = new ButtonGroup();
		nameMode.add(rdbtnMergedImagesWorkNameCustom);
		nameMode.add(rdbtnMergedImagesWorkNameFromFile);
		
		
		JPanel plMergedImagesWorkCounter = new JPanel();
		plMergedImagesWorkCounter.setBorder(new TitledBorder(null, "Counter", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		plMergedImagesWorkCounter.setBackground(new Color(102, 153, 255));
		plMergedImagesWorkCounter.setBounds(6, 113, 262, 95);
		plMergedImagesWork.add(plMergedImagesWorkCounter);
		plMergedImagesWorkCounter.setLayout(null);
		
		tfMergedImagesWorkCounterStart = new JTextField();
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
		lblMergedImagesWorkCounterStart.setBackground(new Color(102, 153, 255));
		lblMergedImagesWorkCounterStart.setBounds(10, 20, 80, 30);
		plMergedImagesWorkCounter.add(lblMergedImagesWorkCounterStart);
		
		JLabel lblMergedImagesWorkCounterIncrease = new JLabel("Increase by:");
		lblMergedImagesWorkCounterIncrease.setBackground(new Color(102, 153, 255));
		lblMergedImagesWorkCounterIncrease.setBounds(10, 55, 80, 30);
		plMergedImagesWorkCounter.add(lblMergedImagesWorkCounterIncrease);
		
		JButton btnMergedImagesWorkSave = new JButton("SAVE");
		btnMergedImagesWorkSave.setBounds(6, 440, 262, 100);
		plMergedImagesWork.add(btnMergedImagesWorkSave);
		
		JButton btnMergedImagesWorkDelete = new JButton("Delete");
		btnMergedImagesWorkDelete.setToolTipText("Delete chosen image");
		btnMergedImagesWorkDelete.setBounds(6, 546, 174, 40);
		plMergedImagesWork.add(btnMergedImagesWorkDelete);
		
		JPanel plMergedImagesWorkBpp = new JPanel();
		plMergedImagesWorkBpp.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "BPP", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		plMergedImagesWorkBpp.setBackground(new Color(102, 153, 255));
		plMergedImagesWorkBpp.setBounds(137, 214, 131, 180);
		plMergedImagesWork.add(plMergedImagesWorkBpp);
		plMergedImagesWorkBpp.setLayout(null);
		
		JRadioButton rdbtnMergedImagesWorkBpp1 = new JRadioButton("1");
		rdbtnMergedImagesWorkBpp1.setBackground(new Color(102, 153, 255));
		rdbtnMergedImagesWorkBpp1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bppMode = 1;
			}
		});
		rdbtnMergedImagesWorkBpp1.setBounds(10, 20, 110, 30);
		plMergedImagesWorkBpp.add(rdbtnMergedImagesWorkBpp1);
		
		JRadioButton rdbtnMergedImagesWorkBpp8 = new JRadioButton("8");
		rdbtnMergedImagesWorkBpp8.setBackground(new Color(102, 153, 255));
		rdbtnMergedImagesWorkBpp8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bppMode = 8;
			}
		});
		rdbtnMergedImagesWorkBpp8.setBounds(10, 50, 110, 30);
		plMergedImagesWorkBpp.add(rdbtnMergedImagesWorkBpp8);
		
		JRadioButton rdbtnMergedImagesWorkBpp16 = new JRadioButton("16");
		rdbtnMergedImagesWorkBpp16.setBackground(new Color(102, 153, 255));
		rdbtnMergedImagesWorkBpp16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bppMode = 16;
			}
		});
		rdbtnMergedImagesWorkBpp16.setBounds(10, 80, 110, 30);
		plMergedImagesWorkBpp.add(rdbtnMergedImagesWorkBpp16);
		
		final JRadioButton rdbtnMergedImagesWorkBpp24 = new JRadioButton("24");
		rdbtnMergedImagesWorkBpp24.setBackground(new Color(102, 153, 255));
		rdbtnMergedImagesWorkBpp24.setSelected(true);
		rdbtnMergedImagesWorkBpp24.addActionListener(new ActionListener() {						//changing the bpp with which images will be saved
			public void actionPerformed(ActionEvent e) {
				bppMode = 24;
			}
		});
		rdbtnMergedImagesWorkBpp24.setBounds(10, 110, 110, 30);
		plMergedImagesWorkBpp.add(rdbtnMergedImagesWorkBpp24);
		
		final JRadioButton rdbtnMergedImagesWorkBpp32 = new JRadioButton("32");
		rdbtnMergedImagesWorkBpp32.setEnabled(false);
		rdbtnMergedImagesWorkBpp32.setBackground(new Color(102, 153, 255));
		rdbtnMergedImagesWorkBpp32.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bppMode = 32;
			}
		});
		rdbtnMergedImagesWorkBpp32.setBounds(10, 140, 110, 30);
		plMergedImagesWorkBpp.add(rdbtnMergedImagesWorkBpp32);
		
		ButtonGroup saveBpp = new ButtonGroup();
		saveBpp.add(rdbtnMergedImagesWorkBpp1);
		saveBpp.add(rdbtnMergedImagesWorkBpp8);
		saveBpp.add(rdbtnMergedImagesWorkBpp16);
		saveBpp.add(rdbtnMergedImagesWorkBpp24);
		saveBpp.add(rdbtnMergedImagesWorkBpp32);
		
		
		JPanel plMergedImagesWorkType = new JPanel();
		plMergedImagesWorkType.setBorder(new TitledBorder(null, "Save as", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		plMergedImagesWorkType.setBackground(new Color(102, 153, 255));
		plMergedImagesWorkType.setBounds(6, 214, 131, 180);
		plMergedImagesWork.add(plMergedImagesWorkType);
		plMergedImagesWorkType.setLayout(null);
		
		JRadioButton rdbtnMergedImagesWorkTypeJPG = new JRadioButton(".JPG");
		rdbtnMergedImagesWorkTypeJPG.setBackground(new Color(102, 153, 255));
		rdbtnMergedImagesWorkTypeJPG.setSelected(true);
		rdbtnMergedImagesWorkTypeJPG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				type = "JPG";
				if (bppMode == 32) {
					bppMode = 24;
					rdbtnMergedImagesWorkBpp32.setSelected(false);
					rdbtnMergedImagesWorkBpp24.setSelected(true);
				}
				rdbtnMergedImagesWorkBpp32.setEnabled(false);
			}
		});
		rdbtnMergedImagesWorkTypeJPG.setBounds(10, 20, 110, 30);
		plMergedImagesWorkType.add(rdbtnMergedImagesWorkTypeJPG);
		
		JRadioButton rdbtnMergedImagesWorkTypeJPEG = new JRadioButton(".JPEG");
		rdbtnMergedImagesWorkTypeJPEG.setBackground(new Color(102, 153, 255));
		rdbtnMergedImagesWorkTypeJPEG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				type = "JPEG";
				if (bppMode == 32) {
					bppMode = 24;												//choosing type in which images will be saved for jpg and bmp we also change bpp mode because
					rdbtnMergedImagesWorkBpp32.setSelected(false);				//they do not support such bpp mode
					rdbtnMergedImagesWorkBpp24.setSelected(true);
				}
				rdbtnMergedImagesWorkBpp32.setEnabled(false);
			}
		});
		rdbtnMergedImagesWorkTypeJPEG.setBounds(10, 50, 110, 30);
		plMergedImagesWorkType.add(rdbtnMergedImagesWorkTypeJPEG);
		
		JRadioButton rdbtnMergedImagesWorkTypeBMP = new JRadioButton(".BMP");
		rdbtnMergedImagesWorkTypeBMP.setBackground(new Color(102, 153, 255));
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
			}
		});
		rdbtnMergedImagesWorkTypeBMP.setBounds(10, 80, 110, 30);
		plMergedImagesWorkType.add(rdbtnMergedImagesWorkTypeBMP);
		
		JRadioButton rdbtnMergedImagesWorkTypePNG = new JRadioButton(".PNG");
		rdbtnMergedImagesWorkTypePNG.setBackground(new Color(102, 153, 255));
		rdbtnMergedImagesWorkTypePNG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				type = "PNG";
				rdbtnMergedImagesWorkBpp32.setEnabled(true);
			}
		});
		rdbtnMergedImagesWorkTypePNG.setBounds(10, 110, 110, 30);
		plMergedImagesWorkType.add(rdbtnMergedImagesWorkTypePNG);
		
		JRadioButton rdbtnMergedImagesWorkTypeTIFF = new JRadioButton(".TIFF");
		rdbtnMergedImagesWorkTypeTIFF.setBackground(new Color(102, 153, 255));
		rdbtnMergedImagesWorkTypeTIFF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				type = "TIFF";
				rdbtnMergedImagesWorkBpp32.setEnabled(true);
			}
		});
		rdbtnMergedImagesWorkTypeTIFF.setBounds(10, 140, 110, 30);
		plMergedImagesWorkType.add(rdbtnMergedImagesWorkTypeTIFF);
		
		ButtonGroup saveAs = new ButtonGroup();
		saveAs.add(rdbtnMergedImagesWorkTypeJPG);
		saveAs.add(rdbtnMergedImagesWorkTypeJPEG);
		saveAs.add(rdbtnMergedImagesWorkTypeBMP);
		saveAs.add(rdbtnMergedImagesWorkTypePNG);
		saveAs.add(rdbtnMergedImagesWorkTypeTIFF);
		
		
		JButton btnMergedImagesWorkDeleteAll = new JButton("Delete all");
		btnMergedImagesWorkDeleteAll.setBounds(180, 546, 88, 40);
		plMergedImagesWork.add(btnMergedImagesWorkDeleteAll);
		
		JButton btnMergedImagesWorkSaveAll = new JButton("Save all");
		btnMergedImagesWorkSaveAll.setBounds(6, 405, 262, 35);
		plMergedImagesWork.add(btnMergedImagesWorkSaveAll);
		
		final JPanel plShade = new JPanel();
		plShade.setVisible(false);
		plShade.setBounds(0, 20, 1366, 660);
		frame.getContentPane().add(plShade);
		plShade.setBackground(Color.WHITE);
		plShade.setLayout(null);
		
		final JPanel plShadeData = new JPanel();
		plShadeData.setBackground(new Color(51, 153, 255));
		plShadeData.setVisible(false);
		
		final JButton btnShadeCancel = new JButton("CANCEL");
		btnShadeCancel.setForeground(new Color(51, 153, 255));
		btnShadeCancel.setBounds(1086, 280, 262, 40);
		plShade.add(btnShadeCancel);
		plShadeData.setBounds(0, 60, 1366, 480);
		plShade.add(plShadeData);
		plShadeData.setLayout(null);
		
		final JTextPane txtpnShadeDataAboutUs = new JTextPane();
		txtpnShadeDataAboutUs.setVisible(false);
		
		final JLabel lblShadeTitle = new JLabel("");
		lblShadeTitle.setBounds(0, 0, 1092, 50);
		plShadeData.add(lblShadeTitle);
		lblShadeTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblShadeTitle.setForeground(Color.WHITE);
		lblShadeTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		
		final JTextPane txtpnShadeDataFaq = new JTextPane();
		txtpnShadeDataFaq.setVisible(false);
		txtpnShadeDataFaq.setText("Q: What does these letters in Shading Direction mean?\nA: Hover mouse over each abbreviation, you will see a tip.");
		txtpnShadeDataFaq.setEditable(false);
		txtpnShadeDataFaq.setBounds(296, 80, 500, 380);
		plShadeData.add(txtpnShadeDataFaq);
		
		final JTextPane txtpnShadeDataBugs = new JTextPane();
		txtpnShadeDataBugs.setText("Known bugs:\n- Name choose options does not work properly.");
		txtpnShadeDataBugs.setEditable(false);
		txtpnShadeDataBugs.setVisible(false);
		txtpnShadeDataBugs.setBounds(296, 80, 500, 380);
		plShadeData.add(txtpnShadeDataBugs);
		txtpnShadeDataAboutUs.setEditable(false);
		txtpnShadeDataAboutUs.setText("Students of Warsaw University of Technology:\nMikołaj Słoń\nMateusz Szperna\nKornel Żaba");
		txtpnShadeDataAboutUs.setBounds(296, 80, 500, 380);
		plShadeData.add(txtpnShadeDataAboutUs);
		
		final JTextPane txtpnShadeDataProgram = new JTextPane();
		txtpnShadeDataProgram.setVisible(false);
		txtpnShadeDataProgram.setText("MicroMerge Beta 0.95");
		txtpnShadeDataProgram.setEditable(false);
		
		final JTextPane txtpnShadeDataThanks = new JTextPane();
		txtpnShadeDataThanks.setVisible(false);
		txtpnShadeDataThanks.setText("Special thanks to Mikolaj and Kornel  and all who helped us to create this wonderful program - MicroMerge! <3");
		txtpnShadeDataThanks.setEditable(false);
		txtpnShadeDataThanks.setBounds(296, 80, 500, 380);
		plShadeData.add(txtpnShadeDataThanks);
		txtpnShadeDataProgram.setBounds(296, 80, 500, 380);
		plShadeData.add(txtpnShadeDataProgram);
		
		JButton btnShadeBackUp = new JButton("");
		btnShadeBackUp.setFocusable(false);
		btnShadeBackUp.setRequestFocusEnabled(false);
		btnShadeBackUp.setBorderPainted(false);
		btnShadeBackUp.setBackground(Color.WHITE);
		btnShadeBackUp.setBounds(0, 0, 1366, 60);
		plShade.add(btnShadeBackUp);
		
		JButton btnShadeBackBottom = new JButton("");
		btnShadeBackBottom.setBackground(Color.WHITE);
		btnShadeBackBottom.setBorderPainted(false);
		btnShadeBackBottom.setRequestFocusEnabled(false);
		btnShadeBackBottom.setFocusable(false);
		btnShadeBackBottom.setBounds(0, 540, 1366, 100);
		plShade.add(btnShadeBackBottom);
		
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
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1366, 20);
		frame.getContentPane().add(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mnMenu.add(mntmNew);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		mnMenu.add(mntmExit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmFaq = new JMenuItem("FAQ");
		mnHelp.add(mntmFaq);
		
		JMenuItem mntmBugs = new JMenuItem("Bugs");
		mnHelp.add(mntmBugs);
		
		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
		JMenuItem mntmCreators = new JMenuItem("Creators");
		mnAbout.add(mntmCreators);
		
		JMenuItem mntmThanks = new JMenuItem("Thanks");
		mnAbout.add(mntmThanks);
		
		JMenuItem mntmProgram = new JMenuItem("MicroMerge");
		mnAbout.add(mntmProgram);
		
		mntmFaq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plMain.setVisible(false);
				plMenu.setVisible(false);
				plShade.setVisible(true);
				plShadeData.setVisible(true);
				plShadeData.setBackground(new Color(153, 0, 0));
				btnShadeCancel.setForeground(new Color(153, 0, 0));
				txtpnShadeDataFaq.setVisible(true);
				txtpnShadeDataBugs.setVisible(false);
				txtpnShadeDataAboutUs.setVisible(false);
				txtpnShadeDataThanks.setVisible(false);
				txtpnShadeDataProgram.setVisible(false);
				lblShadeTitle.setText("FAQ");
			}
		});
		
		mntmBugs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plMain.setVisible(false);
				plMenu.setVisible(false);
				plShade.setVisible(true);
				plShadeData.setVisible(true);
				plShadeData.setBackground(new Color(153, 0, 0));
				btnShadeCancel.setForeground(new Color(153, 0, 0));
				txtpnShadeDataFaq.setVisible(false);
				txtpnShadeDataBugs.setVisible(true);
				txtpnShadeDataAboutUs.setVisible(false);
				txtpnShadeDataThanks.setVisible(false);
				txtpnShadeDataProgram.setVisible(false);
				lblShadeTitle.setText("Bugs");
			}
		});
		
		mntmCreators.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plMain.setVisible(false);
				plMenu.setVisible(false);
				plShade.setVisible(true);
				plShadeData.setVisible(true);
				plShadeData.setBackground(new Color(51, 153, 255));
				btnShadeCancel.setForeground(new Color(51, 153, 255));
				txtpnShadeDataFaq.setVisible(false);
				txtpnShadeDataBugs.setVisible(false);
				txtpnShadeDataAboutUs.setVisible(true);
				txtpnShadeDataThanks.setVisible(false);
				txtpnShadeDataProgram.setVisible(false);
				lblShadeTitle.setText("About Us");
			}
		});
		
		mntmThanks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plMain.setVisible(false);
				plMenu.setVisible(false);
				plShade.setVisible(true);
				plShadeData.setVisible(true);
				plShadeData.setBackground(new Color(51, 153, 255));
				btnShadeCancel.setForeground(new Color(51, 153, 255));
				txtpnShadeDataFaq.setVisible(false);
				txtpnShadeDataBugs.setVisible(false);
				txtpnShadeDataAboutUs.setVisible(false);
				txtpnShadeDataThanks.setVisible(true);
				txtpnShadeDataProgram.setVisible(false);
				lblShadeTitle.setText("Special thanks");
			}
		});
		
		mntmProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plMain.setVisible(false);
				plMenu.setVisible(false);
				plShade.setVisible(true);
				plShadeData.setVisible(true);
				plShadeData.setBackground(new Color(51, 153, 255));
				btnShadeCancel.setForeground(new Color(51, 153, 255));
				txtpnShadeDataFaq.setVisible(false);
				txtpnShadeDataBugs.setVisible(false);
				txtpnShadeDataAboutUs.setVisible(false);
				txtpnShadeDataThanks.setVisible(false);
				txtpnShadeDataProgram.setVisible(true);
				lblShadeTitle.setText("MicroMerge");
			}
		});
		
		
		btnOperationsOperations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plOperations.setVisible(true);
				plMergedImages.setVisible(false);
				btnOperationsOperations.setForeground(new Color(0, 153, 0));
				btnOperationsMergedImages.setForeground(new Color(0,0,0));
			}
		});
		
		btnOperationsMergedImages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plOperations.setVisible(false);
				plMergedImages.setVisible(true);
				btnOperationsOperations.setForeground(new Color(0,0,0));
				btnOperationsMergedImages.setForeground(new Color(0, 153, 255));
			}
		});
		
		for(;numberofdirs<20;numberofdirs++)
		{
			listDirs.add(new JTextField());
			plOperationsListPath.add(listDirs.get(numberofdirs));
			listDirs.get(numberofdirs).setColumns(10);
			listDirs.get(numberofdirs).setVisible(false);
		}
		
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
										frame.revalidate();
										frame.repaint();
										selectedButton.putClientProperty("selected", new Integer(0));
									}
								}
							});
								
							
							resized.flush();
							img.flush();																//i take care of unused resources
							
							
							tbListImages.add(button);	
							
							frame.revalidate();															//i update the display
							frame.repaint();
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
										frame.revalidate();
										frame.repaint();														//i update the frame
									}
								}
							});
							
							
							
							
							resized.flush();
							img.flush();																		//i take care of unsused resources
							
							tbListImages.add(button);	
							button.putClientProperty("selected", new Integer(0));								//noting the the image is not selected
							frame.revalidate();
							frame.repaint();
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
				
				selectedButton.setIcon(new ImageIcon(resized));															//seting the newly resized image
				
				tbListToMerge.add(selectedButton);
				listToMerge.add(selectedButton.getToolTipText());
				selectedButton.putClientProperty("selected",new Integer(1));
				plOperationsListImagesOptions.setVisible(false);
				scrollPaneListImages.setBounds(312, 6, 774, 409);
				
				frame.revalidate();																						//I update the frame
				frame.repaint();

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
			
			frame.revalidate();
			frame.repaint();

			
			}
		});
		
		//returning from preview
		btnOperationsPreviewWorkBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plOperationsImages.setVisible(true);
				plOperationsWork.setVisible(true);
				plOperationsPreview.setVisible(false);												//self explanatory
				plOperationsPreviewWork.setVisible(false);
					

				frame.revalidate();
				frame.repaint();
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
				frame.revalidate();
				frame.repaint();
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
				frame.revalidate();
				frame.repaint();
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
							frame.revalidate();
							frame.repaint();
							buttonToSave = button;
						}
					});
					
					tbListMergedImages.add(button);
					buttonToSave = button;
					if(viewImagesAfterMerge)
					{
						plOperations.setVisible(false);
						plMergedImages.setVisible(true);													//functionality of "view after merge button
					}
					resized.flush();
					img.flush();
					frame.revalidate();																		//clearing resources
					frame.repaint();
					btnOperationsOperations.setForeground(new Color(0,0,0));
					btnOperationsMergedImages.setForeground(new Color(0, 153, 255));
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
								frame.revalidate();
								frame.repaint();
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
					}
					frame.revalidate();
					frame.repaint();
					btnOperationsOperations.setForeground(new Color(0,0,0));
					btnOperationsMergedImages.setForeground(new Color(0, 153, 255));
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
					//System.out.println("the tpye choosen is"+type);
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
				frame.revalidate();																//updating frame
				frame.repaint();
			}
		});
		btnMergedImagesWorkDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i;
				for(i =0; i<listMerged.size();i++)
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
				frame.revalidate();
				frame.repaint();
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

