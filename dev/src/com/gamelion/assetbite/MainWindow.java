package com.gamelion.assetbite;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.AbstractListModel;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTree;

public class MainWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 545, 384);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		
		JSplitPane splitPaneMain = new JSplitPane();
		splitPaneMain.setResizeWeight(0.5);
		
		JPanel panel_1 = new JPanel();
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
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(splitPaneMain, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
		);
		
		JSplitPane splitPaneLeft = new JSplitPane();
		splitPaneLeft.setResizeWeight(0.25);
		splitPaneMain.setLeftComponent(splitPaneLeft);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPaneLeft.setRightComponent(scrollPane);
		
		JTree tree = new JTree();
		scrollPane.setViewportView(tree);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.5);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPaneLeft.setLeftComponent(splitPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane.setLeftComponent(scrollPane_1);
		
		JList list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"iPhone Premium", "iPhone Free", "Andoid Amazon Premium", "Android GP Free", "Android Nook"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane_1.setViewportView(list);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		splitPane.setRightComponent(scrollPane_2);
		
		JList list_1 = new JList();
		list_1.setModel(new AbstractListModel() {
			String[] values = new String[] {"common", "sounds", "scripts", "assets_1024x768", "assets_320x480"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane_2.setViewportView(list_1);
		
		JSplitPane splitPaneRight = new JSplitPane();
		splitPaneRight.setResizeWeight(0.25);
		splitPaneMain.setRightComponent(splitPaneRight);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		splitPaneRight.setRightComponent(scrollPane_3);
		
		JTree tree_1 = new JTree();
		scrollPane_3.setViewportView(tree_1);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		splitPaneRight.setLeftComponent(scrollPane_4);
		
		JList list_2 = new JList();
		scrollPane_4.setViewportView(list_2);
		frame.getContentPane().setLayout(groupLayout);
	}

}
