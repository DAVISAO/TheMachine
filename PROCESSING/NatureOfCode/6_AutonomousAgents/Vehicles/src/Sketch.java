import processing.core.PApplet;
import processing.core.PVector;

public class Sketch extends PApplet {

	public static void main(String[] args) {
		PApplet.main("Sketch");
	}


	class Mover {

		PVector pos;// position
		PVector vel; // velocity
		PVector acc; // acceleration

		Mover(float x, float y) {
			pos = new PVector(x, y);
			vel = new PVector();
			acc = new PVector();
		}

		void update() {

			vel.add(acc);
			pos.add(vel);

			acc.mult(0); // clear acceleration
		}

		void display() {
			pushStyle();
			fill(50);
			ellipse(pos.x, pos.y, 30, 30);
			popStyle();
		}

		void applyForce(PVector force) {
			acc.add(force);
		}

	}
	
	
	class Vehicle extends Mover {
		
		PVector desv; // desired velocity
		PVector steeringForce;
		
		Vehicle(float x, float y) {
			super(x, y);
			desv = new PVector();
			steeringForce = new PVector();
		}
		
		/*
		
		@Override void update() {
			
			
			// Apply steering force
			// F_steer = v_desired - v_current
			steeringForce.set(desv);
			steeringForce.sub(vel);
			//applyForce(steeringForce);
			
			super.update();
		}
		*/
		
		@Override void display() {
			fill(0, 200);
			noStroke();
			pushMatrix();
			translate(pos.x, pos.y);
			rotate(atan(vel.y/vel.x));
			triangle(10, 0, -10, -8, -10, 8);
			popMatrix();
		}
		
	}
	
	Vehicle v;

	@Override public void settings() {
		size(400, 400);
	}

	@Override public void setup() {
		v = new Vehicle(50, 50);
	}

	@Override public void draw() {
		background(200);
		v.update();
		v.display();
	}
}
