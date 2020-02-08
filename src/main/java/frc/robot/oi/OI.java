package frc.robot.oi;

import com.torontocodingcollective.oi.TButton;
import com.torontocodingcollective.oi.TGameController;
import com.torontocodingcollective.oi.TGameController_Logitech;
import com.torontocodingcollective.oi.TOi;
import com.torontocodingcollective.oi.TRumbleManager;
import com.torontocodingcollective.oi.TStick;
import com.torontocodingcollective.oi.TStickPosition;
import com.torontocodingcollective.oi.TToggle;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.HoodPosition;
import frc.robot.RobotConst;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

/**
 * Driver Controller (inherited from TOi)
 *
 * Sticks: Right Stick = Drive Stick Left Stick = Drive Stick Right Stick Press
 * = Toggle PIDs Left Stick Press = Toggle Compressor
 *
 * Buttons: Start Button = Reset Encoders and Gyro Back Button = Cancel any
 * Command
 *
 * Bumpers/Triggers: Left Bumper = Turbo shift
 *
 * POV: Any Angle = Rotate to the Pressed Angle
 *
 */
public class OI extends TOi {

	private TGameController driverController = new TGameController_Logitech(0);
	private TRumbleManager  driverRumble     = new TRumbleManager("Driver", driverController);

	private TToggle         compressorToggle = new TToggle(driverController, TStick.LEFT);
	private TToggle         speedPidToggle   = new TToggle(driverController, TStick.RIGHT);

	private DriveSelector   driveSelector    = new DriveSelector();

	private HoodPosition	previousHoodPosition = HoodPosition.CLOSE;
	private double		    shooterSpeed = 0;

	private double          carouselSpeed = 0;
	private double          towerSpeed = 0;

	@Override
	public boolean getCancelCommand() {
		return driverController.getButton(TButton.BACK);
	}

	public boolean getCompressorEnabled() {
		return compressorToggle.get();
	}

	@Override
	public TStickPosition getDriveStickPosition(TStick stick) {
		return driverController.getStickPosition(stick);
	}

	@Override
	public boolean getReset() {
		return driverController.getButton(TButton.START);
	}

	@Override
	public int getRotateToHeading() {
		return driverController.getPOV();
	}

	/**
	 * Get the selected drive type
	 *
	 * @return {@link DriveControlType} selected on the SmartDashboard. The default
	 *         drive type is {@link DriveControlType#ARCADE}
	 */
	public DriveControlType getSelectedDriveType() {
		return driveSelector.getDriveControlType();
	}

	/**
	 * Get the selected single stick side
	 *
	 * @return {@link TStick} selected on the SmartDashboard. The default single
	 *         stick drive is {@link TStick#RIGHT}
	 */
	public TStick getSelectedSingleStickSide() {
		return driveSelector.getSingleStickSide();
	}

	@Override
	public boolean getSpeedPidEnabled() {
		return speedPidToggle.get();
	}

	public boolean getTurboOn() {
		return driverController.getButton(TButton.LEFT_BUMPER);
	}

	public void init() {
		compressorToggle.set(true);
		speedPidToggle.set(false);
	}

	public void setSpeedPidEnabled(boolean state) {
		speedPidToggle.set(state);
	}

	/*
	 * Shooter Subsystem commands
	 */

	public HoodPosition getHoodPosition() {

		//	        if (driverController.getButton(TButton.A)) {
		//	            previousHoodPosition = HoodPosition.CLOSE;
		//	        }
		//	        if(driverController.getButton(TButton.B)) {
		//	            previousHoodPosition = HoodPosition.MEDIUM;
		//	        }
		//	        if(driverController.getButton(TButton.Y)) {
		//	            previousHoodPosition = HoodPosition.FAR;
		//	        }
		return previousHoodPosition;


	}

	/**public double getShooterSpeed() {


		if (driverController.getButton(TButton.LEFT_BUMPER)) {
			shooterSpeed = shooterSpeed - 0.005;
			if (shooterSpeed<0)
				shooterSpeed = 0;
		}
		if(driverController.getButton(TButton.RIGHT_BUMPER)) {
			shooterSpeed = shooterSpeed + 0.005;
			if (shooterSpeed>1)
				shooterSpeed = 1;
		}
		return shooterSpeed;
	}**/
	
	public boolean runShooterBB() {
	    if (driverController.getButton(TButton.A)) {
	        shooterSpeed = RobotConst.SHOOTER_BANGBANG_SPEED;
	        return true;
	    }
	    return false;
	}

	public boolean runGroundIntake() {
		/**if(driverController.getButton(TButton.X)) {
			return true;

		}**/
		return false;
	}

	public boolean runFeederIntake() {
		/**if(driverController.getButton(TButton.A)) {

			return true;
		}**/
		return false;
	}


	public boolean stopIntake() {
		/**if (driverController.getButton(TButton.Y)) {
			return true;
		}**/
		return false;
	}


	/*
	 * Carousel Subsystem commands
	 */

	//public double setCarouselSpeed() {


	/**if (driverController.getPOV() == 0) {
			carouselSpeed =  0.25;

		}
		if(driverController.getPOV() == 180) {
			carouselSpeed = 0.5;
		}

		if(driverController.getPOV() == -1) {
			carouselSpeed = 0;
		}

		return carouselSpeed;
	}**/
	public boolean runIntakeCarousel() {
		if(driverController.getPOV() == 0) {
			return true;
		}
		return false;
	}

	public boolean runShooterCarousel() {
		if(driverController.getPOV() == 180) {
			return true;
		}
		return false;
	}
    public boolean stopRunCarousel() {
        if(driverController.getPOV() == 270) {
       return true; 
        }
        return false;
    }



	/*
	 * Tower Subsystem commands
	 */

	/**public double setTowerSpeed() {


		if (driverController.getPOV() == 270) {
			towerSpeed =  0.25;
		}
		if(driverController.getPOV() == -1) {
			towerSpeed = 0;
		}

		return towerSpeed;
	}


	public boolean runIntakeTower() {
		return true;
	**/
	public boolean runIntakeTower() {
	   /** if(driverController.getButton(TButton.A)) {
	   return true; 
	    }**/
	    return false;
	}
	
	   public boolean runShooterTower() {
	        if(driverController.getButton(TButton.B)) {
	       return true; 
	        }
	        return false;
	    }
	   
       public boolean stopRunTower() {
           if(driverController.getButton(TButton.X)) {
          return true; 
           }
           return false;
       }

	@Override
	public void updatePeriodic() {

		// Update all Toggles
		compressorToggle.updatePeriodic();
		speedPidToggle.updatePeriodic();
		driverRumble.updatePeriodic();

		// Update all SmartDashboard values
		SmartDashboard.putBoolean("Speed PID Toggle", getSpeedPidEnabled());
		SmartDashboard.putBoolean("Compressor Toggle", getCompressorEnabled());
		SmartDashboard.putString("Driver Controller", driverController.toString());
	}



}
