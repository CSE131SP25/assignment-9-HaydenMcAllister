package assignment9;

import java.util.LinkedList;

public class Snake {

	private static final double SEGMENT_SIZE = 0.02;
	private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
	private LinkedList<BodySegment> segments;
	private double deltaX;
	private double deltaY;
	private boolean ateFood;
	private long lastAteTime;
	private static final long GRACE_PERIOD = 500;
	
	public Snake() {
		//FIXME - set up the segments instance variable
		segments = new LinkedList<>();
		segments.add(new BodySegment(.5,.5, SEGMENT_SIZE));
		deltaX = 0;
		deltaY = 0;
		ateFood = false;
		lastAteTime = 0;
	}
	
	public void changeDirection(int direction) {
		if(direction == 1) { //up
			deltaY = MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 2) { //down
			deltaY = -MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 3) { //left
			deltaY = 0;
			deltaX = -MOVEMENT_SIZE;
		} else if (direction == 4) { //right
			deltaY = 0;
			deltaX = MOVEMENT_SIZE;
		}
	}
	
	/**
	 * Moves the snake by updating the position of each of the segments
	 * based on the current direction of travel
	 */
	public void move() {
		for (int i = segments.size() -1; i>0; i--) {
			BodySegment current = segments.get(i);
			BodySegment previous = segments.get(i-1);
			current.setX(previous.getX());
			current.setY(previous.getY());
		}
		BodySegment head = segments.getFirst();
		head.setX(head.getX() + deltaX);
		head.setY(head.getY() + deltaY);
			    
	}
	
	/**
	 * Draws the snake by drawing each segment
	 */
	public void draw() {
		for (BodySegment segment : segments) {
			segment.draw();
		}
	}
	
	/**
	 * The snake attempts to eat the given food, growing if it does so successfully
	 * @param f the food to be eaten
	 * @return true if the snake successfully ate the food
	 */
	public boolean eatFood(Food f) {
		BodySegment head = segments.getFirst();
		if(Math.abs(head.getX() - f.getX()) < SEGMENT_SIZE+.01 && Math.abs(head.getY() - f.getY()) < SEGMENT_SIZE+.01) {
			segments.addLast(new BodySegment(segments.getLast().getX(),segments.getLast().getY(), SEGMENT_SIZE));;
			ateFood = true;
			lastAteTime = System.currentTimeMillis();
			return true;
		}
		return false;
	}
	
	/**
	 * Returns true if the head of the snake is in bounds
	 * @return whether or not the head is in the bounds of the window
	 */
	public boolean isInbounds() {
		BodySegment head = segments.getFirst();
		return head.getX() >= 0 && head.getX() <= 1 && head.getY() >= 0 && head.getY() <= 1;
	}
	
	
}
