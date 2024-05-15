package palanquin.com;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Test DateSorter.class")
class DateSorterTest {

    @DisplayName("Test 'sortDates()' successful")
    @ParameterizedTest(name = "From {0} result => {1}")
    @MethodSource("getDatesSuccessfulArgs")
    void testSortDatesSuccessful(List<LocalDate> value, Collection<LocalDate> result) {
        DateSorter dateSorter = new DateSorter();
        assertEquals(result, dateSorter.sortDates(value));
    }

    @DisplayName("Test 'sortDates()' failed")
    @ParameterizedTest(name = "From ''{0}'' result => IllegalArgumentException")
    @NullSource
    @EmptySource
    void testSortDatesFailed(List<LocalDate> value) {
        DateSorter dateSorter = new DateSorter();
        assertThrows(IllegalArgumentException.class, () -> dateSorter.sortDates(value));
    }

    private static Stream<Arguments> getDatesSuccessfulArgs() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                LocalDate.of(2004, 7, 1),
                                LocalDate.of(2005, 1, 2),
                                LocalDate.of(2007, 1, 1),
                                LocalDate.of(2032, 5, 3)
                        ),
                        List.of(
                                LocalDate.of(2005, 1, 2),
                                LocalDate.of(2007, 1, 1),
                                LocalDate.of(2032, 5, 3),
                                LocalDate.of(2004, 7, 1)
                        )
                ),
                Arguments.of(
                        List.of(
                                LocalDate.of(2018, 9, 10),
                                LocalDate.of(2019, 10, 22),
                                LocalDate.of(2017, 6, 30),
                                LocalDate.of(2015, 7, 4)
                        ),
                        List.of(
                                LocalDate.of(2018, 9, 10),
                                LocalDate.of(2019, 10, 22),
                                LocalDate.of(2017, 6, 30),
                                LocalDate.of(2015, 7, 4)
                        )
                ),
                Arguments.of(
                        List.of(
                                LocalDate.of(2004, 7, 1),
                                LocalDate.of(2005, 1, 2),
                                LocalDate.of(2007, 1, 1),
                                LocalDate.of(2032, 5, 3),
                                LocalDate.of(2010, 9, 10),
                                LocalDate.of(2020, 3, 17),
                                LocalDate.of(2018, 6, 15),
                                LocalDate.of(2019, 12, 25),
                                LocalDate.of(2022, 11, 11),
                                LocalDate.of(2011, 4, 20),
                                LocalDate.of(2009, 8, 5),
                                LocalDate.of(2017, 10, 31),
                                LocalDate.of(2016, 2, 29),
                                LocalDate.of(2015, 7, 4),
                                LocalDate.of(2021, 5, 6)
                        ),
                        List.of(
                                LocalDate.of(2005, 1, 2),
                                LocalDate.of(2007, 1, 1),
                                LocalDate.of(2010, 9, 10),
                                LocalDate.of(2011, 4, 20),
                                LocalDate.of(2016, 2, 29),
                                LocalDate.of(2017, 10, 31),
                                LocalDate.of(2019, 12, 25),
                                LocalDate.of(2020, 3, 17),
                                LocalDate.of(2022, 11, 11),
                                LocalDate.of(2032, 5, 3),
                                LocalDate.of(2021, 5, 6),
                                LocalDate.of(2018, 6, 15),
                                LocalDate.of(2015, 7, 4),
                                LocalDate.of(2009, 8, 5),
                                LocalDate.of(2004, 7, 1)
                        )
                )
        );
    }

}