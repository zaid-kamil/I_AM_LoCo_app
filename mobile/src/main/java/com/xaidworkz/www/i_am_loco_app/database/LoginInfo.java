package com.xaidworkz.www.i_am_loco_app.database;

import java.util.List;

/**
 * Created by zaid on 14-02-2016.
 */
public class LoginInfo {

    /**
     * status : success
     * data : [{"Name":"john fish","Login":"test@test.com","Password":"123","ProfilePic":"../assets/portraits/5.jpg","UserType":"Customer","LoginDate":{"date":"2016-02-11 00:00:00.000000","timezone_type":3,"timezone":"UTC"},"LoginTime":{"date":"2016-02-25 20:14:13.000000","timezone_type":3,"timezone":"UTC"},"LogoutDate":{"date":"2015-10-26 00:00:00.000000","timezone_type":3,"timezone":"UTC"},"LogoutTime":{"date":"2016-02-25 08:13:00.000000","timezone_type":3,"timezone":"UTC"}}]
     */

    private String status;
    /**
     * Name : john fish
     * Login : test@test.com
     * Password : 123
     * ProfilePic : ../assets/portraits/5.jpg
     * UserType : Customer
     * LoginDate : {"date":"2016-02-11 00:00:00.000000","timezone_type":3,"timezone":"UTC"}
     * LoginTime : {"date":"2016-02-25 20:14:13.000000","timezone_type":3,"timezone":"UTC"}
     * LogoutDate : {"date":"2015-10-26 00:00:00.000000","timezone_type":3,"timezone":"UTC"}
     * LogoutTime : {"date":"2016-02-25 08:13:00.000000","timezone_type":3,"timezone":"UTC"}
     */

    private List<DataEntity> data;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String Name;
        private String Login;
        private String Password;
        private String ProfilePic;
        private String UserType;
        /**
         * date : 2016-02-11 00:00:00.000000
         * timezone_type : 3
         * timezone : UTC
         */

        private LoginDateEntity LoginDate;
        /**
         * date : 2016-02-25 20:14:13.000000
         * timezone_type : 3
         * timezone : UTC
         */

        private LoginTimeEntity LoginTime;
        /**
         * date : 2015-10-26 00:00:00.000000
         * timezone_type : 3
         * timezone : UTC
         */

        private LogoutDateEntity LogoutDate;
        /**
         * date : 2016-02-25 08:13:00.000000
         * timezone_type : 3
         * timezone : UTC
         */

        private LogoutTimeEntity LogoutTime;

        public void setName(String Name) {
            this.Name = Name;
        }

        public void setLogin(String Login) {
            this.Login = Login;
        }

        public void setPassword(String Password) {
            this.Password = Password;
        }

        public void setProfilePic(String ProfilePic) {
            this.ProfilePic = ProfilePic;
        }

        public void setUserType(String UserType) {
            this.UserType = UserType;
        }

        public void setLoginDate(LoginDateEntity LoginDate) {
            this.LoginDate = LoginDate;
        }

        public void setLoginTime(LoginTimeEntity LoginTime) {
            this.LoginTime = LoginTime;
        }

        public void setLogoutDate(LogoutDateEntity LogoutDate) {
            this.LogoutDate = LogoutDate;
        }

        public void setLogoutTime(LogoutTimeEntity LogoutTime) {
            this.LogoutTime = LogoutTime;
        }

        public String getName() {
            return Name;
        }

        public String getLogin() {
            return Login;
        }

        public String getPassword() {
            return Password;
        }

        public String getProfilePic() {
            return ProfilePic;
        }

        public String getUserType() {
            return UserType;
        }

        public LoginDateEntity getLoginDate() {
            return LoginDate;
        }

        public LoginTimeEntity getLoginTime() {
            return LoginTime;
        }

        public LogoutDateEntity getLogoutDate() {
            return LogoutDate;
        }

        public LogoutTimeEntity getLogoutTime() {
            return LogoutTime;
        }

        public static class LoginDateEntity {
            private String date;
            private int timezone_type;
            private String timezone;

            public void setDate(String date) {
                this.date = date;
            }

            public void setTimezone_type(int timezone_type) {
                this.timezone_type = timezone_type;
            }

            public void setTimezone(String timezone) {
                this.timezone = timezone;
            }

            public String getDate() {
                return date;
            }

            public int getTimezone_type() {
                return timezone_type;
            }

            public String getTimezone() {
                return timezone;
            }
        }

        public static class LoginTimeEntity {
            private String date;
            private int timezone_type;
            private String timezone;

            public void setDate(String date) {
                this.date = date;
            }

            public void setTimezone_type(int timezone_type) {
                this.timezone_type = timezone_type;
            }

            public void setTimezone(String timezone) {
                this.timezone = timezone;
            }

            public String getDate() {
                return date;
            }

            public int getTimezone_type() {
                return timezone_type;
            }

            public String getTimezone() {
                return timezone;
            }
        }

        public static class LogoutDateEntity {
            private String date;
            private int timezone_type;
            private String timezone;

            public void setDate(String date) {
                this.date = date;
            }

            public void setTimezone_type(int timezone_type) {
                this.timezone_type = timezone_type;
            }

            public void setTimezone(String timezone) {
                this.timezone = timezone;
            }

            public String getDate() {
                return date;
            }

            public int getTimezone_type() {
                return timezone_type;
            }

            public String getTimezone() {
                return timezone;
            }
        }

        public static class LogoutTimeEntity {
            private String date;
            private int timezone_type;
            private String timezone;

            public void setDate(String date) {
                this.date = date;
            }

            public void setTimezone_type(int timezone_type) {
                this.timezone_type = timezone_type;
            }

            public void setTimezone(String timezone) {
                this.timezone = timezone;
            }

            public String getDate() {
                return date;
            }

            public int getTimezone_type() {
                return timezone_type;
            }

            public String getTimezone() {
                return timezone;
            }
        }
    }
}
