package org.gorjusb.sc2gears.winrate.domain;

import java.io.Serializable;

public class MatchRecord implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7323375518412981722L;
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
		this.played = played;
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
		this.won = won;
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
		this.lost = lost;
	}
	
	
}
