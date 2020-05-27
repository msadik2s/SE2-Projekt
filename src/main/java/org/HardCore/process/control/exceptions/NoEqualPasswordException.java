package org.HardCore.process.control.exceptions;

public class NoEqualPasswordException extends Exception{
    private String reason = null;


    public NoEqualPasswordException( String reason ) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
