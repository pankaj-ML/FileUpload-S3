package Product.ProductRepo

import Product.Model.Product
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ProductRepo:MongoRepository<Product,Long>
{
    fun findTopByOrderByIdDesc():Product

    @Query(value="{'email' : ?0}")
    fun findbyEmail(email: String): List<Product?>

    @Query(value="{'category' : ?0}")
    fun findbyCategory(category: String): List<Product?>


}