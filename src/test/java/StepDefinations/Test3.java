package StepDefinations;

import io.cucumber.java.en.Given;

public class Test3 {

	@Given("^it weighted {int} gramme(s)$")
	public void it_weighted_gramme(int a) {
		System.out.println(a);
		System.out.println("in method");
	}
}
