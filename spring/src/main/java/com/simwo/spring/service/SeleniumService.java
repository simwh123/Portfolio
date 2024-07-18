package com.simwo.spring.service;

import org.springframework.stereotype.Service;
import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

@Service
public class SeleniumService {
    public static String check(String world, String item_Name, String option_1, String option_2, String option_3) {
        if (world.equals("유니온")) {
            world = "scania";
        }

        System.setProperty("webdriver.chrome.driver", "C:/DEV/chromedriver-win64/chromedriver.exe");

        // Chrome 옵션 설정 (필요에 따라 추가 설정 가능)
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // 브라우저 창을 최대화하여 열기
        options.addArguments("--headless");

        // WebDriver 인스턴스 생성
        WebDriver driver = new ChromeDriver(options);

        // 웹 페이지로 이동
        driver.get("https://xn--hz2b1j494a9mhnwh.com/union?bo_table=" + world
                + "&sop=and&sst=once_price&sod=asc&sfl=&stx=&sca=&page=1&search_type=armor&search_type=armor#tab-armor");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // 검색 필터 설정
        WebElement itemName = driver.findElement(By.id("armor_name_input"));
        itemName.sendKeys(item_Name);

        WebElement searchInput1 = driver.findElement(By.id("armor_detail_option_input1"));
        searchInput1.sendKeys(option_1);

        WebElement searchInput2 = driver.findElement(By.id("armor_detail_option_input2"));
        searchInput2.sendKeys(option_2);
        WebElement searchInput3 = driver.findElement(By.id("armor_detail_option_input3"));
        searchInput3.sendKeys(option_3);

        Select select1 = new Select(driver.findElement(By.id("armor_detail_option_select1")));
        select1.selectByValue("35");

        Select select2 = new Select(driver.findElement(By.id("armor_detail_option_select2")));
        select2.selectByValue("35");

        Select select3 = new Select(driver.findElement(By.id("armor_detail_option_select3")));
        select3.selectByValue("35");

        // 검색 버튼 클릭
        List<WebElement> buttons = driver.findElements(By.xpath("//button[@type='submit' and @role='button']"));
        WebElement searchButton = buttons.get(0);
        searchButton.click();

        /*
         * WebElement searchButton = wait
         * .until(ExpectedConditions.elementToBeClickable(By.xpath(
         * "//button[text()='검색']")));
         * ((JavascriptExecutor) driver)
         * .executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});"
         * , searchButton);
         * searchButton.click();
         */

        WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("auction-list")));
        List<WebElement> itemList = table.findElements(By.tagName("li"));
        if (!itemList.isEmpty() && itemList.size() > 0) {
            String fullText = itemList.get(0).getText();

            int startIndex = fullText.indexOf("가격");
            int endIndex = fullText.indexOf("조회");

            String priceText = fullText.substring(startIndex + 3, endIndex).trim(); // "가격" 이후 공백을 제거하여 추출
            System.out.println("Price: " + priceText);

            // WebDriver 종료
            driver.quit();
            return priceText;
        }
        return "";

    }
}
