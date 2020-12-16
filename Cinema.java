package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {
    private int rows;
    private int seats;
    private int totalSeats;
    private boolean big;
    private int ticketsSold;
    private int currentIncome;

    private char[][] visual;

    public Cinema(int rows, int seats) {
        this.rows = rows;
        this.seats = seats;
        totalSeats = rows * seats;
        big = totalSeats > 60;
        ticketsSold = 0;
        visual = new char[rows][seats];
        for (char[] row : visual) {
            Arrays.fill(row, 'S');
        }
    }

    public int getTicketsSold() {
        return ticketsSold;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getCurrentIncome() {
        return currentIncome;
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

    public int calcTotalIncome() {
        if (!big) {
            return totalSeats * 10;
        } else {
            var frontRows = rows / 2;
            var backRows = rows - frontRows;
            return (frontRows * seats * 10) + (backRows * seats * 8);
        }
    }

    private boolean isAvailableSeat(int rowN, int seatN) {
        if (rowN > rows || seatN > seats) {
            System.out.println("Wrong input!");
            return false;
        }
        if (visual[rowN - 1][seatN - 1] == 'B') {
            System.out.println("That ticket has already been purchased!");
            return false;
        }
        return true;
    }

    public int bought(int rowN, int seatN) {
        int price = !big ? 10 : rowN <= rows / 2 ? 10 : 8;
        visual[rowN - 1][seatN - 1] = 'B';
        ticketsSold++;
        currentIncome += price;
        return price;
    }

    public static void main(String[] args) {
        var scan = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        var rows = scan.nextInt();
        System.out.println("Enter the number of seats in each row:");
        var seats = scan.nextInt();
        var cinema = new Cinema(rows, seats);

        while (true) {
            System.out.println("1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit");
            var command = scan.nextInt();

            switch (command) {
                case 1:
                    System.out.println("Cinema:");
                    System.out.println(cinema.getVisual());
                    break;
                case 2:
                    while (true) {
                        System.out.println("Enter a row number:");
                        var rowN = scan.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        var seatN = scan.nextInt();

                        if (cinema.isAvailableSeat(rowN, seatN)) {
                            System.out.printf("Ticket price: $%d\n", cinema.bought(rowN, seatN));
                            break;
                        }
                    }
                    break;
                case 3:
                    System.out.printf("Number of purchased tickets: %d\n" +
                            "Percentage: %.2f%%\n" +
                            "Current income: $%d\n" +
                            "Total income: $%d\n",
                            cinema.getTicketsSold(),
                            cinema.getTicketsSold() / (double) cinema.getTotalSeats() * 100,
                            cinema.getCurrentIncome(),
                            cinema.calcTotalIncome());
                    break;
                case 0:
                    return;
                default:
                    continue;
            }
        }
    }
}