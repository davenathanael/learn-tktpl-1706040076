package id.ac.ui.cs.davenathanael.newspaper

import androidx.lifecycle.ViewModel

class ItemViewModel : ViewModel() {
    private val model = ItemRepository()

    fun getAllItems() = model.items

    fun getItemById(id: Int) = model.items[id]
}