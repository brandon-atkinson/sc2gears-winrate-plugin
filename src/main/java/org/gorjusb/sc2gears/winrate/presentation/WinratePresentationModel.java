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
		// TODO Auto-generated constructor stub
	}

	public WinratePresentationModel(MatchRecord bean) {
		super(bean);
		// TODO Auto-generated constructor stub
	}

	public WinratePresentationModel(ValueModel beanChannel,
			ValueModel triggerChannel) {
		super(beanChannel, triggerChannel);
		// TODO Auto-generated constructor stub
	}

	public WinratePresentationModel(ValueModel beanChannel) {
		super(beanChannel);
		// TODO Auto-generated constructor stub
	}
	
}
