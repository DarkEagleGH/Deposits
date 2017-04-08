package server;

class Deposit {
    private String accountId;
    private String name;
    private String country;
    private String type;
    private String depositor;
    private long amountOnDeposit;
    private int profitability;
    private int termOfDeposit;

    public Deposit(String accountId, String name, String country, String type, String depositor, long amountOnDeposit, int profitability, int termOfDeposit) {
        setAccountId(accountId);
        setName(name);
        setCountry(country);
        setType(type);
        setDepositor(depositor);
        setAmountOnDeposit(amountOnDeposit);
        setProfitability(profitability);
        setTermOfDeposit(termOfDeposit);
    }

    public Deposit(){}

    String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    String getDepositor() {
        return depositor;
    }

    public void setDepositor(String depositor) {
        this.depositor = depositor;
    }

    long getAmountOnDeposit() {
        return amountOnDeposit;
    }

    public void setAmountOnDeposit(long amountOnDeposit) {
        this.amountOnDeposit = amountOnDeposit;
    }

    int getProfitability() {
        return profitability;
    }

    public void setProfitability(int profitability) {
        this.profitability = profitability;
    }

    int getTermOfDeposit() {
        return termOfDeposit;
    }

    public void setTermOfDeposit(int termOfDeposit) {
        this.termOfDeposit = termOfDeposit;
    }
}
