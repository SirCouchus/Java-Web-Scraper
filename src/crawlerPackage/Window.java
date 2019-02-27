package crawlerPackage;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class Window extends JFrame implements ActionListener {

	JPanel pnl = new JPanel();
	
	String[] languages = {"Java", "Python", "PHP", "C++", "JavaScript"};
	
	JCheckBox chk1 = new JCheckBox(languages[0]);
	JCheckBox chk2 = new JCheckBox(languages[1]);
	JCheckBox chk3 = new JCheckBox(languages[2]);
	JCheckBox chk4 = new JCheckBox(languages[3]);
	JCheckBox chk5 = new JCheckBox(languages[4]);
	
	String[] websites = {"Total Jobs", "Indeed"};
	
	JComboBox<String> box1 = new JComboBox<String>(websites);
	
	
	JTextField postCode = new JTextField();
	final JComponent[] input = new JComponent[] {
			new JLabel("Post code"), postCode
	};
	int result = JOptionPane.showConfirmDialog(null, input, "Post Code", JOptionPane.PLAIN_MESSAGE);
	
	JList<String> lst1 = new JList<String>(languages);
	
	JButton btn1 = new JButton("GO");
	JButton btn2 = new JButton("Change Post Code");
	public JTextArea txtArea = new JTextArea(5, 45); //parameters are height and length, respectively
	
	String enteredPostCode = postCode.getText().toString().toLowerCase();
	
	public Window() {
		super("Language finder");
		setSize(600, 400);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pnl.add(btn1);
		btn1.setEnabled(true);
		txtArea.setEditable(false);
		pnl.add(txtArea);
		txtArea.setText("Select the languages you want and then click GO and wait...");
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		chk1.setBackground(new Color(245, 245, 250));
		chk2.setBackground(new Color(245, 245, 250));
		chk3.setBackground(new Color(245, 245, 250));
		chk4.setBackground(new Color(245, 245, 250));
		chk5.setBackground(new Color(245, 245, 250));
		box1.setBackground(new Color(245, 245, 250));
		pnl.add(chk1);
		pnl.add(chk2);
		pnl.add(chk3);
		pnl.add(chk4);
		pnl.add(chk5);
		box1.setSelectedIndex(0);
		pnl.add(box1);
		pnl.add(lst1);
		pnl.add(Spider.progressBar);
		pnl.add(btn2);
		pnl.setBackground(new Color(245, 245, 250));
		if(result == JOptionPane.OK_OPTION) {
			txtArea.setText("You entered " + 
			postCode.getText());
		} else {
			txtArea.setText("Cancelled " + result);
		}
		add(pnl);
		setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String txt = "";
		
		if(event.getSource() == btn2) {
			JTextField postCode = new JTextField();
			final JComponent[] input = new JComponent[] {
					new JLabel("Post code"), postCode
			};
			int result = JOptionPane.showConfirmDialog(null, input, "Post Code", JOptionPane.PLAIN_MESSAGE);
			txtArea.setText(event.getActionCommand() + " clicked\n"
					+ txt);
			enteredPostCode = postCode.getText().toString().toLowerCase();
		}
		
		
		if(event.getSource() == btn1) {
			btn1.setEnabled(true);
			String totalJobs = "https://www.totaljobs.com/jobs/software/in-" + enteredPostCode +  "?radius=10";
			String indeed = "https://www.indeed.co.uk/jobs?q=Software&l=" + enteredPostCode;
			
			boolean totalJobsTrue = box1.getSelectedItem().equals("Total Jobs");
			Spider[] spider = new Spider[10];
			if(chk1.isSelected() && totalJobsTrue) {
				spider[0] = new Spider();
				spider[0].search(totalJobs, "Java");
				
				txt += spider[0].instanceCount + " instances of " + spider[0].word + "\n";
			}else if(chk1.isSelected() && !totalJobsTrue){
				spider[1] = new Spider();
				spider[1].search(indeed, "Java");
				
				txt += spider[1].instanceCount + " instances of " + spider[1].word + "\n";
			}
			if(chk2.isSelected() && totalJobsTrue) {
				spider[2] = new Spider();
				spider[2].search(totalJobs, "Python");
				
				txt += spider[2].instanceCount + " instances of " + spider[2].word + "\n";
			} else if (chk2.isSelected() && !totalJobsTrue) {
				spider[3] = new Spider();
				spider[3].search(indeed, "Python");
				
				txt += spider[3].instanceCount + " instances of " + spider[3].word + "\n";
			}
			if(chk3.isSelected() && totalJobsTrue) {
				spider[4] = new Spider();
				spider[4].search(totalJobs, "PHP");
				
				txt += spider[4].instanceCount + " instances of " + spider[4].word + "\n";
			} else if(chk3.isSelected() && !totalJobsTrue){
				spider[5] = new Spider();
				spider[5].search(indeed, "PHP");
				
				txt += spider[5].instanceCount + " instances of " + spider[5].word + "\n";
			}
			if(chk4.isSelected() && totalJobsTrue) {
				spider[6] = new Spider();
				spider[6].search(totalJobs, "C++");
				
				txt += spider[6].instanceCount + " instances of " + spider[6].word + "\n";
			}else if (chk4.isSelected() && !totalJobsTrue){
				spider[7] = new Spider();
				spider[7].search(indeed, "C++");
				
				txt += spider[7].instanceCount + " instances of " + spider[7].word + "\n";
			}
			if(chk5.isSelected() && totalJobsTrue) {
				spider[8] = new Spider();
				spider[8].search(totalJobs, "JavaScript");
				
				txt += spider[8].instanceCount + " instances of " + spider[8].word + "\n";
			} else if(chk5.isSelected() && !totalJobsTrue){
				spider[9] = new Spider();
				spider[9].search(indeed, "JavaScript");
				
				txt += spider[9].instanceCount + " instances of " + spider[9].word + "\n";
			}
			txtArea.setText(event.getActionCommand() + " clicked\n"
					+ txt);	
		}
		
	}
	

}
