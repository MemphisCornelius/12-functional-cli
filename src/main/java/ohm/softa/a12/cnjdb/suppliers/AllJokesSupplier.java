package ohm.softa.a12.cnjdb.suppliers;

import ohm.softa.a12.cnjdb.CNJDBApi;
import ohm.softa.a12.cnjdb.CNJDBService;
import ohm.softa.a12.model.JokeDto;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

/**
 * Supplier implementation to retrieve all jokes of the ICNDB in a linear way
 *
 * @author Peter Kurfer
 */

public final class AllJokesSupplier implements Supplier<JokeDto> {

	/* ICNDB API proxy to retrieve jokes */
	private final CNJDBApi icndbApi;
	private final Set<String> ids = new HashSet<>();
	private final int MAX_TRIES = 100;


	public AllJokesSupplier() {
		icndbApi = new CNJDBService().getInstance();
		/* TODO fetch the total count of jokes the API is aware of
		 * to determine when all jokes are iterated and the counters have to be reset */
	}

	public JokeDto get() {
		/* TODO retrieve the next joke
		 * note that there might be IDs that are not present in the database
		 * you have to catch an exception and continue if no joke was retrieved to an ID
		 * if you retrieved all jokes (count how many jokes you successfully fetched from the API)
		 * reset the counters and continue at the beginning */
		try {
			for (int i = 0; i < MAX_TRIES; i++) {
				var joke = icndbApi.getRandomJoke().get();
				if(ids.contains(joke.getId())) continue;
				else {
					ids.add(joke.getId());
					return joke;
				}
			}
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException(e);
		}

		return null;
	}

}
