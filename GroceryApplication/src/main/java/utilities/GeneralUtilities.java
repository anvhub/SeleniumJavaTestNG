package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class GeneralUtilities {
	public String selectDropdownWithIndex(WebElement element, int indexNumber) {
		Select object = new Select(element);
		object.selectByIndex(indexNumber);
		WebElement selectedElement = object.getFirstSelectedOption();
		return selectedElement.getText();
	}

	// Accept the alert
	public void acceptAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	// Dismiss the alert
	public void dismissAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}

	// Get alert text
	public String getAlertText(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}

	// Send text to prompt alert
	public void sendTextToAlert(WebDriver driver, String text) {
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(text);
	}

	// Select a radio button if not already selected
	public void selectRadioButton(WebElement radioButton) {
		if (!radioButton.isSelected()) {
			radioButton.click();
		}
	}

	// Check if a radio button is selected
	public boolean isRadioButtonSelected(WebElement radioButton) {
		return radioButton.isSelected();
	}

	// Get row count from a table
	public int getTableRowCount(WebElement tableElement) {
		List<WebElement> rows = tableElement.findElements(By.tagName("tr"));
		return rows.size();
	}

	// Get column count from a specific row
	public int getTableColumnCount(WebElement tableElement, int rowIndex) {
		List<WebElement> rows = tableElement.findElements(By.tagName("tr"));
		if (rowIndex < rows.size()) {
			List<WebElement> cols = rows.get(rowIndex).findElements(By.tagName("td"));
			return cols.size();
		}
		return 0;
	}

	// Get cell data by row and column index
	public String getCellData(WebElement tableElement, int rowIndex, int colIndex) {
		List<WebElement> rows = tableElement.findElements(By.tagName("tr"));
		if (rowIndex < rows.size()) {
			List<WebElement> cols = rows.get(rowIndex).findElements(By.tagName("td"));
			if (colIndex < cols.size()) {
				return cols.get(colIndex).getText();
			}
		}
		return "";
	}

	// Click on cell if it contains specific text
	public void clickCellWithText(WebElement tableElement, String searchText) {
		List<WebElement> rows = tableElement.findElements(By.tagName("tr"));
		for (WebElement row : rows) {
			List<WebElement> cols = row.findElements(By.tagName("td"));
			for (WebElement cell : cols) {
				if (cell.getText().equalsIgnoreCase(searchText)) {
					cell.click();
					return;
				}
			}
		}
	}

	// Get row count (ignoring headers if any)
	public int getDynamicTableRowCount(WebElement tableElement) {
		List<WebElement> rows = tableElement.findElements(By.xpath(".//tbody/tr"));
		return rows.size();
	}

	// Get column count from the first data row
	public int getDynamicTableColumnCount(WebElement tableElement) {
		List<WebElement> firstRowColumns = tableElement.findElements(By.xpath(".//tbody/tr[1]/td"));
		return firstRowColumns.size();
	}

	// Get cell value by row & column index (1-based indexing)
	public String getDynamicCellData(WebElement tableElement, int rowIndex, int colIndex) {
		try {
			WebElement cell = tableElement.findElement(By.xpath(".//tbody/tr[" + rowIndex + "]/td[" + colIndex + "]"));
			return cell.getText();
		} catch (Exception e) {
			return "";
		}
	}

	// Find row index by cell value (returns first match)
	public int getRowIndexByCellText(WebElement tableElement, String searchText) {
		List<WebElement> rows = tableElement.findElements(By.xpath(".//tbody/tr"));
		for (int i = 0; i < rows.size(); i++) {
			List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));
			for (WebElement cell : cols) {
				if (cell.getText().trim().equalsIgnoreCase(searchText)) {
					return i + 1; // Return as 1-based index
				}
			}
		}
		return -1;
	}

	// Click element (like a button/link) inside a specific cell
	public void clickElementInCell(WebElement tableElement, int rowIndex, int colIndex) {
		try {
			WebElement cell = tableElement.findElement(By.xpath(".//tbody/tr[" + rowIndex + "]/td[" + colIndex + "]"));
			cell.findElement(By.tagName("a")).click(); // or use button
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Select a checkbox if not already selected
	public void selectCheckbox(WebElement checkbox) {
		if (!checkbox.isSelected()) {
			checkbox.click();
		}
	}

	// Deselect a checkbox if it's selected
	public void deselectCheckbox(WebElement checkbox) {
		if (checkbox.isSelected()) {
			checkbox.click();
		}
	}

	// Check if checkbox is selected
	public boolean isCheckboxSelected(WebElement checkbox) {
		return checkbox.isSelected();
	}

	public String generateCurrentDateAndTime() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyhhmmss");
		return formatter.format(date);
	}

	public int randon(int limit) {
		Random random = new Random();
		// int limit = 1000;
		int randomNumber = random.nextInt(limit);
		return randomNumber;
	}

	public void pageScroll(int horizontal, int vertical, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(" + horizontal + "," + vertical + ")", "");
	}
	
	public void clickJavaScriptExecutor(WebElement element, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", element);
	}

}
