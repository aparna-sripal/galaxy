package Train;

import java.util.Random;
import java.util.Scanner;


public class Startup extends Admin {

//	private 
	protected long session_id;
	protected WaterSensor water_sensor;
	protected RPMSensor rpm_sensor;
	protected CameraSensor camera_sensor;
	
	public Startup() {
		super();
		session_id = new Random().nextLong();
		init();
		water_sensor = new WaterSensor();
		rpm_sensor = new RPMSensor();
		camera_sensor = new CameraSensor();
	}
	
	private void init() {
		signIn();
		while (train_status == Status.DISABLED) {
			// Await admin to approve status to change.
			continue;
		}
		if (train_status == Status.OFFLINE) {
			init();
		}
	}
	
	
	protected void signIn() {
		Scanner scanner = new Scanner(System.in);
		for (int i = 0; i < MAX_LOGIN_ATTEMPTS; i++) {
			System.out.println("Enter IoT password to start system: ");
			String attempt = scanner.next();
			if (verifyPassword(attempt)) {
				log(session_id, "Successful login to IoT made.\n", Status.ONLINE);
				scanner.close();
				break;
			}
		}
		scanner.close();
		String error_str = "Too many invalid password atempts.\n" +
				"Train status has been disabled.\n";
		log(session_id, error_str, Status.DISABLED);
	}
	
	protected void signOut() {
		log(session_id, "Signing out.\n", Status.OFFLINE);
	}
	
}
