package com.carlogaratti.spike.swing;

import com.carlogaratti.dna.command.receiver.AWTComoBoxInputWidgetTranslatorAdapter;
import com.carlogaratti.dna.command.receiver.AWTJLabelWidgetTranslatorAdapter;
import com.carlogaratti.dna.command.receiver.AWTJOptionPaneWidgetTranslatorAdapter;
import com.carlogaratti.dna.command.receiver.AWTRadioInputWidgetTranslatorAdapter;
import com.carlogaratti.dna.command.receiver.AWTTextInputWidgetTranslatorAdapter;
import com.carlogaratti.dna.command.receiver.NoReceiver;
import com.carlogaratti.dna.command.receiver.Receiver;
import com.carlogaratti.dna.command.receiver.ReceiverJavaConsoleInput;
import com.carlogaratti.dna.command.receiver.ReceiverJavaConsolePrint;

public class AWTWidgetAbstractFactory extends ReceiverAbstractFactory {

	@Override
	public Receiver createTextInputWidgetTranslatorAdapter() {
		return new AWTTextInputWidgetTranslatorAdapter();
	}

	@Override
	protected Receiver createComoBoxInputWidgetTranslatorAdapter() {
		return new AWTComoBoxInputWidgetTranslatorAdapter();
	}

	@Override
	protected Receiver createRadioInputWidgetTranslatorAdapter() {
		return  new AWTRadioInputWidgetTranslatorAdapter();
	}

	@Override
	protected Receiver createJLabelOutputWidgetTranslatorAdapter() {
		return new AWTJLabelWidgetTranslatorAdapter();
	}

	@Override
	protected Receiver createNoReceiver() {
		return new NoReceiver();
	}

	@Override
	protected Receiver createScannerTransaltorAdapter() {
		return new ReceiverJavaConsoleInput();
	}

	@Override
	protected Receiver createOut() {
		return new ReceiverJavaConsolePrint();
	}

	@Override
	protected Receiver createJPaneOutputWidgetTranslatorAdapter() {
		
		return new AWTJOptionPaneWidgetTranslatorAdapter();
	}

}
