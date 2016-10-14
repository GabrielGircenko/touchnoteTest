package com.gircenko.gabriel.touchnotetest.main;

/**
 * Created by Gabriel Gircenko on 13-Oct-16.
 */

public class MainPresenter implements IMainPresenter {

    private IMainView view;

    public MainPresenter(IMainView view) {
        this.view = view;
    }
}
