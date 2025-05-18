CREATE TABLE tracking_number (
    tracking_number_id TEXT PRIMARY KEY NOT NULL,
    customer_id UUID NOT NULL,
    origin_country_id CHAR(2) NOT NULL,
    destination_country_id CHAR(2) NOT NULL,
    metadata TEXT,
    parcel_created_at TIMESTAMPTZ,
    created_at TIMESTAMPTZ DEFAULT NOW(),
    updated_at TIMESTAMPTZ DEFAULT NOW()
);
