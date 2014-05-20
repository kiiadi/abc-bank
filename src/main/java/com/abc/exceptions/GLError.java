package com.abc.exceptions;

public class GLError extends Exception {

	private static final long serialVersionUID = 2654896159957612366L;

	private final int m_errorCode;

	public GLError(int errorCode) {
		m_errorCode = errorCode;
	}

	public int getErrorCode() {
		return m_errorCode;
	}

}
