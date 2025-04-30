//class Shop() {
//    val ListBookShop = mutableListOf(Book(123,"Программирование на котлин",600,"Tbank Educational"), Book(52, "test", 600, "Android"))
//    val ListJournalShop = mutableListOf(Journal(678, "Тренды моды", 52, 3))
//    val ListDiskShop = mutableListOf(Disk(99, "Музыка 90-х", "CD"))
//
//    fun <T: LibraryObject> sell(itemList: MutableList<T>):  T? {
//        if  (itemList.isEmpty()) {
//            println("Магазин пуст!")
//            return null
//        }
//        println("Товары в наличии: ")
//        itemList.forEach{ item ->
//            println("${item.title}, Id = ${item.id}")
//        }
//        println("Введите Id товара которого хотите купить")
//        val numBuy = readlnOrNull()?.toIntOrNull()
//        val index = itemList.indexOfFirst { it.id == numBuy }
//        if (index != -1) {
//            val foundItem = itemList.removeAt(index)
//            println("Вы купили товар ${foundItem.title} c Id = ${foundItem.id}")
//            return foundItem
//        } else {
//            println("Не найден обьект с таким Id")
//            return null
//        }
//    }
//
//    fun SellBook() {
//        sell(ListBookShop)
//    }
//    fun SellJournal() {
//        sell(ListJournalShop)
//    }
//    fun SellDisk() {
//        sell(ListDiskShop)
//    }
//
//    fun ShopMenu() {
//        println("Вы вошли в режим менеджера!")
//        println("Выберите в какой магазин вы хотите пойти")
//        val library = Library()
//        val menus = Menus(library)
//        val manager = Shop()
//        while (true) {
//            println(
//                "1. Купить Книгу\n" +
//                        "2. Купить Газету\n" +
//                        "3. Купить Диск\n" +
//                        "4. Назад\n"
//            )
//            when (readlnOrNull()?.toIntOrNull()) {
//                1 -> manager.SellBook()
//                2 -> manager.SellJournal()
//                3 -> manager.SellDisk()
//                4 -> menus.mainMenu()
//                else -> println("Неправильный выбор!")
//            }
//        }
//    }
//
//}
