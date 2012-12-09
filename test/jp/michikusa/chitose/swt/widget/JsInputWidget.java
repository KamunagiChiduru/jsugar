package jp.michikusa.chitose.swt.widget;

public interface JsInputWidget<E>{
    void clearInput();
    void setInput(E input);
    E getInput();
}
