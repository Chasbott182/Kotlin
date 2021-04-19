import java.net.HttpURLConnection
import java.net.URL

class  Connect(){

    fun site(link: String): String {
        val url = URL(link).openConnection() as HttpURLConnection
        return url.inputStream.bufferedReader().readText()
    }


}