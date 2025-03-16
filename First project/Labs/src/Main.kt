import kotlin.system.exitProcess

fun main() {
    val library = Library()
    val initlibrary =  initLibrary(library)
    val menus = Menus(library)

    initlibrary.addBook("Гарри Поттер и узник Азкабана", 620, "Джоан Роулинг", true)
    initlibrary.addBook("Унесенные ветром", 270, "Маргарет Митчелл", true)
    initlibrary.addBook("Зеленая миля", 1000, "Стивен Кинг", true)
    initlibrary.addJournal(12345, "Известия Москвы", true)
    initlibrary.addJournal(666, "СпидИнфо", true)
    initlibrary.addJournal(9999, "Комсомольская правда", true)
    initlibrary.addDisk("CD", "Рэмбо 2001", true)
    initlibrary.addDisk("DVD", "Контер страйк 1.6", true)
    initlibrary.addDisk("DVD", "Сборник фильмов Гарри Поттер", true)

    menus.main_menu()
}

class LibraryObjects {
    class Book(
        val Id: Int,
        val Title: String, // Название
        val Pages: Int, // Количество страниц
        val Author: String,  //  Автор
        var Acess: Boolean // Доступность
    )

    class Journal (
        val Id: Int,
        val NumIssue: Int, // Номер выпуска
        val Title: String, // Название
        var Acess: Boolean // Доступность
    )
    class Disk (
        val Id: Int,
        val TypeDisk: String, // Тип диска (двд..)
        val Title: String, // Название
        var Acess: Boolean // Доступность
    )
}

class Library {
    val ListBook: MutableList<LibraryObjects.Book> = mutableListOf()
    val ListJournal: MutableList<LibraryObjects.Journal> = mutableListOf()
    val ListDisk: MutableList<LibraryObjects.Disk> = mutableListOf()

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

        var foundItem: Any? = null
        for (item in ListToTake) {
            when (typeId) {
                1 -> {  //  Книга
                    if ((item as LibraryObjects.Book).Id == objectId) {
                        if ((item as LibraryObjects.Book).Acess) {
                            foundItem = item
                            break
                        } else {
                            println("Книги с Id = $objectId нет в наличии, вы не можете ее взять")
                            return
                        }
                    }
                }
                3 -> {// диск
                    if ((item as LibraryObjects.Disk).Id == objectId) {
                        if ((item as LibraryObjects.Disk).Acess) {
                            foundItem = item
                            break
                        } else {
                            println("Диска с Id = $objectId нет в наличии, вы не можете ее взять")
                            return
                        }
                    }
                }
            }
        }
        if (foundItem != null) {
            when (foundItem) {
                is LibraryObjects.Book  ->  {
                    ListBook[objectId].Acess = ListBook[objectId].Acess.not()
                    println("Книга с Id = $objectId взяли домой")}
                is LibraryObjects.Disk  -> {
                    ListDisk[objectId].Acess = ListDisk[objectId].Acess.not()
                    println("Диск с Id = $objectId взяли домой")
                }
            }
        } else {
            println("Обьект с ID $objectId не найден в списке type_id = $typeId.")
        }
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

        var foundItem: Any? = null
        for (item in ListToTake) {
            when (typeId) {
                1 -> {  //  Книга
                    if ((item as LibraryObjects.Book).Id == objectId) {
                        if ((item as LibraryObjects.Book).Acess) {
                            foundItem = item
                            break
                        } else {
                            println("Книги с Id = $objectId нет в наличии, вы не можете ее взять")
                            return
                        }
                    }
                }
                2 -> {// диск
                    if ((item as LibraryObjects.Journal).Id == objectId) {
                        if ((item as LibraryObjects.Journal).Acess) {
                            foundItem = item
                            break
                        } else {
                            println("Газеты с Id = $objectId нет в наличии, вы не можете ее взять")
                            return
                        }
                    }
                }
            }
        }
        if (foundItem != null) {
            when (foundItem) {
                is LibraryObjects.Book  ->  {
                    ListBook[objectId].Acess = ListBook[objectId].Acess.not()
                    println("Книга с Id = $objectId взяли читать в читальный зал")}
                is LibraryObjects.Journal  -> {
                    ListJournal[objectId].Acess = ListJournal[objectId].Acess.not()
                    println("Газету с Id = $objectId взяли читать в читальный зал")
                }
            }
        } else {
            println("Обьект с ID $objectId не найден в списке type_id = $typeId.")
        }
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

