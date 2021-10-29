package com.laputa.iot.common.data.datascope;

/**
 * @author sommer.jiang
 * @Description 排序的enum
 * @time 2021年4月16日
 */
public enum ORDERBY {
    DESC, ASC;

    public ORDERBY reverse() {
        return (this == ASC) ? DESC : ASC;
    }
}
