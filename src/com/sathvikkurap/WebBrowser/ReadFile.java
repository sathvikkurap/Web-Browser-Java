package com.sathvikkurap.WebBrowser;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class ReadFile extends JFrame{
private boolean searched = false;
private int timessearched = -1;
	private JTextField addressBar;
	private JEditorPane display;
	ArrayList<String> cars = new ArrayList<String>();
	String https = "https://"; 
	File myObj = new File("searched.txt");
	//constructor
	public ReadFile() {
		super("Andy's Browser");
		
		addressBar = new JTextField("enter URL");
		addressBar.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
							loadBrowser(event.getActionCommand());
							
					}
				}
				);
		addressBar.addFocusListener(new FocusListener() {

	        @Override
	        public void focusGained(FocusEvent e) {
	           addressBar.setText("");
	           searched = true;
	        }

			@Override
			public void focusLost(FocusEvent e) {
				if(searched) {
					
				}
				else {
					addressBar.setText("enter URL");	
				}
			}
	    });
		add(addressBar, BorderLayout.NORTH);
		
		display = new JEditorPane();
		display.setEditable(false);
		display.addHyperlinkListener(
				new HyperlinkListener() {
					public void hyperlinkUpdate(HyperlinkEvent event) {
						if(event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
							loadBrowser(event.getURL().toString());
						}
					}
				}
				);
		add(new JScrollPane(display), BorderLayout.CENTER);
		setSize(500, 300);
		setVisible(true);
	}	
		//load browser
		private void loadBrowser(String userText){
			try {
				
				if(userText.indexOf(https) == 0) {
					userText = https + userText;
				}
				else {
				
				}
				timessearched++;
				display.setPage(userText);
				cars.add(userText);
				 FileWriter writer = new 	FileWriter("searched.txt");
				int size = cars.size();
		        
		            String str = cars.get(timessearched).toString();
		            writer.write(str + ", ");
		        
		        writer.close();
				System.out.println(cars);
				addressBar.setText(userText);
				
			}
			catch(Exception e) {
				System.out.println("Sorry, the URL you entered does not match any URL's on the web. Please try retyping your link or try again later.");
			}
		}
		public static void main(String []args) {
			ReadFile Browser = new ReadFile();
			Browser.setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
		
	
	
}
