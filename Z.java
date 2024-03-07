public class Z {
    public double[] value = {0,0};

    public double mag() {
        return Math.sqrt(Math.pow(value[0],2) + Math.pow(value[1],2));
    }

    public Z multiply(Z z) {
        Z result = new Z(0, 0);
        result.value[0] = this.value[0] * z.value[0] -(this.value[1]*z.value[1]);
        result.value[1] = this.value[0] * z.value[1] + this.value[1] * z.value[0];
        return result;
    }

    public Z sqr() {
        Z result = new Z(0,0);
        result = this.multiply(this);
        return result;
    }

    public Z(double a, double b) {
        value[0] = a;
        value[1] = b;
    }

    public String toString() {
        return this.value[0] + " + i" + this.value[1];
    }

}
