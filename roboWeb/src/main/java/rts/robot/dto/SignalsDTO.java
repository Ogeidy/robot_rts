package rts.robot.dto;

public class SignalsDTO {
	private boolean fwdLeft;
	private boolean fwdRight;
	private boolean fwd;
	private boolean bckLeft;
	private boolean bckRight;
	private boolean bck;

	public SignalsDTO(byte signals) {
		update(signals);
	}

	public SignalsDTO update(byte signals) {
		byte check = 1;
		if ((signals & check) == check) {
			bck = true;
		} else {
			bck = false;
		}
		check = 2;
		if ((signals & check) == check) {
			fwd = true;
		} else {
			fwd = false;
		}
		check = 4;
		if ((signals & check) == check) {
			bckRight = true;
		} else {
			bckRight = false;
		}
		check = 8;
		if ((signals & check) == check) {
			bckLeft = true;
		} else {
			bckLeft = false;
		}
		check = 16;
		if ((signals & check) == check) {
			fwdRight = true;
		} else {
			fwdRight = false;
		}
		check = 32;
		if ((signals & check) == check) {
			fwdLeft = true;
		} else {
			fwdLeft = false;
		}
		return this;
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
