import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	private JButton buttonGrid[][] = new JButton[5][5];

	private int turtleRow = 0;
	private int turtleCol = 0;

	private Icon turtleIcon = new ImageIcon("images/boredTurtleSm.jpg");

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
		setBounds(100, 100, 450, 508);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);

		JMenuItem mntmUpgrade = new JMenuItem("Upgrade");
		mnAbout.add(mntmUpgrade);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel bottomPanel = new JPanel();
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new BorderLayout(0, 0));

		JButton upButton = new JButton("^");
		upButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				turtleRow = Math.max(0, turtleRow-1);
				refreshGrid();
			}
		});		
		bottomPanel.add(upButton, BorderLayout.NORTH);

		JButton downButton = new JButton("V");
		
		downButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				turtleRow = Math.min(4, turtleRow+1);
				refreshGrid();
			}
		});
		
		bottomPanel.add(downButton, BorderLayout.SOUTH);

		JButton leftButton = new JButton("<");
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				turtleCol = Math.max(0, turtleCol-1);
				refreshGrid();
			}
		});		
		bottomPanel.add(leftButton, BorderLayout.WEST);

		JButton rightButton = new JButton(">");
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				turtleCol = Math.min(4, turtleCol+1);
				refreshGrid();
			}
		});		
		bottomPanel.add(rightButton, BorderLayout.EAST);

		JButton centerButton = new JButton("CENTER");
		centerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				turtleRow = 2;
				turtleCol = 2;
				refreshGrid();
			}
		});
		bottomPanel.add(centerButton, BorderLayout.CENTER);

		JPanel turtleGrid = new JPanel();
		contentPane.add(turtleGrid, BorderLayout.CENTER);
		turtleGrid.setLayout(new GridLayout(5, 5, 0, 0));
		
		MouseAdapter gridClickListener = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JButton b = (JButton) e.getSource();
				for (int row=0; row < 5; row++) {
					for (int col=0; col < 5; col++) {
						if (b == buttonGrid[row][col]) {
							turtleRow = row;
							turtleCol = col;
							refreshGrid();
						}
					}
				}
			}
		};

		for (int row=0; row < 5; row++) {
			for (int col=0; col < 5; col++) {
				JButton nextButton = new JButton("");
				buttonGrid[row][col] = nextButton;
				nextButton.addMouseListener(gridClickListener);
				turtleGrid.add(nextButton);
			}
		}
		
		refreshGrid();
	}

	private void refreshGrid() {
		for (int row=0; row < 5; row++) {
			for (int col=0; col < 5; col++) {
				if ((turtleRow == row) && (turtleCol == col)) {
					buttonGrid[row][col].setIcon(turtleIcon);
				}
				else {
					buttonGrid[row][col].setIcon(null);

				}
			}
		}
	}
}
