package frc.robot.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.twilight.swerve.commands.GoToCommand;
import frc.twilight.swerve.subsystems.Swerve;
import frc.twilight.swerve.vectors.Position;

public class TestPath extends SequentialCommandGroup {
    public TestPath(Swerve swerve) {
        addCommands(
            new GoToCommand(swerve, new Position(0, 0, 0)),
            new GoToCommand(swerve, new Position(1, 0, 0)),
            new GoToCommand(swerve, new Position(1, 1, 0)),
            new GoToCommand(swerve, new Position(0, 1, 0)),
            new GoToCommand(swerve, new Position(0, 0, 0))
        );
    }
}
