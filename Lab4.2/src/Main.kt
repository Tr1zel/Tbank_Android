import kotlin.system.exitProcess

fun main() {
    val library = Library()
    val initlibrary =  InitLibrary(library)
    val menus = Menus(library)

    initlibrary.addBook("Гарри Поттер и узник Азкабана", 620, "Джоан Роулинг")
    initlibrary.addBook("Унесенные ветром", 270, "Маргарет Митчелл")
    initlibrary.addBook("Зеленая миля", 1000, "Стивен Кинг")
    initlibrary.addJournal(12345, "Известия Москвы")
    initlibrary.addJournal(666, "СпидИнфо")
    initlibrary.addJournal(9999, "Комсомольская правда")
    initlibrary.addDisk("CD", "Рэмбо 2001")
    initlibrary.addDisk("DVD", "Контер страйк 1.6")
    initlibrary.addDisk("DVD", "Сборник фильмов Гарри Поттер")

    menus.mainMenu()
}

abstract class LibraryObject(
    val id: Int,
    val title: String,
    var access: Boolean = true // По умолчанию доступен
) {
    abstract fun getShortDescription(): String
    abstract fun getLongDescription(): String
}

class Book(
    id: Int,
    title: String,
    val pages: Int,
    val author: String,
) : LibraryObject(id, title) {
    override fun getShortDescription(): String {
        return """
                Книга: $title, с Id = ($id) доступна: $access 
            """.trimIndent()
    }

    override fun getLongDescription(): String {
        return """
                Книга: $title ($pages стр.) автора: $author c Id: $id доступна: $access
            """.trimIndent()
    }
}

class Journal(
    id: Int,
    title: String,
    val numIssue: Int,
) : LibraryObject(id, title) {
    override fun getShortDescription(): String {
        return """
                Газета: $title, с Id = ($id) доступна: $access 
            """.trimIndent()
    }

    override fun getLongDescription(): String {
        return """
                Выпуск: $numIssue газеты $title с Id: $id доступен: $access
            """.trimIndent()
    }
}

class Disk(
    id: Int,
    title: String,
    val typeDisk: String,
) : LibraryObject(id, title) {
    override fun getShortDescription(): String {
        return """
                Диск: $title, с Id = ($id) доступен: $access 
            """.trimIndent()
    }

    override fun getLongDescription(): String {
        return """
                $typeDisk $title доступен: $access 
            """.trimIndent()
    }
}

class Library {
    val ListBook: MutableList<Book> = mutableListOf()
    val ListJournal: MutableList<Journal> = mutableListOf()
    val ListDisk: MutableList<Disk> = mutableListOf()

    fun takeHome(typeId: Int, objectId: Int) {
        val ListToTake = when (typeId) {
            1 -> ListBook
            2 -> {
                println("Газету нельзя взять домой!")
                return
            }
            3 -> ListDisk
            else -> {
                println("Неверный typeID")
                return
            }
        }

        val foundItem = ListToTake.find { it.id == objectId }
        if (foundItem == null) {
            println("Обьекта с Id = $objectId не найден")
            return
        }
        if (!foundItem.access) {
            println("Обьект с Id = $objectId недоступен")
            return
        }
        foundItem.access = false
        println("Обьект с Id = $objectId взят домой")
    }

    fun readInLibrary(typeId: Int, objectId: Int) {
        val ListToTake = when (typeId) {
            1 -> ListBook
            2 -> ListJournal
            3 -> {
                println("Диски нельзя читать в читальном зале!")
                return
            }
            else -> {
                println("Неверный typeID")
                return
            }
        }

        val foundItem = ListToTake.find { it.id == objectId }
        if (foundItem == null) {
            println("Обьект с Id = $objectId не найден")
            return
        }
        if (!foundItem.access) {
            println("Обьект с Id = $objectId недоступен в данный момент")
            return
        }
        foundItem.access = false
        println("Обьект с Id = $objectId успешно взят в читальный зал")
    }

