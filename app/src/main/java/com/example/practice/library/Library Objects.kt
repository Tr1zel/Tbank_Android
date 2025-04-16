import java.io.Serializable
import java.time.Month

abstract class LibraryObject(
    val id: Int,
    var title: String,
    var access: Boolean = true // По умолчанию доступен
) : Serializable {
    abstract fun getShortDescription(): String
    abstract fun getLongDescription(): String
    abstract fun showInfo(): String
}

class Book(
    id: Int,
    title: String,
    val pages: Int,
    var author: String,
) : LibraryObject(id, title) {
    override fun getShortDescription(): String {
        return """
                Книга: $title, с Id = ($id) доступна: ${if (access) "Да" else "Нет"}
            """.trimIndent()
    }

    override fun getLongDescription(): String {
        return """
                Книга: $title ($pages стр.) автора: $author c Id: $id доступна: ${if (access) "Да" else "Нет"}
            """.trimIndent()
    }

    override fun showInfo(): String {
        return "книга: $title (${pages} стр.) \n" +
                "автора: ${author} с id: $id \n" +
                "доступна: ${if (access) "Да" else "Нет"}"
    }
}

class Journal(
    id: Int,
    title: String,
    val numIssue: Int,
    val numMonthIssue: Int,
) : LibraryObject(id, title) {
    override fun getShortDescription(): String {
        return """
                Газета: $title, с Id = ($id) доступна: ${if (access) "Да" else "Нет"}
            """.trimIndent()
    }

    override fun getLongDescription(): String {
        val Month = ReleaseMonthJournal.fromNum(numMonthIssue)
        return """
                Выпуск: $numIssue, Mесяц: $Month газеты $title с Id: $id доступен: ${if (access) "Да" else "Нет"}
            """.trimIndent()
    }

    override fun showInfo(): String {
        return "выпуск: $numIssue \n" +
                "месяц: $numMonthIssue газеты $title с id: $id \n" +
                "доступен: ${if (access) "Да" else "Нет"}"
    }
}

class Disk(
    id: Int,
    title: String,
    val typeDisk: String,
) : LibraryObject(id, title) {
    override fun getShortDescription(): String {
        return """
                Диск: $title, с Id = ($id) доступен: ${if (access) "Да" else "Нет"}
            """.trimIndent()
    }

    override fun getLongDescription(): String {
        return """
                $typeDisk $title доступен: ${if (access) "Да" else "Нет"}
            """.trimIndent()
    }

    override fun showInfo(): String {
        return "$typeDisk $title \n" +
                "доступен: ${if (access) "Да" else "Нет"}"
    }
}

enum class ReleaseMonthJournal(val numMonthIssue: Int, val russianName: String){
        JANUARY(1, "Январь"),
        FEBRUARY(2, "Февраль"),
        MARCH(3, "Март"),
        APRIL(4, "Апрель"),
        MAY(5, "Май"),
        JUNE(6, "Июнь"),
        JULY(7, "Июль"),
        AUGUST(8, "Август"),
        SEPTEMBER(9, "Сентябрь"),
        OCTOBER(10, "Октябрь"),
        NOVEMBER(11, "Ноябрь"),
        DECEMBER(12, "Декабрь");

    companion object {
        fun fromNum(numMonth: Int): String? {
            return entries.find { it.numMonthIssue == numMonth }?.russianName
        }
    }
}