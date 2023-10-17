package br.com.gamesapi.util.emailValidator

class EmailValidate  {
     fun validateEmail(email: String): String {
        val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        if (regex.matches(email)) {
            return email
        } else {
            throw IllegalArgumentException("Invalid email")
        }
    }
}