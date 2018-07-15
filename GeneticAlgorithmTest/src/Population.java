public class Population {
	Dot[] dots;
	
	float fitnessSum;
	public static int gen = 1;
	
	int bestDot = 0; //index of best dot
	
	int minStep = 1000;
	
	Population(int size){
		dots = new Dot[size];
		for (int i = 0; i < size; i++){
			dots[i] = new Dot();
		}
	}
	
	public void update() {
		for (int i = 0; i < dots.length; i++) {
			if (dots[i].brain.step > minStep) {//if the dot has already taken more steps than the best dot has taken to reach the goal
				dots[i].dead = true;//then it dead
	    	} else {
	    		dots[i].update();
	        }
	    }
    }
	
	void calculateFitness(){
		for (int i = 0; i< dots.length; i++) {
	        dots[i].calcFitness();
	    }
	}
	
	boolean allDotsDead(){
		for (int i = 0; i < dots.length; i++){
			if (!dots[i].dead && !dots[i].reachedGoal){
				return false;
			}
		}
		return true;
	}
	
	void naturalSelection(){
		Dot[] newDots = new Dot[dots.length]; //next gen
		setBestDot();
		calculateFitnessSum();
		
		newDots[0] = dots[bestDot].getChild();
		newDots[0].isBest = true;
		for (int i = 1; i < newDots.length; i++){
			Dot parent = selectParent();
			
			newDots[i] = parent.getChild();
		}
		
		dots = newDots.clone();
		gen++;
	}
	
	void calculateFitnessSum(){
		fitnessSum = 0;
		for (int i=0; i<dots.length; i++){
			fitnessSum += dots[i].fitness;
		}
	}
	
	
	Dot selectParent(){
		float rand = (float) (Math.random() * fitnessSum);
		float runningSum = 0;
		
		for (int i = 0; i < dots.length; i++){
			runningSum += dots[i].fitness;
			if (runningSum > rand){
				return dots[i];
			}
		}
		
		return null; //shouldnt get here
	}
	
	
	void mutateBabies(){
		for (int i = 1; i < dots.length; i++){
			dots[i].brain.mutate();
		}
	}
	
	void setBestDot(){
		float max = 0;
		int maxIndex = 0;
		for (int i = 0; i < dots.length; i++){
			if (dots[i].fitness > max){
				max = dots[i].fitness;
				maxIndex = i;
			}
		}
		
		bestDot = maxIndex;
		
		if(dots[bestDot].reachedGoal){
			minStep = dots[bestDot].brain.step;
			System.out.println("step: " + minStep);
		}
		
	}
	
	
}
