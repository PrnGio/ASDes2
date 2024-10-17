package it.unicam.cs.asdl2425.es2;

/**
 * Un oggetto cassaforte con combinazione ha una manopola che può essere
 * impostata su certe posizioni contrassegnate da lettere maiuscole. La
 * serratura si apre solo se le ultime tre lettere impostate sono uguali alla
 * combinazione segreta.
 * 
 * @author Luca Tesei
 */
public class CombinationLock {
    private String combinazione;
    private boolean aperta;
    private char[] posizione=new char[3];
    private int count;

    /**
     * Costruisce una cassaforte <b>aperta</b> con una data combinazione
     * 
     * @param aCombination
     *                         la combinazione che deve essere una stringa di 3
     *                         lettere maiuscole dell'alfabeto inglese
     * @throw IllegalArgumentException se la combinazione fornita non è una
     *        stringa di 3 lettere maiuscole dell'alfabeto inglese
     * @throw NullPointerException se la combinazione fornita è nulla
     */
    public CombinationLock(String aCombination) {
        if(!aCombination.matches("[A-Z]{3}")){
            throw new IllegalArgumentException("La stringa non è composta da 3 lettere maiuscole");
        }
        else if(aCombination==null)
        {
            throw new NullPointerException("La combinazione è nulla");
        }
        this.combinazione=aCombination;
        this.aperta = true;//inizialmente aperta
    }

    /**
     * Imposta la manopola su una certaposizione.
     * 
     * @param aPosition
     *                      un carattere lettera maiuscola su cui viene
     *                      impostata la manopola
     * @throws IllegalArgumentException
     *                                      se il carattere fornito non è una
     *                                      lettera maiuscola dell'alfabeto
     *                                      inglese
     */
    public void setPosition(char aPosition) {
        if (!Character.isUpperCase(aPosition)) {
            throw new IllegalArgumentException("Il carattere non è maiuscolo");
        }
        posizione[count % 3]=aPosition;
        count ++;
    }

    /**
     * Tenta di aprire la serratura considerando come combinazione fornita le
     * ultime tre posizioni impostate. Se l'apertura non va a buon fine le
     * lettere impostate precedentemente non devono essere considerate per i
     * prossimi tentativi di apertura.
     */
    public void open() {

        if (count < 3) {
            aperta = false;
        }
        else{
            StringBuilder sb = new StringBuilder();
            for (char c : posizione) {
                sb.append(c);
            }
            if (sb.toString().equals(combinazione)) {
                aperta = true;
            } else {
                // Resetta le posizioni in caso di fallimento
                posizione = new char[3];
                count = 0;
                aperta = false;
            }
        }
    }
    /**
     * Determina se la cassaforte è aperta.
     * 
     * @return true se la cassaforte è attualmente aperta, false altrimenti
     */
    public boolean isOpen() {
        return aperta;
    }

    /**
     * Chiude la cassaforte senza modificare la combinazione attuale. Fa in modo
     * che se si prova a riaprire subito senza impostare nessuna nuova posizione
     * della manopola la cassaforte non si apre. Si noti che se la cassaforte
     * era stata aperta con la combinazione giusta le ultime posizioni impostate
     * sono proprio la combinazione attuale.
     */
    public void lock() {
        aperta = false;
        // Resetta il contatore, ma mantiene le ultime posizioni impostate
        count=0;

    }

    /**
     * Chiude la cassaforte e modifica la combinazione. Funziona solo se la
     * cassaforte è attualmente aperta. Se la cassaforte è attualmente chiusa
     * rimane chiusa e la combinazione non viene cambiata, ma in questo caso le
     * le lettere impostate precedentemente non devono essere considerate per i
     * prossimi tentativi di apertura.
     * 
     * @param aCombination
     *                         la nuova combinazione che deve essere una stringa
     *                         di 3 lettere maiuscole dell'alfabeto inglese
     * @throw IllegalArgumentException se la combinazione fornita non è una
     *        stringa di 3 lettere maiuscole dell'alfabeto inglese
     * @throw NullPointerException se la combinazione fornita è nulla
     */
    public void lockAndChangeCombination(String aCombination) {
        if (aCombination == null) {
            throw new NullPointerException("La combinazione non può essere nulla");
        }
        if (!aCombination.matches("[A-Z]{3}")) {
            throw new IllegalArgumentException("La combinazione deve essere una stringa di 3 lettere maiuscole dell'alfabeto inglese");
        }
        if (aperta) {
            combinazione = aCombination;
            aperta = false;
        } else {
            // Resetta le posizioni in caso di fallimento
            posizione = new char[3];
            count = 0;

        }
    }
}