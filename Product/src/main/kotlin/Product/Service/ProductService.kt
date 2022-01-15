package Product.Service

import Product.Model.Product
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.Query
import java.util.*

interface ProductService
{
    fun addProduct(product: Product): Product
    fun updateProduct(id: Long, product: Product):Any?
    fun deleteProduct(id: Long):String

    fun getAllProduct():List<Product?>
    fun findbyEmail(email: String): List<Product?>
    fun findbyCategory(category: String): List<Product?>

}