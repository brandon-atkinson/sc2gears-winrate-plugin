package org.gorjusb.sc2gears.winrate;

import hu.belicza.andras.sc2gearspluginapi.GeneralServices;
import hu.belicza.andras.sc2gearspluginapi.Plugin;
import hu.belicza.andras.sc2gearspluginapi.PluginDescriptor;
import hu.belicza.andras.sc2gearspluginapi.PluginServices;
import hu.belicza.andras.sc2gearspluginapi.api.ReplayFactoryApi.ReplayContent;
import hu.belicza.andras.sc2gearspluginapi.api.listener.ReplayAutosaveListener;
import hu.belicza.andras.sc2gearspluginapi.api.listener.ReplayOpCallback;
import hu.belicza.andras.sc2gearspluginapi.api.listener.ReplayOpsPopupMenuItemListener;
import hu.belicza.andras.sc2gearspluginapi.api.sc2replay.IPlayer;
import hu.belicza.andras.sc2gearspluginapi.api.sc2replay.IReplay;

import java.io.File;
import java.util.EnumSet;
import java.util.List;

import org.gorjusb.sc2gears.winrate.domain.MatchRecord;

public class WinratePlugin implements Plugin {

	private PluginDescriptor pluginDescriptor;
	private PluginServices pluginServces;
	private GeneralServices generalServices;
	private ReplayAutosaveListener replayAutosaveListener;
	private MatchRecord matchRecord = new MatchRecord();

	class WinrateReplayAutoSaveListener implements ReplayAutosaveListener {
		public void replayAutosaved(File autosavedReplayFile,
				File originalReplayFile) {
			IReplay replay = generalServices.getReplayFactoryApi().getReplay(
					autosavedReplayFile.getAbsolutePath(),
					EnumSet.allOf(ReplayContent.class));

			IPlayer favoredPlayer = findMostFavoredPlayer(generalServices
					.getInfoApi().getFavoredPlayerList(), replay.getPlayers());

			if (favoredPlayer != null) {
				adjustMatchRecord(matchRecord, favoredPlayer);
			}

		}
	}

	class PrintWinnersOpsMenuItemListener implements
			ReplayOpsPopupMenuItemListener {
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

	public void destroy() {
		/*
		 * unregister our replay listener, because we play nice
		 */
		generalServices.getCallbackApi().removeReplayAutosaveListener(
				replayAutosaveListener);
	}

	public void init(PluginDescriptor pluginDescriptor,
			PluginServices pluginServices, GeneralServices generalServices) {
		this.pluginDescriptor = pluginDescriptor;
		this.pluginServces = pluginServices;
		this.generalServices = generalServices;

		replayAutosaveListener = new WinrateReplayAutoSaveListener();

		generalServices.getCallbackApi().addReplayAutosaveListener(
				replayAutosaveListener);
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

	private IPlayer findMostFavoredPlayer(List<String> favoredPlayerNames,
			IPlayer[] players) {
		IPlayer favoredPlayer = null;

		FIND_FAVORED: for (String favoredPlayerName : generalServices
				.getInfoApi().getFavoredPlayerList()) {

			for (IPlayer player : players) {
				String playerName = player.getPlayerId().getName();
				if (favoredPlayerName.equals(playerName)) {
					favoredPlayer = player;
					break FIND_FAVORED;
				}
			}

		}

		return favoredPlayer;
	}

	private void adjustMatchRecord(MatchRecord record, IPlayer player) {
		record.setPlayed(record.getPlayed() + 1);
		if (player.isWinner()) {
			record.setWon(record.getWon() + 1);
		}
	}

}
