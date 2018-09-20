package com.gystar.master.bean;

import com.utils.gyymz.http.callback.DataBeanCallBack;

import java.io.Serializable;
import java.util.List;

public class MyClientPerson extends DataBeanCallBack<MyClientPerson.DataBean> {

    public static class DataBean implements Serializable{
        /**
         * conditions : null
         * currPage : 1
         * page : [{"id":57,"can_call_time":"","phone":"15172398797","price":1000,"receive_call_status":0,"shelf_time":"2018/08/08","area_name":"武汉市","name":"魏胜滨"},{"id":56,"can_call_time":"","phone":"13080610491","price":1000,"receive_call_status":0,"shelf_time":"2018/08/08","area_name":"武汉市","name":"范振龙"},{"id":55,"can_call_time":"","phone":"13197300247","price":1000,"receive_call_status":0,"shelf_time":"2018/08/08","area_name":"武汉市","name":"杨剑雄"},{"id":52,"can_call_time":"","phone":"13667130077","price":1000,"receive_call_status":0,"shelf_time":"2018/08/08","area_name":"武汉市","name":"向友军"},{"id":53,"can_call_time":"","phone":"15377687870","price":1000,"receive_call_status":0,"shelf_time":"2018/08/08","area_name":"武汉市","name":"缪斯"},{"id":54,"can_call_time":"","phone":"18772811315","price":1000,"receive_call_status":0,"shelf_time":"2018/08/08","area_name":"武汉市","name":"孔新慧"}]
         * pageSize : 6
         * pageTitle :
         * totalCount : 16
         * totalPageCount : 0
         */

        private Object conditions;
        private int currPage;
        private int pageSize;
        private String pageTitle;
        private int totalCount;
        private int totalPageCount;
        private List<PageBean> page;

        public Object getConditions() {
            return conditions;
        }

        public void setConditions(Object conditions) {
            this.conditions = conditions;
        }

        public int getCurrPage() {
            return currPage;
        }

        public void setCurrPage(int currPage) {
            this.currPage = currPage;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public String getPageTitle() {
            return pageTitle;
        }

        public void setPageTitle(String pageTitle) {
            this.pageTitle = pageTitle;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getTotalPageCount() {
            return totalPageCount;
        }

        public void setTotalPageCount(int totalPageCount) {
            this.totalPageCount = totalPageCount;
        }

        public List<PageBean> getPage() {
            return page;
        }

        public void setPage(List<PageBean> page) {
            this.page = page;
        }

        public static class PageBean implements Serializable{
            /**
             * id : 57
             * can_call_time :
             * phone : 15172398797
             * price : 1000
             * receive_call_status : 0
             * shelf_time : 2018/08/08
             * area_name : 武汉市
             * name : 魏胜滨
             */

            private boolean showBuy;
            private String remark;
            private String order_time;
            private String work_status;


            /*--------------*/
            private int id;
            private String can_call_time;
            private String phone;
            private int price;
            private int receive_call_status;
            private String shelf_time;
            private String area_name;
            private String name;

            private boolean isChecked;

            public boolean isChecked() {
                return isChecked;
            }

            public void setChecked(boolean checked) {
                isChecked = checked;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCan_call_time() {
                return can_call_time;
            }

            public void setCan_call_time(String can_call_time) {
                this.can_call_time = can_call_time;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getReceive_call_status() {
                return receive_call_status;
            }

            public void setReceive_call_status(int receive_call_status) {
                this.receive_call_status = receive_call_status;
            }

            public String getShelf_time() {
                return shelf_time;
            }

            public void setShelf_time(String shelf_time) {
                this.shelf_time = shelf_time;
            }

            public String getArea_name() {
                return area_name;
            }

            public void setArea_name(String area_name) {
                this.area_name = area_name;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getOrder_time() {
                return order_time;
            }

            public void setOrder_time(String order_time) {
                this.order_time = order_time;
            }

            public String getWork_status() {
                return work_status;
            }

            public void setWork_status(String work_status) {
                this.work_status = work_status;
            }


            public boolean isShowBuy() {
                return showBuy;
            }

            public void setShowBuy(boolean showBuy) {
                this.showBuy = showBuy;
            }
        }
    }
}
