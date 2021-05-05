package Train;

public class IoT {
	
	private Analytics_Engine Analytics;
	protected long session_id;
	private final static double MAX_SPEED = 60 ;
	private static double CURR_SPEED;
	private Boolean is_hazard_rain, is_hazard_rpm, is_hazard_camera;
	private String alert;

	//given an Analytics_Engine, we initialize the class variables to the current values from the analytics engine
	//check to see if there is a hazard to begin with and display alert if so
	public IoT(Analytics_Engine Analytics) {
		session_id = Analytics.session_id;
		MAX_SPEED = Analytics.MAX_SPEED;
		is_hazard_rain = Analytics.is_hazard_rain;
		is_hazard_rpm = Analytics.is_hazard_rpm;
		is_hazard_camera = Analytics.is_hazard_camera;
		alert = Analytics.alert;
		if (is_hazard_rain || is_hazard_rpm || is_hazard_camera) {
			displayAlert();
		}
		CURR_SPEED = MAX_SPEED;
	}
	
	//returns if there is hazard rain
	public boolean is_hazard_Rain() {
		return is_hazard_rain;
	}
	
	//returns if there is hazard rpm
	public boolean is_hazard_RPM() {
		return is_hazard_rpm;
	}
	
	//returns if there is an object that is sensed
	public boolean is_hazard_Camera() {
		return is_hazard_camera;
	}
	
	//returns the max safe speed the train should go
	public double readMaxSpeed() {
		return MAX_SPEED;
	}
	
	//sets the max safe speed the train should go
	//if the new max speed is not less than the old one we don't update it and print and error
	protected void setSpeed(double speed) {
		if (speed < MAX_SPEED) {
			log("Set train speed to " + Double.toString(speed) + ".\n", Status.ONLINE);
			CURR_SPEED = speed;
		}
	}
	
	//to render the rain data we process the rain data through the Analytics_Engine and print out the rain_data
	public void renderRain() {
		int rain_data = Analytics.processRainData();
		System.out.println(rain_data);
	}
	
	//to render the rpm data we process the rpm data through the Analytics_Engine and print out the rpm_data
	public void renderRPM() {
		int rpm_data = Analytics.processRPMData();
		System.out.println(rpm_data);
	}
	
	//to render the camera data we process the camera data through the Analytics_Engine and print out the camera_data
	public void renderCamera() {
		int camera_data = Analytics.processCameraData();
		System.out.println(camera_data);
	}
	
	//to render the speed data we print out the current speed and the maximum speed
	public void renderSpeed() {
		System.out.println("Current speed is " + Double.toString(CURR_SPEED));
		System.out.println("Maximum speed is " + Double.toString(MAX_SPEED));
	}
	
	//to render the authentication we print out the current session id
	public void renderAuthentication() {
		System.out.println("IoT authenticated under session ID " + Long.toString(session_id));
	}
	
	//to render an alert we print out the current alert mesage
	public void displayAlert() {
		System.out.println(alert);
	}
	
	//to log we use the log function from the admin
	protected void log(String msg, Status new_status) {
		Technician.log(session_id, msg, new_status);
	}
	

}
