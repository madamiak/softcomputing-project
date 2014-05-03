package pl.wroc.pwr.student.softcomputing.pokerbot.converter;

/**
 * Created by RaV on 15.01.14.
 */
public enum Border {
    Random("Random"), Regular("Regular"), WeakLimper("WeakLimper"), GoodLimper("GoodLimper");
    private String name;

    private Border(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static Border getBorder(String borderName){
        for(Border border : Border.values()){
            if(border.name.equals(borderName)) return border;
        }
        throw new NoSuchBorderException();
    }

    public static class NoSuchBorderException extends RuntimeException{};
}
