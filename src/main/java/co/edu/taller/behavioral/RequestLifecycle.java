package co.edu.taller.behavioral;

public class RequestLifecycle {
    public enum Status {RECEIVED, IN_REVIEW, APPROVED, REJECTED}

    private Status status = Status.RECEIVED;

    public Status status() {
        return status;
    }

    public void review() { // TODO: transition RECEIVED to IN_REVIEW
        throw new UnsupportedOperationException("Implemente State");
    }

    public void approve() { // TODO: transition IN_REVIEW to APPROVED
        throw new UnsupportedOperationException("Implemente State");
    }

    public void reject() { // TODO: transition IN_REVIEW to REJECTED
        throw new UnsupportedOperationException("Implemente State");
    }
}