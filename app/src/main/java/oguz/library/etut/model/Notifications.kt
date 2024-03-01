package oguz.library.etut.model

import com.google.firebase.Timestamp


data class Notifications (
    val id : String = "",
    val title : String = "",
    val desc : String = "",
    val date : Timestamp = Timestamp.now(),
    val author : String = ""
)