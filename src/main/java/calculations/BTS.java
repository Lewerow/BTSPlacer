package calculations;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PGraphics;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.AbstractMarker;
import de.fhpotsdam.unfolding.utils.GeoUtils;
import de.fhpotsdam.unfolding.utils.ScreenPosition;

/**
 * Created by Ja on 24.03.14.
 */
public class BTS extends AbstractMarker {

	private final List<BasebandResource> basebandResources = new LinkedList<BasebandResource>();
	private final List<RadioResource> radioResources = new LinkedList<RadioResource>();

	private static final Color outOfOrder = Color.red;
	private static final Color activeColor = Color.blue;
	private static final int alpha = 20;
    private static final double availableMaxSignalLevel = 15000;
    private static final double maxAvailableRange = 30f;
	private final BtsType cellType;

	public BTS(Location location, BtsType cellType) {
		super(location);
		this.cellType = cellType;
	}

	public void addBBResource(BasebandResource basebandResource) {
		basebandResources.add(basebandResource);
	}

	public int getBBResourceCount() {
		return basebandResources.size();
	}

	public int getRadioResourceCount() {
		return radioResources.size();
	}

	public void addRadioResource(RadioResource radioResource) {
		radioResources.add(radioResource);
	}

	public double getBBCapacity() {
		double capacity = 0;
		for (BasebandResource b : basebandResources)
			capacity += b.getCapacity();

		return capacity;
	}

	public double getMaxSignalLevel() {
		return getBBCapacity() * getRadioResourceCount();
	}

	@Override
	public PlacerLocation getLocation() {
		return (PlacerLocation) location;
	}

	@Override
	public void setLocation(Location l) {
		location = l;
	}

	public List<RadioResource> getRadioResources() {
		return radioResources;
	}

	public List<BasebandResource> getBasebandResources() {
		return basebandResources;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BTS) {
			BTS other = (BTS) obj;
			return basebandResources.containsAll(other.getBasebandResources())
					&& radioResources.containsAll(other.getRadioResources())
					&& location.equals(other.getLocation());
		}
		return false;
	}

	@Override
	public String toString() {
		return String.format("BTS(%s, %s ,%s)", location, basebandResources, radioResources);
	}

	public int getRange() {
		int maxRange = 0;
		for (RadioResource r : radioResources)
			if (maxRange < r.getRange())
				maxRange = r.getRange();

		return maxRange;
	}

	@Override
	public void draw(PGraphics p, float v, float v2, UnfoldingMap map) {
		p.noStroke();
		float distance = getDistance(getLocation(), (float)(getRange() / maxAvailableRange), map);
		switch (cellType) {
		case DIRECTIONAL:
			drawOutOfOrderCells(p, v, v2, distance);
			break;
		case CIRCULAR:
			drawCircleCell(p, v, v2, distance);
			break;
		default:
			break;
		}
	}

	@Override
	public void draw(PGraphics pGraphics, float v, float v2) {
	}

	private void drawOutOfOrderCells(PGraphics p, float x, float y, float distance) {
		p.fill(outOfOrder.getRGB(), alpha);
		drawGradient(p, distance, 0, 1.2f, x, y, 20);
		drawGradient(p, distance, 2, 1.2f, x, y, 20);
		drawGradient(p, distance, 4, 1.2f, x, y, 20);
	}

	private void drawCircleCell(PGraphics p, float x, float y, float distance) {
        int transparency = (int)(255 * getMaxSignalLevel() / availableMaxSignalLevel);
		p.fill(activeColor.getRGB(), transparency);
		drawGradient(p, distance, 0, 6.3f, x, y, 20);
	}

	private void drawGradient(PGraphics p, float distance, float startAngle, float scopeAngle,
			float x, float y, int layerCount) {
		float distanceStep = distance / layerCount;
		float currentDistance = distanceStep;
		for (int i = layerCount; i > 0; i--) {
			p.arc(x, y, currentDistance, currentDistance, startAngle, startAngle + scopeAngle);
			currentDistance += distanceStep;
		}
	}

	private float getDistance(Location mainLocation, float size, UnfoldingMap map) {
		Location tempLocation = GeoUtils.getDestinationLocation(mainLocation, 90, size);
		ScreenPosition pos1 = map.getScreenPosition(mainLocation);
		ScreenPosition pos2 = map.getScreenPosition(tempLocation);
		return PApplet.dist(pos1.x, pos1.y, pos2.x, pos2.y);
	}

	@Override
	protected boolean isInside(float v, float v2, float v3, float v4) {
		return false;
	}
}
