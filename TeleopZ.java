package ftc8390.vv;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TeleopZ")  // @Autonomous(...) is the other common choice
//@Disabled
public class TeleopZ extends Teleop {

    @Override
    public void init() {
        super.init();
        turnDirection=-1;
    }

}
