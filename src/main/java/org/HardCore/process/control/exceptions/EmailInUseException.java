package org.HardCore.process.control.exceptions;

public class EmailInUseException extends Throwable {
    private String reason = null;


    public EmailInUseException( String reason ) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
