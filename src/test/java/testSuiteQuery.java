import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class) // test suite calls both tests

@Suite.SuiteClasses({
        testUserQuery.class,
        testContactQuery.class
})

public class testSuiteQuery {
}
