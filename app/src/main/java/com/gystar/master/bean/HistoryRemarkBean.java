package com.gystar.master.bean;

import com.utils.gyymz.http.callback.DataBeanCallBack;

import java.util.List;

public class HistoryRemarkBean extends DataBeanCallBack<HistoryRemarkBean.DataBean> {

    public static class DataBean {
        /**
         * conditions : null
         * currPage : 1
         * page : [{"id":206,"remark":"2","create_time":"2018-09-12 10:34:28"},{"id":205,"remark":"1","create_time":"2018-09-12 10:34:14"}]
         * pageSize : 6
         * pageTitle :
         * totalCount : 2
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
             * id : 206
             * remark : 2
             * create_time : 2018-09-12 10:34:28
             */

            private int id;
            private String remark;
            private String create_time;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }
        }
    }
}
