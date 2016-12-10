package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
/**
 * This code is a test code to first test our timing autonomous code to reach the beacon.
 * the next portion deals with a distance sensor that will read from 2 distance sensors
 * to help guide the robot to the correct beacon, which will have two variations to go
 * to either the right side or the left side.
 **/
@Autonomous(name="FTCWhite Auto Time + IR V2(w/2 Light sensors", group="Pushbot")
public class FTCAutoBeaconDistanceV2 extends LinearOpMode {

    /* Declare OpMode members. */
    FTCWhiteHardware robot = new FTCWhiteHardware(); // Use White's Hardware
    private ElapsedTime runtime = new ElapsedTime();
    OpticalDistanceSensor lightSensor;// MR ODS sensor
    OpticalDistanceSensor lightSensor2; //second used MR ODS sensor
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
        /*
        <<<<<START IR SENSOR CAPABILITIES>>>>>>
        */
        robot.init(hardwareMap); //initialize hardware map

        lightSensor = hardwareMap.opticalDistanceSensor.get("sense_left");  // initiates MR ODS sensor.
        lightSensor2 = hardwareMap.opticalDistanceSensor.get("sense_right"); // initiates 2nd MR ODS sensor.
        lightSensor.enableLed(true); //enables light sensor
        lightSensor2.enableLed(true); //enables 2nd light sensor
        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();
        while (!(isStarted() || isStopRequested())) {

            // Display the light level while we are waiting to start
            telemetry.addData("Light Level", lightSensor.getLightDetected());
            telemetry.addData("Light Level2", lightSensor2.getLightDetected());
            telemetry.update();
            idle();
        }

        // Start the robot moving forward, and then begin looking for a white line.
        robot.frontLeftMotor.setPower(-1);
        robot.rearLeftMotor.setPower(-1);
        robot.rearRightMotor.setPower(-.25);
        robot.frontRightMotor.setPower(-.25);

        // run until the white line is seen OR the driver presses STOP;
        while (opModeIsActive() && (lightSensor.getLightDetected() < WHITE_THRESHOLD)) {
            // Stop all motors, Start lift motor left
            robot.frontLeftMotor.setPower(0);
            robot.rearLeftMotor.setPower(0);
            robot.rearRightMotor.setPower(0);
            robot.frontRightMotor.setPower(0);
            robot.testMotor.setPower(-1);
            // Display the light level while we are looking for the line
            telemetry.addData("Light Level",  lightSensor.getLightDetected());
            telemetry.update();
        }
        while (opModeIsActive() && (lightSensor2.getLightDetected() < WHITE_THRESHOLD)){
           // Stop all motors, start lift motor right
            robot.frontLeftMotor.setPower(0);
            robot.rearLeftMotor.setPower(0);
            robot.rearRightMotor.setPower(0);
            robot.frontRightMotor.setPower(0);
            robot.testMotor.setPower(1);
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
