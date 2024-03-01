package oguz.library.etut.model

data class Books(
val id : Int,
val title: String,
val Subtitle : String,
val imageName : Int,
val category : String,
var isFavourite : Boolean =false,
val pdf : String
)
