package turing.example.labresult.entity;

/**
 * Enum representing the possible statuses of a lab result.
 */
public enum ResultStatus {

    /** Indicates the lab result is pending and has not yet been processed. */
    PENDING,

    /** Indicates the lab test has been completed. */
    COMPLETED,

    /** Indicates the lab result has been reviewed and approved. */
    APPROVED,

    /** Indicates the lab result is currently being processed. */
    PROCESSING,

    /** Indicates the lab result was rejected. */
    REJECTED
}
