package co.edu.taller.behavioral;

public class RequestLifecycle {
    public enum Status {RECEIVED, IN_REVIEW, APPROVED, REJECTED}

    private State state = new Received();

    public Status status() {
        return state.status();
    }

    public void review() {
        state = state.review();
    }

    public void approve() {
        state = state.approve();
    }

    public void reject() {
        state = state.reject();
    }

    /**
     * State: cada estado decide qué transiciones permite.
     * Por defecto toda transición es inválida; cada estado concreto
     * sobrescribe únicamente las que le corresponden.
     */
    private interface State {
        Status status();

        default State review() {
            throw invalid("pasar a revisión");
        }

        default State approve() {
            throw invalid("aprobar");
        }

        default State reject() {
            throw invalid("rechazar");
        }

        private IllegalStateException invalid(String action) {
            return new IllegalStateException("Transición inválida: no se puede " + action + " desde " + status());
        }
    }

    private static final class Received implements State {
        @Override
        public Status status() {
            return Status.RECEIVED;
        }

        @Override
        public State review() {
            return new InReview();
        }
    }

    private static final class InReview implements State {
        @Override
        public Status status() {
            return Status.IN_REVIEW;
        }

        @Override
        public State approve() {
            return new Approved();
        }

        @Override
        public State reject() {
            return new Rejected();
        }
    }

    /** Estado final: no admite más transiciones. */
    private static final class Approved implements State {
        @Override
        public Status status() {
            return Status.APPROVED;
        }
    }

    /** Estado final: no admite más transiciones. */
    private static final class Rejected implements State {
        @Override
        public Status status() {
            return Status.REJECTED;
        }
    }
}
