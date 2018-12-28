import io.vavr.control.Option;
import org.junit.Test;

import java.util.Optional;
import java.util.function.Function;

import static java.util.Objects.nonNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by mtumilowicz on 2018-12-28.
 */
public class OptionalTest {
    
    @Test
    public void breaksComposition() {
        Function<Integer, Integer> nullFunction = i -> null;
        Function<Integer, String> toString = i -> nonNull(i) ? String.valueOf(i) : "null";

        Function<Integer, String> composition = nullFunction.andThen(toString);

        assertNotEquals(Optional.of(1).map(composition), Optional.of(1).map(nullFunction).map(toString));
    }

    @Test
    public void followsComposition() {
        Function<Integer, Integer> nullFunction = i -> null;
        Function<Integer, String> toString = i -> nonNull(i) ? String.valueOf(i) : "null";

        Function<Integer, String> composition = nullFunction.andThen(toString);

        assertEquals(Option.of(1).map(composition), Option.of(1).map(nullFunction).map(toString));
    }
}
