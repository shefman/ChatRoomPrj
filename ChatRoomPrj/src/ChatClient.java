import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClient {
	
	private JTextArea output;
	private JTextField input;
	private JButton sendButton;
	private JButton quitButton;
	
	public ChatClient(){
		output = new JTextArea(10, 50);
		output.setEditable(false);
		input = new JTextField(50);
		sendButton = new JButton("Send");
		quitButton = new JButton("Quit");
	}
	
	private class SendHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String text = input.getText();
			output.append(text+"\n");
			input.setText("");
		}
	}
	
	private class CloseHandler extends WindowAdapter{
		public void windowClosing(WindowEvent e){
			System.exit(0);
		}
	}

	
	public void launchFrame(){
		JFrame frame = new JFrame("Chat Room");
		
		frame.setLayout(new BorderLayout());
		
		frame.add(output, BorderLayout.WEST);
		frame.add(input, BorderLayout.SOUTH);
		
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(2, 1));
		p1.add(sendButton);
		p1.add(quitButton);
		
		frame.add(p1,BorderLayout.CENTER);
		
		frame.pack();
		frame.setVisible(true);	
		
		sendButton.addActionListener(new SendHandler());
		input.addActionListener(new SendHandler());
		frame.addWindowListener(new CloseHandler());
		quitButton.addActionListener(new ActionListener() {
			
			@Override
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
