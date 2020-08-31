package com.example.cloneicaller.Models;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Relation;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.List;

@Entity
public class DataModel {

    /**
     * data : {"current_page":1,"data":[{"id":2,"code":"+84","phone":"+84345918901","name":"Nguyễn Anh Tuấn","warn_type":3,"updated_at":"2020-07-21 04:43:25","user":null},{"id":3,"code":"+84","phone":"+84909437979","name":"Tuyết Ngân","warn_type":3,"updated_at":"2020-05-06 07:19:58","user":null},{"id":4,"code":"+84","phone":"+84982997171","name":"Trần Ngọc Thiên Anh","warn_type":2,"updated_at":"2020-05-14 10:19:43","user":null},{"id":5,"code":"+84","phone":"+84813313687","name":"Trần Ngọc Thiên Anh","warn_type":1,"updated_at":"2020-07-24 01:56:39","user":null},{"id":6,"code":"+84","phone":"+84909328577","name":"Hoài Phương","warn_type":1,"updated_at":"2020-04-29 02:20:54","user":null}],"first_page_url":"http://icaller.grooo.com.vn/v1/phone/get-db?page=1","from":1,"last_page":14222,"last_page_url":"http://icaller.grooo.com.vn/v1/phone/get-db?page=14222","next_page_url":"http://icaller.grooo.com.vn/v1/phone/get-db?page=2","path":"http://icaller.grooo.com.vn/v1/phone/get-db","per_page":"5","prev_page_url":null,"to":5,"total":71110}
     * timestamp : 2020-08-07 02:09:56
     * phone_deleted : 1,93835,36550,93841,93841,93841,93831,93750,94203,94253,94256,94257,94214,94259,94259,93779,94260
     */

    private DataBeanX data;
    private String timestamp;
    private String phone_deleted;

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPhone_deleted() {
        return phone_deleted;
    }

    public void setPhone_deleted(String phone_deleted) {
        this.phone_deleted = phone_deleted;
    }

    public static class DataBeanX {
        /**
         * current_page : 1
         * data : [{"id":2,"code":"+84","phone":"+84345918901","name":"Nguyễn Anh Tuấn","warn_type":3,"updated_at":"2020-07-21 04:43:25","user":null},{"id":3,"code":"+84","phone":"+84909437979","name":"Tuyết Ngân","warn_type":3,"updated_at":"2020-05-06 07:19:58","user":null},{"id":4,"code":"+84","phone":"+84982997171","name":"Trần Ngọc Thiên Anh","warn_type":2,"updated_at":"2020-05-14 10:19:43","user":null},{"id":5,"code":"+84","phone":"+84813313687","name":"Trần Ngọc Thiên Anh","warn_type":1,"updated_at":"2020-07-24 01:56:39","user":null},{"id":6,"code":"+84","phone":"+84909328577","name":"Hoài Phương","warn_type":1,"updated_at":"2020-04-29 02:20:54","user":null}]
         * first_page_url : http://icaller.grooo.com.vn/v1/phone/get-db?page=1
         * from : 1
         * last_page : 14222
         * last_page_url : http://icaller.grooo.com.vn/v1/phone/get-db?page=14222
         * next_page_url : http://icaller.grooo.com.vn/v1/phone/get-db?page=2
         * path : http://icaller.grooo.com.vn/v1/phone/get-db
         * per_page : 5
         * prev_page_url : null
         * to : 5
         * total : 71110
         */

        private int current_page;
        private String first_page_url;
        private int from;
        private int last_page;
        private String last_page_url;
        private String next_page_url;
        private String path;
        private String per_page;
        private Object prev_page_url;
        private int to;
        private int total;
        private List<DataBean> data;

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public String getFirst_page_url() {
            return first_page_url;
        }

        public void setFirst_page_url(String first_page_url) {
            this.first_page_url = first_page_url;
        }

        public int getFrom() {
            return from;
        }

        public void setFrom(int from) {
            this.from = from;
        }

        public int getLast_page() {
            return last_page;
        }

        public void setLast_page(int last_page) {
            this.last_page = last_page;
        }

        public String getLast_page_url() {
            return last_page_url;
        }

        public void setLast_page_url(String last_page_url) {
            this.last_page_url = last_page_url;
        }

        public String getNext_page_url() {
            return next_page_url;
        }

        public void setNext_page_url(String next_page_url) {
            this.next_page_url = next_page_url;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getPer_page() {
            return per_page;
        }

        public void setPer_page(String per_page) {
            this.per_page = per_page;
        }

        public Object getPrev_page_url() {
            return prev_page_url;
        }

        public void setPrev_page_url(Object prev_page_url) {
            this.prev_page_url = prev_page_url;
        }

        public int getTo() {
            return to;
        }

        public void setTo(int to) {
            this.to = to;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        @Entity(tableName = "databean")
        public static class DataBean {
            /**
             * id : 2
             * code : +84
             * phone : +84345918901
             * name : Nguyễn Anh Tuấn
             * warn_type : 3
             * updated_at : 2020-07-21 04:43:25
             * user : null
             */
            @PrimaryKey
            private int id;
            @ColumnInfo(name = "code")
            private String code;
            @ColumnInfo(name = "phone")
            private String phone;
            @ColumnInfo(name = "name")
            private String name;
            @ColumnInfo(name = "warn_type")
            private int warn_type;
            @ColumnInfo(name = "updated_at")
            private String updated_at;
            @Ignore
            private Object user;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getWarn_type() {
                return warn_type;
            }

            public void setWarn_type(int warn_type) {
                this.warn_type = warn_type;
            }

            public String getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
            }

            public Object getUser() {
                return user;
            }

            public void setUser(Object user) {
                this.user = user;
            }
        }
    }
}
