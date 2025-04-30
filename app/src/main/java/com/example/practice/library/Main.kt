//import kotlin.system.exitProcess
//
//fun main() {
//    val library = Library()
//    val initlibrary =  InitLibrary(library)
//    val menus = Menus(library)
//
//    initlibrary.addBook("Гарри Поттер и узник Азкабана", 620, "Джоан Роулинг")
//    initlibrary.addBook("Унесенные ветром", 270, "Маргарет Митчелл")
//    initlibrary.addBook("Зеленая миля", 1000, "Стивен Кинг")
//    initlibrary.addJournal(12345, "Известия Москвы", 1)
//    initlibrary.addJournal(666, "СпидИнфо", 11)
//    initlibrary.addJournal(9999, "Комсомольская правда", 5)
//    initlibrary.addDisk("CD", "Рэмбо 2001")
//    initlibrary.addDisk("DVD", "Контер страйк 1.6")
//    initlibrary.addDisk("DVD", "Сборник фильмов Гарри Поттер")
//
//    menus.mainMenu()
//}
//
//
//class Menus(private val library: Library) {
//    private val initLibrary = InitLibrary(library)
//    private val formatPrint = FormatPrint(library)
//    private val magazin = Shop()
//    private val digitization = Digitization(library)
//
//    private inline fun <reified T> filtered(list: List<Library>): List<T> {
//        return list.filter { it is T }.map { it as T }
//    }
//
//    fun mainMenu() {
//        while (true) {
//            println(
//                "1. показать книги\n" +
//                        "2. Показать газеты\n" +
//                        "3. Показать диски\n" +
//                        "4. Пойти в магазин\n" +
//                        "5. Кабинет Оцифровки\n" +
//                        "6. Выйти"
//            )
//            println("Выберите пункт меню:")
//
//            when (readlnOrNull()?.toIntOrNull()) {
//                1 -> {
//                    initLibrary.showBook()
//                    secondMenu(1)
//                }
//
//                2 -> {
//                    initLibrary.showJournal()
//                    secondMenu(2)
//                }
//
//                3 -> {
//                    initLibrary.showDisk()
//                    secondMenu(3)
//                }
//
//                4 -> {
//                    magazin.ShopMenu()
//                }
//                5 -> {
//                    digitization.digitizationMenu()
//                }
//                6 -> {
//                    println("Спасибо за использование!")
//                    exitProcess(999)
//                }
//
//                else -> println("Неправильный пункт меню")
//            }
//        }
//    }
//
//    fun secondMenu(typeId: Int) {
//        println("Введите Id обьекта с которым хотите работать! (или -1 для отмены)")
//        val Object_ID = readlnOrNull()?.toIntOrNull()
//        if (Object_ID == null) {
//            println("Id не может быть равным нулю")
//            return
//        }
//        if (Object_ID == -1) return
//        while (true) {
//            println(
//                "1. Взять домой\n" +
//                        "2. Читать в читальном зале\n" +
//                        "3. Показать подробную информацию\n" +
//                        "4. Вернуть\n" +
//                        "5. Назад\n"
//            )
//            println("Выберите пункт меню:")
//
//            when (readlnOrNull()?.toIntOrNull()) {
//                1 -> library.takeHome(typeId, Object_ID)
//                2 -> library.readInLibrary(typeId, Object_ID)
//                3 -> formatPrint.printInfoLong(typeId, Object_ID)
//                4 -> library.returnFromHome(typeId, Object_ID)
//                5 -> mainMenu()
//                else -> println("Неправильный выбор!")
//            }
//        }
//    }
//
//
//}
//
//class FormatPrint(private val library: Library) {
//    // typeId = Id  типа обьекта, 1 = книги, 2 = газеты, 3 = диски
//    fun printInfoShort(typeId: Int) {
//        val ListToPrint = when (typeId) {
//            1 -> library.ListBook
//            2 -> library.ListJournal
//            3 -> library.ListDisk
//            else -> {
//                println("Неверный typeID")
//                return
//            }
//        }
//        if (ListToPrint.isEmpty()) {
//            println("Список пуст!")
//        } else {
//            ListToPrint.forEach { item ->
//                println(item.getShortDescription())
//            }
//        }
//    }
//
//    fun printInfoLong(typeId: Int, objectId: Int) {
//        val ListToPrint = when (typeId) {
//            1 -> library.ListBook
//            2 -> library.ListJournal
//            3 -> library.ListDisk
//            else -> {
//                println("Неверный typeID")
//                return
//            }
//        }
//        if (ListToPrint.isEmpty()) {
//            println("Список пуст!")
//        } else {
//            println(ListToPrint[objectId].getLongDescription())
//            return
//        }
//    }
//}
//
//class Digitization(private val library: Library) {
//
//    fun digitizationMenu() {
//        val menus = Menus(library)
//        println("Вы вошли в кабинет оцифровки")
//        println("Выберите что вы хотите оцифровать:")
//
//        while (true) {
//            println(
//                "1. Оцифровать Книгу\n" +
//                "2. Оцифровать Газету\n" +
//                "3. Оцифровать Диск\n" +
//                "4. Назад\n"
//            )
//            when (readlnOrNull()?.toIntOrNull()) {
//                1 -> Digit(library.ListBook)
//                2 -> Digit(library.ListJournal)
//                3 -> {
//                    println("Диск нельзя оцифровать!")
//                    return
//                }
//
//                4 -> menus.mainMenu()
//                else -> println("Неправильный выбор!")
//            }
//        }
//    }
//
//    fun <T : LibraryObject> Digit(itemList: MutableList<T>) {
//        if  (itemList.isEmpty()) {
//            println("Магазин пуст!")
//            return
//        }
//        println("Товары в наличии:")
//        itemList.forEach { item ->
//            println("${item.title}, Id = ${item.id}")
//        }
//        println("Введите Id обьекта который хотите оцифровать:")
//        val numDigit = readlnOrNull()?.toIntOrNull()
//        val foundItem = itemList.find { it.id == numDigit }
//        itemList.removeAt(itemList.indexOfFirst { it.id == numDigit })
//        if (foundItem != null) {
//            library.ListDisk.add(Disk(library.ListDisk.size, foundItem.title, "CD"))
//            println("Вы успешно оцифровали ${foundItem.title} на диск CD")
//        }
//    }
//}
