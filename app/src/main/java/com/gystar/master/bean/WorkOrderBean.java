package com.gystar.master.bean;

import com.utils.gyymz.http.callback.DataBeanCallBack;

import java.io.Serializable;
import java.util.List;

public class WorkOrderBean  extends DataBeanCallBack<WorkOrderBean.DataBean>{


    public static class DataBean implements Serializable{
        /**
         * conditions : null
         * currPage : 1
         * page : [{"id":105,"work_status":0,"phone":"15802728428","remark":"2","order_time":"2018-09-04 10:55:04","area_name":"武汉市","name":"光银网19"},{"id":104,"work_status":0,"phone":"15972055335","remark":"","order_time":"2018-09-04 10:55:01","area_name":"武汉市","name":"袁春艳"},{"id":103,"work_status":0,"phone":"15527160432","remark":"哇哇哇","order_time":"2018-08-28 15:01:06","area_name":"武汉市","name":"高雷"},{"id":102,"work_status":0,"phone":"13080610491","remark":"","order_time":"2018-08-28 13:11:10","area_name":"武汉市","name":"范振龙"},{"id":101,"work_status":0,"phone":"15377687870","remark":"","order_time":"2018-08-14 16:22:37","area_name":"武汉市","name":"缪斯"},{"id":100,"work_status":0,"phone":"13197300247","remark":"","order_time":"2018-08-13 11:13:22","area_name":"武汉市","name":"杨剑雄"}]
         * pageSize : 6
         * pageTitle :
         * totalCount : 93
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
             * id : 105
             * work_status : 0
             * phone : 15802728428
             * remark : 2
             * order_time : 2018-09-04 10:55:04
             * area_name : 武汉市
             * name : 光银网19
             */

            private int id;
            private int work_status;
            private String phone;
            private String remark;
            private String order_time;
            private String area_name;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getWork_status() {
                return work_status;
            }

            public void setWork_status(int work_status) {
                this.work_status = work_status;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
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
        }
    }
}
