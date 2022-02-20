package net.propromp.brainfuckkt

class BrainfuckInterpreter(val brainfuck: String, val ioPipe: IOPipe, val arraySize: Int = 30000) {
    val byteArray = ByteArray(30000) { 0 }
    var pointerPos = 0
    var pos = 0
    fun execute() {
        while (pos <= brainfuck.lastIndex) {
            when (brainfuck[pos]) {
                '>' -> incrementPointer()
                '<' -> decrementPointer()
                '+' -> incrementByte()
                '-' -> decrementByte()
                '.' -> stdWrite()
                ',' -> stdRead()
                '[' -> loopStart()
                ']' -> loopEnd()
            }
            pos++
        }
    }

    fun incrementPointer() {
        pointerPos++
    }

    fun decrementPointer() {
        pointerPos--
    }

    fun incrementByte() {
        byteArray[pointerPos]++
    }

    fun decrementByte() {
        byteArray[pointerPos]--
    }

    fun stdWrite() {
        ioPipe.write(byteArray[pointerPos])
    }

    fun stdRead() {
        byteArray[pointerPos] = ioPipe.read()
    }

    fun loopStart() {
        if (byteArray[pointerPos] == (0).toByte()) {
            var i = 0
            var nestedCount = 0
            while (true) {
                i++
                if (brainfuck[pos + i] == '[') {
                    nestedCount++
                    continue
                }
                if (brainfuck[pos + i] == ']') {
                    nestedCount--
                    if (nestedCount < 0) {
                        pos += i
                        break
                    }
                }
            }
        }
    }

    fun loopEnd() {
        if (byteArray[pointerPos] != (0).toByte()) {
            var i = 0
            var nestedCount = 0
            while (true) {
                i--
                if (brainfuck[pos + i] == ']') {
                    nestedCount++
                    continue
                }
                if (brainfuck[pos + i] == '[') {
                    nestedCount--
                    if (nestedCount < 0) {
                        pos += i
                        break
                    }
                }
            }
        }
    }
}