package br.com.gamesapi.util.ageTransformer

import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

fun String.convertAge(): Int {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    var age = 0
    age = Period.between(LocalDate.parse(this, formatter), LocalDate.now()).years

    return age
}