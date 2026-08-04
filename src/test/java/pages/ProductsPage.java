package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage extends BasePage {
    private static final String ADD_TO_CART = "//*[text()='%s']//ancestor::div" +
            "[@class='inventory_item']//child::*[text()='Add to cart']";
    private final By counter = By.cssSelector(DATA_TEST_PATTERN.formatted("shopping-cart-badge"));
    private final By cartLink = By.cssSelector(DATA_TEST_PATTERN.formatted("shopping-cart-link"));
    private final By addToCartBtn = By.xpath(TEXT_LOCATOR_PATTERN.formatted("Add to cart"));

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void addToCart(final String goodsName) {
        driver.findElement(By.xpath(ADD_TO_CART.formatted(goodsName))).click();
    }

    public void addToCart(int goodsIndex) {
        List<WebElement> buttons = driver.findElements(addToCartBtn);
        if (goodsIndex < 0 || goodsIndex >= buttons.size()) {
            throw new IndexOutOfBoundsException("No 'Add to cart' button at index " + goodsIndex);
        }
        buttons.get(goodsIndex).click();
    }

    public String checkCounterValue() {
        return driver.findElement(counter).getText();
    }

    public String checkCounterColor() {
        return driver.findElement(counter).getCssValue("background-color");
    }

    public void switchToCart() {
        driver.findElement(cartLink).click();
    }
}
