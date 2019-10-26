// TODO 1
class Cat(private val name: String) {
    var sleep: Boolean = false
        get() {
            println("Fungsi getter dipanggil")
            return field }
        set(value) {
            println("Fungsi setter dipanggil")
            field = value}

    fun toSleep() {
        val isSleep = if (sleep) {
            "sleep!"
        } else {
            "let's play!"
        }
        println("$name, $isSleep")
    }
}

fun main() {

    // TODO 2
    val gippy = Cat("Gippy")

    gippy.toSleep()
    gippy.sleep = true
    gippy.toSleep()
}