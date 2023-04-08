package base;

import org.testng.asserts.IAssert;

public class HardAssert extends MySoftAssert {

    protected void doAssert(IAssert<?> a) {
        this.onBeforeAssert(a);

        try {
            a.doAssert();
            this.onAssertSuccess(a);
        } catch (AssertionError var6) {
            System.out.println("HARD HARD HARD HARD HARD ");
            System.out.println("\u001B[31m" +var6 + "\u001B[0m");
            this.onAssertFailure(a, var6);
            this.m_errors.put(var6, a);
            assertAll();
            throw new AssertionError(var6.toString());

        } finally {
            this.onAfterAssert(a);
        }

    }

}
