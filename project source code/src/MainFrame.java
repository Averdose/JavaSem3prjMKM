import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import javax.swing.JRadioButton;

public class MainFrame extends JFrame {

	private int mergeMode =0;
	private int shadeMode =0;
	private int BWMode = 0;
	private List<String> listToMerge = new ArrayList<String>();
	private final List<JButton> listImgs = new ArrayList<JButton>();
	private final List<JTextField> listDirs = new ArrayList<JTextField>();
	private JPanel contentPane;
	private  int j =0;
	int numberofdirs =0;
	static final String[] EXTENSIONS = new String[]{
	        "tif","tiff", "png", "bmp","jpeg","JPEG","jpg","JPG","BMP", "PNG","zip" // and other formats you need
	    };

	static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {

        @Override
        public boolean accept(final File dir, final String name) {
            for (final String ext : EXTENSIONS) {
                if (name.endsWith("." + ext)) {
                    return (true);
                }
            }
            return (false);
        }
    };
	private JToolBar toolBar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 823, 653);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		final JPanel panel = new JPanel();
		JButton btnNewButton = new JButton("Load");
		final JScrollPane scrollPane = new JScrollPane();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		final JToolBar toolBar_1 = new JToolBar();
		scrollPane_1.setViewportView(toolBar_1);
		panel.setLayout(new GridLayout(20, 1, 0, 0));
		
		final JPanel panel_1 = new JPanel();
		
		JButton refresh = new JButton("fuck the refresh butt");
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				toolBar.removeAll();
				listImgs.clear();
				File dir;
				for(j =0;j<listDirs.size();j++)
				{
					dir = new File(listDirs.get(j).getText());
						if (dir.isDirectory()) {
						for (final File f : dir.listFiles(IMAGE_FILTER)) {
							System.out.println(f.getAbsolutePath());
							
							final JButton button = new JButton();
							BufferedImage img = null;
							button.setToolTipText(f.getAbsolutePath());
							try {
								img = ImageIO.read(f);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							
							BufferedImage resized = new BufferedImage(400, 400, img.getType());
							Graphics2D g = resized.createGraphics();
							g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
							g.drawImage(img, 0, 0, 400, 400, 0, 0, img.getWidth(),img.getHeight(), null);
							g.dispose();
							
							
							button.setIcon(new ImageIcon(resized));
							
							listImgs.add(button);
							
							button.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									
									//JButton selectedButton = new JButton(button);
									
									Integer in = (Integer)button.getClientProperty("selected");
									
									if(in ==0)
									{
										toolBar_1.add(button);
										listToMerge.add(button.getToolTipText());
										button.putClientProperty("selected",new Integer(1));
										
										
									}
									else
									{
										toolBar.add(button);
										for ( int i = 0;  i < listToMerge.size(); i++){
								            String tempName = listToMerge.get(i);
								            if(tempName.equals(button.getToolTipText())){
								                listToMerge.remove(i);
								            }
								        }
										button.putClientProperty("selected", new Integer(0));
									}
									
									
									
									revalidate();
									repaint();
									
								}
							});
							resized.flush();
							img.flush();
							
							
							toolBar.add(button);	
							button.putClientProperty("selected", new Integer(0));
						
						}
					}
				}
			}
		});
		
		JButton btnStarFromThe = new JButton("Star from the top");
		btnStarFromThe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toolBar.removeAll();
				toolBar_1.removeAll();
				listImgs.clear();
				listToMerge.clear();
				j=0;
				for(int i =0;i<listDirs.size();i++)
				{
					listDirs.get(i).setText("");
					listDirs.get(i).setVisible(false);
				}
				revalidate();
				repaint();
			}
		});
		
		JButton btnMergeThisShit = new JButton("Merge this Shit");
		btnMergeThisShit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ImageMerger imgMerger1 = new ImageMerger(mergeMode);
				BufferedImage img = null;
				if(!listToMerge.isEmpty())
				{
					//for(int i =0; i<listToMerge.size();i++)
					//	System.out.println(listToMerge.get(i));
					if(shadeMode ==2)
						img = imgMerger1.Merge(listToMerge);
					else if(shadeMode == 0)
					{
						if(BWMode ==1)
						img = imgMerger1.MergeAndFade(listToMerge, true, 0);	
						else
						img = imgMerger1.MergeAndFade(listToMerge, false, 0);
					}
					else
					{
						if(BWMode == 1)
						img = imgMerger1.MergeAndShade(listToMerge, true, 0);
						else
						img = imgMerger1.MergeAndShade(listToMerge, false, 0);
					}
					BufferedImage resized = new BufferedImage(200, 200, img.getType());
					Graphics2D g = resized.createGraphics();
					g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
					g.drawImage(img, 0, 0, 200, 200, 0, 0, img.getWidth(),img.getHeight(), null);
					g.dispose();
					
					
					JLabel picLabel = new JLabel(new ImageIcon(resized));
					panel_1.removeAll();
					panel_1.add(picLabel);
					
					resized.flush();
					img.flush();
					revalidate();
					repaint();
				}
			}
		});
		
		JButton btnQuickmergeThisShit = new JButton("QuickMerge this shit");
		btnQuickmergeThisShit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageMerger imgMerger1 = new ImageMerger(mergeMode);
				List<String> listStrings = new ArrayList<String>();
				if(!listDirs.isEmpty())
				{
					for(int i =0; i< listDirs.size();i++)
						listStrings.add(listDirs.get(i).getText());
					
					List<BufferedImage> img = new ArrayList<BufferedImage>();
					
					if(shadeMode ==2)
						img = imgMerger1.DirectoryMerge(listStrings);
					else if(shadeMode == 0)
					{
						if(BWMode ==1)
						img = imgMerger1.DirectoryMergeAndFade(listStrings, true, 0);	
						else
						img = imgMerger1.DirectoryMergeAndFade(listStrings, false, 0);
					}
					else
					{
						if(BWMode == 1)
						img = imgMerger1.DirectoryMergeAndShade(listStrings, true, 0);
						else
						img = imgMerger1.DirectoryMergeAndShade(listStrings, false, 0);
					}
					
					
					panel_1.removeAll();
					for(int i =0; i<img.size();i++)
					{
						BufferedImage resized = new BufferedImage(200, 200, img.get(i).getType());
						Graphics2D g = resized.createGraphics();
						g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
						g.drawImage(img.get(i), 0, 0, 200, 200, 0, 0, img.get(i).getWidth(),img.get(i).getHeight(), null);
						g.dispose();
						JLabel picLabel = new JLabel(new ImageIcon(resized));
						panel_1.add(picLabel);
						
					}
					revalidate();
					repaint();
				}
			}
		});
		
		JRadioButton rdbtnOr = new JRadioButton("or");
		rdbtnOr.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				mergeMode = 1;
			}
		});
		
		JRadioButton rdbtnXor = new JRadioButton("xor");
		rdbtnXor.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				mergeMode = 0;
			}
		});
		JRadioButton rdbtnAnd = new JRadioButton("and");
		rdbtnAnd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				mergeMode = 2;
			}
		});
		  ButtonGroup group = new ButtonGroup();
		    group.add(rdbtnOr);
		    group.add(rdbtnXor);
		    group.add(rdbtnAnd);
		
		JRadioButton rdbtnFading = new JRadioButton("fading");
		rdbtnFading.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				shadeMode = 0;
			}
		});
		
		JRadioButton rdbtnShading = new JRadioButton("shading");
		
		rdbtnShading.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				shadeMode = 1;
			}
		});
		JRadioButton rdbtnNormal = new JRadioButton("normal");
		rdbtnNormal.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				shadeMode = 2;
			}
		});
		 ButtonGroup groupShade = new ButtonGroup();
		    groupShade.add(rdbtnFading);
		    groupShade.add(rdbtnShading);
		    groupShade.add(rdbtnNormal);
		
		JRadioButton rdbtnBlackOnWhite = new JRadioButton("black on white");
		rdbtnBlackOnWhite.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				BWMode = 1;
			}
		});
		
		JRadioButton rdbtnWhiteOnBlack = new JRadioButton("white on black");
		rdbtnWhiteOnBlack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				BWMode = 0;
			}
		});
		ButtonGroup groupWB = new ButtonGroup();
	    groupWB.add(rdbtnWhiteOnBlack);
	    groupWB.add(rdbtnBlackOnWhite);
		
	
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(panel, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(18)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(rdbtnNormal)
												.addComponent(rdbtnShading)
												.addComponent(rdbtnFading)
												.addComponent(rdbtnXor)
												.addComponent(rdbtnAnd)
												.addComponent(rdbtnOr)))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(rdbtnBlackOnWhite))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(rdbtnWhiteOnBlack))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnQuickmergeThisShit)
									.addGap(18)
									.addComponent(btnMergeThisShit)
									.addGap(15)))
							.addGap(26)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnStarFromThe)
									.addGap(18)
									.addComponent(refresh)
									.addGap(83)
									.addComponent(btnNewButton)
									.addGap(100))
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 615, GroupLayout.PREFERRED_SIZE)))
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 777, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(refresh)
						.addComponent(btnStarFromThe)
						.addComponent(btnMergeThisShit)
						.addComponent(btnQuickmergeThisShit))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 385, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(30)
									.addComponent(rdbtnAnd)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(rdbtnOr)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(rdbtnXor)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(rdbtnFading))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(18)
									.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(rdbtnShading)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(rdbtnNormal)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(rdbtnBlackOnWhite)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(rdbtnWhiteOnBlack)))))
					.addGap(15)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		

		for(;numberofdirs<20;numberofdirs++)
		{
			listDirs.add(new JTextField());
			panel.add(listDirs.get(numberofdirs));
			listDirs.get(numberofdirs).setColumns(10);
			listDirs.get(numberofdirs).setVisible(false);
		}
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.setAcceptAllFileFilterUsed(false);				    
					
				
				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					
					File dir = fileChooser.getSelectedFile();
					listDirs.get(j).setText(dir.getAbsolutePath());
					listDirs.get(j).setVisible(true);
					j++;
					
					
					if (dir.isDirectory()) {
						for (final File f : dir.listFiles(IMAGE_FILTER)) {
							System.out.println(f.getAbsolutePath());
							if(f.getAbsolutePath().endsWith(".zip"))
							{
								//Opening Zip files here
							}
							
							final JButton button = new JButton();
							BufferedImage img = null;
							button.setToolTipText(f.getAbsolutePath());
							try {
								img = ImageIO.read(f);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							
							BufferedImage resized = new BufferedImage(400, 400, img.getType());
							Graphics2D g = resized.createGraphics();
							g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
							g.drawImage(img, 0, 0, 400, 400, 0, 0, img.getWidth(),img.getHeight(), null);
							g.dispose();
							
							
							button.setIcon(new ImageIcon(resized));
							
							button.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									
									//JButton selectedButton = new JButton(button);
									
									Integer in = (Integer)button.getClientProperty("selected");
									
									if(in ==0)
									{
										toolBar_1.add(button);
										listToMerge.add(button.getToolTipText());
										button.putClientProperty("selected",new Integer(1));
										
										
									}
									else
									{
										toolBar.add(button);
										for ( int i = 0;  i < listToMerge.size(); i++){
								            String tempName = listToMerge.get(i);
								            if(tempName.equals(button.getToolTipText())){
								                listToMerge.remove(i);
								            }
								        }

										button.putClientProperty("selected", new Integer(0));
									}
									
									
									
									revalidate();
									repaint();
									
								}
							});
							
							
							listImgs.add(button);
							
							resized.flush();
							img.flush();
							
							toolBar.add(button);	
							button.putClientProperty("selected", new Integer(0));
						}
					}
			   }
			}
		});
		
		
		
		

		
		toolBar = new JToolBar();
		scrollPane.setViewportView(toolBar);
		contentPane.setLayout(gl_contentPane);
	}
}