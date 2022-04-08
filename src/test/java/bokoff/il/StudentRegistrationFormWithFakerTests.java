package bokoff.il;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.lang.String.format;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import java.util.Locale;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;




public class StudentRegistrationFormWithFakerTests {

  Faker fakerRU = new Faker( new Locale("ru"));
  Faker faker = new Faker();

  String firstName = fakerRU.name().firstName(),
      lastName = fakerRU.name().lastName(),
      email = faker.internet().emailAddress(),
      mobilePhone = "1111111111",
      subjects="English",
      gender="Male",
      filePath= "test.jpg",
      currentAddress=fakerRU.address().fullAddress(),
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
