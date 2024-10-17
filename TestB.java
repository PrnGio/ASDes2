import it.unicam.cs.asdl2425.es2.Burglar;
import it.unicam.cs.asdl2425.es2.CombinationLock;

public class TestB {
    public static void main(String[] args) {
        try {
            new Burglar(null);
        } catch (NullPointerException e) {
            System.out.println("Null pointer funzionante");
        }
        Burglar b1 = new Burglar(new CombinationLock("AAA"));
        System.out.println(b1.toString());

        // altri test

        CombinationLock cl = new CombinationLock("XHS");
        cl.lock();
        Burglar b= new Burglar(cl);
        String comb = b.findCombination();
        System.out.println("Atteso: true" + " Ottenuto: " + comb.equals("XHS"));
        
        System.out.println("Atteso: true" + " Ottenuto: " + (b.getAttempts()>0));
    }
}
