package com.abc.account;

public enum AccountType {
  CHECKING("Checking Account"),
  SAVINGS("Savings Account"),
  MAXI_SAVINGS("Maxi Savings Account");

  private final String label;

  AccountType(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }

  public interface AccountTypeVisitor {
    void visitChecking();

    void visitSaving();

    void visitMaxiSavings();
  }

  public static final class Utils {
    public static void visit(AccountType type, AccountTypeVisitor visitor) {
      switch (type) {
        case CHECKING:
          visitor.visitChecking();
          break;
        case SAVINGS:
          visitor.visitSaving();
          break;
        case MAXI_SAVINGS:
          visitor.visitMaxiSavings();
          break;
      }
    }

    private Utils() {
    }
  }
}
