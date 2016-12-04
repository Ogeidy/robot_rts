package rts.robot.dto;


import org.apache.log4j.Logger;

public class SignalsDTO {
	Logger LOGGER = Logger.getLogger(SignalsDTO.class);

	private boolean fwdLeft;
	private boolean fwdRight;
	private boolean fwd;
	private boolean bckLeft;
	private boolean bckRight;
	private boolean bck;

	public SignalsDTO() {
		fwdLeft = false;
		fwdRight = false;
		fwd = false;
		bckLeft = false;
		bckRight = false;
		bck = false;
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
		LOGGER.info("bck:" + bck);
		LOGGER.info("fwd:" + fwd);
		LOGGER.info("bckRight:" + bckRight);
		LOGGER.info("bckLeft:" + bckLeft);
		LOGGER.info("fwdRight:" + fwdRight);
		LOGGER.info("fwdLeft:" + fwdLeft);
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
