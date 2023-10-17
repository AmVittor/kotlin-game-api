package br.com.gamesapi.util

import br.com.gamesapi.model.User
import java.util.*

class RegisterUser {
    fun createUser(leitura: Scanner): User {
        println("Welcome to Games Api! Let's create your profile. Enter your name:")
        val name = leitura.nextLine()
        println("Enter your email:")
        val email = leitura.nextLine()
        println("Do you want to complete your profile with a username and date of birth? (Y/N)")
        val option = leitura.nextLine()

        return if (option.equals("Y", true)) {
            println("Enter your date of birth (DD/MM/YYYY):")
            val dateOfBirth = leitura.nextLine()
            println("Enter your username:")
            val username = leitura.nextLine()

            User(name, email, dateOfBirth, username)
        } else {
            User(name, email)
        }
    }
}
