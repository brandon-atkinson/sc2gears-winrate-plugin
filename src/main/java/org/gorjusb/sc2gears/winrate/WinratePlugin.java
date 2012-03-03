package org.gorjusb.sc2gears.winrate;

import hu.belicza.andras.sc2gearspluginapi.GeneralServices;
import hu.belicza.andras.sc2gearspluginapi.Plugin;
import hu.belicza.andras.sc2gearspluginapi.PluginDescriptor;
import hu.belicza.andras.sc2gearspluginapi.PluginServices;
import hu.belicza.andras.sc2gearspluginapi.api.ReplayFactoryApi.ReplayContent;
import hu.belicza.andras.sc2gearspluginapi.api.listener.ReplayAutosaveListener;
import hu.belicza.andras.sc2gearspluginapi.api.sc2replay.IPlayer;
import hu.belicza.andras.sc2gearspluginapi.api.sc2replay.IReplay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.EnumSet;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JWindow;

import org.gorjusb.sc2gears.winrate.domain.MatchRecord;
import org.gorjusb.sc2gears.winrate.presentation.ViewBinder;
import org.gorjusb.sc2gears.winrate.presentation.WinratePresentationModel;
import org.gorjusb.sc2gears.winrate.presentation.WinrateView;

public class WinratePlugin implements Plugin {

	private PluginDescriptor pluginDescriptor;
	private PluginServices pluginServces;
	private GeneralServices generalServices;
	private ReplayAutosaveListener replayAutosaveListener;
	private MatchRecord matchRecord;
	private JDialog dialog;
	private WinrateView view;
	private WinratePresentationModel model;
	private ViewBinder binder;
	private JMenuItem menuItem;

	class WinrateReplayAutoSaveListener implements ReplayAutosaveListener {
		public void replayAutosaved(File autosavedReplayFile,
				File originalReplayFile) {
			IReplay replay = generalServices.getReplayFactoryApi().getReplay(
					autosavedReplayFile.getAbsolutePath(),
					EnumSet.allOf(ReplayContent.class));

			IPlayer player = findMostFavoredPlayer(generalServices.getInfoApi()
					.getFavoredPlayerList(), replay.getPlayers());

			if (player != null) {
				adjustMatchRecord(matchRecord, player);
			}

		}
	}

	public WinratePlugin() {
	}

	public void destroy() {
		/*
		 * unregister our replay listener, because we play nice
		 */
		unregisterAutosaveListener();
		unregisterMenuItem();
		disposeDisplay();
	}

	public void init(PluginDescriptor pluginDescriptor,
			PluginServices pluginServices, GeneralServices generalServices) {
		this.pluginDescriptor = pluginDescriptor;
		this.pluginServces = pluginServices;
		this.generalServices = generalServices;
		this.matchRecord = new MatchRecord();
		this.dialog = new JDialog();
		this.model = new WinratePresentationModel(this.matchRecord);
		this.binder = new ViewBinder();
		this.menuItem = new JMenuItem("On-top winrate info");

		registerAutosaveListener();
		registerMenuItem();
		configureDisplay();
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
		} else {
			record.setLost(record.getLost() + 1);
		}
	}

	private void configureDisplay() {
		dialog.setUndecorated(true);
		dialog.setAlwaysOnTop(true);

		view = new WinrateView();
		dialog.getContentPane().add(view);
		dialog.pack();
		generalServices.getGuiUtilsApi().createCloseButton(dialog);

		binder.bind(view, model);
	}

	private void registerAutosaveListener() {
		replayAutosaveListener = new WinrateReplayAutoSaveListener();

		generalServices.getCallbackApi().addReplayAutosaveListener(
				replayAutosaveListener);
	}

	private void unregisterAutosaveListener() {
		generalServices.getCallbackApi().removeReplayAutosaveListener(
				replayAutosaveListener);
		dialog.setVisible(false);
	}

	private void registerMenuItem() {
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("plugin menu item selected");
				if (!dialog.isVisible()) {
					System.out.println("setting window visible");
					dialog.setVisible(true);
				}
			}
		});

		generalServices.getCallbackApi().addMenuItemToPluginsMenu(menuItem);
	}
	
	private void unregisterMenuItem() {
		generalServices.getCallbackApi().removeMenuItemFromPluginsMenu(menuItem);
	}
	
	private void disposeDisplay() {
		dialog.setVisible(false);
		dialog.dispose();
	}
}
