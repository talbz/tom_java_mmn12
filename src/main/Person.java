/**
 * Set finals.
 * Add doc.
 * check setDateOfBirth.
 */

public class Person {

    private String _name;
    private String _id;
    private Date _dateOfBirth;


    public Person(String name, int birthDay, int birthMonth, int birthYear, String id) {
        if (name.equals("")) {
            _name = "Someone";
        } else {
            _name = name;
        }

        if (id.length() != 9) {
            _id = "000000000";
        } else {
            _id = id;
        }

        _dateOfBirth = new Date(birthDay, birthMonth, birthYear);
    }

    public Person(Person other) {
        _name = other.getName();
        _id = other.getId();
        _dateOfBirth = other.getDateOfBirth();
    }

    public String toString() {
        return "Name: " + _name + "\nID: " + _id + "\nDate of birth: " + _dateOfBirth;
    }

    public boolean equals(Person other) {
        return (_name.equals(other.getName())) && (_id.equals(other.getId())) && (_dateOfBirth.equals(other.getDateOfBirth()));
    }

    public int compareTo(Person other) {
        if (_dateOfBirth.before(other.getDateOfBirth())) {
            return 1;
        }
        return (_dateOfBirth.equals(other.getDateOfBirth())) ? 0 : -1;
    }


    public void setName(String name) {
        _name = (name.equals("")) ? _name : name;
    }
    public void setId(String id) {
         _id = (id.length() != 9) ? _id : id;
    }
    /**Check for copy on read*/
    public void setDateOfBirth(Date dateOfBirth) {
        _dateOfBirth = new Date(dateOfBirth);
    }

    public String getName() {
        return _name;
    }
    public String getId() {
        return _id;
    }
    public Date getDateOfBirth() {
        return _dateOfBirth;
    }
}
