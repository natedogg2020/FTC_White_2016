package org.firstinspires.ftc.teamcode;

/**
 * Created by natemckelvey on 10/14/16.
 */

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

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

@TeleOp(name="FTC White MechOp", group="Pushbot")
public class FTCWhiteMecanumDrive extends OpMode {

    /* Declare OpMode members. */
    FTCWhiteHardware robot = new FTCWhiteHardware(); // use the class created to define a Pushbot's hardware
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
        telemetry.addData("Say", "Hello Driver");    //
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
        double encoderLim;
        double encoderVal;
        /*
        <<<<MOTORS>>>>
        */
        encoderLim = -6000.00;
        encoderVal = robot.liftMotor.getCurrentPosition();
        telemetry.addData("DANK MEMES", encoderVal);

        //Ch1 = Right joystick X-axis
        //Ch2 = Right joystick y- axis(unused)
        //Ch3 = Left joystick Y-axis
        //Ch4 = Left joystick X-axis
        ch1 = gamepad1.right_stick_x;
        ch2 = -gamepad1.right_stick_y;
        ch3 = -gamepad1.left_stick_y;
        ch4 = -gamepad1.left_stick_x;

        robot.frontLeftMotor.setPower(ch3 + ch1 + ch4);
        robot.rearLeftMotor.setPower(ch3 + ch1 - ch4);
        robot.rearRightMotor.setPower(ch3 - ch1 - ch4);
        robot.frontRightMotor.setPower(ch3 - ch1 + ch4);
        //robot.liftMotor.setPower(gamepad2.left_trigger - gamepad2.right_trigger);
        if(encoderVal >= encoderLim && gamepad2.right_trigger > 0){
            robot.liftMotor.setPower(-gamepad2.right_trigger);
        } else if (gamepad2.left_trigger > 0) {
            robot.liftMotor.setPower(gamepad2.left_trigger);
        } else {
            robot.liftMotor.setPower(0);
        }
        /*
        <<<SERVOS>>>
        */
        if (gamepad2.y) {
            robot.leftClaw.setPosition(robot.MID_SERVO - 1);
        } else if (gamepad2.a) {
            robot.leftClaw.setPosition(robot.MID_SERVO + 1);
        } else if (gamepad2.dpad_up) {
            robot.rightCLaw.setPosition(robot.MID_SERVO + 1);
        } else if (gamepad2.dpad_down) {
            robot.rightCLaw.setPosition(robot.MID_SERVO - 1);
        } else if (gamepad2.right_bumper) {
            robot.rightBeaconClaw.setPosition(robot.MID_SERVO + .6);
        } else if (gamepad2.b) {
            robot.rightBeaconClaw.setPosition(robot.MID_SERVO - .6);
        } else if (gamepad2.left_bumper) {
            robot.leftBeaconClaw.setPosition(robot.MID_SERVO - .6);
        } else if (gamepad2.dpad_left) {
            robot.leftBeaconClaw.setPosition(robot.MID_SERVO + .6);
        }

        /*

     * Code to run ONCE after the driver hits STOP
     */
    }
}