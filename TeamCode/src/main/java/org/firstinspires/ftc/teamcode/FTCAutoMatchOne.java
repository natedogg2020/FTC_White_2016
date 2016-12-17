package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by 226486 on 12/10/16.
 */

@Autonomous(name="FTCWhite Auto Beacon Preatoyooooo", group="Pushbot")
public class FTCAutoMatchOne extends LinearOpMode {

    /* Declare OpMode members. */
    FTCWhiteHardware robot = new FTCWhiteHardware(); // Use a Pushbot's hardware
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step through each leg of the path, ensuring that the Auto mode has not been stopped along the way

        // Step 1:  Drive forward for 3 seconds
        robot.frontLeftMotor.setPower(0);
        robot.rearLeftMotor.setPower(0);
        robot.rearRightMotor.setPower(0);
        robot.frontRightMotor.setPower(0);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 10)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.frontLeftMotor.setPower(1);
        robot.rearLeftMotor.setPower(1);
        robot.rearRightMotor.setPower(1);
        robot.frontRightMotor.setPower(1);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 4)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
    }
}
