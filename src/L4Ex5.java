import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class L4Ex5 {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8");
        capabilities.setCapability("automationName","UiAutomator2");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","/Users/mbpro/Desktop/JavAppiumAutomation/JavaAppiumAuto/JavaAppiumAuto/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub/"), capabilities);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    //Напишем тест L4Ex5
    @Test
    public void safeFirstArticleToMyList ()
    {
        //Ищем кнопку "Skip" и тапаем по ней
        waitForElementForClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Not find button Skip",
                15
        );
        //Ищем поле ввода и тапаем по нему
        waitForElementForClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Не нашли поле поиска",
                5
        );
        //Ищем поле ввода и вводим в него текст
        waitForElementSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot search input",
                15
        );

        //Ищем нашу статью и тапаем по ней
        waitForElementForClick(
                By.xpath("//*[@text='Object-oriented programming language']"),
                "Not find статья",
                15
        );

        //Ждем и проверяем что статья появилась
        waitForElementPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Not find Title in page",
                40
        );

        //Ищем кнопку Save в таббаре
        waitForElementForClick(
                By.id("org.wikipedia:id/page_save"),
                "Cannot find button Save tabbar",
                20
        );
        //Ищем и тапаем по кнопке Add to list
        waitForElementForClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "Cannot finde button Add to  list",
                15
        );

        //Очищаем поле ввода
        waitForElementAndClear(
                By.xpath("//*[@text ='Name of this list']"),
                "Cannot find field Name of this list",
                15
        );

        //Тапаем в поле ввода и вводим заголовок топика "Learning programming"
        waitForElementSendKeys(
                By.xpath("//*[@text ='Name of this list']"),
                "Topic 1",
                "Can not put title Topic 1",
                10
        );

        //Тапаем по кнопке Ок
        waitForElementForClick(
                By.id("android:id/button1"),
                "Can not tap to Ok",
                10
        );

        //Тапаем по кнопке Назад
        waitForElementForClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find field button Up",
                15
        );
        //Заносим в перемменную статью 2
        String name_article2 = "High-level programming language";
        //Ищем нашу статью 2 и тапаем по ней
        waitForElementForClick(
                By.xpath("//*[@text='" + name_article2 + "']"),
                "Not find статья ",
                15
        );

        //Ждем и проверяем что 2 статья появилась
        waitForElementPresent(
                By.xpath("//*[@text='" + name_article2 + "']"),
                "Not find Title High-level programming language",
                40
        );
        //Ищем кнопку Save в таббаре 2 раз
        waitForElementForClick(
                By.id("org.wikipedia:id/page_save"),
                "Cannot find button Save tabbar",
                20
        );


        //Ищем и тапаем по кнопке Add to list 2 раз
        waitForElementForClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "Cannot find button Add to  list",
                15
        );

        //Ищем и тапаем по кнопке +
        waitForElementForClick(
                By.id("org.wikipedia:id/create_button"),
                "Cannot finde button +",
                15
        );

        //Очищаем поле ввода
        waitForElementAndClear(
                By.xpath("//*[@text ='Name of this list']"),
                "Cannot find field Name of this list",
                15
        );

        //Тапаем в поле ввода и вводим заголовок 2 топика
        waitForElementSendKeys(
                By.xpath("//*[@text ='Name of this list']"),
                "Topic 2",
                "Can not put title Topic 2",
                10
        );

        //Тапаем по кнопке Ок
        waitForElementForClick(
                By.id("android:id/button1"),
                "Can not tap to Ok",
                10
        );

        //Тапаем по кнопке "View list"
        waitForElementForClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "Can not fine and tap to View list ",
                15
        );
        //Ждем и проверяем что статья появилась
        waitForElementPresent(
                By.xpath("//*[@text='" + name_article2 + "']"),
                "Not find Title in page",
                40
        );




        //ДЕЛАЕМ СВАЙП ДЛЯ УДАЛЕНИЯ статьи
        swipeElementToLeft(
                By.xpath("//*[@text='" + name_article2 + "']"),
                "Not delete топик"
        );

        //проверяем что статья отсутсвует
        waitForElementNotPresent(
                By.xpath("//*[@text='" + name_article2 + "']"),
                " топик not delete",
                15
        );


       //Тапаем по кнопке Назад 3 раза
        waitForElementForClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find field button Up",
                30
        );
        //Ждем и проверяем что вернуль на экран статьи
        waitForElementPresent(
                By.xpath("//*[@text='" + name_article2 + "']"),
                "Не вернулись на статью",
                40
        );

        waitForElementForClick(
            By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
            "Cannot find field button Up",
            30
    );
        //Ждем и проверяем что вернулись на экран поиска
        waitForElementPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Упали при проверки что вернулись на экран поиска",
                40
        );
        waitForElementForClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find field button Up",
                30
        );

        //Тапаем по кнопке сохраненок в таббаре
        waitForElementForClick(
                By.id("org.wikipedia:id/nav_tab_reading_lists"),
                "Can not find and tap to Save tabbar",
                15
        );


        //проверяем что топик 1 есть
        waitForElementPresent(
                By.xpath("//*[@text='Topic 1']"),
                "Not find топик",
                20
        );

    }


    //______________________________________________________________________________________________
    //Метод для проверки отсутвия элемента на странице
    private boolean waitForElementNotPresent (By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }
    //Метод для проверки что элемент находится на странице
    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String error_message)
    {
        return waitForElementPresent(by, error_message, 15);
    }

    private WebElement waitForElementForClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, 15);
        element.click();
        return element;
    }

    private WebElement waitForElementSendKeys(By by, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, 15);
        element.sendKeys(value);
        return element;
    }
    // метод по очистке поля вводя
    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, 15);
        element.clear();
        return element;

    }
    //Пишем метод для свайпа вверх
    protected void swipeUp(int timeOfSwipe)
    {
        TouchAction action = new TouchAction(driver); //тут надо выбирать TouchAction из Appium, тк может быть еще из Selenium
        //Определим размер экран девайс. Используем Demension из пакета Selenium (driver.manage().window().getSize() - тут получаем параметры экарана
        Dimension size = driver.manage().window().getSize();
        //Далее задаем переменные с координатами по оси х и у
        int x = size.width / 2; // ставим координату в середине экрана
        int start_y = (int) (size.height * 0.8); // точка по у на 80% внизу экрана. Все в скобках для перевода из формата дабл в инт
        int end_y = (int) (size.height * 0.2); // точка по у на 20% сверху экрана

        //Для свайпа нам нужно нажать на экран, подождать и провести пальцем вверх. Команда perform отсылает всю нашу последовательсноть действий на выполнение
        action.press(x,start_y).waitAction(timeOfSwipe).moveTo(x, end_y).release().perform();
    }


    //Пишем метод который будет свайпат страницу быстро, так тут сразу указываем время свайпа с прошлого меитода, проверять что после свайпа на странице нет нужного элемента и свайпать дальше, пока не найдем нужный элемент
    protected void swipeUpQuick()
    {
        //Используем прошлый метод для свайпа страницы
        swipeUp(200);
    }

    //Теперь пишем метод который будет свайпат страницу, проверять что после свайпа на странице нет нужного элемента и свайпать дальше, пока не найдем нужный элемент
    protected void swipeUpToFindeElement (By by, String error_message, int max_swipe)
    {
  /*    Этот код не нужен, так как мы его записали ниже в цикле while
        //Пишем функцию которая ищет все элементы на странице
        driver.findElements(by);
        //Пишем функцию, которая проверяет сколько элементов нашли на странице c помощь findeElement
        driver.findElements(by).size();
        */
        int already_swiped = 0; //в эту переменную будет записывать реальное колличество свайпов
        while (driver.findElements(by).size() == 0) {

            if (already_swiped > max_swipe) {
                waitForElementPresent(by, "Cannnot find element by swipe" + "\n" + error_message, 0);
                return;
            }

            swipeUpQuick();
            ++already_swiped; //++  - плюсуем каждый свайп в переменную
        }
    }

    //Метод для свайпа влево по определенному элемунту
    protected void swipeElementToLeft (By by, String error_message)
    {
        WebElement element =  waitForElementPresent(
                by,
                error_message,
                10);
        int left_x = element.getLocation().getX();
        //Находим правую границу нашего элемента: к левой плюсуем ширину правой границы
        int right_x = left_x + element.getSize().getWidth();
        //ищем верхнуюю координату
        int up_y = element.getLocation().getY();
        int down_y = up_y + element.getSize().getHeight();
        int middle_y = (up_y + down_y) / 2;

        //Теперь инициируем драйвер
        TouchAction action = new TouchAction(driver); //тут надо выбирать TouchAction из Appium, тк может быть еще из Selenium
        action
                .press(right_x, middle_y)
                .waitAction(300)  //Если свайп очень быстро проходит, то можно увеличить вреимя
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }

}

