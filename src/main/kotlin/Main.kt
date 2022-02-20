import net.propromp.brainfuckkt.BrainfuckInterpreter
import net.propromp.brainfuckkt.IOPipe

fun main() {
    val mill = System.currentTimeMillis()
    val ioPipe = object : IOPipe {
        override fun read() = System.`in`.read().toByte()

        override fun write(byte: Byte) = print(byte.toInt().toChar())
    }
    val interpreter = BrainfuckInterpreter(
        "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.",
        ioPipe
    )
    interpreter.execute()
    println()
    println("実行時間:${System.currentTimeMillis() - mill}ms")
}