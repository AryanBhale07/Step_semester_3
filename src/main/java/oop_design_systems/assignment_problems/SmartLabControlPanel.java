package main.java.oop_design_systems.assignment_problems;

import java.util.*;

interface Capability {
    String getName();
    String apply(String value);
}

class PowerCapability implements Capability {
    private boolean on;

    public String getName() {
        return "Power";
    }

    public String apply(String value) {
        if (!value.equalsIgnoreCase("ON") &&
            !value.equalsIgnoreCase("OFF"))
            return "Rejected";

        on = value.equalsIgnoreCase("ON");

        return on ? "ON" : "OFF";
    }
}

class BrightnessCapability implements Capability {
    private int brightness;

    public String getName() {
        return "Brightness";
    }

    public String apply(String value) {
        int valueInt = Integer.parseInt(value);

        if (valueInt < 0 || valueInt > 100)
            return "Rejected";

        brightness = valueInt;

        return "brightness set to " +
                brightness + "%";
    }
}

class TemperatureCapability implements Capability {
    private double temperature;

    public String getName() {
        return "Temperature";
    }

    public String apply(String value) {
        double valueDouble = Double.parseDouble(value);

        if (valueDouble < 16 || valueDouble > 30)
            return "Rejected";

        temperature = valueDouble;

        return "temperature set to " +
                temperature + "°C";
    }
}

class Device {
    private String name;
    private Map<String, Capability> capabilities =
            new HashMap<>();

    public Device(String name) {
        this.name = name;
    }

    public void addCapability(Capability capability) {
        capabilities.put(capability.getName(), capability);

        System.out.println(
            name + ": " +
            capability.getName() +
            " capability added."
        );
    }

    public void apply(String capabilityName, String value) {
        Capability capability =
                capabilities.get(capabilityName);

        if (capability == null)
            return;

        String result = capability.apply(value);

        if (!result.equals("Rejected"))
            System.out.println(
                name + ": " + result + "."
            );
    }

    public String getName() {
        return name;
    }

    public boolean hasCapability(String name) {
        return capabilities.containsKey(name);
    }
}

class SceneStep {
    String capability;
    String value;

    public SceneStep(String capability, String value) {
        this.capability = capability;
        this.value = value;
    }
}

class Scene {
    private String name;
    private List<SceneStep> steps =
            new ArrayList<>();

    public Scene(String name) {
        this.name = name;
    }

    public void addStep(SceneStep step) {
        steps.add(step);
    }

    public void execute(List<Device> devices) {
        int actions = 0;

        System.out.println(
            "Scene '" + name + "' started."
        );

        for (SceneStep step : steps) {
            for (Device device : devices) {
                if (device.hasCapability(step.capability)) {
                    device.apply(
                        step.capability,
                        step.value
                    );
                    actions++;
                }
            }
        }

        System.out.println(
            "Scene '" + name +
            "' completed: " +
            actions + " actions applied."
        );
    }
}

public class SmartLabControlPanel {
    public static void main(String[] args) {
        Device ac = new Device("Lab AC");
        Device lights = new Device("Ceiling Lights");
        Device projector = new Device("Projector");

        ac.addCapability(new PowerCapability());
        ac.addCapability(new TemperatureCapability());

        lights.addCapability(new PowerCapability());
        lights.addCapability(new BrightnessCapability());

        projector.addCapability(new PowerCapability());

        List<Device> devices =
                Arrays.asList(ac, lights, projector);

        Scene lecture = new Scene("Lecture Mode");

        lecture.addStep(
            new SceneStep("Power", "ON")
        );

        lecture.addStep(
            new SceneStep("Brightness", "40")
        );

        lecture.addStep(
            new SceneStep("Temperature", "24")
        );

        lecture.execute(devices);

        ac.apply("Temperature", "12");

        projector.addCapability(
            new BrightnessCapability()
        );

        projector.apply("Brightness", "70");
    }
}
