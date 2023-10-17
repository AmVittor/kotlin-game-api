package br.com.gamesapi

import br.com.gamesapi.model.Game
import br.com.gamesapi.service.GameApiConsumer
import br.com.gamesapi.util.RegisterUser
import br.com.gamesapi.util.ageTransformer.convertAge
import java.util.Scanner


fun main() {
    val inputScanner = Scanner(System.`in`)
    val userRegistration = RegisterUser()
    val user = userRegistration.createUser(inputScanner)
    println("Registration completed successfully. User data:")
    println(user)
    println("User age: " + user.dateOfBirth?.convertAge())

    do {
        println("Enter a game code to search:")
        val gameCode = inputScanner.nextLine()

        val apiConsumer = GameApiConsumer()
        val gameInfo = apiConsumer.searchGame(gameCode)

        var myGame: Game? = null

        val result = runCatching {
            myGame = Game(gameInfo.info.title, gameInfo.info.thumb)
        }

        result.onFailure {
            println("Game not found. Try another ID.")
        }

        result.onSuccess {
            println("Do you want to add a custom description? Y/N")
            val option = inputScanner.nextLine()
            if (option.equals("Y", true)) {
                println("Enter a custom description for the game:")
                val customDescription = inputScanner.nextLine()
                myGame?.description = customDescription
            } else {
                myGame?.description = myGame?.title
            }

            user.searchedGames.add(myGame)
        }

        println("Do you want to search for another game? Y/N")
        val response = inputScanner.nextLine()

    } while (response.equals("Y", true))

    println("Searched games:")
    println(user.searchedGames)

    println("\n Games sorted by title: ")
    user.searchedGames.sortBy { it?.title }

    user.searchedGames.forEach { println("Title: " + it?.title) }

    val filteredGames = user.searchedGames.filter {
        it?.title?.contains("batman", true) ?: false
    }
    println("\n Filtered games: ")
    println(filteredGames)

    println("Do you want to remove a game from the original list? Y/N")
    val option = inputScanner.nextLine()
    if (option.equals("Y", true)) {
        println(user.searchedGames)
        println("\nEnter the position of the game you want to remove: ")
        val position = inputScanner.nextInt()
        user.searchedGames.removeAt(position)
    }

    println("\n Updated list:")
    println(user.searchedGames)

    println("Search completed successfully.")
}