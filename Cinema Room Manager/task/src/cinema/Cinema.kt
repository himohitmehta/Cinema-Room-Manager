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
        println("3. Statistics")
        println("0. Exit")
        when (readLine()!!.toInt()) {
            1 -> printScheme(movieHall)
            2 -> bookTicket(seats, rows, movieHall)
            3 -> showStatistics(seats, rows, movieHall)
            0 -> break@loop
        }
    }
}

//function to show statistics
fun showStatistics(seats: Int, rows: Int, movieHall: Array<Array<Char>>) {
    val totalSeats = ticketCounter(seats, rows)
    val totalIncome = totalSeats * 10
    val bookedSeats = 0
    val percentage = percentageCalculator(bookedSeats, totalSeats)
    println("Number of purchased tickets: 0")
    println("Percentage: $percentage%")
    println("Current income: $0")
    println("Total income: $totalIncome")


}

//function to count tickets
fun ticketCounter(seats: Int, rows: Int): Int {
    return seats * rows
}

//function calculate percentage
fun percentageCalculator(bookedSeats: Int, totalSeats: Int): Double {

    return bookedSeats.toDouble() / totalSeats.toDouble()
}
//function to check if the seat is reserved or not
//if it is reserved , then user cannot purchase the ticket

//function to book ticket
fun bookTicket(seats: Int, rows: Int, movieHall: Array<Array<Char>>): Int {

    var bookedSeats = 0
    var row = 0
    try {
        println("\nEnter a row number:")

        row = readLine()!!.toInt()

        println("Enter a seat number in that row:")

        val seat = readLine()!!.toInt()

        movieHall.reserveSeat(row, seat)
        bookedSeats++
    } catch (e: ArrayIndexOutOfBoundsException) {
        println("Wrong input!")
        bookTicket(seats, rows, movieHall)
    }
    printPrice(seats, rows, row)
    return bookedSeats
}

//function to reserve seat
fun Array<Array<Char>>.reserveSeat(row: Int, seat: Int) {
    this[row - 1][seat - 1] = 'B'
}

//function to print price of ticket
fun printPrice(seats: Int, rows: Int, row: Int) {
    val totalSeats = rows * seats
    val price = if (totalSeats <= 60) 10 else if (row <= rows / 2) 10 else 8

    println("\nTicket price: \$$price")
}

//function to print cinema hall
fun printScheme(movieHall: Array<Array<Char>>) {
    println("\nCinema:\n${movieHall[0].indices.joinToString(" ", "  ") { (it + 1).toString() }}")

    for (i in movieHall.indices) println("${i + 1} ${movieHall[i].joinToString(" ")}")
}