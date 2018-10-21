package pl.sda;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.Period;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class Ksiegozbior {

    Path plik = Paths.get("C:/Users/mateu/IdeaProjects/sprawdzenieWiedzy/src/pl/sda/ksiazki.txt");
    Charset kodowanie = Charset.forName("utf-8");

    private Set<Ksiazka> ksiazki = new LinkedHashSet<>();


    public void dodajKsiazke(String tytul, String autor) {
        this.ksiazki.add(new Ksiazka(tytul.trim(), autor.trim()));
        System.out.println("Dodano książkę");
    }

    public void wyswieltPoId(int id) {
        for (Ksiazka ksiazka : ksiazki) {
            if (ksiazka.getId() == id) {
                System.out.println(ksiazka);
                return;
            }
        }
    }

    public void zapiszKsiazki() {
        try (BufferedWriter writer = Files.newBufferedWriter(plik, kodowanie, StandardOpenOption.CREATE)) {


            for (Ksiazka ksiazka : ksiazki) {
                if (ksiazka.czyWyporzyczona()) {
                    writer.write(ksiazka.toString() + ";" + ksiazka.getWypozyczonaOd() + ";" + ksiazka.getWypozyczonaDo() + ";" + ksiazka.getWypozyczonaPrzez() + "\n");
                } else {
                    writer.write(ksiazka.toString() + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void wczytajKsiazki() {

        try (BufferedReader reader = Files.newBufferedReader(plik, kodowanie)) {
            while (true) {
                String linia = reader.readLine();
                if (linia == null) {
                    break;
                }
                String[] czesciLini = linia.split(";");

                if (czesciLini[3].trim().equals("WYPOZYCZONA")) {
                    this.ksiazki.add(new Ksiazka(Integer.valueOf(czesciLini[0].trim()), czesciLini[1].trim(), czesciLini[2].trim(), LocalDate.parse(czesciLini[4]), LocalDate.parse(czesciLini[5]), czesciLini[6]));
                } else {
                    this.ksiazki.add(new Ksiazka(Integer.valueOf(czesciLini[0].trim()), czesciLini[1].trim(), czesciLini[2].trim(), czesciLini[3].trim()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void wyswietlPoAutorze(String autor) {
        for (Ksiazka ksiazka : ksiazki) {
            if (ksiazka.getAutor().toLowerCase().equals(autor)) {
                System.out.println(ksiazka);
            }
        }
    }

    public void wyswietlWszystko() {
        for (Ksiazka ksiazka : ksiazki) {
            System.out.println(ksiazka.toString());
        }
    }

    public void wypozyczKsiazke(int id, LocalDate odKiedy, LocalDate doKiedy, String przezKogo) {
        for (Ksiazka ksiazka : ksiazki) {
            if (ksiazka.getId() == id) {
                if ("Dostepna".equals(ksiazka.sprawdzStatus())) {
                    ksiazka.wypozycz(odKiedy, doKiedy, przezKogo);
                    System.out.println("Ksiazka wypozyczona");
                } else {
                    System.out.println("Nie mozna wypozyczyc WYPOYCZONEJ lub USZKODZONEJ ksiazki");
                }
            }
        }
    }

    public void oddajKsiazke(int id) {
        for (Ksiazka ksiazka : ksiazki) {
            if (ksiazka.getId() == id) {
                ksiazka.zwrocono();
                System.out.println("Ksiazka zostala zwrucona");
            }
        }
    }

    public void usun(int id) {
        Iterator<Ksiazka> iterator = ksiazki.iterator();
        while (iterator.hasNext()) {
            Ksiazka ksiazka = iterator.next();
            if (ksiazka.getId() == id) {
                if (ksiazka.czyWyporzyczona()) {
                    System.out.println("Nie mozna usunac wypozyczonej ksiazki");
                } else {
                    iterator.remove();
                    System.out.println("ksiazka usunieta");
                }
            }
        }
    }

    public void uszkodzona(int id) {
        for (Ksiazka ksiazka : ksiazki) {
            if (ksiazka.getId() == id) {
                ksiazka.uszkodzona();
            }
        }
    }

    public void doKiedyWypozyczona(int id) {
        for (Ksiazka ksiazka : ksiazki) {
            if (ksiazka.getId() == id) {
                if ("Wypozyczona".equals(ksiazka.sprawdzStatus())) {
                    System.out.println(ksiazka.getWypozyczonaDo());
                } else {
                    System.out.println("Ksiazka nie jest wypozyczona");
                }
            }
        }

    }

    public void ileDniDoKonca(int id) {
        for (Ksiazka ksiazka : ksiazki) {
            if (ksiazka.getId() == id) {
                if (ksiazka.czyWyporzyczona()) {
                    LocalDate dzis = LocalDate.now();
                    LocalDate doKiedy = ksiazka.getWypozyczonaDo();
                    Period ileDni = Period.between(dzis, doKiedy);
                    System.out.println(ileDni);
                } else {
                    System.out.println("Ksiazka nie jest wyporzyczona");
                }
            }
        }
    }

    public void wyswietlKomendy() {
        System.out.println("Dodaj ksiazke -                 D ; <TYTUL> ; <AUTOR>");
        System.out.println("Zamknij program -               Z ; <ID>");
        System.out.println("Wyswietl z podanym ID -         WID ; <ID>");
        System.out.println("Oddaj Ksiazke -                 ODD;");
        System.out.println("Wyswietl dla autora -           WAUT ; <AUTOR>");
        System.out.println("Wypożycz kosiazke -             WYP ; <ID> ; <OD KIEDY> ; <DO KIEDY> ; <PRZEZ KOGO>");
        System.out.println("Oznacz jako uszkodzona -        USZ ; <ID>");
        System.out.println("Usun ksiazke -                  U ; <ID>");
        System.out.println("Do kiedy wypozyczona -          KIEDY ; <ID>");
        System.out.println("Ile dni zostalo do zwrotu -     ILE ; <ID>");
        System.out.println("Wyswietl komendy =              K");
        System.out.println("Zapisz spis ksiazek do pliku -  ZAPISZ");
        System.out.println();
        System.out.println("Daty podawaj w formacie         yyyy-MM-dd");
    }

}


