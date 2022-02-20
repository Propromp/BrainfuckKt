package net.propromp.brainfuckkt

interface IOPipe {
    fun read(): Byte
    fun write(byte: Byte)
}