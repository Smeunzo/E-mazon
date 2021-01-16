package com.emazon.services.inventory;

import com.emazon.services.inventory.entity.Category;
import com.emazon.services.inventory.entity.Product;
import com.emazon.services.inventory.service.CategoryService;
import com.emazon.services.inventory.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import java.util.ArrayList;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)// authorise prepost annotation
public class InventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductService productService, CategoryService categoryService){
		return args -> {

			Category phones = new Category(null,"phones",new ArrayList<>(),"catégory des telephones");
			Category accessoires = new Category(null,"accessoires",new ArrayList<>(),"accessoires pour telephones");
			Category menuiserie = new Category(null,"menuiserie",new ArrayList<>(),"mains d'oeuvres ");
			Category voitures = new Category(null,"voitures",new ArrayList<>(),"voitures pas cheres");
			Category food = new Category(null,"food",new ArrayList<>(),"de la nourritures");
			categoryService.addCategory(phones);
			categoryService.addCategory(accessoires);
			categoryService.addCategory(menuiserie);
			categoryService.addCategory(voitures);
			categoryService.addCategory(food);

			Product product1 = new Product(1000,"iphone",50,"description","https://media.ldlc.com/r374/ld/products/00/05/73/56/LD0005735694_1.jpg");
			Product product2 = new Product(1000,"samsung",50,"description","https://images.samsung.com/is/image/samsung/fr-galaxy-s20-5g-g981-sm-g981bzadeub-frontcosmicgray-thumb-225953140?$160_160_PNG$");
			Product product3 = new Product(10000,"huawei",20,"description","https://dyw7ncnq1en5l.cloudfront.net/optim/produits/450/50875/huawei-p30-pro_656b316c983f9085__450_400.webp");
			Product product4 = new Product(1020,"xiaomi",2,"description","https://c1.lestechnophiles.com/images.frandroid.com/wp-content/uploads/2020/02/xiaomi-mi-10-pro-frandroid-2020.png?resize=580,580");
			Product product5 = new Product(1,"wiko",3,"description","https://static.fnac-static.com/multimedia/Images/FR/MDM/4a/9b/ea/15375178/1520-2/tsp20201007131238/Smartphone-Wiko-View5-6-55-Double-SIM-64-Go-Rose.jpg");
			Product product6 = new Product(100,"sony",80,"description","https://static.fnac-static.com/multimedia/Images/FR/MDM/56/78/e4/14973014/1540-1/tsp20201120191355/Smartphone-Sony-Xperia-L4-64Go-Double-SIM-Noir.jpg");
			Product product7 = new Product(100,"oneplus",500,"description","https://images.fr.shopping.rakuten.com/photo/oneplus-8t-5g-128-go-vert-1528512658_ML.jpg");
			Product charger = new Product(100,"chargeur",50,"chargeur pour telephone","https://media.gsm55.com/catalog/product/cache/1/image/450x450/9df78eab33525d08d6e5fb8d27136e95/p/a/pack-apl-ip5-r-v2.jpg");
			Product memoire = new Product(100,"carte mémoire",50,"carte micro sd","https://cdn.shopify.com/s/files/1/0002/8445/9071/products/product-image-884360182_600x.jpg?v=1570537904");

			productService.addNewProduct(product1);
			productService.addNewProduct(product2);
			productService.addNewProduct(product3);
			productService.addNewProduct(product4);
			productService.addNewProduct(product5);
			productService.addNewProduct(product6);
			productService.addNewProduct(product7);
			productService.addNewProduct(charger);
			productService.addNewProduct(memoire);

			categoryService.linkProductsToCategory(phones,product1,product2,product3,product4,product5,product6,product7);
			categoryService.linkProductsToCategory(accessoires,charger,memoire);

		};
	}

}
