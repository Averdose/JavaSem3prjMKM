import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
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

public class MainFrame extends JFrame {

	private final List<JButton> listImgs = new ArrayList<JButton>();
	private final List<JTextField> listDirs = new ArrayList<JTextField>();
	private JPanel contentPane;
	private  int j =0;
	int numberofdirs =0;
	static final String[] EXTENSIONS = new String[]{
	        "gif", "png", "bmp","jpeg","JPEG","jpg","JPG","BMP", "PNG" // and other formats you need
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
									
									if(button.getToolTipText() != "")
									{
										toolBar_1.add(button);
										button.setToolTipText("");
										
									}
									else
									{
										toolBar.add(button);
										button.setText("");
									}
									//	button.putClientProperty("selected", new Integer(1));
									//System.out.println(in);
									
									
									
									revalidate();
									repaint();
									/*
									ActionListener[] actions = button.getActionListeners();
									for(ActionListener a : actions)
										button.removeActionListener(a);
									
									button.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent arg0) {
											toolBar.add(button);
											
											revalidate();
											repaint();
										}
										});*/
								}
							});
							resized.flush();
							img.flush();
							
							
							//toolBar.add(button);	
							//button.putClientProperty("selected", new Integer(0));
						
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
		
	
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnStarFromThe)
									.addGap(18)
									.addComponent(refresh)
									.addGap(83)
									.addComponent(btnNewButton)
									.addGap(100))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 532, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 777, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(refresh)
						.addComponent(btnStarFromThe))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(scrollPane)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE))
					.addGap(18)
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
									JButton selectedButton = new JButton();
									selectedButton = button;
									toolBar_1.add(button);
									revalidate();
									repaint();
								}
							});
							
							
							listImgs.add(button);
							
							resized.flush();
							img.flush();
							
							toolBar.add(button);	
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