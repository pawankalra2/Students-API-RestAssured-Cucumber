package data;

import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;

/**
 * Class represents Student details.
 */

public class studentDetails {
    private String id, firstName, lastName, nationality, studentClass;

    //Constructor initializing the variables with datatable values from feature file.
    public studentDetails(DataTable dtStudentDetails) {
        List<Map<String, String>> studentDetails = dtStudentDetails.asMaps(String.class, String.class);

        this.firstName = studentDetails.get(0).get("firstName");
        this.id = studentDetails.get(0).get("id");
        this.lastName = studentDetails.get(0).get("lastName");
        this.nationality = studentDetails.get(0).get("nationality");
        this.studentClass = studentDetails.get(0).get("studentClass");


    }


    public studentDetails() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }


}
