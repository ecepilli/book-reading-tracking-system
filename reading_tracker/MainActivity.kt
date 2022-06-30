package com.example.reading_tracker

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setTitle("Reading Tracker")
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val builder = AppBarConfiguration.Builder(navController.graph)
        builder.setOpenableLayout(drawer)
        val appBarConfiguration = builder.build()
        toolbar.setupWithNavController(navController, appBarConfiguration)
        val navView = findViewById<NavigationView>(R.id.nav_view)
        NavigationUI.setupWithNavController(navView, navController)
        val dao = NewLibraryDatabase.getInstance(this).bookDao




        val books = listOf(
            Books(1,
                "The Catcher in the Rye",
                "J. D. Salinger",
                "https://1k-cdn.com/resimler/kitaplar/1042268_62b27_1607624282.jpg"),
            Books(2, "Lock Every Door", "Riley Sager","https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1540938359i/41837243.jpg"),
            Books(3, "This Is Our Story", "Ashley Elston","https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1452621783i/23341252.jpg"),
            Books(4, "Aeneid", "Virgil","https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1386923968i/12914.jpg"),
            Books(5, "Iliad and the Odyssey", "Homer","https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1172304667i/162806.jpg"),
            Books(6, "One Plus One", "Jojo Moyes","https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1413746904i/23359964.jpg"),
            Books(7, "We Hunt the Flame", "Hafsah Faizal","https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1551185822i/36492488.jpg"),
            Books(8,
                "Professor Spindlebrock's Little Blue Book of Traveling Spells",
                "Joseph D Lyman","https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1608754782i/56392536.jpg"),
            Books(9, "At the Mercy of Monsters", "Sophy Ryser","https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1642562110i/60158010.jpg"),
            Books(10, "Precarious Night", "Sophy L Ryser","https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1553542081i/44591124.jpg"),
            Books(11, "His & Hers", "Alice Feeney","https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1595773166i/45885495.jpg"),
            Books(12, "The Spanish Love Deception", "Elena Armas","https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1610900883i/54189398.jpg"),
            Books(13, "Rule of Wolves", "Leigh Bardugo","https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1598133584i/36307674.jpg"),
            Books(14, "These Violent Delights", "Chloe Gong","https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1580958058i/50892212.jpg"),
            Books(15, "Our Violent Ends", "Chloe Gong","https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1612451534i/44084762.jpg"),
            Books(16, "Namesake", "Adrienne Young","https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1590354154i/53138025.jpg"),
            Books(17,"We Free the Stars", "Hafsah Faizal","https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1570454006i/46223364.jpg")
        )
        lifecycleScope.launch {
            books.forEach { dao.insert(it) }
        }




    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return item.onNavDestinationSelected(navController)
                || super.onOptionsItemSelected(item)
    }
}