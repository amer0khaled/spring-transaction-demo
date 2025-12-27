This demo demonstrates Spring transaction propagation using REQUIRED and REQUIRES_NEW.

The money transfer logic runs inside a single transaction using REQUIRED.
Audit logging runs in a separate transaction using REQUIRES_NEW.

When a transfer fails, all account balance updates are rolled back.
Audit log entries are still committed because they run in an independent transaction.

This clearly shows how REQUIRES_NEW creates a new transaction that is not affected by the rollback of the parent transaction.
