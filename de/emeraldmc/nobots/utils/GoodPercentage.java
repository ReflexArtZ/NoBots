package de.emeraldmc.nobots.utils;

public class GoodPercentage {
    private int factors;
    private int applicables;
    private int percentage;

    public GoodPercentage(int factors) {
        setFactors(factors);
        applicables = 0;
        calcPercentage();
    }


    public int calcPercentage() {
        percentage = (factors/applicables)*100;
        return percentage;
    }
    public void addApplicables() {
        if (applicables >= factors) return;
        applicables++;
        calcPercentage();
    }
    public void setFactors(int factors) {
        if (factors < 1) return;
        this.factors = factors;
    }

    public int getFactors() {
        return factors;
    }

    public int getApplicables() {
        return applicables;
    }

    public int getPercentage() {
        return percentage;
    }
}
