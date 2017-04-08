package server;

class Deposit {
    private String name;
    private String country;
    private String type;
    private String depositor;
    private String accountId;
    private long amountOnDeposit;
    private int profitability;
    private int termOfDeposit;

    public Deposit(String bank, String country, String type, String depositor, String accountId, long amountOnDeposit, int profitability, int termOfDeposit) {
        setBank(bank);
        setCountry(country);
        setType(type);
        setDepositor(depositor);
        setAccountId(accountId);
        setAmountOnDeposit(amountOnDeposit);
        setProfitability(profitability);
        setTermOfDeposit(termOfDeposit);
    }

    public Deposit(){}

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getType() {
        return type;
    }

    public String getDepositor() {
        return depositor;
    }

    public String getAccountId() {
        return accountId;
    }

    public long getAmountOnDeposit() {
        return amountOnDeposit;
    }

    public int getProfitability() {
        return profitability;
    }

    public void setBank(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDepositor(String depositor) {
        this.depositor = depositor;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setAmountOnDeposit(long amountOnDeposit) {
        this.amountOnDeposit = amountOnDeposit;
    }

    public void setProfitability(int profitability) {
        this.profitability = profitability;
    }

    public int getTermOfDeposit() {
        return termOfDeposit;
    }

    public void setTermOfDeposit(int termOfDeposit) {
        this.termOfDeposit = termOfDeposit;
    }
}
