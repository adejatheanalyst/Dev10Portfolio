# Exercise03

1. Add a new class to the project named `Vault`.
2. `Vault` must implement `MoneyStorage`.
3. Complete the implementation. Add fields, constructors, and getters as required.
    (Refer to `Wallet` for inspiration.)
4. Rules:
    - Deposits must be positive values.
    - Cannot overdraw, but can return the remaining balance. 
    For example, if the balance is 45.0 and we withdraw(109.0) 
    the amount returned should be 45.0 and the balance should be 0.0 after the method call.