import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainPageTest {
    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://144.76.189.14/");
        mainPage = new MainPage(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void addNewEvent() {
        mainPage.addEvent("Дедлайн", "Все", "Дедлайн для всех");
        String text = mainPage.getName();
        Assert.assertEquals("Дедлайн", text);
    }

    @Test
    public void fastAddEvent() {
        mainPage.fastAddEvent("29 января, встреча");
        String text = mainPage.getName();
        Assert.assertEquals("встреча", text);
    }

    @Test
    public void checkAndNewEditing() {
        mainPage.addEvent("Дедлайн", "Все", "Дедлайн для всех");
        mainPage.checkAndEditing("Дедлайн");
        String text = mainPage.getDescription();
        Assert.assertEquals("Дедлайн", text);

    }

    @Test
    public void search() {
        mainPage.addEvent("Дедлайн", "Все", "Дедлайн для всех");
        mainPage.search("Дедлайн");
        String text = mainPage.getSearchTitle();
        Assert.assertEquals("Дедлайн", text);
    }

    @Test
    public void checkingDaysOfTheWeek() {
        String[] weekDayNames = {"Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресение"};

        List<WebElement> days = mainPage.getFirstLineDays();

        List<String> dayNames = new ArrayList<String>();
        for (WebElement day : days) {
            dayNames.add(extractDayName(day.getText()));
        }

        Assert.assertArrayEquals(weekDayNames, dayNames.toArray());
    }

    private String extractDayName(String nameAndDate) {
        return nameAndDate.substring(0, nameAndDate.indexOf(','));
    }

    @Test
    public void changeCellClassOnRemoveEvent() {
        mainPage.addEvent("Дедлайн", "Все", "Дедлайн для всех");
        mainPage.removeEvent();

        WebElement targetCell = driver.findElement(By.xpath("//*[@id='calendar-content']/tr[5]/td[4]"));
        String classAttribute = targetCell.getAttribute("class");
        Assert.assertFalse(classAttribute.contains("event"));
    }

    @Test
    public void clearCellContentOnRemoveEvent() {
        mainPage.addEvent("Дедлайн", "Все", "Дедлайн для всех");
        mainPage.removeEvent();

        WebElement targetCell = driver.findElement(By.xpath("//*[@id='calendar-content']/tr[5]/td[4]"));

        List<WebElement> childElements = targetCell.findElements(By.xpath("//p"));

        Assert.assertEquals(0, childElements.size());
    }

    @Test
    public void AvailabilityCurrentDay(){
        String current = driver.findElement(By.xpath("//*[@class='current ']")).getAttribute("class");
        Assert.assertEquals("current ",current);

    }

}