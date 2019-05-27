package view.panels;

import java.awt.Graphics;

import javax.swing.JPanel;

public abstract class Draw extends JPanel {

	protected int MIN_VALUE = 0;

//	public abstract void lineGraph();
//
//	public abstract void cakeGraph();
//
//	public abstract void tracerGraph();

	public abstract void pilierGraph();

	protected Graphics graphic() {
		return this.getGraphics();
	}

}
