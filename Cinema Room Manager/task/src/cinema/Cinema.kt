package cinema

import java.util.*

fun main() {
    println("Enter the number of rows:")

    val rows = readLine()!!.toInt()

    println("Enter the number of seats in each row:")

    val seats = readLine()!!.toInt()
    val movieHall = Array(rows) { Array(seats) { 'S' } }

    loop@ while (true) {
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("0. Exit")
        when(readLine()!!.toInt()) {
            1 -> printScheme(movieHall)
            2 -> bookTicket(seats,rows, movieHall)
            0 -> break@loop
        }
    }
}
fun bookTicket(seats: Int, rows: Int, movieHall: Array<Array<Char>>) {
    println("\nEnter a row number:")

    val row = readLine()!!.toInt()

    println("Enter a seat number in that row:")

    val seat = readLine()!!.toInt()

    movieHall.reserveSeat(row, seat)

    printPrice(seats, rows, row)
}
fun Array<Array<Char>>.reserveSeat(row: Int, seat: Int) {
    this[row - 1][seat - 1] = 'B'
}

fun printPrice(seats: Int, rows: Int, row: Int) {
    val totalSeats = rows * seats
    val price = if (totalSeats <= 60) 10 else if (row <= rows / 2) 10 else 8

    println("\nTicket price: \$$price")
}

fun printScheme(movieHall: Array<Array<Char>>) {
    println("\nCinema:\n${movieHall[0].indices.joinToString(" ", "  ") { (it + 1).toString() }}")

    for (i in movieHall.indices) println("${i + 1} ${movieHall[i].joinToString(" ")}")
}