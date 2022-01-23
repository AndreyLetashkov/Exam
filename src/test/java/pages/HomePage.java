package pages;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import constants.Url;
import org.openqa.selenium.By;
import utils.ParserUtils;
import utils.StringUtils;

public class HomePage extends Form {
    private static final String availableProjectsLocator = "//div[text()[normalize-space()='Available projects']]";
    private final ILink projectLink = getElementFactory().getLink(By.className("list-group"),"ProjectLink");
    private final ILabel versionLabel = getElementFactory().getLabel(By.xpath("//p[contains(@class,'text-muted')]"),"VersionLabel");
    private final IButton addProjectBtn = getElementFactory().getButton(By.xpath("//a[contains(@class, 'btn-primary')]"),"AddProjectBtn");

    public HomePage() { super(By.xpath(availableProjectsLocator),"AvailableProjectsLabel"); }

    public String getVersion() { return StringUtils.getNumber(versionLabel.getText()); }

    public void addProject() {
        addProjectBtn.clickAndWait();
    }

    public void navigateProjectPage(String project) {
        projectLink.findChildElement(By.linkText(project), ElementType.BUTTON).click();
    }

    public String getAttribute(String attribute){
        return projectLink.findChildElement(By.linkText(ParserUtils.parseTestData("project")),
                ElementType.LINK).getAttribute(attribute);
    }

    public String getProjectId(String attributeValue ) {
        return attributeValue.substring(attributeValue.indexOf(StringUtils.getNumber(Url.WEB.getUrl())));
    }

    public boolean isProjectAdded(String nameOfProject){
        return projectLink.findChildElements(By.linkText(nameOfProject), ElementType.BUTTON).size() > 0;
    }

    public boolean isPageOpened() { return projectLink.state().isDisplayed(); }
}