package org.gorjusb.sc2gears.winrate;

import hu.belicza.andras.sc2gearspluginapi.Configurable;
import hu.belicza.andras.sc2gearspluginapi.GeneralServices;
import hu.belicza.andras.sc2gearspluginapi.Plugin;
import hu.belicza.andras.sc2gearspluginapi.PluginDescriptor;
import hu.belicza.andras.sc2gearspluginapi.PluginServices;
import hu.belicza.andras.sc2gearspluginapi.SettingsControl;
import hu.belicza.andras.sc2gearspluginapi.api.ReplayFactoryApi.ReplayContent;
import hu.belicza.andras.sc2gearspluginapi.api.listener.ReplayAutosaveListener;
import hu.belicza.andras.sc2gearspluginapi.api.sc2replay.IReplay;

import java.awt.Window;
import java.io.File;
import java.util.EnumSet;

public class WinratePlugin implements Plugin, Configurable {

	private PluginDescriptor pluginDescriptor;
	private PluginServices pluginServces;
	private GeneralServices generalServices;

	private ReplayAutosaveListener replayListener;
	private Window winrateWindow;

	private WinrateModel winrateModel = new WinrateModel();

	class WinrateReplayAutoSaveListener implements ReplayAutosaveListener {
		@Override
		public void replayAutosaved(File autosavedReplayFile,
				File originalReplayFile) {
			IReplay replay = generalServices.getReplayFactoryApi().getReplay(
					autosavedReplayFile.getAbsolutePath(),
					EnumSet.allOf(ReplayContent.class));
			
			for (String winner : replay.getWinnerNames().split(",")) {
				System.out.println(winner.trim());
			}
			
			for (String player : replay.getPlayerNames().split(",")) {
				System.out.println(player.trim());
			}
		}
	}

	public WinratePlugin() {
	}

	public SettingsControl getSettingsControl() {
		return null;
	}

	public boolean isActionRequired() {
		return false;
	}

	public void destroy() {
		/*
		 * unregister our replay listener, because we play nice
		 */
		generalServices.getCallbackApi().removeReplayAutosaveListener(
				replayListener);
	}

	public void init(PluginDescriptor pluginDescriptor,
			PluginServices pluginServices, GeneralServices generalServices) {
		this.pluginDescriptor = pluginDescriptor;
		this.pluginServces = pluginServices;
		this.generalServices = generalServices;

		generalServices.getCallbackApi().addReplayAutosaveListener(
				new WinrateReplayAutoSaveListener());
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
