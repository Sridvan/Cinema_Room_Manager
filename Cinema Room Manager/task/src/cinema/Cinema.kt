package cinema

import java.lang.IndexOutOfBoundsException

fun main() {
    // write your code here
    var choice = -1
    var purchasedTicket = 0

    var currentIncome = 0


    fun totalSeat(rows: Int, columns: Int): Int {
        return rows * columns
    }

    fun income(rows: Int, columns: Int): Int {
        val totalSeat = rows * columns
        val firstHalf = rows / 2
        val secondHalf = rows - firstHalf
        return if (totalSeat < 60) {
            totalSeat * 10
        } else {
            firstHalf * columns * 10 + secondHalf * columns * 8
        }
    }
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val columns = readln().toInt()


    val totalSeat = totalSeat(rows, columns)
    var totalIncome = income(rows,columns)
    var percentage = "%.2f".format(0.0)


    fun getTicketPrice(totalSeat: Int, rows: Int, row: Int): Int {
        if (totalSeat < 60) {
            return 10
        }
        return if (row > rows / 2) {
            8
        } else 10
    }

    fun cinema(list: MutableList<MutableList<String>>) {
        println("Cinema:")
        for (i in 0..list.size) {
            if (i == 0) {
                print(" ")
            } else {
                print("$i")
            }
            for (j in 1..list[0].size) {
                if (i == 0) {
                    print(" $j")
                } else {
                    print(" ${list[i - 1][j - 1]}")
                }
            }
            println()
        }
    }

    fun buyTicket(totalSeat: Int, list: MutableList<MutableList<String>>){

        while (true) {
            println("Enter a row number:")
            val row = readln().toInt()
            println("Enter a seat number in that row:")
            val column = readln().toInt()
            try {
                if (list[row - 1][column - 1] == "B") {
                    println("That ticket has already been purchased!")
                    continue
                }

                list[row - 1][column - 1] = "B"
                purchasedTicket++
                currentIncome += getTicketPrice(totalSeat, list.size, row)
                println("Ticket price: $${getTicketPrice(totalSeat, list.size, row)}")
                percentage = "%.2f".format((purchasedTicket.toDouble() / totalSeat.toDouble()) * 100)


            } catch (e: IndexOutOfBoundsException) {
                println("Wrong Input!")
                continue
            }
            break
        }

    }





    val list: MutableList<MutableList<String>> = mutableListOf()



    for (i in 0 until rows) {
        list.add(mutableListOf<String>())
        for (j in 0 until columns) {
            list[i].add("S")
        }
    }



    while (choice != 0) {
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")

        choice = readln().toInt()
        when (choice) {
            1 -> cinema(list)
            2 -> buyTicket(totalSeat, list)
            3 -> {
                println("Number of purchased tickets: $purchasedTicket")
                println("Percentage: $percentage%")
                println("Current income: $$currentIncome")
                println("Total income: $$totalIncome")
            }
        }
    }


}


