package org.gorjusb.sc2gears.winrate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
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

public class WinrateSettingsPanel extends JPanel implements
		PropertyChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2065360165720145044L;
	private ButtonGroup repIncGroup;
	private JRadioButton rdbtnAllFavoredPlayers;
	private JRadioButton rdbtnSelectedFavoredPlayer;
	private JComboBox favoredPlayersCombo;
	private boolean updatingUi = false;

	public WinrateSettingsPanel(final SettingsModel model,
			List<String> favoredPlayers) {
		model.addPropertyChangeListener(this);

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
				if (!updatingUi) {
					try {
						updatingUi = true;
						model.setReplayInclusionCriteria(ReplayInclusionCriteria
								.valueOf(e.getActionCommand()));
					} finally {
						updatingUi = false;
					}
				}
			}
		};

		rdbtnAllFavoredPlayers = new JRadioButton("Involving favored players");
		rdbtnAllFavoredPlayers
				.setActionCommand(ReplayInclusionCriteria.ALL_FAVORED_PLAYERS
						.toString());

		rdbtnAllFavoredPlayers.addActionListener(rdbtnListener);

		GridBagConstraints gbc_rdbtnFavoredPlayers = new GridBagConstraints();
		gbc_rdbtnFavoredPlayers.anchor = GridBagConstraints.WEST;
		gbc_rdbtnFavoredPlayers.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnFavoredPlayers.gridx = 0;
		gbc_rdbtnFavoredPlayers.gridy = 2;
		panel.add(rdbtnAllFavoredPlayers, gbc_rdbtnFavoredPlayers);

		rdbtnSelectedFavoredPlayer = new JRadioButton(
				"Involving favored player");
		rdbtnSelectedFavoredPlayer
				.setActionCommand(ReplayInclusionCriteria.SELECTED_FAVORED_PLAYER
						.toString());
		rdbtnSelectedFavoredPlayer.addActionListener(rdbtnListener);

		GridBagConstraints gbc_rdbtnInvolvingFavoredPlayer = new GridBagConstraints();
		gbc_rdbtnInvolvingFavoredPlayer.anchor = GridBagConstraints.WEST;
		gbc_rdbtnInvolvingFavoredPlayer.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnInvolvingFavoredPlayer.gridx = 0;
		gbc_rdbtnInvolvingFavoredPlayer.gridy = 3;
		panel.add(rdbtnSelectedFavoredPlayer, gbc_rdbtnInvolvingFavoredPlayer);

		repIncGroup = new ButtonGroup();
		repIncGroup.add(rdbtnAllFavoredPlayers);
		repIncGroup.add(rdbtnSelectedFavoredPlayer);

		favoredPlayersCombo = new JComboBox(favoredPlayers.toArray());
		favoredPlayersCombo.setEditable(false);

		favoredPlayersCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!updatingUi) {
					try {
						updatingUi = true;
						model.setSelectedFavoredPlayer(favoredPlayersCombo
								.getSelectedItem().toString());
					} finally {
						updatingUi = false;
					}
				}
			}
		});

		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 3;
		panel.add(favoredPlayersCombo, gbc_comboBox);

		updateRadioGroup(model.getReplayInclusionCriteria());
		updateComboBox(model.getSelectedFavoredPlayer());
	}

	public void propertyChange(PropertyChangeEvent evt) {

		if (SettingsModel.REPLAY_INCLUSION_CRITERIA_PROP.equals(evt
				.getPropertyName())) {
			updateRadioGroup((ReplayInclusionCriteria) evt.getNewValue());
		}

		if (SettingsModel.SELECTED_FAVORED_PLAYER_PROP.equals(evt
				.getPropertyName())) {
			updateComboBox((String) evt.getNewValue());
		}
	}

	private void updateRadioGroup(ReplayInclusionCriteria value) {
		System.err.println("inclusion value: " + value);
		repIncGroup.clearSelection();
		if (ReplayInclusionCriteria.ALL_FAVORED_PLAYERS.equals(value)) {
			repIncGroup.setSelected(rdbtnAllFavoredPlayers.getModel(), true);
			favoredPlayersCombo.setEnabled(false);
		} else if (ReplayInclusionCriteria.SELECTED_FAVORED_PLAYER
				.equals(value)) {
			repIncGroup
					.setSelected(rdbtnSelectedFavoredPlayer.getModel(), true);
			favoredPlayersCombo.setEnabled(true);
		}
	}

	private void updateComboBox(String value) {
		favoredPlayersCombo.setSelectedItem(value);
	}
}
