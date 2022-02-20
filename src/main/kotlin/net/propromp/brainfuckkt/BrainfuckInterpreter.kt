package net.propromp.brainfuckkt

class BrainfuckInterpreter(val brainfuck:String, val ioPipe: IOPipe, val arraySize: Int = 30000) {
    val byteArray = ByteArray(30000) {0}
    var pointerPos = 0
    var pos = 0
    fun execute() {
        while(pos<brainfuck.lastIndex) {
            when(brainfuck[pos]) {
                '>' -> incrementPointer()
                '<' -> decrementPointer()
                '+' -> incrementByte()
                '-' -> decrementByte()
                '.' -> stdWrite()
                ',' -> stdRead()
                '[' -> loopStart()
                ']' -> loopEnd()
            }
        }
    }
    fun incrementPointer() {
        pointerPos++
        pos++
    }
    fun decrementPointer() {
        pointerPos--
        pos++
    }
    fun incrementByte() {
        byteArray[pointerPos]++
        pos++
    }
    fun decrementByte() {
        byteArray[pointerPos]--
        pos++
    }
    fun stdWrite() {
        ioPipe.write(byteArray[pointerPos])
        pos++
    }
    fun stdRead() {
        byteArray[pointerPos] = ioPipe.read()
        pos++
    }
    fun loopStart() {
        if(byteArray[pointerPos]==(0).toByte()) {
            var i = 0
            while(true) {
                if(brainfuck[pos+i] == ']') {
                    pos += i + 1
                }
                i++
            }
        } else {
            pos ++
        }
    }
    fun loopEnd() {
        if(byteArray[pointerPos]!=(0).toByte()) {
            var i = 0
            while(true) {
                if(brainfuck[pos+i] == '[') {
                    pos += i + 1
                    break
                }
                i--
            }
        } else {
            pos++
        }
    }
}