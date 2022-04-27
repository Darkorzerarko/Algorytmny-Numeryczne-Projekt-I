import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

class PROJEKT {
    //Funkcja znajdująca wynik działania
    static double wynik(int i, double value, double[] x) {
        double pro = 1;
        for (int j = 0; j < i; j++) {
            pro = pro * (value - x[j]);
            //( "X"  - x_j )
            //tj. (x-x1) w następnym wywołaniu (x-x1)(x-x2) itd
        }
        return pro;
    }

    //Funkcja wyznaczająca ilorazy różnicowe do tablicy 2D
    static void wyliczIlorazy(double[] x, double[][] y, int n) {
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                y[j][i] = (y[j][i - 1] - y[j + 1][i - 1]) / (x[j] - x[i + j]);
                //b_i     = ( f[x_i-1]   -      f[x_i]    ) / (x_i-1-   x_i   )
            }
        }
    }

    //Funkcja do obliczania wartości wielomianu dla podanego "x" za pomocą metody Newtona
    static double wielNewtona(double value, double[] x, double[][] y, int n) {
        double sum = y[0][0]; // = b_0

        for (int i = 1; i < n; i++) {
            sum = sum + (y[0][i] * wynik(i, value, x));
            //sum = sum + (  b_i   * ( "X"  - x_j ) w pętli)
        }
        return sum;
    }

    //Funkcja drukująca tabelę ilorazów różnicowych
    static void drukujTablice(double[][] y, int n, String[] indeksy, double[] x) {
        System.out.println("Tabela wyliczonych ilorazów róźnicowych:");
        System.out.println("Kolorem niebieskim oznaczono ilorazy różnicowe potrzebne do wyprowadzienia wielomianu w postaci Newtona");

        System.out.print("x\u1D62   ");
        String s1 = "f[x\u1D62]";
        System.out.printf("%15s", s1);

        String s2 = "f[x\u1D62,x\u1D62+\u2081]";
        System.out.printf("%15s", s2);
        StringBuilder sb = new StringBuilder("f[x\u1D62,...,x\u1D62+");

        for (int i = 2; i < n; i++) {
            System.out.printf("%13s%s]", sb, indeksy[i]);
        }
        System.out.printf("\n");

        String s = "x";
        for (int i = 0; i < n; i++) {
            System.out.printf("%s%s=%2.0f", s, indeksy[i], x[i]);
            for (int j = 0; j < n - i; j++) {
                if (i == 0) {
                    System.out.printf("%s%15f%s", "\u001B[96m", y[i][j], "\u001B[0m");
                } else {
                    System.out.printf("%15f", y[i][j]);
                }
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        //Kody Unicode do wyświetlania dolnych indeksów
        String[] indeksy = {"\u2080", "\u2081", "\u2082", "\u2083", "\u2084", "\u2085", "\u2086", "\u2087", "\u2088", "\u2089", "\u2081\u2080"};
        //Pierszy przedział
        int input_count = 3;
        double value_x_a = 0.5;
        double value_x_b = 1.5;
        double[][] y = new double[input_count][input_count];
        double[] x = {-1, 0, 1};
        y[0][0] = 0.5;
        y[1][0] = 1;
        y[2][0] = 2;

        //Drugi przedział
        int input_count2 = 3;
        double value_x2_a = 0.5;
        double value_x2_b = 1.5;
        double[][] y2 = new double[input_count2][input_count2];
        double[] x2 = {1, 2, 3};
        y2[0][0] = 2;
        y2[1][0] = 4;
        y2[2][0] = 8;

        //Trzeci przedział
        int input_count3 = 5;
        double value_x3_a = 0.5;
        double value_x3_b = 1.5;
        double[][] y3 = new double[input_count3][input_count3];
        double[] x3 = {-2, 0, 1, 2, 4};
        y3[0][0] = 0.25;
        y3[1][0] = 1;
        y3[2][0] = 2;
        y3[3][0] = 4;
        y3[4][0] = 16;

        //Czwarty przedział
        int input_count4 = 9;
        double value_x4_a = 0.5;
        double value_x4_b = 1.5;
        double[][] y4 = new double[input_count4][input_count4];
        double[] x4 = {-3, -2, -1, 0, 1, 2, 3, 4, 5};
        y4[0][0] = 0.125;
        y4[1][0] = 0.25;
        y4[2][0] = 0.5;
        y4[3][0] = 1;
        y4[4][0] = 2;
        y4[5][0] = 4;
        y4[6][0] = 8;
        y4[7][0] = 16;
        y4[8][0] = 32;

        Scanner sc = new Scanner(System.in);
        String S1;
        Float F1;

        boolean B = true;
        do {
            try {
                System.out.println("Wpisz dla jakiego \"x\" (Float oddzielony kropką zamiast przecinka) chcesz obliczyć potęgę 2^x:");
                S1 = sc.next();
                F1 = Float.parseFloat(S1);

                try {
                    System.out.println("Wybierz przedział (wpisz 1, 2, 3, 4 albo 5):");
                    System.out.println("1: x = {-1, 0, 1}");
                    System.out.println("2: x = {1, 2, 3}");
                    System.out.println("3: x = {-2, 0, 1, 2, 4}");
                    System.out.println("4: x = {-3, -2, -1, 0, 1, 2, 3, 4, 5}");
                    System.out.println("5: własny zakres");
                    String S2 = sc.next();
                    Integer I2 = Integer.parseInt(S2);
                    switch (I2) {
                        case 1 -> {
                            wyliczIlorazy(x, y, input_count);
                            drukujTablice(y, input_count, indeksy, x);
                            System.out.println("Wartość dla \"x\" = " + F1 + " wynosi " + wielNewtona(F1, x, y, input_count) + "\n");
                            System.out.printf("Błąd bezwzględny wynosi: %f\n", Math.abs(Math.pow(2, F1) - wielNewtona(F1, x, y, input_count)));
                            System.out.printf("Błąd względny wynosi: %f\n", Math.abs((Math.pow(2, F1) - wielNewtona(F1, x, y, input_count)) / Math.pow(2, F1)));
                        }
                        case 2 -> {
                            wyliczIlorazy(x2, y2, input_count2);
                            drukujTablice(y2, input_count2, indeksy, x2);
                            System.out.println("Wartość dla \"x\" = " + F1 + " wynosi " + wielNewtona(F1, x2, y2, input_count2) + "\n");
                            System.out.printf("Błąd bezwzględny wynosi: %f\n", Math.abs(Math.pow(2, F1) - wielNewtona(F1, x2, y2, input_count)));
                            System.out.printf("Błąd względny wynosi: %f\n", Math.abs((Math.pow(2, F1) - wielNewtona(F1, x2, y2, input_count2)) / Math.pow(2, F1)));
                        }
                        case 3 -> {
                            wyliczIlorazy(x3, y3, input_count3);
                            drukujTablice(y3, input_count3, indeksy, x3);
                            System.out.println("Wartość dla \"x\" = " + F1 + " wynosi " + wielNewtona(F1, x3, y3, input_count3) + "\n");
                            System.out.printf("Błąd bezwzględny wynosi: %f\n", Math.abs(Math.pow(2, F1) - wielNewtona(F1, x3, y3, input_count3)));
                            System.out.printf("Błąd względny wynosi: %f\n", Math.abs((Math.pow(2, F1) - wielNewtona(F1, x3, y3, input_count3)) / Math.pow(2, F1)));
                        }
                        case 4 -> {
                            wyliczIlorazy(x4, y4, input_count4);
                            drukujTablice(y4, input_count4, indeksy, x4);
                            System.out.println("Wartość dla \"x\" = " + F1 + " wynosi " + wielNewtona(F1, x4, y4, input_count4) + "\n");
                            System.out.printf("Błąd bezwzględny wynosi: %f\n", Math.abs(Math.pow(2, F1) - wielNewtona(F1, x4, y4, input_count4)));
                            System.out.printf("Błąd względny wynosi: %f\n", Math.abs((Math.pow(2, F1) - wielNewtona(F1, x4, y4, input_count4)) / Math.pow(2, F1)));
                        }
                        case 5 -> {
                            try {
                                System.out.println("Podaj ilość argumentów");
                                String S3 = sc.next();
                                Integer input_count_user = Integer.parseInt(S3);
                                double[][] y_user = new double[input_count_user][input_count_user];
                                double[] x_user = new double[input_count_user];
                                for (int i = 0; i < input_count_user; i++) {
                                    System.out.printf("Podaj %d argument: ", i+1);
                                    S3 = sc.next();
                                    x_user[i] = Double.parseDouble(S3);
                                    y_user[i][0] = Math.pow(2, x_user[i]);
                                }
                                System.out.println("Twój zakres" + Arrays.toString(x_user));
                                wyliczIlorazy(x_user, y_user, input_count_user);
                                drukujTablice(y_user, input_count_user, indeksy, x_user);
                                System.out.println("Wartość dla \"x\" = " + F1 + " wynosi " + wielNewtona(F1, x_user, y_user, input_count_user) + "\n");
                                System.out.printf("Błąd bezwzględny wynosi: %f\n", Math.abs(Math.pow(2, F1) - wielNewtona(F1, x_user, y_user, input_count_user)));
                                System.out.printf("Błąd względny wynosi: %f\n", Math.abs((Math.pow(2, F1) - wielNewtona(F1, x_user, y_user, input_count_user)) / Math.pow(2, F1)));


                            } catch (NumberFormatException e) {
                                System.out.println("Nie podałeś poprawnego typu");
                            }
                        }
                        default -> System.out.println("Wybierz poprawny zakres");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Nie podałeś poprawnego typu");
                }
            } catch (NumberFormatException e) {
                System.out.println("Nie podałeś poprawnego typu");
            }
            System.out.println("Wciśnij dowolny klawisz aby kontynuować lub \"N\" by zakończyć program");
            String S3 = sc.next();
            if (Objects.equals(S3, "N")) {
                B = false;
            }
        } while (B);
    }
}
