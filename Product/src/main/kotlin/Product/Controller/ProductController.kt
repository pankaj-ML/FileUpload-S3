package Product.Controller

import Product.Model.Product
import Product.ProductRepo.ProductRepo
import Product.Service.AmazonClient
import Product.Service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@CrossOrigin
@RestController
@RequestMapping("/Product")
class ProductController
{
    @Autowired
    private lateinit var amazonClient: AmazonClient

    @Autowired
    private lateinit var productService:ProductService

    @Autowired
    private lateinit var productRepo: ProductRepo

    fun BucketController(amazonClient: AmazonClient?) {
        if (amazonClient != null) {
            this.amazonClient = amazonClient
        }
    }

    @PostMapping("/add")
    fun addProduct(
        @RequestParam("name")  name: String,
        @RequestParam("description")  description: String,
        @RequestParam("category")  category: String,
        @RequestParam("price")  price: Float,
        @RequestParam("images")  file: MultipartFile,
        @RequestParam("email")  email: String,
        @RequestParam("seller")  seller: String,
    ): ResponseEntity<Any?>{
        val product = Product()
        product.name=name
        product.category=category
        product.description=description
        product.price=price
        product.email=email
        product.seller=seller
        product.images= amazonClient.uploadFile(file)
        var ProductID: Long? = null
        try {
            ProductID = (productRepo.findTopByOrderByIdDesc().id!!).toLong()
            product.id = ProductID + 1
        } catch (e: Exception) {
            product.id = (1).toLong()
        }
        return ResponseEntity.ok(productService.addProduct(product))
    }

    @PutMapping("/update/{pid}")
    fun updateProduct(
        @PathVariable pid: Long,
        @RequestParam("name")  name: String,
        @RequestParam("description")  description: String,
        @RequestParam("category")  category: String,
        @RequestParam("price")  price: Float,
        @RequestParam("images")  file: MultipartFile,
        @RequestParam("email")  email: String,
        @RequestParam("seller")  seller: String,
    ): ResponseEntity<Any?>{
        val product = Product()
        product.name=name
        product.category=category
        product.description=description
        product.price=price
        product.email=email
        product.seller=seller
        product.images= amazonClient.uploadFile(file)

        return ResponseEntity.ok(productService.updateProduct(pid,product))
    }

    @DeleteMapping("/Delete/{pid}")
    fun deletProduct(@PathVariable pid: Long): String
    {
        return try {
            val fileUrl = productRepo.findById(pid).get().images
            amazonClient.deleteFileFromS3Bucket(fileUrl!!)
            productService.deleteProduct(pid)
        } catch (e: Exception) {
            "Product Not Exist"
        }
    }

    @GetMapping("/getAll")
    fun getAll(): List<Product?>
    {
        return  productService.getAllProduct()
    }

    @GetMapping("/byEmail/{email}")
    fun findbyEmail(@PathVariable email: String): List<Product?>
    {
       return  productService.findbyEmail(email)

    }
    @GetMapping("/byCategory/{category}")
    fun findbyCategory(@PathVariable category: String): List<Product?>
    {
        return   productService.findbyCategory(category)

    }

}