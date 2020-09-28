package id.ac.ui.cs.davenathanael.newspaper

data class HelloCounter(var count: Int = 0) {
    fun increment(handle:(c: Int) -> Unit) {
        count += 1

        if (count >= 1) handle(count)
    }
}