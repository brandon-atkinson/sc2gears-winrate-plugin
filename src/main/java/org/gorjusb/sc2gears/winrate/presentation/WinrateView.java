package org.gorjusb.sc2gears.winrate.presentation;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import java.awt.Font;
import java.awt.Color;

public class WinrateView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel playedLabel;
	private JLabel wonLabel;
	private JLabel lostLabel;
	
	public WinrateView() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{110, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblPlayedTitle = new JLabel("Played");
		lblPlayedTitle.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GridBagConstraints gbc_lblPlayedTitle = new GridBagConstraints();
		gbc_lblPlayedTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayedTitle.gridx = 0;
		gbc_lblPlayedTitle.gridy = 0;
		panel.add(lblPlayedTitle, gbc_lblPlayedTitle);
		
		this.playedLabel = new JLabel("New label");
		playedLabel.setForeground(new Color(0, 0, 0));
		playedLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GridBagConstraints gbc_lblPlayedValue = new GridBagConstraints();
		gbc_lblPlayedValue.insets = new Insets(0, 0, 5, 0);
		gbc_lblPlayedValue.gridx = 2;
		gbc_lblPlayedValue.gridy = 0;
		panel.add(playedLabel, gbc_lblPlayedValue);
		
		JLabel lblWonTitle = new JLabel("Won");
		lblWonTitle.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GridBagConstraints gbc_lblWonTitle = new GridBagConstraints();
		gbc_lblWonTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblWonTitle.gridx = 0;
		gbc_lblWonTitle.gridy = 2;
		panel.add(lblWonTitle, gbc_lblWonTitle);
		
		this.wonLabel = new JLabel("New label");
		wonLabel.setForeground(new Color(34, 139, 34));
		wonLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GridBagConstraints gbc_lblWonValue = new GridBagConstraints();
		gbc_lblWonValue.insets = new Insets(0, 0, 5, 0);
		gbc_lblWonValue.gridx = 2;
		gbc_lblWonValue.gridy = 2;
		panel.add(wonLabel, gbc_lblWonValue);
		
		JLabel lblLostTitle = new JLabel("Lost");
		lblLostTitle.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GridBagConstraints gbc_lblLostTitle = new GridBagConstraints();
		gbc_lblLostTitle.insets = new Insets(0, 0, 0, 5);
		gbc_lblLostTitle.gridx = 0;
		gbc_lblLostTitle.gridy = 4;
		panel.add(lblLostTitle, gbc_lblLostTitle);
		
		lostLabel = new JLabel("New label");
		lostLabel.setForeground(new Color(220, 20, 60));
		lostLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GridBagConstraints gbc_lblLostValue = new GridBagConstraints();
		gbc_lblLostValue.gridx = 2;
		gbc_lblLostValue.gridy = 4;
		panel.add(lostLabel, gbc_lblLostValue);
		
	}

	/**
	 * @return the playedLabel
	 */
	public JLabel getPlayedLabel() {
		return playedLabel;
	}

	/**
	 * @return the wonLabel
	 */
	public JLabel getWonLabel() {
		return wonLabel;
	}

	/**
	 * @return the lostLabel
	 */
	public JLabel getLostLabel() {
		return lostLabel;
	}

}