        var foundItem: Any? = null
        for (item in listToReturn) {
            when (typeId) {
                1 -> {  //  Книга
                    if ((item as LibraryObjects.Book).Id == objectId) {
                        if (!(item as LibraryObjects.Book).Acess) {
                            foundItem = item
                            break
                        } else {
                            println("Книга с Id = $objectId в наличии, вы не можете ее вернуть")
                            return
                        }
                    }
                }
                2 -> {  //  Газета
                    if ((item as LibraryObjects.Journal).Id == objectId) {
                        if (!(item as LibraryObjects.Journal).Acess) {
                            foundItem = item
                            break
                        } else {
                            println("Газета с Id = $objectId в наличии, вы не можете ее вернуть")
                            return
                        }
                    }
                }
                3 -> {  //  Диск
                    if ((item as LibraryObjects.Disk).Id == objectId) {
                        if (!(item as LibraryObjects.Disk).Acess) {
                            foundItem = item
                            break
                        } else {
                            println("Диск с Id = $objectId в наличии, вы не можете ее вернуть")
                            return
                        }
                    }
                }
            }
        }

        if (foundItem != null) {
            when (foundItem) {
                is LibraryObjects.Book -> {
                    ListBook[objectId].Acess = ListBook[objectId].Acess.not()
                    println("Книга с Id = $objectId успешно возвращена на базу")
                }
                is LibraryObjects.Journal -> {
                    ListJournal[objectId].Acess = ListJournal[objectId].Acess.not()
                    println("Газета с Id = $objectId успешно возвращена на базу")
                }
                is LibraryObjects.Disk -> {
                    ListDisk[objectId].Acess = ListDisk[objectId].Acess.not()
                    println("Диск с Id = $objectId успешно возвращен на базу")
                }
            }
        } else {
            println("Обьекта с данным Id $objectId не найдено")
        }

    }
}

class initLibrary(private val library: Library) {
    val formatprint = FormatPrint(library)

    fun addBook(title: String, pages: Int, author: String, acess: Boolean) {
        LibraryObjects.Book(Id = library.ListBook.size, Title = title, Pages = pages, Author = author, Acess = acess)
            .also { library.ListBook.add(it) }
    }
    fun addJournal(numIssue: Int, title: String, acess: Boolean) {
        LibraryObjects.Journal(Id = library.ListJournal.size, NumIssue = numIssue, Title = title, Acess = acess)
            .also { library.ListJournal.add(it) }
    }
    fun addDisk(typeDisk: String, title: String, acess: Boolean) {
        LibraryObjects.Disk(Id = library.ListDisk.size, TypeDisk = typeDisk, Title = title, Acess = acess)
            .also { library.ListDisk.add(it) }
    }

    fun showBook(){
        println("Показ книг")
        formatprint.printInfoShort(1)
    }
    fun showJournal() {
        println("Показ газет")
        formatprint.printInfoShort(2)
    }
    fun showDisk() {
        println("Показ дисков")
        formatprint.printInfoShort(3)
    }

}

class Menus(private val library: Library) {
    private val initLibrary = initLibrary(library)
    private val formatPrint = FormatPrint(library)

    fun main_menu() {
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
                    second_menu(1)
                }
                2 -> {
                    initLibrary.showJournal()
                    second_menu(2)
                }
                3 -> {
                    initLibrary.showDisk()
                    second_menu(3)
                }
                4 -> {
                    println("Спасибо за использование!")
                    exitProcess(999)
                }
                else -> println("Неправильный пункт меню")
            }
        }
    }

    fun second_menu(typeId: Int) {
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
                5 -> main_menu()
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
                println(item.formatToStringShort())
            }
        }
    }

    fun printInfoLong(typeId: Int,  objectId: Int) {
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
            println(ListToPrint[objectId].formatToStringLong())
            return
        }
    }

    //if (typeId == 1) "Книга" else if (typeId == 2) "Газета" else "Диск"
    fun Any.formatToStringShort(): String {
        return when (this) {
            is LibraryObjects.Book -> """
                Книга: $Title, с Id = ($Id) доступна: $Acess 
            """.trimIndent()
            is LibraryObjects.Journal -> """
                Газета: $Title, с Id = ($Id) доступна: $Acess 
            """.trimIndent()
            is LibraryObjects.Disk -> """
                Диск: $Title, с Id = ($Id) доступен: $Acess 
            """.trimIndent()
            else -> "Неизвестный тип"
        }
    }

    fun Any.formatToStringLong(): String {
        return when(this) {
            is LibraryObjects.Book  -> """
                Книга: $Title ($Pages стр.) автора: $Author c Id: $Id доступна: $Acess
            """.trimIndent()
            is LibraryObjects.Journal  -> """
                Выпуск: $Title газеты $Title с Id: $Id доступен: $Acess
            """.trimIndent()
            is LibraryObjects.Disk  -> """
                $TypeDisk $Title доступен: $Acess 
            """.trimIndent()
            else -> "Неизвестный тип"
        }
    }
}
