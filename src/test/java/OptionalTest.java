import io.vavr.control.Option;
import org.junit.Test;

import java.util.Optional;
import java.util.function.Function;

import static java.util.Objects.nonNull;
import static org.junit.Assert.*;

/**
 * Created by mtumilowicz on 2018-12-28.
 */
public class OptionalTest {

    private Function<Integer, Integer> nullFunction = i -> null;
    private Function<Integer, String> toString = i -> nonNull(i) ? String.valueOf(i) : "null";
    private Function<Integer, String> composition = nullFunction.andThen(toString);

    @Test
    public void breaksCompositionRules() {
        assertNotEquals(Optional.of(1).map(composition),
                Optional.of(1).map(nullFunction).map(toString));
    }

    @Test
    public void followsCompositionRulesAsStream() {
        assertEquals(Optional.of(1).stream().map(composition).findAny(),
                Optional.of(1).stream().map(nullFunction).map(toString).findAny());
    }

    @Test
    public void followsCompositionRules() {
        assertEquals(Option.of(1).map(composition),
                Option.of(1).map(nullFunction).map(toString));
    }
}
