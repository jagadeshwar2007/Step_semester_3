abstract class Drone {
    public abstract String fly();
}

interface Trackable {
    String getLocation();
}

class DeliveryDrone extends Drone implements Trackable {
    private String id;

    public DeliveryDrone(String id) {
        this.id = id;
    }

    public String fly() {
        return id + " delivering a package";
    }

    public String getLocation() {
        return id + " at Sector 4";
    }
}

class ScoutDrone extends Drone {
    private String id;

    public ScoutDrone(String id) {
        this.id = id;
    }

    public String fly() {
        return id + " scouting the area";
    }
}

class GroundRobot implements Trackable {
    private String id;

    public GroundRobot(String id) {
        this.id = id;
    }

    public String getLocation() {
        return id + " at Sector 4";
    }
}

public class Week7AssignmentQuestion5 {
    static String getLocationIfTrackable(Object o) {
        if (o instanceof Trackable) {
            Trackable t = (Trackable) o;
            return t.getLocation();
        }
        return "Tracking not available";
    }

    public static void main(String[] args) {
        Object[] things = {new DeliveryDrone("DR-1"), new ScoutDrone("SC-1"), new GroundRobot("GR-1")};

        for (Object thing : things) {
            System.out.println(getLocationIfTrackable(thing));
        }
    }
}
