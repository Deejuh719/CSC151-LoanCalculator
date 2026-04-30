package groupproject2026;
import java.util.*;

public class TestLoanClass {
    /** Main method */
	public static void main(String[] args) {
		// Create a Scanner
		Scanner input = new Scanner(System.in);

		// Enter yearly interest rate
		System.out.print("Enter annual interest rate, for example, 8.25: ");
		double annualInterestRate = input.nextDouble();

		// Enter number of years
		System.out.print("Enter number of years as an integer: ");
		int numberOfYears = input.nextInt();

		// Enter loan amount
		System.out.print("Enter loan amount, for example, 120000.95: ");
		double loanAmount = input.nextDouble();

		// Enter extra payment amount
		System.out.print("Enter extra payment amount, for example, 1000.00: ");
		double extraPaymentAmount = input.nextDouble();

		// Create Loan object
		Loan loan = new Loan(annualInterestRate, numberOfYears, loanAmount);

		// Display loan date, monthly payment, total payment, and total interest
		System.out.printf(
			"The loan was created on %s%n" +
			"The monthly payment is %.2f%n" +
			"The total payment is %.2f%n" +
			"The total interest is %.2f%n%n",
			loan.getLoanDate().toString(),
			loan.getMonthlyPayment(),
			loan.getTotalPayment(),
			loan.getTotalInterest()
		);

		extraPayFirstMonth(loan, extraPaymentAmount);
		System.out.println();
		extraPayEveryMonth(loan, extraPaymentAmount);
	}

	/** Extra payment first month only */
	public static void extraPayFirstMonth(Loan l, double extraPay) {
		System.out.printf("Extra payment only first month $%.2f%n", extraPay);
		double myLoanAmount = l.getLoanAmount();
		double myMonthlyInterestRate = l.getAnnualInterestRate() / 1200;
		double myMonthlyPayment = l.getMonthlyPayment();
		int month = 1;
		double interest = 0.0;
		double principal = 0.0;
		double totalInterest = 0.0;
        double totalPayment = 0.0;

		while (myLoanAmount >= myMonthlyPayment) {
			// to do A1 same as A2
			if(extraPay > 0) {
                for(month = 1; month < 2; month++) {
                    interest = myLoanAmount * myMonthlyInterestRate;
                    principal = myMonthlyPayment - interest;
                    totalPayment = principal + extraPay;
                    myLoanAmount = myLoanAmount - totalPayment;
                    totalInterest = totalInterest + interest;
                }
                do {
                    interest = myLoanAmount * myMonthlyInterestRate;
                    principal = myMonthlyPayment - interest;
                    myLoanAmount = myLoanAmount - principal;
                    totalInterest = totalInterest + interest;
					month++;
				} while (month < l.getNumberOfYears() * 12 && myLoanAmount >= myMonthlyPayment);
            } else {
                do {
                    interest = myLoanAmount * myMonthlyInterestRate;
                    principal = myMonthlyPayment - interest;
                    myLoanAmount = myLoanAmount - principal;
                    totalInterest = totalInterest + interest;
					month++;
				} while (month < l.getNumberOfYears() * 12 && myLoanAmount >= myMonthlyPayment);
            }
			if (myLoanAmount < myMonthlyPayment) {
				// to do B1 same as B2
				interest = myLoanAmount * myMonthlyInterestRate;
				principal = myMonthlyPayment - interest;
				myLoanAmount = myLoanAmount - myLoanAmount;
				totalInterest = totalInterest + interest;
			}
		}
		double interestSaved = l.getTotalInterest() - totalInterest;

		System.out.printf(
			"Total interest $%.2f%nTotal interest saved $%.2f%nTotal months %d %n",
			totalInterest, interestSaved, month
		);
	}

	/** Extra payment every month */
	public static void extraPayEveryMonth(Loan l, double extraPay) {
		/* 
		 * =================================================
		 * Don't merge with master branch yet.
		 * Technically done, but could be improved.
		 * Need to ask professor something first, though.
		 * =================================================
		 * 
		 */
		System.out.printf("Extra payment every month $%.2f%n", extraPay);
		double myLoanAmount = l.getLoanAmount();
		double myMonthlyInterestRate = l.getAnnualInterestRate() / 1200;
		double myMonthlyPayment = l.getMonthlyPayment();
		int month = 0;
		double interest = 0.0;
		double principal = 0.0;
		double totalInterest = 0.0;

		while (myLoanAmount >= (myMonthlyPayment + extraPay)) {
			interest = myLoanAmount * myMonthlyInterestRate;
			principal = myMonthlyPayment - interest;
			myLoanAmount -= principal + extraPay;
			totalInterest += interest;
			month++;
		}
		while (myLoanAmount >= myMonthlyPayment) {
			interest = myLoanAmount * myMonthlyInterestRate;
			principal = myMonthlyPayment - interest;
			myLoanAmount -= principal;
			totalInterest += interest;
			month++;
		}
		if (myLoanAmount < myMonthlyPayment) {
			interest = myLoanAmount * myMonthlyInterestRate;
			totalInterest += interest;
			month++;
		}
		double interestSaved = l.getTotalInterest() - totalInterest;

		System.out.printf(
			"Total interest $%.2f%n" +
			"Total interest saved $%.2f%n" +
			"Total months %d %n",
			totalInterest, interestSaved, month
		);
	}
}
