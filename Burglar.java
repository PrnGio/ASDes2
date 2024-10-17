package it.unicam.cs.asdl2425.es2;

/**
 * Uno scassinatore è un oggetto che prende una certa cassaforte e trova la
 * combinazione utilizzando la "forza bruta".
 * 
 * @author Luca Tesei
 *
 */
public class Burglar {

    private CombinationLock lock;
    private long attempts;
    private boolean foundCombination;

    /**
     * Costruisce uno scassinatore per una certa cassaforte.
     * 
     * @param aCombinationLock
     * @throw NullPointerException se la cassaforte passata è nulla
     */
    public Burglar(CombinationLock aCombinationLock) {
        if (aCombinationLock == null) {
            throw new NullPointerException("La cassaforte non può essere nulla");
        }
        this.lock = aCombinationLock;
        this.attempts = 0;
        this.foundCombination = false;
    }

    /**
     * Forza la cassaforte e restituisce la combinazione.
     * 
     * @return la combinazione della cassaforte forzata.
     */
    public String findCombination() {
        {
            char[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
            StringBuilder attempt = new StringBuilder();
            for (char first : letters) {
                for (char second : letters) {
                    for (char third : letters) {
                        attempt.setLength(0);
                        attempt.append(first).append(second).append(third);
                        lock.setPosition(first);
                        lock.setPosition(second);
                        lock.setPosition(third);
                        attempts++;
                        lock.open();
                        if (lock.isOpen()) {
                            foundCombination = true;
                            return attempt.toString();
                        }
                    }
                }
            }
            return null;
        }
    }

    /**
     * Restituisce il numero di tentativi che ci sono voluti per trovare la
     * combinazione. Se la cassaforte non è stata ancora forzata restituisce -1.
     * 
     * @return il numero di tentativi che ci sono voluti per trovare la
     *         combinazione, oppure -1 se la cassaforte non è stata ancora
     *         forzata.
     */
    public long getAttempts() {
            return foundCombination ? attempts : -1;
    }
}
