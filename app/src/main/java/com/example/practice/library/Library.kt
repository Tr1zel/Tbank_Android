//class Library {
//    val ListBook: MutableList<Book> = mutableListOf()
//    val ListJournal: MutableList<Journal> = mutableListOf()
//    val ListDisk: MutableList<Disk> = mutableListOf()
//
//    fun takeHome(typeId: Int, objectId: Int) {
//        val ListToTake = when (typeId) {
//            1 -> ListBook
//            2 -> {
//                println("Газету нельзя взять домой!")
//                return
//            }
//            3 -> ListDisk
//            else -> {
//                println("Неверный typeID")
//                return
//            }
//        }
//
//        val foundItem = ListToTake.find { it.id == objectId }
//        if (foundItem == null) {
//            println("Обьекта с Id = $objectId не найден")
//            return
//        }
//        if (!foundItem.access) {
//            println("Обьект с Id = $objectId недоступен")
//            return
//        }
//        foundItem.access = false
//        println("Обьект с Id = $objectId взят домой")
//    }
//
//    fun readInLibrary(typeId: Int, objectId: Int) {
//        val ListToTake = when (typeId) {
//            1 -> ListBook
//            2 -> ListJournal
//            3 -> {
//                println("Диски нельзя читать в читальном зале!")
//                return
//            }
//            else -> {
//                println("Неверный typeID")
//                return
//            }
//        }
//
//        val foundItem = ListToTake.find { it.id == objectId }
//        if (foundItem == null) {
//            println("Обьект с Id = $objectId не найден")
//            return
//        }
//        if (!foundItem.access) {
//            println("Обьект с Id = $objectId недоступен в данный момент")
//            return
//        }
//        foundItem.access = false
//        println("Обьект с Id = $objectId успешно взят в читальный зал")
//    }
//
//    fun returnFromHome(typeId: Int, objectId: Int) {
//        val listToReturn = when (typeId) {
//            1 -> ListBook
//            2 -> ListJournal
//            3 -> ListDisk
//            else -> {
//                println("Неправильный ID")
//                return
//            }
//        }
//
//        val foundItem = listToReturn.find { it.id == objectId }
//        if (foundItem == null) {
//            println("Обьект с Id = $objectId не найден")
//            return
//        }
//        if (foundItem.access) {
//            println("Обьект с Id = $objectId в наличии")
//            return
//        }
//
//        foundItem.access = true
//        println("Обьект с Id = $objectId успешно возвращен на базу")
//    }
//}
//
//class InitLibrary(private val library: Library) {
//    val formatprint = FormatPrint(library)
//
//    fun addBook(title: String, pages: Int, author: String) {
//        Book(id = library.ListBook.size, title = title, pages = pages, author = author)
//            .also { library.ListBook.add(it) }
//    }
//    fun addJournal(numIssue: Int, title: String, numMonthJournal: Int) {
//        Journal(id = library.ListJournal.size, numIssue = numIssue, title = title, numMonthIssue = numMonthJournal)
//            .also { library.ListJournal.add(it) }
//    }
//    fun addDisk(typeDisk: String, title: String) {
//        Disk(id = library.ListDisk.size, typeDisk = typeDisk, title = title)
//            .also { library.ListDisk.add(it) }
//    }
//
//    fun showBook(){ formatprint.printInfoShort(1) }
//    fun showJournal() { formatprint.printInfoShort(2) }
//    fun showDisk() { formatprint.printInfoShort(3) }
//}
