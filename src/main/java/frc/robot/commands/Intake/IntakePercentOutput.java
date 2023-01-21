package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.Intake;

public class IntakePercentOutput extends CommandBase {
    private final Intake m_intake;

    

    public IntakePercentOutput(Intake intake) {
        m_intake = intake;
        addRequirements(intake);
    }

    @Override
    public void execute() {
        m_intake.setPercentOutput(.1);
    }

}
