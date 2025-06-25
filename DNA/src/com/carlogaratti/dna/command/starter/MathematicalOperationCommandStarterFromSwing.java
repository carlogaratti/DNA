package com.carlogaratti.dna.command.starter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.carlogaratti.dna.command.Command;
import com.carlogaratti.dna.command.SumCommand;
import com.carlogaratti.dna.command.builder.CommandBuilder;
import com.carlogaratti.dna.command.get.GetExactValueCommand;
import com.carlogaratti.dna.command.get.GetInputCommand;
import com.carlogaratti.dna.command.get.GetNextPushedValueCommand;
import com.carlogaratti.dna.command.get.GetNextValueFIFOStrategy;
import com.carlogaratti.dna.command.get.GetNextValueStrategy;
import com.carlogaratti.dna.command.receiver.AWTTextInputWidgetTranslatorAdapter;
import com.carlogaratti.dna.command.receiver.Receiver;
import com.carlogaratti.dna.core.Bag;

import javax.swing.*;
import java.awt.event.*;

public class MathematicalOperationCommandStarterFromSwing  extends JFrame {
	
	private static JFrame f ;
	private static JButton b;
	private static JTextField one;
	private static JTextField two;
	private static JTextField four;
	private static JTextField six;
	private static JPanel p;

  
	public static void main(String[] args) {
		f = new JFrame("textfield");
        b = new JButton("submit");
        one  = new JTextField(16);
        two  = new JTextField(16);
        four  = new JTextField(16);
        six  = new JTextField(16);
        p = new JPanel();
 
        p.add(one);
        p.add(two);
        p.add(four);
        p.add(six);
        p.add(b);
 
        f.add(p);
 
        f.setSize(300, 300);
        f.show();
        
        Bag bag = new Bag("bag");
    	bag.putKey("minus").asString("1");
    	
    	CommandBuilder commandBuilder = new CommandBuilder();
		commandBuilder.addGetInputAWTTextInput(bag);
		Receiver awtReceiver = commandBuilder.receiver();
		Command get = commandBuilder.build();
    	
        one.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
			
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				System.out.println("insertUpdate one");
				awtReceiver.on(one);
				get.pushResultOn("one");
				get.execute();
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				
				
			}
		});
        
        six.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				System.out.println("insertUpdate six");
				awtReceiver.on(six);
				get.pushResultOn("six");
				get.execute();
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				
				
			}
		});

	two.getDocument().addDocumentListener(new DocumentListener() {
		
		@Override
		public void removeUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void insertUpdate(DocumentEvent e) {
			System.out.println("insertUpdate two");
			awtReceiver.on(two);
			get.pushResultOn("two");
			get.execute();
			
		}
		
		@Override
		public void changedUpdate(DocumentEvent e) {
			
			
		}
	});
	
	four.getDocument().addDocumentListener(new DocumentListener() {
		
		@Override
		public void removeUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void insertUpdate(DocumentEvent e) {
			System.out.println("insertUpdate four");
			awtReceiver.on(four);
			get.pushResultOn("four");
			get.execute();
			
		}
		
		@Override
		public void changedUpdate(DocumentEvent e) {
			
			
		}
	});
        
        
       
        b.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {	
            	bag.print();
				Command command = new SumCommand(bag);
				command.pullFrom("one");
				command.pullFrom("two");
				command.pushResultOn("result1");
				command.execute();
				
				command.pullFrom("four");
				command.pullFrom("six");
				command.pushResultOn("result2");
				command.execute();
				
				GetExactValueCommand gex = new GetExactValueCommand(bag);
				gex.on(command);
				gex.pullFrom("result1");
				gex.execute();
				bag.print();
				System.out.println("gex: " + gex.value());;
				
				gex.pullFrom("result2");
				gex.execute();
				bag.print();
				System.out.println("gex: " + gex.value());;
				
				GetNextPushedValueCommand gexNext = new GetNextPushedValueCommand(bag);
				GetNextValueStrategy lifo = new GetNextValueFIFOStrategy(gexNext);
				gexNext.orderStrategy(lifo);
				gexNext.on((GetInputCommand)get);
				gexNext.pushResultOn("result");
				gexNext.execute();
				System.out.println("gexNext: " + gexNext.value("result"));;
				gexNext.execute();
				System.out.println("gexNext " + gexNext.value("result"));;
				gexNext.execute();
				System.out.println("gexNext " + gexNext.value("result"));;
				gexNext.execute();
				System.out.println("gexNext " + gexNext.value("result"));;
				gexNext.execute();
				System.out.println("gexNext " + gexNext.value("result"));;
				((GetInputCommand)get).reset();
				get.showBag();
            }
        });
	}



}
