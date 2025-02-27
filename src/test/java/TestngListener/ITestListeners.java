package TestngListener;

import CaptureScreenshots.CaptureScreenshot;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ITestListeners implements ITestListener {
    //CaptureScreenshot capture;
    //all methods are overriding.
    @Override
    public void onTestStart(ITestResult result) {

        System.out.println("OnTestStart");
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        System.out.println("OnTestStart");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        System.out.println("OnTestFailure" + result.getName());
        //Assert.fail();capture.capturingscreenshots("failing frequently");
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        System.out.println("OnTestSkipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

        System.out.println("OnTestFailedwithinSuccessPercentage");
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

        System.out.println("OnTestStart");
    }

    @Override
    public void onStart(ITestContext context) {

        System.out.println("OnStart");
    }

    @Override
    public void onFinish(ITestContext context) {

        System.out.println("OnFinish");
    }
}
