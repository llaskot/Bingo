package base;



import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;
import org.testng.collections.Maps;

import java.util.Iterator;
import java.util.Map;

    public abstract class MySoftAssert extends Assertion {
        //
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//





        protected final Map<AssertionError, IAssert<?>> m_errors = Maps.newLinkedHashMap();
        private static final String DEFAULT_SOFT_ASSERT_MESSAGE = "The following asserts failed:";



        protected abstract void doAssert(IAssert<?> a);
//        {
//            this.onBeforeAssert(a);
//
//            try {
//                a.doAssert();
//                this.onAssertSuccess(a);
//            } catch (AssertionError var6) {
//                System.out.println("\u001B[31m" +var6 + "\u001B[0m");
//                this.onAssertFailure(a, var6);
//                this.m_errors.put(var6, a);
//
//            } finally {
//                this.onAfterAssert(a);
//            }
//
//        }

        public void assertAll() {
            this.assertAll((String)null);
        }

        public void assertAll(String message) {
            if (!this.m_errors.isEmpty()) {
                StringBuilder sb = new StringBuilder(null == message ? "The following asserts failed:" : message);
                boolean first = true;
                Iterator var4 = this.m_errors.keySet().iterator();

                while(var4.hasNext()) {
                    AssertionError error = (AssertionError)var4.next();
                    if (first) {
                        first = false;
                    } else {
                        sb.append(",");
                    }

                    sb.append("\n\t");
                    sb.append(this.getErrorDetails(error));
                }

                throw new AssertionError(sb.toString());
            }
        }











}
