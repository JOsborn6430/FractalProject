import org.apache.commons.numbers.complex.Complex;
import org.apache.commons.numbers.fraction.BigFraction;
import org.apache.commons.numbers.fraction.Fraction;

public class ZFrac {
    public BigFraction a = BigFraction.ZERO;
    public BigFraction b = BigFraction.ZERO;

    public ZFrac(BigFraction real, BigFraction imaginary) {
        a = real;
        b = imaginary;
    }
    public ZFrac() {
        a = BigFraction.ZERO;
        b = BigFraction.ZERO;
    }

    public String toString() {
        return "[(" + a.toString() + ") + (" + b.toString() + ")i]";
    }

    public ZFrac add(ZFrac z) {
        return new ZFrac(this.a.add(z.a), this.b.add(z.b));
    }

    public ZFrac scale(int s) {
        ZFrac result = new ZFrac(this.a.multiply(s),this.b.multiply(s));
        return result;
    }

    public ZFrac multi(ZFrac z) {
        ZFrac result = new ZFrac(BigFraction.ZERO,BigFraction.ZERO);
        result.a = (this.a.multiply(z.a)).subtract(this.b.multiply(z.b));
        result.b = (this.a.multiply(z.b)).add(this.b.multiply(z.a));
        return result;
    }

    public ZFrac sqr() {
        ZFrac anchor1 = this;
        ZFrac anchor2 = this;
        return anchor1.multi(anchor2);
    }

    public Complex toDouble() {
        Complex result = Complex.ofCartesian(a.doubleValue(),b.doubleValue());
        return result;
    }
}
