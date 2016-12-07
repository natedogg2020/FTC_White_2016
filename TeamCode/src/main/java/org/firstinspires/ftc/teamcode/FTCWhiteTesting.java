package org.firstinspires.ftc.teamcode;

/**
 * Created by natenckelvey on 10/14/16.
 */

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
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
        double          clawOffset      = 0;                       // Servo mid position
        final double    CLAW_SPEED      = 0.02 ;

        //Ch1 = Right joystick X-axis
        //Ch2 = Right joystick y- axis(unused)
        //Ch3 = Left joystick Y-axis
        //Ch4 = Left joystick X-axis
        ch1 = (gamepad1.right_trigger - gamepad1.left_trigger);
        ch2 = -gamepad1.right_stick_y;
        ch3 = -gamepad1.left_stick_y;
        ch4 = -gamepad1.left_stick_x;

        robot.frontLeftMotor.setPower(ch3 + ch1 + ch4);
        robot.rearLeftMotor.setPower(ch3 + ch1 - ch4);
        robot.rearRightMotor.setPower(ch3 - ch1 - ch4);
        robot.frontRightMotor.setPower(ch3 - ch1 + ch4);

        clawOffset = Range.clip(clawOffset, -0.5, 0.5);
        if(gamepad1.a) {
            robot.leftClaw.setPosition(robot.MID_SERVO + 1.5);
            robot.rightCLaw.setPosition(robot.MID_SERVO - 1.5);
        }
        else if(gamepad1.b) {
            robot.leftClaw.setPosition(robot.MID_SERVO - 1.5);
            robot.rightCLaw.setPosition(robot.MID_SERVO + 1.5);
        }
        


            /* @Override
    public void runOpMode() {
        double left;
        double right;
        double max;

        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         *
            robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Run wheels in POV mode (note: The joystick goes negative when pushed forwards, so negate it)
            // In this mode the Left stick moves the robot fwd and back, the Right stick turns left and right.
            left  = -gamepad1.left_stick_y + gamepad1.right_stick_x;
            right = -gamepad1.left_stick_y - gamepad1.right_stick_x;

            // Normalize the values so neither exceed +/- 1.0
            max = Math.max(Math.abs(left), Math.abs(right));
            if (max > 1.0)
            {
                left /= max;
                right /= max;
            }

            robot.leftMotor.setPower(left);
            robot.rightMotor.setPower(right);

            // Use gamepad left & right Bumpers to open and close the claw
            if (gamepad1.right_bumper)
                clawOffset += CLAW_SPEED;
            else if (gamepad1.left_bumper)
                clawOffset -= CLAW_SPEED;

            // Move both servos to new position.  Assume servos are mirror image of each other.
            clawOffset = Range.clip(clawOffset, -0.5, 0.5);
            robot.leftClaw.setPosition(robot.MID_SERVO + clawOffset);
            robot.rightClaw.setPosition(robot.MID_SERVO - clawOffset);

            // Use gamepad buttons to move arm up (Y) and down (A)
            if (gamepad1.y)
                robot.armMotor.setPower(robot.ARM_UP_POWER);
            else if (gamepad1.a)
                robot.armMotor.setPower(robot.ARM_DOWN_POWER);
            else
                robot.armMotor.setPower(0.0);

            // Send telemetry message to signify robot running;
            telemetry.addData("claw",  "Offset = %.2f", clawOffset);
            telemetry.addData("left",  "%.2f", left);
            telemetry.addData("right", "%.2f", right);
            telemetry.update();

            // Pause for metronome tick.  40 mS each cycle = update 25 times a second.
            robot.waitForTick(40);
            */
       /* ch1 = gamepad1.right_stick_x;
        ch2 = -gamepad1.right_stick_y;
        ch3 = -gamepad1.left_stick_y;
        ch4 = gamepad1.left_stick_x;
        robot.frontLeftMotor.setPower(ch4);
        robot.rearLeftMotor.setPower(ch4);
        robot.frontRightMotor.setPower(ch4);
        robot.rearRightMotor.setPower(ch4);
        telemetry.addData("ch4value", ch4);
        */

        /*
        Old Code -- Need to hang on to just in case ;)
        double left;
        double right;

        // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
        left = -gamepad1.left_stick_y;
        right = -gamepad1.right_stick_y;
        robot.leftMotor.setPower(left);
        robot.rightMotor.setPower(right);
        robot.lleftMotor.setPower(left);
        robot.rrightMotor.setPower(right);*/

    /*
     * Code to run ONCE after the driver hits STOP
     */
    }
}