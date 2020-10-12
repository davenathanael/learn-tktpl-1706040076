package id.ac.ui.cs.davenathanael.newspaper

import kotlin.random.Random

class ItemRepository {
    val items: List<Item> = (1..10).map {
        Item(
            "Item #$it",
            Random.nextInt(100),
            Random.nextFloat() * 1000,
            "Description of item #$it. Some dummy text here.")
    }.toList()
}