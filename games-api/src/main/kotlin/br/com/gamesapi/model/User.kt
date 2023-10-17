package br.com.gamesapi.model

import br.com.gamesapi.util.emailValidator.EmailValidate
import kotlin.random.Random

data class User(var name: String, var email: String) {
 private val emailValidate = EmailValidate()
    var dateOfBirth: String? = null
    private var username: String? = null
        set(value) {
            field = value
            if (internalId.isNullOrBlank()) {
                createInternalId()
            }
        }
    private var internalId: String? = null
    val searchedGames = mutableListOf<Game?>()

    constructor(name: String, email: String, dateOfBirth: String, username: String) :
            this(name, email) {
        this.dateOfBirth = dateOfBirth
        this.username = username
        createInternalId()
    }

    init {
        if (name.isNullOrBlank()) {
            throw IllegalArgumentException("Name is blank")
        }
        this.email = emailValidate.validateEmail(email)
    }

    override fun toString(): String {
        return "User(name='$name', email='$email', dateOfBirth=$dateOfBirth, username=$username, internalId=$internalId)"
    }

    private fun createInternalId() {
        val number = Random.nextInt(10000)
        val tag = String.format("%04d", number)

        internalId = "$username#$tag"
    }
}
