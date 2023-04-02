import com.github.javafaker.Faker;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;


public class FakerRegistrationTest extends TestBase{
        RegistrationPage registrationPage = new RegistrationPage();



    @Test
    @Tag("hw")

    public void toolsQaForm () {
        step ("fill form", () -> {
            Faker faker = new Faker();


            String userName = faker.name().firstName();
            String userLastName = faker.name().lastName();
            String userEmail = faker.internet().emailAddress();
            String userGender = faker.options().option("Male", "Female", "Other");
            String userNumber = String.valueOf(faker.number().numberBetween(1000000000, 2009999999));
            String userBirthDay_day = String.valueOf(faker.number().numberBetween(1, 28));
            String userBirthDay_month = faker.options().option("January", "February",
                "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
            String userBirthDay_year = String.valueOf(faker.number().numberBetween(1950, 2000));
            String userSubjects = faker.options().option("English", "Physics", "Chemistry", "Computer Science",
                "Commerce", "Accounting", "Economics");
            String userHobbies = faker.options().option("Music", "Sports", "Reading");
            String userPictureLocation = "pictures/3.png";
            String userAddress = faker.address().streetName();
            String userState = faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");


            String city = new String();
            if (userState == "NCR") {
                city = faker.options().option("Delhi", "Gurgaon", "Noida");
            } else if (userState == "Uttar Pradesh") {
                city = faker.options().option("Agra", "Lucknow", "Merrut");
            } else if (userState == "Haryana") {
                city = faker.options().option("Karnal", "Panipat");
            } else {
                city = faker.options().option("Jaipur", "Jaiselmer");
            }

            String userCity = city;




        registrationPage.openPage();
        registrationPage.executeJS();

        registrationPage.setFirstName(userName);
        registrationPage.setLastName(userLastName);
        registrationPage.setUserEmail(userEmail);
        registrationPage.clickUserGender(userGender);
        registrationPage.setUserNumber(userNumber);
        registrationPage.setBirthDay(userBirthDay_day, userBirthDay_month, userBirthDay_year);
        registrationPage.setSubjects(userSubjects);
        registrationPage.clickHobbies(userHobbies);
        registrationPage.setPictures(userPictureLocation);
        registrationPage.setAddress(userAddress);
        registrationPage.setState(userState);
        registrationPage.setCity(userCity);

        registrationPage.clickSubmit();

        registrationPage.verifyResultsModal();
        registrationPage.verifyResults("Student Name", userName + " " + userLastName);
        registrationPage.verifyResults("Student Email", userEmail);
        registrationPage.verifyResults("Gender", userGender);
        registrationPage.verifyResults("Mobile", userNumber);
        registrationPage.verifyResults("Date of Birth", userBirthDay_day + " " + userBirthDay_month + "," + userBirthDay_year);
        registrationPage.verifyResults("Subjects", userSubjects);
        registrationPage.verifyResults("Hobbies", userHobbies);
        registrationPage.verifyResults("Address", userAddress);
        registrationPage.verifyResults("State and City", userState + " " + userCity);

        registrationPage.clickClose();
        });


    }
}