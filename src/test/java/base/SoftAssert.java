package base;

import org.testng.asserts.IAssert;

public class SoftAssert extends HardAssert{

    protected void doAssert(IAssert<?> a) {
        this.onBeforeAssert(a);

        try {
            a.doAssert();
            this.onAssertSuccess(a);
        } catch (AssertionError var6) {
            System.out.println("SOFT SOFT SOFT SOFT SOFT SOFT ");
            System.out.println("\u001B[31m" +var6 + "\u001B[0m");
            this.onAssertFailure(a, var6);
            this.m_errors.put(var6, a);

        } finally {
            this.onAfterAssert(a);
        }

    }

}
