package br.com.gamesapi.service

import br.com.gamesapi.model.InfoGame
import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class GameApiConsumer {
    private val gson = Gson()

    fun searchGame(id: String): InfoGame {
        val apiUrl = "https://www.cheapshark.com/api/1.0/games?id=$id"

        val client: HttpClient = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder().uri(URI.create(apiUrl)).build()
        val response = client.send(request, HttpResponse.BodyHandlers.ofString())

        val json = response.body()
        return gson.fromJson(json, InfoGame::class.java)
    }

}