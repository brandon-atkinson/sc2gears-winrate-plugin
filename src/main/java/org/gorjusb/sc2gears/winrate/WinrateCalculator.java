package org.gorjusb.sc2gears.winrate;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class WinrateCalculator {
	public int winratePercent(int gamesWon, int gamesLost, int gamesDrawn) {
		BigDecimal effectiveWinsFromDraw = new BigDecimal(gamesDrawn)
				.divide(new BigDecimal(2));
		BigDecimal totalEffectiveWins = effectiveWinsFromDraw
				.add(new BigDecimal(gamesWon));
		BigDecimal totalGames = new BigDecimal(gamesWon).add(
				new BigDecimal(gamesLost)).add(new BigDecimal(gamesDrawn));
		BigDecimal winRate = totalEffectiveWins.divide(totalGames,
				new MathContext(3, RoundingMode.HALF_UP)).multiply(
				new BigDecimal(100));

		return winRate.intValue();
	}
}
