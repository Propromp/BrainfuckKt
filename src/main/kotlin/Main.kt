import net.propromp.brainfuckkt.BrainfuckInterpreter
import net.propromp.brainfuckkt.IOPipe

fun main() {
    val ioPipe = object : IOPipe {
        override fun read() = System.`in`.read().toByte()

        override fun write(byte: Byte) = print(byte.toInt().toChar())
    }
    val mill = System.currentTimeMillis()
    val interpreter = BrainfuckInterpreter(
        """
           ++++[>+++++<-]>[<+++++>-]+<+[>[>+>+<<-]++>>[<<+>>-]>>>[-]++>[-]+
>>>+[[-]++++++>>>]<<<[[<++++++++<++>>-]+<.<[>----<-]<]
<<[>>>>>[>>>[-]+++++++++<[>-<-]+++++++++>[-[<->-]+[<<<]]<[>+<-]>]<<-]<<-] 
        """.trimIndent(),
        ioPipe
    )
    interpreter.execute()
    println()
    println("実行時間:${System.currentTimeMillis() - mill}ms")
}