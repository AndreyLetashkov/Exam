package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.DriverUtils;

public class AddProjectPage extends Form {
    private static final String projectNameLocator = "//input[@id='projectName']";
    private final ITextBox nameField = getElementFactory().getTextBox(By.xpath(projectNameLocator), "projectNameField");
    private final IButton saveBtn = getElementFactory().getButton(By.xpath("//button[@class='btn btn-primary']"), "Save project");
    private final ILabel successLabel = getElementFactory().getLabel(By.xpath("//div[contains(@class,'alert-success')]"), "Success alert");

    public AddProjectPage() { super(By.xpath(projectNameLocator), "projectNameField"); }

    public void enterProjectName(String nameOfNewProject){ nameField.sendKeys(nameOfNewProject); }

    public void switchToActiveTab(){ DriverUtils.switchToAnotherWindow(1); }

    public void clickSaveBtn(){ saveBtn.click(); }

    public boolean isAddedProject(){ return successLabel.getJsActions().isElementOnScreen(); }
}