package io;

public class BulletData {
	public float linVeloc;
	public float linAccel;
	public float angVeloc;
	public float angAccel;
	public float minLV;
	public float maxLV;
	public float minAV;
	public float maxAV;
	
	public BulletData(float linVeloc, float linAccel, float angVeloc,
			float angAccel) {
		super();
		this.linVeloc = linVeloc;
		this.linAccel = linAccel;
		this.angVeloc = angVeloc;
		this.angAccel = angAccel;
		this.minLV = Float.NEGATIVE_INFINITY;
		this.maxLV = Float.POSITIVE_INFINITY;
		this.minAV = Float.NEGATIVE_INFINITY;
		this.maxAV = Float.POSITIVE_INFINITY;
	}
}
