import org.jsoup.Jsoup
import java.io.PrintWriter
import java.lang.StringBuilder
import java.util.Arrays.stream
import java.util.stream.Collectors




fun main() {

    var writer = PrintWriter("G:\\Downloads\\WikiData.csv")
    var sb = StringBuilder()
    sb.append("City|")
    sb.append("State|")
    sb.append("Area Codes")
    sb.append("\n")
    val wiki = Connect()
    val wikiSite: String = wiki.site("https://en.wikipedia.org/wiki/List_of_United_States_cities_by_population")
    val doc = Jsoup.parse(wikiSite)

    doc.selectFirst(".wikitable.sortable")
        .select("tr")
        .select("td:eq(1) a[href*=/wiki/]")
        .map { col -> col.attr("href") }    // <3>
        .parallelStream()    // <4>
        .map { writeFile(it) }    // <5>
        .filter { it != null }
        .forEach { sb.append(it) }
        //.forEach { println(it) } //for testing

    writer.write(sb.toString())
    writer.close()
}

