package ohm.softa.a12;

import ohm.softa.a12.cnjdb.JokeGenerator;
import ohm.softa.a12.model.JokeDto;

import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Stream;

public abstract class App {

	/**
	 * Scanner to read input from the STDIN
	 */
	private static final Scanner inputScanner = new Scanner(System.in);

	/**
	 * Stream generator
	 */
	private static final JokeGenerator jokeGenerator = new JokeGenerator();

	public static void main(String[] args) {
		var shouldQuit = false;
		int jokeCount;
		int skipCount;

		/* loop until the the user wants to quit */
		do {
			jokeCount = readInt("How many jokes do you want?");
			skipCount = readInt("How many jokes do you want to skip");

			Stream<JokeDto> jokesSource = readJokeSource();

			/* TODO consume the `jokesSource`
			 * filter it for non null objects
			 * use `skip` and `limit` to retrieve the required elements
			 * and print the jokes to the STDOUT */

			jokesSource
				.filter(Objects::nonNull)
				.skip(skipCount)
				.limit(jokeCount)
				.map(JokeDto::getValue)
				.forEach(System.out::println);

			System.out.println("If you want to quit press [Q] otherwise press [C] to continue.");
			var input = inputScanner.next();
			if (input.equals("q") || input.equals("Q")) {
				shouldQuit = true;
			}
		} while (!shouldQuit);
		/* close the scanner before exiting */
		inputScanner.close();
		System.exit(0);
	}

	/**
	 * Utility method to read an integer
	 *
	 * @param message message provided to the user
	 * @return read integer value
	 */
	private static int readInt(String message) {
		System.out.println(message);
		do {
			try {
				var input = inputScanner.nextInt();
				if (input >= 0) return input;
			} catch (Exception e) {
				System.out.println("Error while reading integer.");
			}
		} while (true);
	}

	/**
	 * Utility method to determine the joke stream source to use
	 *
	 * @return stream of JokeDtos wrapped in ResponseWrapper objects
	 */
	private static Stream<JokeDto> readJokeSource() {
		System.out.println("Which joke source do you want to use?");
		System.out.println("1) Random jokes");
		System.out.println("2) Linear by id");

		do {
			try {
				var selection = inputScanner.nextInt();
				switch (selection) {
					case 1:
						return jokeGenerator.randomJokesStream();
					default:
						return jokeGenerator.allJokesStream();
				}
			} catch (Exception e) {
				System.out.println("No valid selection");
			}
		} while (true);
	}
}
