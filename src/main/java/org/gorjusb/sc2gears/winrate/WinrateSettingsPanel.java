package org.gorjusb.sc2gears.winrate;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.BorderLayout;

public class WinrateSettingsPanel extends JPanel {
	private JTextField textField;

	public WinrateSettingsPanel() {
		setLayout(new BorderLayout(0, 0));
				
						JLabel lblWinRatePlugin = new JLabel("Win Rate Plugin Settings");
						lblWinRatePlugin.setHorizontalAlignment(SwingConstants.CENTER);
						lblWinRatePlugin.setFont(new Font("Tahoma", Font.PLAIN, 22));
						add(lblWinRatePlugin, BorderLayout.NORTH);
		
				JPanel panel = new JPanel();
				panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Include the following replays", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				add(panel, BorderLayout.CENTER);
				GridBagLayout gbl_panel = new GridBagLayout();
				gbl_panel.columnWidths = new int[] { 0, 0, 0 };
				gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
				gbl_panel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
				gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
				panel.setLayout(gbl_panel);
				
				JRadioButton rdbtnAllReplays = new JRadioButton("All replays");
				rdbtnAllReplays.setHorizontalAlignment(SwingConstants.CENTER);
				GridBagConstraints gbc_rdbtnAllReplays = new GridBagConstraints();
				gbc_rdbtnAllReplays.anchor = GridBagConstraints.WEST;
				gbc_rdbtnAllReplays.insets = new Insets(0, 0, 5, 5);
				gbc_rdbtnAllReplays.gridx = 0;
				gbc_rdbtnAllReplays.gridy = 0;
				panel.add(rdbtnAllReplays, gbc_rdbtnAllReplays);
				
						JRadioButton rdbtnIncludedAnyPlayer = new JRadioButton(
								"Involving favored players");
						GridBagConstraints gbc_rdbtnIncludedAnyPlayer = new GridBagConstraints();
						gbc_rdbtnIncludedAnyPlayer.anchor = GridBagConstraints.WEST;
						gbc_rdbtnIncludedAnyPlayer.insets = new Insets(0, 0, 5, 5);
						gbc_rdbtnIncludedAnyPlayer.gridx = 0;
						gbc_rdbtnIncludedAnyPlayer.gridy = 1;
						panel.add(rdbtnIncludedAnyPlayer, gbc_rdbtnIncludedAnyPlayer);
						
								JRadioButton rdbtnContaining = new JRadioButton(
										"Involving player (full name):");
								GridBagConstraints gbc_rdbtnContaining = new GridBagConstraints();
								gbc_rdbtnContaining.insets = new Insets(0, 0, 5, 5);
								gbc_rdbtnContaining.anchor = GridBagConstraints.WEST;
								gbc_rdbtnContaining.gridx = 0;
								gbc_rdbtnContaining.gridy = 2;
								panel.add(rdbtnContaining, gbc_rdbtnContaining);
								
										textField = new JTextField();
										GridBagConstraints gbc_textField = new GridBagConstraints();
										gbc_textField.insets = new Insets(0, 0, 5, 0);
										gbc_textField.fill = GridBagConstraints.HORIZONTAL;
										gbc_textField.gridx = 1;
										gbc_textField.gridy = 2;
										panel.add(textField, gbc_textField);
										textField.setColumns(10);
	}

}
