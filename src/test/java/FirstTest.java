import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import java.io.IOException;

public class FirstTest {
    private StringCalculator p;
    private final double EPS = 1e-9;

    @Before
    public void before(){
         p = new StringCalculator();
    }

    @Test
    public void TestShouldReturnZeroForEmptyString(){
        Assert.assertEquals(0, p.Add(""));
    }

    @Test()
    public void TestShouldReturnOneNumber(){
        Assert.assertEquals(5,p.Add("5"));
    }

    @Test()
    public void TestShouldAddTwoNumbers(){
        Assert.assertEquals(30,p.Add("5,25"));
    }

    @Test()
    public void TestShouldAddUnknownAmountOfNumbers(){

        Assert.assertEquals(11,p.Add("5,2,4"));
    }

    @Test()
    public void TestShouldAllowNewLineBetweenNumbers(){

        Assert.assertEquals(6,p.Add("1\n2,3"));
    }
    @Test()
    public void TestShouldAllowCustomDelimiter(){
        Assert.assertEquals(3,p.Add("//[;]\n1;2"));
    }
    @Test()
    public void TestShouldRejectNegatives(){
        Assert.assertEquals(-1, p.Add("1,-3"));
    }

    @Test()
    public void TestShouldIgnoreNumberBiggerThan1000(){
        Assert.assertEquals(10, p.Add("5,1001,5"));
    }
    @Test
    public void TestShouldAllowCustomDelimiterCpntainsMoreThanOneSymbol(){
        Assert.assertEquals(6, p.Add( "//[***]\n1***2***3***"));
    }

    @Test
    public void TestShouldAllowAllowMultipleDelimiters(){
        Assert.assertEquals(8, p.Add( "//[+][%]\n1+6%1+"));
    }

    @Test
    public void TestShouldAllowAllowMultipleDelimitersWithLengthLongerThanOneChar(){
        Assert.assertEquals(6, p.Add(  "//[%][%%]\n1%2%%3"));
    }
}
