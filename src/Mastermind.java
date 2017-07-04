import javax.swing.JOptionPane;

public class Mastermind {
	public static void main(String[] args) {
		
		// generate secret code
		int[] secretCode = new int[4];
		for (int i = 0; i < 4; i++) {
			secretCode[i] = (int) (Math.random() * 6);
		}
		
		boolean victory = false;
		for (int turn = 0; turn < 10; turn++) { // allowed 10 guesses
			String input = "";
			while (input.length() != 4) {
				input = JOptionPane.showInputDialog("What is your guess?");
			}
			System.out.println("You guesssed " + input);
			
			int[] guess = parseInput(input);
			
			int numReds = 0;
			for (int i = 0; i < 4; i++) {
				if (guess[i] == secretCode[i]) {
					numReds++;
				}
			}
			
			int[] codeBreakdown = getNumberBreakdown(secretCode);
			int[] guessBreakdown = getNumberBreakdown(guess);
			
			int numWhites = 0;
			for (int i = 0; i < 6; i++) {
				numWhites += Math.min(codeBreakdown[i], guessBreakdown[i]);
			}
			numWhites -= numReds;
			
			if (numReds == 4) {
				System.out.println("You win in " + (turn + 1) + " moves");
				JOptionPane.showMessageDialog(null, "You win in " + (turn + 1) + " moves");
				victory = true;
				break;
			} else {
				System.out.println("Reds: " + numReds);
				System.out.println("Whites: " + numWhites);
				JOptionPane.showMessageDialog(null, "Reds: " + numReds + "\nWhites: " + numWhites);

			}
			
		}
		
		if (!victory) {
			System.out.println("You lose!");
		}
	}
	
	public static int[] parseInput(String input) {
		
		int inputNum = Integer.parseInt(input);
		
		int[] guess = new int[input.length()];
		for (int i = guess.length - 1; i >= 0; i--) {
			guess[i] = inputNum % 10; // get last digit
			inputNum /= 10; // remove last digit
		}
		
		return guess;
	}
	
	public static int[] getNumberBreakdown(int[] code) {
				
		int[] breakdown = new int[6];
		for (int i = 0; i < 6; i++) {
			breakdown[i] = 0;
		}
		
		for (int x : code) {
			breakdown[x]++;
		}
		
		return breakdown;
	}
}
