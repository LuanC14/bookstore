

// Part 3 Null Safety

class NullableExamples {
    // A interrogação permite a variável receber um valor nulo
    var name: String? = "Luan";


    fun tests() {
        println(name?.length) // Atribuir '?' chamada de SafePool, vaz validação de nullPointer e permite usar o método length
        println(name!!.length) // Duas exclamações eu indico para o compilador que name jamais sera null

        var size = name?.length
                ?: 0 // '?:' Chamado de Elvis Operator, implicad um valor colateral caso seja nulo o primeiro

    }
}