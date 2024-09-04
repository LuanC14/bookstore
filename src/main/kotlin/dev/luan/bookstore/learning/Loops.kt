// Parte 2: Estruturas de repetição
fun exampleLoop() {

    // Crescent loop
    for(num in 1 .. 10) {
        println(num)
    }

    // Decrescent loop
    for(num in 10 downTo 1) {
        println(num)
    }

    // two-step interaction
    for(num in 2 .. 10 step 2) {
        println(num)
    }

    // When condition
    var x = 5

    when(x) {
        1 -> print("X equals 1")
        3 -> print("X equals 2")
        in 3 .. 10 -> println("X is between 3 e 11")
        11 -> {
            println("X equals 11")
            println("X bigger than 10")
        }
        12 -> x.plus(1)
//        is Float -> x.plus(1L)
        else -> "Unmapped number"
    }

}

fun main2() {
    exampleLoop()
}