package com.abc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.abc.BankConstants.AccountType;

// This class is stateless . only holds rules at the time of start up. In prod code it will be initialized from DB  
public class InterestRateRuleEngine {
	private Map<AccountType, List<Rule>> _rules = new HashMap<AccountType, List<Rule>>();

	public InterestRateRuleEngine()
	{
		init();
	}
	public BigDecimal calculate(Account account_) {
		BigDecimal interest = BigDecimal.ZERO;
		List<Rule> rules = _rules.get(account_.getAccountType());
		if (rules == null || rules.isEmpty())
			return interest;
		for (Rule rule : rules) {
			if (rule.isApplicable(account_.getAmount())) {
				interest = interest.add(rule.interest(account_.getAmount()));
			}
		}
		return interest;
	}

	public enum RateType {
		FLAT, RATE
	}

	public static class Rule {
		private BigDecimal _minApplicableAmount;
		private BigDecimal _maxApplicableAmount;
		private BigDecimal _minInterestAmount;
		private BigDecimal _maxInterestAmount;
		private BigDecimal _rate;
		private RateType _rateType;
		private AccountType _accountType;

		public AccountType getAccountType() {
			return _accountType;
		}

		public Rule(AccountType accountType_, BigDecimal minApplicableAmount_,
				BigDecimal maxApplicableAmount_, BigDecimal minInterestAmount_,
				BigDecimal maxInterestAmount_, BigDecimal rate_,
				RateType rateType_) {
			_minApplicableAmount = minApplicableAmount_;
			_maxApplicableAmount = maxApplicableAmount_;
			_minInterestAmount = minInterestAmount_;
			_maxInterestAmount = maxInterestAmount_;
			_rate = rate_;
			_rateType = rateType_;
			_accountType = accountType_;
		}

		public boolean isApplicable(BigDecimal amount_) {
			if (_minApplicableAmount != null
					&& amount_.compareTo(_minApplicableAmount) < 0)
				return false;
			if (_maxApplicableAmount != null
					&& amount_.compareTo(_maxApplicableAmount) >= 0)
				return false;
			return true;
		}

		public BigDecimal interest(BigDecimal amount_) {
			BigDecimal interest = BigDecimal.ZERO;
			BigDecimal amount = amount_;
			if (_minApplicableAmount != null) {
				amount = amount.subtract(_minApplicableAmount);
			}
			switch (_rateType) {
			case FLAT:
				interest = interest.add(_rate);
				break;
			case RATE:
				interest = interest.add(amount.multiply(_rate));
				break;
			}
			if (_minInterestAmount != null)
				interest = interest.add(_minInterestAmount);
			return interest;
		}
	}

	private void addRule(Rule rule) {
		List<Rule> rules = _rules.get(rule.getAccountType());
		if (rules == null) {
			rules = new ArrayList<Rule>();
			_rules.put(rule.getAccountType(), rules);
		}
		rules.add(rule);
	}

	public void init() {
		Rule checkingrule = new Rule(AccountType.CHECKING, null, null, null,
				null, new BigDecimal("0.001"), RateType.RATE);

		Rule savingAccountBelow1000rule = new Rule(AccountType.SAVINGS, null,
				new BigDecimal("1000"), null, null, new BigDecimal("0.1"),
				RateType.RATE);
		Rule savingAccountMoreThan1000rule = new Rule(AccountType.SAVINGS,
				new BigDecimal("1000"), null, new BigDecimal("1"), null,
				new BigDecimal("0.002"), RateType.RATE);

		Rule maxiSavingAccountBelow1000rule = new Rule(
				AccountType.MAXI_SAVINGS, null, new BigDecimal("1000"), null,
				null, new BigDecimal("0.02"), RateType.RATE);
		Rule maxisavingAccountBelow2000rule = new Rule(
				AccountType.MAXI_SAVINGS, new BigDecimal("1000"),
				new BigDecimal("2000"), new BigDecimal("20"), null,
				new BigDecimal("0.05"), RateType.RATE);
		Rule maxiSavingAccountMoreThan2000rule = new Rule(
				AccountType.MAXI_SAVINGS, new BigDecimal("2000"), null,
				new BigDecimal("70"), null, new BigDecimal("0.10"), RateType.RATE);
		addRule(checkingrule);
		addRule(savingAccountBelow1000rule);
		addRule(savingAccountMoreThan1000rule);
		addRule(maxiSavingAccountBelow1000rule);
		addRule(maxisavingAccountBelow2000rule);
		addRule(maxiSavingAccountMoreThan2000rule);
	}
}
