package pl.wroc.pwr.student.softcomputing.ui.util;

/**
 * Created by RaV on 05.01.14.
 */
public class TeachingParams {
    private float scale;
    private boolean blackAndWhite=false;
    private boolean grayscale =false;
    private int maxIterations;
    private float learningRate;
    private float errorRate;
    private float momentum;

    public String toNamePostfix() {
        return "-"+parse(scale)
                +"-"+parse(blackAndWhite)
                +"-"+parse(grayscale);
    }

    @Override
    public String toString() {
        return "TeachingParams{" +
                "scale=" + scale +
                ", blackAndWhite=" + blackAndWhite +
                ", grayscale=" + grayscale +
                ", maxIterations=" + maxIterations +
                ", learningRate=" + learningRate +
                ", errorRate=" + errorRate +
                ", momentum=" + momentum +
                '}';
    }

    private String parse(Object element){
        if(element instanceof Boolean){
            if((Boolean)element)
                return "t";
            else
                return "";
        }
        if(element instanceof  Float){
            if((Float)element <= 0)
                return "";
        }
        if(element instanceof  Integer){
            if((Integer)element <= 0)
                return "";
        }
        return element.toString().replaceAll("\\.",",");
    }

    public float getLearningRate() {
        return learningRate;
    }

    public void setLearningRate(float learningRate) {
        if(learningRate<=0)throw new UnsupportedOperationException();
        this.learningRate = learningRate;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        if(scale<=0)throw new UnsupportedOperationException();
        this.scale = scale;
    }

    public boolean isBlackAndWhite() {
        return blackAndWhite;
    }

    public void setBlackAndWhite(boolean blackAndWhite) {
        this.blackAndWhite = blackAndWhite;
    }

    public boolean isGrayscale() {
        return grayscale;
    }

    public void setGrayscale(boolean grayscale) {
        this.grayscale = grayscale;
    }

    public int getMaxIterations() {
        return maxIterations;
    }

    public void setMaxIterations(int maxIterations) {
        if(maxIterations<=0)throw new UnsupportedOperationException();
        this.maxIterations = maxIterations;
    }

    public float getErrorRate() {
        return errorRate;
    }

    public void setErrorRate(float errorRate) {
        if(errorRate<=0)throw new UnsupportedOperationException();
        this.errorRate = errorRate;
    }

    public float getMomentum() {
        return momentum;
    }

    public void setMomentum(float momentum) {
        if(momentum<=0)throw new UnsupportedOperationException();
        this.momentum = momentum;
    }
}
