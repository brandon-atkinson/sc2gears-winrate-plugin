package org.gorjusb.sc2gears.winrate.presentation;

import java.awt.Container;
import java.awt.Window;

import javax.swing.JDialog;

public class MovableBorderlessDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ComponentMover compMover;
	
	public MovableBorderlessDialog() {
		super();
		init();
	}

	public MovableBorderlessDialog(Window owner) {
		super(owner);
		init();
	}
	
	private void init() {
		compMover = new ComponentMover(this, getContentPane());
		setUndecorated(true);
	}

	@Override
	public void setContentPane(Container contentPane) {
		compMover.deregisterComponent(getContentPane());
		super.setContentPane(contentPane);
		compMover.registerComponent(contentPane);
	}
	
}