import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class ChatClient {
	
	private JTextArea output;
	private JTextField input;
	
	private JButton sendButton;
	private JButton quitButton;
	
	private JComboBox<String> username;
	
	private JDialog aboutDialog;
	private JFrame frame;
	private JScrollPane textPane;
	
	public ChatClient(){
		output = new JTextArea(10, 50);
		output.setEditable(false);
		input = new JTextField(50);
		sendButton = new JButton("Send");
		quitButton = new JButton("Quit");
		
		username = new JComboBox<String>();
		username.addItem("Andrey Pupkin");
		username.addItem("Pup88");
		username.addItem("Andy");
		
		textPane = new JScrollPane(output, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

	}
		
	private class SendHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String text = input.getText();
			output.append(username.getSelectedItem()+ ": "+text+"\n");
			input.setText("");
			output.setCaretPosition(output.getDocument().getLength()-1);
		}
	}
	
	private class CloseHandler extends WindowAdapter{
		public void windowClosing(WindowEvent e){
			System.exit(0);
		}
	}

	private class AboutHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if (aboutDialog == null){
				aboutDialog = new AboutDialog(frame, "About", true);
			}
			aboutDialog.setVisible(true);
		}
	}
	
	private class AboutDialog extends JDialog implements ActionListener{
		public AboutDialog(Frame parent, String title, boolean modal){
			super(parent, title, modal);
			add(new JLabel("talk" + "via"),BorderLayout.NORTH);
			JButton b = new JButton("OK");
			add(b,BorderLayout.SOUTH);
			b.addActionListener(this);
			pack();
		}
		public void actionPerformed(ActionEvent e){
			setVisible(false);
		}
	}
	
	public void launchFrame(){
		frame = new JFrame("Chat Room");
		
		JMenuBar mb = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu help = new JMenu("Help");
		JMenuItem aboutMenuItem = new JMenuItem("About");
		JMenuItem quitMenuItem = new JMenuItem("Quit");
		quitMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);				
			}
		});
		aboutMenuItem.addActionListener(new AboutHandler());
		file.add(quitMenuItem);
		help.add(aboutMenuItem);
		mb.add(file);
		mb.add(help);
		frame.setJMenuBar(mb);
		
		frame.setLayout(new BorderLayout());
		
		frame.add(output, BorderLayout.WEST);
		frame.add(input, BorderLayout.SOUTH);
		
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(3, 1));
		p1.add(sendButton);
		p1.add(quitButton);
		p1.add(username);
		
		frame.add(p1,BorderLayout.CENTER);
//		frame.add(textPane, BorderLayout.WEST);
		
		frame.pack();
		frame.setVisible(true);	
		
		sendButton.addActionListener(new SendHandler());
		input.addActionListener(new SendHandler());
		frame.addWindowListener(new CloseHandler());
		quitButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChatClient chat1 = new ChatClient();
		chat1.launchFrame();
	}

}
