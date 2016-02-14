package com.xaidworkz.www.i_am_loco_app.database;

/**
 * Created by zaid on 14-02-2016.
 */
public class PaymentInfo {
    int Sno,
            Jobid,
            Loginid;
    String Name,
            TotalAmount,
            AmountToBePaid,
            PaidToLoginid,
            Status;

    public int getSno() {
        return Sno;
    }

    public void setSno(int sno) {
        Sno = sno;
    }

    public int getJobid() {
        return Jobid;
    }

    public void setJobid(int jobid) {
        Jobid = jobid;
    }

    public int getLoginid() {
        return Loginid;
    }

    public void setLoginid(int loginid) {
        Loginid = loginid;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        TotalAmount = totalAmount;
    }

    public String getAmountToBePaid() {
        return AmountToBePaid;
    }

    public void setAmountToBePaid(String amountToBePaid) {
        AmountToBePaid = amountToBePaid;
    }

    public String getPaidToLoginid() {
        return PaidToLoginid;
    }

    public void setPaidToLoginid(String paidToLoginid) {
        PaidToLoginid = paidToLoginid;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
