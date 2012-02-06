package org.gorjusb.sc2gears.winrate;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;


public class WinrateModel {
	private int gamesWon;
	private int gamesLost;
	private int gamesDrawn;
	
	public WinrateModel() {
	}

	public WinrateModel(int gamesWon, int gamesLost, int gamesDrawn) {
		this.gamesWon = gamesWon;
		this.gamesLost = gamesLost;
		this.gamesDrawn = gamesDrawn;
	}

	public int getGamesWon() {
		return gamesWon;
	}

	public void setGamesWon(int gamesWon) {
		this.gamesWon = gamesWon;
	}

	public int getGamesLost() {
		return gamesLost;
	}

	public void setGamesLost(int gamesLost) {
		this.gamesLost = gamesLost;
	}

	public int getGamesDrawn() {
		return gamesDrawn;
	}

	public void setGamesDrawn(int gamesDrawn) {
		this.gamesDrawn = gamesDrawn;
	}


}
