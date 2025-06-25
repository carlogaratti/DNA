package com.carlogaratti.spike.swing;

import com.carlogaratti.dna.command.receiver.Receiver;

public abstract class ReceiverAbstractFactory {

	public abstract Receiver createTextInputWidgetTranslatorAdapter();

	protected abstract Receiver createComoBoxInputWidgetTranslatorAdapter();

	protected abstract Receiver createRadioInputWidgetTranslatorAdapter();

	protected abstract Receiver createJLabelOutputWidgetTranslatorAdapter();
	protected abstract Receiver createJPaneOutputWidgetTranslatorAdapter();

	protected abstract Receiver createNoReceiver();

	protected abstract Receiver createScannerTransaltorAdapter();

	protected abstract Receiver createOut();

	

}
