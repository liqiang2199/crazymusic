package com.music.model;

import java.util.List;

public class LoginRes {


    /**
     * code : 0
     * data : {"head_img":"http://crazymusic-bucket.oss-cn-shenzhen.aliyuncs.com/a71454a42.png","id":"1","phone":"15528396284","region":{"city":"cd","city_code":"123","district":"whq","district_code":"1234","province":"sc","province_code":"123456"},"roleState":"5","sex":1,"sign":"123123","skileds":[{"id":"1","name":"吉他"},{"id":"4","name":"电子乐"}],"token":"3b84bd2c915b475d829bb95abbf3870d"}
     * msg : 成功
     */

    private int code;
    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * head_img : http://crazymusic-bucket.oss-cn-shenzhen.aliyuncs.com/a71454a42.png
         * id : 1
         * phone : 15528396284
         * region : {"city":"cd","city_code":"123","district":"whq","district_code":"1234","province":"sc","province_code":"123456"}
         * roleState : 5
         * sex : 1
         * sign : 123123
         * skileds : [{"id":"1","name":"吉他"},{"id":"4","name":"电子乐"}]
         * token : 3b84bd2c915b475d829bb95abbf3870d
         */

        private String head_img;
        private String id;
        private String phone;
        private RegionBean region;
        private String roleState;
        private int sex;
        private String sign;
        private String token;
        private List<SkiledsBean> skileds;

        public String getHead_img() {
            return head_img;
        }

        public void setHead_img(String head_img) {
            this.head_img = head_img;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public RegionBean getRegion() {
            return region;
        }

        public void setRegion(RegionBean region) {
            this.region = region;
        }

        public String getRoleState() {
            return roleState;
        }

        public void setRoleState(String roleState) {
            this.roleState = roleState;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public List<SkiledsBean> getSkileds() {
            return skileds;
        }

        public void setSkileds(List<SkiledsBean> skileds) {
            this.skileds = skileds;
        }

        public static class RegionBean {
            /**
             * city : cd
             * city_code : 123
             * district : whq
             * district_code : 1234
             * province : sc
             * province_code : 123456
             */

            private String city;
            private String city_code;
            private String district;
            private String district_code;
            private String province;
            private String province_code;

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCity_code() {
                return city_code;
            }

            public void setCity_code(String city_code) {
                this.city_code = city_code;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public String getDistrict_code() {
                return district_code;
            }

            public void setDistrict_code(String district_code) {
                this.district_code = district_code;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getProvince_code() {
                return province_code;
            }

            public void setProvince_code(String province_code) {
                this.province_code = province_code;
            }
        }

        public static class SkiledsBean {
            /**
             * id : 1
             * name : 吉他
             */

            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
