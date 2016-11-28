package rts.robot.dto;

public class SignalsDTO {
	private final boolean fwdLeft;
	private final boolean fwdRight;
	private final boolean fwd;
	private final boolean bckLeft;
	private final boolean bckRight;
	private final boolean bck;

	public SignalsDTO(byte signal) {
		byte check = 1;
		if ((signal & check) == check) {
			bck = true;
		} else {
			bck = false;
		}
		check = 2;
		if ((signal & check) == check) {
			fwd = true;
		} else {
			fwd = false;
		}
		check = 4;
		if ((signal & check) == check) {
			bckRight = true;
		} else {
			bckRight = false;
		}
		check = 8;
		if ((signal & check) == check) {
			bckLeft = true;
		} else {
			bckLeft = false;
		}
		check = 16;
		if ((signal & check) == check) {
			fwdRight = true;
		} else {
			fwdRight = false;
		}
		check = 32;
		if ((signal & check) == check) {
			fwdLeft = true;
		} else {
			fwdLeft = false;
		}
	}

	public boolean isFwdLeft() {
		return fwdLeft;
	}

	public boolean isFwdRight() {
		return fwdRight;
	}

	public boolean isFwd() {
		return fwd;
	}

	public boolean isBckLeft() {
		return bckLeft;
	}

	public boolean isBckRight() {
		return bckRight;
	}

	public boolean isBck() {
		return bck;
	}
}
