package pl.sda;

import java.time.LocalDate;

public class Ksiazka {

    private static Integer sekwencjaId = 0;

    private Integer id;
    private String tytul;
    private String autor;
    private StatusKsiazki status;
    private LocalDate wypozyczonaOd;
    private LocalDate wypozyczonaDo;
    private String wypozyczonaPrzez;

    public Ksiazka(String tytul, String autor) {
        sekwencjaId++;
        this.id = sekwencjaId;
        this.tytul = tytul;
        this.autor = autor;
        this.status = StatusKsiazki.DOSTEPNA;
    }

    public Ksiazka(Integer id, String tytul, String autor, String status) {
        sekwencjaId = id;
        this.id = id;
        this.tytul = tytul;
        this.autor = autor;
        this.status = StatusKsiazki.valueOf(status);
    }

    public Ksiazka(Integer id, String tytul, String autor, LocalDate wypozyczonaOd, LocalDate wypozyczonaDo, String wypozyczonaPrzez) {
        sekwencjaId = id;
        this.id = id;
        this.tytul = tytul;
        this.autor = autor;

        this.status = StatusKsiazki.WYPOZYCZONA;
        this.wypozyczonaOd = wypozyczonaOd;
        this.wypozyczonaDo = wypozyczonaDo;
        this.wypozyczonaPrzez = wypozyczonaPrzez;
    }

    public void wypozycz(LocalDate odKiedy, LocalDate doKiedy, String przezKogo) {
        this.wypozyczonaDo = doKiedy;
        this.wypozyczonaOd = odKiedy;
        this.wypozyczonaPrzez = przezKogo;
        this.status = StatusKsiazki.WYPOZYCZONA;
    }

    public void zwrocono() {
        this.wypozyczonaDo = null;
        this.wypozyczonaOd = null;
        this.wypozyczonaPrzez = null;
        this.status = StatusKsiazki.DOSTEPNA;
    }

    public void uszkodzona() {
        this.status = StatusKsiazki.USZKODZONA;
        System.out.println("Status zmieniony");
    }

    public String toString() {
        return this.id + " ; " + this.autor + " ; "
                + this.tytul + " ; " + this.status.name();
    }

    public int getId() {
        return id;
    }

    public String getAutor() {
        return autor;
    }

    public String sprawdzStatus() {
        if (this.status.equals(StatusKsiazki.DOSTEPNA)) {
            return "Dostepna";
        } else if (this.status.equals(StatusKsiazki.USZKODZONA)) {
            return "Uszkodzona";
        } else {
            return "Wypozyczona";
        }
    }

    public boolean czyWyporzyczona() {
        return this.status.equals(StatusKsiazki.WYPOZYCZONA);
    }

    public LocalDate getWypozyczonaDo() {
        return wypozyczonaDo;
    }

    public LocalDate getWypozyczonaOd() {
        return wypozyczonaOd;
    }

    public String getWypozyczonaPrzez() {
        return wypozyczonaPrzez;
    }
}
