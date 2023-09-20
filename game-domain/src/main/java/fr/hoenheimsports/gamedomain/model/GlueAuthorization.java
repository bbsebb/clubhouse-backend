package fr.hoenheimsports.gamedomain.model;
/**
 * Enumerates the authorization statuses regarding the use of glue/resin in a handball sports hall.
 * <p>
 * Handball players often use glue or resin to enhance their grip on the ball. This enumeration helps
 * sports halls specify their stance on the use of such substances.
 * </p>
 *
 * <h2>Available Values:</h2>
 * <ul>
 *   <li>{@link GlueAuthorization#AUTHORIZED} - The use of glue/resin is permitted in the sports hall.</li>
 *   <li>{@link GlueAuthorization#UNAUTHORIZED} - The use of glue/resin is not allowed in the sports hall.</li>
 *   <li>{@link GlueAuthorization#UNKNOWN} - The authorization status regarding the use of glue/resin is not specified or unknown.</li>
 * </ul>
 */
public enum GlueAuthorization {
    AUTHORIZED,
    UNAUTHORIZED,
    UNKNOWN
}
