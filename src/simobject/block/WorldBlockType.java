package simobject.block;

import java.awt.Color;

public enum WorldBlockType {
	/* TODO: Decide if there should be a single 'empty" block rather than creating many of them */
	EMPTY (Color.WHITE), 
	NON_EMPTY (Color.BLACK);
	
	private Color renderColor;
	
	private WorldBlockType(Color color) {
		this.renderColor = color;
	}
	
	/**
	 * Returns the render {@link Color} of this {@link WorldBlockType}.
	 * @return The render color of this block type.
	 */
	public Color getRenderColor() {
		return renderColor;
	}
}
