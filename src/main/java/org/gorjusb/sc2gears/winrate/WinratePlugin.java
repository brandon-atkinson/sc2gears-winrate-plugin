package org.gorjusb.sc2gears.winrate;

import hu.belicza.andras.sc2gearspluginapi.Configurable;
import hu.belicza.andras.sc2gearspluginapi.GeneralServices;
import hu.belicza.andras.sc2gearspluginapi.Plugin;
import hu.belicza.andras.sc2gearspluginapi.PluginDescriptor;
import hu.belicza.andras.sc2gearspluginapi.PluginServices;
import hu.belicza.andras.sc2gearspluginapi.SettingsControl;

public class WinratePlugin implements Plugin, Configurable {
	
	private PluginDescriptor pluginDescriptor;
	private PluginServices pluginServces;
	private GeneralServices generalServices;
	
	public SettingsControl getSettingsControl() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isActionRequired() {
		// TODO Auto-generated method stub
		return false;
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void init(PluginDescriptor arg0, PluginServices arg1,
			GeneralServices arg2) {
		// TODO Auto-generated method stub

	}

	PluginDescriptor getPluginDescriptor() {
		return pluginDescriptor;
	}

	void setPluginDescriptor(PluginDescriptor pluginDescriptor) {
		this.pluginDescriptor = pluginDescriptor;
	}

	PluginServices getPluginServces() {
		return pluginServces;
	}

	void setPluginServces(PluginServices pluginServces) {
		this.pluginServces = pluginServces;
	}

	GeneralServices getGeneralServices() {
		return generalServices;
	}

	void setGeneralServices(GeneralServices generalServices) {
		this.generalServices = generalServices;
	}
}
