// ID: 316482355

package interfaces;

/**
 * HitNotifier - when hit occurs, notify to HitListeners.
 */
public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     * @param hl - a interfaces.HitListener object.
     */
    void addHitListener(HitListener hl);
    // Remove hl from the list of listeners to hit events.
    /**
     * remove hl from list of listeners to hit events.
     * @param hl - a interfaces.HitListener object.
     */
    void removeHitListener(HitListener hl);
}
