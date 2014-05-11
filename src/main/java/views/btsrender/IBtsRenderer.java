package views.btsrender;

import java.awt.Graphics;

import calculations.BTS;
import calculations.Terrain;

@Deprecated
public interface IBtsRenderer {

	public void setTerrain(Terrain terrain);

	public void drawBts(Graphics g, BTS bts);

}
