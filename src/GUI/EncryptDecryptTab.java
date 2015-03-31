package GUI;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class EncryptDecryptTab extends JPanel {
	private JLabel lblPlaintextFile;
	private JButton btnBrowseFile;
	private JLabel lblPlaintext;
	private JTextArea textArea_2;
	private JLabel lblNewLabel_3;
	private JTextArea textArea_3;
	private JButton btnSaveCiphertext;
	private JButton button_1;
	private JLabel label_2;
	private JButton button_3;
	private Component label_4;
	private JLabel lblYourPrivateKey;
	private JLabel lblReceiverPublicKey;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JLabel lblNoFileSelected;
	private JScrollPane inputAreaScrollPane;
	private JScrollPane outputAreaScrollPane;

	public EncryptDecryptTab() {
		this.setLayout(null);
		
		lblPlaintextFile = new JLabel("Input file:");
		lblPlaintextFile.setBounds(10, 80, 70, 14);
		this.add(lblPlaintextFile);
		
		btnBrowseFile = new JButton("Browse file...");
		btnBrowseFile.setBounds(10, 105, 157, 23);
		this.add(btnBrowseFile);
		
		lblPlaintext = new JLabel("Input:");
		lblPlaintext.setBounds(10, 139, 46, 14);
		this.add(lblPlaintext);
		
		textArea_2 = new JTextArea();
		// textArea_2.setBounds(332, 164, 328, 126);
		// this.add(textArea_2);
		
		outputAreaScrollPane = new JScrollPane(textArea_2);
		outputAreaScrollPane.setBounds(332, 164, 328, 126);
		add(outputAreaScrollPane);
		
		lblNewLabel_3 = new JLabel("Output:");
		lblNewLabel_3.setBounds(332, 139, 60, 14);
		this.add(lblNewLabel_3);
		
		textArea_3 = new JTextArea();
		// textArea_3.setBounds(10, 164, 308, 126);
		// this.add(textArea_3);
		
		inputAreaScrollPane = new JScrollPane(textArea_3);
		inputAreaScrollPane.setBounds(10, 164, 308, 126);
		add(inputAreaScrollPane);
		
		btnSaveCiphertext = new JButton("Save output");
		btnSaveCiphertext.setBounds(552, 301, 108, 39);
		this.add(btnSaveCiphertext);
		
		button_1 = new JButton("Browse file...");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
			        try {

			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
			    }
			}
		});
		button_1.setBounds(10, 36, 157, 23);
		this.add(button_1);
		
		label_2 = new JLabel("No file selected.");
		label_2.setBounds(177, 40, 141, 14);
		this.add(label_2);
		
		button_3 = new JButton("Browse file...");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
			        try {

			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
			    }
			}
		});
		button_3.setBounds(332, 36, 157, 23);
		this.add(button_3);
		
		label_4 = new JLabel("No file selected.");
		label_4.setBounds(499, 40, 161, 14);
		this.add(label_4);
		
		lblYourPrivateKey = new JLabel("Your private key:");
		lblYourPrivateKey.setBounds(10, 11, 157, 14);
		this.add(lblYourPrivateKey);
		
		lblReceiverPublicKey = new JLabel("Receiver/Sender public key:");
		lblReceiverPublicKey.setBounds(332, 11, 189, 14);
		this.add(lblReceiverPublicKey);
		
		btnNewButton_1 = new JButton("Encrypt");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				encrypt();
			}
		});
		btnNewButton_1.setBounds(332, 301, 100, 39);
		this.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Decrypt");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				decrypt();
			}
		});
		btnNewButton_2.setBounds(442, 301, 100, 39);
		this.add(btnNewButton_2);
		
		lblNoFileSelected = new JLabel("No file selected.");
		lblNoFileSelected.setBounds(177, 109, 141, 14);
		this.add(lblNoFileSelected);
		
		
		btnSaveCiphertext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				save();
			}
		});
		btnBrowseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
			        try {

			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
			    }
			}
		});
	}

	public void encrypt(){}
	public void decrypt(){}
	public void save(){}
	
}
