package cinema;
import java.util.Scanner;

public class Cinema {
	static int[][] hall;
	static Scanner sc;
	static int seats, rows;
	static int purchasedTickets = 0;
	static double purchasedTicketsPercentage = 0;
	static int currentIncome = 0;
	static int totalIncome = 0;
	
	public static void main(String[] args) {
        
		sc = new Scanner(System.in);
		System.out.println("Enter the number of rows:");
		rows = sc.nextInt();
		
		System.out.println("Enter the number of seats in each row:");
		seats = sc.nextInt();
		
		hall = new int [rows][seats];
		totalIncome = getTotalIncome();
		
		
		while(true) {
			int chosen = showMenu();
			
			switch(chosen) {
				case 0:
					sc.close();
					return;
				case 1:
					showTheSeats();
					break;
				case 2:
					buyATicket();
					break;
				case 3:
					showStats();
					break;
			}
		}		
	}
    
    
	public static int showMenu() {
		System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
		return sc.nextInt();
	}
    
    
	public static void showTheSeats() {
		System.out.println("Cinema:");
		System.out.print("  ");
        
		for (int i = 1; i <= seats; i++) {
			System.out.print(i + " ");
		}
		System.out.println();
        
		for (int i = 1; i <= rows; i++) {
			System.out.print(i);
        	
			for (int j = 1; j <= seats; j++) {
				if (hall[i - 1][j - 1] == 0) {
					System.out.print(" S");
				} else {
					System.out.print(" B");
				}
			}
			System.out.println();
		}
        
		System.out.println();
	}
    
	public static void buyATicket() {
    	
		boolean sold = false;
		int row = 0, seat = 0;
		
		while (!sold) {
			System.out.println("Enter a row number:");
			row = sc.nextInt();
			System.out.println("Enter a seat number in that row:");
			seat = sc.nextInt();        
			
			if (row <= 0 || row > rows || seat <= 0 || seat > seats ) {
				System.out.println("Wrong input!");
			} else {
			
				if (hall[row - 1][seat - 1] == 1) {
					System.out.println("That ticket has already been purchased!");
				} else {
					hall[row - 1][seat - 1] = 1;
					sold = true;
				}
			}
		}
		
		int price = getTicketPrice(row);
		
		System.out.printf("Ticket price: $%d\n\n", price);
		
		purchasedTickets++;
		purchasedTicketsPercentage = (double) purchasedTickets / (double) (seats * rows) * 100d;
		currentIncome += price;
		
	}
	
	public static int getTicketPrice(int row) {
		int price = 10;
        
		int half = rows / 2;
		if (row > half && rows * seats > 60) {
			price = 8;
		}
		
		return price;
	}
	
	public static int getTotalIncome() {
		
		if (rows * seats <= 60) {
			return rows * seats * 10;
		}
		
		int half = rows / 2;
		return half * seats * 10 + (rows - half) * seats * 8;
			
	}
	
	public static void showStats() {		
		System.out.printf("Number of purchased tickets: %d\n", purchasedTickets);
		System.out.printf("Percentage: %.2f%%\n", purchasedTicketsPercentage);
		System.out.printf("Current income: $%d\n", currentIncome);
		System.out.printf("Total income: $%d\n\n", totalIncome);
	
	}
}