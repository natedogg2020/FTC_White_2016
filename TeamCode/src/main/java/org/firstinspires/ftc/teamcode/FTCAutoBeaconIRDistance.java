package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name="FTCWhite Auto Time + IR", group="Pushbot")
public class FTCAutoBeaconIRDistance extends LinearOpMode {

    /* Declare OpMode members. */
    FTCWhiteHardware robot = new FTCWhiteHardware(); // Use a Pushbot's hardware
    private ElapsedTime runtime = new ElapsedTime();
    //LightSensor             lightSensor;      // Primary LEGO Light sensor,
    OpticalDistanceSensor lightSensor;   // Alternative MR ODS sensor
    static final double   WHITE_THRESHOLD = 0.2;  // spans between 0.1 - 0.5 from dark to light


    @Override
    public void runOpMode() {

        // Step through each leg of the path, ensuring that the Auto mode has not been stopped along the way

        // Step 1: Drive forward to 2nd tile
        robot.frontLeftMotor.setPower(1);
        robot.rearLeftMotor.setPower(1);
        robot.rearRightMotor.setPower(1);
        robot.frontRightMotor.setPower(1);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.5)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        // Step 2:  Spin right for 1.7 seconds (90 degrees)
        robot.frontLeftMotor.setPower(1);
        robot.rearLeftMotor.setPower(1);
        robot.rearRightMotor.setPower(-1);
        robot.frontRightMotor.setPower(-1);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.7)) {
            telemetry.addData("Path", "Leg 2: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        // Step 3:  Drive Backwards for 3.5 Seconds
        robot.frontLeftMotor.setPower(-1);
        robot.rearLeftMotor.setPower(-1);
        robot.rearRightMotor.setPower(-1);
        robot.frontRightMotor.setPower(-1);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 3.5)) {
            telemetry.addData("Path", "Leg 3: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        //Drive sideways for 2.2 seconds
        robot.frontLeftMotor.setPower(1);
        robot.rearLeftMotor.setPower(-.5);
        robot.rearRightMotor.setPower(-1);
        robot.frontRightMotor.setPower(.5);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 2.2)) {
            telemetry.addData("Path", "Leg 3: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        //Correct Misalignment (.2 seconds)
        robot.frontLeftMotor.setPower(-1);
        robot.rearLeftMotor.setPower(-1);
        robot.rearRightMotor.setPower(1);
        robot.frontRightMotor.setPower(1);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < .2)) {
            telemetry.addData("Path", "Leg 2: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        //Drive Backwards for .5 seconds cuz sweggy mcbeggy
        robot.frontLeftMotor.setPower(-1);
        robot.rearLeftMotor.setPower(-1);
        robot.rearRightMotor.setPower(-1);
        robot.frontRightMotor.setPower(-1);
        runtime.reset();
        //5.2 total time backwards
        while (opModeIsActive() && (runtime.seconds() < .5)) {
            telemetry.addData("Path", "Leg 3: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        // Step 4:  Stop
        robot.frontLeftMotor.setPower(0);
        robot.rearLeftMotor.setPower(0);
        robot.rearRightMotor.setPower(0);
        robot.frontRightMotor.setPower(0);

        telemetry.addData("Path", "Complete");
        telemetry.update();

        //<<<<<START IR SENSOR CAPABILITIES>>>>>>

        robot.init(hardwareMap);
        //lightSensor = hardwareMap.lightSensor.get("sensor_light");                // Primary LEGO Light Sensor
        lightSensor = hardwareMap.opticalDistanceSensor.get("sensor_ods");  // Alternative MR ODS sensor.

        lightSensor.enableLed(true);
        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();
        while (!(isStarted() || isStopRequested())) {

            // Display the light level while we are waiting to start
            telemetry.addData("Light Level", lightSensor.getLightDetected());
            telemetry.update();
            idle();
        }

        // Start the robot moving forward, and then begin looking for a white line.
        robot.frontLeftMotor.setPower(-.5);
        robot.rearLeftMotor.setPower(-.5);
        robot.rearRightMotor.setPower(-.5);
        robot.frontRightMotor.setPower(-.5);

        // run until the white line is seen OR the driver presses STOP;
        while (opModeIsActive() && (lightSensor.getLightDetected() < WHITE_THRESHOLD)) {

            // Display the light level while we are looking for the line
            telemetry.addData("Light Level",  lightSensor.getLightDetected());
            telemetry.update();
        }

        // Stop all motors
        robot.frontLeftMotor.setPower(0);
        robot.rearLeftMotor.setPower(0);
        robot.rearRightMotor.setPower(0);
        robot.frontRightMotor.setPower(0);

    }
}


