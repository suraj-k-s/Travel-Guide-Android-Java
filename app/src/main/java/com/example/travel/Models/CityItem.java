package com.example.travel.Models;



public class CityItem {

        private int imageResourse1;
        private String title1;
        private String hotelname;
        private String key_id1;
        private String citydesc;

        //constructor
        public CityItem() {
        }



    public CityItem(int imageResourse1, String title1, String hotelname, String key_id1, String citydesc) {
            this.imageResourse1 = imageResourse1;
            this.title1 = title1;
            this.hotelname = hotelname;
            this.key_id1 = key_id1;
            this.citydesc = citydesc;
        }

        public int getImageResourse1() {
            return imageResourse1;
        }

        public void setImageResourse1(int imageResourse1) {
            this.imageResourse1 = imageResourse1;
        }

        public String getTitle1() {
            return title1;
        }

        public void setTitle1(String title1) {
            this.title1 = title1;
        }

        public String getHotelname() {
            return hotelname;
        }

        public void setHotelname(String hotelname) {
            this.hotelname = hotelname;
        }

        public String getKey_id1() {
            return key_id1;
        }

        public void setKey_id1(String key_id1) {
            this.key_id1 = key_id1;
        }


        public String getCitydesc() {
            return citydesc;
        }

        public void setCitydesc(String citydesc) {
            this.citydesc = citydesc;
        }


        }
