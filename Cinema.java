package cinema;

import java.util.Scanner;

public class Cinema {
    private int rows;
    private int seats;

    public Cinema(int rows, int seats) {
        this.rows = rows;
        this.seats = seats;
    }

    public int calcIncome() {
        var seatsTotal = rows * seats;
        if (seatsTotal <= 60) {
            return seatsTotal * 10;
        } else {
            var frontRows = rows / 2;
            var backRows = rows - frontRows;
            return (frontRows * seats * 10) + (backRows * seats * 8);
        }
    }

    public static void main(String[] args) {
        var scan = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        var rows = scan.nextInt();
        System.out.println("Enter the number of seats in each row:");
        var seats = scan.nextInt();
        var cinema = new Cinema(rows, seats);
        System.out.printf("Total income:\n$%d", cinema.calcIncome());
    }
}