package ronisvonn.gomes.businesscard

import android.app.Application
import ronisvonn.gomes.businesscard.data.AppDatabase
import ronisvonn.gomes.businesscard.data.BusinessCardRepository

class App : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { BusinessCardRepository(database.businessDao()) }
}