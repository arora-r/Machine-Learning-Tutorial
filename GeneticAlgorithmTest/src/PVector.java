import java.util.Random;

public class PVector {
	private float x;
	private float y;
	private float z;
	
	PVector (float x, float y){
		this.x = x;
		this.y = y;
		this.z = 0;
	}
	
	PVector (float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void set (float newX, float newY, float newZ){
		this.x = newX;
		this.y = newY;
		this.z = newZ;
	}
	public void set (PVector v1){
		this.x = v1.x;
		this.y = v1.y;
		this.z = v1.z;
	}
	
	public float getX (){
		return this.x;
	}
	public float getY (){
		return this.y;
	}
	public float getZ (){
		return this.z;
	}
	
	public void random2D (int range){
		Random rand = new Random();
		this.x = rand.nextInt(range);
		this.y = rand.nextInt(range);
		this.z = 0;
	}
	
	public float dist3 (PVector v1){
		float tempX, tempY, tempZ;
		//Getting separate directions
		tempX = (float) Math.pow((this.x - v1.x), 2);
		tempY = (float) Math.pow((this.y - v1.y), 2);
		tempZ = (float) Math.pow((this.z - v1.z), 2);
		
		//Add and square root the sum of the directions
		return (float) Math.sqrt(tempX + tempY + tempZ);
	}
	
	public static float dist (PVector v1, PVector v2){
		float tempX, tempY, tempZ;
		//Getting separate directions
		tempX = (float) Math.pow((v1.x - v2.x), 2);
		tempY = (float) Math.pow((v1.y - v2.y), 2);
		tempZ = (float) Math.pow((v1.z - v2.z), 2);
		
		//Add and square root the sum of the directions
		return (float) Math.sqrt(tempX + tempY + tempZ);
	}
	
	public static float dist (float x1, float y1, float x2, float y2){
		float tempX, tempY, tempZ;
		//Getting separate directions
		tempX = (float) Math.pow((x1 - x2), 2);
		tempY = (float) Math.pow((y1 - y2), 2);
		tempZ = 0;
		
		//Add and square root the sum of the directions
		return (float) Math.sqrt(tempX + tempY + tempZ);
	}
	
	public static PVector fromAngle (float angle){
		float x, y;
		x = (float)Math.cos(angle);
		y = (float)Math.sin(angle);
		return new PVector(x, y); 
	}
	
	public PVector copy2(){
		PVector newVector = new PVector(this.x, this.y);
		return newVector;
	}
	
	public PVector copy3(){
		PVector newVector = new PVector(this.x, this.y, this.z);
		return newVector;
	}
	
	public void add2(PVector v2){
		this.x += v2.x;
		this.y += v2.y;
	}
	
	public void add3(PVector v2){
		this.x += v2.x;
		this.y += v2.y;
		this.z += v2.z;
	}
	
	public PVector limit (float max){
		//first find the original magnitude (take squares of each comp and add then sqrt)
		//then divide by limit
		//divide each component by the ratio of the original mag/limit
		float originalMag = (float) Math.sqrt((Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)));
		float ratio = originalMag/max;
		return new PVector ((x/ratio),(y/ratio),(z/ratio));
	}
}
