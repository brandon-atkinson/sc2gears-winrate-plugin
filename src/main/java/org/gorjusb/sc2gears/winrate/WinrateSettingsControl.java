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

	public WinrateSettingsControl(SettingsApi settingsApi, InfoApi infoApi) {
		this.settingsApi = settingsApi;
		this.infoApi = infoApi;
	}

	public Container getEditorPanel() {
		panel = new WinrateSettingsPanel();
		panel.setFavoredPlayers(infoApi.getFavoredPlayerList());
		
		ReplayInclusionCriteria inclusion = ReplayInclusionCriteria.ANY_FAVORED_PLAYER;
		try {
			inclusion = ReplayInclusionCriteria.valueOf(settingsApi.getString(INCLUSION_CRITERIA_PROP));
		} catch (IllegalArgumentException iae) {
			//use default
		} catch (NullPointerException npe) {
			//use default
		}
		panel.setInclusionCriteria(inclusion);
		
		
		String selectedPlayer = settingsApi.getString(SELECTED_FAVORED_PLAYER_PROP);
		if (Arrays.asList(infoApi.getFavoredPlayerList()).contains(selectedPlayer)) {
			panel.setSelectedPlayer(selectedPlayer);
		} else {
			settingsApi.remove(SELECTED_FAVORED_PLAYER_PROP);
		}
		return panel;
	}

	public void onCancelButtonPressed() {
		//do nothing
	}

	public void onOkButtonPressed() {
		ReplayInclusionCriteria inclusion = panel.getInclusionCriteria();
		settingsApi.set(INCLUSION_CRITERIA_PROP, inclusion);
		
		if (ReplayInclusionCriteria.SELECTED_FAVORED_PLAYER.equals(inclusion)) {
			settingsApi.set(SELECTED_FAVORED_PLAYER_PROP, panel.getSelectedFavoredPlayer());
		} else {
			settingsApi.remove(SELECTED_FAVORED_PLAYER_PROP);
		}
	}

	public void receiveSettingsDialog(JDialog parentDialog) {
		this.parentDialog = parentDialog;
	}

}
