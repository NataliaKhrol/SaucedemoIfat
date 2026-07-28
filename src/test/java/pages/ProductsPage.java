package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {
    private static final String ADD_TO_CART = "//*[text()='%s']//ancestor::div" +
            "[@class='inventory_item']//child::*[text()='Add to cart']";
    private final By pageName = By.cssSelector("[data-test='title']");
    private final By counter = By.cssSelector("[data-test='shopping-cart-badge']");
    private final By addToCartBnt = By.xpath("//*[text()='Add to cart']");

    //String.format
    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getNamePage() {
        return driver.findElement(pageName).getText();
    }

    public void addToCart(final String goodsName) {
        //  By goods = By.xpath(String.format(ADD_TO_CART, goodsName));
        By goods = By.xpath(ADD_TO_CART.formatted(goodsName));
        driver.findElement(goods).click();
    }

    public void addToCart(int goodsIndex) {
        driver.findElements(addToCartBnt).get(goodsIndex).click();
    }

    public String checkCounterValue() {
        return driver.findElement(counter).getText();
    }

    public String checkCounterColor() {
        return driver.findElement(counter).getCssValue("background-color");
    }
}
