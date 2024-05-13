package org.pinte.models.states;

import org.pinte.models.Canvas;

/**
 * State abstract class
 */
public abstract class State {

	Canvas canvas = Canvas.getInstance();

	/**
	 * Exit state by removing introduced handlers
	 */
	public abstract void exitState();

}
