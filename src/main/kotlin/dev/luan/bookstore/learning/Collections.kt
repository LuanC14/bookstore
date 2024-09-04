// Part 4 Collections
fun main3() {
    var list = listOf<Int>(1, 2, 3, 4, 5, 6) // Lista imutável
    var pares = list.filter { it % 2 == 0 }

    println(pares) // lista filtrada com pares

    println(pares.first()) // Pega o primeiro elemento da lista

    var mutableList = mutableListOf(1, 2, 3, 4, 5)
    mutableList.add(1) // Adiciona 1 no último indice

    mutableList.removeAt(0) // Remove o elemento do indice 0

    mutableList.remove(1) // Remove a primeira ocorrência desse elemento, presente o indice 0, ignorando o 1 que coloquei no último indice

    mutableList[1] = 8 // Sobrescrevendo o valor do indice 1

    mutableList.sort() // Ordena de maneira crescente

    mutableList.shuffle() // Embaralha a lista

    var setList = setOf(1, 2, 3, 1) // criará a lista removendo as ocorrências duplicadas.

    var mapNomeIdade = mutableMapOf<String, Int>("Luan" to 23, "Rafael" to 24) // As chaves são os nomes e o valor após o 'to'.

    mapNomeIdade.put("Igor", 25)
    mapNomeIdade["Igor"] = 25 // Outra maneira de adicionar um valor, no exemplo, ele n ocorrerá pq já foi adicionada acima, pois n pode haver 2 chaves iguais

    mapNomeIdade.putIfAbsent("Igor", 25) // Só adiciona caso ainda não exista uma chave com "Igor"


}