package palanquin.com;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * Marking will be based upon producing a readable, well engineered solution rather than factors
 * such as speed of processing or other performance-based optimizations, which are less
 * important.
 * <p>
 * Implement in single class. Don't chane constructor and signature method.
 */
public class DateSorter {
    private static final String LETTER = "r";

    /**
     * The implementation of this method should sort dates.
     * The output should be in the following order:
     * Dates with an 'r' in the month,
     * sorted ascending (first to last),
     * then dates without an 'r' in the month,
     * sorted descending (last to first).
     * For example, October dates would come before May dates,
     * because October has an 'r' in it.
     * thus: (2004-07-01, 2005-01-02, 2007-01-01, 2032-05-03)
     * would sort to
     * (2005-01-02, 2007-01-01, 2032-05-03, 2004-07-01)
     *
     * @param unsortedDates - an unsorted list of dates
     * @return the collection of dates now sorted as per the spec
     */
    public Collection<LocalDate> sortDates(List<LocalDate> unsortedDates) {
        if (unsortedDates == null || unsortedDates.isEmpty())
            throw new IllegalArgumentException("The list of dates can't be empty or null");

        return unsortedDates.stream()
                .sorted(customDateComparator())
                .toList();
    }

    private static Comparator<LocalDate> customDateComparator() {
        return Comparator.comparing((LocalDate date) ->
                        date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH).toLowerCase().contains(LETTER))
                .reversed()
                .thenComparing((date1, date2) -> {
                    boolean r1 = containsLetter(date1, LETTER);
                    boolean r2 = containsLetter(date2, LETTER);

                    if (!r1 && !r2) return date2.compareTo(date1);
                    return r1 && r2 ? date1.compareTo(date2) : 0;
                });
    }

    private static boolean containsLetter(LocalDate date1, String letter) {
        return date1.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH).toLowerCase().contains(letter);
    }

}
