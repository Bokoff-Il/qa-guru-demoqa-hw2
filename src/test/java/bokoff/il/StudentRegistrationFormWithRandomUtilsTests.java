package bokoff.il;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.lang.String.format;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

//import org.apache.commons.lang3.RandomStringUtils;


public class StudentRegistrationFormWithRandomUtilsTests {

  String firstName = "Ilya",
         lastName = "Bokov",
         email = "bokoff.il@gmail.com",
         mobilePhone = "1111111111",
         subjects="English",
         gender="Male",
         filePath= "test.jpg",
         currentAddress="1st Street",
         dateOfBirth="30 August,2000",
         hobby="Sports",
         state="NCR",
         city="Delhi";

  String fullName = format("%s %s", firstName, lastName);

  @BeforeAll
  static void setUp(){
    Configuration.baseUrl = "https://demoqa.com";
    Configuration.browserSize = "1920x1080";

  }

  @Test
  void fillFormTest(){

    SelenideElement stateElement =$("#state");
    SelenideElement cityElement =$("#city");

    open("/automation-practice-form");

    $("#firstName").setValue(firstName);
    $("#lastName").setValue(lastName);
    $("#userEmail").setValue(email);
    $(byText(gender)).click();
    $("#userNumber").setValue(mobilePhone);
    $("#dateOfBirthInput").click();
    $(".react-datepicker__month-select").selectOption("August");
    $(".react-datepicker__year-select").selectOption("2000");
    $(".react-datepicker__day--030:not(.react-datepicker__day--outside-month").click();
    $("#subjectsInput").setValue(subjects).pressEnter();
    $(byText(hobby)).click();
    $("#uploadPicture").uploadFromClasspath("img/"+filePath);
    $("#currentAddress").setValue(currentAddress);
    stateElement.click();
    stateElement.$(byText(state)).click();
    cityElement.click();
    cityElement.$(byText(city)).click();
    $("#submit").click();

    $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
    $(".table-responsive").shouldHave(
        text(fullName),
        text(email),
        text(mobilePhone),
        text(gender),
        text(dateOfBirth),
        text(subjects),
        text(hobby),
        text(filePath),
        text(currentAddress),
        text(state+" " +city)
                                     );

  }
}
