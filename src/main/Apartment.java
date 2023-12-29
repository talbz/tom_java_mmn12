// comments
public class Apartment {

    int _noOfRooms;
    double _area;
    double _price;
    Person _tenant;
    Date _rentalStartDate;
    Date _rentalEndDate;

    public Apartment(int numberOfRooms, double area, double price, Person tenant,
                     int rentalStartDay, int rentalStartMonth, int rentalStartYear,
                     int rentalEndDay, int rentalEndMonth, int rentalEndYear) {

        _noOfRooms = (numberOfRooms < 1) ? 3 : numberOfRooms;
        _area = (area <= 0.0) ? 80 : area;
        _price = (price <= 0.0) ? 5000 : price;
        _tenant = tenant;
        _rentalStartDate = new Date(rentalStartDay, rentalStartMonth, rentalStartYear);
        _rentalEndDate = new Date(rentalEndDay, rentalEndMonth, rentalEndYear);

        if (! _rentalEndDate.after(_rentalStartDate)) {
            _rentalEndDate = _rentalEndDate.addYearsToDate(1);
        }
    }

    public Apartment (Apartment other) {
        _noOfRooms = other.getNoOfRooms();
        _area = other.getArea();
        _price = other.getPrice();
        _tenant = other.getTenant();
        _rentalStartDate = new Date(other.getRentalStartDate());
        _rentalEndDate = new Date(other.getRentalEndDate());
    }

    public String toString() {
        return "Number of rooms: " + _noOfRooms + "\nArea: " + _area +
                "\nPrice: " + _price + " NIS\nTenant name: " + _tenant.getName() +
                "\nRental start date: " + _rentalStartDate + "\nRental end date: " + _rentalEndDate;
    }

    public boolean equals(Apartment other) {
        return (_noOfRooms == other.getNoOfRooms() && _area == other.getArea() && _price == other.getPrice() &&
                _tenant.equals(other.getTenant()) && _rentalStartDate.equals(other.getRentalStartDate()) &&
                _rentalEndDate.equals(other.getRentalEndDate()));
    }

    public void extendRentalPeriod (int year) {
        if (year > 0) {
            _rentalEndDate = _rentalEndDate.addYearsToDate(year);
        }
    }

    public int daysLeft (Date d) {
        return (d.after(_rentalEndDate)) ? -1 : d.difference(_rentalEndDate);
    }

    public boolean changeTenant (Date startDate, Person p, double price) {
        if (p.getDateOfBirth().after(_tenant.getDateOfBirth()) && price >= _price && startDate.difference(_rentalEndDate) <= 90) {
            _price = price;
            _tenant = p;
            _rentalStartDate = startDate;
            _rentalEndDate = _rentalStartDate.addYearsToDate(1);
            return true;
        }
        return false;
    }

    public void setNoOfRooms(int newNoOfRooms) {
        _noOfRooms = (newNoOfRooms < 1)? _noOfRooms : newNoOfRooms;
    }
    public void setArea(double newArea) {
        _area = (newArea <= 0.0) ? _area : newArea;
    }
    public void setPrice(double newPrice) {
        _price = (newPrice <= 0.0) ? _price : newPrice;
    }
    public void setTenant(Person newTenant) {
        _tenant = new Person(newTenant);
    }
    public void setRentalStartDate(Date newRentalStartDate) {
        if (_rentalEndDate.after(newRentalStartDate)) {
            _rentalStartDate = newRentalStartDate;
        }
    }
    public void setRentalEndDate(Date newRentalEndDate) {
        if (newRentalEndDate.after(_rentalStartDate)) {
            _rentalEndDate = newRentalEndDate;
        }
    }


    public int getNoOfRooms() {
        return _noOfRooms;
    }
    public double getArea() {
        return _area;
    }
    public double getPrice() {
        return _price;
    }
    public Person getTenant() {
        return _tenant;
    }
    public Date getRentalStartDate() {
        return _rentalStartDate;
    }
    public Date getRentalEndDate() {
        return _rentalEndDate;
    }
}
