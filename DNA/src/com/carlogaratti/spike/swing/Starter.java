package com.carlogaratti.spike.swing;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

import com.carlogaratti.dna.AbstractWriter;
import com.carlogaratti.dna.Element;
import com.carlogaratti.project.bank_statement.Item;
import com.carlogaratti.project.bank_statement.ItemStringDefault;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Flow;

import javax.swing.*;
import java.awt.event.*;

public class Starter {
	
	
	final static int FIELD_WIDTH = 10; 
	private static Map<String, String> aspectOnCommand = new HashMap<String, String>();
	private static Map<String, Map<String,Object>> aspectOnElement = new HashMap<String, Map<String,Object>>();

	public static void main(String[] args) {
		//Create a JFrame Object
		JFrame frm = new JFrame();
		 
		//Create label object and set the position 
		JLabel lbl = new JLabel("Pleas enter a number:", JLabel.LEFT);
		lbl.setBounds(80,20,60,20);
		//Add label to frame
		frm.add(lbl);

		//Create text object and set position
		JTextField moltiplicando = new JTextField();
		
		aspectOnElement.put("getMoltiplicando", Map.of("element",moltiplicando, "type", "awtTextInput")); // DA METTERE A POSTO DA QUI!!!!!
		
		aspectOnCommand.put("getMoltiplicando", "GETForTF");
		moltiplicando.setBounds(210,20,200,20);
		frm.add(moltiplicando);
		
		
		
		//Create label object and set the position 
		JLabel logLabel = new JLabel("Log:", JLabel.LEFT);
		
		logLabel.setBounds(80,60,60,20);
		//Add label to frame
		frm.add(logLabel);
		      
		//Create the radio buttons
		JRadioButton yes = new JRadioButton("Yes");
		aspectOnElement.put("getLogYes", Map.of("element",yes, "type", "awtRadioInput"));
		aspectOnCommand.put("getLogYes","GETforRBYes");
		yes.setBounds(170,45,60,50);  
		
		JRadioButton no = new JRadioButton("No");
		//aspectOnElement.put("getLogNo", Map.of("element",no, "type", "awtRadioInput"));
		//aspectOnCommand.put("getLogNo","GETforRBNo");
		no.setBounds(170,75,60,50);  
	
	
		
		//Add radio buttons to group
		ButtonGroup bg = new ButtonGroup();  
		bg.add(yes);
		bg.add(no);
		     
		//Add buttons to frame
		frm.add(yes);
		frm.add(no);
		

	
		//Create label object and set the position 
		JLabel lbl4 = new JLabel("Multiply by:", JLabel.LEFT);
		lbl4.setBounds(80,140,100,20);
		//Add label to frame
		frm.add(lbl4);
		      
		//Create array of strings 
		String by[] = { "2", "3", "4", "5", "6" }; 

		//Create combobox object using the array values  
		JComboBox moltiplicatore = new JComboBox(by); 
		aspectOnElement.put("getMoltiplicatore", Map.of("element",moltiplicatore, "type", "awtComoBoxInput"));
		aspectOnCommand.put("getMoltiplicatore", "GETforCombo");
		//Create a new panel
		JPanel p2 = new JPanel();
		//Add the combobox into the panel      
		p2.add(moltiplicatore); 
		//Set the panel position
		p2.setBounds(140,130,250,30);
		//Add the panel to the frame
		frm.add(p2); 
		
		
		//Create label object and set the position 
		JLabel result = new JLabel("result:", JLabel.LEFT);
		aspectOnElement.put("showResultJLabel", Map.of("element",result, "type", "awtJLabelOutput"));
		aspectOnCommand.put("showResultJLabel", "showResultJLabel");
		result.setBounds(100,180,100,20);
		//Add label to frame
		frm.add(result);
		
		
		JOptionPane popUp = new JOptionPane();
		aspectOnElement.put("showResultJPane", Map.of("element",popUp, "type", "awtJPaneOutput"));
		aspectOnCommand.put("showResultJPane", "showResultJPane");
		
		
		//Create button
		JButton multiplyButton = new JButton("Multipy");
		//aspectOnElement.put("multiply", Map.of("element",multiplyButton, "type", "todo"));
		//aspectOnCommand.put("multiply", "MultiplySubmit");
		//Set button position
		multiplyButton.setBounds(150,270,80,20);
		//Add button to frame
		frm.add(multiplyButton);
		//GESTIONE EVENTO PER Submit
		List<String> operationsToExecute = new ArrayList<>();
		operationsToExecute.add("getLogYes");
		//operationsToExecute.add("getLogNo");
		//operationsToExecute.add("LogExecutor");
		operationsToExecute.add("getMoltiplicando");
		operationsToExecute.add("getMoltiplicatore");
		operationsToExecute.add("multiplyOp");
		operationsToExecute.add("showResultJLabel");
		operationsToExecute.add("LogExecutor");
		operationsToExecute.add("showResultJPane");
		ActionListener muliplyClickOnEvent = new MuliplyClickOnEvent(aspectOnElement, aspectOnCommand, operationsToExecute );
		multiplyButton.addActionListener(muliplyClickOnEvent);
		//Create button
		JButton exit = new JButton("Exit");
		//Set button position
		exit.setBounds(250,270,80,20);       
		//Add button to frame
		frm.add(exit);
		//GESTIONE EVENTO PER Submit
		exit.addActionListener((ActionEvent e) -> {
			System.exit(0);
		});
		
		//Set the size of the form
		frm.setSize(500, 350);  
		//Set the layout  
		frm.setLayout(null);
		//Set the title of the form
		frm.setTitle("APPLICAZIONE PER MOLTIPLICARE");
		//set form in the center of the screen
		frm.setLocationRelativeTo(null);      
		//Display the form 
		frm.setVisible(true);
	
		//operationsToExecute.add("showResultJPane");
	}

}
