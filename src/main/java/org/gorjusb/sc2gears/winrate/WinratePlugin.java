package org.gorjusb.sc2gears.winrate;

import hu.belicza.andras.sc2gearspluginapi.Configurable;
import hu.belicza.andras.sc2gearspluginapi.GeneralServices;
import hu.belicza.andras.sc2gearspluginapi.Plugin;
import hu.belicza.andras.sc2gearspluginapi.PluginDescriptor;
import hu.belicza.andras.sc2gearspluginapi.PluginServices;
import hu.belicza.andras.sc2gearspluginapi.SettingsControl;
import hu.belicza.andras.sc2gearspluginapi.api.ReplayFactoryApi.ReplayContent;
import hu.belicza.andras.sc2gearspluginapi.api.listener.ReplayAutosaveListener;
import hu.belicza.andras.sc2gearspluginapi.api.listener.ReplayOpCallback;
import hu.belicza.andras.sc2gearspluginapi.api.listener.ReplayOpsPopupMenuItemListener;
import hu.belicza.andras.sc2gearspluginapi.api.sc2replay.IPlayer;
import hu.belicza.andras.sc2gearspluginapi.api.sc2replay.IReplay;

import java.awt.Window;
import java.io.File;
import java.util.EnumSet;

public class WinratePlugin implements Plugin, Configurable {

	private PluginDescriptor pluginDescriptor;
	private PluginServices pluginServces;
	private GeneralServices generalServices;

	private ReplayAutosaveListener replayAutosaveListener;
	private ReplayOpsPopupMenuItemListener replayOpsMenuListener;
	private Integer menuListenerHandler;
	private Window winrateWindow;

	private WinrateModel winrateModel = new WinrateModel();

	class WinrateReplayAutoSaveListener implements ReplayAutosaveListener {
		public void replayAutosaved(File autosavedReplayFile,
				File originalReplayFile) {
			IReplay replay = generalServices.getReplayFactoryApi().getReplay(
					autosavedReplayFile.getAbsolutePath(),
					EnumSet.allOf(ReplayContent.class));
		}
	}

	class PrintWinnersOpsMenuItemListener implements ReplayOpsPopupMenuItemListener {
		public void actionPerformed(File[] files, ReplayOpCallback callback,
				Integer handler) {
			for (File file : files) {
				IReplay replay = generalServices.getReplayFactoryApi()
						.getReplay(file.getAbsolutePath(),
								EnumSet.allOf(ReplayContent.class));
				for (IPlayer player : replay.getPlayers()) {
					System.out.println(player.getPlayerId().getFullName()
							+ ": " + (player.isWinner() ? "winner" : "loser"));
				}
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
				replayAutosaveListener);
		generalServices.getCallbackApi().removeReplayOpsPopupMenuItem(menuListenerHandler);
	}

	public void init(PluginDescriptor pluginDescriptor,
			PluginServices pluginServices, GeneralServices generalServices) {
		this.pluginDescriptor = pluginDescriptor;
		this.pluginServces = pluginServices;
		this.generalServices = generalServices;

		replayAutosaveListener = new WinrateReplayAutoSaveListener();
		replayOpsMenuListener = new PrintWinnersOpsMenuItemListener();
		
		generalServices.getCallbackApi().addReplayAutosaveListener(
				replayAutosaveListener);
		menuListenerHandler = generalServices.getCallbackApi().addReplayOpsPopupMenuItem("Print Player Win/Loss",
				null, replayOpsMenuListener);
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
