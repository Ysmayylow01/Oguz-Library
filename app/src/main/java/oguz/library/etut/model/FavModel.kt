package oguz.library.etut.model

class FavModel {
    var image = 0
    var title: String? = null
    var key_id: String? = null
    var subtitle : String? = null
    var category : String? = null
    var pdf : String? = null

    constructor() {}
    constructor(image: Int, title: String?, key_id: String?, subtitle: String?, category: String?, pdf: String?) {
        this.image = image
        this.title = title
        this.key_id = key_id
        this.subtitle = subtitle
        this.category = category
        this.pdf = pdf
    }
}