    fun returnFromHome(typeId: Int, objectId: Int) {
        val listToReturn = when (typeId) {
            1 -> ListBook
            2 -> ListJournal
            3 -> ListDisk
            else -> {
                println("Неправильный ID")
                return
            }
        }

        val foundItem = listToReturn.find { it.id == objectId }
        if (foundItem == null) {
            println("Обьект с Id = $objectId не найден")
            return
        }
        if (foundItem.access) {
            println("Обьект с Id = $objectId в наличии")
            return
        }

        foundItem.access = true
        println("Обьект с Id = $objectId успешно возвращен на базу")
    }
}

class InitLibrary(private val library: Library) {
    val formatprint = FormatPrint(library)

    fun addBook(title: String, pages: Int, author: String) {
        Book(id = library.ListBook.size, title = title, pages = pages, author = author)
            .also { library.ListBook.add(it) }
    }
    fun addJournal(numIssue: Int, title: String) {
        Journal(id = library.ListJournal.size, numIssue = numIssue, title = title)
            .also { library.ListJournal.add(it) }
    }
    fun addDisk(typeDisk: String, title: String) {
        Disk(id = library.ListDisk.size, typeDisk = typeDisk, title = title)
            .also { library.ListDisk.add(it) }
    }

    fun showBook(){ formatprint.printInfoShort(1) }
    fun showJournal() { formatprint.printInfoShort(2) }
    fun showDisk() { formatprint.printInfoShort(3) }
}

class Menus(private val library: Library) {
    private val initLibrary = InitLibrary(library)
    private val formatPrint = FormatPrint(library)

    fun mainMenu() {
        while (true) {
            println(
                "1. Показать книги\n" +
                        "2. Показать газеты\n" +
                        "3. Показать диски\n" +
                        "4. Выйти"
            )
            println("Выберите пункт меню:")

            when (readlnOrNull()?.toIntOrNull()) {
                1 -> {
                    initLibrary.showBook()
                    secondMenu(1)
                }
                2 -> {
                    initLibrary.showJournal()
                    secondMenu(2)
                }
                3 -> {
                    initLibrary.showDisk()
                    secondMenu(3)
                }
                4 -> {
                    println("Спасибо за использование!")
                    exitProcess(999)
                }
                else -> println("Неправильный пункт меню")
            }
        }
    }

    fun secondMenu(typeId: Int) {
        println("Введите Id обьекта с которым хотите работать!")
        val Object_ID = readlnOrNull()?.toIntOrNull()
        if (Object_ID == null){
            println("Id не может быть равным нулю")
            return
        }
        while (true) {
            println("1. Взять домой\n" +
                    "2. Читать в читальном зале\n" +
                    "3. Показать подробную информацию\n" +
                    "4. Вернуть\n" +
                    "5. Назад\n")
            println("Выберите пункт меню:")

            when (readlnOrNull()?.toIntOrNull()) {
                1 -> library.takeHome(typeId, Object_ID)
                2 -> library.readInLibrary(typeId, Object_ID)
                3 -> formatPrint.printInfoLong(typeId, Object_ID)
                4 -> library.returnFromHome(typeId, Object_ID)
                5 -> mainMenu()
                else ->  println("Неправильный выбор!")
            }
        }
    }
}

class FormatPrint(private val library: Library) {
    // typeId = Id  типа обьекта, 1 = книги, 2 = газеты, 3 = диски
    fun printInfoShort(typeId: Int) {
        val ListToPrint = when (typeId) {
            1 -> library.ListBook
            2 -> library.ListJournal
            3 -> library.ListDisk
            else -> {
                println("Неверный typeID")
                return
            }
        }
        if (ListToPrint.isEmpty()) {
            println("Список пуст!")
        } else {
            ListToPrint.forEach { item ->
                println(item.getShortDescription())
            }
        }
    }

    fun printInfoLong(typeId: Int, objectId: Int) {
        val ListToPrint = when (typeId) {
            1 -> library.ListBook
            2 -> library.ListJournal
            3 -> library.ListDisk
            else -> {
                println("Неверный typeID")
                return
            }
        }
        if (ListToPrint.isEmpty()) {
            println("Список пуст!")
        } else {
            println(ListToPrint[objectId].getLongDescription())
            return
        }
    }
}
