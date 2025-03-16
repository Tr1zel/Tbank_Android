fun main() {
    val library = Library()
    val menus = Menus(library)


    library.addBook("Гарри Поттер и узник Азкабана", 620, "Джоан Роулинг", true)
    library.addBook("Унесенные ветром", 270, "Маргарет Митчелл", true)
    library.addBook("Зеленая миля", 1000, "Стивен Кинг", true)
    library.addJournal(12345, "Известия Москвы", true)
    library.addJournal(666, "СпидИнфо", true)
    library.addJournal(9999, "Комсомольская правда", true)
    library.addDisk("CD", "Рэмбо 2001", true)
    library.addDisk("DVD", "Контер страйк 1.6", true)
    library.addDisk("DVD", "Сборник фильмов Гарри Поттер", true)

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

    fun addBook(title: String, pages: Int, author: String, acess: Boolean) {
        LibraryObjects.Book(Id = ListBook.size, Title = title, Pages = pages, Author = author, Acess = acess)
            .also { ListBook.add(it) }
    }
    fun addJournal(numIssue: Int, title: String, acess: Boolean) {
        LibraryObjects.Journal(Id = ListJournal.size, NumIssue = numIssue, Title = title, Acess = acess)
            .also { ListJournal.add(it) }
    }
    fun addDisk(typeDisk: String, title: String, acess: Boolean) {
        LibraryObjects.Disk(Id = ListDisk.size, TypeDisk = typeDisk, Title = title, Acess = acess)
            .also { ListDisk.add(it) }
    }

    fun showBook(){
        println("Показ книг")
        printInfoShort(1)

    }
    fun showJournal() {
        println("Показ газет")
        printInfoShort(2)

    }
    fun showDisk() {
        println("Показ дисков")
        printInfoShort(3)

    }

    fun takeHome(typeId: Int) {
        val ListToTake = when (typeId) {
            1 -> ListBook
            2 -> ListJournal
            3 -> ListDisk
            else -> {
                println("Неверный typeID")
                return
            }
        }
        println("Введите ID обьекта который хотите взять домой:")
        val take_ID = readlnOrNull()?.toIntOrNull()

        if (take_ID == null) {
            println("Некоректный ID")
            return
        }
        var foundItem: Any? = null
        for (item in ListToTake) {
            when (typeId) {
                1 -> {  //  Книга
                    if ((item as LibraryObjects.Book).Id == take_ID) {
                        if ((item as LibraryObjects.Book).Acess) {
                            foundItem = item
                            break
                        } else {
                            println("Книги с Id = $take_ID нет в наличии, вы не можете ее взять")
                            return
                            }
                    }
                }
                2 -> { // Газета
                    if ((item as LibraryObjects.Journal).Id == take_ID) {
                        if ((item as LibraryObjects.Journal).Acess) {
                            foundItem = item
                            break
                        } else {
                            println("Газеты с Id = $take_ID нет в наличии, вы не можете ее взять")
                            return
                        }
                    }
                }
                3 -> {// диск
                    if ((item as LibraryObjects.Disk).Id == take_ID) {
                        if ((item as LibraryObjects.Disk).Acess) {
                            foundItem = item
                            break
                        } else {
                            println("Диска с Id = $take_ID нет в наличии, вы не можете ее взять")
                            return
                        }
                    }
                }
            }
        }
        if (foundItem != null) {
            when (foundItem) {
                is LibraryObjects.Book  ->  {
                    ListBook[take_ID].Acess = ListBook[take_ID].Acess.not()
                    println("Книга с Id = $take_ID взяли домой")}
                is LibraryObjects.Journal  -> {
                    ListJournal[take_ID].Acess = ListBook[take_ID].Acess.not()
                    println("Газету с Id = $take_ID взяли домой")
                }
                is LibraryObjects.Disk  -> {
                    ListDisk[take_ID].Acess = ListDisk[take_ID].Acess.not()
                    println("Диск с Id = $take_ID взяли домой")
                }
            }
        } else {
            println("Обьект с ID $take_ID не найден в списке type_id = $typeId.")
        }
    }

    fun returnFromHome(typeId: Int) {
        val listToReturn = when (typeId) {
            1 -> ListBook
            2 -> ListJournal
            3 -> ListDisk
            else -> {
                println("Неправильный ID")
                return
            }
        }

        println("Введите ID обьекта который хотите вернуть из дома:")
        val return_Id = readlnOrNull()?.toIntOrNull()

        if (return_Id  == null) {
            println("Неверный Id")
            return
        }
        var foundItem: Any? = null
        for (item in listToReturn) {
            when (typeId) {
                1 -> {  //  Книга
                    if ((item as LibraryObjects.Book).Id == return_Id) {
                        if (!(item as LibraryObjects.Book).Acess) {
                            foundItem = item
                            break
                        } else {
                            println("Книга с Id = $return_Id в наличии, вы не можете ее вернуть")
                            return
                        }
                    }
                }
                2 -> {  //  Газета
                    if ((item as LibraryObjects.Journal).Id == return_Id) {
                        if (!(item as LibraryObjects.Journal).Acess) {
                            foundItem = item
                            break
                        } else {
                            println("Газета с Id = $return_Id в наличии, вы не можете ее вернуть")
                            return
                        }
                    }
                }
                3 -> {  //  Диск
                    if ((item as LibraryObjects.Disk).Id == return_Id) {
                        if (!(item as LibraryObjects.Disk).Acess) {
                            foundItem = item
                            break
                        } else {
                            println("Диск с Id = $return_Id в наличии, вы не можете ее вернуть")
                            return
                        }
                    }
                }
            }
        }

        if (foundItem != null) {
            when (foundItem) {
                is LibraryObjects.Book -> {
                    ListBook[return_Id].Acess = ListBook[return_Id].Acess.not()
                    println("Книга с Id = $return_Id успешно возвращена на базу")
                }
                is LibraryObjects.Journal -> {
                    ListJournal[return_Id].Acess = ListJournal[return_Id].Acess.not()
                    println("Газета с Id = $return_Id успешно возвращена на базу")
                }
                is LibraryObjects.Disk -> {
                    ListDisk[return_Id].Acess = ListDisk[return_Id].Acess.not()
                    println("Диск с Id = $return_Id успешно возвращен на базу")
                }
            }
        } else {
            println("Обьекта с данным Id $return_Id не найдено")
        }

    }

    // typeId = Id  типа обьекта, 1 = книги, 2 = газеты, 3 = диски
    fun printInfoShort(typeId: Int) {
        val ListToPrint = when (typeId) {
            1 -> ListBook
            2 -> ListJournal
            3 -> ListDisk
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
}


class Menus(private val library: Library) {

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
                    library.showBook()
                    second_menu(1)
                    }
                2 -> {
                    library.showJournal()
                    second_menu(2)
                }
                3 -> {
                    library.showDisk()
                    second_menu(3)
                }
                4 -> {
                    println("Спасибо за использование!")
                    return
                    }
                else -> println("Неправильный пункт меню")
            }
        }
    }

    fun second_menu(typeId: Int) {
        while (true) {
            println("1. Взять домой\n" +
                    "2. Читать в читальном зале\n" +
                    "3. Показать подробную информацию\n" +
                    "4. Вернуть\n" +
                    "5. Назад\n")
            println("Выберите пункт меню:")

            when (readlnOrNull()?.toIntOrNull()) {
                1 -> library.takeHome(typeId)
                2 -> TODO("Читать в зале")
                3 -> TODO("Показать доп информацию")
                4 -> library.returnFromHome(typeId)
                5 -> main_menu()
                else ->  println("Неправильный выбор!")
            }
        }
    }
}
