/*
 * Java 2 Practical - HaNoi Aptech
 */

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;
import model.DBConnection;
import model.Product;
import service.CategoryService;
import service.CategoryServiceImpl;
import service.ProductService;
import service.ProductServiceImpl;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public class Application {

    private final CategoryService categoryService;
    private final ProductService productService;

    public Application(Connection conn) {
        this.categoryService = new CategoryServiceImpl(conn);
        this.productService = new ProductServiceImpl(conn);
    }

    public static void main(String[] args) {
        DBConnection dBConnection = new DBConnection();
        Connection conn = dBConnection.getConnection();
        Application application = new Application(conn);
        Scanner scanner = new Scanner(System.in);
        try {
            do {
                System.out.println("1.\tAdd a new product");
                System.out.println("2.\tEdit a product");
                System.out.println("3.\tSearch product by Name");
                System.out.println("4.\tSearch product by Price");
                System.out.println("5.\tExit");
                System.out.println("Enter choice [5-Exit]: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        application.addNewProduct(scanner);
                        break;
                    case 2:
                        application.updateProduct(scanner);
                        break;
                    case 3:
                        application.searchProductByName(scanner);
                        break;
                    case 4:
                        application.searchProductByPrice(scanner);
                        break;
                    case 5:
                        System.out.println("Bye bye...");
                        conn.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice!");
                }
            } while (true);
        } catch (SQLException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addNewProduct(Scanner scanner) {
        try {
            System.out.println("--- Add New Product ---");
            System.out.println("Enter Name: ");
            String name = scanner.nextLine();
            if (productService.existName(name)) {
                System.out.println("A Product with name " + name + " already exist...");
            } else {
                System.out.println("Enter Quantity: ");
                int qty = scanner.nextInt();
                System.out.println("Enter Price: ");
                double price = scanner.nextDouble();
                System.out.println("Enter Category ID: ");
                int categoryId = scanner.nextInt();
                Category category = categoryService.findById(categoryId);
                if (category == null) {
                    System.out.println("Category with id " + categoryId + " not found");
                } else {
                    Product product = new Product(name, qty, price, category);
                    System.out.println("Do you want to save \"" + name + "\"? [y/N]: ");
                    String choice = scanner.nextLine();
                    if ("y".equalsIgnoreCase(choice)) {
                        if (productService.create(product)) {
                            System.out.println("The product was successfully added");
                        } else {
                            System.out.println("Failed to create product");
                        }
                    } else {
                        System.out.println("You're Canceled");
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateProduct(Scanner scanner) {
        try {
            System.out.println("--- Update a Product ---");
            List<Product> products = productService.findAll();
            if (products.isEmpty()) {
                System.out.println("No data");
            } else {
                for (Product product : products) {
                    System.out.println(product.toString());
                }
                System.out.println("Enter the Product ID you want to update: ");
                int productId = scanner.nextInt();
                scanner.nextLine();
                Product product = productService.findById(productId);
                if (product == null) {
                    System.out.println("Product with id " + productId + " not found");
                } else {
                    System.out.println("Enter Name: ");
                    String name = scanner.nextLine();
                    if (productService.existName(name)) {
                        System.out.println("A Product with name " + name + " already exist...");
                    } else {
                        System.out.println("Enter Quantity: ");
                        int qty = scanner.nextInt();
                        System.out.println("Enter Price: ");
                        double price = scanner.nextDouble();
                        System.out.println("Enter Category ID: ");
                        int categoryId = scanner.nextInt();
                        Category category = categoryService.findById(categoryId);
                        if (category == null) {
                            System.out.println("Category with id " + categoryId + " not found");
                        } else {
                            product.setName(name);
                            product.setQuantity(qty);
                            product.setPrice(price);
                            product.setCategory(category);
                            System.out.println("Do you want to save \"" + name + "\"? [y/N]: ");
                            String choice = scanner.nextLine();
                            if ("y".equalsIgnoreCase(choice)) {
                                if (productService.update(product)) {
                                    System.out.println("The product has been updated successfully");
                                } else {
                                    System.out.println("Failed to create product");
                                }
                            } else {
                                System.out.println("You're Canceled");
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void searchProductByName(Scanner scanner) {
        try {
            System.out.println("--- Search Product by Name ---");
            System.out.println("Enter Name: ");
            String name = scanner.nextLine();
            List<Product> products = productService.findByName(name);
            if (products.isEmpty()) {
                System.out.println("No data");
            } else {
                System.out.println("Found " + products.size() + " item(s)");
                for (Product product : products) {
                    System.out.println(product.toString());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void searchProductByPrice(Scanner scanner) {
        try {
            System.out.println("--- Search Product by Name ---");
            System.out.println("Enter from price: ");
            double fromPrice = scanner.nextDouble();
            System.out.println("Enter to price: ");
            double toPrice = scanner.nextDouble();
            List<Product> products = productService.findByPrice(fromPrice, toPrice);
            if (products.isEmpty()) {
                System.out.println("No data");
            } else {
                System.out.println("Found " + products.size() + " item(s)");
                for (Product product : products) {
                    System.out.println(product.toString());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
