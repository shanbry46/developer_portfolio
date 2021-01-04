-- SELECT COUNT(*) FROM locations
-- SELECT ROUND(RANDOM() * COUNT(*))::INTEGER FROM drugs

-- DELETE FROM drug_price

-- DROP TABLE drug_price

/*
CREATE TABLE drug_price
(
    dp_id SERIAL PRIMARY KEY,
    date TIMESTAMP NOT NULL,
    price DECIMAL NOT NULL,
    drug_id INTEGER NOT NULL REFERENCES drugs(drug_id),
    location_id INTEGER NOT NULL REFERENCES locations(location_id)
)
*/

insert into drug_price (date, price, drug_id, zip_code) 
values
(CURRENT_TIMESTAMP, (SELECT CAST(ROUND((RANDOM() + 1) * 20) as DECIMAL(10,2))),
(SELECT drug_id FROM drugs LIMIT 1 OFFSET (SELECT ROUND(RANDOM() * COUNT(*))::INTEGER FROM drugs)),
(SELECT floor(random() * 99999 + 10000)::INTEGER))


/*
    // Query to test the database entries
    select  dp_id
        ,date
        ,price
        ,zip_code
        ,rxcui
        ,drug_name
        ,quantity
        from public.drug_price
        LIMIT 1000

    // Query to insert drug to database
    insert into drug_price (date, price, zip_code, rxcui, drug_name, quantity) values ('2020-10-29 21:59:23', 69.69, 69696, 104084, 'ranitidine 25 MG/ML Injectable Solution [Zantac]', 69);
*/