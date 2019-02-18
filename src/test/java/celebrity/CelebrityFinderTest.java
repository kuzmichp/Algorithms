package celebrity;

import com.google.common.collect.ImmutableList;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

public class CelebrityFinderTest {

    @InjectMocks
    private CelebrityFinder celebrityFinder = new CelebrityFinder();
    @Mock
    private RelationManager relationManager;

    @BeforeClass
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldFindCelebrity() {
        // given
        Person celebrity = new Person(1);
        Person randomPerson = new Person(2);
        List<Person> people = ImmutableList.of(celebrity, randomPerson);

        // when
        doReturn(false).when(relationManager).knows(celebrity, randomPerson);
        doReturn(true).when(relationManager).knows(randomPerson, celebrity);

        // then
        assertThat(celebrityFinder.findCelebrity(people)).isEqualTo(celebrity);
    }
}
