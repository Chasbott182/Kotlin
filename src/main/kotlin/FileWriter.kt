import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

fun writeFile(cityLink: String):Cities? {
        val doc: Document

        try {
            doc = Jsoup.connect("https://en.wikipedia.org$cityLink").get()  // <2>
        } catch (e: Exception) {
            return null
        }
        val cities = Cities()
        doc.select(".infobox tr")   // <4>
            .forEach { ele ->   // <5>
                when {
                    ele.getElementsByTag("div")?.hasClass("fn org") ?: false -> {   // <6>
                        cities.name = ele.getElementsByTag("th")?.text()
                    }
                    else -> {
                        val value: String? = if (ele.getElementsByTag("tr").size > 1)
                            ele.getElementsByTag("a")
                                .map(Element::text)
                                .filter(String::isNotEmpty)
                                .joinToString(", ") else
                            ele.getElementsByTag("td")?.first()?.text() // <7>

                        when (ele.getElementsByTag("th")?.first()?.text()) {    // <8>
                            "State" -> cities.state = value ?: ""
                            "U.S. state" -> cities.state = value ?: ""
                            "Area code(s)" -> cities.areaCode = value ?: ""
                            "Area codes" -> cities.areaCode = value ?: ""
                            "Area code" -> cities.areaCode = value ?: ""
                        }
                    }
                }
            }
        return cities

    }
