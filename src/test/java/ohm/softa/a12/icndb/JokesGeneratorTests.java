package ohm.softa.a12.icndb;

import ohm.softa.a12.cnjdb.JokeGenerator;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JokesGeneratorTests {

    private JokeGenerator jokeGenerator = new JokeGenerator();

    @Test
    void testRandomStream() {
        /* timeout to ensure that stream does not loop forever */
        /* TODO implement a test for the random joke stream */
		JokeGenerator jg = new JokeGenerator();
		long count = jg.randomJokesStream().limit(10).count();

		assertEquals(count, 10);
    }


    @Test
    void testJokesStream() {
        /* TODO implement a test for the linear jokes generator */
		JokeGenerator jg = new JokeGenerator();
		long count = jg.allJokesStream().limit(10).count();

		assertEquals(count, 10);
    }

}
