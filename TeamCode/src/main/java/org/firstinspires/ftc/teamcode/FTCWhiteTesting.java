package org.firstinspires.ftc.teamcode;

/**
 * Created by natenckelvey on 10/14/16.
 */

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

/**
 * This file provides basic Telop driving for a Pushbot robot.
 * The code is structured as an Iterative OpMode
 *
 * This OpMode uses the common Pushbot hardware class to define the devices on the robot.
 * All device access is managed through the HardwarePushbot class.
 *
 * This particular OpMode executes a basic Tank Drive Teleop for a PushBot
 * It raises and lowers the claw using the Gampad Y and A buttons respectively.
 * It also opens and closes the claws slowly using the left and right Bumper buttons.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="FTC White Test Op", group="Pushbot")
public class FTCWhiteTesting extends OpMode {

    /* Declare OpMode members. */
    FTCWhiteTestHardware robot = new FTCWhiteTestHardware(); // use the class created to define a Pushbot's hardware
    // could also use HardwareFTCWhiteMatrix class.
    // sets rate to move servo


    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");
        //
        robot.liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0",  "Starting at %7d :%7d");
        telemetry.update();
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        double ch1;
        double ch2;
        double ch3;
        double ch4;
        double          clawOffset      = 0;                       // Servo mid position
        final double    CLAW_SPEED      = 0.02 ;

        //Ch1 = Right joystick X-axis
        //Ch2 = Right joystick y- axis(unused)
        //Ch3 = Left joystick Y-axis
        //Ch4 = Left joystick X-axis
        ch1 = -gamepad1.right_stick_x;
        ch2 = -gamepad1.right_stick_y;
        ch3 = -gamepad1.left_stick_y;
        ch4 = -gamepad1.left_stick_x;

        robot.frontLeftMotor.setPower(ch3 + ch1 + ch4);
        robot.rearLeftMotor.setPower(ch3 + ch1 - ch4);
        robot.rearRightMotor.setPower(ch3 - ch1 - ch4);
        robot.frontRightMotor.setPower(ch3 - ch1 + ch4);
        clawOffset = Range.clip(clawOffset, -0.5, 0.5);
        if(gamepad2.a) {
            robot.leftClaw.setPosition(robot.MID_SERVO + 1.5);
            robot.rightCLaw.setPosition(robot.MID_SERVO - 1.5);
        }
        else if(gamepad2.b) {
            robot.leftClaw.setPosition(robot.MID_SERVO - 1.5);
            robot.rightCLaw.setPosition(robot.MID_SERVO + 1.5);
        }


    }
}