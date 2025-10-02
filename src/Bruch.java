public class Bruch {
    int zaehler;
    int nenner;

    Bruch(int z, int n) {
        zaehler = z;
        nenner = n;
    }

    Bruch add(Bruch b) {
        return new Bruch(zaehler * b.nenner + b.zaehler * nenner, nenner * b.nenner);
    }

    Bruch sub(Bruch b) {
        return new Bruch(zaehler * b.nenner - b.zaehler * nenner, nenner * b.nenner);
    }

    Bruch mul(Bruch b) {
        return new Bruch(zaehler * b.zaehler, nenner * b.nenner);
    }

    Bruch div(Bruch b) {
        return new Bruch(zaehler * b.nenner, nenner * b.zaehler);
    }

    void kuerzen() {
        int a = zaehler;
        int b = nenner;
        while(b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        zaehler /= a;
        nenner /= a;
    }

    public String toString() {
        return zaehler + "/" + nenner;
    }
}
