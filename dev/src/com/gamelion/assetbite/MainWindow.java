package com.gamelion.assetbite;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.AbstractListModel;
import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.gamelion.assetbite.model.ProjectLoader;
import com.gamelion.assetbite.model.rootdirectory.DirectoryDataModel;

import java.awt.FlowLayout;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import javax.swing.JButton;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.gamelion.assetbite.control.rootdirectory.*;
import com.gamelion.assetbite.gui.NameChangeWindow;
import com.gamelion.assetbite.gui.RootDirectory;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import com.gamelion.assetbite.model.elements.*;
import com.gamelion.assetbite.gui.TargetList;
import javax.swing.BoxLayout;
import javax.swing.JToolBar;
import com.gamelion.assetbite.gui.PacksList;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import java.awt.Toolkit;
import java.awt.Dimension;

public class MainWindow implements GuiNotifier.ObserverProjectName{

	private static JFrame frame;
	private JLabel labelProjectName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	
	public static JFrame GetFrame() {
		return frame;
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
		GuiNotifier.getInstance().setObserverProjectName(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/javax/swing/plaf/metal/icons/ocean/info.png")));
		frame.setBounds(100, 100, 846, 449);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		
		JSplitPane splitPaneMain = new JSplitPane();
		splitPaneMain.setDividerSize(4);
		splitPaneMain.setResizeWeight(0.5);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(0);
		panel_1.setBackground(Color.GRAY);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
				.addComponent(splitPaneMain, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(splitPaneMain, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
		);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Load");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainControl.getInstance().LoadProject();
			}
		});
		btnNewButton.setBackground(UIManager.getColor("ArrowButton.background"));
		btnNewButton.setBounds(87, 25, 90, 28);
		panel.add(btnNewButton);
		
	    labelProjectName = new JLabel("No Name");
		labelProjectName.setForeground(new Color(255, 255, 255));
		labelProjectName.setBounds(6, 6, 252, 16);
		panel.add(labelProjectName);
		
		JSplitPane splitPaneLeft = new JSplitPane();
		splitPaneLeft.setPreferredSize(new Dimension(0, 0));
		splitPaneLeft.setMinimumSize(new Dimension(0, 0));
		splitPaneLeft.setDividerSize(4);
		splitPaneLeft.setResizeWeight(0.5);
		splitPaneMain.setLeftComponent(splitPaneLeft);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setPreferredSize(new Dimension(0, 0));
		splitPane.setMaximumSize(new Dimension(0, 0));
		splitPane.setMinimumSize(new Dimension(0, 0));
		splitPane.setDividerSize(4);
		splitPane.setResizeWeight(0.5);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPaneLeft.setLeftComponent(splitPane);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setPreferredSize(new Dimension(0, 0));
		scrollPane_2.setMinimumSize(new Dimension(25, 0));
		splitPane.setRightComponent(scrollPane_2);
		
		PacksList packsList = new PacksList();
		scrollPane_2.setViewportView(packsList);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setPreferredSize(new Dimension(0, 0));
		splitPane.setLeftComponent(scrollPane_1);
		
		TargetList targetList = new TargetList();
		scrollPane_1.setViewportView(targetList);
		targetList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JLayeredPane layeredPane = new JLayeredPane();
		splitPaneLeft.setRightComponent(layeredPane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setMinimumSize(new Dimension(0, 0));
		panel_2.setMaximumSize(new Dimension(0, 0));
		panel_2.setPreferredSize(new Dimension(0, 0));
		panel_2.setBackground(new Color(0, 0, 255));
		layeredPane.setLayer(panel_2, 5);
		
		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(0, 0));
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		panel_3.setBackground(new Color(0, 255, 0));
		layeredPane.setLayer(panel_3, 3);
		GroupLayout gl_layeredPane = new GroupLayout(layeredPane);
		gl_layeredPane.setHorizontalGroup(
			gl_layeredPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
				.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
		);
		gl_layeredPane.setVerticalGroup(
			gl_layeredPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
				.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
		);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setMinimumSize(new Dimension(0, 0));
		scrollPane.setMaximumSize(new Dimension(0, 0));
		scrollPane.setPreferredSize(new Dimension(0, 0));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
		);
		
		RootDirectory tree = new RootDirectory();
		tree.setMaximumSize(new Dimension(0, 0));
		tree.setPreferredSize(new Dimension(0, 0));
		tree.setBorder(null);
		scrollPane.setViewportView(tree);
		panel_2.setLayout(gl_panel_2);
		layeredPane.setLayout(gl_layeredPane);
		
		JSplitPane splitPaneRight = new JSplitPane();
		splitPaneRight.setPreferredSize(new Dimension(0, 0));
		splitPaneRight.setDividerSize(4);
		splitPaneRight.setResizeWeight(0.25);
		splitPaneMain.setRightComponent(splitPaneRight);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setPreferredSize(new Dimension(0, 0));
		splitPaneRight.setRightComponent(scrollPane_3);
		
		JTree tree_1 = new JTree();
		scrollPane_3.setViewportView(tree_1);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setPreferredSize(new Dimension(0, 0));
		splitPaneRight.setLeftComponent(scrollPane_4);
		
		JList list_2 = new JList();
		scrollPane_4.setViewportView(list_2);
		frame.getContentPane().setLayout(groupLayout);
	}


	@Override
	public void ObserverProjectNameChange(String name) {
		labelProjectName.setText(name);
	}
	
}
