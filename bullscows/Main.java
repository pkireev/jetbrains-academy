package bullscows;
import java.util.Random;
import java.util.Scanner;

public class Main {
	static String secretCode = "";
	static boolean guessed = false;
	static int turnsCount = 0;
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		prepareSecretCode();
		
		while(!guessed) {
			String answer = turn();
			int[] result = checkAnswer(answer);

			System.out.println(buildText(result));
			
			if (result[0] == 4) {
				guessed = true;
				System.out.printf("Congrats! The secret code is %s.\n", secretCode);
				sc.close();
			}
		}
	}
	
	public static void prepareSecretCode() {
		while(secretCode.length() < 4) {
			Random r = new Random();
			String n = String.valueOf((secretCode.isEmpty() ? r.nextInt(9) + 1 : r.nextInt(10)));
			
			if (!secretCode.contains(String.valueOf(n))) {
				secretCode += n;
			}
		}
		System.out.println("The secret code is prepared: ****.\n");
	}
	
	public static String turn() {
		System.out.printf("Turn %d. Answer:\n", ++turnsCount);
		String answer = sc.nextLine();
		
		return answer;
	}
	
	public static int[] checkAnswer(String answer) {
		int bulls = 0;
		int cows = 0;
		
		for (int i = 0; i < answer.length(); i++) {
			char ch = answer.charAt(i);

			if (secretCode.charAt(i) == answer.charAt(i)) {
				bulls++;
			} else {
				if (secretCode.indexOf(ch) >= 0) {
					cows++;
				}
			}
		}
		
		return new int[] {bulls, cows};
	}
	
	public static String buildText(int[] result) {
		String text = "Grade: ";
		int bulls = result[0];
		int cows = result[1];
		
		if (bulls > 0) {
			text += String.format("%d bull",  bulls);
			text += (bulls > 1) ? "s": "";
			text += (cows > 0) ? " and ": "";
		}
		
		if (cows > 0) {
			text += String.format("%d cow",  cows);
			text += (cows > 1) ? "s": "";
		}
		
		if (cows == 0 && bulls ==0) {
			text += "None";
		}
		
		text += ".\n";
		return text;
	}

}
