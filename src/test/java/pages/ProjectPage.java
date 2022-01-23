package pages;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IComboBox;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import java.util.List;

public class ProjectPage extends Form {
    private static final String nexageLocator = "//ol[@class='breadcrumb']//li[contains(., 'Nexage')]";
    private static final String elementsTableLocator = "//tr/td";
    private final IComboBox testsTable = getElementFactory().getComboBox(By.className("table"),"TestsTable");
    private final IComboBox dangerAlert = getElementFactory().getComboBox(By.xpath("//div[contains(@class,'alert-danger')]"),
            "Danger alert");

    public ProjectPage() { super(By.xpath(nexageLocator), "NexageLabel"); }

    public List<IElement> getTestsList() { return testsTable.findChildElements(By.xpath(elementsTableLocator), ElementType.TEXTBOX); }

    public void waitingForThePageToLoad() { testsTable.state().waitForDisplayed(); }

    public void waitingForTheTestToAppear(){ dangerAlert.state().waitForNotDisplayed(); }

    public boolean isTestAdded(String testName) {
        return testsTable.findChildElements(By.linkText(testName), ElementType.TEXTBOX).size() > 0;
    }
}