package com.CSC8404.Assesment.Person;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public final class DrivingLicense
{
    private String drivingLicense;

    private Date issueDate;

    private boolean isFullLicense;

    private static int licenseCount = 0;

    private static final HashMap<String, DrivingLicense> drivingLicenseHashMap = new HashMap<>();

    DrivingLicense(String drivingLicense, Date issueDate, boolean isFullLicense)
    {
        this.drivingLicense = drivingLicense;
        this.issueDate = issueDate;
        this.isFullLicense = isFullLicense;
    }

    /***
     * Get the date of issue of the driving license
     * @return A date object containing the issue date of the driving license
     */
    public Date getIssueDate()
    {
        return issueDate;
    }

    /***
     * Get the full driving license number
     * @return a string containing the driving license
     */
    public String getDrivingLicense()
    {
        return drivingLicense;
    }

    /***
     * Get if the license is a full driving license or not
     * @return true if it is a full driving license, false if it is not a full driving license
     */
    public boolean getIsFullLicense()
    {
        return isFullLicense;
    }

    /***
     * Get a new or existing instance of a driving license based on a person,
     * date of issue, and if it is a full license
     * @param lessor person that the driving license belongs to
     * @param issueDate date that the driving license was issued
     * @param isFullLicense true if the driving license is full, false if it is not
     * @return a new instance or a existing one of a driving license
     */
    public static DrivingLicense getInstance(Person lessor, Date issueDate, boolean isFullLicense)
    {
        if(lessor == null || lessor.getFirstName() == null || issueDate == null
                || lessor.getLastName() == null || lessor.getDateOfBirth() == null
                || lessor.getFirstName() == "" || lessor.getLastName() == "")
        {
            throw new IllegalArgumentException("There are arguments missing");
        }

        DateFormat dateStringFormat = new SimpleDateFormat("yyyy/MM/dd");
        String fName = lessor.getFirstName(), lName = lessor.getLastName();
        Date dob = lessor.getDateOfBirth();
        String tempKey = fName + lName + dateStringFormat.format(dob);

        if(!drivingLicenseHashMap.containsKey(tempKey))
        {
            drivingLicenseHashMap.put(tempKey, GenerateDrivingLicense(fName, lName, issueDate, isFullLicense));
        }

        return  drivingLicenseHashMap.get(tempKey);
    }

    /***
     * Function to generate a new driving license from the provided information based on the following pattern
     * Initials of the firstname and lastname, year of issue, and a assignation number
     * Ex: for Randy Collado, with issue date of 24/09/2006: RC-2006-1
     * @param firstName firstname of the person that owns the license
     * @param lastName lastname of the person that owns the license
     * @param issueDate date of issue of the driving license
     * @param isFullLicense true if it is a full license, false if it is not
     * @return new constructed driving license
     */
    private static DrivingLicense GenerateDrivingLicense(String firstName, String lastName, Date issueDate, boolean isFullLicense)
    {
        Calendar tempIssueDate = Calendar.getInstance();

        tempIssueDate.setTime(issueDate);

        String tempLicense = firstName.substring(0, 1) + lastName.substring(0, 1) + "-" + tempIssueDate.get(Calendar.YEAR) + "-" + licenseCount;

        licenseCount += 1;

        return  new DrivingLicense(tempLicense, tempIssueDate.getTime(), isFullLicense);
    }

    /***
     * String representation of the class
     * @return
     * Returns the values of driving license and issue date divided by a "-"
     * Example format
     * DrivingLicense-IssueDate
     */
    @Override
    public String toString() {
        return drivingLicense + "-" + issueDate;
    }

    @Override
    public boolean equals(Object rhs) {

        if(this == rhs) return  true;

        if(!(rhs instanceof DrivingLicense)) return false;

        DrivingLicense dl = (DrivingLicense) rhs;

        return issueDate.equals(dl)
                && (drivingLicense == null ? dl.drivingLicense == null
                    : drivingLicense.equals(dl.drivingLicense));
    }

    @Override
    public int hashCode() {
        final int multiplier = 37;
        int hc = 17;

        hc = (multiplier * hc) + (drivingLicense == null ? 0 : drivingLicense.hashCode());
        return (multiplier * hc) + issueDate.hashCode();
    }
}
