package org.gorjusb.sc2gears.winrate;

import hu.belicza.andras.sc2gearspluginapi.SettingsControl;

import java.awt.Container;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class WinrateSettingsControl implements SettingsControl {
	private WinrateSettingsPanel panel;
	private JDialog dialog;
	
	public WinrateSettingsControl() {
	}

	public Container getEditorPanel() {
		return panel;
	}

	public void onCancelButtonPressed() {
		JOptionPane.showConfirmDialog(panel, "TODO: implement Cancel");
	}

	public void onOkButtonPressed() {
		JOptionPane.showConfirmDialog(panel, "TODO: implement OK");
	}

	public void receiveSettingsDialog(JDialog dialog) {
		this.dialog = dialog;
	}
	
	public WinrateSettingsPanel getPanel() {
		return panel;
	}

	public void setPanel(WinrateSettingsPanel panel) {
		this.panel = panel;
	}

	public JDialog getDialog() {
		return dialog;
	}

	public void setDialog(JDialog dialog) {
		this.dialog = dialog;
	}

}
