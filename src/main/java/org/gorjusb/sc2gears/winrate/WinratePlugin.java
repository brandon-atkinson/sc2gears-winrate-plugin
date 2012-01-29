package org.gorjusb.sc2gears.winrate;

import javax.swing.JDialog;

import hu.belicza.andras.sc2gearspluginapi.Configurable;
import hu.belicza.andras.sc2gearspluginapi.GeneralServices;
import hu.belicza.andras.sc2gearspluginapi.Plugin;
import hu.belicza.andras.sc2gearspluginapi.PluginDescriptor;
import hu.belicza.andras.sc2gearspluginapi.PluginServices;
import hu.belicza.andras.sc2gearspluginapi.SettingsControl;
import hu.belicza.andras.sc2gearspluginapi.api.listener.ReplayAutosaveListener;

public class WinratePlugin implements Plugin, Configurable {
	
	private PluginDescriptor pluginDescriptor;
	private PluginServices pluginServces;
	private GeneralServices generalServices;
	
	private ReplayAutosaveListener replayListener;
	private JDialog winrateDialog;
	
	public SettingsControl getSettingsControl() {
		return null;
	}

	public boolean isActionRequired() {
		return false;
	}

	public void destroy() {
	}

	public void init(PluginDescriptor pluginDescriptor, PluginServices pluginServices,
			GeneralServices generalServices) {
		this.pluginDescriptor = pluginDescriptor;
		this.pluginServces = pluginServices;
		this.generalServices = generalServices;
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
