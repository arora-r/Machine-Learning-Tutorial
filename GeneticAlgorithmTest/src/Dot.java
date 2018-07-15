public class Dot {

	PVector pos; 
	PVector vel;
	PVector acc;
	
	Brain brain;
	
	boolean isBest = false;
	boolean reachedGoal = false;
	boolean dead = false;
	
	float fitness;
	
	Dot(){
		brain = new Brain(100); //Makes a brain that carries 100 instructions
		
		pos = new PVector(GUI.FRAME_WIDTH/2, GUI.FRAME_HEIGHT-50);
		vel = new PVector(0,0);
		acc = new PVector(0,0);
	}
	
	public void move(){
		if (brain.directions.length > brain.step){
			acc = brain.directions[brain.step];
			brain.step++;
		} else {
			dead = true;
		}
		
		//Applying acceleration and moving dot
		vel.add2(acc);
		vel.limit(0.01f); //Limiting speed
		pos.add2(vel);
	}
	
	public void update(){
		if (!dead && !reachedGoal) {
	      move();
	      if (pos.getX() < 2 || pos.getY() < 2 || pos.getX() > GUI.FRAME_WIDTH - 10 || pos.getY() > GUI.FRAME_HEIGHT - 20) {//if near the edges of the window then kill it 
	        dead = true;
	      } else if (PVector.dist(pos.getX(), pos.getY(), GUI.FRAME_WIDTH/2 - 15, 15) < 10) {
	        reachedGoal = true;
	      }
	    }
	}
	
	public void calcFitness(){
		if (reachedGoal) {
			fitness = (float) (1.0/16.0 + 10000.0/(Math.pow(brain.step, 2)));
		} else {
			float distanceToGoal = PVector.dist(pos.getX(), pos.getY(), GUI.FRAME_WIDTH/2 - 15, 15);
			fitness = (float) (1.0/(Math.pow(distanceToGoal, 2)));
		}
	}
	
	//Cloning dot
	public Dot getChild(){
		Dot baby = new Dot();
		baby.brain = brain.clone();
		return baby;
	}
	
	
}
