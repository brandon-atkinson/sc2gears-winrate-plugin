package org.gorjusb.sc2gears.winrate.presentation;

import org.gorjusb.sc2gears.winrate.domain.MatchRecord;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.value.ValueModel;

public class WinratePresentationModel extends PresentationModel<MatchRecord> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public WinratePresentationModel(MatchRecord bean, ValueModel triggerChannel) {
		super(bean, triggerChannel);
	}

	public WinratePresentationModel(MatchRecord bean) {
		super(bean);
	}

	public WinratePresentationModel(ValueModel beanChannel,
			ValueModel triggerChannel) {
		super(beanChannel, triggerChannel);
	}

	public WinratePresentationModel(ValueModel beanChannel) {
		super(beanChannel);
	}
	
}
