package com.unit.test.mockito.model;

/**
 * @author lisen
 * @since 12-06-2018
 */
public class PresenterImpl {

    private ModelImpl mModel;

    public PresenterImpl() {
    }

    public PresenterImpl(ModelImpl model) {
        mModel = model;
    }

    public void setModel(ModelImpl model) {
        mModel = model;
    }

    public void setData(String data) {
        mModel.setData(data);
    }

    public String getData() {
        return mModel.getData();
    }
}
