package org.gorjusb.sc2gears.winrate;

import hu.belicza.andras.sc2gearspluginapi.SettingsControl;
import hu.belicza.andras.sc2gearspluginapi.api.InfoApi;
import hu.belicza.andras.sc2gearspluginapi.api.SettingsApi;

import java.awt.Container;
import java.util.Arrays;

import javax.swing.JDialog;

public class WinrateSettingsControl implements SettingsControl {
	private static final String INCLUSION_CRITERIA_PROP = "inclusion criteria";
	private static final String SELECTED_FAVORED_PLAYER_PROP = "selected favored player";
	private WinrateSettingsPanel panel;
	private JDialog parentDialog;
	private SettingsApi settingsApi;
	private InfoApi infoApi;
	private SettingsModel settingsModel;
	
	public WinrateSettingsControl(SettingsModel settingsModel, SettingsApi settingsApi, InfoApi infoApi) {
		this.settingsModel = settingsModel;
		this.settingsApi = settingsApi;
		this.infoApi = infoApi;
	}

	public Container getEditorPanel() {
		ReplayInclusionCriteria inclusion = ReplayInclusionCriteria.ALL_FAVORED_PLAYERS;
		try {
			inclusion = ReplayInclusionCriteria.valueOf(settingsApi.getString(INCLUSION_CRITERIA_PROP));
		} catch (IllegalArgumentException iae) {
			System.err.println("couldn't initialize model: " + iae);
		} catch (NullPointerException npe) {
			System.err.println("couldn't initialize model: " + npe);
		}
		settingsModel.setReplayInclusionCriteria(inclusion);
		
		String selectedPlayer = settingsApi.getString(SELECTED_FAVORED_PLAYER_PROP);
		if (Arrays.asList(infoApi.getFavoredPlayerList()).contains(selectedPlayer)) {
		    settingsModel.setSelectedFavoredPlayer(selectedPlayer);
		} else {
			settingsApi.remove(SELECTED_FAVORED_PLAYER_PROP);
		}
		
		panel = new WinrateSettingsPanel(settingsModel, infoApi.getFavoredPlayerList());
		
		return panel;
	}

	public void onCancelButtonPressed() {
		//do nothing
	}

	public void onOkButtonPressed() {
		ReplayInclusionCriteria inclusion = settingsModel.getReplayInclusionCriteria();
		settingsApi.set(INCLUSION_CRITERIA_PROP, inclusion);
		
		if (ReplayInclusionCriteria.SELECTED_FAVORED_PLAYER.equals(inclusion)) {
			settingsApi.set(SELECTED_FAVORED_PLAYER_PROP, settingsModel.getSelectedFavoredPlayer());
		} else {
			settingsApi.remove(SELECTED_FAVORED_PLAYER_PROP);
		}
	}

	public void receiveSettingsDialog(JDialog parentDialog) {
		this.parentDialog = parentDialog;
	}

}
