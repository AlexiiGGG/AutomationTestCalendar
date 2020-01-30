import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainPage {
    private WebDriver driver;

    public MainPage (WebDriver driver) {
        this.driver = driver;
    }

    private By fastAddButton = By.xpath("//button[text()='Добавить']");
    private By eventDateTitle = By.xpath("//*[@name='eventDateTitle']");
    private By createButton = By.xpath("//button[text()='Создать']");
    private By searchField = By.xpath("//*[@id='calendar-search']");
    private By deadline = By.xpath("//*[@id='calendar-content']/tr[5]/td[4]");
    private By eventName = By.xpath("//*[@name='eventName']");
    private By eventMambers = By.xpath("//*[@name='eventMambers']");
    private By eventDescription = By.xpath("//*[@name='eventDescription']");
    private By doneButton = By.xpath("//button[text()='Готово']");
    private By name = By.xpath("//*[@class='event-name']");
    private By searchTitle = By.xpath("//*[@class='title']");
    private By foobar = By.xpath("//*[@id='calendar-content']/tr[1]/*");
    private By removeEventButton = By.xpath("//*[@data-event='calendar-remove-event']");

    public MainPage addEvent (String name, String mambers, String description) {
        driver.findElement(deadline).click();
        driver.findElement(eventName).sendKeys(name);
        driver.findElement(eventMambers).sendKeys(mambers);
        driver.findElement(eventDescription).sendKeys(description);
        driver.findElement(doneButton).click();
        return this;
    }

    public MainPage fastAddEvent (String qwe) {
        driver.findElement(fastAddButton).click();
        driver.findElement(eventDateTitle).sendKeys(qwe);
        driver.findElement(createButton).click();
        return this;
    }

    public MainPage checkAndEditing (String description) {
        driver.findElement(deadline).click();
        driver.findElement(eventDescription).click();
        driver.findElement(eventDescription).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        driver.findElement(eventDescription).sendKeys(description);
        driver.findElement(doneButton).click();
        driver.findElement(deadline).click();
        return this;
    }

    public MainPage search (String search) {
        driver.findElement(searchField).sendKeys(search);
        return this;
    }

    public String getName () {
        return driver.findElement(name).getText();

    }

    public MainPage removeEvent () {
        driver.findElement(deadline).click();
        driver.findElement(removeEventButton).click();
        return this;
    }

    public String getSearchTitle (){
        return driver.findElement(searchTitle).getText();
    }
    public String getDescription (){
        return driver.findElement(eventDescription).getText();
    }
    public List<WebElement> getFirstLineDays() {
        return driver.findElements(foobar);
    }

}
