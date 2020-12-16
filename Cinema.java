package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {
    private int rows;
    private int seats;
    private boolean big;
    private char[][] visual;

    public Cinema(int rows, int seats) {
        this.rows = rows;
        this.seats = seats;
        big = rows * seats > 60;
        visual = new char[rows][seats];
        for (char[] row : visual) {
            Arrays.fill(row, 'S');
        }
    }

    public String getVisual() {
        var sb = new StringBuilder(" ");
        for (int i = 1; i <= seats; i++) {
            sb.append(" ").append(i);
        }
        sb.append('\n');
        for (int i = 0; i < rows; i++) {
            sb.append(i + 1);
            for (char c : visual[i]) {
                sb.append(' ').append(c);
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public int calcIncome() {
        if (big) {
            return rows * seats * 10;
        } else {
            var frontRows = rows / 2;
            var backRows = rows - frontRows;
            return (frontRows * seats * 10) + (backRows * seats * 8);
        }
    }

    public int getSeatPrice(int rowN) {
        return !big ? 10 : rowN <= rows / 2 ? 10 : 8;
    }

    public void bought(int rowN, int seatN) {
        visual[rowN - 1][seatN - 1] = 'B';
    }

    public static void main(String[] args) {
        var scan = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        var rows = scan.nextInt();
        System.out.println("Enter the number of seats in each row:");
        var seats = scan.nextInt();

        var cinema = new Cinema(rows, seats);
        System.out.println("Cinema:");
        System.out.println(cinema.getVisual());
        System.out.println("Enter a row number:");
        var rowN = scan.nextInt();
        System.out.println("Enter a seat number in that row:");
        var seatN = scan.nextInt();
        System.out.printf("Ticket price: $%d\n", cinema.getSeatPrice(rowN));
        cinema.bought(rowN, seatN);
        System.out.println("Cinema:");
        System.out.println(cinema.getVisual());

//        System.out.printf("Total income:\n$%d", cinema.calcIncome());
    }
}