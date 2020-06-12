package net.dankito.text.extraction.info

import net.dankito.text.extraction.info.model.DateData
import org.slf4j.LoggerFactory
import java.util.regex.Matcher
import java.util.regex.Pattern


open class DateExtractor : IDateExtractor {

    companion object {
        const val Day = "(?<day>[012][0-9]|3[01]|[1-9])"
        const val Month = "(?<month>1[012]|0?[1-9])"
        const val Year = "(?<year>(?:19|20)?\\d\\d)"
        const val Separator = "[./ -]"
        const val Boundary = "\\b"

        // = "\\b([012][0-9]|3[01]|[1-9])[- /.](1[012]|0?[1-9])[- /.](19|20)?\\d\\d\\b"
        const val DayMonthYearPatternString = "$Boundary$Day$Separator$Month$Separator$Year$Boundary"
        val DayMonthYearPattern = Pattern.compile(DayMonthYearPatternString)

        // = "\\b(1[012]|0?[1-9])[- /.]([012][0-9]|3[01]|[1-9])[- /.](19|20)?\\d\\d\\b"
        const val MonthDayYearPatternString = "$Boundary$Month$Separator$Day$Separator$Year$Boundary"
        val MonthDayYearPattern = Pattern.compile(MonthDayYearPatternString)

        // = "\\b(19|20)?\\d\\d[- /.](1[012]|0?[1-9])[- /.]([012][0-9]|3[01]|[1-9])\\b"
        const val YearMonthDayPatternString = "$Boundary$Year$Separator$Month$Separator$Day$Boundary"
        val YearMonthDayPattern = Pattern.compile(YearMonthDayPatternString)

        // may check also:
        // http://regexlib.com/DisplayPatterns.aspx?cattabindex=4&categoryId=5&AspxAutoDetectCookieSupport=1
        // https://www.regular-expressions.info/dates.html
        // https://stackoverflow.com/questions/51224/regular-expression-to-match-valid-dates
        // https://github.com/HeidelTime/heideltime


        private val log = LoggerFactory.getLogger(DateExtractor::class.java)

    }


    override fun extractDates(text: String): List<DateData> {
        return extractDates(text.split("\n"))
    }

    override fun extractDates(lines: List<String>): List<DateData> {

        val dayMonthYearDates = findDateStrings(lines, DayMonthYearPattern)

        val monthDayYearDates = findDateStrings(lines, MonthDayYearPattern)

        val yearMonthDayDates = findDateStrings(lines, YearMonthDayPattern)


        return getLargestList(dayMonthYearDates, monthDayYearDates, yearMonthDayDates)
    }

    protected open fun <T> getLargestList(vararg lists: List<T>): List<T> {
        if (lists.isNotEmpty()) {
            var largestList = lists.first()

            for (i in 1 until lists.size) {
                val list = lists[i]

                if (list.size > largestList.size) {
                    largestList = list
                }
            }

            return largestList
        }

        return listOf()
    }

    protected open fun findDateStrings(lines: List<String>, pattern: Pattern): List<DateData> {
        val matchers = lines.associateBy( { it } , { pattern.matcher(it) } )

        val matches = matchers.filter { it.value.find() }

        return matches.map { findDateStrings(it.value, it.key) }.flatten()
    }

    protected open fun findDateStrings(matcher: Matcher, line: String): List<DateData> {
        matcher.reset()
        val matches = mutableListOf<DateData>()

        while (matcher.find()) {
            try {
                val day = matcher.group("day").toInt()
                val month = matcher.group("month").toInt()
                val year = matcher.group("year").toInt()

                matches.add(DateData(day, month, year, matcher.group(), line))
            } catch (e: Exception) {
                log.error("Could not map date string '${matcher.group()}' in line '$line' to Date", e)
            }
        }

        return matches
    }

}