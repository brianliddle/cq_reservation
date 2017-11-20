package com.campsite.business;

/**
 * ENUM used to identify key scenarios involving Reservations.
 * NOTE, it is intended to be used with:
 *   EnumMap<ReservationHashKey, Reservation>
 *     or
 *   EnumMap<ReservationHashKey, List<Reservation>>
 *
 *  NEXT - key for the next Reservation
 *  PRIOR - key for the previous Reservation
 *  CONFLICT - key to state there's a conflict with a reservation
 */
public enum ReservationHashKey {
    NEXT, PRIOR, CONFLICT
}
