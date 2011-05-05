import groovy.transform.Canonical

// Normal class definitions. Boring stuff.

@Canonical
class Amount {
    Number value
    String unit

    Amount plus(Amount other) {
        checkUnit(other)
        new Amount(value + other.value, unit)
    }

    Amount minus(Amount other) {
        checkUnit(other)
        new Amount(value - other.value, unit)
    }

    Amount negative() {
        new Amount(-value, unit)
    }

    void checkUnit(Amount other) {
        if (unit != other.unit)
            throw new Exception("Invalid unit")
    }
}

@Canonical
class Account {
    String name
    Amount balance

    def deposit(amount) {
        balance += amount
    }

    def withdraw(amount) {
        balance -= amount
    }

    def transfer(Account to, Amount amount) {
        this.withdraw(amount)
        to.deposit(amount)
    }
}

// Amounts DSL definition.
@Category(Number)
class NumericAmounts {
    def get(String unit) {
        new Amount(this, unit) // 'this' is a Number.
    }
}

// Amounts DSL usage.
use(NumericAmounts) {
    assert 3.pesos + 2.pesos == 5.pesos
    assert 2.peras != 2.manzanas
    assert 5.kiwis - 7.kiwis == -2.kiwis // Amount.negative() called in -2.kiwis
}

// Accounts DSL definition.
def deposit = { Amount amount ->
    [into: { Account account ->
        account.deposit amount
    }]
}

def withdraw = { Amount amount ->
    [from: { Account account ->
        account.withdraw amount
    }]
}

def transfer = { Amount amount ->
    [from: { Account from ->
        [to: {Account to ->
            from.transfer to, amount
        }]
    }]
}

// Accounts and amounts DSL usage:
use(NumericAmounts) {
    def estebanAccount = new Account("Esteban", 500.pesos)

    deposit 100.pesos into estebanAccount
    assert estebanAccount.balance == 600.pesos

    withdraw 1000.pesos from estebanAccount
    assert estebanAccount.balance == -400.pesos

    def joaquinAccount = new Account("Joaquin", 4000.pesos)

    transfer 544.pesos from joaquinAccount to estebanAccount
    assert estebanAccount.balance == 144.pesos
    assert joaquinAccount.balance == 3456.pesos
}