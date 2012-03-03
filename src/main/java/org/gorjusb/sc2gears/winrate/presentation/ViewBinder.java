package org.gorjusb.sc2gears.winrate.presentation;

import java.text.NumberFormat;

import org.gorjusb.sc2gears.winrate.domain.MatchRecord;

import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.value.ConverterFactory;

public class ViewBinder {
	public void bind(WinrateView view, WinratePresentationModel model) {
		Bindings.bind(view.getPlayedLabel(), ConverterFactory.createStringConverter(model.getComponentModel(MatchRecord.PROPERTY_PLAYED), NumberFormat.getIntegerInstance()));
		Bindings.bind(view.getWonLabel(), ConverterFactory.createStringConverter(model.getComponentModel(MatchRecord.PROPERTY_WON), NumberFormat.getIntegerInstance()));
		Bindings.bind(view.getLostLabel(), ConverterFactory.createStringConverter(model.getComponentModel(MatchRecord.PROPERTY_LOST), NumberFormat.getIntegerInstance()));
	}
}
