create or replace PROCEDURE add_new_transactions (
    send_account_number IN NUMBER,
    pay_account_number IN NUMBER,
    transaction_type IN VARCHAR2,
    transaction_amount IN NUMBER
)
AS
    sender_balance DECIMAL(8, 3);
    sender_status VARCHAR(255);
    sender_count NUMBER;
    transaction_fee NUMBER := 0; 
    payee_id_new NUMBER := 0;
    min_balance DECIMAL(8, 3) := 2000;
BEGIN
    -- Check if sender's account exists
    SELECT ACCOUNT_STATUS INTO sender_status FROM MYBANK_APP_ACCOUNT WHERE ACCOUNT_NUMBER = send_account_number;

    IF sender_status = 'open' THEN
        -- Check if sender has any payee

            -- Check if the payee exists for the sender
            SELECT COUNT(*) INTO payee_id_new FROM MYBANK_APP_PAYEE WHERE SENDER_ACCOUNT_NUMBER = send_account_number AND PAYEE_ACCOUNT_NUMBER = pay_account_number; 
            IF payee_id_new <> 0 THEN
                -- Calculate transaction fee if applicable
                IF LOWER(transaction_type) = 'imps' THEN
                    IF transaction_amount >= 1000 AND transaction_amount < 10000 THEN 
                        transaction_fee := 2;
                    ELSIF transaction_amount >= 10000 AND transaction_amount < 100000 THEN 
                        transaction_fee := 4;
                    ELSIF transaction_amount >= 100000 AND transaction_amount < 200000 THEN 
                        transaction_fee := 12;
                    ELSIF transaction_amount >= 200000 AND transaction_amount < 500000 THEN 
                        transaction_fee := 20;
                    END IF;
                END IF;

                IF LOWER(transaction_type) = 'rtgs' THEN
                    IF transaction_amount < 200000 THEN
                        RAISE_APPLICATION_ERROR(-20003, 'Transaction amount must be at least 200000');
                    END IF;
                END IF;

                -- Check sender's balance
                SELECT account_balance INTO sender_balance FROM MYBANK_APP_ACCOUNT WHERE ACCOUNT_NUMBER = send_account_number;
                    -- Ensure sufficient balance for the transaction
                    IF sender_balance >= transaction_amount + transaction_fee AND sender_balance - transaction_amount - transaction_fee >= min_balance THEN

                        -- Insert transaction into transaction table
                        INSERT INTO MYBANK_APP_Transaction 
                        VALUES (transactionid_seq.nextval, transaction_type, send_account_number, pay_account_number, SYSDATE, transaction_amount, 'processing');

                        -- Update sender's account balance
                        UPDATE MYBANK_APP_Account SET account_balance = account_balance - (transaction_amount + transaction_fee) WHERE ACCOUNT_NUMBER = send_account_number;

                        -- Update payee's account balance
                        UPDATE MYBANK_APP_Account SET account_balance = account_balance + transaction_amount WHERE ACCOUNT_NUMBER = pay_account_number;

                    ELSE
                        -- Insufficient balance
                        RAISE_APPLICATION_ERROR(-20001, 'Insufficient balance');
                    END IF;
            ELSE
                -- No payee for sender
                RAISE_APPLICATION_ERROR(-20002, 'No payee found for the sender');
            END IF;
    ELSE
        -- Sender not active
        RAISE_APPLICATION_ERROR(-20004, 'Sender not active');
    END IF;
END;