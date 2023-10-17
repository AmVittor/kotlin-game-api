package br.com.gamesapi.model

data class Game(val title: String, val cover: String) {
    var description: String? = null

    override fun toString(): String {
        return "My Game: \n" +
                "Title: $title \n" +
                "Cover: $cover \n" +
                "Description: $description"
    }
}
