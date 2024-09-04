
class MyClass(var nome: String, var idade: Int) {

    // Um tipo de método estático usado para a classe criar a própria instância (Singleton)
    companion object {
        fun createWithDefaultValues(): MyClass {
            return MyClass("Luan", 23)
        }
    }
}

fun main() {
    var myClass = MyClass.createWithDefaultValues();
}