package com.example.numbersfacts.model

data class Responses(
	val number: Int? = null,
	val found: Boolean? = null,
	val text: String? = null,
	val type: String? = null
)


// Data Model for the Response returned from the TMDB Api
data class TmdbMovieResponse(
	val results: List<Responses>
)


