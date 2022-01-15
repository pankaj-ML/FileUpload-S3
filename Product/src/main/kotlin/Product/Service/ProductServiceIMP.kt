package Product.Service

import Product.Model.Product
import Product.ProductRepo.ProductRepo
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductServiceIMP: ProductService
{
    @Autowired
    private lateinit var ProductRepo: ProductRepo
    override fun addProduct(product: Product):Product
    {
        return ProductRepo.save(product)
    }

    override fun updateProduct(id: Long, product: Product): Any?
    {
        return if(ProductRepo.existsById(id)){
            val data =  ProductRepo.findById(id).get()

            val updatedProduct = ProductRepo.save(
                data.apply {
                    name = product.name
                    price = product.price
                    description = product.description
                    images = product.images
                }
            )
            ProductRepo.save(updatedProduct)
        }
        else
        {
            return "Product Not Exist"
        }
    }

    override fun deleteProduct(id: Long): String {
        ProductRepo.deleteById(id)
        return "Product Deleted"
    }

    override fun getAllProduct(): List<Product?>
    {
        return ProductRepo.findAll()
    }

    override fun findbyEmail(email: String): List<Product?>
    {
        return ProductRepo.findbyEmail(email)
    }

    override fun findbyCategory(category: String): List<Product?>
    {
        return ProductRepo.findbyCategory(category)
    }
}