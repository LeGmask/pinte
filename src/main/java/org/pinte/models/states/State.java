package org.pinte.models.states;

import org.pinte.models.Canvas;

/**
 * State interface
 */
public abstract class State {

	Canvas canvas = Canvas.getInstance();

	/**
	 * Singleton instance of the Canvas
	 */
	public abstract void initialize();
}
