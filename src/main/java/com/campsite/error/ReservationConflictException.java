package com.campsite.error;

/**
 * Thrown when attempting to create / modify reservations but a conflicting
 * Reservation(s) prevent this from happening.
 */
public class ReservationConflictException extends Exception {
}
