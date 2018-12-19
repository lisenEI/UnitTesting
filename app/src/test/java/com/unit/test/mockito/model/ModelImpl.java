package com.unit.test.mockito.model;

/**
 * @author lisen
 * @since 12-06-2018
 */
public class ModelImpl {

    public static final String DEFAULT_DATA = "default_data";

    private String mData = DEFAULT_DATA;

    private int mType;

    public ModelImpl() {
    }

    public void setData(String data) {
        mData = data;
    }

    public String getData() {
        return mData;
    }

    public void setType(int type) {
        mType = type;
    }

    public void sleep(final long millis) {
        try {
            Thread.sleep(millis);
            String data = getData();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
