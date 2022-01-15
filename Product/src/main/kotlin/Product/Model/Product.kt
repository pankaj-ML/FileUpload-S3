package Product.Model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.constraints.NotBlank

@Document("product")
data class Product
    (
    @Id
    var id:Long?=null,
    @field:NotBlank(message = "Name field is mandatory")
    var name:String?=null,
    @field:NotBlank(message = "Description field is mandatory")
    var description:String?=null,
    @field:NotBlank(message = "Category field is mandatory")
    var category: String?=null,
    @field:NotBlank(message = "Price field is mandatory")
    var price:Float?=null,
    @field:NotBlank(message = "Image field is mandatory")
    var images:String?=null,
    @field:NotBlank(message = "Seller field is mandatory")
    var seller: String?=null,
    @field:NotBlank(message = "Email field is mandatory")
    var email: String?=null
)