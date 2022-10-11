package assignment3;

/* In this Java enum, we keep the bonus corresponding to the academic title */

public enum Degrees {
    BSc(0.1),
    MSc(0.2),
    PhD(0.35);

    public final double bonus;

    Degrees(double bonus) {
        this.bonus = bonus;
    }
}