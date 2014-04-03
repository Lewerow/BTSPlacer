package calculations;

import org.fest.assertions.api.Assertions;
import org.junit.Test;

/**
 * Created by Ja on 24.03.14.
 */
public class BasebandResourceTest {

	@Test
	public void shouldBeEqual() {
		// given
		BasebandResource b1 = new BasebandResource(20);
		BasebandResource b2 = new BasebandResource(20);

		// when
		boolean isEqual = b1.equals(b2);

		// then
		Assertions.assertThat(isEqual).isTrue();
	}
}
