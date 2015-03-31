package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import algo.ECC;
import algo.Point;

import java.io.FileWriter;

public class GenerateTab extends JPanel{
	
	private JTextField paramA;
	private JTextField paramB;
	private JTextField paramP;
	private JTextField paramBaseX;
	private JTextField paramBaseY;
	private JButton button;
	private JLabel label;
	private JTextArea privateKeyTextArea;
	private JButton button_2;
	private JButton btnNewButton_5;
	private JLabel lblNewLabel;
	private JTextArea publicKeyTextArea;
	private JButton btnNewButton;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblBasePoint;

	public GenerateTab() {
		this.setLayout(null);
		
		button = new JButton("Generate new private key");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				generatePrivateKey(Long.parseLong(paramP.getText()));
			}
		});
		
		button.setBounds(10, 89, 201, 23);
		this.add(button);
		
		label = new JLabel("Your private key:");
		label.setBounds(10, 119, 108, 14);
		this.add(label);
		
		privateKeyTextArea = new JTextArea();
		privateKeyTextArea.setBounds(10, 136, 331, 136);
		this.add(privateKeyTextArea);
		
		button_2 = new JButton("Save private key");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				int returnVal = fc.showSaveDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
			        try {
			            FileWriter fw = new FileWriter(fc.getSelectedFile() + ".priv");
			            fw.write(paramA.getText() + " " + paramB.getText() + " " + paramP.getText() + "\n");
			            fw.write(paramBaseX.getText() + " " + paramBaseY.getText() + "\n");
			            fw.write(privateKeyTextArea.getText() + "\n");
			            fw.close();
			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
			    }
			}
		});
		button_2.setBounds(10, 283, 201, 23);
		this.add(button_2);
		
		btnNewButton_5 = new JButton("Generate public key");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				generatePublicKey(Long.parseLong(privateKeyTextArea.getText()));
			}
		});
		btnNewButton_5.setBounds(351, 89, 201, 23);
		this.add(btnNewButton_5);
		
		lblNewLabel = new JLabel("Your public key:");
		lblNewLabel.setBounds(351, 119, 108, 14);
		this.add(lblNewLabel);
		
		publicKeyTextArea = new JTextArea();
		publicKeyTextArea.setBounds(351, 136, 331, 136);
		this.add(publicKeyTextArea);
		publicKeyTextArea.setEditable(false);
		
		btnNewButton = new JButton("Save public key");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				int returnVal = fc.showSaveDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
			        try {
			            FileWriter fw = new FileWriter(fc.getSelectedFile() + ".pub");
			            fw.write(paramA.getText() + " " + paramB.getText() + " " + paramP.getText() + "\n");
			            fw.write(paramBaseX.getText() + " " + paramBaseY.getText() + "\n");
			            fw.write(publicKeyTextArea.getText() + "\n");
			            fw.close();
			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
			    }
			}
		});
		btnNewButton.setBounds(351, 283, 201, 23);
		this.add(btnNewButton);
		
		lblNewLabel_4 = new JLabel("a:");
		lblNewLabel_4.setBounds(10, 11, 46, 14);
		this.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("b:");
		lblNewLabel_5.setBounds(177, 11, 46, 14);
		this.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("p:");
		lblNewLabel_6.setBounds(352, 11, 46, 14);
		this.add(lblNewLabel_6);
		
		lblBasePoint = new JLabel("Base point (x, y): ");
		lblBasePoint.setBounds(518, 11, 164, 14);
		this.add(lblBasePoint);
		
		paramA = new JTextField();
		paramA.setBounds(10, 33, 157, 20);
		this.add(paramA);
		paramA.setColumns(10);
		
		paramB = new JTextField();
		paramB.setBounds(177, 33, 164, 20);
		this.add(paramB);
		paramB.setColumns(10);
		
		paramP = new JTextField();
		paramP.setBounds(352, 33, 156, 20);
		this.add(paramP);
		paramP.setColumns(10);
		
		paramBaseX = new JTextField();
		paramBaseX.setBounds(518, 33, 164, 20);
		this.add(paramBaseX);
		paramBaseX.setColumns(10);
		
		paramBaseY = new JTextField();
		paramBaseY.setBounds(518, 58, 164, 20);
		this.add(paramBaseY);
		paramBaseY.setColumns(10);
	}
	
	private void generatePrivateKey(long p) {
		// test tambah
		// ECC.setParam(1, 6, 11, new Float(0, 0));
		// ECC.add(new Float(2, 4), new Float(5, 9));
		
		// test kali
		// ECC.setParam(2, 1, 5, new Point(0, 1));
		// System.out.println(ECC.times(2, new Point(0, 1)));
		
		// set param
		ECC.setParam(
			(long) Long.parseLong(paramA.getText()), 
			(long) Long.parseLong(paramB.getText()), 
			(long) Long.parseLong(paramP.getText()), 
			new Point(
				(long) Long.parseLong(paramBaseX.getText()), 
				(long) Long.parseLong(paramBaseY.getText())
			)
		);
		
		int privateKey = (int) (1 + (Math.random() * (p - 1)));
		privateKeyTextArea.setText(String.valueOf(privateKey));
		generatePublicKey(privateKey);
	}
	
	private void generatePublicKey(long privateKey){
		// set param
		ECC.setParam(
			(long) Long.parseLong(paramA.getText()), 
			(long) Long.parseLong(paramB.getText()), 
			(long) Long.parseLong(paramP.getText()), 
			new Point(
				(long) Long.parseLong(paramBaseX.getText()), 
				(long) Long.parseLong(paramBaseY.getText())
			)
		);
		
		Point publicKey = ECC.times(privateKey, ECC.basePoint);
		
		publicKeyTextArea.setText(publicKey.x + " " + publicKey.y);
	}
}
