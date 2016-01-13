import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartWin {

	private JFrame frame;
	private JTextField tfOperationsPreviewWorkPath;

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
		frame = new JFrame();
		frame.setBounds(277, 100, 1366, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel plMenu = new JPanel();
		plMenu.setBounds(0, 20, 1366, 40);
		frame.getContentPane().add(plMenu);
		plMenu.setBackground(new Color(192, 192, 192));
		plMenu.setLayout(null);
		
		JButton btnOperationsOperations = new JButton("Manual Merge");
		btnOperationsOperations.setBounds(455, 0, 455, 40);
		plMenu.add(btnOperationsOperations);
		
		JButton btnOperationsMergedImages = new JButton("Marged Images");
		btnOperationsMergedImages.setBounds(910, 0, 456, 40);
		plMenu.add(btnOperationsMergedImages);
		
		JButton btnOperationsFastMerge = new JButton("Fast Merge");
		btnOperationsFastMerge.setBounds(0, 0, 455, 40);
		plMenu.add(btnOperationsFastMerge);
		
		JPanel plMain = new JPanel();
		plMain.setBounds(0, 60, 1366, 678);
		frame.getContentPane().add(plMain);
		plMain.setLayout(new CardLayout(0, 0));
		
		final JPanel plOperations = new JPanel();
		plOperations.setVisible(false);
		plMain.add(plOperations, "name_77612886309962");
		plOperations.setLayout(null);
		
		JPanel plOperationsImages = new JPanel();
		plOperationsImages.setBackground(new Color(192, 192, 192));
		plOperationsImages.setBounds(0, 0, 1092, 640);
		plOperations.add(plOperationsImages);
		plOperationsImages.setLayout(null);
		
		JPanel plOperationsListImagesOptions = new JPanel();
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
		
		JScrollPane scrollPaneListImages = new JScrollPane();
		scrollPaneListImages.setBounds(312, 6, 774, 409);
		plOperationsImages.add(scrollPaneListImages);
		
		JToolBar tbListImages = new JToolBar();
		tbListImages.setFloatable(false);
		scrollPaneListImages.setViewportView(tbListImages);
		
		JScrollPane scrollPaneListToMarge = new JScrollPane();
		scrollPaneListToMarge.setBounds(6, 420, 1080, 170);
		plOperationsImages.add(scrollPaneListToMarge);
		
		JPanel plOperationsListPath = new JPanel();
		plOperationsListPath.setBounds(6, 6, 300, 409);
		plOperationsImages.add(plOperationsListPath);
		plOperationsListPath.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel plOperationsWork = new JPanel();
		plOperationsWork.setBackground(new Color(102, 204, 51));
		plOperationsWork.setBounds(1092, 0, 274, 640);
		plOperations.add(plOperationsWork);
		plOperationsWork.setLayout(null);
		
		JButton btnOperationsWorkLoad = new JButton("LOAD");
		btnOperationsWorkLoad.setBounds(0, 6, 274, 100);
		plOperationsWork.add(btnOperationsWorkLoad);
		
		JButton btnOperationsWorkRefresh = new JButton("Refresh");
		btnOperationsWorkRefresh.setBounds(0, 106, 274, 40);
		plOperationsWork.add(btnOperationsWorkRefresh);
		
		JButton btnOperationsWorkMerge = new JButton("MERGE");
		btnOperationsWorkMerge.setBounds(0, 420, 274, 170);
		plOperationsWork.add(btnOperationsWorkMerge);
		
		JPanel plOperationsPreview = new JPanel();
		plOperationsPreview.setVisible(false);
		plOperationsPreview.setBackground(Color.WHITE);
		plOperationsPreview.setBounds(0, 0, 1092, 640);
		plOperations.add(plOperationsPreview);
		plOperationsPreview.setLayout(null);
		
		JButton imgOperationsPreview = new JButton("ImageToView");
		imgOperationsPreview.setBounds(146, 5, 800, 590);
		plOperationsPreview.add(imgOperationsPreview);
		
		JPanel plOperationsPreviewWork = new JPanel();
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
		
		final JPanel plMergedImages = new JPanel();
		plMergedImages.setVisible(false);
		plMain.add(plMergedImages, "name_77604441665075");
		plMergedImages.setLayout(null);
		
		JPanel plMergedImagesView = new JPanel();
		plMergedImagesView.setBounds(0, 0, 1092, 640);
		plMergedImages.add(plMergedImagesView);
		plMergedImagesView.setLayout(null);
		
		JButton btnMergedImagesViewView = new JButton("MergedImage");
		btnMergedImagesViewView.setBounds(6, 6, 1080, 534);
		plMergedImagesView.add(btnMergedImagesViewView);
		
		JScrollPane scrollPaneListMergedImages = new JScrollPane();
		scrollPaneListMergedImages.setBounds(6, 546, 1080, 40);
		plMergedImagesView.add(scrollPaneListMergedImages);
		
		JToolBar tbListMergedImages = new JToolBar();
		scrollPaneListMergedImages.setViewportView(tbListMergedImages);
		
		JPanel plMergedImagesWork = new JPanel();
		plMergedImagesWork.setBounds(1092, 0, 274, 640);
		plMergedImages.add(plMergedImagesWork);
		plMergedImagesWork.setBackground(new Color(204, 153, 51));
		plMergedImagesWork.setLayout(null);
		
		JButton btnMergedImagesWorkSave = new JButton("SAVE");
		btnMergedImagesWorkSave.setBounds(0, 440, 274, 100);
		plMergedImagesWork.add(btnMergedImagesWorkSave);
		
		JButton btnMergedImagesWorkDelete = new JButton("DELETE");
		btnMergedImagesWorkDelete.setBounds(0, 546, 274, 40);
		plMergedImagesWork.add(btnMergedImagesWorkDelete);
		
		final JPanel plFastMerge = new JPanel();
		plMain.add(plFastMerge, "name_83171862252583");
		plFastMerge.setLayout(null);
		
		JPanel plFastMergeFiles = new JPanel();
		plFastMergeFiles.setBounds(0, 0, 1092, 640);
		plFastMerge.add(plFastMergeFiles);
		plFastMergeFiles.setLayout(null);
		
		JScrollPane scrollPaneListFiles = new JScrollPane();
		scrollPaneListFiles.setBounds(6, 6, 1080, 409);
		plFastMergeFiles.add(scrollPaneListFiles);
		
		JToolBar tbListFiles = new JToolBar();
		scrollPaneListFiles.setViewportView(tbListFiles);
		
		JScrollPane scrollPaneListFilesToMerge = new JScrollPane();
		scrollPaneListFilesToMerge.setBounds(6, 420, 1080, 170);
		plFastMergeFiles.add(scrollPaneListFilesToMerge);
		
		JToolBar tbListFilesToMerge = new JToolBar();
		scrollPaneListFilesToMerge.setViewportView(tbListFilesToMerge);
		
		JPanel plFastMergeWork = new JPanel();
		plFastMergeWork.setBackground(new Color(51, 102, 204));
		plFastMergeWork.setBounds(1092, 0, 274, 640);
		plFastMerge.add(plFastMergeWork);
		plFastMergeWork.setLayout(null);
		
		JButton btnFastMergeWorkLoad = new JButton("Load files");
		btnFastMergeWorkLoad.setBounds(0, 6, 274, 100);
		plFastMergeWork.add(btnFastMergeWorkLoad);
		
		JButton btnFastMergeWorkMerge = new JButton("New button");
		btnFastMergeWorkMerge.setBounds(0, 420, 274, 170);
		plFastMergeWork.add(btnFastMergeWorkMerge);
		
		JPanel plShade = new JPanel();
		plShade.setVisible(false);
		plShade.setBounds(0, 20, 1366, 660);
		frame.getContentPane().add(plShade);
		plShade.setBackground(new Color(0, 0, 0, 120));
		plShade.setLayout(null);
		
		JPanel plAboutUs = new JPanel();
		plAboutUs.setBounds(433, 50, 500, 500);
		plShade.add(plAboutUs);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1366, 20);
		frame.getContentPane().add(menuBar);
		
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
		
		JMenuItem mntmCreators = new JMenuItem("Creators");
		mnAbout.add(mntmCreators);
		
		JMenuItem mntmThanks = new JMenuItem("Thanks");
		mnAbout.add(mntmThanks);
		
		JMenuItem mntmProgramo = new JMenuItem("Program");
		mnAbout.add(mntmProgramo);
		
		btnOperationsFastMerge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plFastMerge.setVisible(true);
				plOperations.setVisible(false);
				plMergedImages.setVisible(false);
			}
		});
		
		btnOperationsOperations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plFastMerge.setVisible(false);
				plOperations.setVisible(true);
				plMergedImages.setVisible(false);
			}
		});
		
		btnOperationsMergedImages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plFastMerge.setVisible(false);
				plOperations.setVisible(false);
				plMergedImages.setVisible(true);
			}
		});
	}
}
