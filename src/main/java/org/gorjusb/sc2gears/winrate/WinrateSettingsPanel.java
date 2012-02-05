package org.gorjusb.sc2gears.winrate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class WinrateSettingsPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2065360165720145044L;
	private JTextField textField;

	public WinrateSettingsPanel() {
		setLayout(new BorderLayout(0, 0));

		JLabel lblWinRatePlugin = new JLabel("Win Rate Plugin Settings");
		lblWinRatePlugin.setHorizontalAlignment(SwingConstants.CENTER);
		lblWinRatePlugin.setFont(new Font("Tahoma", Font.PLAIN, 22));
		add(lblWinRatePlugin, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Consider only replays",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JRadioButton rdbtnFavoredPlayers = new JRadioButton(
				"Involving favored players");
		
		GridBagConstraints gbc_rdbtnFavoredPlayers = new GridBagConstraints();
		gbc_rdbtnFavoredPlayers.anchor = GridBagConstraints.WEST;
		gbc_rdbtnFavoredPlayers.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnFavoredPlayers.gridx = 0;
		gbc_rdbtnFavoredPlayers.gridy = 2;
		panel.add(rdbtnFavoredPlayers, gbc_rdbtnFavoredPlayers);

		JRadioButton rdbtnPlayerName = new JRadioButton(
				"Involving player (full name):");
		GridBagConstraints gbc_rdbtnPlayerName = new GridBagConstraints();
		gbc_rdbtnPlayerName.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnPlayerName.anchor = GridBagConstraints.WEST;
		gbc_rdbtnPlayerName.gridx = 0;
		gbc_rdbtnPlayerName.gridy = 3;
		panel.add(rdbtnPlayerName, gbc_rdbtnPlayerName);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.weightx = 1.0;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 3;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);

		ButtonGroup repIncGroup = new ButtonGroup();
		repIncGroup.add(rdbtnFavoredPlayers);
		repIncGroup.add(rdbtnPlayerName);
	}
}
