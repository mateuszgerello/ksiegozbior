package pl.sda;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Ksiegozbior ksiegozbior = new Ksiegozbior();

        ksiegozbior.wczytajKsiazki();
        ksiegozbior.wyswietlKomendy();

        while (true) {
            System.out.println();
            System.out.println("podaj komende");
            String komenda = scanner.nextLine();

            if ("Z".equals(komenda.toUpperCase())) {
                break;
            }
            String[] czesciKomendy = komenda.split(";");


            try {
                switch (czesciKomendy[0].toUpperCase()) {
                    case "D":
                        ksiegozbior.dodajKsiazke(czesciKomendy[1], czesciKomendy[2]);
                        break;
                    case "W":
                        ksiegozbior.wyswietlWszystko();
                        break;
                    case "WID":
                        ksiegozbior.wyswieltPoId(Integer.valueOf(czesciKomendy[1]));
                        break;
                    case "WAUT":
                        ksiegozbior.wyswietlPoAutorze(czesciKomendy[1]);
                        break;
                    case "WYP":
                        ksiegozbior.wypozyczKsiazke(
                                Integer.valueOf(czesciKomendy[1]),
                                LocalDate.parse(czesciKomendy[2]),
                                LocalDate.parse(czesciKomendy[3]),
                                czesciKomendy[4]);
                        break;
                    case "ODD":
                        ksiegozbior.oddajKsiazke(Integer.valueOf((czesciKomendy[1])));
                        break;
                    case "U":
                        ksiegozbior.usun(Integer.valueOf(czesciKomendy[1]));
                        break;
                    case "USZ":
                        ksiegozbior.uszkodzona(Integer.valueOf(czesciKomendy[1]));
                        break;
                    case "KIEDY":
                        ksiegozbior.doKiedyWypozyczona(Integer.valueOf(czesciKomendy[1]));
                        break;
                    case "ILE":
                        ksiegozbior.ileDniDoKonca(Integer.valueOf(czesciKomendy[1]));
                        break;
                    case "K":
                        ksiegozbior.wyswietlKomendy();
                        break;
                    case "ZAPISZ":
                        ksiegozbior.zapiszKsiazki();
                        break;
                    default:
                        System.out.println("Bledna komenda");

                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("Podałes zla ilosc parametrow");
            } catch (java.time.format.DateTimeParseException ex) {
                System.out.println("Podada data ma zly format");
            }
        }
    }
}

