package encryptdecrypt

// import java.util.*

// val scanner = Scanner(System.`in`)

fun main(args: Array<String>) {
    /*val order = scanner.nextLine()
    val message = scanner.nextLine()
    val key = scanner.nextInt()*/

    var mode = ""
    var data = ""
    var key = 0
    var inPath = ""
    var outPath = ""

    for (i in 1..args.lastIndex step 2) when(args[i - 1]) {
        "-mode" -> mode = args[i]

        "-data" -> data = args[i]

        "-key" -> key = args[i].toInt()

        "-in" -> inPath = args[i]

        "-out" -> outPath = args[i]
    }

    val processed = process(mode, data, key)
    println(processed)
}

fun process(order: String, string: String, key: Int): String = when (order) {
    "enc" -> encrypt(string, key)
    "dec" -> decrypt(string, key)
    else -> "Invalid input"
}

fun encrypt(string: String, key: Int): String {
    var encrypted = ""
    string.forEach { encrypted += it.plus(key) }
    return encrypted
}

fun decrypt(string: String, key: Int): String {
    var decrypted = ""
    string.forEach { decrypted += it.minus(key) }
    return decrypted
}