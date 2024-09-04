package dev.luan.bookstore.learning


// Part 1, class, constructors, concat strings, variables and functions

// Generic class for example
class ExampleClassAsParamter {
    fun randomFunction(name: String, age: Number) {}
}

// Example class with sample constructor
// attribute pv if without var or val, is not accessable
// parameterClass variable to show that is possible pass objects as parameter.
class Learning(var school: String, var theme: String, pv: String, val parameterClass: ExampleClassAsParamter) {

    // Lateinit is key to indicate who the property will receive value later
    lateinit var myName: String;

    // Example concat variables
    fun concatVariables(): String {
        // Val is imutable variable
        val a = "A"
        val b = "B"

        return "First value concatenated: ${a}, Second value concatenated: ${b}"
    }

    // Unit is type used when function not has return. She's not literally Void, but one feature of NullSafety...
    fun thisFunctionReturnVoid(): Unit {
        println("Hello World")

        // Is possible pass thes parameter out of order
        parameterClass.randomFunction(age = 3, name = "Luan")
    }
}

fun main() {
    val learning = Learning("Udemy", "Kotlin", "private attribute", ExampleClassAsParamter());
    println(learning.concatVariables())
    println(learning.thisFunctionReturnVoid())
}



