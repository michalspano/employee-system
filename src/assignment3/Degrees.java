package assignment3;

/* In this Java enum, we keep the bonus corresponding to the academic title */

public enum Degrees {
    BSC(0.1),
    MSC(0.2),
    PHD(0.35);

    public final double bonus;

    Degrees(double bonus) {
        this.bonus = bonus;
    }
}