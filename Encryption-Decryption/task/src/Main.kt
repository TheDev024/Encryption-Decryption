package encryptdecrypt

import java.io.File

class EncryptionDecryption {
    fun cmd(mode: String, data: String, inPath: String, outPath: String, key: Int, algorithm: String) {
        val string = if (inPath == "") data else {
            val file = File(inPath)
            file.readText()
        }
        val processed = if (mode == "dec") decrypt(algorithm, string, key) else encrypt(algorithm, string, key)
        if (outPath == "") println(processed) else {
            val file = File(outPath)
            file.writeText(processed)
        }
    }

    private fun encrypt(algorithm: String, string: String, key: Int): String = when (algorithm) {
        // "shift" -> encryptShift(string, key)

        "unicode" -> encryptUnicode(string, key)

        // else -> "Invalid algorithm"

        else -> encryptShift(string, key)
    }

    private fun decrypt(algorithm: String, string: String, key: Int): String = when (algorithm) {
        // "shift" -> decryptShift(string, key)

        "unicode" -> decryptUnicode(string, key)

        // else -> "Invalid algorithm"

        else -> decryptShift(string, key)
    }

    private fun encryptUnicode(string: String, key: Int): String {
        var encrypted = ""
        string.forEach { encrypted += it.plus(key) }
        return encrypted
    }

    private fun decryptUnicode(string: String, key: Int): String {
        var decrypted = ""
        string.forEach { decrypted += it.minus(key) }
        return decrypted
    }

    private fun encryptShift(string: String, key: Int): String {
        var encrypted = ""
        string.forEach { char -> encrypted += if (char.isLetter()) {
            if (char.isLowerCase()) 'a' + (key + (char - 'a')) % 26 else 'A' + (key + (char - 'A')) % 26
        } else char }
        return encrypted
    }

    private fun decryptShift(string: String, key: Int): String {
        var decrypted = ""
        string.forEach { char -> decrypted += if (char.isLetter()) {
            if (char.isLowerCase()) 'a' + (26 - key + (char - 'a')) % 26 else 'A' + (-key + (char - 'A')) % 26
        } else char }
        return decrypted
    }
}

fun main(args: Array<String>) {

    var mode = ""
    var data = ""
    var key = 0
    var inPath = ""
    var outPath = ""
    var algorithm = ""

    for (i in 1..args.lastIndex step 2) when (args[i - 1]) {
        "-mode" -> mode = args[i]

        "-data" -> data = args[i]

        "-key" -> key = args[i].toInt()

        "-in" -> inPath = args[i]

        "-out" -> outPath = args[i]

        "-alg" -> algorithm = args[i]
    }

    val encryptor = EncryptionDecryption()
    encryptor.cmd(mode, data, inPath, outPath, key, algorithm)
}