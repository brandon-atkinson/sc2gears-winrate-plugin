package org.gorjusb.sc2gears.winrate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class WinrateSettingsPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2065360165720145044L;
	private ButtonGroup repIncGroup;
	private JRadioButton rdbtnAllFavoredPlayers;
	private JRadioButton rdbtnSelectedFavoredPlayer;
	private JComboBox favoredPlayersCombo;
	private ReplayInclusionCriteria inclusionCriteria;

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
				0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		ActionListener rdbtnListener = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				setInclusionCriteria(ReplayInclusionCriteria.valueOf(e
						.getActionCommand()));
			}
		};

		rdbtnAllFavoredPlayers = new JRadioButton("Involving favored players");
		rdbtnAllFavoredPlayers
				.setActionCommand(ReplayInclusionCriteria.ANY_FAVORED_PLAYER
						.toString());

		rdbtnAllFavoredPlayers.addActionListener(rdbtnListener);

		GridBagConstraints gbc_rdbtnFavoredPlayers = new GridBagConstraints();
		gbc_rdbtnFavoredPlayers.anchor = GridBagConstraints.WEST;
		gbc_rdbtnFavoredPlayers.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnFavoredPlayers.gridx = 0;
		gbc_rdbtnFavoredPlayers.gridy = 2;
		panel.add(rdbtnAllFavoredPlayers, gbc_rdbtnFavoredPlayers);

		repIncGroup = new ButtonGroup();
		repIncGroup.add(rdbtnAllFavoredPlayers);

		rdbtnSelectedFavoredPlayer = new JRadioButton(
				"Involving favored player");
		rdbtnSelectedFavoredPlayer
				.setActionCommand(ReplayInclusionCriteria.SELECTED_FAVORED_PLAYER
						.toString());
		rdbtnSelectedFavoredPlayer.addActionListener(rdbtnListener);

		repIncGroup.setSelected(rdbtnSelectedFavoredPlayer.getModel(), true);

		GridBagConstraints gbc_rdbtnInvolvingFavoredPlayer = new GridBagConstraints();
		gbc_rdbtnInvolvingFavoredPlayer.anchor = GridBagConstraints.WEST;
		gbc_rdbtnInvolvingFavoredPlayer.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnInvolvingFavoredPlayer.gridx = 0;
		gbc_rdbtnInvolvingFavoredPlayer.gridy = 3;
		panel.add(rdbtnSelectedFavoredPlayer, gbc_rdbtnInvolvingFavoredPlayer);

		favoredPlayersCombo = new JComboBox();
		favoredPlayersCombo.setEditable(false);

		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 3;
		panel.add(favoredPlayersCombo, gbc_comboBox);
	}

	public void setFavoredPlayers(List<String> favoredPlayers) {
		Object[] playerList = new Object[0];
		if (favoredPlayers != null) {
			playerList = favoredPlayers.toArray();
		}

		favoredPlayersCombo.setModel(new DefaultComboBoxModel(playerList));
	}

	public ReplayInclusionCriteria getInclusionCriteria() {
		return inclusionCriteria;
	}

	public void setInclusionCriteria(ReplayInclusionCriteria inclusionCriteria) {
		if (inclusionCriteria == null) {
			throw new IllegalArgumentException("");
		} else {
			this.inclusionCriteria = inclusionCriteria;
		}

		repIncGroup.clearSelection();
		switch (inclusionCriteria) {
		case ANY_FAVORED_PLAYER:
			repIncGroup.setSelected(rdbtnAllFavoredPlayers.getModel(), true);
			favoredPlayersCombo.setEnabled(false);
			break;

		case SELECTED_FAVORED_PLAYER:
			repIncGroup
					.setSelected(rdbtnSelectedFavoredPlayer.getModel(), true);
			favoredPlayersCombo.setEnabled(true);
			break;
		}
	}

	public String getSelectedFavoredPlayer() {
		String favoredPlayer = null;

		if (ReplayInclusionCriteria.SELECTED_FAVORED_PLAYER
				.equals(inclusionCriteria)) {
			favoredPlayer = favoredPlayersCombo.getSelectedItem().toString();
		}

		return favoredPlayer;
	}

	public void setSelectedPlayer(String playerName) {
		favoredPlayersCombo.setSelectedItem(playerName);
	}

}
