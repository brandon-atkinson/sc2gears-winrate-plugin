package org.gorjusb.sc2gears.winrate;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.event.SwingPropertyChangeSupport;

public class SettingsModel {
	public static final String REPLAY_INCLUSION_CRITERIA_PROP = "replayInclusionCriteria";
	public static final String SELECTED_FAVORED_PLAYER_PROP = "selectedFavoredPlayer";
	
	private final PropertyChangeSupport propSupport = new SwingPropertyChangeSupport(this);
	private ReplayInclusionCriteria replayInclusionCriteria;
	private String selectedFavoredPlayer;
	
	public ReplayInclusionCriteria getReplayInclusionCriteria() {
		return replayInclusionCriteria;
	}
	
	public void setReplayInclusionCriteria(
			ReplayInclusionCriteria replayInclusionCriteria) {
		ReplayInclusionCriteria orig = this.replayInclusionCriteria;
		this.replayInclusionCriteria = replayInclusionCriteria;
		propSupport.firePropertyChange(REPLAY_INCLUSION_CRITERIA_PROP, orig, replayInclusionCriteria);
	}
	
	public String getSelectedFavoredPlayer() {
		return selectedFavoredPlayer;
	}
	
	public void setSelectedFavoredPlayer(String selectedFavoredPlayer) {
		String orig = this.selectedFavoredPlayer;
		this.selectedFavoredPlayer = selectedFavoredPlayer;
		propSupport.firePropertyChange(SELECTED_FAVORED_PLAYER_PROP, orig, selectedFavoredPlayer);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propSupport.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propSupport.removePropertyChangeListener(listener);
	}
	
	public PropertyChangeListener[] getPropertyChangeListeners() {
		return propSupport.getPropertyChangeListeners();
	}
	
	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propSupport.addPropertyChangeListener(propertyName, listener);
	}
	
	public void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propSupport.removePropertyChangeListener(propertyName, listener);
	}
	
	public PropertyChangeListener[] getPropertyChangeListeners(
			String propertyName) {
		return propSupport.getPropertyChangeListeners(propertyName);
	}
	public boolean hasListeners(String propertyName) {
		return propSupport.hasListeners(propertyName);
	}
	
}
