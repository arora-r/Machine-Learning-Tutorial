public class Brain {
	PVector[] directions;
	int step = 0;
	
	Brain(int size){
		directions = new PVector[size];
		randomize();
	}
	
	void randomize(){
		for (int i = 0; i < directions.length; i++){
			float randomAngle = (float) (Math.random()*2*Math.PI);
			directions[i] = PVector.fromAngle(randomAngle);
		}
	}
	
	public Brain clone(){
		Brain clone = new Brain(directions.length);
		for (int i = 0; i < directions.length; i++){
			clone.directions[i] = directions[i].copy2();
		}
		return clone;
	}
	
	public void mutate(){
		float mutationRate = 0.03f; //Percent chance that any vector in the directions gets changed 
		for (int i = 0; i < directions.length; i++){
			float rand = (float) Math.random();
			if (rand < mutationRate){
				float randomAngle = (float) (Math.random()*2*Math.PI);
				directions[i] = PVector.fromAngle(randomAngle);
			}
		}
	}
}
