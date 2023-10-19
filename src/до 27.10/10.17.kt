import java.io.File

data class Message(val address: String?, val topic: String?, val content: String?, val timestamp: String?) {
    fun toHTML(): String {
        var template = "<!DOCTYPE html>\n" + "<html>\n" + "<head>\n" +
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"css.css\">\n" +
                "</head>\n" + "<body>\n"
        template += "<table> "
         address?.let { template += ("<tr><td class=\"address\">address</td><td>$it</td></tr> \n") }
        topic?.let { template += ("<tr><td class=\"topic\">topic</td><td>$it</td></tr> \n") }
        content?.let { template += ("<tr><td class=\"date\">date</td><td>$it</td></tr> \n") }
        timestamp?.let { template += ("<tr><td class=\"body\">body</td><td>$it</td></tr> \n") }
        template +=  "</table>"
        template +=  "</body>\n" + "</html>\n"
        return template
    }
    fun toCSS(): String {
        var template_css = "table { border-collapse: collapse; }\n" +
                                    "td {padding: 5px; \nborder: 1px solid black;}\n"
        address?.let { template_css += ".address {color: blue;}\n"}
        topic?.let { template_css += ".topic {color: green;}\n"}
        content?.let { template_css += ".date {color: red;}\n"}
        timestamp?.let { template_css += ".body {font-weight: bold;}\n"}
        return template_css
    }
}

fun main() {
    val m = Message("askbill@microsoft.com", "Важное письмо", "Привет! Как дела?", "Среда, Октябрь 17, 2023 11:56:29")
    val html = m.toHTML()
    println(html)
    val css = m.toCSS()
    val file = File("message.html")
    file.writeText(html)
    val file_css = File("css.css")
    file_css.writeText(css)
}