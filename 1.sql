CREATE OR REPLACE TRIGGER criptare_date_conectare
AFTER INSERT OR UPDATE on credential
FOR EACH ROW
declare
    --hash_parola RAW(100);
   -- raw_parola RAW(100);
   -- mod_operare NUMBER;
    --hash_parola_char VARCHAR2(200);
    
    raw_sir RAW(100);
    raw_parola RAW(100);
    rezultat RAW(100);
    cheie VARCHAR2(10):= '12345678';
    mod_operare NUMBER;
begin
   -- raw_parola := utl_i18n.string_to_raw(:new.password,'AL32UTF8');
  --  mod_operare := DBMS_CRYPTO.HASH_MD5;
  --  hash_parola := DBMS_CRYPTO.Hash (raw_parola, mod_operare);
  --  hash_parola_char := utl_i18n.raw_to_char(hash_parola,'AL32UTF');
   -- dbms_output.put_line('Parola Hash este ' || hash_parola_char);
  
  --  UPDATE credential SET password = hash_parola_char WHERE id = :new.id;
   -- dbms_output.put_line('ajunge aici ' || hash_parola_char);
   
    raw_sir := utl_i18n.string_to_raw(:new.password, 'AL32UTF8');
    raw_parola := utl_i18n.string_to_raw(cheie, 'AL32UTF8');
    
    mod_operare := DBMS_CRYPTO.ENCRYPT_DES + DBMS_CRYPTO.PAD_ZERO + DBMS_CRYPTO.CHAIN_ECB;
    
    rezultat := DBMS_CRYPTO.ENCRYPT(raw_sir, mod_operare, raw_parola);
    dbms_output.put_line('Rezultatul este: ' || rezultat);
    
    text_criptat := RAWTOHEX(rezultat);
   
   
   
end;
/
ALTER TRIGGER criptare_date_conectare ENABLE;
insert into credential(id, email, password, employee_id) values (8,'employee@gmail.com','employee_password',2);
COMMIT;
select * from credential;