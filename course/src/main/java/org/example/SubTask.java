package org.example;

public class SubTask {

    private String text;

    public SubTask(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SubTask list = (SubTask) obj;
        return list.getText().equals(this.getText());
    }
}