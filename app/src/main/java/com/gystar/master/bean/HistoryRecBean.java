package com.gystar.master.bean;

import com.utils.gyymz.http.callback.DataBeanCallBack;

import java.util.List;

public class HistoryRecBean  extends DataBeanCallBack<HistoryRecBean.DataBean>{

    public static class DataBean {
        /**
         * conditions : null
         * currPage : 1
         * page : [{"amount":1,"id":56,"phone":"13247236020","time":"2018-07-30 09:36:19","recharge_phone":"15090742405","order_id":"GYSTAR000020180730093618RW"},{"amount":95,"id":26,"phone":"15090742405","time":"2018-07-19 11:20:41","recharge_phone":"15090742405","order_id":"GYSTAR000020180719112040UD"},{"amount":95,"id":25,"phone":"15090742405","time":"2018-07-19 11:10:39","recharge_phone":"15090742405","order_id":"GYSTAR000020180719111038BP"}]
         * pageSize : 6
         * pageTitle :
         * totalCount : 3
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

        public static class PageBean {
            /**
             * amount : 1
             * id : 56
             * phone : 13247236020
             * time : 2018-07-30 09:36:19
             * recharge_phone : 15090742405
             * order_id : GYSTAR000020180730093618RW
             */

            private int amount;
            private int id;
            private String phone;
            private String time;
            private String recharge_phone;
            private String order_id;

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getRecharge_phone() {
                return recharge_phone;
            }

            public void setRecharge_phone(String recharge_phone) {
                this.recharge_phone = recharge_phone;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }
        }
    }
}
