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
                    myLoanAmount -= totalPayment;
                    totalInterest += interest;
                }
                do {
                    interest = myLoanAmount * myMonthlyInterestRate;
                    principal = myMonthlyPayment - interest;
                    myLoanAmount -= principal;
                    totalInterest += interest;
					month++;
				} while (month < l.getNumberOfYears() * 12 && myLoanAmount >= myMonthlyPayment);
            } else {
                do {
                    interest = myLoanAmount * myMonthlyInterestRate;
                    principal = myMonthlyPayment - interest;
                    myLoanAmount -= principal;
                    totalInterest += interest;
					month++;
				} while (month < l.getNumberOfYears() * 12 && myLoanAmount >= myMonthlyPayment);
            }
			if (myLoanAmount < myMonthlyPayment) {
				// to do B1 same as B2
				interest = myLoanAmount * myMonthlyInterestRate;
				principal = myMonthlyPayment - interest;
				myLoanAmount -= myLoanAmount;
				totalInterest += interest;
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
		System.out.printf("Extra payment every month $%.2f%n", extraPay);
		double myLoanAmount = l.getLoanAmount();
		double myMonthlyInterestRate = l.getAnnualInterestRate() / 1200;
		double myMonthlyPayment = l.getMonthlyPayment();
		int month = 0;
		double interest = 0.0;
		double principal = 0.0;
		double totalInterest = 0.0;

		while (myLoanAmount >= (myMonthlyPayment + extraPay)) {
			// to do A2 same as A1
			// calculate the new month's interest based on myLoanAmount and myMonthlyInterestRate
			// calculate the principal paid this month from myMonthlyPayment minus the new month's interest
			// calculate the new myLoanAmount after minus this month's principal, then minus extra payment
			// add the interest paid this month to totalInterest
			// increment the month count
		}
		while (myLoanAmount >= myMonthlyPayment) {
			// to do B2 same as B1
			// calculate the new month's interest based on myLoanAmount and myMonthlyInterestRate
			// calculate the principal paid this month from myMonthlyPayment minus the new month's interest
			// calculate the new myLoanAmount after minus this month's principal
			// add the interest paid this month to totalInterest
			// increment the month count
		}
		if (myLoanAmount < myMonthlyPayment) {
			// to do
			// calculate the new month's interest based on myLoanAmount and myMonthlyInterestRate
			// add the interest paid this month to totalInterest
			// increment the month count
		}
		double interestSaved = l.getTotalInterest() - totalInterest;

		System.out.printf(
			"Total interest $%.2f%nTotal interest saved $%.2f%nTotal months %d %n",
			totalInterest, interestSaved, month
		);
	}
}
