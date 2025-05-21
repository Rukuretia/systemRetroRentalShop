package models;

import java.util.Date;

public class Rental {
    Date startDate;
    Date returnDate;
    int rentalPeriod;

    double calculateOverdueCost(){
        return 0;
    }
}
