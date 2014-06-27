package io;

public class Location {
	public boolean absolute;
	public float x;
	public float y;
	public float angle;
	
	public Location(float x, float y, float angle, boolean absolute) {
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.absolute = absolute;
	}
}
