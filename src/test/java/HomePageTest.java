import com.wysiwyg.HomePage;
import com.wysiwyg.model.StyleConstants;
import config.ApplicationConfig;
import org.apache.wicket.Component;
import org.apache.wicket.Session;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * Created by Ioana Popescu on 7/29/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class HomePageTest {
    private WicketTester wicketTester;
    HomePage homePage;

    @Before
    public void setUp() {
        wicketTester = new WicketTester();
        wicketTester.startPage(HomePage.class);
        homePage = (HomePage)wicketTester.getLastRenderedPage();
    }
    @Test
    public void testRenderHomePage() {
        wicketTester.assertRenderedPage(HomePage.class);
        wicketTester.assertComponent("form:uiText", TextArea.class);
        wicketTester.assertComponent("form:boldButton", Button.class);
        wicketTester.assertComponent("form:italicButton", Button.class);
        wicketTester.assertComponent("form:underlineButton", Button.class);
        wicketTester.assertComponent("form:alignCenterButton", Button.class);
        wicketTester.assertComponent("form:alignLeftButton", Button.class);
        wicketTester.assertComponent("form:alignJustifyButton", Button.class);
        wicketTester.assertComponent("form:font", DropDownChoice.class);
        wicketTester.assertComponent("form:size", DropDownChoice.class);
    }

    @Test
    public void testDefaultStyleValue() {
        assertEquals(homePage.getStyleModel().getObject(), "font-family:Times New Roman;font-size:12px;text-align: left;");
    }

    @Test
    public void testBoldButton() {
        assertFalse(homePage.getText().isBold());
        FormTester formTester = wicketTester.newFormTester("form");
        formTester.setValue("boldButton", "");
        formTester.submit() ;
        assertTrue(homePage.getText().isBold());
        assertTrue(homePage.getStyleModel().getObject().contains(StyleConstants.BOLD));
    }

    @Test
    public void testItalicButton() {
        assertFalse(homePage.getText().isItalic());
        FormTester formTester = wicketTester.newFormTester("form");
        formTester.setValue("italicButton", "whatever");
        formTester.submit() ;
        assertTrue(homePage.getText().isItalic());
        assertTrue(homePage.getStyleModel().getObject().contains(StyleConstants.ITALIC));
    }
    @Test
    public void testUnderlineButton() {
        assertFalse(homePage.getText().isUnderline());
        FormTester formTester = wicketTester.newFormTester("form");
        formTester.setValue("underlineButton", "whatever");
        formTester.submit() ;
        assertTrue(homePage.getText().isUnderline());
        assertTrue(homePage.getStyleModel().getObject().contains(StyleConstants.UNDERLINE));
    }

    @Test
    public void testFontFamilyPopulated() {
        DropDownChoice fonts = (DropDownChoice) wicketTester.getComponentFromLastRenderedPage("form:font");
        int actual = fonts.getChoices().size();
        int expected = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames().length;
        assertEquals(expected, actual);
    }
    @Test
    public void testFontSizesPopulated() {
        DropDownChoice sizes = (DropDownChoice) wicketTester.getComponentFromLastRenderedPage("form:size");
        int actual = sizes.getChoices().size();
        assertEquals(StyleConstants.FONT_SIZES.length, actual);
    }
}
