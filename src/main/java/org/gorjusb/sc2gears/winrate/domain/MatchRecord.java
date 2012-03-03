package org.gorjusb.sc2gears.winrate.domain;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public class MatchRecord implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7323375518412981722L;
	public static final String PROPERTY_PLAYED = "played";
	public static final String PROPERTY_WON = "won";
	public static final String PROPERTY_LOST = "lost";
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private int played;
	private int won;
	private int lost;
	/**
	 * @return the played
	 */
	public int getPlayed() {
		return played;
	}
	/**
	 * @param played the played to set
	 */
	public void setPlayed(int played) {
		int oldValue = this.played;
		this.played = played;
		pcs.firePropertyChange(PROPERTY_PLAYED, oldValue, played);
	}
	/**
	 * @return the won
	 */
	public int getWon() {
		return won;
	}
	/**
	 * @param won the won to set
	 */
	public void setWon(int won) {
		int oldValue = this.won;
		this.won = won;
		pcs.firePropertyChange(PROPERTY_WON, oldValue, won);
	}
	
	/**
	 * @return the lost
	 */
	public int getLost() {
		return lost;
	}
	/**
	 * @param lost the lost to set
	 */
	public void setLost(int lost) {
		int oldValue = this.lost;
		this.lost = lost;
		pcs.firePropertyChange(PROPERTY_LOST, oldValue, lost);
	}
	/**
	 * @param listener
	 * @see java.beans.PropertyChangeSupport#addPropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}
	/**
	 * @param listener
	 * @see java.beans.PropertyChangeSupport#removePropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}
	/**
	 * @param propertyName
	 * @param listener
	 * @see java.beans.PropertyChangeSupport#addPropertyChangeListener(java.lang.String, java.beans.PropertyChangeListener)
	 */
	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(propertyName, listener);
	}
	/**
	 * @param propertyName
	 * @param listener
	 * @see java.beans.PropertyChangeSupport#removePropertyChangeListener(java.lang.String, java.beans.PropertyChangeListener)
	 */
	public void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(propertyName, listener);
	}
	
}
