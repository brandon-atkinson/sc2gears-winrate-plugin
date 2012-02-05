package org.gorjusb.sc2gears.winrate;

/**
 * @author brandon
 *
 */
public interface WinrateCalculationStrategy {
	int calculate(int gamesWon, int gamesLost, int gamesDrawn); 
}
