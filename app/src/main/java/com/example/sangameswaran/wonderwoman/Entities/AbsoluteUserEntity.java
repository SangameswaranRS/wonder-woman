package com.example.sangameswaran.wonderwoman.Entities;

/**
 * Created by Sangameswaran on 06-01-2018.
 */

public class AbsoluteUserEntity {
    String userName,userEmailId,emergencyContact1,emergencyContact2,userHomeAddress,userAge,userId;

    public AbsoluteUserEntity() {
    }

    public AbsoluteUserEntity(String userName, String userEmailId, String emergencyContact1, String emergencyContact2, String userHomeAddress, String userAge, String userId) {
        this.userName = userName;
        this.userEmailId = userEmailId;
        this.emergencyContact1 = emergencyContact1;
        this.emergencyContact2 = emergencyContact2;
        this.userHomeAddress = userHomeAddress;
        this.userAge = userAge;
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmailId() {
        return userEmailId;
    }

    public void setUserEmailId(String userEmailId) {
        this.userEmailId = userEmailId;
    }

    public String getEmergencyContact1() {
        return emergencyContact1;
    }

    public void setEmergencyContact1(String emergencyContact1) {
        this.emergencyContact1 = emergencyContact1;
    }

    public String getEmergencyContact2() {
        return emergencyContact2;
    }

    public void setEmergencyContact2(String emergencyContact2) {
        this.emergencyContact2 = emergencyContact2;
    }

    public String getUserHomeAddress() {
        return userHomeAddress;
    }

    public void setUserHomeAddress(String userHomeAddress) {
        this.userHomeAddress = userHomeAddress;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
