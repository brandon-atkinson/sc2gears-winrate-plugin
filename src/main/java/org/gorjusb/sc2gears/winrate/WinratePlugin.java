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
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import javax.swing.JMenuItem;

import org.gorjusb.sc2gears.winrate.domain.MatchRecord;
import org.gorjusb.sc2gears.winrate.presentation.MovableBorderlessDialog;
import org.gorjusb.sc2gears.winrate.presentation.ViewBinder;
import org.gorjusb.sc2gears.winrate.presentation.WinratePresentationModel;
import org.gorjusb.sc2gears.winrate.presentation.WinrateView;

public class WinratePlugin implements Plugin {

	private PluginDescriptor pluginDescriptor;
	private PluginServices pluginServces;
	private GeneralServices generalServices;
	private ReplayAutosaveListener replayAutosaveListener;
	private MatchRecord matchRecord;
	private MovableBorderlessDialog dialog;
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

			List<IPlayer> favoredPlayers = findFavoredPlayers(replay);
			
			if (favoredPlayers.size() == 1) {
				adjustMatchRecord(matchRecord, favoredPlayers.get(0));
			} else if (favoredPlayers.size() > 1) {
				/*
				 * if multiple players in replay are considered 'favored'
				 * we just make sure all of them are either winners or losers.
				 * As long as all the results are consistent, we'll adjust 
				 * the record accordingly. This is a special weird case,
				 * but it could allow people who lend out their smurf accounts
				 * to play team games with people and still have their stats
				 * work.    
				 */
				boolean firstResult = favoredPlayers.get(0).isWinner();
				
				for (IPlayer player : favoredPlayers) {
					if (player.isWinner() != firstResult) {
						return;
					}
				}
				
				adjustMatchRecord(matchRecord, favoredPlayers.get(0));
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
		this.dialog = new MovableBorderlessDialog(generalServices
				.getGuiUtilsApi().getMainFrame());
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

	private List<IPlayer> findFavoredPlayers(IReplay replay) {
		List<IPlayer> favoredPlayerObjs = new ArrayList<IPlayer>();
		List<String> favoredPlayerNames = generalServices.getInfoApi()
				.getFavoredPlayerList();

		for (IPlayer player : replay.getPlayers()) {
			if (favoredPlayerNames.contains(player.getPlayerId().getName())) {
				favoredPlayerObjs.add(player);
			}
		}

		return favoredPlayerObjs;
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
		generalServices.getCallbackApi()
				.removeMenuItemFromPluginsMenu(menuItem);
	}

	private void disposeDisplay() {
		dialog.setVisible(false);
		dialog.dispose();
	}
}
